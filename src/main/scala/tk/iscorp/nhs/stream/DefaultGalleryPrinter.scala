package tk.iscorp.nhs.stream

import org.apache.commons.io.FileUtils

import java.io.File

class DefaultGalleryPrinter(val outPutMode: OutPutMode, val file: File) extends GalleryPrinter {
  override def apply(glr: String): Unit = {
    outPutMode match {
      case FileOutPutMode() ⇒
        FileUtils.writeStringToFile(file,
                                    glr,
                                    "UTF-8",
                                    true)
      case STDOUTMode() ⇒
        println(glr)
    }
  }
}
