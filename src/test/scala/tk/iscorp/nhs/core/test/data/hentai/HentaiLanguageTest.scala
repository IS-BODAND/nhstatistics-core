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
import tk.iscorp.nhs.core.data.hentai._

@RunWith(classOf[JUnitRunner])
class HentaiLanguageTest extends WordSpec {
  // Don't ask why I randomly decided to break convention with the field names
  private var english_69  : HentaiLanguage = _
  private var english_69_2: HentaiLanguage = _
  private var english_420 : HentaiLanguage = _
  private var rewrite_69  : HentaiLanguage = _
  private var rewrite_69_2: HentaiLanguage = _
  private var rewrite_420 : HentaiLanguage = _
  private var japan_69  : HentaiLanguage = _
  private var japan_69_2: HentaiLanguage = _
  private var japan_420 : HentaiLanguage = _
  private var chinese_69  : HentaiLanguage = _
  private var chinese_69_2: HentaiLanguage = _
  private var chinese_420 : HentaiLanguage = _
  private var trans_69  : HentaiLanguage = _
  private var trans_69_2: HentaiLanguage = _
  private var trans_420 : HentaiLanguage = _
  private var other_69  : HentaiLanguage = _
  private var other_69_2: HentaiLanguage = _
  private var other_420 : HentaiLanguage = _
  "A HentaiLanguage" when {
    "created" should {
      "initialize" when {
        "it's EnglishHentai" in {
          english_69 = new EnglishHentai(69)
          english_69_2 = new EnglishHentai(69)
          english_420 = new EnglishHentai(420)
          assertNotNull(english_69)
          assertNotNull(english_69_2)
          assertNotNull(english_420)
        }
        "it's JapaneseHentai" in {
          japan_69 = new JapaneseHentai(69)
          japan_69_2 = new JapaneseHentai(69)
          japan_420 = new JapaneseHentai(420)
          assertNotNull(japan_69)
          assertNotNull(japan_69_2)
          assertNotNull(japan_420)
        }
        "it's ChineseHentai" in {
          chinese_69 = new ChineseHentai(69)
          chinese_69_2 = new ChineseHentai(69)
          chinese_420 = new ChineseHentai(420)
          assertNotNull(chinese_69)
          assertNotNull(chinese_69_2)
          assertNotNull(chinese_420)
        }
        "it's TranslatedHentai" in {
          trans_69 = new TranslatedHentai(69)
          trans_69_2 = new TranslatedHentai(69)
          trans_420 = new TranslatedHentai(420)
          assertNotNull(trans_69)
          assertNotNull(trans_69_2)
          assertNotNull(trans_420)
        }
        "it's RewriteHentai" in {
          rewrite_69 = new RewriteHentai(69)
          rewrite_69_2 = new RewriteHentai(69)
          rewrite_420 = new RewriteHentai(420)
          assertNotNull(rewrite_69)
          assertNotNull(rewrite_69_2)
          assertNotNull(rewrite_420)
        }
        "it's OtherLanguageHentai" in {
          other_69 = new OtherLanguageHentai(69)
          other_69_2 = new OtherLanguageHentai(69)
          other_420 = new OtherLanguageHentai(420)
          assertNotNull(other_69)
          assertNotNull(other_69_2)
          assertNotNull(other_420)
        }
      }
    }
    "xml asked" should {
      "return valid xml" in {
        val wanted = <language name="English" amount="69"/>.toString()
        assertEquals(wanted, english_69.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"language":{"amount":69,"name":"English"}}"""
        assertEquals(wanted, english_69.toJson.toJSONString)
      }
    }
  }
  "EnglishHentai" when {
    "equality checked" should {
      "return true" when {
        "English and amount equal" in {
          assertEquals(english_69, english_69_2)
        }
      }
      "return false" when {
        "only English equals" in {
          assertNotEquals(english_69, english_420)
        }
        "only amount equal" in {
          assertNotEquals(english_69, rewrite_69)
        }
        "nothing equals" in {
          assertNotEquals(english_69, rewrite_420)
        }
        "other isn't language" in {
          val obj = new Object
          assertNotEquals(english_69, obj)
        }
      }
    }
  }
  "JapaneseHentai" when {
    "equality checked" should {
      "return true" when {
        "Japanese and amount equal" in {
          assertEquals(japan_69, japan_69_2)
        }
      }
      "return false" when {
        "only Japanese equals" in {
          assertNotEquals(japan_69, japan_420)
        }
        "only amount equal" in {
          assertNotEquals(japan_69, rewrite_69)
        }
        "nothing equals" in {
          assertNotEquals(japan_69, rewrite_420)
        }
        "other isn't language" in {
          val obj = new Object
          assertNotEquals(japan_69, obj)
        }
      }
    }
  }
  "ChineseHentai" when {
    "equality checked" should {
      "return true" when {
        "Chinese and amount equal" in {
          assertEquals(chinese_69, chinese_69_2)
        }
      }
      "return false" when {
        "only Chinese equals" in {
          assertNotEquals(chinese_69, chinese_420)
        }
        "only amount equal" in {
          assertNotEquals(chinese_69, rewrite_69)
        }
        "nothing equals" in {
          assertNotEquals(chinese_69, rewrite_420)
        }
        "other isn't language" in {
          val obj = new Object
          assertNotEquals(chinese_69, obj)
        }
      }
    }
  }
  "RewriteHentai" when {
    "equality checked" should {
      "return true" when {
        "Rewrite and amount equal" in {
          assertEquals(rewrite_69, rewrite_69_2)
        }
      }
      "return false" when {
        "only Rewrite equals" in {
          assertNotEquals(rewrite_69, rewrite_420)
        }
        "only amount equal" in {
          assertNotEquals(rewrite_69, trans_69)
        }
        "nothing equals" in {
          assertNotEquals(rewrite_69, trans_420)
        }
        "other isn't language" in {
          val obj = new Object
          assertNotEquals(rewrite_69, obj)
        }
      }
    }
  }
  "TranslatedHentai" when {
    "equality checked" should {
      "return true" when {
        "Translated and amount equal" in {
          assertEquals(trans_69, trans_69_2)
        }
      }
      "return false" when {
        "only Translated equals" in {
          assertNotEquals(trans_69, trans_420)
        }
        "only amount equal" in {
          assertNotEquals(trans_69, rewrite_69)
        }
        "nothing equals" in {
          assertNotEquals(trans_69, rewrite_420)
        }
        "other isn't language" in {
          val obj = new Object
          assertNotEquals(trans_69, obj)
        }
      }
    }
  }
  "OtherLanguageHentai" when {
    "equality checked" should {
      "return true" when {
        "Other and amount equal" in {
          assertEquals(other_69, other_69_2)
        }
      }
      "return false" when {
        "only Other equals" in {
          assertNotEquals(other_69, other_420)
        }
        "only amount equal" in {
          assertNotEquals(other_69, rewrite_69)
        }
        "nothing equals" in {
          assertNotEquals(other_69, rewrite_420)
        }
        "other isn't language" in {
          val obj = new Object
          assertNotEquals(other_69, obj)
        }
      }
    }
  }
}
