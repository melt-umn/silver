" Vim syntax file
" Language: Silver

" exit if syntax already loaded
if exists("b:current_syntax")
	finish
endif

" keywords and regexes copied from gedit and google-code-prettyify

syn keyword svlangExternals grammar import imports exports build hiding as only option

syn keyword svlangDeclarations parser parse attribute annotation function local closed nonterminal type occurs on production terminal marking foreign layout disambiguate action global productions lexer class default

syn keyword svlangPrimitiveTypes IO Boolean Decorated Float Function Integer Production String

syn keyword svlangStorageClass abstract aspect concrete inherited synthesized autocopy ignore

syn keyword svlangScopeDeclarations left association right precedence operator dominates submits classes

syn keyword svlangFlowOther forwarding forwards to return pluck print

syn keyword svlangFlow case of let in end decorate with prefix else forward if new then toString toInt toFloat length

syn keyword svlangIde temp_imp_ide_dcl temp_imp_ide_font font color italic bold

syn keyword svlangKw builder postbuilder exporter folder property string required string wizard new file stub generator product name version option resource

syn keyword svlangBoolean false true

syn keyword svlangTodo TODO FIXME XXX


syn region svlangBlock start=/\v\{/ end=/\v\}/		transparent fold
syn region svlangComment start=/\v\{-/ end=/\v-\}/	contains=svlangTodo,svlangComment fold
syn match svlangComment /\v--.*/ 			contains=svlangTodo
syn match svlangRegex /\v\/((\\\/)|[^/])*\//
syn match svlangString /\v"((\\")|[^"])*"/ 		contains=escapedChar
syn match svlangCharacter /\v\'\\?.\'/			contains=escapedChar
syn match svlangEscapedChar /\v\\[\\"\'nrbtf]|\d{1,3}|u\d{1,4}/
syn match svlangNumeric /\v<(0[xX])?\d+[lL]?>/
syn match svlangFloat /\v<(\d+[eE][-+]?\d+)|(\d*\.\d\+([eE][-+]?\d+)?[fFdD]?)|(\d\+[fFdD])>/

syntax sync fromstart

hi def link svlangExternals		Include
hi def link svlangDeclarations		Keyword
hi def link svlangPrimitiveTypes	Type
hi def link svlangStorageClass	 	StorageClass
hi def link svlangScopeDeclarations	Keyword
hi def link svlangFlowOther		Statement
hi def link svlangFlow		 	Statement
hi def link svlangIde			Type
hi def link svlangKw			Keyword
hi def link svlangBoolean		Boolean
hi def link svlangComment		Comment
hi def link svlangRegex		 	String
hi def link svlangString		String
hi def link svlangCharacter		Character
hi def link svlangEscapedChar		SpecialChar
hi def link svlangNumeric		Number
hi def link svlangFloat		 	Float
hi def link svlangTodo		 	Todo

set autoindent
set expandtab
set shiftwidth=2
set softtabstop=2

