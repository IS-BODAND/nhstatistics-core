package tk.iscorp.nhs.core.data.hentai.factory

import scala.language.existentials

trait HentaiDataFactory[T] {


  def construct(name: String, amount: Int)(implicit id: Int): T
}
