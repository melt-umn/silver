#!/bin/bash

set -e

# On CS machines, load the module 'java/maven'
# On personal machines, make sure 'mvn' is in your path.
BUILD_COMM="mvn"

if [ ! -d ../../runtime ]; then
  echo "In wrong directory?  Run from silver/runtime/imp as ./build.sh"
  exit 1
fi

if [ ! -d ../../jars ]; then
  mkdir ../../jars
fi

CURR_DIR=`pwd`

cd main
$BUILD_COMM clean package
cp target/edu.umn.cs.melt.ide.copper-*.jar ../../../jars/IDEPluginRuntime.jar

