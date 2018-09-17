package tk.iscorp.nhs.core.test.data.hentai

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.HentaiCharacter

@RunWith(classOf[JUnitRunner])
class HentaiCharacterTest extends WordSpec {
  var testObject: HentaiCharacter = _
  var testObject2: HentaiCharacter = new HentaiCharacter("testMan", 69)
  var testObject3: HentaiCharacter = new HentaiCharacter("testMan", 420)
  var testObject4: HentaiCharacter = new HentaiCharacter("testOMan", 69)
  var testObject5: HentaiCharacter = new HentaiCharacter("testOMan", 420)
  "A HentaiCharacter" when {
    "created" should {
      "initialize" in {
        testObject = new HentaiCharacter("testMan", 69)
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
        val wanted = <character name="testMan" amount="69"/>.toString()
        assertEquals(wanted, testObject.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"character":{"amount":69,"name":"testMan"}}"""
        assertEquals(wanted, testObject.toJson.toJSONString)
      }
    }
  }
}
