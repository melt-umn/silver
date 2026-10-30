grammar silver:compiler:analysis:warnings:flow;

import silver:util:graph as g;
import silver:compiler:definition:flow:driver only
  edgeMap, tileEdgeMap, unstitchedGraph, fromSigVertex, isTranslationAttr, isSigVertex, hasOwnInhEq,
  isConstructionVertex, vertexSet;

synthesized attribute warnSharingCycles :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnSharingCycles = false;
}
abstract production warnSharingCyclesFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnSharingCycles = true;
  forwards to @rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--warn-sharing-cycles", paramString=nothing(),
      help="warn about potential cycles due to missing inherited override equations (conservative check, disabled in --mwda by default)",
      flagParser=flag(warnSharingCyclesFlag))];
}

{--
 - Might supplying an inherited attribute 'i' to the tree 'ref', at its decoration site
 - 'decSite', depend on 'i' on 'ref' itself?  Returns the check for a given 'i', so that
 - the dependencies of the shared tree's value are only expanded once per decoration site.
 -}
function sharingSiteDependsOnInh
(Boolean ::= String) ::=
  ref::VertexType  decSite::VertexType  graph::ProductionGraph  prodGraphs::EnvTree<ProductionGraph>  flowEnv::FlowEnv
  realEnv::Env
{
  local refDeps :: set:Set<FlowVertex> = expandGraph(ref.eqDeps ++ ref.outerEqDeps, graph);
  -- The paths of decoration sites from decSite, found once for all attributes.
  local paths :: DecSitePaths = findDecSitePaths(graph.prod, decSite, flowEnv, prodGraphs);
  return decSiteDependsOnInh(ref, decSite, graph, prodGraphs, flowEnv, realEnv, refDeps, paths, _);
}

{--
 - sharingSiteDependsOnInh for i, where refDeps is what the value of ref reaches.  A translation
 - attribute can have a flow type like `toCore {toCore.env}` (legal where the circularity is in a
 - non-root position of the translation), and then every path through the value of that translation
 - reaches toCore.env.  So where the value of ref, or of the translation attribute of ref that i is
 - on, reaches i, what is supplied for i counts only through valueFreeDeps.
 -}
function decSiteDependsOnInh
Boolean ::=
  ref::VertexType  decSite::VertexType  graph::ProductionGraph  prodGraphs::EnvTree<ProductionGraph>  flowEnv::FlowEnv
  realEnv::Env  refDeps::set:Set<FlowVertex>  paths::DecSitePaths  i::String
{
  local target :: FlowVertex = ref.inhVertex(i);
  -- Only what the decoration site itself supplies for i: its inherited vertex also reaches the tree
  -- shared there (see addDecSiteTreeEqs), which would make every such decoration site look like a
  -- cycle.
  local supplied :: [FlowVertex] = suppliedInhDeps(graph, i, paths, [], prodGraphs, flowEnv);
  -- What constructing the outer node of the decoration site depends on, unless the value of ref
  -- reaches i: a path from there may just come from the stitch point of an access of a translation
  -- attribute, which the closed graph cannot tell apart from a cycle.
  local outer :: [FlowVertex] = if set:contains(target, refDeps) then [] else decSite.outerEqDeps;
  local deps :: [FlowVertex] = supplied ++ outer;
  local valueReaches :: Boolean =
    set:contains(target, refDeps) ||
    set:contains(target, expandGraph(map(ref.synVertex, transAttrPrefixes(i)), graph));
  return
    set:contains(target, expandGraph(deps, graph)) &&
    (!valueReaches || set:contains(target, expandGraph(valueFreeDeps(graph, flowEnv, realEnv, deps), graph)));
}

-- The translation attributes that the inherited attribute attr of a tree is on, named as on that
-- tree: t1 and t1.t2 for t1.t2.k.
fun transAttrPrefixes [String] ::= attr::String =
  case splitTransAttrInh(attr) of
  | just((transAttr, rest)) -> transAttr :: map(\ p::String -> s"${transAttr}.${p}", transAttrPrefixes(rest))
  | nothing() -> []
  end;

aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  -- oh no again!
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  -- Check for cycles due to missing inherited equations on shared trees.
  -- We need to check inherited attributes known locally to occur on the shared tree,
  -- that don't have an inherited equation defined locally, and thus may be involved in a cycle.
  -- We don't need *all* inherited attributes, because only the ones we know about
  -- could be a dependency for constructing the decoration site tree.
  top.errors <-
    case top.decSiteVertexInfo, e.flowVertexInfo of
    | _, just(localVertexType(fName)) when isForwardProdAttr(top.frame.fullName, fName, top.flowEnv) -> []
    | just(decSite), just(ref) when top.config.warnSharingCycles ->
      let dependsOnInh :: (Boolean ::= String) =
            sharingSiteDependsOnInh(ref, decSite, top.frame.flowGraph, myGraphs, top.flowEnv, top.env)
      in flatMap(\ i::String ->
        -- A tree that has i by the time it is shared here needs no override: from an equation on it
        -- or on a tree shared into it, or from every sharing site of a child shared through the
        -- signature (see hasInhBeforeSharing).
        if !hasInhBeforeSharing(top.frame.fullName, ref, i, top.flowEnv, top.env)
        && decSiteHasInhEq(top.frame.fullName, decSite, i, myGraphs, myFlow, top.flowEnv, top.env)
        && dependsOnInh(i)
        then [mwdaWrnFromOrigin(top, s"Potentially missing inherited override equation for ${i} on ${ref.vertexName}; a cycle may exist via its sharing decoration site ${decSite.vertexName}")]
        else [],
        getInhAndInhOnTransAttrsOn(e.finalType.typeName, top.env))
      end
    | _, _ -> []
    end;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  -- oh no again!
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;
  local myFlow :: EnvTree<FlowType> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).grammarFlowTypes;

  -- Same checks as for decorationSiteExpr, for application of a prod with a shared signature param.
  top.errors <-
    case sigDecSite, e.flowVertexInfo of
    | just(decSite), just(ref)
        when top.config.warnSharingCycles && sigIsShared && isForwardParam ->
      let dependsOnInh :: (Boolean ::= String) =
            sharingSiteDependsOnInh(ref, decSite, top.frame.flowGraph, myGraphs, top.flowEnv, top.env)
      in flatMap(\ i::String ->
        if !hasInhBeforeSharing(top.frame.fullName, ref, i, top.flowEnv, top.env)
        && decSiteHasInhEq(top.frame.fullName, decSite, i, myGraphs, myFlow, top.flowEnv, top.env)
        && dependsOnInh(i)
        then [mwdaWrnFromOrigin(top, s"Potentially missing inherited override equation for ${i} on ${ref.vertexName}; a cycle may exist via its sharing decoration site ${decSite.vertexName}")]
        else [],
        getInhAndInhOnTransAttrsOn(e.finalType.typeName, top.env))
      end
    | _, _ -> []
    end;
}

{--
 - The vertices that the first equation for attr on some path of t depends on directly: an equation
 - of graph's production, or the equation of the production applied at a subterm, from that
 - production's tile flow graph.  A join over the paths, beside pathBound's meet.  Edges from a
 - decoration site to the tree shared there are not followed (see addDecSiteTreeEqs), and pSeen
 - holds the parent walks in progress.
 -}
fun suppliedInhDeps
[FlowVertex] ::= graph::ProductionGraph  attr::String  t::DecSitePaths  pSeen::[(VertexType, String)]
  prodGraphs::EnvTree<ProductionGraph>  flowEnv::FlowEnv =
  case t of
  | pathVertex(vt, steps) ->
    set:toList(g:edgesFrom(vt.inhVertex(attr), graph.unstitchedGraph)) ++
    (if hasOwnInhEq(graph.prod, vt, attr, flowEnv) then []
     else
       flatMap(stepSuppliedInhDeps(graph, attr, _, pSeen, prodGraphs, flowEnv),
         -- The decoration sites where a forward production attribute is itself shared add nothing
         -- to its own copies (see addSharingEqs); a decoration site that shares a translation
         -- attribute of it is still followed.
         if isFwdProdAttrVertex(graph.prod, vt, flowEnv) then filter(\ s::PathStep -> !sharesTreeItself(s), steps)
         else steps))
  -- A cut is back at a vertex type on the path.  Under a renamed attribute, possible only with a
  -- cycle of decoration sites through a translation attribute, the cut loses that vertex type's
  -- equation.
  | pathCut(_) -> []
  end;

