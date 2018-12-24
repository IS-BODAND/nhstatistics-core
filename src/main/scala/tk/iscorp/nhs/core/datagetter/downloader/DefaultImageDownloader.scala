package tk.iscorp.nhs.core.datagetter.downloader

import java.io.{File, IOException}
import java.net.URL

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory

class DefaultImageDownloader extends ImageDownloader {
  private val logger = LoggerFactory.getLogger("DefaultImageDownloader<" + toString.dropWhile(_ != '@').drop(1) + ">")

  def downloadImages(gly: Seq[URL], path: String): Unit = {
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

    Await.ready(f1, Duration.Inf)
    Await.ready(f2, Duration.Inf)
    Await.ready(f3, Duration.Inf)
  }

  private def downloadList(urls: Seq[(URL, Int)], path: String): Unit = {
    urls.foreach { case (url, i) ⇒
      val pageFile = new File(s"$path/$i.jpg")
      try {
        FileUtils.copyURLToFile(url, pageFile)
      } catch {
        case e: IOException ⇒
          logger.error(s"Error downloading page $i")
          logger.error(e.getMessage)
      }
    }
  }
}
