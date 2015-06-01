This is a HOWTO on changing the version number in the PSL code base. A new version number should be applied as a new commit that does nothing else, so make sure you are working on a clean working copy with no uncommitted changes.

# Version Number Policy

Version numbers consist of the following components:

* x = major version
* y = minor version
* z = patch version

Your new version number should be of the form x.y.z (for a stable version) or x.y.z-SNAPSHOT (for an unstable version). Note that the patch version is not written if it is 0. For example, version 1.1 is always written as x.y, not x.y.z, and version 1.1-SNAPSHOT is always written as x.y-SNAPSHOT, not x.y.z-SNAPSHOT. If the new version is just of the form x.y or x.y-SNAPSHOT, ignore the ".z" in the below instructions.

All the occurrences of a PSL version number should be kept in sync, i.e., have the same value for all occurrences in all `pom.xml` files and other resources across all modules. In addition, **only one commit in the entire Git repository should have a particular stable version number**.

# Edit the code

Version numbers appear as a module's version in its `pom.xml` file, as well as the version of parents and dependencies.
The following list is all the occurrences of the version number in the PSL code (relative to the root directory):

1. `pom.xml` (1x: version)
1. `psl-addon/pom.xml` (2x: version and parent version)
1. `psl-addon/psl-addon-mosek/pom.xml` (3x: version, parent version, and psl-core dependency version)
1. `psl-archetype/pom.xml` (2x: version and parent version)
1. `psl-archetype/psl-archetype-example/pom.xml` (2x: version and parent version)
1. `psl-archetype/psl-archetype-example/src/main/resources/META-INF/maven/archetype-metadata.xml` (1x: property default value)
1. `psl-archetype/psl-archetype-example/src/main/resources/archetype-resources/pom.xml` (1x: psl-groovy dependency version)
1. `psl-archetype/psl-archetype-groovy/pom.xml` (2x: version and parent version)
1. `psl-archetype/psl-archetype-groovy/src/main/resources/archetype-resources/pom.xml` (1x: psl-groovy dependency version)
1. `psl-core/pom.xml` (2x: version and parent version)
1. `psl-groovy/pom.xml` (3x: version, parent version, and psl-core dependency version)
1. `psl-parser/pom.xml` (3x: version, parent version, and psl-core dependency version)

**Total line changes: 23**

Remember to check the diff statistics before proceeding.

### OSX Script for Changing Version Numbers in the Code

```
#!/bin/bash

# THIS VERSION ONLY WORKS FOR THE MAC OSX VERSION OF SED

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 2 ] || die "Two arguments, old and new versions, required"

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-addon/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-addon/psl-addon-mosek/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-archetype/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-archetype/psl-archetype-example/pom.xml

sed -i "" "s_<defaultValue>$1\</defaultValue>_<defaultValue>$2</defaultValue>_g" psl-archetype/psl-archetype-example/src/main/resources/META-INF/maven/archetype-metadata.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-archetype/psl-archetype-example/src/main/resources/archetype-resources/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-archetype/psl-archetype-groovy/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-archetype/psl-archetype-groovy/src/main/resources/archetype-resources/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-core/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-groovy/pom.xml

sed -i "" "s_<version>$1\</version>_<version>$2</version>_g" psl-parser/pom.xml

git diff --shortstat

echo "Does the above say 23 lines added and deleted? IF NOT, SOMETHING WENT WRONG!"
```

### Linux Script for Changing Version Numbers in the Code

```
#!/bin/bash

# THIS VERSION ONLY WORKS FOR THE LINUX VERSION OF SED

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 2 ] || die "Two arguments, old and new versions, required"

sed -i "s_<version>$1</version>_<version>$2</version>_g" pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-addon/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-addon/psl-addon-mosek/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-archetype/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-archetype/psl-archetype-example/pom.xml

sed -i "s_<defaultValue>$1</defaultValue>_<defaultValue>$2</defaultValue>_g" psl-archetype/psl-archetype-example/src/main/resources/META-INF/maven/archetype-metadata.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-archetype/psl-archetype-example/src/main/resources/archetype-resources/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-archetype/psl-archetype-groovy/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-archetype/psl-archetype-groovy/src/main/resources/archetype-resources/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-core/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-groovy/pom.xml

sed -i "s_<version>$1</version>_<version>$2</version>_g" psl-parser/pom.xml

git diff --shortstat

echo "Does the above say 23 lines added and deleted? IF NOT, SOMETHING WENT WRONG!"
```

# Commit

Commit the changes with one of the following commit messages.

If you are changing to a stable version, use:
```
Version x.y.z
```

If you are changing to a new snapshot version, use:
```
Started x.y.z-SNAPSHOT
```

Push your commit when finished.