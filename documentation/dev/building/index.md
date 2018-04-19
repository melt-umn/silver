---
layout: sv_wiki
title: Building Silver
---

## Prerequisites

Go see the [installation guide](install-guide) on getting the latest
development version to use. 

## Building

Silver is written in Silver, which causes no end of terrific and fun
bootstrapping entertainment. But, in most cases it's a total
non-issue. 

Building Silver should be this easy:

```
./deep-rebuild
```

But, on occasion, changes are made that make bootstrapping difficult. Generally speaking, the solution should be this easy:

```
rm jars/*.jar
./fetch-jars
./deep-rebuild
```

If it's not that easy, then somebody broke the build.

The repo doesn't have jars in it. fetch-jars will get them, either via NFS or via HTTP (new! shiny!) if you're not on a CS machine.

The deep-rebuild script rebuilds Silver basically 3 times, along with some runtime rebuilding thrown in there for good measure.

If you want to just test whether Silver builds, instead of deep-rebuild, run:

```
./self-compile
```

And that just does one recompile.  During development that'll be your friend.

Once self-compile succeeds, the new binary is under build/. To make use of it, copy it to jars/.

**ALWAYS** run deep-rebuild before committing any changes.  You should always run the tests under test, too.

## Running the tests

```
cd test
./runtest
```

Should build a test harness, and go through and run each test in the test/ directory.

There should be no failures.

## Jars backup

It's usually quite useful to do:

```
mkdir -p JARS-BAK
cp jars/* JARS-BAK/
```

before beginning something that might threaten Silver's ability to build itself. You could always re-./fetch-jars, but this is faster. And that directory name is already in .hgignore. :)

## MELT folks on UMN CS machines

Do this for cool performance:

```
mkdir -p /export/scratch/$USER-generated/src
mkdir -p /export/scratch/$USER-generated/bin
ln -s /export/scratch/$USER-generated/src generated/src
ln -s /export/scratch/$USER-generated/bin generated/bin
```

That way all those pesky temporary files can get stored on fast local disk, rather than slow NFS.

Also, check to make sure you're using the local version of java (installed on our lab machines) instead of the NFS version.

Good:

```
% which java
/usr/bin/java
```

Bad:

```
% which java
/soft/jdk-1.6.0_21/bin/java
```

On your own machines none of this should be necessary.
