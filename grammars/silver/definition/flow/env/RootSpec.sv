grammar silver:definition:flow:env;

import silver:driver:util;

attribute flowDefs occurs on RootSpec;

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  
  unparses <- ["flow [" ++ implode(",\n ", foldr(consFlow, nilFlow(), r.flowDefs).unparses) ++ "]"];
  
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _
{
  top.flowDefs = g.flowDefs;
}

aspect production grammarPart
top::GrammarPart ::= r::Root  fn::String
{
  top.flowDefs = r.flowDefs;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.flowDefs = [];
}

aspect production consGrammar
top::Grammar ::= h::GrammarPart  t::Grammar
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

