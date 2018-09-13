package tk.iscorp.nhs.data.hentai.factory

import tk.iscorp.nhs.data.hentai.HentaiGroup

class HentaiGroupFactory extends HentaiDataFactory[HentaiGroup] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiGroup = {
    new HentaiGroup(name, amount)
  }
}
