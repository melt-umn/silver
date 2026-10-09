grammar silver:compiler:analysis:warnings:flow;


aspect production inheritedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  -- Make sure we aren't introducing any hidden transitive dependencies.

  -- oh no again!
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local vertexHasHideableEq :: (Boolean ::= VertexType String) =
    possibleDecSiteHasInhEq(top.frame.fullName, _, _, myGraphs, top.flowEnv, top.env);

  local refDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case filter(vertexHasHideableEq(_, attr.attrDcl.fullName), dl.defLHSDecSites) of
    | [] -> nothing()
    | vs -> just(onlyLhsInh(expandGraph(
        dl.defLHSVertex.outerEqDeps ++
        flatMap(decSiteOwnInhDeps(top.frame.flowGraph, _, attr.attrDcl.fullName, myGraphs), vs) ++
        flatMap((.outerEqDeps), vs),
        top.frame.flowGraph)))
    end;

  local transBaseRefDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr) ->
      case filter(vertexHasHideableEq(_, dl.inhAttrName), dl.defLHSTransBaseDecSites) of
      | [] -> nothing()
      | vs -> just(onlyLhsInh(expandGraph(
          v.outerEqDeps ++
          flatMap(decSiteOwnInhDeps(top.frame.flowGraph, _, dl.inhAttrName, myGraphs), vs) ++
          flatMap((.outerEqDeps), vs),
          top.frame.flowGraph)))
      end
    | _ -> nothing()
    end;

  -- problem = lhsinh deps - inh deps on dec site
  local lhsInhExceedsRefDecSiteDeps :: [String] =
    case refDecSiteInhDepsLhsInh of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  local lhsInhExceedsTransBaseRefDecSiteDeps :: [String] =
    case transBaseRefDecSiteInhDepsLhsInh of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;
  
  -- Extension productions that implement a dispatch signature

  local ns :: NamedSignature =  -- top.frame.signature might have aspect sig names that don't match the flow env
    case getValueDcl(top.frame.fullName, top.env) of
    | dcl :: _ -> dcl.namedSignature
    | _ -> error("didn't find a decl for prod " ++ top.frame.fullName)
    end;
  local implementedSig :: Maybe<NamedSignature> =
    case getValueDcl(top.frame.fullName, top.env) of
    | dcl :: _ -> dcl.implementedSignature
    | _ -> nothing()
    end;
  local dispatchSigDeps :: Maybe<set:Set<String>> = do {
    dispatchSig :: NamedSignature <- implementedSig;
    guard(!isExportedBy(
      top.frame.sourceGrammar,
      [substring(0, lastIndexOf(":", dispatchSig.fullName), dispatchSig.fullName)],
      top.compiledGrammars));
    sigName <-
      case dl.defLHSVertex of
      | rhsVertexType(sigName) -> just(sigName)
      | transAttrVertexType(rhsVertexType(sigName), _) -> just(sigName)
      | _ -> nothing()
      end;
    let sigPos = positionOf(sigName, ns.inputNames);
    when_(sigPos < 0, error("sigName lookup failed"));
    guard(sigPos < length(dispatchSig.inputElements));
    let dispatchVertex = rhsInhVertex(
      head(drop(sigPos, dispatchSig.inputElements)).elementName,
      dl.inhAttrName);
    -- The LHS inherited attributes that this equation may depend on: what both applications of the dispatch signature
    -- and the implementations this child may be passed on to allow (see dispatchChildAllowedInhs).  These include the
    -- dependencies of host-language implementations' equations for the same child and attribute, and the forward flow
    -- type (see addDispatchUnknownImplInhEqs.)
    return dispatchChildAllowedInhs(findProductionGraph(dispatchSig.fullName, myGraphs), dispatchVertex);
  };

  -- problem = lhsinh deps - lhsinh deps allowed by applications of the dispatch signature
  local lhsInhExceedsDispatchSigDeps :: [String] =
    case dispatchSigDeps of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  top.errors <-
    if top.config.warnMissingInh
    && !null(lhsInhExceedsRefDecSiteDeps)
    then
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} may exceed a flow type " ++
        s"with hidden transitive dependencies on ${implode(", ", lhsInhExceedsRefDecSiteDeps)}; " ++
        s"on some reference to this tree, this attribute may be expected to depend ${depListStr(refDecSiteInhDepsLhsInh.fromJust)}" ++
        s" (from ${implode(", ", map((.vertexPP), lookupRefPossibleDecSites(top.frame.fullName, dl.defLHSVertex, top.flowEnv)))})")]
    else [];
  top.errors <-
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr)
        when top.config.warnMissingInh
        && !null(lhsInhExceedsTransBaseRefDecSiteDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${transAttr}.${attr.attrDcl.fullName} on ${v.vertexPP} may exceed a flow type " ++
        s"with hidden transitive dependencies on ${implode(", ", lhsInhExceedsTransBaseRefDecSiteDeps)}; " ++
        s"on some reference to this tree, this attribute may be expected to depend ${depListStr(transBaseRefDecSiteInhDepsLhsInh.fromJust)}" ++
        s" (from ${implode(", ", map((.vertexPP), lookupRefPossibleDecSites(top.frame.fullName, v, top.flowEnv)))})")]
    | _ -> []
    end;
  top.errors <-
    case implementedSig of
    | just(sig)
        when top.config.warnMissingInh
        && !null(lhsInhExceedsDispatchSigDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} has excess dependencies on " ++
        s"${implode(", ", lhsInhExceedsDispatchSigDeps)}; applications of dispatch ${sig.fullName} allow it to depend " ++
        depListStr(dispatchSigDeps.fromJust))]
    | _ -> []
    end;
}

