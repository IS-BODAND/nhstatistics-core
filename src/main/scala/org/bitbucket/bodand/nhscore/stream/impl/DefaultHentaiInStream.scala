package org.bitbucket.bodand.nhscore.stream.impl

import org.bitbucket.bodand.nhscore.data.Gallery
import org.bitbucket.bodand.nhscore.datagetter.{HtmlResponseProcessor, HttpGetter}
import org.bitbucket.bodand.nhscore.stream.HentaiInStream
import org.jetbrains.annotations.NotNull

/**
  * Default implementation of [[org.bitbucket.bodand.nhscore.stream.HentaiInStream]]
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
    * @return The Gallery specified by the input ID. [[org.bitbucket.bodand.nhscore.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  override def readByID(id: String, isoDate: Boolean): Gallery = {
    val httpResponse = httpClientWrapper.getResponseFromUri(s"https://nhentai.net/g/$id")
    htmlResponseProcessor.processHtmlToGallery(httpResponse, isoDate)
  }
}
