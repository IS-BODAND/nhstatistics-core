Since 1.3.3 the `HtmlResponseProcessor` class is public, and you can use it to parse nhentai page sources, because you
have them or something. Or you are someone from behind nhentai and don't want to download all your webpages as you have
them already. Whatever is the case, with this class you can give it a html source in a String and it returns you an
instance of `Gallery` generated from that page.

```java
// Java
public class LocalDoujinLoader {
    public static void main(String[] args) {
        HtmlResponseProcessor hrp = new HtmlResponseProcessor();
        // Get the html page source from local file; I used Apache Commons IO here
        String htmlSource = FileUtils.readFileToString(new File("doujin.html"), "UTF-8"); 
        Gallery gallery = hrp.processHtmlToGallery(htmlSource);
        Gallery galleryWithISODate = hrp.processHtmlToGallery(htmlSource);
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
    val galleryWithISODate = hrp.processHtmlToGallery(htmlSource)
  }
} 
```
