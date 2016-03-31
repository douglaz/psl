### Getting started with Git
The [Git website](http://git-scm.com/) has information on installing Git, as do the GitHub guides mentioned below. [This tutorial](http://progit.org/book/ch1-3.html) is helpful for learning how to use Git, and [this tutorial](https://git.wiki.kernel.org/index.php/GitSvnCrashCourse) is particularly helpful for SVN users.

### Checking out branches which track remote branches

To use an existing branch in the remote repo on GitHub, create a tracking branch to track it. It can be kept in sync via ```git pull```. For example to track the branch 'develop' (assuming the GitHub repo is named 'origin') run

```
>> git branch --track develop origin/develop
```

then

```
>> git checkout develop
```