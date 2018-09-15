package tk.iscorp.nhs.stream.impl

import org.jetbrains.annotations.NotNull
import tk.iscorp.nhs.data.Gallery
import tk.iscorp.nhs.datagetter.{HtmlResponseProcessor, HttpGetter}
import tk.iscorp.nhs.stream.HentaiInStream

/**
  * Default implementation of [[tk.iscorp.nhs.stream.HentaiInStream]]
  */
class DefaultHentaiInStream extends HentaiInStream {
  private val httpClientWrapper = new HttpGetter
  private val htmlResponseProcessor = new HtmlResponseProcessor

  /**
    * Reads one doujin with id
    *
    * @param id      The ID of the doujin to be read
    * @param isoDate Whether the doujin's upload date should be set to the ISO 8601 date version
    *
    * @throws NullPointerException If any of the parameters is `null`
    * @return The Gallery specified by the input ID. [[tk.iscorp.nhs.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  override def readByID(id: String, isoDate: Boolean): Gallery = {
    val httpResponse = httpClientWrapper.getResponseFromUri(s"https://nhentai.net/g/$id")
    htmlResponseProcessor.processHtmlToGallery(httpResponse, isoDate)
  }
}
