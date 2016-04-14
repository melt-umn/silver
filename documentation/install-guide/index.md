---
layout: sv_wiki
title: Installation guide
menu_title: Installation Guide
menu_weight: 1.00
---

# Prerequisites

[Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and [Apache ANT](http://ant.apache.org/bindownload.cgi). For Ubuntu users:

```
apt-get install default-jdk ant
```

For OSX, using Homebrew:

```
# Install a java JDK separately.
brew install coreutils ant
```

# Getting Silver

The **latest release** can be found [here](http://melt.cs.umn.edu/silver/downloads.html) (mirrored [here](http://code.google.com/p/silver/downloads/list).)

The **latest development build** can be downloaded [here](http://melt.cs.umn.edu/downloads/silver-dev/silver-latest.tar.gz).

To use the bleeding edge from version control, [see below](InstallGuide#Using_the_latest_development_version.md).

# Testing with tutorials

Here is an example session, running the hello world tutorial grammar:

```
silver$ cd tutorials/hello
silver/tutorials/hello$ ./silver-compile
 -- SNIPPED --
silver/tutorials/hello$ java -jar hello.jar
Hello, World!
```

If you have any issues, first try the [frequently asked question page]({{ "/silver/doc/faq/" | prepend: site.sv_wiki_base }}) to see if there are any questions like yours.

# Installing the 'silver' script

```
silver$ cd support/bin
silver/support/bin$ ./install-silver-bin
```

Note that this assumes you have a ~/bin. In most distributions, if you don't have a ~/bin, all you have to do is `mkdir ~/bin`, and the default shell scripts will notice it and add it to your `PATH` next time your shell is started.

At this point, Silver should be all set. You can test it with: (leaving off from above)

```
silver/support/bin$ cd ../../tutorials
silver/tutorials$ silver hello
 -- SNIPPED --
silver/tutorials$ java -jar hello.jar
Hello World!
```

Note that this differs from previously by using the '`silver`' script in `~/bin` instead of the local `silver-compile` script, and it is only in the `tutorials` directory, not in `tutorials/hello`.

# Using the latest development version

This section is only for anyone who would like to help develop Silver itself.

## Additional prerequisites

Mercurial, and wget. For Ubuntu users:

```
apt-get install default-jdk ant mercurial wget
```

For OSX:

```
# Again, install Java separately.
brew install coreutils ant mercurial wget
```

## Checking out Silver

Wherever you wish to checkout the repository, do this:

```
$ hg clone https://code.google.com/p/silver
$ cd silver
silver$ ./fetch-jars
silver$ cd support/bin
silver/support/bin$ ./install-silver-bin
```

All this does is get you the latest repository checkout, the latest jars (which are not stored in the repository), and puts the `silver` shell script in `~/bin` pointing to these things (see [above](InstallGuide#Installing_the_%27silver%27_script.md)).

## Updating it

To update the development version, run:

```
silver$ ./update
```

This will pull the latest changes, and update your working copy. It will also download the latest jars (which may be necessary! Silver is written in Silver, so there can be bootstrapping issues) and clear out any generated files, which may now be stale with the new version.
