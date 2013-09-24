#!/bin/bash

BUILD_COMM="/home/patrick/maven3/bin/mvn"
CURR_DIR=`pwd`

cd edu.umn.cs.melt.copper
$BUILD_COMM clean install
cd $CURR_DIR

cd edu.umn.cs.melt.silver  
$BUILD_COMM clean install
cd $CURR_DIR

cd main
$BUILD_COMM clean package
cp target/edu.umn.cs.melt.ide.copper-*.jar $CURR_DIR

cd $CURR_DIR
