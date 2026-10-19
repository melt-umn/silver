grammar silver:compiler:definition:flow:env;

imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:env;
imports silver:compiler:definition:core;
imports silver:compiler:definition:type only typerep;
imports silver:compiler:analysis:uniqueness;

import silver:compiler:definition:type;


inherited attribute flowEnv :: FlowEnv;
monoid attribute flowDefs :: [FlowDef];
-- These are factored out of FlowDefs to avoid a circular dependency,
-- since they are needed during type checking
monoid attribute specDefs :: [(String, String, [String], [String])];  -- (nt, attr, [inhs], [referenced flow specs])
monoid attribute refDefs :: [(String, [String])];

data nonterminal FlowEnv with
  synTree, inhTree, defTree, fwdTree, prodTree, implTree, implOfTree, fwdInhTree, refTree,
  sharedRefTree, refPossibleDecSiteTree, refDecSiteTree, sharedIntoTree, sigShareTree,
  localInhTree, localTree, nonSuspectTree, hostSynTree, specTree,
  prodGraphTree;

synthesized attribute synTree :: EnvTree<FlowDef>;
synthesized attribute inhTree :: EnvTree<FlowDef>;
synthesized attribute defTree :: EnvTree<FlowDef>;
synthesized attribute fwdTree :: EnvTree<FlowDef>;
synthesized attribute fwdInhTree :: EnvTree<FlowDef>;
synthesized attribute prodTree :: EnvTree<String>;
synthesized attribute implTree :: EnvTree<(String, [String])>;
synthesized attribute implOfTree :: EnvTree<(String, [String])>;
synthesized attribute refTree :: EnvTree<[String]>;
synthesized attribute sharedRefTree :: EnvTree<SharedRefSite>;
synthesized attribute refPossibleDecSiteTree :: EnvTree<VertexType>;
synthesized attribute refDecSiteTree :: EnvTree<VertexType>;
synthesized attribute sharedIntoTree :: EnvTree<(VertexType, Boolean)>;
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
  top.implTree = directBuildTree(d.implTreeContribs);
  top.implOfTree = directBuildTree(map(\ c::(String, String, [String]) -> (c.2, (c.1, c.3)), d.implTreeContribs));
  top.refTree = directBuildTree(refContribs);
  top.sharedRefTree = directBuildTree(sharedRefContribs);
  top.refPossibleDecSiteTree = directBuildTree(d.refPossibleDecSiteContribs);
  top.refDecSiteTree = directBuildTree(d.refDecSiteContribs);
  top.sharedIntoTree = directBuildTree(d.sharedIntoContribs);
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

-- places where the tree at the root of this vertex (that it is a translation attribute of, and so on),
-- or a translation attribute of that tree at any depth, is shared
fun lookupSharedRefs [SharedRefSite] ::= prod::String v::VertexType e::FlowEnv =
  searchEnvTree(s"${prod}:${transRootVertex(v).vertexName}", e.sharedRefTree);

-- possible decoration sites for places where this tree is shared
fun lookupRefPossibleDecSites [VertexType] ::= prod::String v::VertexType e::FlowEnv =
  searchEnvTree(s"${prod}:${v.vertexName}", e.refPossibleDecSiteTree);

-- unconditional decoration sites for places where this tree is shared
fun lookupRefDecSite [VertexType] ::= prod::String v::VertexType e::FlowEnv =
  searchEnvTree(s"${prod}:${v.vertexName}", e.refDecSiteTree);

-- possible decoration sites for places where this tree is shared, or where a tree that it is
-- a translation attribute of (at any depth) is shared, as sharing a tree also shares its translations
fun lookupAllRefPossibleDecSites [VertexType] ::= prod::String v::VertexType e::FlowEnv =
  lookupRefPossibleDecSites(prod, v, e) ++
  case v of
  | transAttrVertexType(treeVertex, transAttr) ->
    map(transAttrVertexType(_, transAttr), lookupAllRefPossibleDecSites(prod, treeVertex, e))
  | _ -> []
  end;

