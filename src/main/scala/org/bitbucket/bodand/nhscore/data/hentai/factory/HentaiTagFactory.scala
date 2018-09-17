package org.bitbucket.bodand.nhscore.data.hentai.factory

import org.bitbucket.bodand.nhscore.data.hentai.HentaiTag

import scala.language.existentials

class HentaiTagFactory extends HentaiDataFactory[HentaiTag] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiTag = {
    new HentaiTag(name, amount)
  }
}
