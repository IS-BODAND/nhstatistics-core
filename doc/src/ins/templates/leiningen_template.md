While Leiningen is mainly used by and for [Clojure](https://clojure.org), it also runs on the JVM so this library is 
available to it through normal Maven repositories, by doing the same thing as the others.

Adding JitPack:
```clojure
; In your project.clj
:repositories [ ; other repositories
               ["jitpack" "https://jitpack.io"]]
```
Adding the dependency:
```clojure
; In your project.clj
:dependencies [ ; other dependencies
               [com.github.isbodand/nhstatistics-core "==!version=="]]
```
