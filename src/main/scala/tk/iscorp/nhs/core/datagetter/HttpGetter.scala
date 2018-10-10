/** *****************************************************************************
  * Copyright 2018 bodand
  * *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * *
  * http://www.apache.org/licenses/LICENSE-2.0
  * *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  * *****************************************************************************/
package tk.iscorp.nhs.core.datagetter

import java.net.URI

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.jetbrains.annotations.{NonNls, NotNull, Nullable}

private[core] class HttpGetter() {
  @NotNull
  private val httpClient: CloseableHttpClient = HttpClients.createDefault()
  @NotNull
  private val httpRegex =
    """https://\w+\.[a-zA-Z]+(/.++)?/?""".r.pattern

  @NotNull
  @NonNls
  def getResponseFromUri(@Nullable uriString: String): String =
  {
    if (uriString == null) ""
    else if (!httpRegex.matcher(uriString).matches()) ""
    else {
      val uri = new URI(uriString)
      val request = new HttpGet(uri)
      val response = httpClient.execute(request)
      val resposeEntity = response.getEntity
      EntityUtils.toString(resposeEntity)
    }
  }
}
