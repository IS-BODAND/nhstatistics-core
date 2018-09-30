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
package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JMap}

import scala.language.existentials
import scala.xml.Node

/**
  * HentaiTag tag. Represents one tag that has been put on at least one doujin.
  *
  * @param name   Name of the tag
  * @param amount Amount of doujin with the tag
  */
class HentaiTag(@NonNls @NotNull override val name: String, @NotNull override val amount: Int)
    extends HentaiData {
  override def toXml: Node = <tag name={s"$name"} amount={s"$amount"} />

  override def toJson: JSONObject =
    new JSONObject(new JMap[String, Any]() {
      {
        put("tag", new JMap[String, Any]() {
          {
            put("name", name)
            put("amount", new Integer(amount))
          }
        })
      }
    })
}
