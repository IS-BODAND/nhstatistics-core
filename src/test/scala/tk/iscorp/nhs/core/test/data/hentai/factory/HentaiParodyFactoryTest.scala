package tk.iscorp.nhs.core.test.data.hentai.factory

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.factory.HentaiParodyFactory

@RunWith(classOf[JUnitRunner])
class HentaiParodyFactoryTest extends WordSpec {
  var hpf: HentaiParodyFactory = _
  "A HentaiParodyFactory" when {
    "created" should {
      "initialize" in {
        hpf = new HentaiParodyFactory
      }
    }
    ".construct called" should {
      "create new HentaiParody" in {
        assertNotNull(hpf.construct("asd", 2)(99999))
      }
    }
  }
}
