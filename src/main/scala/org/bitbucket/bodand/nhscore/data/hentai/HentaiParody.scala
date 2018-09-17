package org.bitbucket.bodand.nhscore.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

import scala.language.existentials
import scala.xml.Node

class HentaiParody(@NonNls @NotNull override val name: String,
                   @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <parody name={s"$name"} amount={s"$amount"} />
}
