package org.bitbucket.bodand.nhscore.data.hentai.factory

import org.bitbucket.bodand.nhscore.data.hentai.HentaiArtist

import scala.language.existentials

class HentaiArtistFactory extends HentaiDataFactory[HentaiArtist] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiArtist = {
    new HentaiArtist(name, amount)
  }
}
