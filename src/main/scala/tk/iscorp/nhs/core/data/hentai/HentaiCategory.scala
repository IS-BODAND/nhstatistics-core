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

import org.jetbrains.annotations.NotNull
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JMap}

import scala.xml.Node

/**
  * HentaiCategory tag. Represents a category type on nhentai.
  * It can be:
  * <ul>
  * <li>Manga - [[MangaHentai]]</li>
  * <li>Doujinshi - [[DoujinshiHentai]]</li>
  * </ul>
  * [[OtherCategoryHentai]] is kept for surprising appearances of different tags.
  *
  * @author bodand
  *
  * @since 1.0
  */
sealed abstract case class HentaiCategory() extends HentaiData {
  override def toXml: Node = <category name={s"$name"} amount={s"$amount"}/>

  override def toJson: JSONObject = new JSONObject(new JMap[String, Any]() {{
    put("name", name)
    put("amount", new Integer(amount))
  }})
}

class MangaHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Manga"

  override def equals(obj: Any): Boolean = obj match {
    case mh: MangaHentai ⇒
      mh.amount == this.amount
    case _ ⇒ false
  }
}

class DoujinshiHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Doujinshi"

  override def equals(obj: Any): Boolean = obj match {
    case mh: DoujinshiHentai ⇒
      mh.amount == this.amount
    case _ ⇒ false
  }
}

class OtherCategoryHentai(@NotNull override val amount: Int) extends HentaiCategory {
  override val name: String = "Other"

  override def equals(obj: Any): Boolean = obj match {
    case mh: OtherCategoryHentai ⇒
      mh.amount == this.amount
    case _ ⇒ false
  }
}
