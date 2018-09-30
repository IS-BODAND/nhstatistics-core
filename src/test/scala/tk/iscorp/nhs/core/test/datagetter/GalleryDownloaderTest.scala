/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/
package tk.iscorp.nhs.core.test.datagetter

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.datagetter.GalleryDownloader
import tk.iscorp.nhs.core.test.GalleryDataSupplier

import java.io.{File, FileFilter}

@RunWith(classOf[JUnitRunner])
class GalleryDownloaderTest extends WordSpec {
  private var downloader: GalleryDownloader = _
  private val testGallery = GalleryDataSupplier.no1
  "A downloader" when {
    "created" should {
      "initialize" in {
        downloader = new GalleryDownloader
      }
    }
    "need a Gallery" should {
      "download" when {
        "one parameter is present" in {

          downloader.download(testGallery)

          checkDownloadedDirectory(s"dwn/${testGallery.id}", testGallery.pageCount)
        }
        "two parameters are present" in {
          downloader.download(testGallery, "dwn2")

          checkDownloadedDirectory("dwn2", testGallery.pageCount)
        }
        "three parameters are present" when afterWord("bonus data is") {
          "disabled" in {
            downloader.download(testGallery, "dwn3", bonusData = false)

            checkDownloadedDirectory("dwn3", testGallery.pageCount)
          }
          "enabled" in {
            downloader.download(testGallery, "dwn4", bonusData = true)

            val bonusFileAmount = 2 // .json .xml

            checkDownloadedDirectory("dwn4", testGallery.pageCount, bonusFileAmount)
          }
        }
        "four parameters are present" when afterWord("bonus data is") {
          "enabled" when afterWord(", hiding is") {
            "enabled" in {
              downloader.download(testGallery, "dwn5", bonusData = true, hideBonusData = true)

              val bonusFileAmount = 2 // .json .xml

              checkDownloadedDirectory("dwn5", testGallery.pageCount, bonusFileAmount)

            }
            "disabled" in {
              downloader.download(testGallery, "dwn6", bonusData = true, hideBonusData = false)

              val bonusFileAmount = 2 // .json .xml

              checkDownloadedDirectory("dwn6", testGallery.pageCount + bonusFileAmount, 0)
            }
          }
          "disabled" when afterWord(", hiding is") {
            "enabled" in {
              downloader.download(testGallery, "dwn7", bonusData = false, hideBonusData = true)

              checkDownloadedDirectory("dwn7", testGallery.pageCount)
            }
            "disabled" in {
              downloader.download(testGallery, "dwn8", bonusData = false, hideBonusData = false)

              checkDownloadedDirectory("dwn8", testGallery.pageCount)
            }
          }
        }
      }
    }
  }

  private def checkDownloadedDirectory(dirName: String, expectedAmount: Int): Unit = {
    val directory = new File(dirName)

    checkDirectoryness(directory)

    assertEquals("Missing images", expectedAmount, directory.listFiles().length)
  }

  private def checkDownloadedDirectory(
      dirName: String,
      expectedNormal: Int,
      expectedHidden: Int
  ): Unit = {
    val directory = new File(dirName)

    checkDirectoryness(directory)

    val hiddenFiles = directory
      .listFiles(new FileFilter {
        override def accept(pathname: File): Boolean =
          pathname.isHidden
      })
      .length

    val normalFiles = directory
      .listFiles(new FileFilter {
        override def accept(pathname: File): Boolean =
          !pathname.isHidden
      })
      .length

    assertEquals("Missing images", expectedNormal, normalFiles)

    assertEquals("Missing/More hidden files", expectedHidden, hiddenFiles)
  }

  private def checkDirectoryness(directory: File): Unit = {
    assertTrue("Directory doesn't exist", directory.exists())
    assertTrue("Directory's not a directory", directory.isDirectory)
  }
}