-- unconditional decoration sites for places where this tree is shared, or where a tree that it is
-- a translation attribute of (at any depth) is shared, as sharing a tree also shares its translations
fun lookupAllRefDecSites [VertexType] ::= prod::String v::VertexType e::FlowEnv =
  lookupRefDecSite(prod, v, e) ++
  case v of
  | transAttrVertexType(treeVertex, transAttr) ->
    map(transAttrVertexType(_, transAttr), lookupAllRefDecSites(prod, treeVertex, e))
  | _ -> []
  end;

-- The vertex types shared in prod that are translation attributes of v (of translation attributes,
-- and so on), each once, with those translation attributes outermost first: v.t1.t2 gives ["t1",
-- "t2"]
fun sharedTransAttrsOf [([String], VertexType)] ::= prod::String  v::VertexType  e::FlowEnv =
  nub(flatMap(
    \ s::SharedRefSite ->
      case transAttrsFrom(v, s.sharedVertex) of
      | just(transAttrs) -> [(transAttrs, s.sharedVertex)]
      | nothing() -> []
      end,
    lookupSharedRefs(prod, v, e)));

-- The translation attributes t1 ... tk, for k >= 1, such that sub is v.t1 ... tk.
fun transAttrsFrom Maybe<[String]> ::= v::VertexType  sub::VertexType =
  case sub of
  | transAttrVertexType(treeVertex, transAttr) when treeVertex == v -> just([transAttr])
  | transAttrVertexType(treeVertex, transAttr) ->
    map(\ transAttrs::[String] -> transAttrs ++ [transAttr], transAttrsFrom(v, treeVertex))
  | _ -> nothing()
  end;

-- May the tree at v in prod become prod's forward, in some branch, directly or through locals that
-- it is shared into?
fun mayBecomeForward Boolean ::= prod::String  v::VertexType  flowEnv::FlowEnv =
  mayBecomeForwardSeen([], prod, v, flowEnv);
-- mayBecomeForward, leaving out the trees in seen, which were visited on the way to v.
function mayBecomeForwardSeen
Boolean ::= seen::[VertexType]  prod::String  v::VertexType  flowEnv::FlowEnv
{
  local decSites :: [VertexType] = nub(lookupAllRefPossibleDecSites(prod, v, flowEnv));
  -- Only a tree at a local, or at a translation attribute of a local, can be shared again.
  local localDecSites :: [VertexType] =
    filter(
      \ d::VertexType -> case transRootVertex(d) of | localVertexType(_) -> true | _ -> false end,
      decSites);
  return
    !contains(v, seen) &&
    (contains(forwardVertexType(), decSites) ||
     any(map(mayBecomeForwardSeen(v :: seen, prod, _, flowEnv), localDecSites)));
}

-- the trees shared at a decoration site of a production, each with whether the sharing is
-- unconditional
fun lookupSharedInto [(VertexType, Boolean)] ::= prod::String  v::VertexType  e::FlowEnv =
  searchEnvTree(s"${prod}:${v.vertexName}", e.sharedIntoTree);
-- the trees unconditionally shared at a decoration site (at most one, as a tree is shared once)
fun lookupSharedIntoAlways [VertexType] ::= prod::String  v::VertexType  e::FlowEnv =
  map(fst, filter(snd, lookupSharedInto(prod, v, e)));

-- places in the host language where this child was decorated in a production forwarding to this one
fun lookupSigShareSites [(String, VertexType)] ::= prod::String sigName::String e::FlowEnv =
  searchEnvTree(crossnames(prod, sigName), e.sigShareTree);

fun lookupAllSigShareSites [(String, VertexType)] ::= prod::String sigName::String e::FlowEnv realEnv::Env =
  -- places where this child was decorated in a production forwarding to this one
  lookupSigShareSites(prod, sigName, e) ++
  -- or in a dispatch signature that this production implements
  case implementedSigChild(prod, sigName, e, realEnv) of
  | just((dispatch, se)) -> lookupSigShareSites(dispatch, se.elementName, e)
  | nothing() -> []
  end;

