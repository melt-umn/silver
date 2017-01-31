#!/bin/bash

# stop on error
set -eu

mkdir -p ~/.config/nvim/syntax
cp -f sv.vim ~/.config/nvim/syntax/
cat filetype.vim >> ~/.config/nvim/filetype.vim

