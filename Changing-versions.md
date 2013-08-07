To change the [[version|versions]] of PSL your project uses, edit your project's `pom.xml` file. The POM will declare dependencies on one or more PSL artifacts, e.g.,

```xml
<dependencies>
    ...
    <dependency>
        <groupId>edu.umd.cs</groupId>
        <artifactId>psl-groovy</artifactId>
        <version>1.1</version>
    </dependency>
    ...
</dependencies>
```

Change the version element of each such dependency to a new version (all the same one) and rebuild.