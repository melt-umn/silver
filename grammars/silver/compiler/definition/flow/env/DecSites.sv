grammar silver:compiler:definition:flow:env;

import silver:util:treemap as map;
import silver:util:treeset as set;
import silver:compiler:definition:flow:driver;

{--
 - Generate a decision tree to determine all decoration sites where an inherited equation could be supplied
 - for it to be available on some vertex type.
 - This is used in checking for inherited completeness.
 -
 - @param prodName The name of the production containing the vertex type.
 - @param vt The vertex type to find decoration sites for.
 - @param flowEnv The flow environment.
 - @param realEnv The regular environment.
 - @return A decision tree to determine if an inherited attributes has been supplied for vt.
 -}
fun findDecSites
DecSiteTree ::= prodName::String vt::VertexType flowEnv::FlowEnv realEnv::Env =
  findDecSitesChain([], false, prodName, vt, flowEnv, realEnv);

{--
 - findDecSites, where chain holds the trees that led to vt by steps from a tree to a tree it was
 - shared from (a tree shared into its decoration site, or the tree at one of its sharing sites),
 - and the dispatch signatures' children whose sharing sites were checked on the way.  A cycle
 - through these alone counts as supplied: every tree on it also arrives through a sharing site
 - outside the cycle, which is checked on its own.  With beforeSharing, only what the tree has by
 - the time it is shared: the decoration sites at which it is shared supply only what it lacks then,
 - so they are left out, as are projections (see resolveBeforeSharing).
 -}
