grammar silver:definition:flow:env;

imports silver:definition:flow:ast;
imports silver:definition:env;
imports silver:definition:core;

exports silver:definition:flow:env_parser with silver:definition:env:env_parser;

autocopy attribute flowEnv :: Decorated FlowEnv;
synthesized attribute flowDefs :: [FlowDef];

nonterminal FlowEnv with synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree, localInhTree;

inherited attribute synTree :: EnvTree<FlowDef>;
inherited attribute inhTree :: EnvTree<FlowDef>;
inherited attribute defTree :: EnvTree<FlowDef>;
inherited attribute fwdTree :: EnvTree<FlowDef>;
inherited attribute fwdInhTree :: EnvTree<FlowDef>;
inherited attribute prodTree :: EnvTree<FlowDef>;
inherited attribute refTree :: EnvTree<FlowDef>;
inherited attribute localInhTree ::EnvTree<FlowDef>;

abstract production dummyFlowEnv
top::FlowEnv ::=
{
}

function fromFlowDefs
Decorated FlowEnv ::= d::FlowDefs
{
  production attribute e::FlowEnv;
  e = dummyFlowEnv();
  e.synTree = directBuildTree(d.synTreeContribs);
  e.inhTree = directBuildTree(d.inhTreeContribs);
  e.defTree = directBuildTree(d.defTreeContribs);
  e.fwdTree = directBuildTree(d.fwdTreeContribs);
  e.fwdInhTree = directBuildTree(d.fwdInhTreeContribs);
  e.prodTree = directBuildTree(d.prodTreeContribs);
  e.refTree = directBuildTree(d.refTreeContribs);
  e.localInhTree = directBuildTree(d.localInhTreeContribs);
  
  return e;
}


function lookupSyn
[FlowDef] ::= prod::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.synTree);
}

function lookupInh
[FlowDef] ::= prod::String  sigName::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, crossnames(sigName, attr)), e.inhTree);
}

function lookupDef
[FlowDef] ::= nt::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(nt, attr), e.defTree);
}

function lookupFwd
[FlowDef] ::= prod::String  e::Decorated FlowEnv
{
  return searchEnvTree(prod, e.fwdTree);
}

function lookupFwdInh
[FlowDef] ::= prod::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.fwdInhTree);
}

function lookupLocalInh
[FlowDef] ::= prod::String  fName::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, crossnames(fName, attr)), e.localInhTree);
}

function getProdsOn
[FlowDef] ::= nt::String  e::Decorated FlowEnv
{
  return searchEnvTree(nt, e.prodTree);
}

function getInhsForNtRef
[FlowDef] ::= nt::String  e::Decorated FlowEnv
{
  return searchEnvTree(nt, e.refTree);
}
