Since 1.3.3 the HtmlResponseProcessor class is publicly available which can create doujin Gallery objects from a 
given string. Since this was just an inner class this is not that well documented _yet_. The only use case you have 
for it is quite simple though; give it an html source of an nhentai page, and it returns a Gallery object.
```java
// Java
public class LocalDoujinLoader {
    public static void main(String[] args) {
        HtmlResponseProcessor hrp = new HtmlResponseProcessor();
        // Get the html page source from local file; I used Apache Commons IO here
        String htmlSource = FileUtils.readFileToString(new File("doujin.html"), "UTF-8"); 
        Gallery gallery = hrp.processHtmlToGallery(htmlSource);
        Gallery galleryWithISODate = hrp.processHtmlToGallery(htmlSource, true);
    }
}
```
```scala
// Scala
object LocalDoujinLoader {
  def main(args: Array[String]): Unit = {
    val hrp = new HtmlResponseProcessor
    val htmlSource = io.Source.fromFile("doujin.html").mkString
    val gallery = hrp.processHtmlToGallery(htmlSource)
    val galleryWithISODate = hrp.processHtmlToGallery(htmlSource, isoDate = true)
  }
} 
```
