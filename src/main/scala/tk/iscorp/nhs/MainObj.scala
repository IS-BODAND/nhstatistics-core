package tk.iscorp.nhs

import org.apache.commons.io.FileUtils
import tk.iscorp.nhs.data.Gallery
import tk.iscorp.nhs.datagetter.{HtmlResponseProcessor, HttpGetter}
import tk.iscorp.nhs.inputparser.{ArgParser, ParseData}
import tk.iscorp.nhs.stream.{FileOutPutMode, HentaiOutStream, STDOUTMode}

object MainObj {
  private      val httpClientWrapper          = new HttpGetter
  private lazy val htmlResponseProcessor      = new HtmlResponseProcessor
  implicit     var parsedArguments: ParseData = _

  def main(args: Array[String]): Unit = {
    val argParser = new ArgParser
    parsedArguments = argParser.parse(args)
    val doujinOutPutStream: HentaiOutStream =
      if (parsedArguments.file == null) {
        HentaiOutStream.construct(STDOUTMode())
      } else {
        FileUtils.deleteQuietly(parsedArguments.file)
        HentaiOutStream.construct(FileOutPutMode(), parsedArguments.file)
      }

    if (userIsAFuckingIdiotOrANewGuy) {
      argParser.printHelp()
    } else if (parsedArguments.until != 0) {
      val got = polyDoujinRequest()

      doujinOutPutStream << got
    } else {
      val got = monoDoujinRequest()

      doujinOutPutStream << got
    }
  }

  private def userIsAFuckingIdiotOrANewGuy: Boolean = {
    parsedArguments.help || (parsedArguments.id == "" && parsedArguments.until == 0)
  }

  private def polyDoujinRequest(): List[Gallery] = {
    (for {
      i ← 1 to parsedArguments.until
    } yield {
      htmlResponseProcessor
         .processHtmlToGallery(httpClientWrapper.getHttpFromUri(s"https://nhentai.net/g/$i"))
    }).toList
  }

  private def monoDoujinRequest(): Gallery = {
    val httpResponse = httpClientWrapper.getHttpFromUri(s"https://nhentai.net/g/${parsedArguments.id}")
    htmlResponseProcessor.processHtmlToGallery(httpResponse)
  }
}
