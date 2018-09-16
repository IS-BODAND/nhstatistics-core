Installing with Gradle is just as easy as it should be. All that's needed is to add the JitPack repository and the 
dependency of nhstatistics-core.

Adding JitPack:  
```groovy
repositories {
    // other repositories
    maven { url 'https://jitpack.io' }
}
```
Adding the dependency:  
```groovy
dependencies {
    // other dependencies
    implementation 'org.bitbucket.bodand:nhstatistics-core:1.0'
}
```
