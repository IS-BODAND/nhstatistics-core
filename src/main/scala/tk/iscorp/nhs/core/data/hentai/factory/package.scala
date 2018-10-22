package tk.iscorp.nhs.core.data.hentai

/**
  * The factory package has factories to construct [[tk.iscorp.nhs.core.data.hentai.HentaiData]] implementors
  * Except the HentaiCategory who is currently constructed in-place and doesn't have a factory.
  * All classes can be constructed normally, these are used by the library to get around type-erasure in
  * [[tk.iscorp.nhs.core.datagetter.HtmlResponseProcessor]]
  */
package object factory {

}
