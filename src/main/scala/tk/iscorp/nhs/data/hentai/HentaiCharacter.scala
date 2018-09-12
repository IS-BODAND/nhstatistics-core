package tk.iscorp.nhs.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

import scala.language.existentials
import scala.xml.Node

class HentaiCharacter(@NonNls @NotNull override val name: String,
                      @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <character name={name} amount={amount} />
}
