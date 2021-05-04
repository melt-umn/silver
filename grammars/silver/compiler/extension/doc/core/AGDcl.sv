grammar silver:compiler:extension:doc:core;

imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:ffi;
imports silver:compiler:extension:autoattr;
imports silver:compiler:modification:defaultattr;

synthesized attribute docUnparse::String occurs on AGDcl;
synthesized attribute docForName::String occurs on AGDcl;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
	top.docForName = id.name;
	top.docUnparse = "`function " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production aspectFunctionDcl
top::AGDcl ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
{
	top.docForName = id.name;
	top.docUnparse = "`aspect function " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
	top.docDcls := [];
	top.docs := []; -- Not considered to need docs
}

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
	top.docForName = id.name;
	top.docUnparse = "`abstract production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
	top.docForName = id.name;
	top.docUnparse = "`concrete production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
	top.docForName = a.name;
	top.docUnparse = s"`synthesized attribute ${a.name}${tl.unparse} :: ${te.unparse}`";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
	top.docForName = a.name;
	top.docUnparse = s"`inherited attribute ${a.name}${tl.unparse} :: ${te.unparse}`";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
	top.docForName = id.name;
	top.docUnparse = s"`nonterminal ${id.name}`";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production aspectProductionDcl
top::AGDcl ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
{
	top.docForName = "";
	top.docUnparse = "`aspect production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
	top.docDcls := [];
	top.docs := []; -- Not considered to need docs
}

aspect production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
	top.docForName = id.name;
	top.docUnparse = s"`terminal ${id.unparse}`";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production lexerClassDecl
top::AGDcl ::= 'lexer' 'class' id::Name modifiers::LexerClassModifiers ';'
{
	top.docForName = id.name;
	top.docUnparse = s"`lexer class ${id.unparse} ${modifiers.unparse}`";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}'
{
	top.docForName = n.name;
	top.docUnparse = s"`parser ${n.unparse} :: ${t.unparse}`";
	top.docDcls := [pair(n.name, docDclInfo(n.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
	top.docForName = a.name;
	top.docUnparse = s"`annotation ${a.unparse}${tl.unparse} :: ${te.unparse}`";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production equalityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' inh::Name ',' syn::Name ';'
{
	top.docForName = inh.name++" and "++syn.name++" (equality pair)";
	top.docUnparse = s"`equality attribute ${inh.name}, ${syn.name}`";
	top.docDcls := [pair(inh.name, docDclInfo(inh.name, top.location, top.grammarName)),
	                pair(syn.name, docDclInfo(syn.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production monoidAttributeDcl
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' e::Expr ',' _ ';'
{
	top.docForName = a.name;
	top.docUnparse = s"`monoid attribute ${a.unparse}${tl.unparse} :: ${te.unparse}`";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production threadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
	top.docForName = inh.name++" and "++syn.name++" (threaded pair)";
	top.docUnparse = s"`threaded attribute ${inh.name}, ${syn.name}${tl.unparse} :: ${te.unparse}`";
	top.docDcls := [pair(inh.name, docDclInfo(inh.name, top.location, top.grammarName)),
	                pair(syn.name, docDclInfo(syn.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production functorAttributeDcl
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
	top.docForName = a.name;
	top.docUnparse = s"`monoid attribute ${a.unparse}`";
	top.docDcls := [pair(a.name, docDclInfo(a.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

-- Bizzare mismatching signature errors when uncommented:
-- aspect production unificationAttributeDcl
-- top::AGDcl ::= _ _ inh::Name i::TypeExpr _ synPartial::Name _ syn::Name _ _ qs::QNames _
-- {
-- 	top.docForName = inh.name++" and "++synPartial.name++" and "++syn.name++" (unification set)";
-- 	top.docUnparse = s"`unification attribute ${inh.name}, ${syn.name}, ${syn.name}`";
-- 	top.docDcls := [pair(inh.name, docDclInfo(inh.name, top.location, top.grammarName)),
-- 	                pair(syn.name, docDclInfo(syn.name, top.location, top.grammarName)),
-- 	                pair(synPartial.name, docDclInfo(synPartial.name, top.location, top.grammarName))];
-- 	top.docs := [mkUndocumentedItem(top.docForName, top)];
-- }

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' lhs::Name '::' _ '::=' body::ProductionBody 
{
	top.docForName = "aspect default production "++lhs.name;
	top.docUnparse = s"`aspect default production ${lhs.unparse}`";
	top.docDcls := [];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production ffiTypeDclUgly
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs 'foreign' '=' trans::String_t ';'
{
	top.docForName = id.name;
	top.docUnparse = s"`ffi type ${id.unparse}`";
	top.docDcls := [pair(id.name, docDclInfo(id.name, top.location, top.grammarName))];
	top.docs := [mkUndocumentedItem(top.docForName, top)];
}


aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
	top.docForName = "";
	top.docs := [];
}

aspect production emptyAGDcl
top::AGDcl ::=
{
	top.docForName = "";
	top.docs := [];
}
