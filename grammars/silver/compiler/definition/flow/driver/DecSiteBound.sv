grammar silver:compiler:definition:flow:driver;

{--
 - Bounds for an override equation for an attribute on a tree shared in its production, per path of
 - decoration sites (see overrideDecSiteInhs).  At run time the tree takes one path of decoration
 - sites, and the override hides the first equation for the attribute outside its production on that
 - path, whose consumers assume that equation's dependencies.  So the override may depend only on
 - what every path that reaches such an equation allows.
 -}

-- In the terms of the production a walk is in: its vertices (only LHS inherited vertices at the
-- overriding production); the inherited attributes of the overridden tree, named as on that tree,
-- that the hidden equations access; and the qualified inputs, the LHS inherited vertices of the
-- first set that every consumer of the hidden equation needs (see mapApplied).
type PathSets = (set:Set<FlowVertex>, set:Set<String>, set:Set<FlowVertex>);
-- PathSets for every path through a tree, or nothing() where no path reaches an equation that the
-- override hides; with the loop heads whose current values were used at cuts.
type PathResult = (Maybe<PathSets>, [LoopKey]);
-- A loop head: the production, vertex type and attribute, whether the level is covered, capped, or
-- capped through a dispatch signature, and its entry.
type LoopKey = (String, VertexType, String, Boolean, Boolean, Boolean, Maybe<FlowVertex>);
-- A parent walk: the production, its entry, the parent and the attribute.
type ParentKey = (String, Maybe<FlowVertex>, VertexType, String);

{--
 - What a bound is for: the overriding production's graphs (pcGraph, pcOverrideFree, see
 - overrideFreeGraph) and its override equations (see isOverrideEq); the overridden tree, attribute
 - and nonterminal; and whether the override records its dependencies at the tree's decoration sites
 - (see defLHSRecorded).
 -}
data PathCtx = pathCtx with pcGraph, pcOverrideFree, pcOverrides, pcTree, pcAttr, pcRecorded, pcTreeNt,
  pcProdGraphs, pcFlowTypes, pcFlowEnv, pcWarnFwd;

annotation pcGraph::ProductionGraph;
annotation pcOverrideFree::ProductionGraph;
annotation pcOverrides::[FlowDef];
annotation pcTree::VertexType;
annotation pcAttr::String;
annotation pcRecorded::Boolean;
annotation pcTreeNt::String;
annotation pcProdGraphs::EnvTree<ProductionGraph>;
annotation pcFlowTypes::EnvTree<FlowType>;
annotation pcFlowEnv::FlowEnv;
-- Whether every implementation that its dispatch signature's grammar does not export forwards to an
-- application of that dispatch signature with its own child (see OrphanedProduction.sv), so that
-- the implementations not known here qualify their inputs (see unknownImplSets).
annotation pcWarnFwd::Boolean;

{--
 - Where a walk is.
 - lvProd: the production the walk is in.
 - lvOnTree: the walked tree is the overridden tree, a translation attribute of it, or a tree that
 -   the overridden tree is a translation attribute of, and not the tree at the parent of an
 -   application.
 - lvHiding: the walk is for the overridden attribute, whose equations the override hides, and not
 -   for an attribute of the overridden tree that those equations access (see nameBounds), nor a
 -   parent walk (see parentBound).
 - lvCovered, lvCap: see enterLevel.  lvCapViaDispatchSig: the cap is through the dispatch signature
 -   (see implLevel).
 - lvTreeChild: lvProd's child holding the overridden tree, with the prefix that gives its
 -   attributes' names on that tree (see treeNaming).
 - lvTreeAttr: the overridden tree's attribute the walk is for.
 - lvEntry: the vertex where the walk entered lvProd, which leads only back to the hidden equation;
 -   nothing() at the overriding production (see atTop).
 -}
data PathLevel = pathLevel with lvProd, lvOnTree, lvHiding, lvCovered, lvCap, lvCapViaDispatchSig, lvTreeChild,
  lvTreeAttr, lvEntry;

annotation lvProd::String;
annotation lvOnTree::Boolean;
annotation lvHiding::Boolean;
annotation lvCovered::Boolean;
annotation lvCap::Maybe<(String, String, String)>;
annotation lvCapViaDispatchSig::Boolean;
annotation lvTreeChild::Maybe<(String, String)>;
annotation lvTreeAttr::String;
annotation lvEntry::Maybe<FlowVertex>;

-- How the equation at a vertex type bounds the paths through it (see equationRule).
data EqRule = eqLeaf found::PathSets | eqPass | eqCap cap::PathSets;

{--
 - A lower bound, for every path through t, of what the productions around the first equation for
 - attr on that path supply.  t is a tree of findDecSitePaths for the tree the walk follows at lvl,
 - and attr is that tree's attribute.  loops holds the current values of the loop heads being
 - solved, and pSeen the parent walks in progress.
 -}
fun pathBound
PathResult ::= ctx::PathCtx  lvl::PathLevel  attr::String  t::DecSitePaths  loops::[(LoopKey, Maybe<PathSets>)]
  pSeen::[ParentKey] =
  case t of
  | pathVertex(vt, steps) -> vertexBound(ctx, lvl, attr, vt, steps, loops, pSeen)
  | pathCut(vt) ->
    case lookup(loopKey(lvl, vt, attr), loops) of
    | just(current) -> (current, [loopKey(lvl, vt, attr)])
    -- Not a loop head being solved
    | nothing() -> (just(noSets), [])
    end
  end;

