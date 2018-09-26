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

package tk.iscorp.nhs.core.test.data.hentai.factory

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.factory.HentaiTagFactory

@RunWith(classOf[JUnitRunner])
class HentaiTagFactoryTest extends WordSpec {
  var htf: HentaiTagFactory = _
  "A HentaiTagFactory" when {
    "created" should {
      "initialize" in {
        htf = new HentaiTagFactory
      }
    }
    ".construct called" should {
      "create new HentaiTag" in {
        assertNotNull(htf.construct("asd", 2)(99999))
      }
    }
  }
}
