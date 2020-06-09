#!/bin/sh
set -e
export SVJVM_FLAGS="-Xmx16G -Xss128M"
echo    === RESTORE OLD RUNTIME ===
cp JARS-BAK/SilverRuntime.jar jars/SilverRuntime.jar
echo    === INSTALL IMPL_HACK ===
set +e
mv grammars/core/originsimpl/Impl_hack* grammars/core/originsimpl/Impl_hack.sv
mv grammars/core/originsimpl/Impl_real* grammars/core/originsimpl/Impl_real.sv.disabled
mv grammars/core/reflect/AST_untracked* grammars/core/reflect/AST_untracked.sv
mv grammars/core/reflect/AST_tracked* grammars/core/reflect/AST_tracked.sv.disabled
echo    === DEEP CLEAN ===
./deep-clean -delete all
echo    === COMPILE WITH OLD SILVER ===
set -e
./old-compile --clean --one-jar
set +e
echo    === BUILD CHILDRUNTIME ===
rm -rf generated/*/core generated/*/lib/xml
support/bin/silver-custom build/silver.composed.Default.jar core
support/bin/silver-custom build/silver.composed.Default.jar lib:xml:ast
set -e
echo    === BUILD JAVA RUNTIME ===
cd runtime/java
ant
echo    === INSTALL NEW RUNTIME ===
cd ../..
cp runtime/java/*.jar jars
echo    === INSTALL IMPL_REAL ===
mv grammars/core/originsimpl/Impl_real* grammars/core/originsimpl/Impl_real.sv
mv grammars/core/originsimpl/Impl_hack* grammars/core/originsimpl/Impl_hack.sv.disabled
mv grammars/core/reflect/AST_tracked* grammars/core/reflect/AST_tracked.sv
mv grammars/core/reflect/AST_untracked* grammars/core/reflect/AST_untracked.sv.disabled
echo    === DONE\? ===
