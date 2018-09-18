/*******************************************************************************
 InfoSoft OpenSource Licence

 Copyright (c) 2018.

 Permission is hereby granted to absolutely free usage of this software in any way that doesn't conflict with the the licensee's local laws.
 Modification and redistribution of this software is permitted, but the changes must be stated, and the source software (this one) must be stated.
 Redistributed versions must be licensed under the InfoSoft OpenSource Licence.
 Projects using a modified or not, verion of this software, may or may not use the InfoSoft OpenSource Licence. Commercial distribution is permitted.
 This licence must be made available to the end user from within the program, and to all programmers from a IS-OSL.LICENCE.txt file.
 Inclusion of the licence in the source file(s) may be used instead of the IS-OSL.LICENCE.txt file.

 ******************************************************************************/

package tk.iscorp.nhs.core.data

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject
import tk.iscorp.nhs.core.data.hentai._

import java.util.{ArrayList ⇒ JList, HashMap ⇒ JMap}

import scala.xml.Node

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
  *
  * @author bodand
  *
  * @since 1.0
  */
@NonNls
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
              @NonNls @NotNull val uploadDate: String,
              @NotNull val id: Int) {


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
  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case glr: Gallery ⇒
        glr.id == this.id
      case _ ⇒ false
    }
  }

  /**
    * Returns the gallery in xml format. If doesn't contain any parodies for example, the parodies tag still exists,
    * just empty.
    *
    * @example
    * &lt;gallery id="1"&gt;<br/>
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
    * @return A scala.xml.Node object with the xml data in it.
    *
    * @since 1.1
    */
  def toXml: Node = {
    //fuckin indentation
<gallery id={s"$id"}>
  <name>{s"$name"}</name>
  <sec-name>{s"$japName"}</sec-name>
  <parodies>
    {parodies.map(_.toXml)}
  </parodies>
  <characters>
    {characters.map(_.toXml)}
  </characters>
  <tags>
    {tags.map(_.toXml)}
  </tags>
  <artists>
    {artists.map(_.toXml)}
  </artists>
  <groups>
    {groups.map(_.toXml)}
  </groups>
  <languages>
    {languages.map(_.toXml)}
  </languages>
  {category.toXml}
  <pages size={s"$pageCount"} />
  <upload>{s"$uploadDate"}</upload>
</gallery>

  }

  /**
    * Returns the gallery in Json format. Order of appearance may wary from object to object.
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
    *
    * @return A org.json.simple.JSONObject object of the gallery.
    *
    * @since 1.2
    */
  def toJson: JSONObject = {
    new JSONObject(new JMap[String, Any]() {
      {
        put("name", name)
        put("sec-name", japName)
        put("parodies", new JList[JSONObject] {
          {
            parodies.foreach(p ⇒ add(p.toJson))
          }
        })
        put("characters", new JList[JSONObject] {
          {
            characters.foreach(c ⇒ add(c.toJson))
          }
        })
        put("tags", new JList[JSONObject] {
          {
            tags.foreach(t ⇒ add(t.toJson))
          }
        })
        put("artists", new JList[JSONObject] {
          {
            artists.foreach(a ⇒ add(a.toJson))
          }
        })
        put("groups", new JList[JSONObject] {
          {
            groups.foreach(g ⇒ add(g.toJson))
          }
        })
        put("languages", new JList[JSONObject] {
          {
            languages.foreach(l ⇒ add(l.toJson))
          }
        })
        put("category", category.toJson)
        put("pages", new Integer(pageCount))
        put("upload", uploadDate)
      }
    })
  }

  /**
    * @return A pretty, printable string containing the data of the gallery.
    *
    * @since 1.0
    */
  override def toString: String = {
    s"""$name ${if (japName != "") s"($japName)" else ""}
       |
       |$printParodiesConditionally
       |
       |$printCharactersConditionally
       |
       |$printTagsConditionally
       |
       |$printArtistsConditionally
       |
       |$printGroupsConditionally
       |
       |${makeStringPossiblyPlural(languages.length, "Language")}: ${stringifyArray(languages)}
       |
       |Category: ${category.toString}
       |
       |${makeStringPossiblyPlural(pageCount, "Page")}: $pageCount
       |Uploaded: $uploadDate
     """.stripMargin
  }

  private def printArtistsConditionally = {
    if (artists.length > 0) {
      s"${makeStringPossiblyPlural(artists.length, "Artist")}: ${
        stringifyArray(artists)
      }"
    } else {
      "{No artists specified}"
    }
  }

  private def printTagsConditionally = {
    if (tags.length > 0) s"${makeStringPossiblyPlural(tags.length, "Tag")}: ${stringifyArray(tags)}" else ""
  }

  private def printCharactersConditionally = {
    if (characters.length > 0) {
      s"${makeStringPossiblyPlural(characters.length, "Character")}: " +
         s"${stringifyArray(characters)}"
    } else {
      "{No characters specified}"
    }
  }

  private def printParodiesConditionally = {
    if (parodies.length > 0) {
      s"${makeStringPossiblyPlural(parodies.length, "Parod", "y", "ies")}: " +
         s"${stringifyArray(parodies)}"
    } else {
      "{No parodies specified}"
    }
  }

  private def printGroupsConditionally: String = {
    if (groups.length > 0) {
      s"${makeStringPossiblyPlural(groups.length, "Group")}: ${stringifyArray(groups)}"
    } else {
      "{No groups specified}"
    }
  }

  private def stringifyArray(array: Array[_ <: HentaiData]): String = {
    array map (_.toString) mkString ", "
  }

  private def makeStringPossiblyPlural(amountToChangeOn: Int,
                                       base: String,
                                       sg: String = "", pl: String = "s"): String = {
    s"$base${if (amountToChangeOn == 1) sg else pl}"
  }
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
            uploadDate: String = "20XX-01-01",
            id: Int = 999999): Gallery = {
    new Gallery(name, japName,
                parodies,
                characters,
                tags,
                artists,
                groups,
                languages,
                category,
                pageCount,
                uploadDate,
                id)
  }
}
