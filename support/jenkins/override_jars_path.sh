#!/usr/bin/env bash

set -eu

if [ ! -d jars ]; then
  echo "Usage: support/jenkins/override_jars_path.sh"
  exit 1
fi

TARGET=$(readlink -f jars)

ITER=""
FAIL=0
for dir in ${TARGET//\// }; do
  ITER=$ITER/$dir
  # I guess the best approach is look at ls's output, 10th character
  # for the world-x bit
  LSLINE=$(ls -ld $ITER)
  if [ ${LSLINE:9:1} != "x" ]; then
    echo "chmod a+x $ITER"
    FAIL=1
  fi
done

# Also check local directory's world-readability
LSLINE=$(ls -ld $TARGET)
if [ ${LSLINE:7:1} != "r" ]; then
  echo "chmod a+r $TARGET"
  FAIL=1
fi

# Also check for readability of those files
FILEFAIL=0
for file in $TARGET/*; do
  LSLINE=$(ls -l $file)
  if [ ${LSLINE:7:1} != "r" ]; then
    FAIL=1
    FILEFAIL=1
  fi
done

if [ $FILEFAIL = "1" ]; then
  echo "chmod a+r jars/*.jar"
fi

# Conclude
if [ $FAIL = "1" ]; then
  echo "Permissions problems! Jenkins won't be able to read your jars."
  echo "The above commands will fix minimally."
  exit 1
fi

echo "Looks good, use this path:"
echo $TARGET

