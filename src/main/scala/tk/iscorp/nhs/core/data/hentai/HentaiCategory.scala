package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.NotNull

import scala.xml.Node

sealed abstract case class HentaiCategory() extends HentaiData {
  override def toXml: Node = <category name={s"$name"} amount={s"$amount"}/>
}

class MangaHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Manga"
}

class DoujinshiHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Doujinshi"
}

class OtherCategoryHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Other"
}
