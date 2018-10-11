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
import tk.iscorp.nhs.core.data.hentai.HentaiCharacter

@RunWith(classOf[JUnitRunner])
class HentaiCharacterTest extends WordSpec {
  var testMan69: HentaiCharacter = _
  var testMan69_2: HentaiCharacter = new HentaiCharacter("testMan", 69)
  var testMan420: HentaiCharacter = new HentaiCharacter("testMan", 420)
  var testOMan69: HentaiCharacter = new HentaiCharacter("testOMan", 69)
  var testOMan420: HentaiCharacter = new HentaiCharacter("testOMan", 420)
  "A HentaiCharacter" when {
    "created" should {
      "initialize" in {
        testMan69 = new HentaiCharacter("testMan", 69)
        assertNotNull(testMan69)
      }
    }
    "equality checked" should {
      "return true" when {
        "character's name and amount equal" in {
          assertEquals(testMan69, testMan69_2)
        }
      }
      "return false" when {
        "only charecter's name equals" in {
          assertNotEquals(testMan69, testMan420)
        }
        "only amount equal" in {
          assertNotEquals(testMan69, testOMan69)
        }
        "nothing equals" in {
          assertNotEquals(testMan69, testOMan420)
        }
        "other isn't character" in {
          val obj = new Object
          assertNotEquals(testMan69, obj)
        }
      }
    }
    "xml asked" should {
      "return valid xml" in {
        val wanted = <character name="testMan" amount="69"/>.toString()
        assertEquals(wanted, testMan69.toXml)
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"character":{"amount":69,"name":"testMan"}}"""
        assertEquals(wanted, testMan69.toJson)
      }
    }
  }
}
