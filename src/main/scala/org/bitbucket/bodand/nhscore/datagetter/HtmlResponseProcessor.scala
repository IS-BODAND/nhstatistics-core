package org.bitbucket.bodand.nhscore.datagetter

import org.bitbucket.bodand.nhscore.Utils
import org.bitbucket.bodand.nhscore.data.Gallery
import org.bitbucket.bodand.nhscore.data.hentai._
import org.bitbucket.bodand.nhscore.hentai._
import org.bitbucket.bodand.nhscore.hentai.factory._
import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, Node}
import org.jsoup.select.{Elements, NodeVisitor}

import scala.collection.mutable.ArrayBuffer
import scala.language.{postfixOps, reflectiveCalls}
import scala.reflect.{ClassTag, _}

class HtmlResponseProcessor {
  private val regexText                        = "([\\s\\w.-]+)\\s+".r
  private val regexNumberWithCommaEmparethised = "\\((\\d+)(?:,(\\d+))?\\)".r

  def processHtmlToGallery(html: String, isoDate: Boolean = false): Gallery = {
    val document = Jsoup.parse(html)

    implicit val id: Int = document
       .selectFirst("div#cover>a")
       .attr("href")
       .substring(3)
       .takeWhile(_ != '/')
       .toInt

    val name: String = try {
      document.selectFirst("div#info h1").childNode(0).toString
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.error(s"$id => Error getting hentai name. Most likely means that it was deleted. " +
                              s"Continuing to try, don't expect much.")
        "!![[ERROR GETTING DOUJIN NAME]]!!"
    }
    val japName: String = try {
      document.selectFirst("div#info h2").childNode(0).toString
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.info("Gallery has no secondary/japanese name")
        ""
    }


    try {
      val allTags = document.select("div#info section div.tag-container span.tags")

      val parodies: Array[HentaiParody] = getElements[HentaiParody, HentaiParodyFactory](allTags.remove(0))
      val characters: Array[HentaiCharacter] = getElements[HentaiCharacter, HentaiCharacterFactory](allTags.remove(0))
      val tags: Array[HentaiTag] = getElements[HentaiTag, HentaiTagFactory](allTags.remove(0))
      val artists: Array[HentaiArtist] = getElements[HentaiArtist, HentaiArtistFactory](allTags.remove(0))
      val groups: Array[HentaiGroup] = getElements[HentaiGroup, HentaiGroupFactory](allTags.remove(0))
      val languages: Array[HentaiLanguage] = getElements[HentaiLanguage, HentaiLanguageFactory](allTags.remove(0))
      val category: HentaiCategory = getCategory(allTags.remove(0))

      val pageCount = document.selectFirst("div#info>div").ownText().takeWhile(_ != ' ').toInt

      val date = {
        val asd = document.selectFirst("div#info div time")
        if (isoDate) {
          asd.attr("datetime")
        } else {
          asd.text()
        }
      }

      new Gallery(name, japName, parodies, characters, tags, artists, groups, languages, category,
                  pageCount, date, id)
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.error(s"""Error getting data for "$name" using dummy Gallery data""")
        Gallery.dummy()
    }
  }

  private def getCategory(categoryRAW: Element): HentaiCategory = {

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

  private def getElements[HType <: HentaiData : ClassTag, HTypeFact <: HentaiDataFactory[HType] : ClassTag]
  (raw: Element)
  (implicit id: Int) = {
    if (raw.childNodeSize() > 0) {
      tagExtractor[HType, HTypeFact](raw.children())
    } else {
      Array.empty[HType]
    }
  }

  def tagExtractor[E <: HentaiData : ClassTag, EConst <: HentaiDataFactory[E] : ClassTag](elem: Elements)
                                                                                         (implicit id: Int)
  : Array[E] = {
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
              Utils.logger.error(s"""$id => Error parsing tag: "${node.toString}"""")
          }
        }
      }

      override def tail(node: Node, depth: Int): Unit = {}
    })
    tmpArr.toArray
  }
}
