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
package tk.iscorp.nhs.core.test.data.hentai

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.{DoujinshiHentai, HentaiCategory, MangaHentai, OtherCategoryHentai}

@RunWith(classOf[JUnitRunner])
class HentaiCategoryTest extends WordSpec {
  var manga69: HentaiCategory = _
  var manga69_2: HentaiCategory = _
  var manga420: HentaiCategory = _
  var doujin69: HentaiCategory = _
  var doujin69_2: HentaiCategory = _
  var doujin420: HentaiCategory = _
  var other69: HentaiCategory = new OtherCategoryHentai(69)
  var other69_2: HentaiCategory = new OtherCategoryHentai(69)
  var other420: HentaiCategory = new OtherCategoryHentai(420)
  "A HentaiCategory" when {
    "created" should {
      "initialize" when {
        "it's manga" in {
          manga69 = new MangaHentai(69)
          manga69_2 = new MangaHentai(69)
          manga420 = new MangaHentai(420)
          assertNotNull(manga69)
          assertNotNull(manga69_2)
          assertNotNull(manga420)
        }
        "it's doujinshi" in {
          doujin69 = new DoujinshiHentai(69)
          doujin69_2 = new DoujinshiHentai(69)
          doujin420 = new DoujinshiHentai(420)
          assertNotNull(doujin69)
          assertNotNull(doujin69_2)
          assertNotNull(doujin420)
        }
        "it's trying to fuck us over" in {
          other69 = new OtherCategoryHentai(69)
          other69_2 = new OtherCategoryHentai(69)
          other420 = new OtherCategoryHentai(420)
          assertNotNull(other69)
          assertNotNull(other69_2)
          assertNotNull(other420)
        }
      }
    }
    "xml asked" should {
      "return valid xml" in {
        val wanted = <category name="Manga" amount="69"/>.toString()
        assertEquals(wanted, manga69.toXml)
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"name":"Manga","amount":69}"""
        assertEquals(wanted, manga69.toJson)
      }
    }
  }
  "MangaHentai" when {
    "equality checked" should {
      "return true" when {
        "category's name and amount equal" in {
          assertEquals(manga69, manga69_2)
        }
      }
      "return false" when {
        "only category's name equals" in {
          assertNotEquals(manga69, manga420)
        }
        "only amount equal" in {
          assertNotEquals(manga69, other69_2)
        }
        "nothing equals" in {
          assertNotEquals(manga69, other420)
        }
        "other isn't category" in {
          val obj = new Object
          assertNotEquals(manga69, obj)
        }
      }
    }
  }
  "DoujinshiHentai" when {
    "equality checked" should {
      "return true" when {
        "category's name and amount equal" in {
          assertEquals(doujin69, doujin69_2)
        }
      }
      "return false" when {
        "only category's name equals" in {
          assertNotEquals(doujin69, doujin420)
        }
        "only amount equal" in {
          assertNotEquals(doujin69, other69_2)
        }
        "nothing equals" in {
          assertNotEquals(doujin69, other420)
        }
        "other isn't category" in {
          val obj = new Object
          assertNotEquals(doujin69, obj)
        }
      }
    }
  }
  "OtherCategoryHentai" when {
    "equality checked" should {
      "return true" when {
        "category's name and amount equal" in {
          assertEquals(other69, other69_2)
        }
      }
      "return false" when {
        "only category's name equals" in {
          assertNotEquals(other69, other420)
        }
        "only amount equal" in {
          assertNotEquals(other69, manga69)
        }
        "nothing equals" in {
          assertNotEquals(other69, manga420)
        }
        "other isn't category" in {
          val obj = new Object
          assertNotEquals(other69, obj)
        }
      }
    }
  }
}
