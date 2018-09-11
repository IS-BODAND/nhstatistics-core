package tk.iscorp.nhs

import tk.iscorp.nhs.datagetter.{HtmlResponseProcessor, HttpGetter}
import tk.iscorp.nhs.inputparser.ArgParser

object MainObj {
  def main(args: Array[String]): Unit = {
    val argParser = new ArgParser
    val parsedArguments = argParser.parse(args)

    if (parsedArguments.help || parsedArguments.id == "") {
      argParser.printHelp()
    } else {
      val httpClientWrapper = new HttpGetter
      val htmlResponseProcessor = new HtmlResponseProcessor
      val httpResponse = httpClientWrapper.getHttpFromUri(s"https://nhentai.net/g/${parsedArguments.id}")
      val semiDummyGallery = htmlResponseProcessor.processHtmlToGallery(httpResponse)

      println(semiDummyGallery.toString)
    }
  }
}
