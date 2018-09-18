package tk.iscorp.nhs.core.test.data.hentai.factory

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.factory.HentaiLanguageFactory

@RunWith(classOf[JUnitRunner])
class HentaiLanguageFactoryTest extends WordSpec {
  var hlf: HentaiLanguageFactory = _
  "A HentaiLanguageFactory" when {
    "created" should {
      "initialize" in {
        hlf = new HentaiLanguageFactory
      }
    }
    ".construct called" should {
      "create new HentaiLanguage" in {
        assertNotNull(hlf.construct("asd", 2)(99999))
      }
    }
  }
}
