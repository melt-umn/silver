#!/bin/bash

if [ ! -d jars ]; then
  echo "Run from silver root as './support/profile/run.sh'"
  exit 1
fi

mkdir -p build
cd build

java -Xss8M -Xmx3000M -Xrunhprof:heap=sites,cpu=samples -jar ../jars/RunSilver.jar --clean silver:composed:Default
