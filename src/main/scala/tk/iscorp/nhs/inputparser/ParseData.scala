package tk.iscorp.nhs.inputparser

import java.io.File

class ParseData(val help: Boolean,
                val id: String,
                val isoDate: Boolean,
                val until: Int,
                val file: File) {

}

object ParseData {
  def dummy(): ParseData = {
    new ParseData(false, "1", true, 0, new File("~dummyfile.txt"))
  }
}
