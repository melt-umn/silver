#!/bin/bash

# For now, this expects you to have Copper installed locally;
# this can be done by running `mvn install` from wherever you have the copper repo checkedd out.

mvn install:install-file -Dfile=../jars/SilverRuntime.jar -DgroupId=edu.umn.cs.melt -DartifactId=silver-runtime -Dversion=0.4.4-SNAPSHOT -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=../jars/silver.compiler.composed.Default.jar -DgroupId=edu.umn.cs.melt -DartifactId=silver -Dversion=0.4.4-SNAPSHOT -Dpackaging=jar -DgeneratePom=true

mvn package

