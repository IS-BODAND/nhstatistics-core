/** *****************************************************************************
  * Copyright 2018 bodand
  * *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * *
  * http://www.apache.org/licenses/LICENSE-2.0
  * *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  * *****************************************************************************/
package tk.iscorp.nhs.core.datagetter

import java.io.{File, IOException}
import java.net.URI
import java.nio.file.{FileSystems, Files, Path}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import tk.iscorp.nhs.core.data.Gallery

class GalleryDownloader {
  private val logger = LoggerFactory.getLogger("Downloader")

  def download(gly: Gallery): Int =
    download(gly, s"dwn/${gly.id}")

  def download(gly: Gallery, path: String): Int =
    download(gly, path, bonusData = false)

  def download(gly: Gallery, path: String, bonusData: Boolean): Int =
    download(gly, path, bonusData, hideBonusData = true)

  def download(gly: Gallery, path: String, bonusData: Boolean, hideBonusData: Boolean): Int =
  {
    logger.info(s"Downloading gallery ${gly.name}")

    FileUtils.deleteQuietly(new File(path))

    val pagesDownloaded = downloadImages(gly, path)

    createBonusDataIfNeeded(gly, path, bonusData, hideBonusData)

    pagesDownloaded
  }

  private def createBonusDataIfNeeded(gly: Gallery,
                                      path: String,
                                      bonusData: Boolean,
                                      hideBonusData: Boolean): Any =
  {
    if (bonusData) {
      val writeFilesResult: WrittenFiles = writeFiles(gly, path)
      val WrittenFiles(xmlStringPath: String,
                       xmlFile: File,
                       jsonStringPath: String,
                       jsonFile: File) = writeFilesResult

      if (hideBonusData) {
        hideFiles(gly, path, xmlStringPath, xmlFile, jsonStringPath, jsonFile)
      }
    }
  }

  private def hideFiles(gly: Gallery,
                        path: String,
                        xmlStringPath: String,
                        xmlFile: File,
                        jsonStringPath: String,
                        jsonFile: File): Any =
  {
    val xmlPath = FileSystems.getDefault.getPath(xmlStringPath)
    val jsonPath = FileSystems.getDefault.getPath(jsonStringPath)

    if (isWindows) {
      hideOnWin(xmlPath, jsonPath)
    } else {
      hideOnNix(gly, path, xmlFile, jsonFile)
    }
  }

  private def hideOnNix(gly: Gallery, path: String, xmlFile: File, jsonFile: File): Unit =
  {
    val hiddenXmlFile = new File(s"$path/.${gly.id}.xml")
    val hiddenJsonFile = new File(s"$path/.${gly.id}.json")

    FileUtils.moveFile(xmlFile, hiddenXmlFile)
    FileUtils.moveFile(jsonFile, hiddenJsonFile)
  }

  private def hideOnWin(xmlPath: Path, jsonPath: Path): Path =
  {
    Files.setAttribute(xmlPath, "dos:hidden", true)
    Files.setAttribute(jsonPath, "dos:hidden", true)
  }

  case class WrittenFiles(xmlStringPath: String,
                          xmlFile: File,
                          jsonStringPath: String,
                          jsonFile: File)

  private def writeFiles(gly: Gallery, path: String): WrittenFiles =
  {
    val xmlStringPath = s"$path/${gly.id}.xml"
    val xmlFile = new File(xmlStringPath)
    val jsonStringPath = s"$path/${gly.id}.json"
    val jsonFile = new File(jsonStringPath)

    val xml = gly.toXml.toString()
    val json = gly.toJson.toJSONString

    FileUtils.touch(new File(path + "/"))
    FileUtils.touch(xmlFile)
    FileUtils.touch(jsonFile)

    FileUtils.writeStringToFile(xmlFile, xml, "UTF-8")
    FileUtils.writeStringToFile(jsonFile, json, "UTF-8")
    WrittenFiles(xmlStringPath, xmlFile, jsonStringPath, jsonFile)
  }

  private def downloadImages(gly: Gallery, path: String): Int =
  {
    val ret = new ArrayBuffer[Int]()
    val (pagesThird, remainder) = gly.pageCount match {
      case n if n % 3 == 0 ⇒
        (gly.pageCount / 3, 0)
      case n if n % 3 == 1 ⇒
        (gly.pageCount / 3, 1)
      case n if n % 3 == 2 ⇒
        (gly.pageCount / 3, 2)
    }
    val thirds =
      (
          1 until pagesThird, // 1/3
          pagesThird until pagesThird * 2, // 2(1/3)
          pagesThird * 2 to pagesThird * 3 + remainder // remaining
      )

    val f1 = Future {
      downloadThird(gly, path, thirds._1)
    }
    val f2 = Future {
      downloadThird(gly, path, thirds._2)
    }
    val f3 = Future {
      downloadThird(gly, path, thirds._3)
    }

    ret += Await.result(f1, Duration.Inf)
    ret += Await.result(f2, Duration.Inf)
    ret += Await.result(f3, Duration.Inf)

    ret.sum
  }

  private def downloadThird(gly: Gallery, path: String, range: Range): Int =
  {
    var pagesDownloaded: Int = 0
    for {
      i ← range
      dataId = gly.dataId
    } {
      val page = new File(s"$path/$i.jpg")
      val url = new URI(s"https://i.nhentai.net/galleries/$dataId/$i.jpg").toURL

      logger.debug(s"Downloading page $i")
      try {
        FileUtils.copyURLToFile(url, page)
      } catch {
        case e: IOException ⇒
          logger.error(s"Error downloading page $i")
          logger.error(e.getMessage)
      }

      if (page.exists()) {
        pagesDownloaded += 1
      }
    }
    pagesDownloaded
  }

  private def isWindows: Boolean = System.getProperty("os.name").startsWith("Windows")
}