-- The paths past s, a step from a vertex type in lvl's production, for attr as named at that vertex
-- type.
fun stepBound
PathResult ::= ctx::PathCtx  lvl::PathLevel  attr::String  s::PathStep  loops::[(LoopKey, Maybe<PathSets>)]
  pSeen::[ParentKey] =
  case s of
  | localStep(rename, dest) ->
    case renameAttr(rename, attr) of
    | just(a) -> pathBound(ctx, lvl, a, dest, loops, pSeen)
    | nothing() -> (nothing(), [])
    end
  | appliedStep(parent, applied, sigName, into, parentTree) ->
    appliedBound(ctx, lvl, attr, parent, applied, sigName, into, parentTree, loops, pSeen)
  end;

-- At vt: the first equation for attr, or else the meet over the steps onward, with a loop through
-- vt solved to its greatest fixed point.  A loop head at the overriding production's own level is
-- not solved.
function vertexBound
PathResult ::= ctx::PathCtx  lvl::PathLevel  attr::String  vt::VertexType  onward::[PathStep]
  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey]
{
  local key::LoopKey = loopKey(lvl, vt, attr);
  local first::PathResult = onwardBound(ctx, lvl, attr, onward, key, nothing(), loops, pSeen);
  local solved::PathResult =
    if atTop(lvl) || !contains(key, first.2) then first
    else solveLoop(ctx, lvl, attr, onward, key, first.1, loops, pSeen, 0);
  return
    case equationRule(ctx, lvl, attr, vt) of
    | eqLeaf(found) -> (just(found), [])
    | eqPass() -> solved
    | eqCap(cap) -> (meetSets([just(cap), solved.1]), solved.2)
    end;
}

-- The meet over the steps onward from the vertex type of key, whose value as a loop head is
-- current.
fun onwardBound
PathResult ::= ctx::PathCtx  lvl::PathLevel  attr::String  onward::[PathStep]  key::LoopKey
  current::Maybe<PathSets>  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey] =
  meetResults(
    map(stepBound(ctx, lvl, attr, _, if atTop(lvl) then loops else (key, current) :: loops, pSeen), onward));

-- The loop head key, from its value current, iterated down to its greatest fixed point.  A head
-- past maxLoopRounds counts as empty.
function solveLoop
PathResult ::= ctx::PathCtx  lvl::PathLevel  attr::String  onward::[PathStep]  key::LoopKey  current::Maybe<PathSets>
  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey]  rounds::Integer
{
  local next::PathResult = onwardBound(ctx, lvl, attr, onward, key, current, loops, pSeen);
  return
    if next.1 == current then (next.1, remove(key, next.2))
    else if rounds >= maxLoopRounds then (just(noSets), remove(key, next.2))
    else solveLoop(ctx, lvl, attr, onward, key, next.1, loops, pSeen, rounds + 1);
}

{--
 - Whether the equation for attr at vt ends the paths through vt: as the first equation, with the
 - dependencies its consumers assume (eqLeaf); not at all (eqPass); or as a cap on the paths beyond
 - it (eqCap).
 -}
function equationRule
EqRule ::= ctx::PathCtx  lvl::PathLevel  attr::String  vt::VertexType
{
  local pg::ProductionGraph = findProductionGraph(lvl.lvProd, ctx.pcProdGraphs);
  local hasEq::Boolean = hasOwnInhEq(lvl.lvProd, vt, attr, ctx.pcFlowEnv);
  -- The overriding production's graph shows the override at the tree's decoration sites.
  local shown::Boolean = showsOverride(ctx, lvl);
  return
    -- What the applying production supplies to the LHS, and not the copy that the override records
    -- there.
    if transRootVertex(vt) == lhsVertexType()
    then eqLeaf((vertexSet([vt.inhVertex(attr)]), set:empty(), vertexSet([vt.inhVertex(attr)])))
    else if atTop(lvl)
    then
      case vt of
      -- The forward's own equation, and the override where the forward's consumers see it.
      | forwardVertexType() ->
        eqLeaf(topSets(ctx, ownEqDeps(ctx, vt, attr) ++ (if shown then ownEqDeps(ctx, ctx.pcTree, ctx.pcAttr) else [])))
      | _ when hasEq && !shown -> eqLeaf(topSets(ctx, ownEqDeps(ctx, vt, attr)))
      | _ -> eqPass()
      end
    else if lvl.lvCovered
    then
      case vt of
      -- What an extension synthesized attribute computed through the forward assumes
      | forwardVertexType() when hasEq ->
        eqLeaf((vertexSet(map(lhsInhVertex, attr :: set:toList(inhDepsForSyn("forward", pg.lhsNt, ctx.pcFlowTypes)))),
                set:empty(), vertexSet([])))
      | _ -> eqPass()
      end
    else
      case vt, lvl.lvCap of
      | _, _ when !hasEq -> eqPass()
      -- Through the dispatch signature, every consumer of the equation assumes all of what that
      -- signature allows.
      | rhsVertexType(c), just((child, dispatch, sn)) when c == child ->
        eqCap((allowedSet(ctx, dispatch, sn, attr), set:empty(),
               if lvl.lvCapViaDispatchSig then allowedSet(ctx, dispatch, sn, attr) else vertexSet([])))
      | _, _ ->
        eqLeaf(qualify(sigSets(ctx, lvl, pg.tileEdgeMap(vt.inhVertex(attr))), ownLeafInputs(ctx, lvl, pg, vt, attr)))
      end;
}

-- The LHS inherited vertices that the equation for attr at vt in lvl's production (see
-- ownInhEqDeps) needs through pg's tile flow graph, and that pg's normal graph also reaches from
-- lvl's entry: every consumer of that equation needs them.  pg's other edges at vt are not the
-- equation's and are left out: the copy beside an explicit forward equation, recorded copies and
-- the edges to trees shared into a local.
function ownLeafInputs
set:Set<FlowVertex> ::= ctx::PathCtx  lvl::PathLevel  pg::ProductionGraph  vt::VertexType  attr::String
{
  local deps::[FlowVertex] = ownInhEqDeps(lvl.lvProd, vt, attr, ctx.pcFlowEnv);
  local reached::set:Set<FlowVertex> = lhsVertices(foldr(set:union, vertexSet(deps), map(pg.tileEdgeMap, deps)));
  return
    case lvl.lvEntry of
    | just(entry) -> set:intersect(reached, pg.edgeMap(entry))
    | nothing() -> vertexSet([])
    end;
}

