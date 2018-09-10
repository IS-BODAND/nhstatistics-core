package tk.iscorp.nhs

import org.apache.commons.io.FileUtils
import tk.iscorp.nhs.data.serialize.StringToXML
import tk.iscorp.nhs.datagetter.HttpGetter

import java.io.File

object MainObj {
  def main(args: Array[String]): Unit = {
    val httpClientWrapper = new HttpGetter
    val httpResponse = httpClientWrapper.getHttpFromUri("https://nhentai.net/")
    val xmlSerializer = new StringToXML
    val htmlInXml = xmlSerializer.toXML(httpResponse)
    FileUtils.writeStringToFile(new File("nhentaiFrontpage.bodandkeep.html"), htmlInXml.toString, "UTF-8")
  }
}
