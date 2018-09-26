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

package tk.iscorp.nhs.core.datagetter

import org.apache.commons.validator.routines.UrlValidator
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.jetbrains.annotations.{NonNls, NotNull, Nullable}

import java.net.URI

private[core] class HttpGetter() {
  @NotNull
  private val httpClient: CloseableHttpClient = HttpClients.createDefault()
  @NotNull
  private val urlSchemes: Array[String]       = Array("https")

  @NotNull
  @NonNls
  def getResponseFromUri(@Nullable uriString: String): String = {
    val urlValidator = new UrlValidator(urlSchemes)
    if (uriString == null) ""
    else if (!urlValidator.isValid(uriString)) ""
    else {
      val uri = new URI(uriString)
      val request = new HttpGet(uri)
      val response = httpClient.execute(request)
      val resposeEntity = response.getEntity
      EntityUtils.toString(resposeEntity)
    }
  }
}
