grammar silver:compiler:analysis:warnings:flow;

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
 - the dependencies of the shared tree's value are only expanded once per site.
 -
 - This is a weak heuristic, because we do not distinguish between eq and outer-eq
 - dependencies in the flow types of translation attributes, and they can have flow types
 - like `toCore {toCore.env}` (this can be legal if a circularity exists in a non-root
 - position of a translation attribute equation.)  In this case we want to avoid flagging
 - apparent cycles involving the decoration site's outer-eq dependencies, because they might
 - just be coming from the stitch point of an access of the translation attribute,
 - and since the graphs are built as transitive closures, there isn't an efficient way to
 - check for a path in the flow graph while excluding some edges.
 -}
function sharingSiteDependsOnInh
(Boolean ::= String) ::= ref::VertexType  decSite::VertexType  graph::ProductionGraph
{
  local refDeps :: set:Set<FlowVertex> = expandGraph(ref.eqDeps ++ ref.outerEqDeps, graph);
  return \ i::String -> set:contains(ref.inhVertex(i), expandGraph(
      if set:contains(ref.inhVertex(i), refDeps)
      then [decSite.inhVertex(i)]
      else decSite.inhDeps(i),
    graph));
}

aspect production decorationSiteExpr
top::Expr ::= '@' e::Expr
{
  -- oh no again!
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  -- Check for cycles due to missing inherited equations on shared trees.
  -- We need to check inherited attributes known locally to occur on the shared tree,
  -- that don't have an inherited equation defined locally, and thus may be involved in a cycle.
  -- We don't need *all* inherited attributes, because only the ones we know about
  -- could be a dependency for constructing the decoration site tree.
  top.errors <-
    case top.decSiteVertexInfo, e.flowVertexInfo of
    | _, just(localVertexType(fName)) when isForwardProdAttr(top.frame.fullName, fName, top.flowEnv) -> []
    | just(decSite), just(ref) when top.config.warnSharingCycles ->
      let dependsOnInh :: (Boolean ::= String) = sharingSiteDependsOnInh(ref, decSite, top.frame.flowGraph)
      in flatMap(\ i::String ->
        if !vertexHasInhEq(top.frame.fullName, ref, i, top.flowEnv)
        && decSiteHasInhEq(top.frame.fullName, decSite, i, myGraphs, top.flowEnv, top.env)
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

  -- Same checks as for decorationSiteExpr, for application of a prod with a shared signature param.
  top.errors <-
    case sigDecSite, e.flowVertexInfo of
    | just(decSite), just(ref)
        when top.config.warnSharingCycles && sigIsShared && isForwardParam ->
      let dependsOnInh :: (Boolean ::= String) = sharingSiteDependsOnInh(ref, decSite, top.frame.flowGraph)
      in flatMap(\ i::String ->
        if !vertexHasInhEq(top.frame.fullName, ref, i, top.flowEnv)
        && decSiteHasInhEq(top.frame.fullName, decSite, i, myGraphs, top.flowEnv, top.env)
        && dependsOnInh(i)
        then [mwdaWrnFromOrigin(top, s"Potentially missing inherited override equation for ${i} on ${ref.vertexName}; a cycle may exist via its sharing decoration site ${decSite.vertexName}")]
        else [],
        getInhAndInhOnTransAttrsOn(e.finalType.typeName, top.env))
      end
    | _, _ -> []
    end;
}
