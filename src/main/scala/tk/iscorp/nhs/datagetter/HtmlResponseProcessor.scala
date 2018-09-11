package tk.iscorp.nhs.datagetter

import org.jsoup.Jsoup
import org.jsoup.nodes.Node
import org.jsoup.select.{Elements, NodeVisitor}
import tk.iscorp.nhs.Utils
import tk.iscorp.nhs.data.Gallery
import tk.iscorp.nhs.data.hentai._
import tk.iscorp.nhs.data.hentai.factory._

import scala.collection.mutable.ArrayBuffer
import scala.language.{postfixOps, reflectiveCalls}
import scala.reflect.{ClassTag, _}

class HtmlResponseProcessor {
  private val regexText                        = "([\\s\\w-]+)\\s+".r
  private val regexNumberWithCommaEmparethised = "\\((\\d+)(?:,(\\d+))?\\)".r

  def processHtmlToGallery(html: String): Gallery = {
    val document = Jsoup.parse(html)
    val name: String = document.selectFirst("div#info h1").childNode(0).toString
    val japName: String = try {
      document.selectFirst("div#info h2").childNode(0).toString
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.info("Gallery has no secondary name")
        ""
    }

    val allTags = document.select("div#info section div.tag-container span.tags")

    val parodiesRAW = allTags.remove(0)
    val parodies: Array[HentaiParody] = {
      if (parodiesRAW.childNodeSize() > 0) {
        tagExtractor[HentaiParody, HentaiParodyFactory](parodiesRAW.children())
      } else {
        Array.empty[HentaiParody]
      }
    }
    val charactersRAW = allTags.remove(0)
    val characters: Array[HentaiCharacter] = {
      if (charactersRAW.childNodeSize() > 0) {
        tagExtractor[HentaiCharacter, HentaiCharacterFactory](charactersRAW.children())
      } else {
        Array.empty[HentaiCharacter]
      }
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
        tagExtractor[HentaiArtist, HentaiArtistFactory](artistsRAW.children())
      } else {
        Array.empty[HentaiArtist]
      }
    }
    val groupsRAW = allTags.remove(0)
    val groups: Array[HentaiGroup] = {
      if (groupsRAW.childNodeSize() > 0) {
        tagExtractor[HentaiGroup, HentaiGroupFactory](groupsRAW.children())
      } else {
        Array.empty[HentaiGroup]
      }
    }
    val languagesRAW = allTags.remove(0)
    val languages: Array[HentaiLanguage] = {
      if (languagesRAW.childNodeSize() > 0) {
        tagExtractor[HentaiLanguage, HentaiLanguageFactory](languagesRAW.children())
      } else {
        Array.empty[HentaiLanguage]
      }
    }
    val categoryRAW = allTags.remove(0)
    val category = {
      val name = categoryRAW.child(0).ownText()
      val amount = categoryRAW.child(0).child(0).ownText() match {
        case regexNumberWithCommaEmparethised(n, m) ⇒
          if (m != null) n + m toInt else n toInt
        case _ ⇒
          Utils.logger.error(s"""Error parsing category amount: "$name"""")
          0
      }
      name match {
        case "manga" ⇒
          new MangaHentai(amount)
        case "doujinshi" ⇒
          new DoujinshiHentai(amount)
        case _ ⇒
          Utils.logger.warn(s"Hentai category not found, this is most likely some kind of error. Category " +
                               s"found: $name" +
                               s"expected: manga|doujinshi")
          new OtherCategoryHentai(amount)
      }
    }

    new Gallery(name, japName, parodies, characters, tags, artists, groups, languages, category,
                66, "2001-09-11")
  }

  def tagExtractor[E <: HentaiData : ClassTag, EConst <: HentaiDataFactory[E] : ClassTag](elem: Elements): Array[E] = {
    val constructorClass = classTag[EConst].runtimeClass
    val constructor = constructorClass.newInstance().asInstanceOf[EConst]

    val tmpArr: ArrayBuffer[E] = new ArrayBuffer[E]()
    var tmpName: String = ""
    var tmpInUse = false

    elem.traverse(new NodeVisitor {
      override def head(node: Node, depth: Int): Unit = {
        if (depth > 0 && !node.toString.contains("<")) {
          node.toString match {
            case regexText(txt) ⇒
              tmpName = txt
              tmpInUse = true
            case regexNumberWithCommaEmparethised(n, m) ⇒
              tmpArr += constructor.construct(tmpName, if (m != null) n + m toInt else n toInt)
              tmpInUse = false
            case _ ⇒
              Utils.logger.error(s"""Error parsing tag: "${node.toString}"""")
          }
        }
      }

      override def tail(node: Node, depth: Int): Unit = {}
    })
    tmpArr.toArray
  }
}
