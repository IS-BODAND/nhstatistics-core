package tk.iscorp.nhs.core.datagetter.downloader

import java.net.URL

trait ImageDownloader {
  def downloadImages(urls: Seq[URL], path: String): Int
}
