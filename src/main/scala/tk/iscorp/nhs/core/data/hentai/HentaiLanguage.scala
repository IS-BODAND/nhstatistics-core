package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.NotNull
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JMap}

import scala.xml.Node

sealed abstract case class HentaiLanguage() extends HentaiData {
  override def toXml: Node = <language name={s"$name"} amount={s"$amount"} />

  override def toJson: JSONObject = new JSONObject(new JMap[String, Any]() {{
    put("language", new JMap[String, Any]() {{
      put("name", name)
      put("amount", new Integer(amount))
    }})
  }})
}

class EnglishHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "English"

  override def equals(obj: Any): Boolean = obj match {
    case eh: EnglishHentai ⇒
      eh.amount == this.amount
    case _ ⇒ false
  }
}

class JapaneseHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Japanese"

  override def equals(obj: Any): Boolean = obj match {
    case jh: JapaneseHentai ⇒
      jh.amount == this.amount
    case _ ⇒ false
  }
}

class ChineseHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Chinese" //Who the fuck reads chinese hentai

  override def equals(obj: Any): Boolean = obj match {
    case ch: ChineseHentai ⇒
      ch.amount == this.amount
    case _ ⇒ false
  }
}

class TranslatedHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Translated"

  override def equals(obj: Any): Boolean = obj match {
    case th: TranslatedHentai ⇒
      th.amount == this.amount
    case _ ⇒ false
  }
}

class RewriteHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Rewrite"

  override def equals(obj: Any): Boolean = obj match {
    case rh: RewriteHentai ⇒
      rh.amount == this.amount
    case _ ⇒ false
  }
}

class OtherLanguageHentai(@NotNull override val amount: Int) extends HentaiLanguage {
  override val name: String = "Other"

  override def equals(obj: Any): Boolean = obj match {
    case oh: OtherLanguageHentai ⇒
      oh.amount == this.amount
    case _ ⇒ false
  }
}
