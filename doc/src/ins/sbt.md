In Sbt a resolver is what Maven calls a repository. Although if I'm allowed to suggest using a project management system,
and you happen to be a beginner, learning something else might do you more good. If you want to strictly stay in the scala
ecosystem, there's Mill, otherwise JVM-wise there is Gradle and Ant, while of course understanding Maven. But please,
don't use Sbt.

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
  "com.github.isbodand" % "nhstatistics-core" % "1.3.5"
)
```

