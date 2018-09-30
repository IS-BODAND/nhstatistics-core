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
        "tag's name and amount equal" in {
          assertEquals(testObject, testObject2)
        }
      }
      "return false" when {
        "only tag names equals" in {
          assertNotEquals(testObject, testObject3)
        }
        "only amount equal" in {
          assertNotEquals(testObject, testObject4)
        }
        "nothing equals" in {
          assertNotEquals(testObject, testObject5)
        }
        "object not HentaiTag" in {
          val obj = new Object
          assertNotEquals(testObject, obj)
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
