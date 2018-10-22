/** *****************************************************************************
  * Copyright 2018 bodand
  * *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * *
  * http://www.apache.org/licenses/LICENSE-2.0
  * *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  * *****************************************************************************/
package tk.iscorp.nhs.core.data.hentai.factory

import tk.iscorp.nhs.core.data.hentai.HentaiCharacter

/**
  * Factory for a [[tk.iscorp.nhs.core.data.hentai.HentaiCharacter]]
  */
class HentaiCharacterFactory extends HentaiDataFactory[HentaiCharacter] {

  /**
    * Constructs a new HentaiData implementor object
    *
    * @param name   Name of the HentaiData implementor to store
    * @param amount Amount of doujin associated with the HentaiData implementor
    * @param id     Implicit. ID of the doujin this HentaiData is got from.
    *               Only used in logging.
    *
    * @return A new HentaiData object
    */
  override def construct(name: String, amount: Int)(implicit id: Int): HentaiCharacter =
    new HentaiCharacter(name, amount)
}
