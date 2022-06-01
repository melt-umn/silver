#!/bin/bash

mvn install:install-file -Dfile=../jars/SilverRuntime.jar -DgroupId=edu.umn.cs.melt -DartifactId=silver-runtime -Dversion=0.4.4-SNAPSHOT -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=../jars/silver.compiler.composed.Default.jar -DgroupId=edu.umn.cs.melt -DartifactId=silver -Dversion=0.4.4-SNAPSHOT -Dpackaging=jar -DgeneratePom=true

mvn package

