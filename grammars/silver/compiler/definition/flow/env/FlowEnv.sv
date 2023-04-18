grammar silver:compiler:definition:flow:env;

imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:env;
imports silver:compiler:definition:core;
imports silver:compiler:analysis:uniqueness;

import silver:compiler:definition:type;


inherited attribute flowEnv :: FlowEnv;
monoid attribute flowDefs :: [FlowDef];
-- These are factored out of FlowDefs to avoid a circular dependency,
-- since they are needed during type checking
monoid attribute specDefs :: [(String, String, [String], [String])];  -- (nt, attr, [inhs], [referenced flow specs])
monoid attribute refDefs :: [(String, [String])];

nonterminal FlowEnv with synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree, uniqueRefTree, refDecSiteTree, localInhTree, localTree, nonSuspectTree, hostSynTree, specTree, prodGraphTree;

annotation synTree :: EnvTree<FlowDef>;
annotation inhTree :: EnvTree<FlowDef>;
annotation defTree :: EnvTree<FlowDef>;
annotation fwdTree :: EnvTree<FlowDef>;
annotation fwdInhTree :: EnvTree<FlowDef>;
annotation prodTree :: EnvTree<FlowDef>;
annotation refTree :: EnvTree<[String]>;
annotation uniqueRefTree :: EnvTree<UniqueRefSite>;
annotation refDecSiteTree :: EnvTree<VertexType>;
annotation localInhTree ::EnvTree<FlowDef>;
annotation localTree :: EnvTree<FlowDef>;
annotation nonSuspectTree :: EnvTree<[String]>;
annotation hostSynTree :: EnvTree<FlowDef>;
annotation specTree :: EnvTree<(String, [String], [String])>;
annotation prodGraphTree :: EnvTree<FlowDef>;

abstract production flowEnv
top::FlowEnv ::=
{}

function fromFlowDefs
FlowEnv ::=
  specContribs::[(String, String, [String], [String])] refContribs::[(String, [String])]
  uniqueRefContribs::[(String, UniqueRefSite)]
  d::FlowDefs
{
  return flowEnv(
    synTree = directBuildTree(d.synTreeContribs),
    inhTree = directBuildTree(d.inhTreeContribs),
    defTree = directBuildTree(d.defTreeContribs),
    fwdTree = directBuildTree(d.fwdTreeContribs),
    fwdInhTree = directBuildTree(d.fwdInhTreeContribs),
    prodTree = directBuildTree(d.prodTreeContribs),
    refTree = directBuildTree(refContribs),
    uniqueRefTree = directBuildTree(uniqueRefContribs),
    refDecSiteTree = directBuildTree(d.refDecSiteContribs),
    localInhTree = directBuildTree(d.localInhTreeContribs),
    localTree = directBuildTree(d.localTreeContribs),
    nonSuspectTree = directBuildTree(d.nonSuspectContribs),
    hostSynTree = directBuildTree(d.hostSynTreeContribs),
    specTree = directBuildTree(specContribs),
    prodGraphTree = directBuildTree(d.prodGraphContribs)
  );
}


-- synthesized equation in a production
function lookupSyn
[FlowDef] ::= prod::String  attr::String  e::FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.synTree);
}

-- inherited equation for a child in a production
function lookupInh
[FlowDef] ::= prod::String  sigName::String  attr::String  e::FlowEnv
{
  return searchEnvTree(crossnames(prod, crossnames(sigName, attr)), e.inhTree);
}

-- default equation for a nonterminal
function lookupDef
[FlowDef] ::= nt::String  attr::String  e::FlowEnv
{
  return searchEnvTree(crossnames(nt, attr), e.defTree);
}

-- forward equation for a production
function lookupFwd
[FlowDef] ::= prod::String  e::FlowEnv
{
  return searchEnvTree(prod, e.fwdTree);
}

-- inherited equation for the forward in a production
function lookupFwdInh
[FlowDef] ::= prod::String  attr::String  e::FlowEnv
{
  return searchEnvTree(crossnames(prod, attr), e.fwdInhTree);
}

