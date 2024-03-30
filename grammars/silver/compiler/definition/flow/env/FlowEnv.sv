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

data nonterminal FlowEnv with
  synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree,
  sharedRefTree, refPossibleDecSiteTree, refDecSiteTree, sigShareTree,
  localInhTree, localTree, nonSuspectTree, hostSynTree, specTree,
  prodGraphTree;

synthesized attribute synTree :: EnvTree<FlowDef>;
synthesized attribute inhTree :: EnvTree<FlowDef>;
synthesized attribute defTree :: EnvTree<FlowDef>;
synthesized attribute fwdTree :: EnvTree<FlowDef>;
synthesized attribute fwdInhTree :: EnvTree<FlowDef>;
synthesized attribute prodTree :: EnvTree<FlowDef>;
synthesized attribute refTree :: EnvTree<[String]>;
synthesized attribute sharedRefTree :: EnvTree<SharedRefSite>;
synthesized attribute refPossibleDecSiteTree :: EnvTree<VertexType>;
synthesized attribute refDecSiteTree :: EnvTree<VertexType>;
synthesized attribute sigShareTree :: EnvTree<(String, VertexType)>;
synthesized attribute localInhTree ::EnvTree<FlowDef>;
synthesized attribute localTree :: EnvTree<FlowDef>;
synthesized attribute nonSuspectTree :: EnvTree<[String]>;
synthesized attribute hostSynTree :: EnvTree<FlowDef>;
synthesized attribute specTree :: EnvTree<(String, [String], [String])>;
synthesized attribute prodGraphTree :: EnvTree<FlowDef>;

abstract production flowEnv
top::FlowEnv ::=
  specContribs::[(String, String, [String], [String])] refContribs::[(String, [String])]
  sharedRefContribs::[(String, SharedRefSite)]
  d::FlowDefs
{
  top.synTree = directBuildTree(d.synTreeContribs);
  top.inhTree = directBuildTree(d.inhTreeContribs);
  top.defTree = directBuildTree(d.defTreeContribs);
  top.fwdTree = directBuildTree(d.fwdTreeContribs);
  top.fwdInhTree = directBuildTree(d.fwdInhTreeContribs);
  top.prodTree = directBuildTree(d.prodTreeContribs);
  top.refTree = directBuildTree(refContribs);
  top.sharedRefTree = directBuildTree(sharedRefContribs);
  top.refPossibleDecSiteTree = directBuildTree(d.refPossibleDecSiteContribs);
  top.refDecSiteTree = directBuildTree(d.refDecSiteContribs);
  top.sigShareTree = directBuildTree(d.sigShareContribs);
  top.localInhTree = directBuildTree(d.localInhTreeContribs);
  top.localTree = directBuildTree(d.localTreeContribs);
  top.nonSuspectTree = directBuildTree(d.nonSuspectContribs);
  top.hostSynTree = directBuildTree(d.hostSynTreeContribs);
  top.specTree = directBuildTree(specContribs);
  top.prodGraphTree = directBuildTree(d.prodGraphContribs);
}


-- synthesized equation in a production
fun lookupSyn [FlowDef] ::= prod::String  attr::String  e::FlowEnv =
  searchEnvTree(crossnames(prod, attr), e.synTree);

-- inherited equation for a child in a production
fun lookupInh [FlowDef] ::= prod::String  sigName::String  attr::String  e::FlowEnv =
  searchEnvTree(crossnames(prod, crossnames(sigName, attr)), e.inhTree);

-- default equation for a nonterminal
fun lookupDef [FlowDef] ::= nt::String  attr::String  e::FlowEnv =
  searchEnvTree(crossnames(nt, attr), e.defTree);

-- forward equation for a production
fun lookupFwd [FlowDef] ::= prod::String  e::FlowEnv = searchEnvTree(prod, e.fwdTree);

-- inherited equation for the forward in a production
fun lookupFwdInh [FlowDef] ::= prod::String  attr::String  e::FlowEnv =
  searchEnvTree(crossnames(prod, attr), e.fwdInhTree);

-- inherited equation for a local in a production
fun lookupLocalInh [FlowDef] ::= prod::String  fName::String  attr::String  e::FlowEnv =
  searchEnvTree(crossnames(prod, crossnames(fName, attr)), e.localInhTree);

fun lookupLocalEq [FlowDef] ::= prod::String  fName::String  e::FlowEnv =
  searchEnvTree(crossnames(prod, fName), e.localTree);

-- places where this tree is shared
fun lookupSharedRefs [SharedRefSite] ::= prod::String v::VertexType e::FlowEnv =
  searchEnvTree(s"${prod}:${v.vertexName}", e.sharedRefTree);

-- possible decoration sites for places where this tree is shared
fun lookupRefPossibleDecSites [VertexType] ::= prod::String v::VertexType e::FlowEnv =
  searchEnvTree(s"${prod}:${v.vertexName}", e.refPossibleDecSiteTree);

-- unconditional decoration sites for places where this tree is shared
fun lookupRefDecSite [VertexType] ::= prod::String v::VertexType e::FlowEnv =
  searchEnvTree(s"${prod}:${v.vertexName}", e.refDecSiteTree);

