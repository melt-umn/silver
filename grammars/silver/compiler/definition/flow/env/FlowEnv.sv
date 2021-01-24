grammar silver:compiler:definition:flow:env;

imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:env;
imports silver:compiler:definition:core;


autocopy attribute flowEnv :: Decorated FlowEnv;
monoid attribute flowDefs :: [FlowDef];

nonterminal FlowEnv with synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree, localInhTree, localTree, nonSuspectTree, hostSynTree, specTree, prodGraphTree;

inherited attribute synTree :: EnvTree<FlowDef>;
inherited attribute inhTree :: EnvTree<FlowDef>;
inherited attribute defTree :: EnvTree<FlowDef>;
inherited attribute fwdTree :: EnvTree<FlowDef>;
inherited attribute fwdInhTree :: EnvTree<FlowDef>;
inherited attribute prodTree :: EnvTree<FlowDef>;
inherited attribute refTree :: EnvTree<FlowDef>;
inherited attribute localInhTree ::EnvTree<FlowDef>;
inherited attribute localTree :: EnvTree<FlowDef>;
inherited attribute nonSuspectTree :: EnvTree<[String]>;
inherited attribute hostSynTree :: EnvTree<FlowDef>;
inherited attribute specTree :: EnvTree<Pair<String [String]>>;
inherited attribute prodGraphTree :: EnvTree<FlowDef>;

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
  e.localTree = directBuildTree(d.localTreeContribs);
  e.nonSuspectTree = directBuildTree(d.nonSuspectContribs);
  e.hostSynTree = directBuildTree(d.hostSynTreeContribs);
  e.specTree = directBuildTree(d.specContribs);
  e.prodGraphTree = directBuildTree(d.prodGraphContribs);
  
  return e;
}


-- synthesized equation in a production
function lookupSyn
[FlowDef] ::= prod::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.synTree);
}

-- inherited equation for a child in a production
function lookupInh
[FlowDef] ::= prod::String  sigName::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, crossnames(sigName, attr)), e.inhTree);
}

-- default equation for a nonterminal
function lookupDef
[FlowDef] ::= nt::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(nt, attr), e.defTree);
}

-- forward equation for a production
function lookupFwd
[FlowDef] ::= prod::String  e::Decorated FlowEnv
{
  return searchEnvTree(prod, e.fwdTree);
}

-- inherited equation for the forward in a production
function lookupFwdInh
[FlowDef] ::= prod::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.fwdInhTree);
}

-- inherited equation for a local in a production
function lookupLocalInh
[FlowDef] ::= prod::String  fName::String  attr::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, crossnames(fName, attr)), e.localInhTree);
}

function lookupLocalEq
[FlowDef] ::= prod::String  fName::String  e::Decorated FlowEnv
{
  return searchEnvTree(crossnames(prod, fName), e.localTree);
}

-- "blessed set" of inherited attribute required/assumed to exist for references
function getInhsForNtRef
[FlowDef] ::= nt::String  e::Decorated FlowEnv
{
  return searchEnvTree(nt, e.refTree);
}

-- implicit forward syn copy equations that are allowed to affect the flow type
function getNonSuspectAttrsForProd
[String] ::= prod::String  e::Decorated FlowEnv
{
  return concat(searchEnvTree(prod, e.nonSuspectTree));
}

-- all (non-forwarding) productions constructing a nonterminal
function getNonforwardingProds
[String] ::= nt::String  e::Decorated FlowEnv
{
  local extractProdName :: (String ::= FlowDef) =
    \p::FlowDef -> case p of prodFlowDef(_, p) -> p end;

  return map(extractProdName, searchEnvTree(nt, e.prodTree));
}

-- Ext Syns subject to ft lower bound
function getHostSynsFor
[String] ::= nt::String  e::Decorated FlowEnv
{
  local extractHostSynName :: (String ::= FlowDef) =
    \f::FlowDef -> case f of hostSynFlowDef(_, at) -> at end;

  return map(extractHostSynName, searchEnvTree(nt, e.hostSynTree));
}

-- Get syns (and "forward") that have flow types specified
function getSpecifiedSynsForNt
[String] ::= nt::String  e::Decorated FlowEnv
{
  return map(fst, searchEnvTree(nt, e.specTree));
}
function getFlowTypeSpecFor
[Pair<String [String]>] ::= nt::String  e::Decorated FlowEnv
{
  return searchEnvTree(nt, e.specTree);
}

function getGraphContribsFor
[FlowDef] ::= prod::String  e::Decorated FlowEnv
{
  return searchEnvTree(prod, e.prodGraphTree);
}

