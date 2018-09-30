Installing with Maven is just as easy as it should be. All that's needed is to add the JitPack repository and the 
dependency of nhstatistics-core.

Adding JitPack:  
```xml

<repositories>
  <!-- other repositories -->
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```
Adding the dependency:  
```xml

<dependency>
  <!-- other dependencies -->
  <groupId>'com.github.isbodand</groupId>
  <artifactId>nhstatistics-core</artifactId>
  <version>1.3.2</version>
</dependency>
```
