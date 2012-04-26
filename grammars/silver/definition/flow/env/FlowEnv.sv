grammar silver:definition:flow:env;

imports silver:definition:flow:ast;
imports silver:definition:env;
imports silver:definition:core;

autocopy attribute flowEnv :: Decorated FlowEnv;
synthesized attribute flowDefs :: [FlowDef];

nonterminal FlowEnv with synTree, fwdTree;

inherited attribute synTree :: EnvTree<FlowDef>;
inherited attribute fwdTree :: EnvTree<FlowDef>;

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
  e.fwdTree = directBuildTree(d.fwdTreeContribs);
  
  return e;
}


function lookupSyn
[FlowDef] ::= prod::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.synTree);
}

function lookupFwd
[FlowDef] ::= prod::String  e::Decorated FlowEnv
{
  return searchEnvTree(prod, e.fwdTree);
}

