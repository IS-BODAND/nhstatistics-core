/*******************************************************************************
 InfoSoft OpenSource Licence

 Copyright (c) 2018.

 Permission is hereby granted to absolutely free usage of this software in any way that doesn't conflict with the the licensee's local laws.
 Modification and redistribution of this software is permitted, but the changes must be stated, and the source software (this one) must be stated.
 Redistributed versions must be licensed under the InfoSoft OpenSource Licence.
 Projects using a modified or not, verion of this software, may or may not use the InfoSoft OpenSource Licence. Commercial distribution is permitted.
 This licence must be made available to the end user from within the program, and to all programmers from a IS-OSL.LICENCE.txt file.
 Inclusion of the licence in the source file(s) may be used instead of the IS-OSL.LICENCE.txt file.

 ******************************************************************************/

package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject

import scala.xml.Node

/**
  * For different hentai information to implement, eg. artists, characters, as tags.
  * Contains the name of the tag as it appears on nhentai and the amount of doujin with that tag.
  *
  * Allows simple transformation to data formats: XML and JSON.
  *
  * @author bodand
  *
  * @since 1.0
  */
trait HentaiData {

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
  def toXml: Node

  /**
    * Returns the data in json.
    *
    * @return A org.simple.json.JSONObject object with fields name and amount.
    *
    * @since 1.2
    */
  @NonNls
  @NotNull
  def toJson: JSONObject

  /**
    * @return A pretty, printable string of this object.
    *
    * @example If the name is `genderbent-bodand` (I've seen some, in some manga)
    *          and the amount is `1` the string will be:
    *          `[genderbent-bodand (1)]`
    *
    * @since 1.0
    */
  @NonNls
  @NotNull
  override def toString: String = {
    s"[$name ($amount)]"
  }

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
