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
import tk.iscorp.nhs.core.data.hentai.HentaiGroup

@RunWith(classOf[JUnitRunner])
class HentaiGroupTest extends WordSpec {
  var testGroup69  : HentaiGroup = _
  var testGroup69_2: HentaiGroup = new HentaiGroup("testGroup", 69)
  var testGroup420 : HentaiGroup = new HentaiGroup("testGroup", 420)
  var testOGroup69 : HentaiGroup = new HentaiGroup("testOGroup", 69)
  var testOGroup420: HentaiGroup = new HentaiGroup("testOGroup", 420)
  "A HentaiGroup" when {
    "created" should {
      "initialize" in {
        testGroup69 = new HentaiGroup("testGroup", 69)
        assertNotNull(testGroup69)
      }
    }
    "equality checked" should {
      "return true" when {
        "group's name and amount equal" in {
          assertEquals(testGroup69, testGroup69_2)
        }
      }
      "return false" when {
        "only group name equals" in {
          assertNotEquals(testGroup69, testGroup420)
        }
        "only amount equal" in {
          assertNotEquals(testGroup69, testOGroup69)
        }
        "nothing equals" in {
          assertNotEquals(testGroup69, testOGroup420)
        }
        "other isn't group" in {
          val obj = new Object
          assertNotEquals(testGroup69, obj)
        }
      }
    }
    "xml asked" should {
      "return valid xml" in {
        val wanted = <group name="testGroup" amount="69"/>.toString()
        assertEquals(wanted, testGroup69.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"group":{"amount":69,"name":"testGroup"}}"""
        assertEquals(wanted, testGroup69.toJson.toJSONString)
      }
    }
  }
}
