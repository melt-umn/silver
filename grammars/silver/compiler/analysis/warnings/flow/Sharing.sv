grammar silver:compiler:analysis:warnings:flow;

synthesized attribute warnSharing :: Boolean occurs on CmdArgs;

aspect production endCmdArgs
top::CmdArgs ::= l::[String]
{
  top.warnSharing = false;
}
abstract production warnSharingFlag
top::CmdArgs ::= rest::CmdArgs
{
  top.warnSharing = true;
  forwards to @rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--warn-sharing", paramString=nothing(),
      help="warn about improper use of tree sharing",
      flagParser=flag(warnSharingFlag))];
}

aspect production decorationSiteExpr
top::Expr ::=  '@' e::Expr
{
  -- Check that we are exported by the decoration site.
  top.errors <-
    case e.flowVertexInfo of
    | just(v) when
        top.config.warnSharing &&
        !isExportedBy(top.grammarName, vertexGrammars(v, top.frame, top.env), top.compiledGrammars) ->
      [mwdaWrnFromOrigin(top, s"Orphaned sharing of ${v.vertexPP} in production ${top.frame.fullName}.")]
    | _ -> []
    end;
}

-- TODO: Handle dependencies for inh overrides on forward/forward prod attrs
-- TODO: I forgot what the above TODO was about
aspect production productionReference
top::Expr ::= @q::QName
{
  top.errors <-
    if !top.config.warnSharing
    || !q.lookupValue.found
    || !any(map((.elementShared), q.lookupValue.dcl.namedSignature.inputElements))
    || q.lookupValue.dcl.implementedSignature.isJust
    then []
    else case top.decSiteVertexInfo of
    | just(forwardVertexType_real()) -> []
    | just(localVertexType(fName)) when isForwardProdAttr(fName, top.env) -> []
    | _ -> [mwdaWrnFromOrigin(top, s"Production ${q.name} has shared children in its signature, and can only be applied in the root position of a forward or forward production attribute equation.")]
    end;
}

aspect production dispatchApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  top.errors <-
    if !top.config.warnSharing
    then []
    else case top.decSiteVertexInfo of
    | just(forwardVertexType_real()) -> []
    | just(localVertexType(fName)) when isForwardProdAttr(fName, top.env) -> []
    | _ -> [mwdaWrnFromOrigin(e, s"Dispatch can only be applied in the root position of a forward or forward production attribute equation.")]
    end;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  -- Check that we are exported by the decoration site.
  top.errors <-
    case e.flowVertexInfo of
    | just(v) when
        top.config.warnSharing &&
        sigIsShared && isForwardParam &&
        !isExportedBy(top.grammarName, vertexGrammars(v, top.frame, top.env), top.compiledGrammars) ->
      [mwdaWrnFromOrigin(top, s"Orphaned sharing of ${v.vertexPP} in production ${top.frame.fullName}.")]
    | _ -> []
    end;
}

-- Grammars that can validly share a vertex
fun vertexGrammars [String] ::= v::VertexType frame::BlockContext env::Env =
  case v of
  | rhsVertexType(_) -> [frame.sourceGrammar]
  | localVertexType(fName) when getValueDcl(fName, env) matches valDcl :: _ -> [valDcl.sourceGrammar]
  | transAttrVertexType(rhsVertexType(sigName), transAttr) ->
    frame.sourceGrammar ::
    case lookup(sigName, zip(frame.signature.inputNames, frame.signature.inputTypes)) of
    | just(t) when getOccursDcl(transAttr, t.typeName, env) matches dcl :: _ ->
      [dcl.sourceGrammar]
    | _ -> []
    end
  | transAttrVertexType(localVertexType(fName), transAttr)
      when getValueDcl(fName, env) matches valDcl :: _ ->
    valDcl.sourceGrammar ::
    case getOccursDcl(transAttr, valDcl.typeScheme.monoType.typeName, env) of
    | dcl :: _ -> [dcl.sourceGrammar]
    | _ -> []
    end
  | _ -> []
  end;
