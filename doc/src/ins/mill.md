Installing with Mill is while might look complicated, is still easy if you know scala, and have learned how Mill handles
it's things, yet it is true it's quite unique.

Adding JitPack to a custom ZincWorker object
```scala
// In your build.sc or anywhere
import coursier.maven.MavenRepository

object ZincWorkerWithJitPack extends ZincWorkerModule {
  def repositories() = super.repositories ++ Seq(
    MavenRepository("https://jitpack.io")
  )
}
```

Adding the custom ZincWorker and the dependency
```scala
// In your build.sc
import your.ZincWorkerWithJitPack

object YourScalaProject extends ScalaModule { //or JavaModule
  // other settings
  def zincWorker = ZincWorkerWithJitPack
  def ivyDeps = Agg(
    // other dependencies
    ivy"com.github.isbodand:nhstatistics-core:1.3.5"
  )
}
```
