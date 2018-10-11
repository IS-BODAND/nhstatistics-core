/** *****************************************************************************
  * Copyright 2018 bodand
  * *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * *
  * http://www.apache.org/licenses/LICENSE-2.0
  * *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  * *****************************************************************************/
package tk.iscorp.nhs.core.data

import java.time.format.DateTimeFormatter
import java.time.{OffsetDateTime, ZoneOffset}

import org.jetbrains.annotations.{NonNls, NotNull}
import tk.iscorp.nhs.core.data.hentai._
import tk.iscorp.nhs.core.transform.{JSONTransformable, XmlTransformable}

/**
  * Represents a gallery on the nhentai site. Doujin = Gallery; Doujin = Galleries ;; Japanese is nice
  *
  * @param name       Name of the gallery
  * @param japName    Secondary/Japanese name; posibbly empty if the gallery has none
  * @param parodies   Series the doujin parodizes
  * @param characters Characters who appear in the doujin
  * @param tags       Amazing fetishes the doujin fulfils
  * @param artists    People who drew the doujin
  * @param groups     Groups containing the doujin
  * @param languages  Language of the doujin
  * @param category   Whether the doujin is a doujinshi or a manga (fan-fic/original)
  * @param pageCount  The amount of pages in the doujin
  * @param uploadDate Upload date of the doujin. Will change to some Date object.
  * @param id         ID of the doujin. Starts from 1.
  * @param dataId     ID of the doujin from the behind the scenes image storage.
  *                   https://i.nhentai.net/galleries/&lt;dataID&gt;/1.jpg
  *
  * @author bodand
  * @since       1.0 - Added class
  *        1.3.5 - Changed date to OffsetDateTime
  *              - Changed toXML/toJSON to return strings
  */
