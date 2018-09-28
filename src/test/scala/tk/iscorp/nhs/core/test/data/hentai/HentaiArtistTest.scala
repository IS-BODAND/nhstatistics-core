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
import tk.iscorp.nhs.core.data.hentai.HentaiArtist

@RunWith(classOf[JUnitRunner])
class HentaiArtistTest extends WordSpec {
  var testMan69  : HentaiArtist = _
  var testMan69_2: HentaiArtist = new HentaiArtist("testMan", 69)
  var testMan420 : HentaiArtist = new HentaiArtist("testMan", 420)
  var testOMan69 : HentaiArtist = new HentaiArtist("testOMan", 69)
  var testOMan420: HentaiArtist = new HentaiArtist("testOMan", 420)
  "A HentaiArtist" when {
    "created" should {
      "initialize" in {
        testMan69 = new HentaiArtist("testMan", 69)
        assertNotNull(testMan69)
      }
    }
    "equality checked" should {
      "return true" when {
        "artist's name and amount equal" in {
          assertEquals(testMan69, testMan69_2)
        }
      }
      "return false" when {
        "only artist name equals" in {
          assertNotEquals(testMan69, testMan420)
        }
        "only amount equal" in {
          assertNotEquals(testMan69, testOMan69)
        }
        "nothing equals" in {
          assertNotEquals(testMan69, testOMan420)
        }
        "other isn't artist" in {
          val obj = new Object
          assertNotEquals(testMan69, obj)
        }
      }
    }
    "xml asked" should {
      "return valid xml" in {
        val wanted = <artist name="testMan" amount="69"/>.toString()
        assertEquals(wanted, testMan69.toXml.toString())
      }
    }
    "json asked" should {
      "return valid json" in {
        val wanted = """{"artist":{"amount":69,"name":"testMan"}}"""
        assertEquals(wanted, testMan69.toJson.toJSONString)
      }
    }
  }
}
