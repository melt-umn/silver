grammar silver:compiler:analysis:warnings:flow;


-- Make sure an inherited equation on a shared tree does not introduce hidden transitive
-- dependencies.
aspect production inheritedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    if top.config.warnMissingInh
    then
      map(mwdaWrnFromOrigin(top, _),
        overrideMessages(
          false, dl.defLHSVertex, attr.attrDcl.fullName, dl.defLHSRecorded, lhsInhDeps, top.frame.flowGraph,
          top.frame.sourceGrammar, top.grammarName, top.compiledGrammars, top.flowEnv, top.env, top.config.warnFwd))
    else [];
}
aspect production inhBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    if top.config.warnMissingInh
    then
      map(mwdaWrnFromOrigin(top, _),
        overrideMessages(
          false, dl.defLHSVertex, attr.attrDcl.fullName, dl.defLHSRecorded, lhsInhDeps, top.frame.flowGraph,
          top.frame.sourceGrammar, top.grammarName, top.compiledGrammars, top.flowEnv, top.env, top.config.warnFwd))
    else [];
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  top.errors <-
    if top.config.warnMissingInh
    then
      map(mwdaWrnFromOrigin(top, _),
        overrideMessages(
          true, dl.defLHSVertex, attr.attrDcl.fullName, dl.defLHSRecorded, lhsInhDeps, top.frame.flowGraph,
          top.frame.sourceGrammar, top.grammarName, top.compiledGrammars, top.flowEnv, top.env, top.config.warnFwd))
    else [];
}

{--
 - The messages for an override equation (or contribution, isContribution) of graph's production for
 - attr on the tree at v, where deps, its dependencies on LHS inherited attributes, exceed its
 - bounds (see overrideBounds).  Where v is a translation attribute t of a tree w, the equation is
 - checked for attr on v and for t.attr on w.  Only the check on an extension implementation's
 - child, not on a translation attribute of that child, is also bounded by what applications of the
 - implementation's dispatch signature allow (see implCapInhs), so that an excess is reported once.
 -}
function overrideMessages
[String] ::= isContribution::Boolean  v::VertexType  attr::String  recorded::Boolean  deps::set:Set<String>
  graph::ProductionGraph  sourceGrammar::String  grammarName::String  compiledGrammars::EnvTree<Decorated RootSpec>
  flowEnv::FlowEnv  env::Env  warnFwd::Boolean
{
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(grammarName, compiledGrammars)).productionFlowGraphs;
  local myFlowTypes::EnvTree<FlowType> = head(searchEnvTree(grammarName, compiledGrammars)).grammarFlowTypes;
  -- The messages for the equation, checked as an equation for the attribute a on the tree at w.
  local messageOn::([String] ::= VertexType String) =
    \ w::VertexType  a::String ->
      overrideMessage(
        isContribution, boundCtx(graph, recorded, w, a, myGraphs, myFlowTypes, flowEnv, env, warnFwd), deps,
        sourceGrammar, compiledGrammars);
  return
    case v of
    | transAttrVertexType(w, transAttr) -> messageOn(v, attr) ++ messageOn(w, s"${transAttr}.${attr}")
    | _ -> messageOn(v, attr)
    end;
}

{--
 - The message for the override of ctx where deps, its dependencies, exceed the intersection of its
 - bounds (see overrideBounds) and cap (see implCapInhs).  Where bounds is not empty, the override
 - hides an equation on some path or its tree may become the forward, and the message names, for
 - each attribute in excess, the first bound that lacks it: the forward rule, then cap, then the
 - decoration sites, for which overrideDecSiteWitness finds why.  Where cap is the only bound, the
 - override is on a child that an extension implementation does not pass on.
 -}