class Gallery(@NonNls @NotNull val name: String,
              @NonNls @NotNull val japName: String,
              @NonNls @NotNull val parodies: Array[HentaiParody],
              @NonNls @NotNull val characters: Array[HentaiCharacter],
              @NonNls @NotNull val tags: Array[HentaiTag],
              @NonNls @NotNull val artists: Array[HentaiArtist],
              @NonNls @NotNull val groups: Array[HentaiGroup],
              @NotNull val languages: Array[HentaiLanguage],
              @NotNull val category: HentaiCategory,
              @NotNull val pageCount: Int,
              @NonNls @NotNull val uploadDate: OffsetDateTime,
              @NotNull val id: Int,
              @NotNull val dataId: Int) extends XmlTransformable with JSONTransformable {

  /**
    * Checks equality of with another object.
    * True if other one is a gallery and their ids match
    *
    * @param obj The object to test equality on
    *
    * @return Whether the two objects equal based on their id field
    *
    * @since 1.0
    */
  override def equals(obj: scala.Any): Boolean =
    obj match {
      case glr: Gallery ⇒
        glr.id == this.id
      case _ ⇒ false
    }

  /**
    * Returns the gallery in xml format in a String.
    *
    * @example
    * &lt;gallery id="1" data-id="9"&gt;<br/>
    * &nbsp;&nbsp;&lt;name&gt;(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)&lt;/name&gt;<br/>
    * &nbsp;&nbsp;&lt;sec-name&gt;(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)&lt;/sec-name&gt;<br/>
    * &nbsp;&nbsp;&lt;parodies&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;parody name="pangya" amount="78"/&gt;<br/>
    * &nbsp;&nbsp;&lt;/parodies&gt;<br/>
    * &nbsp;&nbsp;&lt;characters&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;character name="kooh" amount="41"/&gt;<br/>
    * &nbsp;&nbsp;&lt;/characters&gt;<br/>
    * &nbsp;&nbsp;&lt;tags&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;tag name="lolicon" amount="45693"/&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;tag name="catgirl" amount="5796"/&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;tag name="gymshorts" amount="176"/&gt;<br/>
    * &nbsp;&nbsp;&lt;/tags&gt;<br/>
    * &nbsp;&nbsp;&lt;artists&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;artist name="koari" amount="46"/&gt;<br/>
    * &nbsp;&nbsp;&lt;/artists&gt;<br/>
    * &nbsp;&nbsp;&lt;groups&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;group name="arisan-antenna" amount="34"/&gt;<br/>
    * &nbsp;&nbsp;&lt;/groups&gt;<br/>
    * &nbsp;&nbsp;&lt;languages&gt;<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&lt;language name="Japanese" amount="129652"/&gt;<br/>
    * &nbsp;&nbsp;&lt;/languages&gt;<br/>
    * &nbsp;&nbsp;&lt;category name="Doujinshi" amount="138514"/&gt;<br/>
    * &nbsp;&nbsp;&lt;pages size="14"/&gt;<br/>
    * &nbsp;&nbsp;&lt;upload&gt;June 28, 2014, 2:12 p.m.&lt;/upload&gt;<br/>
    * &lt;/gallery&gt;<br/>
    * @return A String with the gallery data in Xml format
    *
    * @since 1.1
    *        1.3   - added the new data-id property
    *        1.3.5 - Changed to return string to reduce dependency overhead
    */
  override def toXml: String =
    s"""<gallery id="$id" data-id="$dataId">
       |  <name>$name</name>
       |  <sec-name>$japName</sec-name>
       |  <parodies>
       |    ${parodies.map(_.toXml).mkString("\n    ")}
       |  </parodies>
       |  <characters>
       |    ${characters.map(_.toXml).mkString("\n    ")}
       |  </characters>
       |  <tags>
       |    ${tags.map(_.toXml).mkString("\n    ")}
       |  </tags>
       |  <artists>
       |    ${artists.map(_.toXml).mkString("\n    ")}
       |  </artists>
       |  <groups>
       |    ${groups.map(_.toXml).mkString("\n    ")}
       |  </groups>
       |  <languages>
       |    ${languages.map(_.toXml).mkString("\n    ")}
       |  </languages>
       |  ${category.toXml}
       |  <pages size="$pageCount"/>
       |  <upload>${uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))}</upload>
       |</gallery>""".stripMargin

  /**
    * Returns the gallery in JSON format. Order of appearance may wary from object to object.
    *
    * @example
    * {<br/>
    * &nbsp;&nbsp;"characters": [<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"character": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 41,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "kooh"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;],<br/>
    * &nbsp;&nbsp;"parodies": [<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"parody": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 78,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "pangya"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;],<br/>
    * &nbsp;&nbsp;"pages": 14,<br/>
    * &nbsp;&nbsp;"id": 1,<br/>
    * &nbsp;&nbsp;"data-id": 9,<br/>
    * &nbsp;&nbsp;"languages": [<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"language": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 129652,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "Japanese"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;],<br/>
    * &nbsp;&nbsp;"artists": [<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"artist": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 46,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "koari"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;],<br/>
    * &nbsp;&nbsp;"upload": "June 28, 2014, 2:12 p.m.",<br/>
    * &nbsp;&nbsp;"name": "(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)",<br/>
    * &nbsp;&nbsp;"groups": [<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"group": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 34,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "arisan-antenna"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;],<br/>
    * &nbsp;&nbsp;"category": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;"name": "Doujinshi",<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;"amount": 138514<br/>
    * &nbsp;&nbsp;},<br/>
    * &nbsp;&nbsp;"sec-name": "(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)",<br/>
    * &nbsp;&nbsp;"tags": [<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"tag": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 45693,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "lolicon"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;},<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"tag": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 5796,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "catgirl"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;},<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;{<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"tag": {<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"amount": 176,<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"name": "gymshorts"<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;&nbsp;&nbsp;}<br/>
    * &nbsp;&nbsp;]<br/>
    * }
    * @return A String of the gallery in JSON format
    *
    * @since 1.2
    *        1.3   - Added the missing id field, and the new data-id field
    *        1.3.5 - Changed to return String to reduce dependencies
    */
  override def toJson: String =
    s"""{"id":$id,"pages":$pageCount,
       |"data-id":$dataId,"upload":"${uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))}",
       |"name":"$name",
       |"sec-name":"$japName",
       |"category":${category.toJson},
       |"characters":[${characters.map(_.toJson).mkString(",")}],
       |"parodies":[${parodies.map(_.toJson).mkString(",")}],
       |"languages":[${languages.map(_.toJson).mkString(",")}],
       |"artists":[${artists.map(_.toJson).mkString(",")}],
       |"groups":[${groups.map(_.toJson).mkString(",")}],
       |"tags":[${tags.map(_.toJson).mkString(",")}]}""".stripMargin

  /**
    * @return A pretty, printable string containing the data of the gallery.
    *
    * @since 1.0
    */
  override def toString: String =
    s"""$name ${if (japName != "") s"($japName)" else ""}
       |$printParodiesConditionally
       |$printCharactersConditionally
       |$printTagsConditionally
       |$printArtistsConditionally
       |$printGroupsConditionally
       |
       |${makeStringPossiblyPlural(languages.length, "Language")}: ${stringifyArray(languages)}
       |
       |Category: ${category.toString}
       |
       |${makeStringPossiblyPlural(pageCount, "Page")}: $pageCount
       |Uploaded: ${uploadDate.format(DateTimeFormatter.BASIC_ISO_DATE)}
     """.stripMargin

  private def printArtistsConditionally =
    if (artists.length > 0)
      s"${makeStringPossiblyPlural(artists.length, "Artist")}: ${stringifyArray(artists)}\n"
    else ""

  private def printTagsConditionally =
    if (tags.length > 0)
      s"${makeStringPossiblyPlural(tags.length, "Tag")}: ${stringifyArray(tags)}\n"
    else ""

  private def printCharactersConditionally =
    if (characters.length > 0) {
      s"${makeStringPossiblyPlural(characters.length, "Character")}: " +
      s"${stringifyArray(characters)}\n"
    } else ""

  private def printParodiesConditionally =
    if (parodies.length > 0) {
      s"${makeStringPossiblyPlural(parodies.length, "Parod", "y", "ies")}: " +
      s"${stringifyArray(parodies)}\n"
    } else ""

  private def printGroupsConditionally: String =
    if (groups.length > 0) {
      s"${makeStringPossiblyPlural(groups.length, "Group")}: ${stringifyArray(groups)}\n"
    } else ""

  private def stringifyArray(array: Array[_ <: HentaiData]): String =
    array map (_.toString) mkString ", "

  private def makeStringPossiblyPlural(amountToChangeOn: Int,
                                       base: String,
                                       sg: String = "",
                                       pl: String = "s"): String =
    s"$base${if (amountToChangeOn == 1) sg else pl}"
}

