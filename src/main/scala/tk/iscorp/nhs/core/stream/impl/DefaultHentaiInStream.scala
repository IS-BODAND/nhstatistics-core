/*******************************************************************************
 Copyright 2018 bodand

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ******************************************************************************/
package tk.iscorp.nhs.core.stream.impl

import org.jetbrains.annotations.NotNull
import tk.iscorp.nhs.core.data.Gallery
import tk.iscorp.nhs.core.datagetter.{HtmlResponseProcessor, HttpGetter}
import tk.iscorp.nhs.core.stream.HentaiInStream

/**
  * Default implementation of [[tk.iscorp.nhs.core.stream.HentaiInStream]]
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
    * @return The Gallery specified by the input ID. [[tk.iscorp.nhs.core.data.Gallery#dummy]] if ID doesn't point to
    *         any real doujin.
    */
  @NotNull
  override def readByID(id: String, isoDate: Boolean): Gallery = {
    val httpResponse =
      httpClientWrapper.getResponseFromUri(s"https://nhentai.net/g/$id")
    htmlResponseProcessor.processHtmlToGallery(httpResponse)
  }
}