-- The dependencies of prod's own equation for attr at vt, where vertexHasInhEq finds one: its
-- explicit equation, or else the copy from the LHS that the forward or a forward production
-- attribute gets (see addFwdInhEqs).
fun ownInhEqDeps [FlowVertex] ::= prod::String  vt::VertexType  attr::String  flowEnv::FlowEnv =
  case vt of
  | rhsVertexType(sigName) -> flatMap(inhEqDeps, lookupInh(prod, sigName, attr, flowEnv))
  | localVertexType(fName) ->
    case lookupLocalInh(prod, fName, attr, flowEnv) of
    | [] when isForwardProdAttr(prod, fName, flowEnv) && !splitTransAttrInh(attr).isJust -> [lhsInhVertex(attr)]
    | ds -> flatMap(inhEqDeps, ds)
    end
  | forwardVertexType() ->
    case lookupFwdInh(prod, attr, flowEnv) of
    | [] -> [lhsInhVertex(attr)]
    | ds -> flatMap(inhEqDeps, ds)
    end
  | anonVertexType(_, _, _) -> flatMap(inhEqDeps, lookupLocalInh(prod, vt.vertexName, attr, flowEnv))
  | _ -> []
  end;

-- The dependencies of an inherited equation or contribution on a tree (see overrideEqVertex), or on
-- the forward.
fun inhEqDeps [FlowVertex] ::= d::FlowDef =
  case d of
  | fwdInhEq(_, _, deps) -> deps
  | inhEq(_, _, _, deps, _) -> deps
  | localInhEq(_, _, _, deps, _) -> deps
  | transInhEq(_, _, _, _, deps, _, _) -> deps
  | localTransInhEq(_, _, _, _, deps, _, _) -> deps
  | extraEq(_, _, deps, _) -> deps
  | _ -> []
  end;

{--
 - The level of applied, entered from lvl at its child sigName for attr.  It is covered where its
 - own equations hide nothing, as its graph projects what lvl's production supplies, which shows the
 - override.  It is capped at sigName where its own equation there is assumed as its dispatch
 - signature allows.
 -}
function enterLevel
PathLevel ::= ctx::PathCtx  lvl::PathLevel  applied::String  sigName::String  attr::String
{
  -- Covered where lvl's production shows the override at a sharing site of sigName (see
  -- sigSharingStitchPoints).
  local covered::Boolean = entersCovered(ctx, lvl, applied, sigName);
  -- Otherwise, where lvl's production shows the override, capped where applied implements a
  -- dispatch signature at sigName (see implementedSigStitchPoints).
  local cap::Maybe<(String, String)> =
    case findProductionGraph(applied, ctx.pcProdGraphs).implementedSigChildren of
    | just((dispatch, names)) when showsOverride(ctx, lvl) && !covered ->
      map(\ sn::String -> (dispatch, sn), lookup(sigName, names))
    | _ -> nothing()
    end;
  return levelAt(lvl, applied, sigName, attr, covered, cap, false);
}

{--
 - The level of impl, an implementation of the dispatch signature applied in lvl's production,
 - entered at its child sn at the position of the signature's child sigName.  It is covered as an
 - applied production is, by the signature's sharing sites (see implementedSigStitchPoints).
 - Otherwise it is capped at sn: lvl's production stitches the signature's tile flow graph, which
 - has what the signature allows whether or not lvl's production shows the override.
 -}
function implLevel
PathLevel ::= ctx::PathCtx  lvl::PathLevel  dispatch::String  sigName::String  impl::String  sn::String  attr::String
{
  local covered::Boolean = entersCovered(ctx, lvl, dispatch, sigName);
  return
    levelAt(lvl, impl, sn, attr, covered, if covered then nothing() else just((dispatch, sigName)), !covered);
}

-- Does lvl's production show the override at the decoration site where the walk leaves it: by the
-- copy recorded at the overriding production, or through a covered level's projection (see
-- addDecSiteTreeEqs)?
fun showsOverride Boolean ::= ctx::PathCtx  lvl::PathLevel =
  lvl.lvHiding && (if atTop(lvl) then ctx.pcRecorded else lvl.lvCovered);

-- Is a level entered from lvl at prod's child sigName, or at that position of an implementation of
-- prod, covered: does lvl's production show the override, and is it a sharing site of that child?
fun entersCovered Boolean ::= ctx::PathCtx  lvl::PathLevel  prod::String  sigName::String =
  showsOverride(ctx, lvl) &&
  any(map(\ s::(String, VertexType) -> s.1 == lvl.lvProd, lookupSigShareSites(prod, sigName, ctx.pcFlowEnv)));

-- The level of prod, entered from lvl at its child sigName for attr, covered as given, and capped
-- at sigName by what cap's dispatch signature allows at cap's child.
fun levelAt
PathLevel ::= lvl::PathLevel  prod::String  sigName::String  attr::String  covered::Boolean
  cap::Maybe<(String, String)>  capViaDispatchSig::Boolean =
  pathLevel(lvProd=prod, lvOnTree=lvl.lvOnTree, lvHiding=lvl.lvHiding, lvCovered=covered,
    lvCap=map(\ c::(String, String) -> (sigName, c.1, c.2), cap),
    lvCapViaDispatchSig=capViaDispatchSig,
    lvTreeChild=
      if lvl.lvOnTree then map(\ add::String -> (sigName, add), treeNaming(lvl.lvTreeAttr, attr))
      else nothing(),
    lvTreeAttr=lvl.lvTreeAttr, lvEntry=just(rhsInhVertex(sigName, attr)));

