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

package tk.iscorp.nhs.core.data.hentai

import org.jetbrains.annotations.{NonNls, NotNull}
import org.json.simple.JSONObject

import java.util.{HashMap ⇒ JMap}

import scala.language.existentials
import scala.xml.Node

/**
  * HentaiParody tag. Represents one series which was parodized by at least one doujin.
  *
  * @param name   Name of the parodized series
  * @param amount Amount of doujin parodizing the series
  */
class HentaiParody(@NonNls @NotNull override val name: String,
                   @NotNull override val amount: Int) extends HentaiData {
  override def toXml: Node = <parody name={s"$name"} amount={s"$amount"} />

  override def toJson: JSONObject = new JSONObject(new JMap[String, Any]() {{
    put("parody", new JMap[String, Any]() {{
      put("name", name)
      put("amount", new Integer(amount))
    }})
  }})
}
