package org.bitbucket.bodand.nhscore

import org.apache.commons.io.FileUtils
import org.bitbucket.bodand.nhscore.inputparser.{ArgParser, ParseData}
import org.bitbucket.bodand.nhscore.stream.HentaiOutStream
import org.bitbucket.bodand.nhscore.stream.impl.{DefaultHentaiInStream, FileHentaiOutStream, StandardHentaiOutStream}

object MainObj {
  implicit     var parsedArguments: ParseData = _

  def main(args: Array[String]): Unit = {
    val argParser = new ArgParser
    parsedArguments = argParser.parse(args)
    val doujinOutPutStream: HentaiOutStream =
      if (parsedArguments.file == null) {
        new StandardHentaiOutStream
      } else {
        FileUtils.deleteQuietly(parsedArguments.file)
        new FileHentaiOutStream(parsedArguments.file)
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
