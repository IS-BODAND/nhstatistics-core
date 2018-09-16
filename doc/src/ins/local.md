While it is advised to use a project/dependency management system(even Sbt) with actually managing dependencies, it is 
possible to use nhstatistics-core without doing that completely logical thing and having it as a local dependency.

For the first step you need to clone the repository from git:  
```
$ git clone https://bodand@bitbucket.org/bodand/nhstatistics-core.git
```

Then you need to build everything with gradle:
```
$ cd nhstatistics-core/
$ ./gradlew build
```

Then need to add `build/libs/nhentaistatistics-1.0-sources.jar` if you only need these sources as a local dependency to 
your project, if you want to have all dependencies required by this project you can use `nhscore-1.0.jar`.

