package tk.iscorp.nhs.data.hentai.factory

import tk.iscorp.nhs.data.hentai.HentaiArtist

import scala.language.existentials

class HentaiArtistFactory extends HentaiDataFactory[HentaiArtist] {
  override def construct(name: String,
                         amount: Int): HentaiArtist = {
    new HentaiArtist(name, amount)
  }
}
