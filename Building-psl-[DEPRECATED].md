The PSL source code is [publicly available](https://github.com/linqs/psl/) and hosted on [GitHub](https://github.com). See [[Git policies]] for details on the organization of the PSL repository.

### Quick start

If you are already comfortable using [Git](http://git-scm.com/) and you don't want or need to push commits to GitHub, then you can just clone the PSL repository.

For read-only access:

```
>> git clone git://github.com/linqs/psl.git
```

For read-write access:

```
>> git clone git@github.com:linqs/psl.git
```

Now skip ahead to the section on compiling PSL.

### Getting started with Git

The [Git website](http://git-scm.com/) has information on installing Git, as do the GitHub guides mentioned below. [This tutorial](http://progit.org/book/ch1-3.html) is helpful for learning how to use Git, and [this tutorial](https://git.wiki.kernel.org/index.php/GitSvnCrashCourse) is particularly helpful for SVN users.

Once Git is installed and you're ready to use it, you can run the above command to clone the PSL repository.

### Checking out branches which track remote branches

To use an existing branch in the remote repo on GitHub, create a tracking branch to track it. It can be kept in sync via ```git pull```. For example to track the branch 'develop' (assuming the GitHub repo is named 'origin') run

```
>> git branch --track develop origin/develop
```

then

```
>> git checkout develop
```

### Preparing for pushing commits to the PSL repository or a fork on GitHub

Create a free account on [GitHub](https://github.com). Then follow one of the following sets of instructions to set up Git and GitHub:

* [Linux guide](http://help.github.com/linux-set-up-git/)
* [OSX guide](http://help.github.com/mac-set-up-git/)
* [Windows guide](http://help.github.com/win-set-up-git/)

You can [fork the PSL repository](http://help.github.com/fork-a-repo/), which means that you create a fork hosted on GitHub. You then clone that repository to a local machine, make commits, and, optionally, push some or all of those commits back to the repository on GitHub. Those commits are then publicly available (unless you have paid GitHub for private hosting).

### Building PSL

Change to the top-level directory of your working copy and run

```
>> mvn compile
```

You can install PSL to your local Maven repository by running

```
>> mvn install
```

### Updating your Projects

Remember to update your project's `pom.xml` file with the (possibly) new version you installed.