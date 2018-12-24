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

import java.io.File
import java.net.URI
import java.nio.file.{FileSystems, Files, Path}

import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.datagetter.downloader.{DefaultImageDownloader, ImageDownloader}

/**
  * Class that can download all images from a gallery defined by a provided
  * Gallery instance.
  *
  * Most likely will be moved to a Core-Utils library or something like that.
  *
  * @since 1.3.4
  * @author bodand
  * @todo make a normal multithreaded downloader
  *       and annihilate the current one
  */
class GalleryDownloader {
  private val logger = LoggerFactory.getLogger("GalleryDownloader<" + toString.dropWhile(_ != '@').drop(1) + ">")

  /**
    * Downloads a gallery instance's images to the
    * {directory of the jar file}/dwn/{id of the gallery}
    * Each image will be named it's page number
    *
    * @param gly The Gallery to download
    *
    * @return The number of pages downloaded
    */
  def download(gly: Gallery): Unit =
    download(gly, s"dwn/${gly.id}")

  /**
    * Downloads a gallery instance's images to the specified path
    * Each image will be named it's page number
    *
    * @param gly  The Gallery to download
    * @param path The path to place the pages
    *
    * @return The number of pages downloaded
    */
  def download(gly: Gallery, path: String): Unit =
    download(gly, path, bonusData = false)

  /**
    * Downloads a gallery instance's images to the specified path.
    * Bonus data in json and xml format can be downloaded as hidden files,
    * named {gallery's id}.json and {gallery's id}.xml respectively.
    * On Unix based system a . will precede their names as per hiding rules.
    *
    * @param gly       The Gallery to download
    * @param path      The path to place the pages
    * @param bonusData Whether to download bonus data
    *
    * @return The number of pages downloaded
    */
  def download(gly: Gallery, path: String, bonusData: Boolean): Unit =
    download(gly, path, bonusData, hideBonusData = true)

  /**
    * Downloads a gallery instance's images to the specified path.
    * Bonus data in json and xml format can be downloaded.
    * named {gallery's id}.json and {gallery's id}.xml respectively.
    * The bonus files can be specified to be hidden.
    * On Unix based system a . will precede their names as per hiding rules.
    *
    * If bonusData is set to false, hideBonusData is not checked.
    *
    * @param gly           The Gallery to download
    * @param path          The path to place the pages
    * @param bonusData     Whether to download bonus data
    * @param hideBonusData Whether to hide the bonus data.
    *
    * @return The number of pages downloaded
    */
  def download(gly: Gallery, path: String, bonusData: Boolean, hideBonusData: Boolean): Unit = {
    logger.info(s"Downloading gallery ${gly.name}")

    FileUtils.deleteQuietly(new File(path))

    downloadImages(gly, path)

    createBonusDataIfNeeded(gly, path, bonusData, hideBonusData)
  }

  private def createBonusDataIfNeeded(gly: Gallery,
                                      path: String,
                                      bonusData: Boolean,
                                      hideBonusData: Boolean): Unit = {
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
                        jsonFile: File): Unit = {
    val xmlPath = FileSystems.getDefault.getPath(xmlStringPath)
    val jsonPath = FileSystems.getDefault.getPath(jsonStringPath)

    if (isWindows) {
      hideOnWin(xmlPath, jsonPath)
    } else {
      hideOnNix(gly, path, xmlFile, jsonFile)
    }
  }

  private def hideOnNix(gly: Gallery, path: String, xmlFile: File, jsonFile: File): Unit = {
    val hiddenXmlFile = new File(s"$path/.${gly.id}.xml")
    val hiddenJsonFile = new File(s"$path/.${gly.id}.json")

    FileUtils.moveFile(xmlFile, hiddenXmlFile)
    FileUtils.moveFile(jsonFile, hiddenJsonFile)
  }

  private def hideOnWin(xmlPath: Path, jsonPath: Path): Unit = {
    Files.setAttribute(xmlPath, "dos:hidden", true)
    Files.setAttribute(jsonPath, "dos:hidden", true)
  }

  case class WrittenFiles(xmlStringPath: String,
                          xmlFile: File,
                          jsonStringPath: String,
                          jsonFile: File)

  private def writeFiles(gly: Gallery, path: String): WrittenFiles = {
    val xmlStringPath = s"$path/${gly.id}.xml"
    val xmlFile = new File(xmlStringPath)
    val jsonStringPath = s"$path/${gly.id}.json"
    val jsonFile = new File(jsonStringPath)

    val xml = gly.toXml
    val json = gly.toJson

    FileUtils.touch(xmlFile)
    FileUtils.touch(jsonFile)

    FileUtils.writeStringToFile(xmlFile, xml, "UTF-8")
    FileUtils.writeStringToFile(jsonFile, json, "UTF-8")
    WrittenFiles(xmlStringPath, xmlFile, jsonStringPath, jsonFile)
  }

  private def downloadImages(gly: Gallery, path: String): Unit = {
    val downloader: ImageDownloader = {
      try {
        val akkaDownloaderClass = Class.forName("tk.iscorp.nhs.utils.AkkaImageDownloader")
        akkaDownloaderClass.newInstance().asInstanceOf[ImageDownloader]
      } catch {
        case _: Exception ⇒
          new DefaultImageDownloader
      }
    }

    val urls =
      for {
        i <- 1 to gly.pageCount
        dataId = gly.dataId
      } yield new URI(s"https://i.nhentai.net/galleries/$dataId/$i.jpg").toURL

    downloader.downloadImages(urls, path)
  }

  private def isWindows: Boolean = System.getProperty("os.name").startsWith("Windows")
}
