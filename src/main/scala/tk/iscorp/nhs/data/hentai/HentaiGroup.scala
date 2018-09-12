package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

import scala.language.existentials
import scala.xml.Node

class HentaiGroup(@NonNls @NotNull override val name: String,
                  @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <group name={name} amount={amount} />
}

