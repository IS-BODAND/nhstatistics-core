package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiArtist

import scala.language.existentials

class HentaiArtistFactory extends HentaiDataFactory[HentaiArtist] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiArtist = {
    new HentaiArtist(name, amount)
  }
}