function overrideMessage
[String] ::= isContribution::Boolean  ctx::PathCtx  deps::set:Set<String>  sourceGrammar::String
  compiledGrammars::EnvTree<Decorated RootSpec>
{
  local prod :: String = ctx.pcGraph.prod;
  local v :: VertexType = ctx.pcTree;
  local bounds :: [(BoundSource, set:Set<String>)] = overrideBounds(ctx);
  local cap :: Maybe<(String, set:Set<String>)> = implCapInhs(ctx, sourceGrammar, compiledGrammars);
  local all::[(BoundSource, set:Set<String>)] =
    filter(\ b::(BoundSource, set:Set<String>) -> !isDecSiteBound(b.1), bounds) ++
    map(\ c::(String, set:Set<String>) -> (capBound(c.1), c.2), catMaybes([cap])) ++
    filter(\ b::(BoundSource, set:Set<String>) -> isDecSiteBound(b.1), bounds);
  local bound::set:Set<String> = foldr(set:intersect, head(all).2, map(snd, tail(all)));
  local excess::[String] = set:toList(set:difference(deps, bound));
  local kind::String = if isContribution then "contribution equation" else "override equation";
  local witnessOf::((String, [String]) ::= String) =
    \ x::String ->
      case filter(\ b::(BoundSource, set:Set<String>) -> !set:contains(x, b.2), all) of
      | (fwdRuleBound(), _) :: _ -> (s"by the forward flow type of ${ctx.pcGraph.lhsNt}", [])
      | (capBound(dispatch), _) :: _ -> (s"by what ${dispatch} allows", [])
      | (decSiteBound(d), _) :: _ ->
        fromMaybe((s"where the paths from ${d.vertexPP} meet", []), overrideDecSiteWitness(ctx, d, lhsInhVertex(x)))
      | [] -> ("", [])
      end;
  -- The route's first entry is the decoration site, which "(from ...)" already names unless it
  -- names several.
  local onRoute::([String] ::= [String]) =
    \ route::[String] ->
      if length(nub(lookupRefPossibleDecSites(prod, v, ctx.pcFlowEnv))) <= 1 then drop(1, route) else route;
  local witnesses::[((String, [String]), String)] = map(\ x::String -> (witnessOf(x), x), excess);
  -- The attributes in excess grouped by their witness, in the order in which the witnesses first
  -- appear
  local clauses::[((String, [String]), [String])] =
    map(\ w::(String, [String]) -> (w, lookupAll(w, witnesses)), nub(map(fst, witnesses)));
  local clauseText::(String ::= ((String, [String]), [String])) =
    \ c::((String, [String]), [String]) ->
      (if length(clauses) == 1 && length(excess) > 1 then "all are"
       else implode(", ", c.2) ++ (if length(c.2) == 1 then " is" else " are")) ++
      s" excluded ${c.1.1}" ++
      (if null(onRoute(c.1.2)) then "" else s", through ${implode(", ", onRoute(c.1.2))}");
  return
    if null(all) || null(excess) then []
    else if !null(bounds)
    then
      [s"Inherited ${kind} for ${ctx.pcAttr} on ${v.vertexPP} may exceed a flow type with hidden transitive " ++
       s"dependencies on ${implode(", ", excess)}; on some reference to this tree, this attribute may be " ++
       s"expected to depend ${depListStr(bound)} (from ${overrideDecSitesPP(prod, v, map(fst, cap), ctx.pcFlowEnv)})" ++
       concat(map(\ c::((String, [String]), [String]) -> "; " ++ clauseText(c), clauses))]
    else
      [s"Inherited ${if isContribution then kind else "equation"} for ${ctx.pcAttr} on ${v.vertexPP} has excess " ++
       s"dependencies on ${implode(", ", excess)}; applications of dispatch ${cap.fromJust.1} allow it to depend " ++
       depListStr(cap.fromJust.2)];
}

{--
 - The bounds on the override equation of ctx for attr on the tree at v.  For each decoration site
 - of v: what every path from that decoration site allows (see overrideDecSiteInhs), and what
 - constructing the outer nodes of v and of the decoration site depends on.  Where v may become the
 - forward: the forward flow type and attr, as for a forward inherited equation (see Inh.sv), since
 - an extension synthesized attribute's flow type need only contain the forward flow type.
 -}