-- Into the child sigName of applied, applied at parent in lvl's production: the paths from there
-- (into), in the terms of applied, mapped back into lvl's production.  For a dispatch signature,
-- those of each host-language implementation, in the signature's terms, and what the
-- implementations not known here supply; only one implementation is applied, so their paths meet.
function appliedBound
PathResult ::= ctx::PathCtx  lvl::PathLevel  attr::String  parent::VertexType  applied::String  sigName::String
  into::AppliedChild  parentTree::DecSitePaths  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey]
{
  local found::PathResult =
    case into of
    | prodChild(dest) -> pathBound(ctx, enterLevel(ctx, lvl, applied, sigName, attr), attr, dest, loops, pSeen)
    | dispatchSigChild(impls) ->
      meetResults(
        (unknownImplSets(ctx, lvl, applied, sigName, attr), []) ::
        map(
          \ i::ImplChild ->
            case i of
            | implChild(impl, sn, names, dest) ->
              case pathBound(ctx, implLevel(ctx, lvl, applied, sigName, impl, sn, attr), attr, dest, loops, pSeen) of
              | (r, used) -> (map(implSetsToDispatchSig(names, _), r), used)
              end
            end,
          impls))
    end;
  return
    case found of
    | (just(s), used) -> (mapApplied(ctx, lvl, parent, applied, s, parentTree, pSeen), used)
    | r -> r
    end;
}

-- What the implementations of dispatch not known here supply to its child sigName for attr, in the
-- terms of dispatch: nothing() where they are covered (see implLevel) or cannot supply attr (see
-- unknownImplMaySupply), and otherwise what dispatch allows, which their own checks and the
-- applying production assume.  Where pcWarnFwd holds, every consumer of their equations assumes all
-- that dispatch allows, so all of it is qualified.
function unknownImplSets
Maybe<PathSets> ::= ctx::PathCtx  lvl::PathLevel  dispatch::String  sigName::String  attr::String
{
  local maySupply::Boolean =
    unknownImplMaySupply(findProductionGraph(dispatch, ctx.pcProdGraphs), rhsInhVertex(sigName, attr));
  return
    if !maySupply || entersCovered(ctx, lvl, dispatch, sigName) then nothing()
    else
      just((allowedSet(ctx, dispatch, sigName, attr), set:empty(),
        if ctx.pcWarnFwd then allowedSet(ctx, dispatch, sigName, attr) else vertexSet([])));
}

-- found, a bound for applied at parent in lvl's production, from applied's terms into lvl's:
-- applied's LHS inherited attributes as lvl's production supplies them to the tree at parent (see
-- parentBound), and found's other vertices as both productions have them (see mapAppliedWith).
-- nothing() where the tree at parent never gets a qualified input of found: every consumer of the
-- hidden equation needs that input, so the completeness checks report every demand of it.
fun mapApplied
Maybe<PathSets> ::= ctx::PathCtx  lvl::PathLevel  parent::VertexType  applied::String  found::PathSets
  parentTree::DecSitePaths  pSeen::[ParentKey] =
  mapAppliedWith(ctx, lvl, parent, applied, found, parentBound(ctx, lvl, parent, parentTree, _, pSeen));
{--
 - mapApplied, with the parent walk for each of applied's LHS inherited attributes given by
 - parentOf.  lvl's graph unites the decoration sites of every tree it passes through, while
 - applied's flow types, aspects and references assume applied's own equations: found's other
 - vertices count as lvl's graph has them only where applied's normal graph has them too, through
 - parentOf, or where they construct a tree.
 -}
