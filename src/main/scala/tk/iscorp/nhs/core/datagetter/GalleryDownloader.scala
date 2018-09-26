/*******************************************************************************
 InfoSoft OpenSource Licence

 Copyright (c) 2018.

 Permission is hereby granted to absolutely free usage of this software in any way
 that doesn't conflict with the the licensee's local laws. Modification and
 redistribution of this software is permitted, but the changes must be stated, and
 the source software (this one) must be stated. Redistributed versions must be
 licensed under the InfoSoft OpenSource Licence. Projects using a (modified or not)
 version of this software, may or may not use the InfoSoft OpenSource Licence.
 Commercial distribution is permitted. This licence must be made available to the
 end user from within the program, and to all programmers from a IS-OSL.LICENCE.txt file.
 Inclusion of the licence in the source file(s) may be used instead of the IS-OSL.LICENCE.txt file.

 ******************************************************************************/

package tk.iscorp.nhs.core.datagetter

import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.SystemUtils
import org.slf4j.LoggerFactory
import tk.iscorp.nhs.core.data.Gallery

import java.io.{File, IOException}
import java.net.URI
import java.nio.file.{FileSystems, Files, Path}

class GalleryDownloader {
  private val logger = LoggerFactory.getLogger("Downloader")

  def download(gly: Gallery): Int = {
    download(gly, s"dwn/${gly.id}")
  }

  def download(gly: Gallery, path: String): Int = {
    download(gly, path, bonusData = false)
  }

  def download(gly: Gallery, path: String, bonusData: Boolean): Int = {
    download(gly, path, bonusData, hideBonusData = true)
  }

  def download(gly: Gallery, path: String, bonusData: Boolean, hideBonusData: Boolean): Int = {
    logger.info(s"Downloading gallery ${gly.name}")

    FileUtils.deleteQuietly(new File(path))

    val pagesDownloaded = downloadImages(gly, path)

    createBonusDataIfNeeded(gly, path, bonusData, hideBonusData)

    pagesDownloaded
  }

  private def createBonusDataIfNeeded(gly: Gallery,
                                      path: String,
                                      bonusData: Boolean,
                                      hideBonusData: Boolean): Any = {
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
                        jsonFile: File): Any = {
    val xmlPath = FileSystems.getDefault.getPath(xmlStringPath)
    val jsonPath = FileSystems.getDefault.getPath(jsonStringPath)

    if (SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_OS2) {
      hideOnWin(xmlPath, jsonPath)
    } else {
      hideOnNix(gly, path, xmlFile, jsonFile)
    }
  }

  private def hideOnNix(gly: Gallery,
                        path: String,
                        xmlFile: File,
                        jsonFile: File): Unit = {
    val hiddenXmlFile = new File(s"$path/.${gly.id}.xml")
    val hiddenJsonFile = new File(s"$path/.${gly.id}.json")

    FileUtils.moveFile(xmlFile, hiddenXmlFile)
    FileUtils.moveFile(jsonFile, hiddenJsonFile)
  }

  private def hideOnWin(xmlPath: Path, jsonPath: Path): Path = {
    Files.setAttribute(xmlPath, "dos:hidden", true)
    Files.setAttribute(jsonPath, "dos:hidden", true)
  }

  case class WrittenFiles(xmlStringPath: String, xmlFile: File, jsonStringPath: String, jsonFile: File)

  private def writeFiles(gly: Gallery,
                         path: String): WrittenFiles = {
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

  private def downloadImages(gly: Gallery, path: String): Int = {
    var pagesDownloaded: Int = 0
    for {
      i ← 1 to gly.pageCount
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
}
