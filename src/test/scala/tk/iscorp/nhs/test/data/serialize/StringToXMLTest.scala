package tk.iscorp.nhs.test.data.serialize

import org.junit.Assert._
import org.junit.runner.RunWith
import org.scalatest.WordSpec
import org.scalatest.junit.JUnitRunner
import tk.iscorp.nhs.data.serialize.StringToXML
import tk.iscorp.nhs.datagetter.HttpGetter

@RunWith(classOf[JUnitRunner])
class StringToXMLTest extends WordSpec {
  private val httpGetter = new HttpGetter
  private val responseString = httpGetter.getHttpFromUri("https://nhentai.net/")
  "A StringtoXML" should {
    var stringToXML: StringToXML = null
    "initialize" in {
      stringToXML = new StringToXML
    }
    "return empty" when {
      "html is not valid" in {
        val invalidXML = responseString.substring(489, 682)
        assertEquals(None, stringToXML.toXML(invalidXML))
      }
    }
  }
}
