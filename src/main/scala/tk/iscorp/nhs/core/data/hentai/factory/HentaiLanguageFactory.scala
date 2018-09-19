/*******************************************************************************
 InfoSoft OpenSource Licence

 Copyright (c) 2018.

 Permission is hereby granted to absolutely free usage of this software in any way
 that doesn't conflict with the the licensee's local laws. Modification and
 redistribution of this software is permitted, but the changes must be stated, and
 the source software (this one) must be stated. Redistributed versions must be
 licensed under the InfoSoft OpenSource Licence. Projects using a (modified or not)
 version of this software, may or may not use the InfoSoft OpenSource Licence.
 Commercial distribution is permitted. This licence must be made available to the
 end user from within the program, and to all programmers from a IS-OSL.LICENCE.txt file.
 Inclusion of the licence in the source file(s) may be used instead of the IS-OSL.LICENCE.txt file.

 ******************************************************************************/

package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.Utils
import tk.iscorp.nhs.core.data.hentai._

/**
  * Factory for a [[tk.iscorp.nhs.core.data.hentai.HentaiLanguage]]
  */
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