-- places where this child was decorated in a production forwarding to this one
fun lookupSigShareSites [(String, VertexType)] ::= prod::String sigName::String e::FlowEnv =
  searchEnvTree(crossnames(prod, sigName), e.sigShareTree);

-- places where this child was decorated in a production forwarding to this one,
-- or in a dispatch signature that this production implements
fun lookupAllSigShareSites [(String, VertexType)] ::= prod::String sigName::String e::FlowEnv realEnv::Env =
  lookupSigShareSites(prod, sigName, e) ++
  case getValueDcl(prod, realEnv) of
  | dcl :: _ when dcl.implementedSignature matches just(sig) ->
    lookupSigShareSites(
      sig.fullName,
      head(drop(positionOf(sigName, dcl.namedSignature.inputNames), sig.inputNames)),
      e)
  | _ -> []
  end;

-- Just a helper for filtering.
fun remoteProdMissingInhEq
Boolean ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv realEnv::Env =
  !remoteProdHasInhEq(prodName, vt, attrName, flowEnv, realEnv);

-- Is this there an equation for this inh attr on any decoration site for this vertex?
fun remoteProdHasInhEq
Boolean ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv realEnv::Env =
  any(unzipWith(vertexHasInhEq(_, _, attrName, flowEnv), lookupAllDecSites(prodName, vt, flowEnv))) ||
  case vt of
  | rhsVertexType(sigName) when getValueDcl(sigName, realEnv) matches dcl :: _ ->
      dcl.isShared &&
      all(unzipWith(vertexHasInhEq(_, _, attrName, flowEnv),
        lookupAllSigShareSites(prodName, sigName, flowEnv, realEnv)))
  | _ -> false
  end;

-- Find all decoration sites productions/vertices for this vertex
-- TODO: This doesn't gracefully handle cycles in sharing.
fun lookupAllDecSites [(String, VertexType)] ::= prodName::String  vt::VertexType  flowEnv::FlowEnv =
  (prodName, vt) ::
  case vt of
  | subtermVertexType(_, remoteProdName, sigName) ->
    lookupAllDecSites(remoteProdName, rhsVertexType(sigName), flowEnv)
  | _ -> flatMap(lookupAllDecSites(prodName, _, flowEnv), lookupRefDecSite(prodName, vt, flowEnv))
  end;

fun vertexHasInhEq Boolean ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv =
  case vt of
  | rhsVertexType(sigName) -> !null(lookupInh(prodName, sigName, attrName, flowEnv))
  | localVertexType(fName) -> !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
  | transAttrVertexType(rhsVertexType(sigName), transAttr) ->
    !null(lookupInh(prodName, sigName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(localVertexType(fName), transAttr) ->
    !null(lookupLocalInh(prodName, fName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(_, _) -> false
  | anonVertexType(fName) -> !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
  | subtermVertexType(_, remoteProdName, sigName) ->
    vertexHasInhEq(remoteProdName, rhsVertexType(sigName), attrName, flowEnv)
  -- This is a tricky case since we don't know what decorated this prod.
  -- checkEqDeps can count on missing LHS inh eqs being caught as flow issues elsewhere,
  -- but here we are remotely looking for equations that might not be the direct dependency of
  -- anything in the prod flow graph.
  | lhsVertexType_real() -> false  -- Shouldn't ever be directly needed, since the LHS is never the dec site for another vertex.
  | forwardVertexType_real() -> false  -- Same as LHS, but we can check this if e.g. forwarding to a child.
  end;

{--
 - This is a glorified lambda function, to help look for equations.
 - Literally, we're just checking for null here.
 -
 - @param f  The lookup function for the appropriate type of equation
 -           e.g. `lookupInh(prod, rhs, _, env)`
 - @param attr  The attribute to look up.
 -}
fun isEquationMissing Boolean ::= f::([FlowDef] ::= String)  attr::String = null(f(attr));

-- default set of inherited attributes required/assumed to exist for references
fun getInhsForNtRef [[String]] ::= nt::String  e::FlowEnv = searchEnvTree(nt, e.refTree);

-- implicit forward syn copy equations that are allowed to affect the flow type
fun getNonSuspectAttrsForProd [String] ::= prod::String  e::FlowEnv =
  concat(searchEnvTree(prod, e.nonSuspectTree));

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
fun getSpecifiedSynsForNt [String] ::= nt::String  e::FlowEnv =
  map(fst, searchEnvTree(nt, e.specTree));
fun getFlowTypeSpecFor [(String, [String], [String])] ::= nt::String  e::FlowEnv =
  searchEnvTree(nt, e.specTree);

fun getGraphContribsFor [FlowDef] ::= prod::String  e::FlowEnv =
  searchEnvTree(prod, e.prodGraphTree);

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
[FlowDef] ::= ns::NamedSignature env::Env t::Type vt::VertexType
{
  local contexts::Contexts = foldContexts(ns.contexts);
  contexts.env = env;
  return map(
    \ synDeps::(String, [String]) -> synOccursContextEq(ns.fullName, vt, synDeps.fst, synDeps.snd),
    lookupAll(t.typeName, contexts.occursContextInhDeps));
}

function splitTransAttrInh
Maybe<(String, String)> ::= attr::String
{
  local i::Integer = indexOf(".", attr);
  return if i == -1 then nothing() else
    just((substring(0, i, attr), substring(i + 1, length(attr), attr)));
}
