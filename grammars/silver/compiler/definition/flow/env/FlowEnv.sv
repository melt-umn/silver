grammar silver:compiler:definition:flow:env;

imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:env;
imports silver:compiler:definition:core;

import silver:compiler:definition:type;


autocopy attribute flowEnv :: Decorated FlowEnv;
monoid attribute flowDefs :: [FlowDef];
-- These are factored out of FlowDefs to avoid a circular dependency,
-- since they are needed during type checking
monoid attribute specDefs :: [(String, String, [String])];
monoid attribute refDefs :: [(String, [String])];

nonterminal FlowEnv with synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree, localInhTree, localTree, nonSuspectTree, hostSynTree, specTree, prodGraphTree;

inherited attribute synTree :: EnvTree<FlowDef>;
inherited attribute inhTree :: EnvTree<FlowDef>;
inherited attribute defTree :: EnvTree<FlowDef>;
inherited attribute fwdTree :: EnvTree<FlowDef>;
inherited attribute fwdInhTree :: EnvTree<FlowDef>;
inherited attribute prodTree :: EnvTree<FlowDef>;
inherited attribute refTree :: EnvTree<[String]>;
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
Decorated FlowEnv ::=
  specContribs::[(String, String, [String])] refContribs::[(String, [String])]
  d::FlowDefs
{
  production attribute e::FlowEnv;
  e = dummyFlowEnv();
  e.synTree = directBuildTree(d.synTreeContribs);
  e.inhTree = directBuildTree(d.inhTreeContribs);
  e.defTree = directBuildTree(d.defTreeContribs);
  e.fwdTree = directBuildTree(d.fwdTreeContribs);
  e.fwdInhTree = directBuildTree(d.fwdInhTreeContribs);
  e.prodTree = directBuildTree(d.prodTreeContribs);
  e.refTree = directBuildTree(refContribs);
  e.localInhTree = directBuildTree(d.localInhTreeContribs);
  e.localTree = directBuildTree(d.localTreeContribs);
  e.nonSuspectTree = directBuildTree(d.nonSuspectContribs);
  e.hostSynTree = directBuildTree(d.hostSynTreeContribs);
  e.specTree = directBuildTree(specContribs);
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

-- default set of inherited attributes required/assumed to exist for references
function getInhsForNtRef
[[String]] ::= nt::String  e::Decorated FlowEnv
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
    \p::FlowDef -> case p of prodFlowDef(_, p) -> p | _ -> error("Searches only prod defs") end;

  return map(extractProdName, searchEnvTree(nt, e.prodTree));
}

-- Ext Syns subject to ft lower bound
function getHostSynsFor
[String] ::= nt::String  e::Decorated FlowEnv
{
  local extractHostSynName :: (String ::= FlowDef) =
    \f::FlowDef -> case f of hostSynFlowDef(_, at) -> at | _ -> error("Searches only host defs") end;

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

monoid attribute occursContextInhDeps::[(String, String, [String])]  -- (type name, syn, inhs)
  occurs on Contexts, Context;
monoid attribute occursContextInhSetDeps::[(String, String, [TyVar])]  -- (type name, syn, InhSet tyvars)
  occurs on Contexts, Context;
propagate occursContextInhDeps, occursContextInhSetDeps on Contexts;

aspect default production
top::Context ::=
{
  top.occursContextInhDeps := [];
  top.occursContextInhSetDeps := [];
}
aspect production synOccursContext
top::Context ::= syn::String _ _ inhs::Type ntty::Type
{
  local maxInhSetMembers::(Maybe<[String]>, [TyVar]) = getMaxInhSetMembers([], inhs, top.env);
  top.occursContextInhDeps :=
    case maxInhSetMembers.fst of
    | just(inhAttrs) -> [(ntty.typeName, syn, inhAttrs)]
    | nothing() -> []
    end;
  top.occursContextInhSetDeps := [(ntty.typeName, syn, maxInhSetMembers.snd)];
}

-- Defs for the dependencies introduced by syn occurs-on contexts at a decoration site
function occursContextDeps
[FlowDef] ::= ns::NamedSignature env::Decorated Env t::Type vt::VertexType
{
  local contexts::Contexts = foldContexts(ns.contexts);
  contexts.env = env;
  return map(
    \ synDeps::(String, [String]) -> synOccursContextEq(ns.fullName, vt, synDeps.fst, synDeps.snd),
    lookupAll(t.typeName, contexts.occursContextInhDeps));
}
