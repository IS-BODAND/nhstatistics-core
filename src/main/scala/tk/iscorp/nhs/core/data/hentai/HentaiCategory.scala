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

import java.util.{HashMap ⇒ JMap}

import scala.xml.Node

import org.jetbrains.annotations.NotNull
import org.json.simple.JSONObject

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
  * @since 1.0
  */
sealed abstract case class HentaiCategory() extends HentaiData {
  override def toXml: Node = <category name={s"$name"} amount={s"$amount"}/>

  override def toJson: JSONObject =
    new JSONObject(new JMap[String, Any]() {
      {
        put("name", name)
        put("amount", new Integer(amount))
      }
    })
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
