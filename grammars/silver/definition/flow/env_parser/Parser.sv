grammar silver:definition:flow:env_parser;

import silver:definition:env;
import silver:definition:env:env_parser;

import silver:definition:core only location, env;
import silver:definition:flow:env only flowDefs;
import silver:definition:flow:ast;

terminal FlowTerm 'flow' lexer classes {C_1};
terminal DefTerm 'def' lexer classes {C_1};

attribute flowDefs occurs on IRootSpecParts, IRootSpecPart;

--------------- i don't know yet ------------------------
aspect production parserRootSpec
top::RootSpec ::= p::IRootSpecParts
{
  top.flowDefs = p.flowDefs;
}
---------------------------------------------------------

aspect production aRoot1
top::IRootSpecParts ::= r::IRootSpecPart
{
  top.flowDefs = r.flowDefs;
}

aspect production aRoot2
top::IRootSpecParts ::= r1::IRootSpecPart r2::IRootSpecParts
{
  top.flowDefs = r1.flowDefs ++ r2.flowDefs;
}

----

aspect default production
top::IRootSpecPart ::=
{
  top.flowDefs = [];
}

concrete production aRootSyntax
top::IRootSpecPart ::= 'flow' s::IFlows
{
  top.flowDefs = s.flowDefs;
}

nonterminal IFlows with flowDefs;
nonterminal IFlowsInner with flowDefs;
nonterminal IFlow with flowDefs;

concrete production aFlowsNone
top::IFlows ::= '[' ']'
{
  top.flowDefs = [];
}
{- FOR THE MOMENT, I'm just going to deliberately break this
concrete production aFlowsSome
top::IFlows ::= '[' l::IFlowsInner ']'
{
  top.flowDefs = l.flowDefs;
}

concrete production aFlowsOne
top::IFlowsInner ::= l::IFlow
{
  top.flowDefs = l.flowDefs;
}
concrete production aFlowsCons
top::IFlowsInner ::= l::IFlowsInner ',' r::IFlow
{
  top.flowDefs = l.flowDefs ++ r.flowDefs;
}

concrete production aFlowSyn
top::IFlow ::= 'syn' '(' prod::IName ',' attr::IName ')'
{
  top.flowDefs = [synEq(prod.aname, attr.aname)];
}

concrete production aFlowDef
top::IFlow ::= 'def' '(' nt::IName ',' attr::IName ')'
{
  top.flowDefs = [defEq(nt.aname, attr.aname)];
}

concrete production aFlowFwd
top::IFlow ::= 'fwd' '(' prod::IName ')'
{
  top.flowDefs = [fwdEq(prod.aname)];
}

concrete production aFlowProd
top::IFlow ::= 'prod' '(' nt::IName ',' prod::IName ')'
{
  top.flowDefs = [prodFlowDef(nt.aname, prod.aname)];
}
-}
