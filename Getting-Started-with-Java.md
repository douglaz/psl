Application builders and advanced users can integrate PSL into their code as a library. Since the PSL codebase is organized as a Maven project, it is easiest to include PSL as a dependency via Maven.

## Integrating PSL via Maven

The [[PSL codebase | https://github.com/linqs/psl]] is organized as a Maven project with several subprojects. The subproject most likely of interest is `psl-core`, but stable versions of all the subprojects are published to the [[PSL Maven repository | https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-releases/]].

Including a PSL subproject in your Maven project is easy. It requires two steps. First, add `psl-core` (and any other subprojects) as dependencies to your `pom.xml` file:

```xml
<dependencies>
    ...
    <dependency>
        <groupId>edu.umd.cs</groupId>
        <artifactId>psl-core</artifactId>
        <version>1.2.1</version>
    </dependency>
    ...
</dependencies>
```

## The PSL API

## Example?

## Advanced Topics