object Gallery {

  /**
    * Dummy gallery used in testing and for returning for faulty html response
    *
    * @return A Gallery object with bullshit data
    *
    * @since 1.1
    */
  def dummy(name: String = "Dummy Gallery",
            japName: String = "Dummi Garreri",
            tags: Array[HentaiTag] = Array(new HentaiTag("Mindfucking", 1)),
            parodies: Array[HentaiParody] = Array(new HentaiParody("InfoSoft The Animation", 1)),
            characters: Array[HentaiCharacter] = Array(new HentaiCharacter("genderbent-bodand", 1)),
            artists: Array[HentaiArtist] = Array(new HentaiArtist("Broccodile", 69)),
            groups: Array[HentaiGroup] = Array(new HentaiGroup("InfoSoft HentaiBundle", 6)),
            languages: Array[HentaiLanguage] = Array(new EnglishHentai(69)),
            category: MangaHentai = new MangaHentai(96),
            pageCount: Int = 85,
            uploadDate: OffsetDateTime = OffsetDateTime.of(1970, 1,
                                                           1, 0,
                                                           0, 0,
                                                           1, ZoneOffset.UTC),
            id: Int = 999999,
            dataId: Int = 9999999): Gallery =
    new Gallery(name,
                japName,
                parodies,
                characters,
                tags,
                artists,
                groups,
                languages,
                category,
                pageCount,
                uploadDate,
                id,
                dataId)
}
