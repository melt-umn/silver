grammar silver:compiler:definition:flow:env;

import silver:compiler:driver:util;

monoid attribute hasFlowDefs::Boolean with false, ||;
monoid attribute hasRefDefs::Boolean with false, ||;
monoid attribute hasSpecDefs::Boolean with false, ||;

attribute flowDefs, refDefs, specDefs, hasFlowDefs, hasRefDefs, hasSpecDefs occurs on InterfaceItems, InterfaceItem;
propagate flowDefs, refDefs, specDefs, hasFlowDefs, hasRefDefs, hasSpecDefs on InterfaceItems, InterfaceItem;

aspect production consInterfaceItem
top::InterfaceItems ::= h::InterfaceItem t::InterfaceItems
{
  top.interfaceErrors <- if !top.hasFlowDefs then ["Missing item flowDefs"] else [];
  top.interfaceErrors <- if !top.hasRefDefs then ["Missing item refDefs"] else [];
  top.interfaceErrors <- if !top.hasSpecDefs then ["Missing item specDefs"] else [];
}

aspect default production
top::InterfaceItem ::=
{
  propagate flowDefs, refDefs, specDefs, hasFlowDefs, hasRefDefs, hasSpecDefs;
}

abstract production flowDefsInterfaceItem
top::InterfaceItem ::= val::[FlowDef]
{
  top.flowDefs <- val;
  top.hasFlowDefs <- true;
}

abstract production refDefsInterfaceItem
top::InterfaceItem ::= val::[(String, [String])]
{
  top.refDefs <- val;
  top.hasRefDefs <- true;
}

abstract production specDefsInterfaceItem
top::InterfaceItem ::= val::[(String, String, [String], [String])]
{
  top.specDefs <- val;
  top.hasSpecDefs <- true;
}

aspect function unparseRootSpec
ByteArray ::= r::Decorated RootSpec
{
  interfaceItems <- [flowDefsInterfaceItem(r.flowDefs)];
  interfaceItems <- [refDefsInterfaceItem(r.refDefs)];
  interfaceItems <- [specDefsInterfaceItem(r.specDefs)];
}

attribute flowDefs, refDefs, specDefs occurs on RootSpec;
propagate flowDefs, refDefs, specDefs on RootSpec;
