package tk.iscorp.nhs.data.hentai.factory

import tk.iscorp.nhs.data.hentai.HentaiParody

import scala.language.existentials

class HentaiParodyFactory extends HentaiDataFactory[HentaiParody] {
  override def construct(name: String,
                         amount: Int): HentaiParody = {
    new HentaiParody(name, amount)
  }
}
