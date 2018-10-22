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
import java.time.{Instant, OffsetDateTime, ZoneOffset}

import org.jetbrains.annotations.{Nls, NonNls, NotNull, Nullable}
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
              @NonNls @NotNull val languages: Array[HentaiLanguage],
              @NonNls @NotNull val category: HentaiCategory,
              @NonNls @NotNull val pageCount: Int,
              @NonNls @NotNull val uploadDate: OffsetDateTime,
              @NonNls @NotNull val id: Int,
              @NonNls @NotNull val dataId: Int) extends XmlTransformable with JSONTransformable {

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
  @NotNull
  override def equals(@Nullable obj: scala.Any): Boolean =
  {
    obj match {
      case glr: Gallery ⇒
        glr.id == this.id
      case null ⇒ false
      case _ ⇒ false
    }
  }


  /**
    * Returns the gallery in xml format in a String.
    *
    * @example
    * <pre>
    * &lt;gallery xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    * xmlns="http://iscorp.tk/is-xml/NHentaiData"
    * xsi:schemaLocation="http://iscorp.tk/is-xml/NHentaiData http://users.atw.hu/bodand/info-xml/nhs-xml-data.xsd"
    * id="1" data-id="9" &gt;
    * &lt;name&gt;(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)&lt;/name&gt;
    * &lt;sec-name&gt;(C71) [???????? (??)] Eat The Rich! (??????? ???)&lt;/sec-name&gt;
    * &lt;parodies&gt;
    * &lt;parody name="pangya" amount="78"/&gt;
    * &lt;/parodies&gt;
    * &lt;characters&gt;
    * &lt;character name="kooh" amount="41"/&gt;
    * &lt;/characters&gt;
    * &lt;tags&gt;
    * &lt;tag name="lolicon" amount="45693"/&gt;&lt;tag name="catgirl" amount="5796"/&gt;&lt;tag name="gymshorts"
    * amount="176"/&gt;
    * &lt;/tags&gt;
    * &lt;artists&gt;
    * &lt;artist name="koari" amount="46"/&gt;
    * &lt;/artists&gt;
    * &lt;groups&gt;
    * &lt;group name="arisan-antenna" amount="34"/&gt;
    * &lt;/groups&gt;
    * &lt;languages&gt;
    * &lt;language name="Japanese" amount="129652"/&gt;
    * &lt;/languages&gt;
    * &lt;category name="Doujinshi" amount="138514"/&gt;
    * &lt;pages size="14"/&gt;
    * &lt;upload&gt;2014-06-28 14:12&lt;/upload&gt;
    * &lt;/gallery&gt;</pre>
    * @return A String with the gallery data in Xml format
    *
    * @since 1.1<br />
    *        1.3   - added the new data-id property<br />
    *        1.3.5 - Changed to return string to reduce dependency overhead<br />
    */
  @NotNull
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
       |  <upload>${uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</upload>
       |</gallery>""".stripMargin

  /**
    * Returns the gallery in JSON format in a string;
    *
    * @example
    * <pre>
    * {
    * "id": 1,
    * "data-id": 9,
    * "name": "(C71) [Arisan-Antenna (Koari)] Eat The Rich! (Sukatto Golf Pangya)",
    * "sec-name": "(C71) [ありさんアンテナ (小蟻)] Eat The Rich! (スカッとゴルフ パンヤ)",
    * "parodies": [
    * {
    * "parody": {
    * "amount": 78,
    * "name": "pangya"
    * }
    * }
    * ],
    * "characters": [
    * {
    * "character": {
    * "amount": 41,
    * "name": "kooh"
    * }
    * }
    * ],
    * "tags": [
    * {
    * "tag": {
    * "amount": 45693,
    * "name": "lolicon"
    * }
    * },
    * {
    * "tag": {
    * "amount": 5796,
    * "name": "catgirl"
    * }
    * },
    * {
    * "tag": {
    * "amount": 176,
    * "name": "gymshorts"
    * }
    * }
    * ],
    * "artists": [
    * {
    * "artist": {
    * "amount": 46,
    * "name": "koari"
    * }
    * }
    * ],
    * "groups": [
    * {
    * "group": {
    * "amount": 34,
    * "name": "arisan-antenna"
    * }
    * }
    * ],
    * "languages": [
    * {
    * "language": {
    * "amount": 129652,
    * "name": "Japanese"
    * }
    * }
    * ],
    * "category": {
    * "name": "Doujinshi",
    * "amount": 138514
    * },
    * "pages": 14,
    * "upload": "2014-06-28 14:12"
    * }</pre>
    * @return A String of the gallery in JSON format
    *
    * @since 1.2
    *        1.3   - Added the missing id field, and the new data-id field
    *        1.3.5 - Changed to return String to reduce dependencies
    */
  @NotNull
  @NonNls
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
  @NotNull
  @Nls
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
       |Uploaded: ${uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}
       |""".stripMargin.trim

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

/**
  * Utility object mainly used for testing.
  * Its dummy method is used when there's an error fetching actual galleries.
  */
object Gallery {

  /**
    * Dummy gallery used in testing and for returning for faulty html response
    * Can be used to test whether the processing of html was successful.
    *
    * The returned value can be overwritten by the parameters, each by their own
    * respective name.
    *
    * @return A Gallery object with imaginary data, and epoch creation time.
    *
    * @since 1.1
    */
  @NotNull
  def dummy(name: String = "Dummy Gallery",
            japName: String = "Dummi Garreri",
            tags: Array[HentaiTag] = Array(new HentaiTag("Mindfucking",
                                                         1)),
            parodies: Array[HentaiParody] = Array(new HentaiParody("InfoSoft The Animation",
                                                                   1)),
            characters: Array[HentaiCharacter] = Array(new HentaiCharacter("genderbent-bodand",
                                                                           1)),
            artists: Array[HentaiArtist] = Array(new HentaiArtist("Broccodile",
                                                                  69)),
            groups: Array[HentaiGroup] = Array(new HentaiGroup("InfoSoft HentaiBundle",
                                                               6)),
            languages: Array[HentaiLanguage] = Array(new EnglishHentai(69)),
            category: MangaHentai = new MangaHentai(96),
            pageCount: Int = 85,
            uploadDate: OffsetDateTime = Instant.EPOCH.atOffset(ZoneOffset.UTC),
            id: Int = 999999, dataId: Int = 9999999): Gallery =
  {
    new Gallery(name, japName, parodies, characters, tags, artists, groups,
                languages, category, pageCount, uploadDate, id, dataId)
  }
}
