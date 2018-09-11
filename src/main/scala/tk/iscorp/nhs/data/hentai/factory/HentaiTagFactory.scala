package tk.iscorp.nhs.data.hentai.factory

import tk.iscorp.nhs.data.hentai.HentaiTag

import scala.language.existentials

class HentaiTagFactory extends HentaiDataFactory[HentaiTag] {
  override def construct(name: String,
                         amount: Int): HentaiTag = {
    new HentaiTag(name, amount)
  }
}
