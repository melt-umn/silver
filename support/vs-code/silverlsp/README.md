# silverlsp README

This extension provides language server protocol-based editor features for [Silver](https://melt.cs.umn.edu/silver).

## Features

* Semantic token-based syntax highlighting, powered by [Copper](https://melt.cs.umn.edu/copper)
* Diagnostic error reporting
* View / jump to declaration
* Clean rebuild command

## Requirements

Running this extension requires Java 11.  Issues have been noted with newer versions of Java.

## Extension Settings

This extension contributes the following settings:

* `silver.enableMWDA`: Enable the modular well-definedness analysis
* `silver.jvmArgs`: JVM flags to use when launching the language server, the heap size may need to be increased for large projects
* `silver.compilerJar`: Path to a jar file containing an alternate version of the Silver compiler
* `silver.parserName`: Full name of the Silver parser to use, must be set if compiler jar is specified

## Known Issues

The use of Copper for semantic tokens means that in case of a syntax error, highlighting is not shown for the rest of the file after the syntax error.
