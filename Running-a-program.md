To run a PSL program, change to the top-level directory of its project (the directory with the [Maven](http://maven.apache.org) ```pom.xml``` file).

Compile your project:

```
>> mvn compile
```

Now use Maven to generate a classpath for your project's dependencies:

```
>> mvn dependency:build-classpath -DoutputFile=<classpath file>
```

where \<classpath file\> is replaced