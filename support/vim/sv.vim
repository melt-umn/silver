" Vim syntax file
" Language: Silver

" exit if syntax already loaded
if exists("b:current_syntax")
	finish
endif

" keywords and regexes copied from gedit and google-code-prettyify

syn keyword externals grammar import imports exports build hiding as only option

syn keyword declarations parser parse attribute annotation function local closed nonterminal type occurs on production terminal marking foreign layout disambiguate action global productions lexer class default

syn keyword primitiveTypes IO Boolean Decorated Float Function Integer Production String

syn keyword storageClass abstract aspect concrete inherited synthesized autocopy ignore

syn keyword scopeDeclarations left association right precedence operator dominates submits classes

syn keyword flowOther forwarding forwards to return pluck print

syn keyword flow case of let in end decorate with prefix else forward if new then toString toInt toFloat length

syn keyword ide temp_imp_ide_dcl temp_imp_ide_font font color italic bold

syn keyword kw builder postbuilder exporter folder property string required string wizard new file stub generator product name version option resource

syn keyword boolean false true

syn match comment "\(--.*\)\|\({-\([^-]\|-\+[^-}]\)*-}\)$"
syn match regex "\/\(\(\\\/\)\|[^/]\)*\/"
syn match string "\"\(\(\\\"\)\|[^"]\)*\"" 		contains=escapedChar
syn match character "\'\\\?.\'"				contains=escapedChar
syn match escapedChar "\\[\\\"\'nrbtf]\|\d{1,3}\|u\d{1,4}"
syn match numeric "\<\(0[xX]\)\?\d\+[lL]\?\>"
syn match float "\<\(\d\+[eE][-+]\?\d\+\)\|\(\d*\.\d\+\([eE][-+]\?\d\+\)\?[fFdD]\?\)\|\(\d\+[fFdD]\)\>"

hi def link externals		Include
hi def link declarations	Keyword
hi def link primitiveTypes	Type
hi def link storageClass	StorageClass
hi def link scopeDeclarations	Keyword
hi def link flowOther		Statement
hi def link flow		Statement
hi def link ide			Type
hi def link kw			Keyword
hi def link boolean		Boolean
hi def link comment		Comment
hi def link regex		String
hi def link string		String
hi def link character		Character
hi def link escapedChar		SpecialChar
hi def link numeric		Number
hi def link float		Float

