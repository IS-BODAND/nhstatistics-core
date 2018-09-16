package org.bitbucket.bodand.nhscore.hentai.factory

import org.bitbucket.bodand.nhscore.data.hentai.HentaiParody

import scala.language.existentials

class HentaiParodyFactory extends HentaiDataFactory[HentaiParody] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiParody = {
    new HentaiParody(name, amount)
  }
}
