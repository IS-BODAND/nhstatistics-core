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
