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
              @NotNull val language: HentaiLanguage,
              @NotNull val category: HentaiCategory,
              @NotNull val pageCount: Int,
              @NonNls @NotNull val uploadDate: String) {

  override
  def toString: String = {
    s"""$name ($japName)
       |
       |${makeStringPossiblyPlural(parodies.length, "Parod", "y", "ies")}: ${stringifyArray(parodies)}
       |${makeStringPossiblyPlural(characters.length, "Character")}: ${stringifyArray(characters)}
       |${makeStringPossiblyPlural(tags.length, "Tag")}: ${stringifyArray(tags)}
       |${makeStringPossiblyPlural(artists.length, "Artist")}: ${stringifyArray(artists)}
       |${makeStringPossiblyPlural(groups.length, "Group")}: ${stringifyArray(groups)}
       |
       |Language: ${language.toString}
       |
       |Category: ${category.toString}
       |
       |${makeStringPossiblyPlural(pageCount, "Page")}: $pageCount
       |Uploaded: $uploadDate
     """.stripMargin
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
            tags: Array[HentaiTag] = Array(new HentaiTag("Mindfucking", 1))): Gallery = {
    new Gallery(name,
                japName,
                Array(new HentaiParody("InfoSoft The Animation", 1)),
                Array(new HentaiCharacter("genderbent-bodand", -1)),
                tags,
                Array(new HentaiArtist("Broccodile", 69)),
                Array(new HentaiGroup("InfoSoft HentaiBundle", 6)),
                new EnglishHentai(69),
                new MangaHentai(96), 85, "20XX-01-01")
  }
}