-- suppliedInhDeps past the step s, where attr is named as before s.
fun stepSuppliedInhDeps
[FlowVertex] ::= graph::ProductionGraph  attr::String  s::PathStep  pSeen::[(VertexType, String)]
  prodGraphs::EnvTree<ProductionGraph>  flowEnv::FlowEnv =
  case s of
  | localStep(rename, dest) ->
    case renameAttr(rename, attr) of
    | just(a) -> suppliedInhDeps(graph, a, dest, pSeen, prodGraphs, flowEnv)
    | nothing() -> []
    end
  -- The applied production's equation for sigName, from its tile flow graph, without the vertex
  -- of sigName itself, which leads back to the tree shared there.  Its LHS inherited vertices are
  -- what graph's production gives the tree at parent.
  | appliedStep(parent, applied, sigName, _, parentTree) ->
    flatMap(
      \ v::FlowVertex ->
        case v of
        | lhsInhVertex(a) when contains((parent, a), pSeen) -> []
        | lhsInhVertex(a) -> suppliedInhDeps(graph, a, parentTree, (parent, a) :: pSeen, prodGraphs, flowEnv)
        | _ -> [fromSigVertex(applied, parent, v)]
        end,
      filter(\ v::FlowVertex -> v.isSigVertex && v != rhsInhVertex(sigName, attr),
        set:toList(findProductionGraph(applied, prodGraphs).tileEdgeMap(rhsInhVertex(sigName, attr)))))
  end;

{--
 - What deps depend on, for the sharing-cycle check of an inherited attribute that the shared tree's
 - own value depends on, as with a translation attribute whose flow type is like toCore {toCore.env}
 - (see decSiteDependsOnInh).  Every path through such a value reaches the attribute, so here a
 - tree's value counts only through what its equation depends on directly.  This assumes that a
 - translation attribute's value is needed only for the root of the translation, and that this root
 - needs only inherited attributes of the tree that the translation attribute is on.
 -}
function valueFreeDeps
[FlowVertex] ::= graph::ProductionGraph  flowEnv::FlowEnv  realEnv::Env  deps::[FlowVertex]
{
  -- What the applied production gives a subterm in deps is in deps already (see
  -- stepSuppliedInhDeps), so only the equations of graph's production for that subterm remain.
  local own :: [FlowVertex] =
    flatMap(
      \ v::FlowVertex ->
        case v of
        | subtermInhVertex(p, n, c, a) -> ownTreeEqDeps([], graph, subtermVertexType(p, n, c), a, flowEnv)
        | _ -> [v]
        end,
      deps);
  return valueFreeDepsSeen(graph, realEnv, own, vertexSet([]));
}

-- valueFreeDeps for todo, leaving out the vertices in seen.  A tree, or a translation attribute of
-- the LHS, counts through what its equation depends on directly, and the value of any other
-- translation attribute through the inherited vertices that it reaches on the tree that the
-- attribute is on.
function valueFreeDepsSeen
[FlowVertex] ::= graph::ProductionGraph  realEnv::Env  todo::[FlowVertex]  seen::set:Set<FlowVertex>
{
  local v :: FlowVertex = head(todo);
  -- valueFreeDeps for more and the rest of todo, with v seen
  local walkOn :: ([FlowVertex] ::= [FlowVertex]) =
    \ more::[FlowVertex] -> valueFreeDepsSeen(graph, realEnv, more ++ tail(todo), set:add([v], seen));
  return
    if null(todo) then []
    else if set:contains(v, seen) then valueFreeDepsSeen(graph, realEnv, tail(todo), seen)
    else if isTreeVertex(v, realEnv) then walkOn(set:toList(g:edgesFrom(v, graph.unstitchedGraph)))
    else if isTransValueVertex(v, realEnv)
    then filter(isPlainInhOf(v, _), set:toList(graph.edgeMap(v))) ++ walkOn([])
    else v :: walkOn([]);
}

