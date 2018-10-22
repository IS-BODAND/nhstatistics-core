The library offers two Streams for doujin IO, both are traits with default implementations. These are just called streams,
they do not implement Java's Stream interfaces, nor behave as if they did.

Let's start with the doujin input. For that the `HentaiInStream` trait has been declared, which is implemented in
`DefaultHentaiInStream` and most likely doesn't need to be reimplemented.

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

