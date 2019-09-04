---
layout: sv_wiki
title: Frequently asked questions
menu_title: FAQ
menu_weight: 70.0
---



## Why do I get a NoClassDefFoundError when trying to run a tutorial?

Possibilities:

  * Be sure you're using a reasonable JVM. `java -version` should be at least Java 1.6. We know silver does NOT work with `gcj`. Use OpenJDK or the Oracle distributions.
  * You have moved Silver since building that jar.  In the default build mode, Silver hard codes an absolute path to the runtime jars into generated jars. Try simply rebuilding the tutorial:
```
./silver-compile --clean
```
  * You are on Windows.  That hardcoded path doesn't work, so you may have to use a different build mode, to produce a jar with no dependencies:
```
./silver-compile --clean --onejar
```
  * There are spaces in the path to Silver.  Solution is the same as on windows (use --onejar.)  (Why is this a problem? Well, apparently Java manifest files don't like spaces in paths. It's only our fault in that this probably shouldn't be our default build method.)


## What do I do if javac raises errors on Silver-generated code?

There are very few known wrong-code bugs in Silver. There are, however, several known bugs in the build system.

Try re-running silver with the `--clean` flag. This should force Silver to regenerate the out-of-date java files, solving the javac error.

If not, please file a bug report.

## How do I resolve stack overflows or out of memory errors?

Unfortunately, the JVM isn't required to support tail call elimination, and Silver, being a purely functional language, can sometimes exhaust stack space.

The solution is to use the `-Xss` option the JVM provides to give it more stack space.  The default script (`support/bin/silver`) uses `-Xmx2000M -Xss4M`, which is nearly always enough.

(In the past, the `-Xmx` option was usually necessary to increase heap space, too. These days, it just doesn't really hurt to leave it.)

## Why is the Silver compiler so painfully slow?

Although Silver isn't going to win the language shootout benchmarks anytime soon, it should be reasonably fast!

However, it does generate a large number of small intermediate files in the generated directory.  There are two known cases where things might slow to a crawl:

  * Windows Vista seems to just be flat-out terrible. Now you have an excuse to go get 7. Or something else.
  * The `generated` directory is stored on a network filesystem.  There shouldn't ever be anything that needs saving/backing up inside the `generated` directory.  We suggest symlinking it or setting the SILVER\_GEN environment variable to store that directory locally. (MELT internal people on CS machines: symlink it to scratch space.)

## Does Silver run on Windows?

Yes, but you have to use the `--onejar` flag.  By default Silver embeds full paths to the runtime jars in the manifest, since that produces smaller jars and is quicker for development.  For some reason (probably because it's terrible Java practice) this just doesn't work properly on windows machines.  `--onejar` just mixes the runtime straight into the generated jar, so this problem is avoided. For the moment, this is fine; the runtime isn't too big.

Better solutions are quite possible, but just haven't been developed yet.
