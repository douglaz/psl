After [[installing PSL|building psl]], execute the following command:

```
mvn archetype:generate -DarchetypeArtifactId=psl-archetype-groovy \
-DarchetypeGroupId=edu.umd.cs -DarchetypeVersion=<PSL version number>
```

where \<PSL version number\> is replaced with your PSL [[version|versions]].

The [[Maven|http://maven.apache.org]] [[archetype plugin|http://maven.apache.org/archetype/maven-archetype-plugin/]] will then create a new project in which you can write PSL programs. The project will be configured to use the Maven project-management tool. You should be prompted for an a group ID (a Maven project namespace, just like a Java package), artifact ID (project name), and a version number for your project, as well as a name for the first Java package to create, which defaults to the specified group ID.

The project will be set up with configuration files in

    <project root>/src/main/resources

You can place Java and Groovy source files in

    <project root>/src/main/java

A stub Groovy script will be created at

    <project root>/src/main/java/<package path>/App.groovy