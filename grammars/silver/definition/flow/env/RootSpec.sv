grammar silver:definition:flow:env;

import silver:driver:util;

monoid attribute maybeFlowDefs::Maybe<[FlowDef]> with nothing(), orElse;
attribute maybeFlowDefs occurs on InterfaceItems, InterfaceItem;
propagate maybeFlowDefs on InterfaceItems;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.maybeFlowDefs.isJust then ["Missing item flowDefs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  propagate maybeFlowDefs;
}

abstract production flowDefsInterfaceItem
top::InterfaceItem ::= val::[FlowDef]
{
  top.maybeFlowDefs := just(val);
}

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  interfaceItems <- [flowDefsInterfaceItem(r.flowDefs)];
}

attribute flowDefs occurs on RootSpec;

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.flowDefs := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _ _
{
  top.flowDefs := g.flowDefs;
}

aspect production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  interfaceTime::Integer _
{
  top.flowDefs := i.maybeFlowDefs.fromJust;
}