-- What graph's production gives the tree at vt for attr by its own equations: the vertices that the
-- inherited vertex of vt for attr has edges to in the unstitched graph, and the same for the trees
-- shared into vt that this vertex reaches (local y = @x; see addDecSiteTreeEqs), leaving out the
-- trees in seen.
function ownTreeEqDeps
[FlowVertex] ::= seen::[VertexType]  graph::ProductionGraph  vt::VertexType  attr::String  flowEnv::FlowEnv
{
  local shared :: [VertexType] =
    filter(
      \ s::VertexType -> !contains(s, seen) && set:contains(s.inhVertex(attr), graph.edgeMap(vt.inhVertex(attr))),
      map(fst, lookupSharedInto(graph.prod, vt, flowEnv)));
  return
    set:toList(g:edgesFrom(vt.inhVertex(attr), graph.unstitchedGraph)) ++
    flatMap(ownTreeEqDeps(vt :: seen, graph, _, attr, flowEnv), shared);
}

-- Is v the vertex of a tree, for the equation giving the tree or the outer dependencies of that
-- equation?  So are the forward's equation vertex (see forwardEqVertex) and a translation attribute
-- of the LHS, which v's production gives.
fun isTreeVertex Boolean ::= v::FlowVertex  realEnv::Env =
  isConstructionVertex(v) ||
  case v of
  | lhsSynVertex(attr) -> indexOf(".", attr) == -1 && isTranslationAttr(attr, realEnv)
  | _ -> false
  end;

-- Is v the value of a translation attribute, a synthesized vertex for a translation attribute (see
-- transAttrVertexType)?  Only the translation attributes in realEnv are recognized.
fun isTransValueVertex Boolean ::= v::FlowVertex  realEnv::Env =
  case synVertexAttr(v) of
  | just(attr) -> isTranslationAttr(substring(lastIndexOf(".", attr) + 1, length(attr), attr), realEnv)
  | nothing() -> false
  end;

-- The attribute of v, if v is a synthesized vertex.
fun synVertexAttr Maybe<String> ::= v::FlowVertex =
  case v of
  | lhsSynVertex(attr) -> just(attr)
  | rhsSynVertex(_, attr) -> just(attr)
  | localSynVertex(_, attr) -> just(attr)
  | forwardSynVertex(attr) -> just(attr)
  | forwardParentSynVertex(attr) -> just(attr)
  | anonSynVertex(_, attr) -> just(attr)
  | subtermSynVertex(_, _, _, attr) -> just(attr)
  | _ -> nothing()
  end;

-- Where v is the synthesized vertex of a translation attribute: is w the inherited vertex of an
-- attribute of the tree that the translation attribute is on, and not of an attribute of a
-- translation attribute of that tree?
fun isPlainInhOf Boolean ::= v::FlowVertex  w::FlowVertex =
  case v, w of
  | lhsSynVertex(a), lhsInhVertex(b) -> isPlainUnder(a, b)
  | rhsSynVertex(c1, a), rhsInhVertex(c2, b) -> c1 == c2 && isPlainUnder(a, b)
  | localSynVertex(f1, a), localInhVertex(f2, b) -> f1 == f2 && isPlainUnder(a, b)
  | forwardSynVertex(a), forwardInhVertex(b) -> isPlainUnder(a, b)
  | forwardParentSynVertex(a), forwardParentInhVertex(b) -> isPlainUnder(a, b)
  | anonSynVertex(f1, a), anonInhVertex(f2, b) -> f1 == f2 && isPlainUnder(a, b)
  | subtermSynVertex(p1, n1, c1, a), subtermInhVertex(p2, n2, c2, b) ->
    p1 == p2 && n1 == n2 && c1 == c2 && isPlainUnder(a, b)
  | _, _ -> false
  end;

-- Is b an attribute of the tree that the translation attribute a is on: k for a translation
-- attribute t, or s.k for s.t?
function isPlainUnder
Boolean ::= a::String  b::String
{
  local pre :: String = substring(0, lastIndexOf(".", a) + 1, a);
  return startsWith(pre, b) && indexOf(".", substring(length(pre), length(b), b)) == -1;
}

-- Is s a step to where the tree itself is shared, rather than a translation attribute of it?
fun sharesTreeItself Boolean ::= s::PathStep =
  case s of
  | localStep(sameAttr(), _) -> true
  | _ -> false
  end;
