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
  forwards to rest;
}
aspect function parseArgs
Either<String  Decorated CmdArgs> ::= args::[String]
{
  flags <- [
    flagSpec(name="--warn-sharing", paramString=nothing(),
      help="warn about improper use of tree sharing",
      flagParser=flag(warnSharingFlag))];
}

-- TODO: Handle dependencies for inh overrides on forward/forward prod attrs
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
  top.errors <-
    case e.flowVertexInfo of
    | nothing() when top.config.warnSharing && sigIsShared ->
      [mwdaWrnFromOrigin(e, "Shared tree must correspond to a known decoration site")]
    | _ -> []
    end;
}
