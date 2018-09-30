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
import tk.iscorp.nhs.core.data.hentai.factory.HentaiGroupFactory

@RunWith(classOf[JUnitRunner])
class HentaiGroupFactoryTest extends WordSpec {
  var hgf: HentaiGroupFactory = _
  "A HentaiGroupFactory" when {
    "created" should {
      "initialize" in {
        hgf = new HentaiGroupFactory
      }
    }
    ".construct called" should {
      "create new HentaiGroup" in {
        assertNotNull(hgf.construct("asd", 2)(99999))
      }
    }
  }
}
