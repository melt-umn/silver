grammar silver:compiler:analysis:warnings:flow;

aspect production synthesizedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
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
  local dispatchHostSigDeps:: Maybe<set:Set<FlowVertex>> = do {
    dispatchSig :: NamedSignature <- implementedSig;
    guard(!isExportedBy(
      top.frame.sourceGrammar,
      [substring(0, lastIndexOf(":", dispatchSig.fullName), dispatchSig.fullName)],
      top.compiledGrammars));
    return set:fromList(flatMap(fromDispatchSigVertex(dispatchSig, ns, _),
      set:toList(findProductionGraph(dispatchSig.fullName, myGraphs).tileEdgeMap(
        lhsSynVertex(attr.attrDcl.fullName)))));
  };

  local sigNames::[String] =
    take(length(fromMaybe(ns, implementedSig).inputNames), ns.inputNames);
  local tileSigDeps::set:Set<FlowVertex> =
    expandTileGraphSigDeps(e.flowDeps, sigNames, top.frame.flowGraph);

  -- problem = lhsinh deps - inh deps on host implementation prods
  local tileSigDepsExceedsDispatchHostSigDeps :: [FlowVertex] =
    case dispatchHostSigDeps of
    | just(deps) -> set:toList(set:difference(tileSigDeps, deps))
    | _ -> []
    end;

  top.errors <-
    case implementedSig of
    | just(sig)
        when top.config.warnMissingInh
        && !null(tileSigDepsExceedsDispatchHostSigDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Synthesized override equation for ${attr.attrDcl.fullName} has excess dependencies on\n" ++
        flatMap(\ v::FlowVertex -> s"\t${v.vertexName}\n", tileSigDepsExceedsDispatchHostSigDeps) ++
        s"In host-language implementations of dispatch ${sig.fullName}, it depends ${depVertexListStr(dispatchHostSigDeps.fromJust)}")]
    | _ -> []
    end;
}

aspect production inheritedAttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  -- Make sure we aren't introducing any hidden transitive dependencies.

  local vertexHasHideableEq :: (Boolean ::= VertexType String) =
    possibleDecSiteHasInhEq(top.frame.fullName, _, _, myGraphs, top.flowEnv, top.env);

  local refDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    if isExportedBy(top.grammarName, [attr.attrDcl.sourceGrammar], top.compiledGrammars)
    then nothing()
    else case filter(
      vertexHasHideableEq(_, attr.attrDcl.fullName),
      lookupRefPossibleDecSites(top.frame.fullName, dl.defLHSVertex, top.flowEnv)) of
    | [] -> nothing()
    | vs -> just(onlyLhsInh(expandGraph(
        dl.defLHSVertex.eqVertex ++
        map(\ v::VertexType -> v.inhVertex(attr.attrDcl.fullName), vs),
        top.frame.flowGraph)))
    end;

  local transBaseRefDecSiteInhDepsLhsInh :: Maybe<set:Set<String>> =
    case dl.defLHSVertex of
    | transAttrVertexType(v, transAttr)
        when !isExportedBy(
          top.grammarName,
          [attr.attrDcl.sourceGrammar, substring(0, lastIndexOf(":", transAttr), transAttr)],
          top.compiledGrammars) ->
      case filter(
        vertexHasHideableEq(_, dl.inhAttrName),
        lookupRefPossibleDecSites(top.frame.fullName, v, top.flowEnv)) of
      | [] -> nothing()
      | vs -> just(onlyLhsInh(expandGraph(
          v.eqVertex ++
          map(\ v::VertexType -> v.inhVertex(dl.inhAttrName), vs),
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
  local dispatchHostSigDeps:: Maybe<set:Set<FlowVertex>> = do {
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
    return set:fromList(flatMap(fromDispatchSigVertex(dispatchSig, ns, _),
      rhsEqVertex(sigName) ::  -- TODO: Workaround: the rhs inh vertex should depend on the rhs eq vertex already!
      set:toList(findProductionGraph(dispatchSig.fullName, myGraphs).tileEdgeMap(dispatchVertex))));
  };

  local sigNames::[String] =
    take(length(fromMaybe(ns, implementedSig).inputNames), ns.inputNames);
  local tileSigDeps::set:Set<FlowVertex> =
    expandTileGraphSigDeps(e.flowDeps, sigNames, top.frame.flowGraph);

  -- problem = lhsinh deps - inh deps on host implementation prods
  local tileSigDepsExceedsDispatchHostSigDeps :: [FlowVertex] =
    case dispatchHostSigDeps of
    | just(deps) -> set:toList(set:difference(tileSigDeps, deps))
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
        && !null(tileSigDepsExceedsDispatchHostSigDeps) ->
      [mwdaWrnFromOrigin(top,
        s"Inherited override equation for ${attr.attrDcl.fullName} on ${dl.defLHSVertex.vertexPP} has excess dependencies on\n" ++
        flatMap(\ v::FlowVertex -> s"\t${v.vertexName}\n", tileSigDepsExceedsDispatchHostSigDeps) ++
        s"In host-language implementations of dispatch ${sig.fullName}, it depends ${depVertexListStr(dispatchHostSigDeps.fromJust)}")]
    | _ -> []
    end;
}

-- TODO: technically we should also repeat the above checks for collection equations


fun depListStr String ::= deps::set:Set<String> =
  case set:toList(deps) of
  | [] -> "on no left-side inherited attributes"
  | deps -> "only on " ++ implode(", ", deps)
  end;

fun depVertexListStr String ::= deps::set:Set<FlowVertex> =
  case set:toList(deps) of
  | [] -> "on nothing"
  | deps -> "only on\n" ++ flatMap(\ v::FlowVertex -> s"\t${v.vertexName}\n", deps)
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

fun fromDispatchSigVertex
[FlowVertex] ::= dispatchSig::NamedSignature prodSig::NamedSignature v::FlowVertex =
  case v of
  | lhsSynVertex(_) -> [v]
  | lhsInhVertex(_) -> [v]
  | rhsEqVertex(sn) ->
    [rhsEqVertex(head(drop(positionOf(sn, dispatchSig.inputNames), prodSig.inputNames)))]
  | rhsSynVertex(sn, a) ->
    [rhsSynVertex(head(drop(positionOf(sn, dispatchSig.inputNames), prodSig.inputNames)), a)]
  | rhsInhVertex(sn, a) ->
    [rhsInhVertex(head(drop(positionOf(sn, dispatchSig.inputNames), prodSig.inputNames)), a)]
  | _ -> []
  end;

