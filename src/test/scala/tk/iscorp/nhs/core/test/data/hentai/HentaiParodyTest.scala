package tk.iscorp.nhs.core.test.data.hentai

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.HentaiParody

@RunWith(classOf[JUnitRunner])
class HentaiParodyTest extends WordSpec {
  var testObject: HentaiParody = _
  var testObject2: HentaiParody = new HentaiParody("testMan", 69)
  var testObject3: HentaiParody = new HentaiParody("testMan", 420)
  var testObject4: HentaiParody = new HentaiParody("testOMan", 69)
  var testObject5: HentaiParody = new HentaiParody("testOMan", 420)
  "A HentaiParody" when {
    "created" should {
      "initialize" in {
        testObject = new HentaiParody("testMan", 69)
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
        val wanted = <parody name="testMan" amount="69"/>.toString()
        assertEquals(wanted, testObject.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"parody":{"amount":69,"name":"testMan"}}"""
        assertEquals(wanted, testObject.toJson.toJSONString)
      }
    }
  }
}
