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
  -- This always changes between builds due to anon vertices named using genInt().
  -- Even if we could assign deterministic anon vertex names, changes to the flow
  -- defs don't affect dependent grammars unless the flow analysis is run.
  -- So we just ignore changes in flow defs here, and always rebuild dependent
  -- grammars when running the flow analysis.
  top.isEqual = true;

  top.flowDefs <- val;
  top.hasFlowDefs <- true;
}

abstract production refDefsInterfaceItem
top::InterfaceItem ::= val::[(String, [String])]
{
  propagate isEqual;
  top.refDefs <- val;
  top.hasRefDefs <- true;
}

abstract production specDefsInterfaceItem
top::InterfaceItem ::= val::[(String, String, [String], [String])]
{
  propagate isEqual;
  top.specDefs <- val;
  top.hasSpecDefs <- true;
}

aspect function packInterfaceItems
InterfaceItems ::= r::Decorated RootSpec
{
  interfaceItems <- [flowDefsInterfaceItem(r.flowDefs)];
  interfaceItems <- [refDefsInterfaceItem(r.refDefs)];
  interfaceItems <- [specDefsInterfaceItem(r.specDefs)];
}

attribute flowDefs, refDefs, specDefs occurs on RootSpec;
propagate flowDefs, refDefs, specDefs on RootSpec;
