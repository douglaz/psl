After ensuring that the [[prerequisites]] are installed, execute the following command:

```sh
>> mvn archetype:generate -DarchetypeArtifactId=psl-archetype-groovy \
-DarchetypeRepository=https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-releases/ \
-DarchetypeGroupId=edu.umd.cs -DarchetypeVersion=1.1
```

When prompted to accept the default property values, enter 'Y'.

You can replace the version number at the end with the PSL [[version|versions]] you want to use. 

The [[Maven|http://maven.apache.org]] [[archetype plugin|http://maven.apache.org/archetype/maven-archetype-plugin/]] will then create a new project in which you can write PSL programs. The project will be configured to use the Maven project-management tool. You should be prompted for an a group ID (a Maven project namespace, just like a Java package), artifact ID (project name), and a version number for your project, as well as a name for the first Java package to create, which defaults to the specified group ID.

The PSL libraries will be downloaded automatically (if necessary) when you use Maven to compile and run this project.

The project will be set up with configuration files in

    <project root>/src/main/resources

You can place Java and Groovy source files in

    <project root>/src/main/java

A stub Groovy script will be created at

    <project root>/src/main/java/<package path>/App.groovy

which you can [[run|running a program]].

**Tips and troubleshooting**

* The Windows shell (CMD.EXE) doesn't accept line continuations ('\'), so remove those and enter the command all on one line.