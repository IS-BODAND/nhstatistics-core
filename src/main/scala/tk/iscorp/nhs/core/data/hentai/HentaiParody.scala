package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JMap}

import scala.language.existentials
import scala.xml.Node

class HentaiParody(@NonNls @NotNull override val name: String,
                   @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <parody name={s"$name"} amount={s"$amount"} />

  override def toJson: JSONObject = new JSONObject(new JMap[String, Any]() {{
    put("parody", new JMap[String, Any]() {{
      put("name", name)
      put("amount", new Integer(amount))
    }})
  }})
}
