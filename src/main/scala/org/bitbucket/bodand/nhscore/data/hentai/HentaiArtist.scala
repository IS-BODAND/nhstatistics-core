package org.bitbucket.bodand.nhscore.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}

import scala.language.existentials
import scala.xml.Node

class HentaiArtist(@NonNls @NotNull override val name: String,
                   @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <artist name={s"$name"} amount={s"$amount"} />
}
