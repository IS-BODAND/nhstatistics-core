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
package tk.iscorp.nhs.core.data.hentai

import scala.language.existentials

import org.jetbrains.annotations.{NonNls, NotNull}

/**
  * HentaiTag tag. Represents one tag that has been put on at least one doujin.
  *
  * @param name   Name of the tag
  * @param amount Amount of doujin with the tag
  */
class HentaiTag(@NonNls @NotNull override val name: String,
                @NotNull override val amount: Int) extends HentaiData {
  override def toXml: String = s"""<tag name="$name" amount="$amount"/>"""

  override def toJson: String =
    s"""{"tag":{"amount":$amount,"name":"$name"}}"""
}
