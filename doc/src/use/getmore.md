`HentaiInStream` can read multiple doujin at once by using the `HentaiInStream#readMultipleByID` which takes an argument
of multiple Strings in an Array, a Scala List, or a Java util.List. The returned value always uses the same collection
which was used to provide the Strings. i.e Here, we supplied an Array of String and it returned Array of Gallery. 

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
object MultipleDoujinGetterCLI {
  def main(args: Array[String]): Unit = {
    val inStream = new DefaultHentaiInStream
    val doujin: Array[Gallery] = inStream.readMultipleByID(args)
  }
}
```
