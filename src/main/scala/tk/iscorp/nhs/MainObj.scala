package tk.iscorp.nhs

import org.apache.commons.io.FileUtils
import tk.iscorp.nhs.datagetter.{HtmlResponseProcessor, HttpGetter}
import tk.iscorp.nhs.inputparser.{ArgParser, ParseData}

import java.io.File

object MainObj {
  private      val httpClientWrapper          = new HttpGetter
  private lazy val htmlResponseProcessor      = new HtmlResponseProcessor
  implicit     var parsedArguments: ParseData = _

  def main(args: Array[String]): Unit = {
    val argParser = new ArgParser
    parsedArguments = argParser.parse(args)

    val xml =
    <galleries>
      {
        for {
          i ← 1 to 246515
        } yield {
          htmlResponseProcessor
             .processHtmlToGallery(httpClientWrapper.getHttpFromUri(s"https://nhentai.net/g/$i}"))
        }
      }
    </galleries>

    FileUtils.writeStringToFile(new File("Galleries.xml"), xml.toString(), "UTF-8")
  }

  private def averageRun(argParser: ArgParser,
                         parsedArguments: ParseData): Unit = {
    if (parsedArguments.help || parsedArguments.id == "") {
      argParser.printHelp()
    } else {
      val httpResponse = httpClientWrapper.getHttpFromUri(s"https://nhentai.net/g/${parsedArguments.id}")
      val gallery = htmlResponseProcessor.processHtmlToGallery(httpResponse)

      println(gallery.toString)
    }
  }
}
