package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JHashMap}

import scala.language.existentials
import scala.xml.Node

class HentaiArtist(@NonNls @NotNull override val name: String,
                   @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <artist name={s"$name"} amount={s"$amount"} />

  override def toJson: JSONObject = new JSONObject (new JHashMap[String, Any]() {{
    put("artist", new JHashMap[String, Any]() {{
      put("name", name)
      put("amount", new Integer(amount))
    }})
  }})
}
