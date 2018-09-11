package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.NotNull

sealed abstract case class HentaiCategory() extends HentaiData

class MangaHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Manga"
}

class DoujinshiHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Doujinshi"
}

class OtherCategoryHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Other"
}
