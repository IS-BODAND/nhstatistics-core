package tk.iscorp.nhs.datagetter

import org.jsoup.Jsoup
import org.jsoup.nodes.Node
import org.jsoup.select.{Elements, NodeVisitor}
import tk.iscorp.nhs.Utils
import tk.iscorp.nhs.data.Gallery
import tk.iscorp.nhs.data.hentai._
import tk.iscorp.nhs.data.hentai.factory.{HentaiDataFactory, HentaiTagFactory}

import scala.collection.mutable.ArrayBuffer
import scala.language.{postfixOps, reflectiveCalls}
import scala.reflect.{ClassTag, _}

class HtmlResponseProcessor {
  def processHtmlToGallery(html: String): Gallery = {
    val document = Jsoup.parse(html)
    val name: String = document.selectFirst("div#info h1").childNode(0).toString
    val japName: String = document.selectFirst("div#info h2").childNode(0).toString

    val allTags = document.select("div#info section div.tag-container span.tags")

    val parodiesRAW = allTags.remove(0)
    val parodies: Array[HentaiParody] = {
      if (parodiesRAW.childNodeSize() > 0) {
        Array.empty[HentaiParody]
      } else {
        Array.empty[HentaiParody]
      } //todo
    }
    val charactersRAW = allTags.remove(0)
    val categories: Array[HentaiCategory] = {
      if (charactersRAW.childNodeSize() > 0) {
        Array.empty[HentaiCategory]
      } else {
        Array.empty[HentaiCategory]
      } //todo
    }
    val tagsRAW = allTags.remove(0)
    val tags: Array[HentaiTag] = {
      if (tagsRAW.childNodeSize() > 0) {
        tagExtractor[HentaiTag, HentaiTagFactory](tagsRAW.children())
      } else {
        Array.empty[HentaiTag]
      }
    }
    val artistsRAW = allTags.remove(0)
    val artists: Array[HentaiArtist] = {
      if (artistsRAW.childNodeSize() > 0) {
        Array.empty[HentaiArtist]
      } else {
        Array.empty[HentaiArtist]
      }
    }
    val groupsRAW = allTags.remove(0)
    val groups: Array[HentaiGroup] = {
      if (groupsRAW.childNodeSize() > 0) {
        Array.empty[HentaiGroup]
      } else {
        Array.empty[HentaiGroup]
      }
    }
    val languagesRAW = allTags.remove(0)
    val categoryRAW = allTags.remove(0)

    Gallery.dummy(name, japName, tags)
  }

  def tagExtractor[E <: HentaiData : ClassTag,
  EConst <: HentaiDataFactory[E] : ClassTag](elem: Elements): Array[E] = {
    val constructorClass = classTag[EConst].runtimeClass
    val constructor = constructorClass.newInstance().asInstanceOf[EConst]

    val tmpArr: ArrayBuffer[E] = new ArrayBuffer[E]()
    var tmpName: String = ""
    var tmpInUse = false

    val regexText = "([\\sa-zA-Z]+)\\s+".r
    val regexNumberWithCommaEmparethisised = "\\((\\d+)(?:,(\\d+))?\\)".r

    elem.traverse(new NodeVisitor {
      override def head(node: Node, depth: Int): Unit = {
        if (depth > 0 && !node.toString.contains("<")) {
          node.toString match {
            case regexText(txt)                           ⇒
              tmpName = txt
              tmpInUse = true
            case regexNumberWithCommaEmparethisised(n, m) ⇒
              tmpArr += constructor.construct(tmpName, if (m != null) n + m toInt else n toInt)
              tmpInUse = false
            case _                                        ⇒
              Utils.logger.error(s"""Error parsing tag: "${node.toString}"""")
          }
        }
      }

      override def tail(node: Node, depth: Int): Unit = {}
    })
    tmpArr.toArray
  }
}
