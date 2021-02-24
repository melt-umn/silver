grammar silver:compiler:extension:doc:core;

imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:copper;

synthesized attribute docUnparse::String occurs on AGDcl;


aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
	top.docUnparse = "`function " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.undocumentedNamed = [id.name];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
	top.docUnparse = "`aspect function " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [];
	top.undocumentedNamed = []; -- Not considered to need docs
	top.docs := []; -- Not considered to need docs
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
	-- top.docUnparse = "`abstract production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.undocumentedNamed = [id.name];
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
	top.docUnparse = "`concrete production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.undocumentedNamed = [id.name];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
	top.docUnparse = s"`synthesized attribute ${a.name}${tl.unparse} :: ${te.unparse}` {#${a.name}}";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.undocumentedNamed = [a.name];
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
	top.docUnparse = s"`inherited attribute ${a.name}${tl.unparse} :: ${te.unparse}` {#${a.name}}";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.undocumentedNamed = [a.name];
}

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
	top.docUnparse = s"`nonterminal ${id.name}` {#${id.name}}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.undocumentedNamed = [id.name];
}

aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
	top.docs := [];
	top.undocumentedNamed = [];
}

aspect production emptyAGDcl
top::AGDcl ::=
{
	top.docs := [];
	top.undocumentedNamed = [];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
	top.docUnparse = "`aspect production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`) {#" ++ id.name ++ "}";
	top.docDcls := [];
	top.undocumentedNamed = []; -- Not considered to need docs
	top.docs := []; -- Not considered to need docs
}

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
	top.docUnparse = s"`terminal ${id.unparse}` {#${id.unparse}}\nRegex: `${r.unparse}`. Modifiers: `${tm.unparse}`\n\n";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.undocumentedNamed = [id.name];
}

aspect production lexerClassDecl
top::AGDcl ::= 'lexer' 'class' id::Name modifiers::LexerClassModifiers ';'
{
	top.docUnparse = s"`lexer class ${id.unparse} ${modifiers.unparse}` {#${id.unparse}}";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.undocumentedNamed = [id.name];
}

aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}'
{
	top.docUnparse = s"`parser ${n.unparse} :: ${t.unparse}` {#${n.unparse}}";
	top.docDcls := [pair(n.name, docDclInfo(n.name, top.location, top.grammarName))];
	top.undocumentedNamed = [n.name];
}