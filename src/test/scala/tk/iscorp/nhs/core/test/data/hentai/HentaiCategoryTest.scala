package tk.iscorp.nhs.core.test.data.hentai

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.{DoujinshiHentai, HentaiCategory, MangaHentai}

@RunWith(classOf[JUnitRunner])
class HentaiCategoryTest extends WordSpec {
  var testObject: HentaiCategory = _
  var testObject2: HentaiCategory = new MangaHentai(69)
  var testObject3: HentaiCategory = new MangaHentai(420)
  var testObject4: HentaiCategory = new DoujinshiHentai(69)
  var testObject5: HentaiCategory = new DoujinshiHentai(420)
  "A HentaiCategory" when {
    "created" should {
      "initialize" in {
        testObject = new MangaHentai(69)
        assertNotNull(testObject)
      }
    }
    "equality checked" should {
      "return true" when {
        "artists name and amount equal" in {
          assertTrue(testObject == testObject2)
        }
      }
      "return false" when {
        "only artist name equals" in {
          assertFalse(testObject2 == testObject3)
        }
        "only amount equal" in {
          assertFalse(testObject2 == testObject4)
        }
        "nothing equals" in {
          assertFalse(testObject2 == testObject5)
        }
      }
    }
    "inequality checked" should {
      "return false" when {
        "artists name and amount equal" in {
          assertFalse(testObject != testObject2)
        }
      }
      "return false" when {
        "only artist name equals" in {
          assertTrue(testObject2 != testObject3)
        }
        "only amount equal" in {
          assertTrue(testObject2 != testObject4)
        }
        "nothing equals" in {
          assertTrue(testObject2 != testObject5)
        }
      }
    }
    "xml asked" should {
      "return valid xml" in {
        val wanted = <category name="Manga" amount="69"/>.toString()
        assertEquals(wanted, testObject.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"name":"Manga","amount":69}"""
        assertEquals(wanted, testObject.toJson.toJSONString)
      }
    }
  }
}
