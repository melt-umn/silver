#!/bin/bash

# stop on error
set -eu

mkdir -p ~/.vim/syntax/ ~/.vim/ftdetect
cp -f sv.vim ~/.vim/syntax/
echo "au BufRead,BufNewFile *.sv set filetype=sv" > ~/.vim/ftdetect/sv.vim

