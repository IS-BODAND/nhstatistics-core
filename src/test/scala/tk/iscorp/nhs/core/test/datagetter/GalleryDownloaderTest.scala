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
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.data.hentai._
import tk.iscorp.nhs.core.datagetter.GalleryDownloader

import java.io.{File, FileFilter}

@RunWith(classOf[JUnitRunner])
class GalleryDownloaderTest extends WordSpec {
  private var downloader: GalleryDownloader = _
  private val testGallery = new Gallery("(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)",
                                        "(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)",
                                        Array(new HentaiParody("pangya", 78)),
                                        Array(new HentaiCharacter("kooh", 41)),
                                        Array(new HentaiTag("lolicon", 45693),
                                              new HentaiTag("catgirl", 5796),
                                              new HentaiTag("gymshorts", 176)),
                                        Array(new HentaiArtist("koari", 46)),
                                        Array(new HentaiGroup("arisan-antenna", 34)),
                                        Array(new JapaneseHentai(129652)),
                                        new DoujinshiHentai(138514),
                                        14, "June 28, 2014, 2:12 p.m.", 1, 9)
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

          val directory = new File(s"dwn/${testGallery.id}")
          assertTrue("Directory doesn't exist",
                     directory.exists())
          assertTrue("Directory's not a directory",
                     directory.isDirectory)

          assertEquals("Missing images",
                       testGallery.pageCount,
                       directory.listFiles().length)
        }
        "two parameters are present" in {
          downloader.download(testGallery, "dwn2")

          val directory = new File(s"dwn2")
          assertTrue("Directory doesn't exist",
                     directory.exists())
          assertTrue("Directory's not a directory",
                     directory.isDirectory)

          assertEquals("Missing images",
                       testGallery.pageCount,
                       directory.listFiles().length)
        }
        "three parameters are present" when afterWord("bonus data is") {
          "disabled" in {
            downloader.download(testGallery, "dwn3", bonusData = false)

            val directory = new File(s"dwn3")
            assertTrue("Directory doesn't exist",
                       directory.exists())
            assertTrue("Directory's not a directory",
                       directory.isDirectory)

            assertEquals("Missing images",
                         testGallery.pageCount,
                         directory.listFiles().length)
          }
          "enabled" in {
            downloader.download(testGallery, "dwn4", bonusData = true)

            val bonusFileAmount = 2 // .json .xml
            val directory = new File(s"dwn4")
            assertTrue("Directory doesn't exist",
                       directory.exists())
            assertTrue("Directory's not a directory",
                       directory.isDirectory)

            assertEquals("Missing images/data files",
                         testGallery.pageCount + bonusFileAmount,
                         directory.listFiles().length)
          }
        }
        "four parameters are present" when afterWord("bonus data is") {
          "enabled" when afterWord(", hiding is") {
            "enabled" in {
              downloader.download(testGallery, "dwn5", bonusData = true, hideBonusData = true)

              //directory
              val bonusFileAmount = 2 // .json .xml
              val directory = new File(s"dwn5")
              assertTrue("Directory doesn't exist", directory.exists())
              assertTrue("Directory's not a directory", directory.isDirectory)

              // images
              val imagesDownloaded = directory.listFiles().length
              assertEquals("Missing images",
                           testGallery.pageCount + bonusFileAmount,
                           imagesDownloaded)

              // hidden files
              val nonHiddenFiles = directory.listFiles(new FileFilter {
                override def accept(pathname: File): Boolean = {
                  !pathname.isHidden
                }
              })
              val nonHiddenFileAmount = nonHiddenFiles.length
              assertEquals("Idk what broke here, but something with hiding",
                           nonHiddenFileAmount,
                           testGallery.pageCount)
            }
            "disabled" in {
              downloader.download(testGallery, "dwn6", bonusData = true, hideBonusData = false)

              //directory
              val bonusFileAmount = 2 // .json .xml
              val directory = new File(s"dwn6")
              assertTrue("Directory doesn't exist", directory.exists())
              assertTrue("Directory's not a directory", directory.isDirectory)

              // images
              val imagesDownloaded = directory.listFiles().length
              assertEquals("Missing images",
                           testGallery.pageCount + bonusFileAmount,
                           imagesDownloaded)

              // hidden files
              val nonHiddenFiles = directory.listFiles(new FileFilter {
                override def accept(pathname: File): Boolean = {
                  !pathname.isHidden
                }
              })
              val nonHiddenFileAmount = nonHiddenFiles.length
              assertEquals("Idk what broke here, but something with hiding",
                           testGallery.pageCount + bonusFileAmount,
                           nonHiddenFileAmount)
            }
          }
          "disabled" when afterWord(", hiding is") {
            "enabled" in {
              downloader.download(testGallery, "dwn7", bonusData = false, hideBonusData = true)

              val directory = new File(s"dwn7")
              assertTrue("Directory doesn't exist",
                         directory.exists())
              assertTrue("Directory's not a directory",
                         directory.isDirectory)

              assertEquals("Missing images",
                           testGallery.pageCount,
                           directory.listFiles().length)
            }
            "disabled" in {
              downloader.download(testGallery, "dwn8", bonusData = false, hideBonusData = false)

              val directory = new File(s"dwn8")
              assertTrue("Directory doesn't exist",
                         directory.exists())
              assertTrue("Directory's not a directory",
                         directory.isDirectory)

              assertEquals("Missing images",
                           testGallery.pageCount,
                           directory.listFiles().length)
            }
          }
        }
      }
    }
  }
}