-- inherited equation for a local in a production
function lookupLocalInh
[FlowDef] ::= prod::String  fName::String  attr::String  e::FlowEnv
{
  return searchEnvTree(crossnames(prod, crossnames(fName, attr)), e.localInhTree);
}

function lookupLocalEq
[FlowDef] ::= prod::String  fName::String  e::FlowEnv
{
  return searchEnvTree(crossnames(prod, fName), e.localTree);
}

-- unique references taken for a child
function lookupUniqueRefs
[UniqueRefSite] ::= prod::String sigName::String  e::FlowEnv
{
  return searchEnvTree(prod ++ ":" ++ sigName, e.uniqueRefTree);
}

-- unique references taken for a local/production attribute
function lookupLocalUniqueRefs
[UniqueRefSite] ::= fName::String  e::FlowEnv
{
  return searchEnvTree(fName, e.uniqueRefTree);
}

-- decoration sites for unique references taken for a child
function lookupRefDecSite
[VertexType] ::= prod::String  sigName::String  e::FlowEnv
{
  return searchEnvTree(s"${prod}:${sigName}", e.refDecSiteTree);
}

-- decoration sites for unique references taken for a local/production attribute
function lookupLocalRefDecSite
[VertexType] ::= fName::String  e::FlowEnv
{
  return searchEnvTree(fName, e.refDecSiteTree);
}

{--
 - Combine lists of flow defs from mutually-exclusive subexpressions,
 - by only keeping decoration site equations that appear in both places.
 -}
function intersectRefDecSiteEqs
[FlowDef] ::= d1::[FlowDef] d2::[FlowDef]
{
  local decSites1::[(String, VertexType)] = flatMap((.refDecSiteContribs), d1);
  local decSites2::[(String, VertexType)] = flatMap((.refDecSiteContribs), d2);
  return
    filter(\ d::FlowDef -> all(map(\ ds::(String, VertexType) -> lookup(ds.1, decSites2).isJust, d.refDecSiteContribs)), d1) ++
    filter(\ d::FlowDef -> all(map(\ ds::(String, VertexType) -> lookup(ds.1, decSites1).isJust, d.refDecSiteContribs)), d2);
}

{--
 - This is a glorified lambda function, to help look for equations.
 - Literally, we're just checking for null here.
 -
 - @param f  The lookup function for the appropriate type of equation
 -           e.g. `lookupInh(prod, rhs, _, env)`
 - @param attr  The attribute to look up.
 -}
function isEquationMissing
Boolean ::= f::([FlowDef] ::= String)  attr::String
{
  return null(f(attr));
}

-- default set of inherited attributes required/assumed to exist for references
function getInhsForNtRef
[[String]] ::= nt::String  e::FlowEnv
{
  return searchEnvTree(nt, e.refTree);
}

-- implicit forward syn copy equations that are allowed to affect the flow type
function getNonSuspectAttrsForProd
[String] ::= prod::String  e::FlowEnv
{
  return concat(searchEnvTree(prod, e.nonSuspectTree));
}

-- all (non-forwarding) productions constructing a nonterminal
function getNonforwardingProds
[String] ::= nt::String  e::FlowEnv
{
  local extractProdName :: (String ::= FlowDef) =
    \p::FlowDef -> case p of prodFlowDef(_, p) -> p | _ -> error("Searches only prod defs") end;

  return map(extractProdName, searchEnvTree(nt, e.prodTree));
}

-- Ext Syns subject to ft lower bound
function getHostSynsFor
[String] ::= nt::String  e::FlowEnv
{
  local extractHostSynName :: (String ::= FlowDef) =
    \f::FlowDef -> case f of hostSynFlowDef(_, at) -> at | _ -> error("Searches only host defs") end;

  return map(extractHostSynName, searchEnvTree(nt, e.hostSynTree));
}

-- Get syns (and "forward") that have flow types specified
function getSpecifiedSynsForNt
[String] ::= nt::String  e::FlowEnv
{
  return map(fst, searchEnvTree(nt, e.specTree));
}
function getFlowTypeSpecFor
[(String, [String], [String])] ::= nt::String  e::FlowEnv
{
  return searchEnvTree(nt, e.specTree);
}

function getGraphContribsFor
[FlowDef] ::= prod::String  e::FlowEnv
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
