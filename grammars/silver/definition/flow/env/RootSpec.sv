grammar silver:definition:flow:env;

import silver:driver:util;

synthesized attribute maybeFlowDefs::Maybe<[FlowDef]> occurs on InterfaceItems, InterfaceItem;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.maybeFlowDefs = orElse(t.maybeFlowDefs, h.maybeFlowDefs);
  top.interfaceErrors <- if !top.maybeFlowDefs.isJust then ["Missing item flowDefs"] else [];
}

aspect production nilInterfaceItem
top::InterfaceItems ::=
{
  top.maybeFlowDefs = nothing();
}

aspect default production
top::InterfaceItem ::=
{
  top.maybeFlowDefs = nothing();
}

abstract production flowDefsInterfaceItem
top::InterfaceItem ::= val::[FlowDef]
{
  top.maybeFlowDefs = just(val);
}

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  interfaceItems <- [flowDefsInterfaceItem(r.flowDefs)];
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

attribute flowDefs occurs on RootSpec;

aspect production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  interfaceTime::Integer _
{
  top.flowDefs = i.maybeFlowDefs.fromJust;
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

