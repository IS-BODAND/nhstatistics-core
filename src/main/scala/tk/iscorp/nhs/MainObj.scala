package tk.iscorp.nhs

import tk.iscorp.nhs.datagetter.{HtmlResponseProcessor, HttpGetter}

object MainObj {
  def main(args: Array[String]): Unit = {
    val httpClientWrapper = new HttpGetter
    val htmlResponseProcessor = new HtmlResponseProcessor
    val httpResponse = httpClientWrapper.getHttpFromUri("https://nhentai.net/g/228922")
    val semiDummyGallery = htmlResponseProcessor.processHtmlToGallery(httpResponse)

    println(semiDummyGallery.toString)
  }
}
