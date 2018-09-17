package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiParody

import scala.language.existentials

class HentaiParodyFactory extends HentaiDataFactory[HentaiParody] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiParody = {
    new HentaiParody(name, amount)
  }
}
