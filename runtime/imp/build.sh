#!/bin/bash

# On CS machines, load the module 'java/maven'

set -e

# Sanity
if [ ! -d ../../runtime ]; then
  echo "In wrong directory?  Run from silver/runtime/imp as ./build.sh"
  exit 1
fi

if [ ! -d ../../jars ]; then
  mkdir ../../jars
fi

cd main

# We need to build this with the knowledge of what's generated by the grammars
# core and ide. So use silver to generate the corresponding java files here.
java -jar ../../../jars/RunSilver.jar -G . ide
rm build.xml

mvn clean package

#cp target/edu.umn.cs.melt.ide.copper-*.jar ../../../jars/IDEPluginRuntime.jar

mkdir temp
cd temp
jar xf ../target/edu.umn.cs.melt.ide.copper-*.jar
rm -r ide core
jar cmf META-INF/MANIFEST.MF ../IDEPluginRuntime.jar *
cd ..
rm -r temp
mv IDEPluginRuntime.jar ../../../jars/

# clean up generated code
rm -r src/core src/ide

# unnecessary, but for symmetry, leave 'main'
cd ..

