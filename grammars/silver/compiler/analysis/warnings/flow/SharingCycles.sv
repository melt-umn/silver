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
    | just(decSite), just(ref) when top.config.warnSharingCycles -> flatMap(\ i::String ->
        if !vertexHasInhEq(top.frame.fullName, ref, i, top.flowEnv)
        && decSiteHasInhEq(top.frame.fullName, decSite, i, myGraphs, top.flowEnv, top.env)
        && contains(ref.inhVertex(i), expandGraph(decSite.inhVertex(i) :: decSite.eqVertex, top.frame.flowGraph))
        then [mwdaWrnFromOrigin(top, s"Potentially missing inherited override equation for ${i} on ${ref.vertexName}; a cycle may exist via its sharing decoration site ${decSite.vertexName}")]
        else [],
        getInhAndInhOnTransAttrsOn(e.finalType.typeName, top.env))
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
      flatMap(\ i::String ->
        if !vertexHasInhEq(top.frame.fullName, ref, i, top.flowEnv)
        && decSiteHasInhEq(top.frame.fullName, decSite, i, myGraphs, top.flowEnv, top.env)
        && contains(ref.inhVertex(i), expandGraph(decSite.inhVertex(i) :: decSite.eqVertex, top.frame.flowGraph))
        then [mwdaWrnFromOrigin(top, s"Potentially missing inherited override equation for ${i} on ${ref.vertexName}; a cycle may exist via its sharing decoration site ${decSite.vertexName}")]
        else [],
        getInhAndInhOnTransAttrsOn(e.finalType.typeName, top.env))
    | _, _ -> []
    end;
}
