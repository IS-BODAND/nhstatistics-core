package tk.iscorp.nhs.core.test.data.hentai.factory

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.factory.HentaiCharacterFactory

@RunWith(classOf[JUnitRunner])
class HentaiCharacterFactoryTest extends WordSpec {
  var hcf: HentaiCharacterFactory = _
  "A HentaiCharacterFactory" when {
    "created" should {
      "initialize" in {
        hcf = new HentaiCharacterFactory
      }
    }
    ".construct called" should {
      "create new HentaiCharacter" in {
        assertNotNull(hcf.construct("asd", 2)(99999))
      }
    }
  }
}