function findDecSitesChain
DecSiteTree ::=
  chain::[(String, VertexType)]  beforeSharing::Boolean  prodName::String vt::VertexType flowEnv::FlowEnv
  realEnv::Env
{
  local recurse::(DecSiteTree ::= String VertexType) =
    findDecSitesChain([], beforeSharing, _, _, flowEnv, realEnv);
  -- The chain for the trees that this tree was shared from
  local here::[(String, VertexType)] = (prodName, vt) :: chain;

  local ntName::String = vertexTypeName(prodName, vt, realEnv);

  -- Decoration sites where translation attributes of the tree (of translation attributes, and so on)
  -- are shared, for the inherited attributes on these translation attributes.
  -- The nonterminals seen so far bound this in the presence of a cycle in translation attribute occurrences.
  local transAttrShareDecSites::([DecSiteTree] ::= [String] String VertexType) =
    \ seenNts::[String] nt::String transBase::VertexType ->
      if contains(nt, seenNts) then []
      else flatMap(
        \ occDcl::OccursDclInfo ->
          case getAttrDcl(occDcl.attrOccurring, realEnv) of
          | dcl :: _ when dcl.isTranslation ->
            let transVt::VertexType = transAttrVertexType(transBase, occDcl.attrOccurring)
            in map(transAttrDec(occDcl.attrOccurring, _),
                map(recurse(prodName, _), lookupRefDecSite(prodName, transVt, flowEnv)) ++
                transAttrShareDecSites(nt :: seenNts, occDcl.attrTypeName, transVt))
            end
          | _ -> []
          end,
        getAttrOccursOn(nt, realEnv));

  -- The tree at the root of vt.  Where it is a forward production attribute y, it already has this
  -- production as its forward parent: the decoration sites at which y itself is shared supply
  -- nothing to it (see addSharingEqs).
  local rootVt::VertexType = transRootVertex(vt);
  local rootIsFwdProdAttr::Boolean = isFwdProdAttrVertex(prodName, rootVt, flowEnv);

  nondecorated local own::DecSiteTree =
    viaProdVertexDec(prodName, vt,
      (if vt.isInhDefVertex
       -- Direct inherited equation at a decoration site
       then directDec(prodName, vt)
       else if vt.isFlowTypeDepVertex
       -- Tracked via a flow type, don't need to check here
       then alwaysDec()
       else neverDec()) +
      case vt of
      -- Before sharing, only a pattern variable shared in error reaches a subterm, and the
      -- subterm's projection would need production graphs
      | subtermVertexType(_, _, _) when beforeSharing -> neverDec()
      -- Via projected remote equation
      | subtermVertexType(parent, prodOrSig, sigName) ->
         (if !null(getValueDcl(prodOrSig, realEnv))
          -- Projected from a production
          then recurse(prodOrSig, rhsVertexType(sigName))
          -- Projected from a dispatch signature: what every host-language implementation supplies.
          -- An implementation in an extension must forward to an application of the dispatch signature
          -- with the same shared children (see OrphanedProduction.sv).
          -- So a chain of such forwards eventually reaches a host-language implementation.
          else
            case getTypeDcl(prodOrSig, realEnv) of
            | sigDcl :: _ -> 
              viaProdVertexDec(
                prodOrSig, rhsVertexType(sigName),
                product(map(\ prod::(String, [String]) ->
                  case drop(positionOf(sigName, sigDcl.dispatchSignature.inputNames), prod.2) of
                  | sn :: _ ->
                    -- An implementation that dispatches again with the same child relies on what the other
                    -- implementations supply.
                    if any(map(isDispatchSite(prodOrSig, sigName, _), lookupAllRefDecSites(prod.1, rhsVertexType(sn), flowEnv)))
                    then alwaysDec()
                    else recurse(prod.1, rhsVertexType(sn))
                  | _ -> error(s"findDecSites: Couldn't resolve ${sigName} in ${prodOrSig}")
                  end,
                -- Look at all the (host) productions that implement this dispatch signature
                getImplementingProds(prodOrSig, flowEnv))))
            -- TODO: This could be a production in a grammar that isn't in scope in the local environment,
            -- e.g. in a modification, that was missed in the above getValueDcl(prodOrSig, realEnv).
            -- We really should be using the global env here.
            | _ -> hiddenProdDec(prodOrSig, rhsVertexType(sigName))
            end) *
          subtermProjectedDepsDec(prodName, parent, prodOrSig, sigName, flowEnv, realEnv)
      -- Via the reference set of a pattern match scrutinee
      | anonScrutineeVertexType(_, grammarName, l) ->
        anonScrutineeRefSetDec(getAnonScrutineeRefSet(prodName, vt.vertexName, flowEnv), grammarName, l)
      -- Via signature/dispatch sharing, at every host-language application (see sigShareSite)
      | rhsVertexType(sigName) when sigSharedChild(prodName, sigName, flowEnv, realEnv) ->
        sigShareSitesDec(chain, beforeSharing, prodName, sigName, flowEnv, realEnv)
      | _ -> neverDec()
      end +
      -- Via direct sharing, of this tree or of a tree that it is a translation attribute of,
      -- leaving out the decoration sites at which rootVt is shared where it is a forward production
      -- attribute
      (if beforeSharing then neverDec()
       else sum(map(recurse(prodName, _),
         removeAll(
           if rootIsFwdProdAttr then map(rerootVertexType(vt, _), lookupRefDecSite(prodName, rootVt, flowEnv)) else [],
           lookupAllRefDecSites(prodName, vt, flowEnv))))) +
      -- Via a tree unconditionally shared at this decoration site, which was decorated first and
      -- keeps its equations.  Not at a subterm: the tree shared there is the source vertex, already
      -- resolved on its own path.
      (case vt of
       | subtermVertexType(_, _, _) -> neverDec()
       | _ ->
         sum(map(
           findDecSitesChain(here, beforeSharing, prodName, _, flowEnv, realEnv),
           lookupSharedIntoAlways(prodName, vt, flowEnv)))
       end) +
      -- Via translation attribute sharing
      (if beforeSharing then neverDec() else sum(transAttrShareDecSites([], ntName, vt))) +
      -- Via the tree that this is a translation attribute of
      case vt of
      | transAttrVertexType(treeVertex, transAttr) when suppliesTransAttrInhs(treeVertex) ->
        transAttrOfDec(transAttr, recurse(prodName, treeVertex))
      | _ -> neverDec()
      end);

  return if contains((prodName, vt), chain) then alwaysDec() else own;
}

