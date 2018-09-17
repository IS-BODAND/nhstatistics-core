package org.bitbucket.bodand.nhscore.data.hentai.factory

import org.bitbucket.bodand.nhscore.data.hentai.HentaiGroup

class HentaiGroupFactory extends HentaiDataFactory[HentaiGroup] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiGroup = {
    new HentaiGroup(name, amount)
  }
}
