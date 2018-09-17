package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject

import scala.xml.Node

trait HentaiData {
  @NonNls
  @NotNull
  def name: String

  @NotNull
  def amount: Int

  @NonNls
  @NotNull
  def toXml: Node

  @NonNls
  @NotNull
  def toJson: JSONObject

  @NonNls
  @NotNull
  override def toString: String = {
    s"[$name ($amount)]"
  }

  @NotNull
  override def equals(obj: scala.Any): Boolean = obj match {
    case hd: HentaiData ⇒
      hd.name == this.name && hd.amount == this.amount
    case _ ⇒ false
  }
}