-- The dispatch signature that prod implements and the dispatch signature's child at the position of
-- prod's child sigName: by prod's declaration or, where that is out of scope here, by prod's flow
-- definitions.
function implementedSigChild
Maybe<(String, NamedSignatureElement)> ::= prod::String  sigName::String  e::FlowEnv  realEnv::Env
{
  -- The dispatch signature, prod's children and the dispatch signature's children
  local dispatchChildren :: Maybe<(String, [String], [NamedSignatureElement])> =
    case getValueDcl(prod, realEnv) of
    | dcl :: _ when dcl.implementedSignature matches just(dispatchSig) ->
      just((dispatchSig.fullName, dcl.namedSignature.inputNames, dispatchSig.inputElements))
    | dcl :: _ -> nothing()
    | [] ->
      case lookupImplementedSig(prod, e) of
      | just((dispatch, sigNames)) ->
        case getTypeDcl(dispatch, realEnv) of
        | typeDcl :: _ -> just((dispatch, sigNames, typeDcl.dispatchSignature.inputElements))
        | [] -> nothing()
        end
      | nothing() -> nothing()
      end
    end;
  local pos :: Integer = positionOf(sigName, dispatchChildren.fromJust.2);
  return
    -- Where prod's signature does not match the dispatch signature, there is no such child.
    if dispatchChildren.isJust && pos >= 0 && pos < length(dispatchChildren.fromJust.3)
    then just((dispatchChildren.fromJust.1, head(drop(pos, dispatchChildren.fromJust.3))))
    else nothing();
}

-- The dispatch signature that prod implements and the names of prod's children, from prod's flow
-- definitions: for an implementation declared in a grammar this one does not import (see
-- findDecSites).
fun lookupImplementedSig Maybe<(String, [String])> ::= prod::String  e::FlowEnv =
  case searchEnvTree(prod, e.implOfTree) of
  | r :: _ -> just(r)
  | [] -> nothing()
  end;

-- Is the child sigName of prod shared through its signature?  By prod's declaration, or, for an
-- implementation whose declaration is out of scope here, by the dispatch signature's child at the
-- same position.
fun sigSharedChild Boolean ::= prod::String  sigName::String  flowEnv::FlowEnv  realEnv::Env =
  case getValueDcl(prod, realEnv) of
  | dcl :: _ -> lookupSignatureInputElem(sigName, dcl.namedSignature).elementShared
  | [] ->
    case implementedSigChild(prod, sigName, flowEnv, realEnv) of
    | just((_, se)) -> se.elementShared
    | nothing() -> false
    end
  end;

-- name of the nonterminal type of the tree of a vertex type in a production,
-- or "" if this isn't known (e.g. for an anonymous decoration site)
fun vertexTypeName String ::= prodName::String  vt::VertexType  realEnv::Env =
  case vt of
  | forwardVertexType() when getValueDcl(prodName, realEnv) matches prdDcl :: _ ->
    prdDcl.namedSignature.outputElement.typerep.typeName
  | rhsVertexType(sigName) when getValueDcl(prodName, realEnv) matches prdDcl :: _ ->
    lookupSignatureInputElem(sigName, prdDcl.namedSignature).typerep.typeName
  | localVertexType(fName) when getValueDcl(fName, realEnv) matches dcl :: _ -> dcl.typeScheme.typeName
  -- The type of a translation attribute (of a translation attribute, and so on) is that of its occurrence
  | transAttrVertexType(v, transAttr)
      when getOccursDcl(transAttr, vertexTypeName(prodName, v, realEnv), realEnv) matches occDcl :: _ ->
    occDcl.attrTypeName
  | _ -> ""
  end;

