package org.bitbucket.bodand.nhscore.inputparser

import java.io.File

class ParseData(val help: Boolean,
                val id: String,
                val isoDate: Boolean,
                val until: Range,
                val file: File) {
  def hasValidValues: Boolean = !help && id != "" || until != Range.inclusive(1, 0)

  def hasUntilValue: Boolean = until != Range.inclusive(1, 0)

  def hasIDValue: Boolean = id != ""
}

object ParseData {
  def dummy(): ParseData = {
    new ParseData(false, "1", true, Range.inclusive(1, 0), new File("~dummyfile.txt"))
  }
}
