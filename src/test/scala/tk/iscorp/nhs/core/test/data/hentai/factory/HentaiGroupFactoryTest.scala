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
