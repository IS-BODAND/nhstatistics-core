package tk.iscorp.nhs

import org.apache.commons.io.FileUtils
import tk.iscorp.nhs.inputparser.{ArgParser, ParseData}
import tk.iscorp.nhs.stream.HentaiOutStream
import tk.iscorp.nhs.stream.impl.{DefaultFileHentaiOutStream, DefaultHentaiInStream, DefaultSTDHentaiOutStream}

object MainObj {
  implicit     var parsedArguments: ParseData = _

  def main(args: Array[String]): Unit = {
    val argParser = new ArgParser
    parsedArguments = argParser.parse(args)
    val doujinOutPutStream: HentaiOutStream =
      if (parsedArguments.file == null) {
        new DefaultSTDHentaiOutStream
      } else {
        FileUtils.deleteQuietly(parsedArguments.file)
        new DefaultFileHentaiOutStream(parsedArguments.file)
      }

    val doujinInStream = new DefaultHentaiInStream

    if (!parsedArguments.hasValidValues) {
      argParser.printHelp()
    } else if (parsedArguments.hasUntilValue) {
      val ids = parsedArguments.until.toArray.map(_.toString)
      val got = doujinInStream.readMultipleByID(ids, parsedArguments.isoDate)

      doujinOutPutStream << got
    } else {
      val got = doujinInStream.readByID(parsedArguments.id, parsedArguments.isoDate)

      doujinOutPutStream << got
    }
  }
}