-- The projection for the subterm sigName of prodOrSig at parent in prodName.  It comes from the
-- tree at parent or, for a pattern variable, from the tree that the match reaches prodOrSig from
-- (see matchTarget), through forwards if the match may follow them (see matchThroughForwards).
function subtermProjectedDepsDec
DecSiteTree ::=
  prodName::String  parent::VertexType  prodOrSig::String  sigName::String  flowEnv::FlowEnv  realEnv::Env
{
  local defs :: [FlowDef] = getGraphContribsFor(prodName, flowEnv);
  local target :: VertexType = matchTarget(prodName, prodOrSig, parent, !null(lookupFwd(prodName, flowEnv)));
  local isPatternVar :: Boolean =
    any(map(
      \ d::FlowDef ->
        case d of
        | patternRuleEq(_, matchProd, scrutinee, vars) ->
          matchProd == prodOrSig && scrutinee == parent &&
          any(map(\ v::PatternVarProjection -> case v of patternVarProjection(c, _) -> c == sigName end, vars))
        | _ -> false
        end,
      defs));
  return
    if isPatternVar
    then
      projectedDepsDec(
        prodOrSig, sigName, matchThroughForwards(realEnv, prodOrSig),
        findDecSites(prodName, target, flowEnv, realEnv))
    else projectedDepsDec(prodOrSig, sigName, nothing(), findDecSites(prodName, parent, flowEnv, realEnv));
}

-- What the sharing sites of the child sigName of prodName supply to the tree shared there:
-- applications of prodName, and, where prodName implements a dispatch signature, applications of
-- the signature.  chain and beforeSharing are as in findDecSitesChain.
function sigShareSitesDec
DecSiteTree ::=
  chain::[(String, VertexType)]  beforeSharing::Boolean  prodName::String  sigName::String  flowEnv::FlowEnv
  realEnv::Env
{
  local here::[(String, VertexType)] = (prodName, rhsVertexType(sigName)) :: chain;
  local sharingSitesDec :: (DecSiteTree ::= [(String, VertexType)] String String) =
    \ c::[(String, VertexType)] p::String sn::String ->
      product(unzipWith(
        findDecSitesChain(c, beforeSharing, _, _, flowEnv, realEnv), lookupSigShareSites(p, sn, flowEnv)));
  return
    -- places where this child was decorated in a production forwarding to this one,
    sharingSitesDec(here, prodName, sigName) *
    -- or in a dispatch signature that this production implements
    case implementedSigChild(prodName, sigName, flowEnv, realEnv) of
    -- Every implementation that passes its child on to the dispatch signature has these sharing
    -- sites, so they are checked once.
    | just((dispatch, se)) when contains((dispatch, rhsVertexType(se.elementName)), here) -> alwaysDec()
    | just((dispatch, se)) ->
      sharingSitesDec((dispatch, rhsVertexType(se.elementName)) :: here, dispatch, se.elementName)
    | nothing() -> alwaysDec()
    end;
}

{--
 - Can the inherited attributes of a translation attribute t of the tree at vt be supplied to that
 - tree (as t.inh), rather than to t directly?  They can for a translation attribute, for the
 - forward, whose translation attributes are the production's own (see FwdTransAttr.sv), and for a
 - subterm, as a production application may supply them for its child.  The translation attributes
 - of a child, local or the LHS are only supplied directly or by sharing.
 -}
fun suppliesTransAttrInhs Boolean ::= vt::VertexType =
  case vt of
  | subtermVertexType(_, _, _) -> true
  | transAttrVertexType(_, _) -> true
  | forwardVertexType() -> true
  | _ -> false
  end;

-- Is the decoration site vt of a shared tree the child sigName of an application of the dispatch signature?
fun isDispatchSite Boolean ::= dispatch::String  sigName::String  vt::VertexType =
  case vt of
  | subtermVertexType(_, d, sn) -> d == dispatch && sn == sigName
  | _ -> false
  end;

{--
 - The state used in finding possible decoration sites.
 - We track the (prod, vertex) and (dispatch sig, rhs name) pairs already visited
 - to avoid O(n^2) blowup due to revisiting the same productions in different
 - branches of the resolution tree.
 -}
type PDSState = ([(String, VertexType)], [(String, String)]);

