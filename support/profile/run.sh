#!/bin/bash

if [ ! -d jars ]; then
  echo "Run from silver root as './support/profile/run.sh'"
  exit 1
fi

mkdir -p build
cd build

java -Xss8M -Xmx3000M -Xrunhprof:heap=sites,cpu=samples -jar ../jars/silver.composed.Default.jar --clean silver:composed:Default

# Heap profile:
#java -Xss8M -Xmx3000M -Xrunhprof:heap=sites,depth=2 -jar ../jars/silver.composed.Default.jar --clean silver:composed:Default

# Deep CPU stack profile:
#java -Xss8M -Xmx3000M -Xrunhprof:cpu=samples,depth=12 -jar ../jars/silver.composed.Default.jar --clean silver:composed:Default

# Kinda useless CPU profile
#java -Xss8M -Xmx3000M -Xrunhprof:cpu=samples,depth=1,interval=1 -jar ../jars/silver.composed.Default.jar --clean silver:composed:Default

