After ensuring that the [[prerequisites]] are installed, users can run some example programs. 

To set up the examples, change to the directory in which you want to create the project of examples.

Then execute the following command:

```
>> mvn archetype:generate -DarchetypeArtifactId=psl-archetype-example \
-DremoteRepositories=https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-releases/ \
-DarchetypeGroupId=edu.umd.cs -DarchetypeVersion=<PSL version number>
```

where \<PSL version number\> is replaced with the PSL [[version|versions]] you want to use.

The [[Maven|http://maven.apache.org]] [[archetype plugin|http://maven.apache.org/archetype/maven-archetype-plugin/]] will then create a new project of PSL examples. The project will be configured to use the Maven project-management tool. The PSL libraries will be downloaded automatically (if necessary) when you use Maven to compile and run this project.

You can now run the [[example programs]].