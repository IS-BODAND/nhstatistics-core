package tk.iscorp.nhs.datagetter

import com.esotericsoftware.kryo.NotNull
import org.apache.commons.validator.routines.UrlValidator
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils
import org.jetbrains.annotations.{NonNls, Nullable}

import java.net.URI

class HttpGetter() {
  @NotNull
  private val httpClient: CloseableHttpClient = HttpClients.createDefault()
  @NotNull
  private val urlSchemes: Array[String] = Array("http", "https")

  @NotNull
  @NonNls
  def getHttpFromUri(@Nullable uriString: String): String = {
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
