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
  * HentaiArtist tag. Represents one artist from nhentai, and the amount of their art.
  *
  * @param name   Name of the artist
  * @param amount Amount of the doujin this artist has made
  *
  * @author bodand
  * @since 1.0
  */
class HentaiArtist(@NonNls @NotNull override val name: String,
                   @NotNull override val amount: Int) extends HentaiData {

  /**
    * Returns the data in xml in a String
    *
    * @return A scala.xml.Node object with name and amount attributes.
    *
    * @example &lt;artist name="Broccodile" amount="69"/&gt;
    * @since 1.1<br />
    *        1.3.5 - Returns in String to reduce overhead
    */
  override def toXml: String =
    s"""<artist name="$name" amount="$amount"/>"""

  /**
    * Returns the data in json in a String
    *
    * @return A org.simple.json.JSONObject object with fields name and amount.
    *
    * @example {"artist":"amount":69,"name":"Broccodile"}
    * @since 1.2<br>
    *        1.3.5 - Returns in String to reduce library overhead
    */
  override def toJson: String =
    s"""{"artist":{"amount":$amount,"name":"$name"}}"""
}
