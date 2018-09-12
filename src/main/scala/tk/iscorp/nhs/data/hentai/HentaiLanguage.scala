package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.NotNull

import scala.xml.Node

sealed abstract case class HentaiLanguage() extends HentaiData {
  override def toXml: Node = <language name={s"$name"} amount={s"$amount"} />
}

class EnglishHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "English"
}

class JapaneseHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Japanese"
}

class ChineseHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Chinese" //Who the fuck reads chinese hentai
}

class TranslatedHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Translated"
}

class OtherLanguageHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Other"
}
