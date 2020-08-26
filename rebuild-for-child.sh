#!/bin/sh
set -euo pipefail
export SVJVM_FLAGS="-Xmx16G -Xss128M"

function cleanup () {
	git apply -R compat-build.patch
}

trap cleanup EXIT

echo    === RESTORE OLD RUNTIME ===
cp JARS-BAK/SilverRuntime.jar jars/SilverRuntime.jar
echo    === INSTALL IMPL_HACK ===
git apply compat-build.patch
echo    === DEEP CLEAN ===
./deep-clean -delete all
echo    === COMPILE WITH OLD SILVER ===
./old-compile --clean --one-jar
echo    === BUILD CHILDRUNTIME ===
rm -rf generated/*/core generated/*/lib/xml ||true
support/bin/silver-custom build/silver.composed.Default.jar core ||true
support/bin/silver-custom build/silver.composed.Default.jar lib:xml:ast ||true
echo    === BUILD JAVA RUNTIME ===
cd runtime/java
ant
echo    === INSTALL NEW RUNTIME ===
cd ../..
cp runtime/java/*.jar jars
echo    === INSTALL IMPL_REAL ===
cleanup
echo    === DONE\? ===
