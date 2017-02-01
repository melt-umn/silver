#!/bin/bash

# stop on error
set -eu

if [[ -z ${VIMHOME+dummytext} ]]; then
	VIMHOME="${HOME}/.vim";
fi;

mkdir -p ${VIMHOME}/syntax/ ${VIMHOME}/ftdetect
cp -f sv.vim ${VIMHOME}/syntax/
echo "au BufRead,BufNewFile *.sv set filetype=sv" > ${VIMHOME}/ftdetect/sv.vim

