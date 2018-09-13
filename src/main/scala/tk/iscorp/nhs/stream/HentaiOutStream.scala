package tk.iscorp.nhs.stream

import tk.iscorp.nhs.data.Gallery

import java.io.File

class HentaiOutStream(outPutMode: OutPutMode, file: File)
                     (private val printer: DefaultGalleryPrinter) {

  def <<(gallery: Gallery): Unit = {
    printer(gallery.toString)
  }

  def <<(galleries: List[Gallery]): Unit = {
    printer(galleries.mkString("\n" + "-" * 80 + "\n"))
  }
}

object HentaiOutStream {
  def construct(outPutMode: OutPutMode,
                file: File = new File("galleries.txt")): HentaiOutStream = {
    new HentaiOutStream(outPutMode, file)(new DefaultGalleryPrinter(outPutMode, file))
  }
}
