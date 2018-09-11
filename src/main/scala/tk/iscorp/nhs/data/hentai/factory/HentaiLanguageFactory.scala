package tk.iscorp.nhs.data.hentai.factory

import tk.iscorp.nhs.Utils
import tk.iscorp.nhs.data.hentai._

class HentaiLanguageFactory extends HentaiDataFactory[HentaiLanguage] {
  override def construct(name: String,
                         amount: Int): HentaiLanguage = {
    name match {
      case "english" ⇒
        new EnglishHentai(amount)
      case "japanese" ⇒
        new JapaneseHentai(amount)
      case "chinese" ⇒
        new ChineseHentai(amount)
      case "translated" ⇒
        Utils.logger.info("The translated language tag makes no sense. Yet it is everywhere, even here.")
        new TranslatedHentai(amount)
      case lang ⇒
        Utils.logger.warn(s"Hentai language not found, this is most likely some kind of error. Category " +
                             s"found: $lang" +
                             s"expected: english|japanese|chinese")

        new OtherLanguageHentai(amount)
    }
  }
}
