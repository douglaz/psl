This a HOWTO on releasing a new stable PSL version. All first and second level headers are steps in the process, and should be followed sequentially.

(This page also contains instructions for new SNAPSHOT versions, since this is often part of the process of releasing a stable version.)

# Preliminaries

## Get the Code Ready
A release is a single commit that increments the software's version number and does nothing else. So, before you release a version, make sure all your changes are committed and pushed, and the code is in the state in which you want to release it.

## Test the Code
Remember to test the code and double check it is ready for release. Make sure there are no errors or bugs.

1. Install the code. (Run `mvn install`.)
1. [[Install and run the examples | Installing examples]].

## Find the New Version Number
Stable version numbers are of the format x.y or x.y.z, where

* x = major version
* y = minor version
* z = patch version

The git branch the code is on should already have a version number in its `pom.xml` files of the form x.y.z-SNAPSHOT. Whatever x.y.z-SNAPSHOT is, the new version will be x.y.z. Note that the patch version is not written if it is 0. For example, version 1.1 is always written as x.y, not x.y.z. If the new version is just of the form x.y, ignore the ".z" in the below instructions.

# Create the New Stable Release

## Change the Version
The first step is to [[change the version number | Changing the version number]] to the stable version number. Remember to perform the commit at the end of the instructions.

## Tag the New Stable Version
Run the following two commands:
```
>> git tag -a x.y.z -m 'Version x.y.z'
>> git push origin x.y.z
```

# Update Git Branches

There are two ways the branch structure of the Git repo might change because of a new stable version:

1. The master branch might need to be updated
1. A working branch might need to be deleted

## Updating the Master Branch

The Master branch should always point to the commit of the highest stable version number, where x, y, and z are treated as separate orders of magnitude.

So, if the master branch points to version 1.2, then releasing 1.1.1 would not update the master branch, but releasing 1.2.1 or 1.3 would.

If the master branch is upstream of the new stable version. This makes it easy. Simply run the following commands:
**TODO: TEST THIS**

```
>> git checkout master
>> git pull x.y.z
>> git push
```

This will advance the master branch. If, however, the master branch is not upstream,
**TODO: WRITE THIS AND TEST IT**

## Deleting a working branch

# Deploy New Stable Version

With the new stable version checked out, on a machine with file system access to the repository, in the top level directory of the project (the one with the PSL project pom.xml file, not any of the subprojects), run the following command:

```
>> mvn deploy -P release
```