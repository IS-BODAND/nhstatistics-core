Installing with a project/dependency management system is just as easy as it should be. 
All that's needed is to add the JitPack repository and the dependency of nhstatistics-core. I present here the sample code
to do this with Maven, Gradle, Ant, Sbt, Mill, and Leiningen. If you use any other system and wish to see that code here, just create an issue 
on GitHub.

Adding JitPack:  
```xml
<!--In your pom.xml-->
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
<!--In your pom.xml-->
<dependencies>
  <!-- other dependencies -->
  <dependency>
    <groupId>'com.github.isbodand</groupId>
    <artifactId>nhstatistics-core</artifactId>
    <version>==!version==</version>
  </dependency>
</dependencies>
```
