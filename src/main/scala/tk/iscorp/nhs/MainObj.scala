package tk.iscorp.nhs

import tk.iscorp.nhs.datagetter.HttpGetter

object MainObj {
  def main(args: Array[String]): Unit = {
    val httpClientWrapper = new HttpGetter
    val httpResponse = httpClientWrapper.getHttpFromUri("https://nhentai.net/g/228922")
  }
}
