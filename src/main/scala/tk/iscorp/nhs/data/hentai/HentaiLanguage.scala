package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.NotNull

sealed abstract case class HentaiLanguage() extends HentaiData

class EnglishHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override def name: String = "English"
}

class JapaneseHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override def name: String = "Japanese"
}

class ChineseHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override def name: String = "Chinese" //Who the fuck reads chinese hentai
}

class OtherLanguageHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override def name: String = "Other"
}