function overrideBounds
[(BoundSource, set:Set<String>)] ::= ctx::PathCtx
{
  local prod :: String = ctx.pcGraph.prod;
  local v :: VertexType = ctx.pcTree;
  local fwdFlowType :: set:Set<String> = inhDepsForSyn("forward", ctx.pcGraph.lhsNt, ctx.pcFlowTypes);
  local decSiteInhs :: [(BoundSource, set:Set<String>)] =
    flatMap(
      \ d::VertexType ->
        case overrideDecSiteInhs(ctx, d) of
        | just(s) ->
          [(decSiteBound(d), set:union(s, onlyLhsInh(expandGraph(v.outerEqDeps ++ d.outerEqDeps, ctx.pcGraph))))]
        | nothing() -> []
        end,
      lookupRefPossibleDecSites(prod, v, ctx.pcFlowEnv));
  return
    (if mayBecomeForward(prod, v, ctx.pcFlowEnv) then [(fwdRuleBound(), set:add([ctx.pcAttr], fwdFlowType))] else []) ++
    decSiteInhs;
}

{--
 - The name of the dispatch signature that ctx's production implements, and what its applications
 - allow the override equation of ctx to depend on (see dispatchChildAllowedInhs), where the
 - equation is on the production's child at a position of the dispatch signature, sourceGrammar (the
 - production's grammar) is not exported by the dispatch signature's, and an implementation not
 - known here may supply the attribute to that child (see unknownImplMaySupply).  Whichever path the
 - child takes next, applications that dispatch to the production assume what the dispatch
 - signature's tile flow graph allows, and earlier implementations in a chain what its normal graph
 - allows.
 -}
fun implCapInhs
Maybe<(String, set:Set<String>)> ::=
  ctx::PathCtx  sourceGrammar::String  compiledGrammars::EnvTree<Decorated RootSpec> =
  do {
    sigName :: String <-
      case ctx.pcTree of
      | rhsVertexType(sigName) -> just(sigName)
      | _ -> nothing()
      end;
    impl :: (String, [(String, String)]) <- ctx.pcGraph.implementedSigChildren;
    guard(!isExportedBy(sourceGrammar, [substring(0, lastIndexOf(":", impl.1), impl.1)], compiledGrammars));
    sn :: String <- lookup(sigName, impl.2);
    let dispatchGraph = findProductionGraph(impl.1, ctx.pcProdGraphs);
    let dispatchVertex = rhsInhVertex(sn, ctx.pcAttr);
    guard(unknownImplMaySupply(dispatchGraph, dispatchVertex));
    return (impl.1, dispatchChildAllowedInhs(dispatchGraph, dispatchVertex));
  };

-- The decoration sites of the tree at v named in a message about an override equation on that tree,
-- the forward where the tree may become the forward through locals, and the applications of
-- capDispatch, a dispatch signature that bounds it.
fun overrideDecSitesPP
String ::= prod::String  v::VertexType  capDispatch::Maybe<String>  flowEnv::FlowEnv =
  implode(", ",
    map((.vertexPP), nub(
      lookupRefPossibleDecSites(prod, v, flowEnv) ++
      (if mayBecomeForward(prod, v, flowEnv) then [forwardVertexType()] else []))) ++
    map(\ dispatch::String -> s"applications of dispatch ${dispatch}", catMaybes([capDispatch])));

-- Where a bound on an override equation comes from: the forward rule, an extension implementation's
-- dispatch signature (see implCapInhs), or a decoration site of the overridden tree.
data BoundSource = fwdRuleBound | capBound dispatch::String | decSiteBound d::VertexType;

fun isDecSiteBound Boolean ::= b::BoundSource =
  case b of
  | decSiteBound(_) -> true
  | _ -> false
  end;

fun depListStr String ::= deps::set:Set<String> =
  case set:toList(deps) of
  | [] -> "on no left-side inherited attributes"
  | deps -> "only on " ++ implode(", ", deps)
  end;