function mapAppliedWith
Maybe<PathSets> ::= ctx::PathCtx  lvl::PathLevel  parent::VertexType  applied::String  found::PathSets
  parentOf::(Maybe<PathSets> ::= String)
{
  local vs::[FlowVertex] = set:toList(found.1);
  local qualifiedKs::[String] = filterLhsInh(set:toList(found.3));
  -- An LHS synthesized vertex adds nothing: found has what its equation depends on in applied's
  -- tile flow graph, and lvl's graph would unite that with the other productions that may construct
  -- the tree at parent.
  local nonLhs::[FlowVertex] = filter(\ w::FlowVertex -> !isLhsInhVertex(w) && !isLhsSynVertex(w), vs);
  local appliedGraph::ProductionGraph = findProductionGraph(applied, ctx.pcProdGraphs);
  -- The LHS inherited attributes that nonLhs reach in applied's normal graph
  local ownKs::[String] = filterLhsInh(set:toList(foldr(set:union, vertexSet([]), map(appliedGraph.edgeMap, nonLhs))));
  -- Each parent walk, done once
  local walks::[(String, Maybe<PathSets>)] = map(\ k::String -> (k, parentOf(k)), nub(filterLhsInh(vs) ++ ownKs));
  local walkOf::(Maybe<PathSets> ::= String) =
    \ k::String ->
      case lookup(k, walks) of
      | just(r) -> r
      | nothing() -> parentOf(k)
      end;
  -- The parent walk of each of applied's LHS inherited attributes in found, whose qualified inputs
  -- stay qualified only where found qualifies that attribute.
  local parentParts::[(String, Maybe<PathSets>)] =
    map(
      \ k::String ->
        (k, map(\ p::PathSets -> if contains(k, qualifiedKs) then p else (p.1, p.2, vertexSet([])), walkOf(k))),
      filterLhsInh(vs));
  local lhsPart::PathSets =
    joinSets(
      (vertexSet([]), found.2, vertexSet([])) ::
      map(\ p::(String, Maybe<PathSets>) -> fromMaybe(noSets, p.2), parentParts));
  local pg::ProductionGraph = findProductionGraph(lvl.lvProd, ctx.pcProdGraphs);
  -- What vertices reach in lvl's production: at the overriding production without its override
  -- equations, which applied's flow types do not include, and elsewhere through its tile flow graph
  local reach::(set:Set<FlowVertex> ::= [FlowVertex]) =
    \ ws::[FlowVertex] ->
      if atTop(lvl) then expandGraph(ws, ctx.pcOverrideFree)
      else vertexSet(ws ++ flatMap(\ w::FlowVertex -> set:toList(pg.tileEdgeMap(w)), ws));
  -- Vertices of lvl's production, in lvl's terms
  local inLvl::(PathSets ::= set:Set<FlowVertex>) =
    \ ws::set:Set<FlowVertex> ->
      if atTop(lvl) then (lhsVertices(ws), set:empty(), vertexSet([]))
      else sigSets(ctx, lvl, ws);
  local othersReach::set:Set<FlowVertex> = reach(map(fromSigVertex(applied, parent, _), nonLhs));
  local lvlSets::PathSets = inLvl(othersReach);
  local ownSets::PathSets =
    joinSets(map(
      \ k::String ->
        case walkOf(k) of
        | just(p) -> (p.1, p.2, vertexSet([]))
        | nothing() -> noSets
        end,
      ownKs));
  -- What constructing the trees on the way depends on, which every consumer that accesses them
  -- needs
  local builtSets::PathSets = inLvl(reach(filter(isConstructionVertex, set:toList(othersReach))));
  -- otherSets is within lvlSets, so where lvlSets adds nothing to lhsPart, the walks of ownKs are
  -- not needed.
  local otherSets::PathSets =
    if set:subset(lvlSets.1, lhsPart.1) && set:subset(lvlSets.2, lhsPart.2) then noSets
    else
      joinSets([
        (set:intersect(lvlSets.1, ownSets.1), set:intersect(lvlSets.2, ownSets.2), vertexSet([])), builtSets]);
  return
    if any(map(\ p::(String, Maybe<PathSets>) -> contains(p.1, qualifiedKs) && !p.2.isJust, parentParts))
    then nothing()
    else just(joinSets([otherSets, lhsPart]));
}

-- What lvl's production supplies to the tree at parent for k, as the paths of that tree
-- (parentTree) realize it: nothing() where no path gives k, and lvl's production has no edge from
-- the parent's vertex for k either, such as a copy recorded there, which a completeness check would
-- count as an equation.  A parent walk already in progress has an unknown value, so it counts as
-- empty, not as nothing(), which an enclosing meet would drop.
function parentBound
Maybe<PathSets> ::= ctx::PathCtx  lvl::PathLevel  parent::VertexType  parentTree::DecSitePaths  k::String
  pSeen::[ParentKey]
{
  local key::ParentKey = (lvl.lvProd, lvl.lvEntry, parent, k);
  local plvl::PathLevel = pathLevel(lvProd=lvl.lvProd, lvOnTree=false, lvHiding=false,
    lvCovered=false, lvCap=nothing(), lvCapViaDispatchSig=false, lvTreeChild=lvl.lvTreeChild, lvTreeAttr=lvl.lvTreeAttr,
    lvEntry=lvl.lvEntry);
  -- The top level can be a function, whose graph is not among the production graphs.
  local unstitched::g:Graph<FlowVertex> =
    if atTop(lvl) then ctx.pcOverrideFree.unstitchedGraph
    else findProductionGraph(lvl.lvProd, ctx.pcProdGraphs).unstitchedGraph;
  return
    if contains(key, pSeen) then just(noSets)
    else
      case pathBound(ctx, plvl, k, parentTree, [], key :: pSeen).1 of
      | just(s) -> just(s)
      | nothing() when !set:isEmpty(g:edgesFrom(parent.inhVertex(k), unstitched)) -> just(noSets)
      | nothing() -> nothing()
      end;
}

-- The signature vertices in vs, of lvl's production, where those of the child holding the
-- overridden tree become that tree's inherited attributes (through its flow type for a synthesized
-- attribute).  The entry vertex is left out.
function sigSets
PathSets ::= ctx::PathCtx  lvl::PathLevel  vs::set:Set<FlowVertex>
{
  local treeInhs::(Maybe<[String]> ::= FlowVertex) =
    \ w::FlowVertex ->
      case lvl.lvTreeChild, w of
      | just((c, add)), rhsInhVertex(s, n) when s == c -> just([add ++ n])
      | just((c, add)), rhsSynVertex(s, n) when s == c ->
        just(set:toList(inhDepsForSyn(add ++ n, ctx.pcTreeNt, ctx.pcFlowTypes)))
      | _, _ -> nothing()
      end;
  local kept::[FlowVertex] = filter(\ w::FlowVertex -> w.isSigVertex && just(w) != lvl.lvEntry, set:toList(vs));
  return
    (vertexSet(filter(\ w::FlowVertex -> !treeInhs(w).isJust, kept)),
     set:add(concat(catMaybes(map(treeInhs, kept))), set:empty()), vertexSet([]));
}

{--
 - The LHS inherited attributes that the overridden tree's attributes in todo depend on along the
 - paths of tree.  Each attribute in todo is walked once, and the overridden attribute and the
 - attributes already walked (done) count as empty.  The walk starts at the decoration site, so the
 - overriding production's own equation on the tree for that attribute is not used: the consumers on
 - the path do not see it.
 -}
