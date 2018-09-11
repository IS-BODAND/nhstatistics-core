package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

sealed abstract case class HentaiCategory() extends HentaiData {
}

class MangaHentai(@NonNls @NotNull override val name: String,
                  @NonNls @NotNull override val amount: Int) extends HentaiCategory

class DoujinshiHentai(@NonNls @NotNull override val name: String,
                      @NonNls @NotNull override val amount: Int) extends HentaiCategory

class OtherCategoryHentai(@NonNls @NotNull override val name: String,
                          @NonNls @NotNull override val amount: Int) extends HentaiCategory
