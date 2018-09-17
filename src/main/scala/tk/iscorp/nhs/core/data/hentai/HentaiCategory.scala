package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.NotNull
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JMap}

import scala.xml.Node

sealed abstract case class HentaiCategory() extends HentaiData {
  override def toXml: Node = <category name={s"$name"} amount={s"$amount"}/>

  override def toJson: JSONObject = new JSONObject(new JMap[String, Any]() {{
    put("name", name)
    put("amount", new Integer(amount))
  }})
}

class MangaHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Manga"

  override def equals(obj: Any): Boolean = obj match {
    case mh: MangaHentai ⇒
      mh.amount == this.amount
    case _ ⇒ false
  }
}

class DoujinshiHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Doujinshi"

  override def equals(obj: Any): Boolean = obj match {
    case mh: DoujinshiHentai ⇒
      mh.amount == this.amount
    case _ ⇒ false
  }
}

class OtherCategoryHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Other"

  override def equals(obj: Any): Boolean = obj match {
    case mh: OtherCategoryHentai ⇒
      mh.amount == this.amount
    case _ ⇒ false
  }
}
