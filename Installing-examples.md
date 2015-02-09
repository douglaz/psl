After ensuring that the [[prerequisites]] are installed, users can run some example programs. 

To set up the examples, change to the directory in which you want to create the project of examples.

Then execute the following command:

```sh
>> mvn archetype:generate -DarchetypeArtifactId=psl-archetype-example \
-DarchetypeRepository=https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-releases/ \
-DarchetypeGroupId=edu.umd.cs -DarchetypeVersion=1.1
```

When prompted to accept the default property values, enter 'Y'.

You can replace the version number at the end with the PSL [[version|versions]] you want to use. 

The [[Maven|http://maven.apache.org]] [[archetype plugin|http://maven.apache.org/archetype/maven-archetype-plugin/]] will then create a new project of PSL examples. The project will be configured to use the Maven project-management tool. The PSL libraries will be downloaded automatically (if necessary) when you use Maven to compile and run this project.

You can now [[run|Running a program]] the [[example programs]].

**Tips and troubleshooting**

* The Windows shell (CMD.EXE) doesn't accept line continuations ('\'), so remove those and enter the command all on one line.