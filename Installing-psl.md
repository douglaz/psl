## Dependencies

### Java 6 JDK
Ensure that the [Java 6 JDK](http://java.sun.com/javase/6) is installed.
### Maven
PSL uses [Maven](http://maven.apache.org) to manage builds and dependencies. The first thing needed to use PSL is [ensure that Maven is installed](http://maven.apache.org/download.html). PSL is developed with Maven 3.x. See [running Maven](http://maven.apache.org/run-maven/index.html) for help using Maven to build projects.

## Get PSL
### Option A: Have Maven download PSL
One way to install PSL is from the PSL release repository. Run the following command:

```
>> mvn dependency:get \
-DrepoUrl=https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-releases/ \
-Dartifact=edu.umd.cs:psl:<PSL version number>
```

where \<PSL version number\> is replaced with the [[PSL version|versions]] you want.