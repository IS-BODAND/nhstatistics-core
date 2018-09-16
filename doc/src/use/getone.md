Getting a doujin is just the easiest thing with the NHStatistics Core library. All you need is to implement the 
HentaiInStream trait (interface with implementable stuff, for Java people), and you can use that to get your doijin. 
Thankfully I already implemented one default version which should always work. It's a trait(interface) because of 
inner conflicts, but as I said the implemented DefaultHentaiInStream should work.

Now using this DefaultHentaiInStream class is really easy:
```java
// Java
public class DoujinGetterCLI {
    public static void main(String[] args) throws IndexOutOfBoundsException {
        HentaiInStream inStream = new DefaultHentaiInStream();
        Gallery doujin = inStream.readByID(args[0]);
    }
}
```
```scala
// Scala
object DoujinGetterCLI {
  def main(args: Array[String]): Unit = {
    val inStream = new DefaultHentaiInStream
    val doujin: Gallery = inStream.readByID(args.head)
  }
}
```

Now both of these snippets read one doujin specified by the user supplied command line argument. 
  
If a second true value is supplied to readByID it reads the upload date as ISO 8601 compliant. In later 
releases this will be removed and some kind of Date class will be used to store the date.
