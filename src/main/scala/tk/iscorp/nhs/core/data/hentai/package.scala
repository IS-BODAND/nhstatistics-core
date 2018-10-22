package tk.iscorp.nhs.core.data

/**
  * The hentai package contains all tags and the HentaiData trait to store all
  * data from nhentai
  *
  * Contents:
  * - the HentaiData trait, all the others implement it
  * <ul>
  * <li>HentaiArtist - Stores hentai artists</li>
  * <li>HentaiCategory - Stores the hentai's category
  * can be either of the following:<br />
  * <ul>
  * <li>MangaHentai</li>
  * <li>DoujinshiHentai</li>
  * <li>OtherCategoryHentai - if all else fails</li>
  * </ul>
  * </li>
  * <li>HentaiCharacter - Stores characters who appear in hentai</li>
  * <li>HentaiGroup - Stores groups into which hentai are organized</li>
  * <li>HentaiLanguage - Stores the hentai's language
  * can be either of the following:<br />
  * <ul>
  * <li>EnglishHentai</li>
  * <li>JapaneseHentai</li>
  * <li>ChineseHentai</li>
  * <li>RewriteHentai</li>
  * <li>TranslatedHentai</li>
  * <li>OtherLanguageHentai - if all else fails</li>
  * </ul>
  * </li>
  * <li>HentaiParody - Stores shows which the hentai "parodizes"</li>
  * <li>HentaiTag - Stores the tag which describe the hentai's contents</li>
  * </ul>
  */
package object hentai {

}
