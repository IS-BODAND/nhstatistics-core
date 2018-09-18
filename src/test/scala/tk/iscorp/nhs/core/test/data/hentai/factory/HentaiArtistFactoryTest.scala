package tk.iscorp.nhs.core.test.data.hentai.factory

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.core.data.hentai.factory.HentaiArtistFactory

@RunWith(classOf[JUnitRunner])
class HentaiArtistFactoryTest extends WordSpec {
  var haf: HentaiArtistFactory = _
  "A HentaiArtistFactory" when {
    "created" should {
      "initialize" in {
        haf = new HentaiArtistFactory
      }
    }
    ".construct called" should {
      "create new HentaiArtist" in {
        assertNotNull(haf.construct("asd", 2)(99999))
      }
    }
  }
}