{--
 - Generate a decision tree to determine all decoration sites where an inherited attribute
 - might be supplied to some vertex type.
 - This is used in checking for potentially hidden transitive dependencies.
 - This mirrors the above, but we also consider sites where a tree is only conditionally shared.
 - Since we only care if a vertex is *possibly* supplied with an attribute, we can memoize the
 - vertices visited in the entire search (using a State monad) rather than just the current branch.
 - The search starts at a decoration site.  It enters another production only through an application that shares the
 - tree as one of its children, and only that application decorated the tree.  So unlike findDecSites, it does not
 - follow a child shared through the signature out to the production's other applications.
 -
 - @param prodName The name of the production containing the vertex type.
 - @param vt The decoration site to start from.
 - @param flowEnv The flow environment.
 - @param realEnv The regular environment.
 - @return A decision tree to determine if an inherited attributes could possibly be supplied for vt.
 -}
function findPossibleDecSites
State<PDSState DecSiteTree> ::=
  prodName::String vt::VertexType
  flowEnv::FlowEnv realEnv::Env
{
  local ntName::String = vertexTypeName(prodName, vt, realEnv);

  local recurse::(State<PDSState DecSiteTree> ::= String VertexType) =
    findPossibleDecSites(_, _, flowEnv, realEnv);

  -- As in findDecSites, but for all places where translation attributes of the tree are possibly shared.
  local transAttrSharePossibleDecSites::(State<PDSState [DecSiteTree]> ::= [String] String VertexType) =
    \ seenNts::[String] nt::String transBase::VertexType ->
      if contains(nt, seenNts) then pure([])
      else map(concat, traverseA(
        \ occDcl::OccursDclInfo ->
          case getAttrDcl(occDcl.attrOccurring, realEnv) of
          | dcl :: _ when dcl.isTranslation ->
            let transVt::VertexType = transAttrVertexType(transBase, occDcl.attrOccurring)
            in do {
              viaShare :: [DecSiteTree] <-
                traverseA(recurse(prodName, _), lookupRefPossibleDecSites(prodName, transVt, flowEnv));
              viaNestedShare :: [DecSiteTree] <-
                transAttrSharePossibleDecSites(nt :: seenNts, occDcl.attrTypeName, transVt);
              return map(transAttrDec(occDcl.attrOccurring, _), viaShare ++ viaNestedShare);
            }
            end
          | _ -> pure([])
          end,
        getAttrOccursOn(nt, realEnv)));

  return do {
    seen :: PDSState <- getState();
    if contains((prodName, vt), seen.1)
    then pure(neverDec())
    else do {
      setState(((prodName, vt) :: seen.1, seen.2));
      viaVertex :: DecSiteTree <-
        case vt of
        -- Via projected remote equation
        | subtermVertexType(parent, prodOrSig, sigName) -> map(
            -- Transitive dependencies of an attribute on the projection must be supplied
            -- for the attribute on the projection to be depended upon.
            bothDec(_,
              projectedDepsDec(prodOrSig, sigName, nothing(), findDecSites(prodName, parent, flowEnv, realEnv))),
            if !null(getValueDcl(prodOrSig, realEnv))
            -- Projected from a production
            then recurse(prodOrSig, rhsVertexType(sigName))
            -- Projected from a dispatch signature
            else if contains((prodOrSig, sigName), seen.2)
            -- This is a dispatch that we have already tried to resolve.
            then pure(neverDec())
            -- Otherwise, look at all the (host) productions that implement this dispatch signature
            else 
              case getTypeDcl(prodOrSig, realEnv) of
              | sigDcl :: _ -> map(sum, traverseA(
                \ prod::(String, [String]) ->
                  case drop(positionOf(sigName, sigDcl.dispatchSignature.inputNames), prod.2) of
                  | sn :: _ -> do {
                      modifyState(\ seen::PDSState -> (seen.1, (prod.1, sn) :: seen.2));
                      recurse(prod.1, rhsVertexType(sn));
                    }
                  | _ -> error(s"findPossibleDecSites: Couldn't resolve ${sigName} in ${prodOrSig}")
                  end,
                getImplementingProds(prodOrSig, flowEnv)))
              -- TODO: This could be a production in a grammar that isn't in scope in the local environment,
              -- e.g. in a modification, that was missed in the above getValueDcl(prodOrSig, realEnv).
              -- We really should be using the global env here.
              | _ -> pure(alwaysDec())
              end)
        -- Via the reference set of a pattern match scrutinee
        | anonScrutineeVertexType(_, grammarName, l) ->
          pure(anonScrutineeRefSetDec(getAnonScrutineeRefSet(prodName, vt.vertexName, flowEnv), grammarName, l))
        | _ -> pure(neverDec())
        end;
      viaDirectShare :: [DecSiteTree] <-
        traverseA(recurse(prodName, _), lookupAllRefPossibleDecSites(prodName, vt, flowEnv));
      viaTransAttrShare :: [DecSiteTree] <- transAttrSharePossibleDecSites([], ntName, vt);
      viaTree :: DecSiteTree <-
        case vt of
        | transAttrVertexType(treeVertex, transAttr) when suppliesTransAttrInhs(treeVertex) ->
          map(transAttrOfDec(transAttr, _), recurse(prodName, treeVertex))
        | _ -> pure(neverDec())
        end;
      return
       (if vt.isInhDefVertex
        -- Direct inherited equation at a decoration site
        then directDec(prodName, vt)
        else if vt.isFlowTypeDepVertex
        -- May be supplied non-locally
        then alwaysDec()
        else neverDec()) +
        viaVertex + sum(viaDirectShare) + sum(viaTransAttrShare) + viaTree;
    };
  };
}

