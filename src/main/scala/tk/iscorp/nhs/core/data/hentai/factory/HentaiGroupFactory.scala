package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiGroup

class HentaiGroupFactory extends HentaiDataFactory[HentaiGroup] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiGroup = {
    new HentaiGroup(name, amount)
  }
}
