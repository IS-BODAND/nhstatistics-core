/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/
package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.Utils
import tk.iscorp.nhs.core.data.hentai._

/**
  * Factory for a [[tk.iscorp.nhs.core.data.hentai.HentaiLanguage]]
  */
class HentaiLanguageFactory extends HentaiDataFactory[HentaiLanguage] {
  override def construct(name: String, amount: Int)(implicit id: Int): HentaiLanguage =
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
        Utils.logger.info(
          s"$id => The translated language tag makes no sense. Yet it is everywhere, even here."
        )
        new TranslatedHentai(amount)
      case lang ⇒
        Utils.logger.warn(
          s"Hentai language not found, this is most likely some kind of error. Category\n" +
            s"found: $lang\n" +
            s"expected: english|japanese|chinese|rewrite\n"
        )

        new OtherLanguageHentai(amount)
    }
}
