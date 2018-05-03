---
layout: sv_wiki
title: Command line reference
menu_title: Command Line 
menu_weight: 20.0
---

# Running Silver

If you have followed the [installation guide]({{ "/install-guide/" | prepend: site.silver_base }}), you have a `silver` script in your `~/bin` which is typically invoked as follows:

```
silver grammar:to:build
```

If you do not want to use the `silver` script, or are on a platform other than linux, you will likely want to write a build script that looks something like the following:

```
java -Xss4M -Xmx1800M -jar $SILVER_HOME/jars/silver.composed.Default.jar  grammar:to:build
```

In both cases _grammar:to:build_ is the name of the grammar to compile. By default, the Silver compiler will look for the directory `./grammar/to/build` from where you run the silver script.

The optional command-line flags are described below.


## Command line arguments

The common optional flags used with Silver are:

  * `-o` _jar-name_
    * Changes the name of the generated jar file to the specified name. (Include the `.jar`)

  * `--clean`
    * Ignores generated files from previous runs, and rebuilds everything.

  * `-I` _paths_
    * Instructs Silver to look for grammars in the specified directories before looking in those specified in `GRAMMAR_PATH`. (Or the defaults, of the current directory and the `grammars` directory where Silver is installed.)

Less common:

  * `--copperdump`
    * By default, a dump file containing information about the parser is generated only when LALR conflicts are detected, to aid in debugging the grammar.  This flag will force a dump to occur even without errors.  This may be useful in debugging parse errors that you think shouldn't be happening: copper reports the parser state, and with the assistance of this information, may reveal what is going wrong.
  * `--warn-all`
    * Perform some extra analysis. This includes a modularity analysis that will help find, for example, missing equations.

      If you want to see the generated flow-types and dependencies, then add the `--dump-flow-graph` flag.

      More information on the modular well-definedness analysis can be found [here](../concepts/modular-well-definedness).

  * `-G` _path_
    * Specifies where to store Silver-generated temporary files.  This overrides the value found in `SILVER_GEN`. (And the default is in the `generated` directory where Silver is installed.)



**Note:** If you encounter a strange build error, try again with the `--clean` flag. There are a few relatively rare build bugs that can still cause generated files to become stale.


## Environment variables

Most uses of Silver should not require any adjustments to environment variables but
they are documented here nonetheless.

  * `GRAMMAR_PATH`
    * A colon separated list of paths to search for grammars.  By default, this is set to include the current directory and the `grammars` directory in the Silver distribution. Generally, if you need Silver to look in other directories using the `-I` switch is preferable.

  * `SILVER_HOME`
    * The location of the Silver jar files. If the `silver` script is used, this flag is set there.  If running Silver by calling the `RunSilver.jar` file directly this may not need to be set as the jar file will set it automatically.

  * `SILVER_GEN`
    * The directory to generated temporary files within. This is set by default to the `generated` directory in the Silver distribution. An alternative to modifying this variable is to simply symlink this directory to the directory you wish to store temporary files in.

For the `silver` script, there is also

  * `SVJVM_FLAGS`
    * Used to adjust flags sent to the jvm. For example, `SVJVM_FLAGS="-Xss8M -Xmx4000M -verbose:gc" silver grammar:to:build` (bash syntax) will give the jvm more memory and print some information every time a garbage collection happens.

## The `main` function

If the grammar specified on the command line to build also contains a `main` function, then the resulting jar file will be executable.  `main` functions should have the following basic form:

```
function main
IOVal<Integer> ::= args::[String] ioin::IO
{
  return ioval(ioin, 0);
}
```

For an explanation of how IO works in Silver, see [IO Functions]({{ "/lib/io/" | prepend: site.silver_base }}).  The integer value wrapped inside `IOVal` is the exit status of the program (0 is success.)  And of course, the command line arguments are provided as a list of strings.

Simple examples of main functions can be found in every tutorial distributed with Silver.

# Nailgunning Silver

Under `support/nailgun` there are two scripts for running `nailgun` on Silver jars. These require `nailgun` of course and the scripts specifically require `bash`. (On lab machines the scripts will find a nailgun build under our project space automatically. On Ubuntu machines, you can `apt-get install nailgun` and it'll work.)

Nailgun runs everything in one jvm, saving repeated warm-up time. For largish test suites, this is possibly huge: our ableC test suite went from 45 minutes to 2.

To use these scripts, run `./install-sv-nailgun` in `support/nailgun`. This requires `~/bin` and bash.

To use nailgun, first initialize your bash shell with **`. sv-nailgun`**. Note that's "dot space" not "./". This 'sources' the script into your bash shell, setting some environment variables and adding some functions. If it fails, there should be some sort of error message.

Next you initialize the jar you want to run repeatedly with **`sv-serve <jarfile>`**. Unless an error appears, this should work.

After that, you can call that jar repeatedly with **`sv-call <args...>`**. Each of these will re-use the JVM.

When done, call **`sv-serve-stop`** to stop that JVM.

Enjoy the speedups.

**Mac OS X Bug**: you probably need Homebrew. The default `sed` command on OSX is broken somehow and this will cause errors when you run `sv-call` complaining that it can't find exactly the right class that it should be able to find. The trouble is that there's a unprintable trailing carriage return that should be stripped out, but apparently isn't. Install GNU sed instead and everything should be okay.
