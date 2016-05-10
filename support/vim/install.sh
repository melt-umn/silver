#!/bin/bash
mkdir -p ~/.vim/syntax/ ~/.vim/ftdetect \
	&& cp -f sv.vim ~/.vim/syntax/ \
	&& echo "au BufRead,BufNewFile *.sv setfiletype sv" > ~/.vim/ftdetect/sv.vim

