package tk.iscorp.nhs.data

import org.jetbrains.annotations.{NonNls, NotNull}
import tk.iscorp.nhs.data.hentai._

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
              @NonNls @NotNull val uploadDate: String) {

  override
  def toString: String = {
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

  private
  def stringifyArray(array: Array[_ <: HentaiData]): String = {
    array map (_.toString) mkString ", "
  }

  private
  def makeStringPossiblyPlural(amountToChangeOn: Int,
                               base: String,
                               sg: String = "", pl: String = "s"): String = {
    s"$base${if (amountToChangeOn == 1) sg else pl}"
  }
}

object Gallery {
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
            uploadDate: String = "20XX-01-01"): Gallery = {
    new Gallery(name, japName,
                parodies,
                characters,
                tags,
                artists,
                groups,
                languages,
                category,
                pageCount,
                uploadDate)
  }
}