-- Flatten a resolved decision tree, to determine the minimal places where an
-- equation is needed.
partial strategy attribute reduceDecSiteStep =
  rule on DecSiteTree of
  | altDec(alwaysDec(), d) -> alwaysDec()
  | altDec(d, alwaysDec()) -> alwaysDec()
  | altDec(neverDec(), d) -> ^d
  | altDec(d, neverDec()) -> ^d
  | bothDec(alwaysDec(), d) -> ^d
  | bothDec(d, alwaysDec()) -> ^d
  | bothDec(neverDec(), _) -> neverDec()
  | bothDec(_, neverDec()) -> neverDec()
  | altDec(altDec(d1, d2), d3) -> altDec(^d1, altDec(^d2, ^d3))
  | bothDec(bothDec(d1, d2), d3) -> bothDec(^d1, bothDec(^d2, ^d3))
  | depAttrDec(_, alwaysDec()) -> alwaysDec()
  | depAttrDec(_, neverDec()) -> neverDec()
  end occurs on DecSiteTree;

-- The inherited attribute for which we are trying to resolve the decision tree
inherited attribute attrToResolve::String occurs on DecSiteTree;
propagate attrToResolve on DecSiteTree excluding depAttrDec, projectedDepsDec, transAttrDec, transAttrOfDec;
aspect production depAttrDec
top::DecSiteTree ::= attrName::String d::DecSiteTree
{
  d.attrToResolve = attrName;
}

-- The set of (prod, vertex, inh) that have been seen so far in this branch of the tree
inherited attribute seenProdVertexAttrs::set:Set<(String, VertexType, String)> occurs on DecSiteTree;
propagate seenProdVertexAttrs on DecSiteTree excluding viaProdVertexDec;
aspect production viaProdVertexDec
top::DecSiteTree ::= prodName::String vt::VertexType d::DecSiteTree
{
  d.seenProdVertexAttrs = set:add([(prodName, vt, top.attrToResolve)], top.seenProdVertexAttrs);
}

partial strategy attribute elimCycleDecSiteStep =
  rule on DecSiteTree of
  | viaProdVertexDec(prodName, vt, _)
      when set:contains((prodName, vt, top.attrToResolve), top.seenProdVertexAttrs) ->
      -- This is a cycle due to a missing equation somewhere.
      neverDec()
  | viaProdVertexDec(_, _, alwaysDec()) -> alwaysDec()
  | viaProdVertexDec(_, _, neverDec()) -> neverDec()
  end occurs on DecSiteTree;

attribute flowEnv, productionFlowGraphs, grammarFlowTypes occurs on DecSiteTree;

