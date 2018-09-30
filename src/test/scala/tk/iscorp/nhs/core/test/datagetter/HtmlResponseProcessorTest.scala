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

import org.apache.commons.io.FileUtils
import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.datagetter.HtmlResponseProcessor
import tk.iscorp.nhs.core.test.GalleryDataSupplier

import java.io.File

@RunWith(classOf[JUnitRunner])
class HtmlResponseProcessorTest extends WordSpec {
  private val dummyGallery = Gallery.dummy()
  private val testGallery = GalleryDataSupplier.no1
  private val testGalleryWithISODate = GalleryDataSupplier.no1(true)
  private var htp: HtmlResponseProcessor = _
  "An HtmlResponseProcessor" when {
    "created" should {
      "initialize" in {
        htp = new HtmlResponseProcessor
      }
    }
    "asked to process data" should {
      "return dummy" when {
        "data is emtpy" in {
          assertEquals(dummyGallery, htp.processHtmlToGallery(""))
        }
        "html doesn't contain needed data" in {
          val brokenHTMLFile =
            new File(getClass.getClassLoader.getResource("brokenHTML.html").toURI)
          val brokenHTML = FileUtils.readFileToString(brokenHTMLFile, "UTF-8")

          assertEquals(
            dummyGallery,
            htp.processHtmlToGallery(brokenHTML)
          )
        }
        "nhentai 404 is found" in {
          val nh404File = new File(getClass.getClassLoader.getResource("nhentai404.html").toURI)
          val nh404 = FileUtils.readFileToString(nh404File, "UTF-8")

          assertEquals(
            dummyGallery,
            htp.processHtmlToGallery(nh404)
          )
        }
      }
      "return data in Gallery" when {
        "html is good" when {
          val nhentaiHtmlFile =
            new File(getClass.getClassLoader.getResource("nhentaiid1.html").toURI)
          val nhPage = FileUtils.readFileToString(nhentaiHtmlFile, "utf-8")
          "iso date not requested" in {

            assertEquals(
              testGallery,
              htp.processHtmlToGallery(nhPage)
            )
          }
          "iso date requested" in {
            assertEquals(
              testGalleryWithISODate,
              htp.processHtmlToGallery(nhPage, isoDate = true)
            )
          }
        }
      }
    }
  }
}
