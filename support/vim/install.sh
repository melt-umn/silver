#!/usr/bin/env bash

# stop on error
set -eu

VIMHOME="${VIMHOME:-${HOME}/.vim}";

mkdir -p ${VIMHOME}/syntax/ ${VIMHOME}/ftdetect
cp -f ftdetect/sv.vim ${VIMHOME}/ftdetect/
cp -f syntax/sv.vim ${VIMHOME}/syntax/
