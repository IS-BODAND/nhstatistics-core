package tk.iscorp.nhs.core.test.data.hentai

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.HentaiTag

@RunWith(classOf[JUnitRunner])
class HentaiTagTest extends WordSpec {
  var testObject: HentaiTag = _
  var testObject2: HentaiTag = new HentaiTag("testMan", 69)
  var testObject3: HentaiTag = new HentaiTag("testMan", 420)
  var testObject4: HentaiTag = new HentaiTag("testOMan", 69)
  var testObject5: HentaiTag = new HentaiTag("testOMan", 420)
  "A HentaiTag" when {
    "created" should {
      "initialize" in {
        testObject = new HentaiTag("testMan", 69)
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
        val wanted = <tag name="testMan" amount="69"/>.toString()
        assertEquals(wanted, testObject.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"tag":{"amount":69,"name":"testMan"}}"""
        assertEquals(wanted, testObject.toJson.toJSONString)
      }
    }
  }
}
