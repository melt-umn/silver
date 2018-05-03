---
layout: sv_wiki
title: Installation guide
menu_title: Installation Guide
menu_weight: 10.0
---

# Prerequisites

[Java JDK, version 1.7](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and [Apache ANT](http://ant.apache.org/bindownload.cgi). For Ubuntu users:

```
apt-get install default-jdk-headless ant
```

For OSX, using Homebrew (install a JDK separately):

```
brew install coreutils ant
```

# Getting Silver

The **latest release** can be [downloaded here](/downloads).

To clone from GitHub instead, [see below](#using-the-latest-development-version).

# Testing with tutorials

Here is an example session, running the hello world tutorial grammar:

```
silver$ cd tutorials/hello
silver/tutorials/hello$ ./silver-compile
 -- SNIPPED --
silver/tutorials/hello$ java -jar hello.jar
Hello, World!
```

If you have any issues, first try the [frequently asked question page]({{ "/faq/" | prepend: site.silver_base }}) to see if there are any questions like yours.

# Installing the 'silver' script

```
silver$ ./support/bin/install-silver-bin
```

Note that this assumes you have a ~/bin. In most distributions, if you
don't have a ~/bin, all you have to do is `mkdir ~/bin`, and the
default shell scripts will notice it and add it to your `PATH` next
time your shell is started. 

At this point, Silver should be all set. You can test it with: (leaving off from above)

```
silver$ cd tutorials
silver/tutorials$ silver hello
 -- SNIPPED --
silver/tutorials$ java -jar hello.jar
Hello World!
```

Note that this differs from previously by using the '`silver`' script
in `~/bin` instead of the local `silver-compile` script, and it is
only in the `tutorials` directory, not in `tutorials/hello`. 


# Using the latest development version

Instead of downloading a relase, Silver can be checked out directly from GitHub instead.

## Additional prerequisites

Git and wget. For Ubuntu users:

```
apt-get install default-jdk ant git wget
```

For OSX:

```
# Again, install Java separately.
brew install coreutils ant git wget
```

## Checking out Silver

Wherever you wish to checkout the repository, do this:

```
$ git clone https://github.com/melt-umn/silver.git
$ cd silver
silver$ ./update
```

And then proceed with the instructions above (e.g. testing with tutorials, installing `silver` to `~/bin`, etc.)

## Updating jars

To update the a version cloned from GitHub, run:

```
silver$ ./update
```

This will pull the latest changes, and update your working copy. It
will also download the latest jars (which may be necessary! Silver is
written in Silver, so there can be bootstrapping issues) and clear out
any generated files, which may now be stale with the new version.

## Building Silver

See [here]({{ "/dev/building" | prepend: site.silver_base }}).

