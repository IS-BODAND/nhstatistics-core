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

package tk.iscorp.nhs.core.test.datagetter

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.datagetter.HttpGetter

@RunWith(classOf[JUnitRunner])
class HttpGetterTest extends WordSpec {
  "An HttpGetter" should {
    var httpGetter: HttpGetter = null
    "initialize" in {
      httpGetter = new HttpGetter
    }
    "return empty" when {
      "uri is not uri" in {
        assertEquals("", httpGetter.getResponseFromUri("janus pannonius"))
      }
      "uri is not accepted" in {
        assertEquals("", httpGetter.getResponseFromUri("mailto:///janus@pannoni.us"))
      }
      "uri is null" in {
        assertEquals("", httpGetter.getResponseFromUri(null))
      }
    }
    "return html from nhentai" in {
      val html = httpGetter.getResponseFromUri("https://nhentai.net/")
      assertFalse(html.isEmpty)
    }
  }
}
