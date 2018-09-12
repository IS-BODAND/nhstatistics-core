package tk.iscorp.nhs.inputparser

class ParseData(val help: Boolean, val id: String, val isoDate: Boolean) {

}

object ParseData {
  def dummy(): ParseData = {
    new ParseData(false, "1", true)
  }
}
