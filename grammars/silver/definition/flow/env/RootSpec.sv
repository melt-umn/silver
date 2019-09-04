grammar silver:definition:flow:env;

import silver:driver:util;

attribute flowDefs occurs on RootSpec, GrammarProperties;

aspect production consGrammarProperties
top::GrammarProperties ::= h::GrammarProperty t::GrammarProperties
{
  top.flowDefs = fromMaybe(t.flowDefs, h.maybeFlowDefs);
}

aspect production nilGrammarProperties
top::GrammarProperties ::=
{
  top.flowDefs = error("Grammar property flowDefs missing from interface file");
}

synthesized attribute maybeFlowDefs::Maybe<[FlowDef]> occurs on GrammarProperty;

aspect default production
top::GrammarProperty ::=
{
  top.maybeFlowDefs = nothing();
}

abstract production flowDefsGrammarProperty
top::GrammarProperty ::= val::[FlowDef]
{
  top.maybeFlowDefs = just(val);
}

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  grammarProperties <- [flowDefsGrammarProperty(r.flowDefs)];
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.flowDefs = [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _ _
{
  top.flowDefs = g.flowDefs;
}

aspect production interfaceRootSpec
top::RootSpec ::= p::GrammarProperties  interfaceTime::Integer _
{
  top.flowDefs = p.flowDefs;
}

aspect production nilGrammar
top::Grammar ::=
{
  top.flowDefs = [];
}

aspect production consGrammar
top::Grammar ::= h::Root  t::Grammar
{
  top.flowDefs = h.flowDefs ++ t.flowDefs;
}

