package tk.iscorp.nhs.core.datagetter.downloader

import java.io.{File, IOException}
import java.net.URL

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory

class DefaultImageDownloader extends ImageDownloader {
  private val logger = LoggerFactory.getLogger("DefaultImageDownloader<" + toString.dropWhile(_ != '@').drop(1) + ">")

  def downloadImages(gly: Seq[URL], path: String): Int = {
    val ret = new ArrayBuffer[Int]()

    val (firstThird, twoThirds) = gly.zipWithIndex.splitAt((gly.size * 1 / 3).ceil.toInt)
    val (secondThird, thirdThird) = twoThirds.splitAt((gly.size * 1 / 2).ceil.toInt)

    val f1 = Future {
      downloadList(firstThird, path)
    }
    val f2 = Future {
      downloadList(secondThird, path)
    }
    val f3 = Future {
      downloadList(thirdThird, path)
    }

    ret += Await.result(f1, Duration.Inf)
    ret += Await.result(f2, Duration.Inf)
    ret += Await.result(f3, Duration.Inf)

    ret.sum
  }

  private def downloadList(urls: Seq[(URL, Int)], path: String): Int = {
    var pagesDownloaded = 0
    urls.foreach { case (url, i) ⇒
      val pageFile = new File(s"$path/$i.jpg")
      try {
        FileUtils.copyURLToFile(url, pageFile)
      } catch {
        case e: IOException ⇒
          logger.error(s"Error downloading page $i")
          logger.error(e.getMessage)
      }

      if (pageFile.exists()) {
        pagesDownloaded += 1
      }
    }
    pagesDownloaded
  }
}
