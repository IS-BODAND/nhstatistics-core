You might want to output some of those great doujin you downloaded the data of  to the user. For this the library 
supplies the HentaiOutStream trait which is implemented in StandardHentaiOutStream for writing to STDOUT, and FileHentaiOutStream 
for writing to a file. FileHentaiOutStream can append to currently existing files so you can make a to-read-list or 
something with it. They all have overloaded methods to instantly print Arrays, Scala Lists, and Java ArrayLists of 
Gallery objects. They also have the C++ styled << methods overloaded so in Scala you can chain them nicely with that, 
but in Java they don't really bring much of anything worthy of mention for you need to call them as they were normal 
methods, and their names are quite interesting.

```java
// Java
public class MultipleDoujinGetterCLI {
    public static void main(String[] args) throws IndexOutOfBoundsException {
        HentaiInStream inStream = new DefaultHentaiInStream();
        Gallery oneDoujin = inStream.readByID(args[0]);
        Gallery[] moreDoujin = inStream.readMultipleByID(args);
        
        // writing to STDOUT
        HentaiOutStream outStream = new StandardHentaiOutStream();
        outStream.print(oneDoujin); 
        System.out.println("--Separator Line--");
        outStream.print(moreDoujin);
        
        // writing to File "to-read-list.txt"
        HentaiOutStream fileStream = new FileHentaiOutSream(new File("to-read-list.txt"));
        fileStream.print(oneDoujin);
        fileStream.print(moreDoujin, true);
    }
}
```
```scala
// Scala
object DoujinGetterCLI {
  def main(args: Array[String]): Unit = {
    val inStream = new DefaultHentaiInStream
    val oneDoujin: Gallery = inStream.readByID(args.head)
    val moreDoujin: Array[Gallery] = inStream.readMultipleByID(args)
    
    // writing to STDOUT
    val outStream = new StandardHentaiOutStream
    outStream.print(oneDoujin)
    println("--Separator Line--")
    outStream.print(moreDoujin)
    
    outStream << oneDoujin << moreDoujin
    
    // writing to File "to-read-list.txt"
    val fileOutStream = new FileHentaiOutStream(new File("to-read-list.txt"))
    fileOutStream.print(oneDoujin)
    fileOutStream << moreDoujin // the << method automagically appends
  }
}
```
