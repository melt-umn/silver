grammar silver:compiler:extension:doc:core;

imports silver:compiler:definition:concrete_syntax;
imports silver:compiler:modification:ffi;
imports silver:compiler:extension:autoattr;
imports silver:compiler:modification:defaultattr;
imports silver:compiler:modification:copper;
imports silver:compiler:modification:copper_mda;
imports silver:compiler:definition:flow:syntax;
imports silver:compiler:modification:collection;

@@{- @warning INTENDED TO BE INTERFERED WITH like .pp. -}
synthesized attribute docUnparse::String occurs on AGDcl;
@@{- @warning INTENDED TO BE INTERFERED WITH like .pp. -}
synthesized attribute docForName::String occurs on AGDcl;

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
{
  top.docForName = id.name;
  top.docUnparse = "`function " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
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
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production concreteProductionDcl
top::AGDcl ::= 'concrete' 'production' id::Name ns::ProductionSignature pm::ProductionModifiers body::ProductionBody
{
  top.docForName = id.name;
  top.docUnparse = "`concrete production " ++ id.name ++ "` &nbsp; (`" ++ ns.unparse ++ "`)";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production attributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`synthesized attribute ${a.name}${tl.unparse} :: ${te.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production attributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`inherited attribute ${a.name}${tl.unparse} :: ${te.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production attributeDclTrans
top::AGDcl ::= 'translation' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`translation attribute ${a.name}${tl.unparse} :: ${te.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.docForName = id.name;
  top.docUnparse = s"`${quals.docUnparse}nonterminal ${id.name}${tl.unparse}`";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
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
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production lexerClassDecl
top::AGDcl ::= 'lexer' 'class' id::Name modifiers::LexerClassModifiers ';'
{
  top.docForName = id.name;
  top.docUnparse = s"`lexer class ${id.unparse} ${modifiers.unparse}`";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production parserDcl
top::AGDcl ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}'
{
  top.docForName = n.name;
  top.docUnparse = s"`parser ${n.unparse} :: ${t.unparse}`";
  top.docDcls := [(n.name, docDclInfo(n.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production annotationDcl
top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs '::' te::TypeExpr ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`annotation ${a.unparse}${tl.unparse} :: ${te.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production equalityAttributeDcl
top::AGDcl ::= 'equality' 'attribute' syn::Name 'with' inh::QName ';'
{
  top.docForName = syn.name;
  top.docUnparse = s"`equality attribute ${syn.name} with ${inh.name}`";
  top.docDcls := [(syn.name, docDclInfo(syn.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production orderingAttributeDcl
top::AGDcl ::= 'ordering' 'attribute' keySyn::Name ',' syn::Name 'with' inh::QName ';'
{
  top.docForName = keySyn.name++" and "++syn.name++" (ordering pair)";
  top.docUnparse = s"`ordering attribute ${keySyn.name}, ${syn.name} with ${inh.name}`";
  top.docDcls := [(keySyn.name, docDclInfo(keySyn.name, sourceLocation=top.location, sourceGrammar=top.grammarName)),
                  (syn.name, docDclInfo(syn.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production monoidAttributeDcl
top::AGDcl ::= 'monoid' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' e::Expr ',' _ ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`monoid attribute ${a.unparse}${tl.unparse} :: ${te.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production threadedAttributeDcl
top::AGDcl ::= 'threaded' 'attribute' inh::Name ',' syn::Name tl::BracketedOptTypeExprs '::' te::TypeExpr d::OptDirectionMod ';'
{
  top.docForName = inh.name++" and "++syn.name++" (threaded pair)";
  top.docUnparse = s"`threaded attribute ${inh.name}, ${syn.name}${tl.unparse} :: ${te.unparse} direction=${if d.reversed then "right to left" else "left to right"}`";
  top.docDcls := [(inh.name, docDclInfo(inh.name, sourceLocation=top.location, sourceGrammar=top.grammarName)),
                  (syn.name, docDclInfo(syn.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production functorAttributeDcl
top::AGDcl ::= 'functor' 'attribute' a::Name ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`monoid attribute ${a.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

-- Bizzare mismatching signature errors when uncommented:
-- aspect production unificationAttributeDcl
-- top::AGDcl ::= _ _ inh::Name i::TypeExpr _ synPartial::Name _ syn::Name _ _ qs::QNames _
-- {
--   top.docForName = inh.name++" and "++synPartial.name++" and "++syn.name++" (unification set)";
--   top.docUnparse = s"`unification attribute ${inh.name}, ${syn.name}, ${syn.name}`";
--   top.docDcls := [(inh.name, docDclInfo(inh.name, sourceLocation=top.location, sourceGrammar=top.grammarName)),
--                   (syn.name, docDclInfo(syn.name, sourceLocation=top.location, sourceGrammar=top.grammarName)),
--                   (synPartial.name, docDclInfo(synPartial.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
--   top.docs := [mkUndocumentedItem(top.docForName, top)];
-- }

aspect production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' ns::AspectDefaultProductionSignature body::ProductionBody 
{
  top.docForName = "aspect default production "++ns.namedSignature.outputElement.typerep.typeName;
  top.docUnparse = s"`aspect default production ${ns.unparse}`";
  top.docDcls := [];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production ffiTypeDclUgly
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs 'foreign' '=' trans::String_t ';'
{
  top.docForName = id.name;
  top.docUnparse = s"`ffi type ${id.unparse}`";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}


aspect production defaultAttributionDcl
top::AGDcl ::= at::Decorated! QName attl::Decorated! BracketedOptTypeExprs with {} nt::Decorated! QName with {} nttl::Decorated! BracketedOptTypeExprs with {}
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production emptyAGDcl
top::AGDcl ::=
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production flowtypeDcl
top::AGDcl ::= 'flowtype' nt::QName '=' specs::FlowSpecs ';'
{
  -- TODO: Enable documenting these?
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production flowtypeAttrDcl
top::AGDcl ::= 'flowtype' attr::FlowSpec 'on' nts::NtList ';'
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production disambiguationGroupDcl
top::AGDcl ::= 'disambiguate' terms::TermList acode::ActionCode_c
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::TypeExpr 'action' acode::ActionCode_c ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`parser attribute ${a.unparse}`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production attributeAspectParser
top::AGDcl ::= 'aspect' 'parser' 'attribute' a::QName 'action' acode::ActionCode_c ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`aspect parser attribute ${a.unparse}`";
  top.docDcls := [];
  top.docs := []; -- Not considered to need docs
}

aspect production errorAGDcl
top::AGDcl ::= e::[Message]
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production defsAGDcl
top::AGDcl ::= d::[Def]
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production appendAGDcl
top::AGDcl ::= h::AGDcl t::AGDcl
{
  -- Should be overridden if relevant on what forwards to this
  top.docForName = h.docForName;
  top.docUnparse = h.docUnparse;
}

aspect production jarNameDcl
top::AGDcl ::= n::Name
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production errorAttributionDcl
top::AGDcl ::= msg::[Message] at::Decorated! QName attl::BracketedOptTypeExprs nt::QName nttl::BracketedOptTypeExprs
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

aspect production globalValueDclConcrete
top::AGDcl ::= 'global' id::Name '::' cl::ConstraintList '=>' t::TypeExpr '=' e::Expr ';'
{
  top.docForName = id.name;
  top.docUnparse = s"`global ${id.unparse}`";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production typeAliasDecl
top::AGDcl ::= 'type' id::Name tl::BracketedOptTypeExprs '=' te::TypeExpr ';'
{
  top.docForName = id.name;
  top.docUnparse = s"`type ${id.unparse}`";
  top.docDcls := [(id.name, docDclInfo(id.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production collectionAttributeDclSyn
top::AGDcl ::= 'synthesized' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`synthesized attribute ${a.unparse} (collection)`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production collectionAttributeDclInh
top::AGDcl ::= 'inherited' 'attribute' a::Name tl::BracketedOptTypeExprs '::' te::TypeExpr 'with' q::NameOrBOperator ';'
{
  top.docForName = a.name;
  top.docUnparse = s"`synthesized attribute ${a.unparse} (collection)`";
  top.docDcls := [(a.name, docDclInfo(a.name, sourceLocation=top.location, sourceGrammar=top.grammarName))];
  top.docs := [mkUndocumentedItem(top.docForName, top)];
}

aspect production copperMdaDcl
top::AGDcl ::= 'copper_mda' testname::Name '(' orig::QName ')' '{' m::ParserComponents '}'
{
  top.docForName = "";
  top.docUnparse = "";
  top.docs := [];
}

attribute docUnparse occurs on NTDeclQualifiers;
aspect docUnparse on NTDeclQualifiers of
| closedNTQualifier(_, rest) -> "closed " ++ rest.docUnparse
| dataNTQualifier(_, rest) -> "data " ++ rest.docUnparse
| trackedNTQualifier(_, rest) -> rest.docUnparse
| nilNTQualifier() -> ""
end;