fun nameBounds
set:Set<String> ::= ctx::PathCtx  tree::DecSitePaths  todo::[String]  done::[String]  acc::set:Set<String> =
  case todo of
  | [] -> acc
  | m :: rest when contains(m, done) -> nameBounds(ctx, tree, rest, done, acc)
  | m :: rest ->
    case pathBound(ctx, topLevel(ctx, m), m, tree, [], []).1 of
    | just((vs, ns, _)) -> nameBounds(ctx, tree, rest ++ set:toList(ns), m :: done, set:union(acc, onlyLhsInh(vs)))
    | nothing() -> nameBounds(ctx, tree, rest, m :: done, acc)
    end
  end;

-- The constants of a bound on graph's production's override equation for attr on the tree at v (see
-- PathCtx).
fun boundCtx
PathCtx ::= graph::ProductionGraph  recorded::Boolean  v::VertexType  attr::String
  prodGraphs::EnvTree<ProductionGraph>  flowTypes::EnvTree<FlowType>  flowEnv::FlowEnv  realEnv::Env  warnFwd::Boolean =
  pathCtx(pcGraph=graph, pcOverrideFree=fromMaybe(graph, graph.overrideFreeGraph),
    pcOverrides=filter(isOverrideEq(_, flowEnv), getGraphContribsFor(graph.prod, flowEnv)), pcTree=v, pcAttr=attr,
    pcRecorded=recorded, pcTreeNt=vertexTypeName(graph.prod, v, realEnv), pcProdGraphs=prodGraphs,
    pcFlowTypes=flowTypes, pcFlowEnv=flowEnv, pcWarnFwd=warnFwd);

{--
 - The LHS inherited attributes that the override equation of ctx may depend on, for the paths of
 - decoration sites from d: nothing() if none of them reaches an equation the override hides.
 -}
function overrideDecSiteInhs
Maybe<set:Set<String>> ::= ctx::PathCtx  d::VertexType
{
  local tree::DecSitePaths = findDecSitePaths(ctx.pcGraph.prod, d, ctx.pcFlowEnv, ctx.pcProdGraphs);
  return
    map(\ s::PathSets -> nameBounds(ctx, tree, set:toList(s.2), [ctx.pcAttr], onlyLhsInh(s.1)),
      pathBound(ctx, topLevel(ctx, ctx.pcAttr), ctx.pcAttr, tree, [], []).1);
}

{--
 - Why the bound that overrideDecSiteInhs gives for the paths from d lacks target, an LHS inherited
 - vertex of the overriding production: the first leaf along those paths, in their order, whose
 - bound lacks it, with the decoration sites on the way there, starting at d.  This repeats
 - pathBound along one path, and runs only for a message.
 -}
fun overrideDecSiteWitness Maybe<(String, [String])> ::= ctx::PathCtx  d::VertexType  target::FlowVertex =
  pathWitness(
    ctx, topLevel(ctx, ctx.pcAttr), ctx.pcAttr,
    findDecSitePaths(ctx.pcGraph.prod, d, ctx.pcFlowEnv, ctx.pcProdGraphs), just(d.vertexPP),
    \ r::Maybe<PathSets> ->
      case r of
      | just(s) -> !set:contains(target, s.1)
      | nothing() -> false
      end,
    [], []);

-- Why pathBound's bound for t at lvl lacks the target that lacks tests for (see
-- overrideDecSiteWitness).  label names t's vertex type on the route, where the route shows it.
fun pathWitness
Maybe<(String, [String])> ::= ctx::PathCtx  lvl::PathLevel  attr::String  t::DecSitePaths  label::Maybe<String>
  lacks::(Boolean ::= Maybe<PathSets>)  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey] =
  case t of
  | pathVertex(vt, steps) -> vertexWitness(ctx, lvl, attr, vt, steps, label, lacks, loops, pSeen)
  | pathCut(vt) -> just((s"by the loop back to ${vt.vertexPP} of ${lvl.lvProd}", catMaybes([label])))
  end;

-- At vt: its leaf, or its cap where the cap lacks the target, or else the first step onward whose
-- bound lacks the target, or else where those steps meet.  A loop head has its solved value for the
-- steps below it, as in vertexBound.
function vertexWitness
Maybe<(String, [String])> ::= ctx::PathCtx  lvl::PathLevel  attr::String  vt::VertexType  steps::[PathStep]
  label::Maybe<String>  lacks::(Boolean ::= Maybe<PathSets>)  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey]
{
  local here::Maybe<PathSets> = vertexBound(ctx, lvl, attr, vt, steps, loops, pSeen).1;
  local inner::[(LoopKey, Maybe<PathSets>)] = if atTop(lvl) then loops else (loopKey(lvl, vt, attr), here) :: loops;
  local onRoute::(Maybe<(String, [String])> ::= Maybe<(String, [String])>) =
    map(\ w::(String, [String]) -> (w.1, catMaybes([label]) ++ w.2), _);
  local lacking::[PathStep] = filter(\ st::PathStep -> lacks(stepBound(ctx, lvl, attr, st, inner, pSeen).1), steps);
  return
    case equationRule(ctx, lvl, attr, vt), lacking of
    | eqLeaf(_), _ -> just((leafText(ctx, lvl, attr, vt), catMaybes([label])))
    | eqCap(cap), _ when lacks(just(cap)) ->
      just((s"by what ${fromMaybe(("", "", ""), lvl.lvCap).2} allows", catMaybes([label])))
    | _, st :: _ -> onRoute(stepWitness(ctx, lvl, attr, st, lacks, inner, pSeen))
    | _, [] -> just((s"where the paths from ${vt.vertexPP} of ${lvl.lvProd} meet", catMaybes([label])))
    end;
}