-- Resolve the decision tree for a particular attribute, replacing decoration
-- sites known to be supplied with alwaysDec().
partial strategy attribute lookupDecSiteStep =
  rule on top::DecSiteTree of
  | directDec(prodName, vt)
        when vertexHasInhEq(prodName, vt, top.attrToResolve, top.flowEnv) ->
      alwaysDec()
  -- This is safe as the tree is traversed top-down, so the current attrToResolve is the final one.
  | depAttrDec(attrName, d) when top.attrToResolve == attrName -> ^d
  | projectedDepsDec(prodName, sigName, throughForwards, d) ->
      product(map(depAttrDec(_, ^d),
        projectedLhsDeps(
          findProductionGraph(prodName, top.productionFlowGraphs), rhsVertexType(sigName), top.attrToResolve,
          throughForwards, top.grammarFlowTypes)))
  | anonScrutineeRefSetDec(refSet, _, _) when contains(top.attrToResolve, refSet) ->
      alwaysDec()
  | transAttrDec(attrName, d) ->
      case splitTransAttrInh(top.attrToResolve) of
      | just((transAttr, inhAttr)) when transAttr == attrName -> depAttrDec(inhAttr, ^d)
      | _ -> neverDec()
      end
  | transAttrOfDec(attrName, d) -> depAttrDec(s"${attrName}.${top.attrToResolve}", ^d)
  end occurs on DecSiteTree;

partial strategy attribute resolveDecSiteStep =
  --rule on DecSiteTree of
  --| ds -> unsafeTracePrint(^ds, ds.attrToResolve ++ " on " ++ ds.dbgPP ++ "\n\n")
  --end <*
  elimCycleDecSiteStep <+ lookupDecSiteStep <+ reduceDecSiteStep <+
  -- Short-circuit alternatives to potentially avoid building the entire tree
  altDec(resolveDecSiteStep, id) <+
  some(resolveDecSiteStep)
  occurs on DecSiteTree;

-- Remove redundant subtrees in the resolved decision tree.
partial strategy attribute elimRedundantDecSiteStep =
  rule on DecSiteTree of
  | viaProdVertexDec(_, _, d) -> ^d
  | altDec(d1, d2) when contains(^d1, d2.decSiteAlts) -> ^d2
  | bothDec(d1, d2) when contains(^d1, d2.decSiteReqs) -> ^d2
  end occurs on DecSiteTree;

partial strategy attribute cleanupDecSiteStep =
  someTopDown(reduceDecSiteStep <+ elimRedundantDecSiteStep)
  occurs on DecSiteTree;

strategy attribute resolveDecSite =
  repeat(resolveDecSiteStep) <* repeat(cleanupDecSiteStep)
  occurs on DecSiteTree;

propagate
  flowEnv, productionFlowGraphs, grammarFlowTypes,
  reduceDecSiteStep, elimRedundantDecSiteStep, elimCycleDecSiteStep,
  lookupDecSiteStep, resolveDecSiteStep, cleanupDecSiteStep, resolveDecSite
  on DecSiteTree;

{--
  - Determine if some decoration site has some inherited attribute supplied.
  -
  - @param d The decoration site to check.
  - @param attrName The name of the inherited attribute.
  - @param prodGraphs The final production flow graphs.
  - @param flowTypes The final flow types.
  - @param flowEnv The flow environment.
  - @return alwaysDec(), if the attribute is always present,
  - or else the places where it could be supplied.
  -}
function resolveDecSiteInhEq
DecSiteTree ::=
  attrName::String d::DecSiteTree prodGraphs::EnvTree<ProductionGraph> flowTypes::EnvTree<FlowType> flowEnv::FlowEnv
{
  d.attrToResolve = attrName;
  d.productionFlowGraphs = prodGraphs;
  d.grammarFlowTypes = flowTypes;
  d.flowEnv = flowEnv;
  d.seenProdVertexAttrs = set:empty();
  d.maxDepth = 20;
  --return unsafeTracePrint(d.resolveDecSite, s"====== resolveDecSiteInhEq for ${attrName} on ${d.dbgPP} ======\n\n");
  return d.resolveDecSite;
}

{--
  - Determine if some flow vertex type in a production has some inherited attribute supplied.
  -
  - @param prodName The name of the production containing the vertex.
  - @param vt The vertex type to check.
  - @param attrName The name of the inherited attribute.
  - @param prodGraphs The final production flow graphs.
  - @param flowTypes The final flow types.
  - @param flowEnv The flow environment.
  - @param realEnv The regular environment.
  - @return alwaysDec(), if the attribute is always present,
  - or else the places where it could be supplied.
  -}
