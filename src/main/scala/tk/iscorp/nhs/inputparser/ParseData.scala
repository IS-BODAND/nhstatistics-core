package tk.iscorp.nhs.inputparser

class ParseData(val help: Boolean, val id: String) {

}

object ParseData {
  def dummy(): ParseData = {
    new ParseData(false, "1")
  }
}