-- The witness past the step st from a vertex type in lvl's production, for attr as named there.
fun stepWitness
Maybe<(String, [String])> ::= ctx::PathCtx  lvl::PathLevel  attr::String  st::PathStep
  lacks::(Boolean ::= Maybe<PathSets>)  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey] =
  case st of
  | localStep(rename, dest) ->
    case renameAttr(rename, attr) of
    | just(a) ->
      pathWitness(
        ctx, lvl, a, dest,
        just(
          case dest of
          | pathVertex(vt, _) -> vt.vertexPP
          | pathCut(vt) -> vt.vertexPP
          end),
        lacks, loops, pSeen)
    | nothing() -> nothing()
    end
  | appliedStep(parent, applied, sigName, into, parentTree) ->
    appliedWitness(ctx, lvl, attr, parent, applied, sigName, into, parentTree, lacks, loops, pSeen)
  end;

-- The witness past the child sigName of applied, applied at parent in lvl's production.  lacks is
-- tested in lvl's terms, so each bound in applied's terms is mapped back first, with each parent
-- walk done once (see mapApplied).
function appliedWitness
Maybe<(String, [String])> ::= ctx::PathCtx  lvl::PathLevel  attr::String  parent::VertexType  applied::String
  sigName::String  into::AppliedChild  parentTree::DecSitePaths  lacks::(Boolean ::= Maybe<PathSets>)
  loops::[(LoopKey, Maybe<PathSets>)]  pSeen::[ParentKey]
{
  -- applied's LHS inherited attributes, each with its parent walk, which is evaluated when first
  -- needed
  local parentTable::[(String, Maybe<PathSets>)] =
    map(
      \ k::String -> (k, parentBound(ctx, lvl, parent, parentTree, k, pSeen)),
      nub(flatMap(
        \ vws::(FlowVertex, set:Set<FlowVertex>) -> filterLhsInh(vws.1 :: set:toList(vws.2)),
        g:adjacency(findProductionGraph(applied, ctx.pcProdGraphs).graph))));
  local parentOf::(Maybe<PathSets> ::= String) =
    \ k::String ->
      case lookup(k, parentTable) of
      | just(r) -> r
      | nothing() -> parentBound(ctx, lvl, parent, parentTree, k, pSeen)
      end;
  local lacksIn::(Boolean ::= Maybe<PathSets>) =
    \ r::Maybe<PathSets> ->
      case r of
      | just(s) -> lacks(mapAppliedWith(ctx, lvl, parent, applied, s, parentOf))
      | nothing() -> false
      end;
  return
    case into of
    | prodChild(dest) ->
      pathWitness(ctx, enterLevel(ctx, lvl, applied, sigName, attr), attr, dest, nothing(), lacksIn, loops, pSeen)
    | dispatchSigChild(impls) ->
      if lacksIn(unknownImplSets(ctx, lvl, applied, sigName, attr))
      then just((s"by what ${applied} allows", []))
      else
        case
          filter(
            \ i::ImplChild ->
              case i of
              | implChild(impl, sn, names, dest) ->
                lacksIn(
                  map(implSetsToDispatchSig(names, _),
                    pathBound(ctx, implLevel(ctx, lvl, applied, sigName, impl, sn, attr), attr, dest, loops, pSeen).1))
              end,
            impls)
        of
        | implChild(impl, sn, names, dest) :: _ ->
          pathWitness(
            ctx, implLevel(ctx, lvl, applied, sigName, impl, sn, attr), attr, dest, just(s"child ${sn} of ${impl}"),
            \ r::Maybe<PathSets> -> lacksIn(map(implSetsToDispatchSig(names, _), r)), loops, pSeen)
        | [] -> just((s"where the implementations of ${applied} meet", []))
        end
    end;
}

-- The leaf of a witness at vt, where equationRule gives eqLeaf.
fun leafText String ::= ctx::PathCtx  lvl::PathLevel  attr::String  vt::VertexType =
  if transRootVertex(vt) == lhsVertexType()
  then s"by what the left-hand side of ${lvl.lvProd} gets for ${attr}"
  else if lvl.lvCovered && vt == forwardVertexType()
  then s"by the forward flow type of ${findProductionGraph(lvl.lvProd, ctx.pcProdGraphs).lhsNt}"
  else if vt == forwardVertexType() && null(lookupFwdInh(lvl.lvProd, attr, ctx.pcFlowEnv))
  then s"by the forward's copy of ${attr} from the left-hand side of ${lvl.lvProd}"
  else s"by the equation for ${attr} in ${lvl.lvProd}";

-- Does prod have its own equation for attr at vt?  The equations at a subterm are the applied
-- production's, past its applied step.
fun hasOwnInhEq Boolean ::= prod::String  vt::VertexType  attr::String  flowEnv::FlowEnv =
  case vt of
  | subtermVertexType(_, _, _) -> false
  | _ -> vertexHasInhEq(prod, vt, attr, flowEnv)
  end;

-- What the overriding production's own equations for attr on the tree at vt depend on, without the
-- copies recorded there for overrides of the trees shared at vt: the override-free graph has none,
-- and the override equations on vt are added back.
fun ownEqDeps [FlowVertex] ::= ctx::PathCtx  vt::VertexType  attr::String =
  set:toList(g:edgesFrom(vt.inhVertex(attr), ctx.pcOverrideFree.unstitchedGraph)) ++
  flatMap(inhEqDeps,
    filter(\ d::FlowDef -> overrideEqVertex(d) == just(treeAttr(ctx.pcGraph.prod, vt, attr)), ctx.pcOverrides));

-- Is lvl the overriding production's own level?
fun atTop Boolean ::= lvl::PathLevel = !lvl.lvEntry.isJust;

fun loopKey LoopKey ::= lvl::PathLevel  vt::VertexType  attr::String =
  (lvl.lvProd, vt, attr, lvl.lvCovered, lvl.lvCap.isJust, lvl.lvCapViaDispatchSig, lvl.lvEntry);

