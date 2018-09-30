/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/
package tk.iscorp.nhs.core.datagetter

import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, Node}
import org.jsoup.select.{Elements, NodeVisitor}
import tk.iscorp.nhs.core.Utils
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.data.hentai._
import tk.iscorp.nhs.core.data.hentai.factory._

import scala.collection.mutable.ArrayBuffer
import scala.language.{postfixOps, reflectiveCalls}
import scala.reflect._

class HtmlResponseProcessor {
  private val regexText = "([\\s\\w.-]+)\\s+".r
  private val regexNumberWithCommaEmparethised = "\\((\\d+)(?:,(\\d+))?\\)".r

  def processHtmlToGallery(html: String, isoDate: Boolean = false): Gallery = {
    val document = Jsoup.parse(html)

    var fault = 0

    implicit val id: Int = try {
      document
        .selectFirst("div#cover>a")
        .attr("href")
        .substring(3)
        .takeWhile(_ != '/')
        .toInt
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.error(
          "√-1 => Error getting hentai id. Most likely means that it was deleted. " +
            s"Continuing to try, don't expect much."
        )
        fault += 1
        0
    }

    val dataId = try {
      Utils.logger.debug(document.toString)
      document
        .selectFirst("div#cover>a>img")
        .attr("data-src")
        .substring(32)
        .takeWhile(c ⇒ c.isDigit)
        .toInt
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.error(
          s"${if (id != 0) id else "√-1"} => Error getting data id. Most likely means that it was " +
            s"deleted. Continuing to try, don't expect much."
        )
        fault += 1
        0
    }

    val name: String = try {
      document.selectFirst("div#info h1").childNode(0).toString
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.error(
          s"${if (id != 0) id else "√-1"} => Error getting hentai name. Most likely means that it " +
            s"was deleted. Continuing to try, don't expect much."
        )
        fault += 1
        "!![[ERROR GETTING DOUJIN NAME]]!!"
    }
    val japName: String = try {
      document.selectFirst("div#info h2").childNode(0).toString
    } catch {
      case _: NullPointerException ⇒
        Utils.logger.info(s"$id => Gallery has no secondary/japanese name")
        ""
    }

    if (fault >= 2) {
      Utils.logger.error(
        s"$id => Error getting doujin data. Assuming it is deleted, returning with dummy Gallery."
      )
      Gallery.dummy()
    } else {
      try {
        val allTags =
          document.select("div#info section div.tag-container span.tags")

        val parodies: Array[HentaiParody] =
          getElements[HentaiParody, HentaiParodyFactory](allTags.remove(0))
        val characters: Array[HentaiCharacter] =
          getElements[HentaiCharacter, HentaiCharacterFactory](allTags.remove(0))
        val tags: Array[HentaiTag] =
          getElements[HentaiTag, HentaiTagFactory](allTags.remove(0))
        val artists: Array[HentaiArtist] =
          getElements[HentaiArtist, HentaiArtistFactory](allTags.remove(0))
        val groups: Array[HentaiGroup] =
          getElements[HentaiGroup, HentaiGroupFactory](allTags.remove(0))
        val languages: Array[HentaiLanguage] =
          getElements[HentaiLanguage, HentaiLanguageFactory](allTags.remove(0))
        val category: HentaiCategory = getCategory(allTags.remove(0))

        val pageCount = document
          .selectFirst("div#info>div")
          .ownText()
          .takeWhile(_ != ' ')
          .toInt

        val date = {
          val asd = document.selectFirst("div#info div time")
          if (isoDate) {
            asd.attr("datetime")
          } else {
            asd.text()
          }
        }

        new Gallery(
          name,
          japName,
          parodies,
          characters,
          tags,
          artists,
          groups,
          languages,
          category,
          pageCount,
          date,
          id,
          dataId
        )
      } catch {
        case _: NullPointerException ⇒
          Utils.logger.error(s"""Error getting data for "$name" using dummy Gallery data""")
          Gallery.dummy()
      }
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
        Utils.logger.warn(
          s"Hentai category not found, this is most likely some kind of error. Category\n" +
            s"\tfound: $name\n" +
            s"\texpected: manga|doujinshi"
        )
        new OtherCategoryHentai(amount)
    }

  }

  private def getElements[HType <: HentaiData: ClassTag,
                          HTypeFact <: HentaiDataFactory[HType]: ClassTag](
      raw: Element
  )(implicit id: Int): Array[HType] =
    if (raw.childNodeSize() > 0) {
      tagExtractor[HType, HTypeFact](raw.children())
    } else {
      Array.empty[HType]
    }

  private def tagExtractor[E <: HentaiData: ClassTag, EFactory <: HentaiDataFactory[E]: ClassTag](
      elem: Elements
  )(implicit id: Int): Array[E] = {
    val factoryClass = classTag[EFactory].runtimeClass
    val factory = factoryClass.newInstance().asInstanceOf[EFactory]

    val tmpArr: ArrayBuffer[E] = new ArrayBuffer[E]()
    var tmpName: String = ""
    var tmpInUse = false

    elem.traverse(new NodeVisitor {
      override def head(node: Node, depth: Int): Unit =
        if (depth > 0 && !node.toString.contains("<")) {
          node.toString match {
            case regexText(txt) ⇒
              tmpName = txt
              tmpInUse = true
            case regexNumberWithCommaEmparethised(n, m) ⇒
              tmpArr += factory.construct(
                tmpName,
                if (m != null) n + m toInt
                else n toInt
              )
              tmpInUse = false
            case _ ⇒
              Utils.logger.error(s"""$id => Error parsing tag: "${node.toString}"""")
          }
        }

      override def tail(node: Node, depth: Int): Unit = {}
    })
    tmpArr.toArray
  }
}
