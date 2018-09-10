package tk.iscorp.nhs.data.serialize

import org.w3c.tidy.Tidy

import java.io.{StringReader, StringWriter}

import scala.xml.{Elem, XML}

class StringToXML {
  def toXML(str: String): Option[Elem] = {
    try {
      val pureXml = purifyXML(str)
      val elem = XML.loadString(pureXml)
      println(elem)
      Some(elem)
    } catch {
      case e: Throwable ⇒
        e.printStackTrace()
        None
    }
  }

  private def purifyXML(str: String): String = {
    val tidy = createTidy(str)
    val inputString = new StringReader(str)
    val formedHtml = new StringWriter()
    tidy.parse(inputString, formedHtml)

    val fullXmlHtmlRegex = "(<html.*?>[\\s\\S]+?</html>)".r.unanchored

    formedHtml.toString match {
      case fullXmlHtmlRegex(goodString) ⇒
        goodString.replaceAllLiterally("<!DOCTYPE html>", "")
      case _ ⇒
        ""
    }
  }

  private def createTidy(str: String): Tidy = {
    val tidy = new Tidy
    tidy.setInputEncoding("UTF-8")
    tidy.setOutputEncoding("UTF-8")
    tidy.setXHTML(true)
    tidy.setXmlTags(true)
    tidy.setForceOutput(true)
    tidy.setShowWarnings(false)
    tidy
  }
}
