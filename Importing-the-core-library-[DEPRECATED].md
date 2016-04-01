The core PSL library can be added to a Java project without any additional user interface. This can be useful, for example, if you want to use the library API directly or integrate PSL into another application.

There are two steps: (1) adding PSL's Maven repositories to your .pom file, and (2) adding psl-core as a dependency.

### Add PSL's Maven repositories

Add the following snippet as an element inside your .pom file's project tag (or along with other repositories if you already have a repositories tag):

```xml
<repositories>
	<repository>
		<releases>
			<enabled>true</enabled>
			<updatePolicy>daily</updatePolicy>
			<checksumPolicy>fail</checksumPolicy>
		</releases>
		<id>psl-releases</id>
		<name>PSL Releases</name>
		<url>https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-releases/</url>
		<layout>default</layout>
	</repository>
	<repository>
		<releases>
			<enabled>true</enabled>
			<updatePolicy>daily</updatePolicy>
			<checksumPolicy>fail</checksumPolicy>
		</releases>
		<id>psl-thirdparty</id>
		<name>PSL Third Party</name>
		<url>https://scm.umiacs.umd.edu/maven/lccd/content/repositories/psl-thirdparty/</url>
		<layout>default</layout>
	</repository>
</repositories>
```

### Add psl-core dependency

Add the following snippet as an element inside your .pom file's project tag (or along with other dependencies if you already have a dependencies tag):

```xml
<dependencies>
    <dependency>
        <groupId>edu.umd.cs</groupId>
        <artifactId>psl-core</artifactId>
        <version>1.1</version>
    </dependency>
</dependencies>
```