# Preliminaries

### Get the Code Ready
A release is a single commit that increments the software's version number and does nothing else. So, before you release a version, make sure all your changes are committed and pushed, and the code is in the state in which you want to release it.

### Find the New Version Number
Version numbers are of the format x.y.z, where

* x = major version
* y = minor version
* z = patch version

The git branch the code is on should already have a version number in its `pom.xml` files of the form x.y.z-SNAPSHOT. Whatever x.y.z-SNAPSHOT is, the new version will be x.y.z.

# Changing the Version
The first step is to update **all** the version numbers in the code. All the occurrences of a PSL version number should be kept in sync, i.e., have the same value for all occurrences in all `pom.xml` files and other resources across all modules. In addition, **only one commit in the entire Git repository should have a particular stable version number**. (A stable version number is a version number with "-SNAPHOT" on the end.)

The following list is all the occurrences of the version number in the PSL code (relative to the root directory):

1. `pom.xml`
1. `psl-addon/pom.xml`
1. `psl-addon/psl-addon-mosek/pom.xml`


