#!/usr/bin/env bash

set -eu

if [ ! -x link-to-export-scratch.sh ] || [ ! -f whatisthis.txt ]; then
	echo "Wrong directory? Run as ./link-to-export-scratch.sh"
	exit 1
fi

if [ ! -d /export/scratch ]; then
	echo "Are you on a umn machine?"
	exit 2
fi

if [ -d bin ]; then
	rm -r bin
fi

if [ -d src ]; then
	rm -r src
fi

mkdir -p /export/scratch/$(whoami)-silver-generated/{src,bin}
ln -s /export/scratch/$(whoami)-silver-generated/bin
ln -s /export/scratch/$(whoami)-silver-generated/src
