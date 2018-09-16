package org.bitbucket.bodand.nhscore.hentai.factory

import org.bitbucket.bodand.nhscore.Utils
import org.bitbucket.bodand.nhscore.hentai._

class HentaiLanguageFactory extends HentaiDataFactory[HentaiLanguage] {
  override def construct(name: String,
                         amount: Int)
                        (implicit id: Int): HentaiLanguage = {
    name match {
      case "english" ⇒
        new EnglishHentai(amount)
      case "japanese" ⇒
        new JapaneseHentai(amount)
      case "chinese" ⇒
        new ChineseHentai(amount)
      case "rewrite" ⇒
        new RewriteHentai(amount)
      case "translated" ⇒
        Utils.logger.info(s"$id => The translated language tag makes no sense. Yet it is everywhere, even here.")
        new TranslatedHentai(amount)
      case lang ⇒
        Utils.logger.warn(s"Hentai language not found, this is most likely some kind of error. Category\n" +
                             s"found: $lang\n" +
                             s"expected: english|japanese|chinese|rewrite\n")

        new OtherLanguageHentai(amount)
    }
  }
}