fun resolveInhEq
DecSiteTree ::=
    prodName::String vt::VertexType attrName::String
    prodGraphs::EnvTree<ProductionGraph> flowTypes::EnvTree<FlowType> flowEnv::FlowEnv realEnv::Env =
  resolveDecSiteInhEq(attrName, findDecSites(prodName, vt, flowEnv, realEnv), prodGraphs, flowTypes, flowEnv);

{--
 - Determine if a decoration site for some vertex has an inherited attribute supplied.
 - 
 - @param prodName The name of the production containing the vertex.
 - @param vt The vertex type to check.
 - @param attrName The name of the inherited attribute.
 - @param prodGraphs The final production flow graphs.
 - @param flowTypes The final flow types.
 - @param flowEnv The flow environment.
 - @param realEnv The regular environment.
 - @return true if the vertex is guaranteed to be supplied with the attribute.
 -}
fun decSiteHasInhEq
Boolean ::=
    prodName::String vt::VertexType attrName::String
    prodGraphs::EnvTree<ProductionGraph> flowTypes::EnvTree<FlowType> flowEnv::FlowEnv realEnv::Env =
  resolveInhEq(prodName, vt, attrName, prodGraphs, flowTypes, flowEnv, realEnv) == alwaysDec();

-- Resolves attrName in d, which findDecSitesChain or sigShareSitesDec found with beforeSharing:
-- alwaysDec() if the tree that d was found for has attrName by the time it is shared.  d then has
-- no projections, so resolving it needs no production graphs or flow types and can run while the
-- production graphs are being constructed.
fun resolveBeforeSharing DecSiteTree ::= attrName::String  d::DecSiteTree  flowEnv::FlowEnv =
  resolveDecSiteInhEq(
    attrName, d, error("What a tree has before it is shared is resolved without production graphs"),
    error("What a tree has before it is shared is resolved without flow types"), flowEnv);

-- Does the tree at vt in prodName have attrName by the time prodName shares it (see
-- resolveBeforeSharing)?
fun hasInhBeforeSharing
Boolean ::= prodName::String  vt::VertexType  attrName::String  flowEnv::FlowEnv  realEnv::Env =
  resolveBeforeSharing(attrName, findDecSitesChain([], true, prodName, vt, flowEnv, realEnv), flowEnv) == alwaysDec();

-- Helper for checking multiple inh attributes
function decSitesMissingInhEqs
[(DecSiteTree, [String])] ::=
  prodName::String vt::VertexType attrNames::[String]
  prodGraphs::EnvTree<ProductionGraph> flowTypes::EnvTree<FlowType> flowEnv::FlowEnv realEnv::Env
{
  nondecorated local d::DecSiteTree = findDecSites(prodName, vt, flowEnv, realEnv);
  local resolved::map:Map<DecSiteTree String> =
    map:add(map(\ a -> (resolveDecSiteInhEq(a, d, prodGraphs, flowTypes, flowEnv), a), attrNames), map:empty());
  return flatMap(\ d -> 
    case map:lookup(d, resolved) of
    | [] -> []
    | missing -> [(d, missing)]
    end,
    remove(alwaysDec(), map:keys(resolved)));
}

{--
 - Determine if an inherited attribute for some vertex could possibly be demanded somewhere.
 - 
 - @param prodName The name of the production containing the vertex.
 - @param vt The vertex type to check.
 - @param attrName The name of the inherited attribute.
 - @param prodGraphs The final production flow graphs.
 - @param flowTypes The final flow types.
 - @param flowEnv The flow environment.
 - @param realEnv The regular environment.
 - @return true if the vertex might ever be supplied with the attribute, false otherwise.
 -}
fun possibleDecSiteHasInhEq
Boolean ::=
    prodName::String vt::VertexType attrName::String
    prodGraphs::EnvTree<ProductionGraph> flowTypes::EnvTree<FlowType> flowEnv::FlowEnv realEnv::Env =
  resolveDecSiteInhEq(
    attrName, evalState(findPossibleDecSites(prodName, vt, flowEnv, realEnv), ([], [])), prodGraphs, flowTypes,
    flowEnv)
  == alwaysDec();
