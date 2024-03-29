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

data nonterminal FlowEnv with synTree, inhTree, defTree, fwdTree, prodTree, fwdInhTree, refTree, uniqueRefTree, refPossibleDecSiteTree, refDecSiteTree, localInhTree, localTree, nonSuspectTree, hostSynTree, specTree, prodGraphTree;

synthesized attribute synTree :: EnvTree<FlowDef>;
synthesized attribute inhTree :: EnvTree<FlowDef>;
synthesized attribute defTree :: EnvTree<FlowDef>;
synthesized attribute fwdTree :: EnvTree<FlowDef>;
synthesized attribute fwdInhTree :: EnvTree<FlowDef>;
synthesized attribute prodTree :: EnvTree<FlowDef>;
synthesized attribute refTree :: EnvTree<[String]>;
synthesized attribute uniqueRefTree :: EnvTree<UniqueRefSite>;
synthesized attribute refPossibleDecSiteTree :: EnvTree<VertexType>;
synthesized attribute refDecSiteTree :: EnvTree<VertexType>;
synthesized attribute localInhTree ::EnvTree<FlowDef>;
synthesized attribute localTree :: EnvTree<FlowDef>;
synthesized attribute nonSuspectTree :: EnvTree<[String]>;
synthesized attribute hostSynTree :: EnvTree<FlowDef>;
synthesized attribute specTree :: EnvTree<(String, [String], [String])>;
synthesized attribute prodGraphTree :: EnvTree<FlowDef>;

abstract production flowEnv
top::FlowEnv ::=
  specContribs::[(String, String, [String], [String])] refContribs::[(String, [String])]
  uniqueRefContribs::[(String, UniqueRefSite)]
  d::FlowDefs
{
  top.synTree = directBuildTree(d.synTreeContribs);
  top.inhTree = directBuildTree(d.inhTreeContribs);
  top.defTree = directBuildTree(d.defTreeContribs);
  top.fwdTree = directBuildTree(d.fwdTreeContribs);
  top.fwdInhTree = directBuildTree(d.fwdInhTreeContribs);
  top.prodTree = directBuildTree(d.prodTreeContribs);
  top.refTree = directBuildTree(refContribs);
  top.uniqueRefTree = directBuildTree(uniqueRefContribs);
  top.refPossibleDecSiteTree = directBuildTree(d.refPossibleDecSiteContribs);
  top.refDecSiteTree = directBuildTree(d.refDecSiteContribs);
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

-- unique references taken for a child
fun lookupUniqueRefs [UniqueRefSite] ::= prod::String sigName::String  e::FlowEnv =
  searchEnvTree(prod ++ ":" ++ sigName, e.uniqueRefTree);

-- unique references taken for a local/production attribute
fun lookupLocalUniqueRefs [UniqueRefSite] ::= fName::String  e::FlowEnv =
  searchEnvTree(fName, e.uniqueRefTree);

-- unique references taken for a translation attribute on a child
fun lookupTransUniqueRefs
[UniqueRefSite] ::= prod::String sigName::String attrName::String e::FlowEnv =
  searchEnvTree(prod ++ ":" ++ sigName ++ "." ++ attrName, e.uniqueRefTree);

-- unique references taken for a translation attribute on a local
fun lookupLocalTransUniqueRefs [UniqueRefSite] ::= fName::String attrName::String e::FlowEnv =
  searchEnvTree(fName ++ "." ++ attrName, e.uniqueRefTree);

-- possible decoration sites for unique references taken for a child
fun lookupRefPossibleDecSites [VertexType] ::= prod::String  sigName::String  e::FlowEnv =
  searchEnvTree(s"${prod}:${sigName}", e.refPossibleDecSiteTree);

-- possible decoration sites for unique references taken for a local/production attribute
fun lookupLocalRefPossibleDecSites [VertexType] ::= fName::String  e::FlowEnv =
  searchEnvTree(fName, e.refPossibleDecSiteTree);

-- possible decoration sites for unique references taken for a translation attribute on a child
fun lookupTransRefPossibleDecSites
[VertexType] ::= prod::String  sigName::String  attrName::String  e::FlowEnv =
  searchEnvTree(s"${prod}:${sigName}.${attrName}", e.refPossibleDecSiteTree);

-- possible decoration sites for unique references taken for a translation attribute on a local/production attribute
fun lookupLocalTransRefPossibleDecSites [VertexType] ::= fName::String  attrName::String  e::FlowEnv =
  searchEnvTree(s"${fName}.${attrName}", e.refPossibleDecSiteTree);

-- unconditional decoration sites for unique references taken for a child
fun lookupRefDecSite [VertexType] ::= prod::String  sigName::String  e::FlowEnv =
  searchEnvTree(s"${prod}:${sigName}", e.refDecSiteTree);

-- unconditional decoration sites for unique references taken for a local/production attribute
fun lookupLocalRefDecSite [VertexType] ::= fName::String  e::FlowEnv =
  searchEnvTree(fName, e.refDecSiteTree);

-- unconditional decoration sites for unique references taken for a translation attribute on a child
fun lookupTransRefDecSite
[VertexType] ::= prod::String  sigName::String  attrName::String  e::FlowEnv =
  searchEnvTree(s"${prod}:${sigName}.${attrName}", e.refDecSiteTree);

-- unconditional decoration sites for unique references taken for a translation attribute on a local/production attribute
fun lookupLocalTransRefDecSite [VertexType] ::= fName::String  attrName::String  e::FlowEnv =
  searchEnvTree(s"${fName}.${attrName}", e.refDecSiteTree);

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
