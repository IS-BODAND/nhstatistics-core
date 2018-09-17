package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

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
  override def toString: String = {
    s"[$name ($amount)]"
  }
}