-- The level of the overriding production, for its tree's attribute a: hiding where a is the
-- overridden attribute.
fun topLevel PathLevel ::= ctx::PathCtx  a::String =
  pathLevel(lvProd=ctx.pcGraph.prod, lvOnTree=true, lvHiding=a == ctx.pcAttr, lvCovered=false, lvCap=nothing(),
    lvCapViaDispatchSig=false, lvTreeChild=nothing(), lvTreeAttr=a, lvEntry=nothing());

-- The LHS inherited vertices that vs reach in the overriding production's graph.
fun topSets PathSets ::= ctx::PathCtx  vs::[FlowVertex] =
  (lhsVertices(expandGraph(vs, ctx.pcGraph)), set:empty(), vertexSet([]));

global noSets::PathSets = (vertexSet([]), set:empty(), vertexSet([]));

-- s, with the vertices of inputs among its own as its qualified inputs (see mapApplied).
fun qualify PathSets ::= s::PathSets  inputs::set:Set<FlowVertex> = (s.1, s.2, set:intersect(inputs, s.1));

fun vertexSet set:Set<FlowVertex> ::= vs::[FlowVertex] = set:add(vs, set:emptyWith(compareVertexId));

fun lhsVertices set:Set<FlowVertex> ::= vs::set:Set<FlowVertex> = set:filter(isLhsInhVertex, vs);

fun isLhsInhVertex Boolean ::= v::FlowVertex =
  case v of
  | lhsInhVertex(_) -> true
  | _ -> false
  end;

fun isLhsSynVertex Boolean ::= v::FlowVertex =
  case v of
  | lhsSynVertex(_) -> true
  | _ -> false
  end;

-- Is v the value of a tree, or what constructing the root of a tree depends on (see eqVertex and
-- outerEqVertex)?
fun isConstructionVertex Boolean ::= v::FlowVertex =
  case v of
  | lhsEqVertex() -> true
  | rhsEqVertex(_) -> true
  | rhsOuterEqVertex(_) -> true
  | localEqVertex(_) -> true
  | localOuterEqVertex(_) -> true
  | transAttrOuterEqVertex(_, _) -> true
  | forwardOuterEqVertex() -> true
  | forwardParentEqVertex() -> true
  | anonEqVertex(_) -> true
  | subtermEqVertex(_, _, _) -> true
  | subtermOuterEqVertex(_, _, _) -> true
  | lhsSynVertex(attr) -> attr == "forward"
  | _ -> false
  end;

-- The intersection of rs, where nothing() is the identity.  An input stays qualified only where
-- every operand qualifies it.
fun meetSets Maybe<PathSets> ::= rs::[Maybe<PathSets>] =
  foldr(
    \ r::Maybe<PathSets> acc::Maybe<PathSets> ->
      case r, acc of
      | just(a), just(b) -> just((set:intersect(a.1, b.1), set:intersect(a.2, b.2), set:intersect(a.3, b.3)))
      | just(_), nothing() -> r
      | nothing(), _ -> acc
      end,
    nothing(), rs);

-- The union of ss.
fun joinSets PathSets ::= ss::[PathSets] =
  foldr(\ a::PathSets b::PathSets -> (set:union(a.1, b.1), set:union(a.2, b.2), set:union(a.3, b.3)), noSets, ss);

fun meetResults PathResult ::= rs::[PathResult] = (meetSets(map(fst, rs)), nub(concat(map(snd, rs))));

-- What dispatch allows the attribute attr of its child sigName to depend on (see
-- dispatchChildAllowedInhs).
fun allowedSet set:Set<FlowVertex> ::= ctx::PathCtx  dispatch::String  sigName::String  attr::String =
  vertexSet(map(lhsInhVertex, set:toList(dispatchChildAllowedInhs(
    findProductionGraph(dispatch, ctx.pcProdGraphs), rhsInhVertex(sigName, attr)))));

-- s, from the terms of an implementation into those of its dispatch signature, where names pairs
-- their children.  The vertices of the implementation's children that the dispatch signature lacks
-- are left out.
fun implSetsToDispatchSig PathSets ::= names::[(String, String)]  s::PathSets =
  (vertexSet(catMaybes(map(implVertexToDispatchSig(names, _), set:toList(s.1)))), s.2, s.3);

fun implVertexToDispatchSig Maybe<FlowVertex> ::= names::[(String, String)]  w::FlowVertex =
  case w of
  | rhsEqVertex(c) -> map(\ n::String -> rhsEqVertex(n), lookup(c, names))
  | rhsOuterEqVertex(c) -> map(\ n::String -> rhsOuterEqVertex(n), lookup(c, names))
  | rhsSynVertex(c, a) -> map(\ n::String -> rhsSynVertex(n, a), lookup(c, names))
  | rhsInhVertex(c, a) -> map(\ n::String -> rhsInhVertex(n, a), lookup(c, names))
  | _ -> just(w)
  end;

-- How a walked tree's attributes are named on the overridden tree, where the walked tree's b is the
-- overridden tree's a: the prefix to add, where the walked tree is a translation attribute of the
-- overridden tree.  A walk only ever strips translation attributes from its attribute (see
-- renameAttr), so b is a or a suffix of it.
fun treeNaming Maybe<String> ::= a::String  b::String =
  if a == b then just("")
  else if endsWith("." ++ b, a) then just(substring(0, length(a) - length(b), a))
  else nothing();

-- How many rounds solveLoop iterates a loop head before giving up on it: each round walks the
-- subtree below the head again, and nested loops multiply that.  Giving up counts the head as
-- empty, which can only add messages.
global maxLoopRounds::Integer = 8;
