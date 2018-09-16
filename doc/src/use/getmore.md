Getting multiple doujin is just as easy as it is to get one doujin with HentaiInStream. 
The readMultipleByID method takes an Array, a Scala List, or a Java ArrayList of Strings as an input and returns a 
corresponding data structure of read Gallery objects.

```java
// Java
public class MultipleDoujinGetterCLI {
    public static void main(String[] args) {
        HentaiInStream inStream = new DefaultHentaiInStream();
        Gallery[] doujin = inStream.readMultipleByID(args);
    }
}
```
```scala
// Scala
object DoujinGetterCLI {
  def main(args: Array[String]): Unit = {
    val inStream = new DefaultHentaiInStream
    val doujin: Array[Gallery] = inStream.readMultipleByID(args)
  }
}
```

The readMultipleByID currently can also take the second boolean parameter for the ISO compliant date.
