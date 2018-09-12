package tk.iscorp.nhs.datagetter

import org.apache.commons.io.FileUtils
import tk.iscorp.nhs.{Unused, Utils}

import java.io.File

import scala.language.postfixOps
import scala.xml.{Elem, Node, XML}

@Unused
class HTMLCache(cacheXml: String) {
  private val xml = XML.loadString(FileUtils.readFileToString(new File(cacheXml), "UTF-8"))

  def isCached(uri: String): Boolean = {
    xml \ "cache" exists (_ \@ "uri" == uri)
  }

  def cache(uri: String, html: String): Unit = {
    def addChild(n: Node, newChild: Node) = {
      n match {
        case Elem(prefix, label, attribs, scope, child@_*) =>
          Elem(prefix, label, attribs, scope, true, child ++ newChild: _*)
        case _ => Utils.logger.error("HTML Caching error - can only add children to elements!")
      }
    }

    val htmlFile = new File(s"cache/${uri.reverseIterator.takeWhile(_ != '/').toString()}.html")
    FileUtils.writeStringToFile(htmlFile, html, "UTF-8")

    addChild(xml, <cache uri={uri} fileName={htmlFile.getName}/> )
  }


}
