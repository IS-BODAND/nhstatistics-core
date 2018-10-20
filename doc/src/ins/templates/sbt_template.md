In Sbt a resolver is what Maven calls a repository. 

Adding JitPack:  
```scala
// In your build.sbt
resolvers ++= Seq(
  // other resolvers
  "jitpack" at "https://jitpack.io"
)
```
Adding the dependency:  
```scala
// In your build.sbt
libraryDependencies ++= Seq(
  // other dependencies
  "com.github.isbodand" % "nhstatistics-core" % "==!version=="
)
```

