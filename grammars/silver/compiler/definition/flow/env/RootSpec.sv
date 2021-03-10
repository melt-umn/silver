grammar silver:compiler:definition:flow:env;

import silver:compiler:driver:util;

monoid attribute maybeFlowDefs::Maybe<[FlowDef]> with nothing(), orElse;
monoid attribute maybeRefDefs::Maybe<[(String, [String])]> with nothing(), orElse;
monoid attribute maybeSpecDefs::Maybe<[(String, String, [String])]> with nothing(), orElse;
attribute maybeFlowDefs, maybeRefDefs, maybeSpecDefs occurs on InterfaceItems, InterfaceItem;
propagate maybeFlowDefs, maybeRefDefs, maybeSpecDefs on InterfaceItems;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.maybeFlowDefs.isJust then ["Missing item flowDefs"] else [];
  top.interfaceErrors <- if !top.maybeRefDefs.isJust then ["Missing item refDefs"] else [];
  top.interfaceErrors <- if !top.maybeSpecDefs.isJust then ["Missing item specDefs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  propagate maybeFlowDefs, maybeRefDefs, maybeSpecDefs;
}

abstract production flowDefsInterfaceItem
top::InterfaceItem ::= val::[FlowDef]
{
  top.maybeFlowDefs := just(val);
}

abstract production refDefsInterfaceItem
top::InterfaceItem ::= val::[(String, [String])]
{
  top.maybeRefDefs := just(val);
}

abstract production specDefsInterfaceItem
top::InterfaceItem ::= val::[(String, String, [String])]
{
  top.maybeSpecDefs := just(val);
}

aspect function unparseRootSpec
String ::= r::Decorated RootSpec
{
  interfaceItems <- [flowDefsInterfaceItem(r.flowDefs)];
  interfaceItems <- [refDefsInterfaceItem(r.refDefs)];
  interfaceItems <- [specDefsInterfaceItem(r.specDefs)];
}

attribute flowDefs, refDefs, specDefs occurs on RootSpec;

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.flowDefs := [];
  top.refDefs := [];
  top.specDefs := [];
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  _ _ _ _
{
  top.flowDefs := g.flowDefs;
  top.refDefs := g.refDefs;
  top.specDefs := g.specDefs;
}

aspect production interfaceRootSpec
top::RootSpec ::= i::InterfaceItems  interfaceTime::Integer _
{
  top.flowDefs := i.maybeFlowDefs.fromJust;
  top.refDefs := i.maybeRefDefs.fromJust;
  top.specDefs := i.maybeSpecDefs.fromJust;
}

