package tk.iscorp.nhs.data.serialize

import scala.xml.{Elem, XML}

class StringToXML {
  def toXML(str: String): Option[Elem] = {
    try {
      Some(XML.loadString(str))
    } catch {
      case _: Throwable ⇒
        None
    }
  }
}
