#!/bin/bash

# stop on error
set -eu

mkdir -p ~/.config/nvim/syntax/ ~/.config/nvim/ftdetect
cp -f sv.vim ~/.config/nvim/syntax/
echo "au BufRead,BufNewFile *.sv setfiletype sv" > ~/.config/nvim/ftdetect/sv.vim

