grammar silver:compiler:definition:flow:env;

imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:env;
imports silver:compiler:definition:core;

import silver:compiler:definition:type;


autocopy attribute flowEnv :: FlowEnv;
monoid attribute flowDefs :: [FlowDef];
-- These are factored out of FlowDefs to avoid a circular dependency,
-- since they are needed during type checking
monoid attribute specDefs :: [(String, String, [String], [String])];  -- (nt, attr, [inhs], [referenced flow specs])
monoid attribute refDefs :: [(String, [String])];

nonterminal FlowEnv with synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree, localInhTree, localTree, nonSuspectTree, hostSynTree, specTree, prodGraphTree, inhSetBaseMemberTree, inhSetMemberTree, inhSetRefTree;

annotation synTree :: EnvTree<FlowDef>;
annotation inhTree :: EnvTree<FlowDef>;
annotation defTree :: EnvTree<FlowDef>;
annotation fwdTree :: EnvTree<FlowDef>;
annotation fwdInhTree :: EnvTree<FlowDef>;
annotation prodTree :: EnvTree<FlowDef>;
annotation refTree :: EnvTree<[String]>;
annotation localInhTree ::EnvTree<FlowDef>;
annotation localTree :: EnvTree<FlowDef>;
annotation nonSuspectTree :: EnvTree<[String]>;
annotation hostSynTree :: EnvTree<FlowDef>;
annotation specTree :: EnvTree<(String, [String], [String])>;
annotation prodGraphTree :: EnvTree<FlowDef>;
annotation inhSetBaseMemberTree :: EnvTree<String>;
annotation inhSetMemberTree :: EnvTree<String>;
annotation inhSetRefTree :: EnvTree<(String, VertexType, Location)>;

abstract production flowEnv
top::FlowEnv ::=
{}

function fromFlowDefs
FlowEnv ::=
  specContribs::[(String, String, [String], [String])] refContribs::[(String, [String])]
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
    localInhTree = directBuildTree(d.localInhTreeContribs),
    localTree = directBuildTree(d.localTreeContribs),
    nonSuspectTree = directBuildTree(d.nonSuspectContribs),
    hostSynTree = directBuildTree(d.hostSynTreeContribs),
    specTree = directBuildTree(specContribs),
    prodGraphTree = directBuildTree(d.prodGraphContribs),
    inhSetBaseMemberTree = directBuildTree(d.inhSetBaseMemberContribs),
    inhSetMemberTree = directBuildTree(d.inhSetMemberContribs),
    inhSetRefTree = directBuildTree(d.inhSetRefContribs)
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

function getInhSetConstBaseMembers
[String] ::= fn::String  e::FlowEnv
{
  return sort(nub(searchEnvTree(fn, e.inhSetBaseMemberTree)));
}

function getInhSetConstMembers
[String] ::= fn::String  e::FlowEnv
{
  return sort(nub(searchEnvTree(fn, e.inhSetMemberTree)));
}

function getInhSetRefs
[(String, VertexType, Location)] ::= fn::String  e::FlowEnv
{
  return searchEnvTree(fn, e.inhSetRefTree);
}

-- Compute a lower bound on the members of an InhSet type, including transitive ones arising from subset constraints
function getMinInhSetMembers
([String], [TyVar]) ::= seen::[TyVar] t::Type env::Decorated Env flowEnv::FlowEnv
{
  local c::Context = inhSubsetContext(varType(freshTyVar(inhSetKind())), t);
  c.env = env;

  local recurse::[([String], [TyVar])] =
    map(
      \ d::InstDclInfo -> getMinInhSetMembers(t.freeVariables ++ seen, d.typeScheme.monoType, env, flowEnv),
      c.resolved);

  return
    case t of
    | skolemType(tv) when contains(tv, seen) -> ([], [])
    | varType(_) -> ([], [])  -- If an InhSet is unspecialized after type checking, assume it is empty
    | inhSetConstType(fn) -> (getInhSetConstMembers(fn, flowEnv), [])
    | _ -> (sort(unions(t.inhSetMembers :: map(fst, recurse))), unions(t.freeVariables :: map(snd, recurse))) 
    end;
}

-- Compute a lower bound on the members of the reference set for a reference type
function getMinInhsOnRef
[String] ::= t::Type env::Decorated Env flowEnv::FlowEnv
{
  return
    case t of
    | decoratedType(_, i) -> getMinInhSetMembers([], i, env, flowEnv).fst
    | _ -> []
    end;
}

-- Try to compute an upper bound on the members of an InhSet type, including transitive ones arising from subset constraints
function getMaxInhSetMembers
(Maybe<[String]>, [TyVar]) ::= seen::[TyVar] t::Type env::Decorated Env flowEnv::FlowEnv
{
  local c::Context = inhSubsetContext(t, varType(freshTyVar(inhSetKind())));
  c.env = env;

  local recurse::[(Maybe<[String]>, [TyVar])] =
    map(
      \ d::InstDclInfo -> getMaxInhSetMembers(t.freeVariables ++ seen, d.typerep2, env, flowEnv),
      c.resolved);

  return
    case t of
    | skolemType(tv) when contains(tv, seen) -> (nothing(), [])
    | varType(_) -> (just([]), [])  -- If an InhSet is unspecialized after type checking, assume it is empty
    | inhSetType(inhs) -> (just(inhs), [])
    | inhSetConstType(_) -> (nothing(), [])  -- We can never compute an upper bound for the contributions
    | _ -> (map(sort, foldr(
        \ inhs1::Maybe<[String]> inhs2::Maybe<[String]> -> alt(lift2(intersect, inhs1, inhs2), alt(inhs1, inhs2)),
        empty, map(fst, recurse))),
      unions(t.freeVariables :: map(snd, recurse)))
    end;
}

-- Try to compute an upper bound on the members of the reference set for a reference type
function getDepsForTakingRef
Maybe<[String]> ::= t::Type env::Decorated Env flowEnv::FlowEnv
{
  return
    case t of
    | decoratedType(_, inhSetConstType(fn)) -> just(getInhSetConstBaseMembers(fn, flowEnv))  -- Not all the inhs on the ref, but all the ones we need to supply here
    | decoratedType(_, i) -> getMaxInhSetMembers([], i, env, flowEnv).fst
    | _ -> just([])
    end;
}

attribute flowEnv occurs on Contexts, Context;

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
  local maxInhSetMembers::(Maybe<[String]>, [TyVar]) = getMaxInhSetMembers([], inhs, top.env, top.flowEnv);
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