-- inherited equation for some arbitrary vertex type
-- (note that inh is just an inherited attribute, not trans.inh)
fun vertexHasInhEq Boolean ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv =
  case vt of
  | rhsVertexType(sigName) -> !null(lookupInh(prodName, sigName, attrName, flowEnv))
  -- A forward production attribute gets from the LHS each inherited attribute it has no equation
  -- for, except those on its translation attributes.
  | localVertexType(fName) when isForwardProdAttr(prodName, fName, flowEnv) && !splitTransAttrInh(attrName).isJust ->
    true
  | localVertexType(fName) -> !null(lookupLocalInh(prodName, fName, attrName, flowEnv))
  -- The forward gets every inherited attribute from the LHS, those on its translation attributes
  -- too: a forwarding production takes its translation attributes from its forward (see
  -- FwdTransAttr.sv).
  | forwardVertexType() -> true
  -- Note that we only support inh equations on trans attrs directly on a child/local,
  -- and not chained trans attrs.
  | transAttrVertexType(rhsVertexType(sigName), transAttr) ->
    !null(lookupInh(prodName, sigName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(localVertexType(fName), transAttr) ->
    !null(lookupLocalInh(prodName, fName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(_, _) -> false
  | anonVertexType(_, _, _) -> !null(lookupLocalInh(prodName, vt.vertexName, attrName, flowEnv))
  | anonScrutineeVertexType(_, _, _) -> false
  | subtermVertexType(_, remoteProdName, sigName) ->
    vertexHasInhEq(remoteProdName, rhsVertexType(sigName), attrName, flowEnv)
  -- This is a tricky case since we don't know what decorated this prod.
  -- We can count on missing LHS inh eqs being caught as flow issues elsewhere,
  -- but here we are remotely looking for equations that might not be the direct dependency of
  -- anything in the prod flow graph.
  | lhsVertexType() -> false  -- Shouldn't ever be directly needed, since the LHS is never the dec site for another vertex.
  | forwardParentVertexType() -> false  -- Same as LHS - the thing that forwarded to us.
  end;

{--
 - Does the tree vt arrive at the production with signature ns with inherited attributes that the
 - production's graph does not define?  A child shared through the signature does, from its
 - applications, and so may a nonterminal child at a position of the dispatch signature implSig that
 - the production implements, even where implSig does not share it (see
 - implementedSigStitchPoints).  So may the translation attributes of these, and a local shared from
 - any of them (local y = @a), and so on.  Any other child gets them from the production itself: an
 - override where the production is applied is bounded by its own equations (see pathBound).
 -}
fun arrivesWithInhs Boolean ::= ns::NamedSignature  implSig::Maybe<NamedSignature>  flowEnv::FlowEnv  vt::VertexType =
  arrivesWithInhsSeen([], ns, implSig, flowEnv, vt);
function arrivesWithInhsSeen
Boolean ::= seen::[VertexType]  ns::NamedSignature  implSig::Maybe<NamedSignature>  flowEnv::FlowEnv  vt::VertexType
{
  local root::VertexType = transRootVertex(vt);
  return
    case root of
    | rhsVertexType(sigName) ->
      lookupSignatureInputElem(sigName, ns).elementShared ||
      case implSig of
      | just(dispatch) ->
        positionOf(sigName, ns.inputNames) < length(dispatch.inputElements) &&
        lookupSignatureInputElem(sigName, ns).typerep.isNonterminal
      | nothing() -> false
      end
    | localVertexType(_) when !contains(root, seen) ->
      any(map(
        arrivesWithInhsSeen(root :: seen, ns, implSig, flowEnv, _),
        map(fst, lookupSharedInto(ns.fullName, root, flowEnv))))
    | _ -> false
    end;
}

{--
 - Does the tree at v in prod carry inherited attributes supplied before prod shares it, beyond v's
 - own equations?  It does if it arrives with inherited attributes supplied elsewhere (see
 - arrivesWithInhs), or if it is at a local that a tree was shared into.  Then the inherited
 - vertices of the decoration sites where it is shared reach v's (see addDecSiteTreeEqs).
 -}
fun carriesEarlierInhs Boolean ::= prod::String  v::VertexType  flowEnv::FlowEnv  realEnv::Env =
  case transRootVertex(v), getValueDcl(prod, realEnv) of
  | localVertexType(fName), _ -> !null(lookupSharedInto(prod, localVertexType(fName), flowEnv))
  | _, dcl :: _ -> arrivesWithInhs(dcl.namedSignature, dcl.implementedSignature, flowEnv, v)
  | rhsVertexType(sigName), [] -> sigSharedChild(prod, sigName, flowEnv, realEnv)
  | _, [] -> false
  end;

-- May the tree at v in prod have attr by the time prod shares it: from an equation on v, or as a
-- tree that carries inherited attributes supplied earlier (see carriesEarlierInhs)?
fun mayHaveInhBeforeSharing Boolean ::= prod::String  v::VertexType  attr::String  flowEnv::FlowEnv  realEnv::Env =
  vertexHasInhEq(prod, v, attr, flowEnv) || carriesEarlierInhs(prod, v, flowEnv, realEnv);

-- used for duplicate equations checks
-- (note that inh is just an inherited attribute, not trans.inh)
fun countVertexEqs Integer ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv  realEnv::Env =
  case vt of
  | rhsVertexType(sigName) ->
      length(lookupInh(prodName, sigName, attrName, flowEnv)) +
      -- Every sharing site supplies the attribute before this production gets the tree
      (if !null(lookupAllSigShareSites(prodName, sigName, flowEnv, realEnv)) &&
          sigShareSitesHaveInhEq(prodName, sigName, attrName, flowEnv, realEnv)
       then 1 else 0)
  | localVertexType(fName) -> length(lookupLocalInh(prodName, fName, attrName, flowEnv))
  | transAttrVertexType(rhsVertexType(sigName), transAttr) ->
      length(lookupInh(prodName, sigName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(localVertexType(fName), transAttr) ->
      length(lookupLocalInh(prodName, fName, s"${transAttr}.${attrName}", flowEnv))
  | transAttrVertexType(_, _) -> 0
  | anonVertexType(_, _, _) -> length(lookupLocalInh(prodName, vt.vertexName, attrName, flowEnv))
  | anonScrutineeVertexType(_, _, _) -> 0
  | subtermVertexType(_, remoteProdName, sigName) -> 0
  | lhsVertexType() -> length(lookupSyn(prodName, attrName, flowEnv))
  | forwardParentVertexType() -> 0
  | forwardVertexType() -> length(lookupFwdInh(prodName, attrName, flowEnv))
  end;

-- Check if a production attribute is a forward production attribute.
-- Note this expects the local env for the production!
-- If looking up in another prod, need to get the prod attr defs for the prod.
fun isForwardProdAttr Boolean ::= prod::String  fName::String  e::FlowEnv =
  case lookupLocalEq(prod, fName, e) of
  | localEq(_, _, _, isFwrd, _) :: _ -> isFwrd
  | _ -> false
  end;

-- Is vt a forward production attribute of prod?
fun isFwdProdAttrVertex Boolean ::= prod::String  vt::VertexType  e::FlowEnv =
  case vt of
  | localVertexType(fName) -> isForwardProdAttr(prod, fName, e)
  | _ -> false
  end;

-- vt with root in place of transRootVertex(vt): the same translation attribute (of a translation
-- attribute, and so on) of the tree at root.
fun rerootVertexType VertexType ::= vt::VertexType  root::VertexType =
  case vt of
  | transAttrVertexType(v, transAttr) -> transAttrVertexType(rerootVertexType(v, root), transAttr)
  | _ -> root
  end;

fun getAnonScrutineeRefSet [String] ::= prod::String  fName::String  e::FlowEnv =
  case lookupLocalEq(prod, fName, e) of
  | anonScrutineeEq(_, _, _, _, refSet, _, _, _) :: _ -> refSet
  | _ -> []
  end;

-- default set of inherited attributes required/assumed to exist for references
fun getInhsForNtRef [[String]] ::= nt::String  e::FlowEnv = searchEnvTree(nt, e.refTree);

-- implicit forward syn copy equations that are allowed to affect the flow type
fun getNonSuspectAttrsForProd [String] ::= prod::String  e::FlowEnv =
  concat(searchEnvTree(prod, e.nonSuspectTree));

-- all (non-forwarding) productions constructing a nonterminal
fun getNonforwardingProds [String] ::= nt::String  e::FlowEnv =
  searchEnvTree(nt, e.prodTree);

-- all host productions implementing a dispatch signature, along with their input sig names
fun getImplementingProds [(String, [String])] ::= dispatchSig::String e::FlowEnv =
  searchEnvTree(dispatchSig, e.implTree);

{--
 - Does every host-language application of a production/dispatch signature supply an inherited
 - attribute to its child shared through the signature?  For an application passing on the applying
 - production's own child, what that child has counts.
 -}
fun sigShareSitesHaveInhEq Boolean ::= prod::String  sigName::String  attr::String  e::FlowEnv  realEnv::Env =
  resolveBeforeSharing(
    attr,
    viaProdVertexDec(prod, rhsVertexType(sigName),
      sigShareSitesDec([], true, prod, sigName, e, realEnv)),
    e)
    == alwaysDec();

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
  local maxInhSetMembers::(Maybe<[String]>, [TyVar]) = getMaxInhSetMembers([], ^inhs, top.env);
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
