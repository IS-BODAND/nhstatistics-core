Installing with Sbt is just as easy as it should be. All that's needed is to add the JitPack resolver and the 
dependency of nhstatistics-core. 
Though you should be using a more capable project management system, if I'm allowed to comment on that.

Adding JitPack:  
```sbtshell
resolvers += "jitpack" at "https://jitpack.io"
```
Adding the dependency:  
```sbtshell
libraryDependencies += "org.bitbucket.bodand" % "nhstatistics-core" % "1.0"
```

