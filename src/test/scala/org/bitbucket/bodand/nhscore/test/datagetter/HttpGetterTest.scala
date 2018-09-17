package org.bitbucket.bodand.nhscore.test.datagetter

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
