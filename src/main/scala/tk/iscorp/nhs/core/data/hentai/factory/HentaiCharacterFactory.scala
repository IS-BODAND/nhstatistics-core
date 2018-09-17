package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiCharacter

import scala.language.existentials


class HentaiCharacterFactory extends HentaiDataFactory[HentaiCharacter] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiCharacter = {
    new HentaiCharacter(name, amount)
  }
}

