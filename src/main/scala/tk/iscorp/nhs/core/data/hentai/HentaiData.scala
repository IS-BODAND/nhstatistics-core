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

import org.jetbrains.annotations.{NonNls, NotNull}
import tk.iscorp.nhs.core.transform.{JSONTransformable, XmlTransformable}

/**
  * For different hentai information to implement, eg. artists, characters, as tags.
  * Contains the name of the tag as it appears on nhentai and the amount of doujin with that tag.
  *
  * Allows simple transformation to data formats: XML and JSON.
  *
  * @author bodand
  * @since 1.0
  */
trait HentaiData extends XmlTransformable with JSONTransformable {

  /**
    * The name of the tag.
    *
    * @since 1.0
    */
  @NonNls
  @NotNull
  def name: String

  /**
    * Amount of doujin that have this tag on them.
    *
    * @since 1.0
    */
  @NotNull
  def amount: Int

  /**
    * Returns the data in xml.
    *
    * @return A scala.xml.Node object with name and amount attributes.
    *
    * @since 1.1
    */
  @NonNls
  @NotNull
  override def toXml: String

  /**
    * Returns the data in json.
    *
    * @return A org.simple.json.JSONObject object with fields name and amount.
    *
    * @since 1.2
    */
  @NonNls
  @NotNull
  override def toJson: String

  /**
    * @return A pretty, printable string of this object.
    *
    * @example If the name is `genderbent-bodand` (I've seen some, in some manga)
    *          and the amount is `1` the string will be:
    *          `[genderbent-bodand (1)]`
    * @since 1.0
    */
  @NonNls
  @NotNull
  override def toString: String =
    s"[$name ($amount)]"

  /**
    * Equivalency test based on equal name and amount.
    *
    * @param obj The object to check equivalency with.
    *
    * @return Whether the objects match in type, and if yes; their names and amounts equal or not.
    *
    * @since 1.0
    */
  @NotNull
  override def equals(obj: scala.Any): Boolean = obj match {
    case hd: HentaiData ⇒
      hd.name == this.name && hd.amount == this.amount
    case _ ⇒ false
  }
}
