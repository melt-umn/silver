#!/bin/sh
export SVJVM_FLAGS="-Xmx16G -Xss128M"
echo    === RESTORE OLD RUNTIME ===
cp JARS-BAK/SilverRuntime.jar jars/SilverRuntime.jar
echo    === INSTALL IMPL_HACK ===
mv grammars/core/originsimpl/Impl_hack* grammars/core/originsimpl/Impl_hack.sv
mv grammars/core/originsimpl/Impl_real* grammars/core/originsimpl/Impl_real.sv.disabled
echo    === DEEP CLEAN ===
./deep-clean -delete all
echo    === COMPILE WITH OLD SILVER ===
./old-compile --clean --one-jar
echo    === BUILD CHILDRUNTIME ===
rm -rf generated/*/core generated/*/lib/xml
support/bin/silver-custom build/silver.composed.Default.jar core
support/bin/silver-custom build/silver.composed.Default.jar lib:xml:ast
echo    === BUILD JAVA RUNTIME ===
cd runtime/java
ant
echo    === INSTALL NEW RUNTIME ===
cd ../..
cp runtime/java/*.jar jars
echo    === INSTALL IMPL_REAL ===
mv grammars/core/originsimpl/Impl_real* grammars/core/originsimpl/Impl_real.sv
mv grammars/core/originsimpl/Impl_hack* grammars/core/originsimpl/Impl_hack.sv.disabled
echo    === DONE\? ===