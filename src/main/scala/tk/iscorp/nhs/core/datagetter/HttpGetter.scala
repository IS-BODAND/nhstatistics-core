/*******************************************************************************
 InfoSoft OpenSource Licence

 Copyright (c) 2018.

 Permission is hereby granted to absolutely free usage of this software in any way
 that doesn't conflict with the the licensee's local laws. Modification and
 redistribution of this software is permitted, but the changes must be stated, and
 the source software (this one) must be stated. Redistributed versions must be
 licensed under the InfoSoft OpenSource Licence. Projects using a (modified or not)
 version of this software, may or may not use the InfoSoft OpenSource Licence.
 Commercial distribution is permitted. This licence must be made available to the
 end user from within the program, and to all programmers from a IS-OSL.LICENCE.txt file.
 Inclusion of the licence in the source file(s) may be used instead of the IS-OSL.LICENCE.txt file.

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
