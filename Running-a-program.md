To run a PSL program, change to the top-level directory of its project (the directory with the [Maven](http://maven.apache.org) ```pom.xml``` file).

Compile your project:

```
>> mvn compile
```

Now use Maven to generate a classpath for your project's dependencies:

```
>> mvn dependency:build-classpath -Dmdep.outputFile=<classpath file>
```

where \<classpath file\> is replaced with a file in which you will store classpath information.

You can now run a class with the command

```
>> java -cp ./target/classes:`cat <classpath file>` <fully qualified class name>
```

where \<fully qualified class name\> is the full name (package and class) of the class you want to run (e.g., edu.umd.cs.example.BasicExample).

**Tips and troubleshooting**

* The classpath for the dependencies will need to be regenerated to incorporate any new dependencies or dependencies in new locations (such as when dependency versions have been changed).
* PSL and PSL projects are configured to use the [Groovy-Eclipse compiler for Maven](http://groovy.codehaus.org/Groovy-Eclipse+compiler+plugin+for+Maven) to compile Groovy scripts. (The reference to [Eclipse](http://www.eclipse.org) in its name signifies that it is based on the same compiler used in Eclipse, not that Eclipse is required.) This compiler creates regular Java class files from your Groovy scripts. The main methods generated for these class files run the scripts. Hence, the `java` command is used to run a script.
* Classes can also be run with the command

```
>> mvn exec:java -Dexec.mainClass=<fully qualified class name>
```

The advantages are that the project does not need to be compiled separately and the classpath does not need to be generated or updated separately. The disadvantages are that the class output is preceded and succeeded by Maven output, exception stack traces are not printed by default (add the `-e` switch), and Maven adds some overhead to execution (sometimes a significant amount, especially on less powerful machines).