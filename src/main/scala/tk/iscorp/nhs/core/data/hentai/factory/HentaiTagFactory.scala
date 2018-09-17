package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiTag

import scala.language.existentials

class HentaiTagFactory extends HentaiDataFactory[HentaiTag] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiTag = {
    new HentaiTag(name, amount)
  }
}