-- TODO: massive copy/paste section for collection equations:

aspect production inhBaseColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  -- Make sure we aren't introducing any hidden transitive dependencies.

  -- oh no again!
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local vertexHasHideableEq :: (Boolean ::= VertexType String) =
    possibleDecSiteHasInhEq(top.frame.fullName, _, _, myGraphs, top.flowEnv, top.env);

  local refDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case filter(vertexHasHideableEq(_, attr.attrDcl.fullName), dl.defLHSDecSites) of
    | [] -> nothing()
    | vs -> just(onlyLhsInh(expandGraph(
        dl.defLHSVertex.outerEqDeps ++
        flatMap(decSiteOwnInhDeps(top.frame.flowGraph, _, attr.attrDcl.fullName, myGraphs), vs) ++
        flatMap((.outerEqDeps), vs),
        top.frame.flowGraph)))
    end;

  local transBaseRefDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr) ->
      case filter(vertexHasHideableEq(_, dl.inhAttrName), dl.defLHSTransBaseDecSites) of
      | [] -> nothing()
      | vs -> just(onlyLhsInh(expandGraph(
          v.outerEqDeps ++
          flatMap(decSiteOwnInhDeps(top.frame.flowGraph, _, dl.inhAttrName, myGraphs), vs) ++
          flatMap((.outerEqDeps), vs),
          top.frame.flowGraph)))
      end
    | _ -> nothing()
    end;

  -- problem = lhsinh deps - inh deps on dec site
  local lhsInhExceedsRefDecSiteDeps :: [String] =
    case refDecSiteInhDepsLhsInh of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  local lhsInhExceedsTransBaseRefDecSiteDeps :: [String] =
    case transBaseRefDecSiteInhDepsLhsInh of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;
  
  -- Extension productions that implement a dispatch signature

  local ns :: NamedSignature =  -- top.frame.signature might have aspect sig names that don't match the flow env
    case getValueDcl(top.frame.fullName, top.env) of
    | dcl :: _ -> dcl.namedSignature
    | _ -> error("didn't find a decl for prod " ++ top.frame.fullName)
    end;
  local implementedSig :: Maybe<NamedSignature> =
    case getValueDcl(top.frame.fullName, top.env) of
    | dcl :: _ -> dcl.implementedSignature
    | _ -> nothing()
    end;
  local dispatchSigDeps :: Maybe<set:Set<String>> = do {
    dispatchSig :: NamedSignature <- implementedSig;
    guard(!isExportedBy(
      top.frame.sourceGrammar,
      [substring(0, lastIndexOf(":", dispatchSig.fullName), dispatchSig.fullName)],
      top.compiledGrammars));
    sigName <-
      case dl.defLHSVertex of
      | rhsVertexType(sigName) -> just(sigName)
      | transAttrVertexType(rhsVertexType(sigName), _) -> just(sigName)
      | _ -> nothing()
      end;
    let sigPos = positionOf(sigName, ns.inputNames);
    when_(sigPos < 0, error("sigName lookup failed"));
    guard(sigPos < length(dispatchSig.inputElements));
    let dispatchVertex = rhsInhVertex(
      head(drop(sigPos, dispatchSig.inputElements)).elementName,
      dl.inhAttrName);
    -- The LHS inherited attributes that this equation may depend on: what both applications of the dispatch signature
    -- and the implementations this child may be passed on to allow (see dispatchChildAllowedInhs).  These include the
    -- dependencies of host-language implementations' equations for the same child and attribute, and the forward flow
    -- type (see addDispatchUnknownImplInhEqs.)
    return dispatchChildAllowedInhs(findProductionGraph(dispatchSig.fullName, myGraphs), dispatchVertex);
  };

  -- problem = lhsinh deps - lhsinh deps allowed by applications of the dispatch signature
  local lhsInhExceedsDispatchSigDeps :: [String] =
    case dispatchSigDeps of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  top.errors <-
    if top.config.warnMissingInh
    && !null(lhsInhExceedsRefDecSiteDeps)
    then
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} may exceed a flow type " ++
        s"with hidden transitive dependencies on ${implode(", ", lhsInhExceedsRefDecSiteDeps)}; " ++
        s"on some reference to this tree, this attribute may be expected to depend ${depListStr(refDecSiteInhDepsLhsInh.fromJust)}" ++
        s" (from ${implode(", ", map((.vertexPP), lookupRefPossibleDecSites(top.frame.fullName, dl.defLHSVertex, top.flowEnv)))})")]
    else [];
  top.errors <-
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr)
        when top.config.warnMissingInh
        && !null(lhsInhExceedsTransBaseRefDecSiteDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${transAttr}.${attr.attrDcl.fullName} on ${v.vertexPP} may exceed a flow type " ++
        s"with hidden transitive dependencies on ${implode(", ", lhsInhExceedsTransBaseRefDecSiteDeps)}; " ++
        s"on some reference to this tree, this attribute may be expected to depend ${depListStr(transBaseRefDecSiteInhDepsLhsInh.fromJust)}" ++
        s" (from ${implode(", ", map((.vertexPP), lookupRefPossibleDecSites(top.frame.fullName, v, top.flowEnv)))})")]
    | _ -> []
    end;
  top.errors <-
    case implementedSig of
    | just(sig)
        when top.config.warnMissingInh
        && !null(lhsInhExceedsDispatchSigDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} has excess dependencies on " ++
        s"${implode(", ", lhsInhExceedsDispatchSigDeps)}; applications of dispatch ${sig.fullName} allow it to depend " ++
        depListStr(dispatchSigDeps.fromJust))]
    | _ -> []
    end;
}
aspect production inhAppendColAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  -- Make sure we aren't introducing any hidden transitive dependencies.

  -- oh no again!
  local myGraphs::EnvTree<ProductionGraph> = head(searchEnvTree(top.grammarName, top.compiledGrammars)).productionFlowGraphs;

  local vertexHasHideableEq :: (Boolean ::= VertexType String) =
    possibleDecSiteHasInhEq(top.frame.fullName, _, _, myGraphs, top.flowEnv, top.env);

  local refDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case filter(vertexHasHideableEq(_, attr.attrDcl.fullName), dl.defLHSDecSites) of
    | [] -> nothing()
    | vs -> just(onlyLhsInh(expandGraph(
        dl.defLHSVertex.outerEqDeps ++
        flatMap(decSiteOwnInhDeps(top.frame.flowGraph, _, attr.attrDcl.fullName, myGraphs), vs) ++
        flatMap((.outerEqDeps), vs),
        top.frame.flowGraph)))
    end;

  local transBaseRefDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr) ->
      case filter(vertexHasHideableEq(_, dl.inhAttrName), dl.defLHSTransBaseDecSites) of
      | [] -> nothing()
      | vs -> just(onlyLhsInh(expandGraph(
          v.outerEqDeps ++
          flatMap(decSiteOwnInhDeps(top.frame.flowGraph, _, dl.inhAttrName, myGraphs), vs) ++
          flatMap((.outerEqDeps), vs),
          top.frame.flowGraph)))
      end
    | _ -> nothing()
    end;

  -- problem = lhsinh deps - inh deps on dec site
  local lhsInhExceedsRefDecSiteDeps :: [String] =
    case refDecSiteInhDepsLhsInh of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  local lhsInhExceedsTransBaseRefDecSiteDeps :: [String] =
    case transBaseRefDecSiteInhDepsLhsInh of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;
  
  -- Extension productions that implement a dispatch signature

  local ns :: NamedSignature =  -- top.frame.signature might have aspect sig names that don't match the flow env
    case getValueDcl(top.frame.fullName, top.env) of
    | dcl :: _ -> dcl.namedSignature
    | _ -> error("didn't find a decl for prod " ++ top.frame.fullName)
    end;
  local implementedSig :: Maybe<NamedSignature> =
    case getValueDcl(top.frame.fullName, top.env) of
    | dcl :: _ -> dcl.implementedSignature
    | _ -> nothing()
    end;
  local dispatchSigDeps :: Maybe<set:Set<String>> = do {
    dispatchSig :: NamedSignature <- implementedSig;
    guard(!isExportedBy(
      top.frame.sourceGrammar,
      [substring(0, lastIndexOf(":", dispatchSig.fullName), dispatchSig.fullName)],
      top.compiledGrammars));
    sigName <-
      case dl.defLHSVertex of
      | rhsVertexType(sigName) -> just(sigName)
      | transAttrVertexType(rhsVertexType(sigName), _) -> just(sigName)
      | _ -> nothing()
      end;
    let sigPos = positionOf(sigName, ns.inputNames);
    when_(sigPos < 0, error("sigName lookup failed"));
    guard(sigPos < length(dispatchSig.inputElements));
    let dispatchVertex = rhsInhVertex(
      head(drop(sigPos, dispatchSig.inputElements)).elementName,
      dl.inhAttrName);
    -- The LHS inherited attributes that this equation may depend on: what both applications of the dispatch signature
    -- and the implementations this child may be passed on to allow (see dispatchChildAllowedInhs).  These include the
    -- dependencies of host-language implementations' equations for the same child and attribute, and the forward flow
    -- type (see addDispatchUnknownImplInhEqs.)
    return dispatchChildAllowedInhs(findProductionGraph(dispatchSig.fullName, myGraphs), dispatchVertex);
  };

  -- problem = lhsinh deps - lhsinh deps allowed by applications of the dispatch signature
  local lhsInhExceedsDispatchSigDeps :: [String] =
    case dispatchSigDeps of
    | just(deps) -> set:toList(set:difference(lhsInhDeps, deps))
    | _ -> []
    end;

  top.errors <-
    if top.config.warnMissingInh
    && !null(lhsInhExceedsRefDecSiteDeps)
    then
      [mwdaWrnFromOrigin(top,
        s"Inherited contribution equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} may exceed a flow type " ++
        s"with hidden transitive dependencies on ${implode(", ", lhsInhExceedsRefDecSiteDeps)}; " ++
        s"on some reference to this tree, this attribute may be expected to depend ${depListStr(refDecSiteInhDepsLhsInh.fromJust)}" ++
        s" (from ${implode(", ", map((.vertexPP), lookupRefPossibleDecSites(top.frame.fullName, dl.defLHSVertex, top.flowEnv)))})")]
    else [];
  top.errors <-
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr)
        when top.config.warnMissingInh
        && !null(lhsInhExceedsTransBaseRefDecSiteDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited contribution equation for ${transAttr}.${attr.attrDcl.fullName} on ${v.vertexPP} may exceed a flow type " ++
        s"with hidden transitive dependencies on ${implode(", ", lhsInhExceedsTransBaseRefDecSiteDeps)}; " ++
        s"on some reference to this tree, this attribute may be expected to depend ${depListStr(transBaseRefDecSiteInhDepsLhsInh.fromJust)}" ++
        s" (from ${implode(", ", map((.vertexPP), lookupRefPossibleDecSites(top.frame.fullName, v, top.flowEnv)))})")]
    | _ -> []
    end;
  top.errors <-
    case implementedSig of
    | just(sig)
        when top.config.warnMissingInh
        && !null(lhsInhExceedsDispatchSigDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited contribution equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} has excess dependencies on " ++
        s"${implode(", ", lhsInhExceedsDispatchSigDeps)}; applications of dispatch ${sig.fullName} allow it to depend " ++
        depListStr(dispatchSigDeps.fromJust))]
    | _ -> []
    end;
}


fun depListStr String ::= deps::set:Set<String> =
  case set:toList(deps) of
  | [] -> "on no left-side inherited attributes"
  | deps -> "only on " ++ implode(", ", deps)
  end;

fun vertexHasPossibleInhEq Boolean ::= v::VertexType env::Env =
  case v of
  | subtermVertexType(_, prodName, sigName) ->
      case getTypeDcl(prodName, env), getValueDcl(prodName, env) of
      | dcl :: _, _ -> !lookupSignatureInputElem(sigName, dcl.dispatchSignature).elementShared
      | _, dcl :: _ -> !lookupSignatureInputElem(sigName, dcl.namedSignature).elementShared
      | _, _ -> false
      end
  | _ -> true
  end;
