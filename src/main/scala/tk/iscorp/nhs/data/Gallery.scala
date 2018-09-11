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
              @NonNls @NotNull val language: HentaiLanguage,
              @NonNls @NotNull val category: HentaiCategory,
              @NonNls @NotNull val pageCount: Int,
              @NonNls @NotNull val uploadDate: String) {
}
