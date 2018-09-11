package tk.iscorp.nhs.data.hentai.factory

import scala.language.existentials

trait HentaiDataFactory[T] {


  def construct(name: String, amount: Int): T
}
