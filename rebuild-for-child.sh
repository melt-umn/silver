#!/bin/sh
echo    === RESTORE OLD RUNTIME ===
cp ../SilverRuntime.clean-orig.jar jars/SilverRuntime.jar
echo    === DEEP CLEAN ===
./deep-clean -delete all
echo    === COMPILE WITH OLD SILVER ===
./old-compile --clean --one-jar
echo    === BUILD CHILDRUNTIME ===
support/bin/silver silver:modification:origintracking:childruntime
echo    === BUILD JAVA RUNTIME ===
cd runtime/java
ant
echo    === INSTALL NEW RUNTIME ===
cd ../..
cp runtime/java/*.jar jars
echo    === DONE\? ===