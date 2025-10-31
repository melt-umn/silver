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
        !isExportedBy(top.grammarName, vertexGrammars(top.env, top.frame.fullName, v), top.compiledGrammars) ->
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
    else case top.appDecSiteVertexInfo of
    | just(forwardVertexType()) -> []
    | just(localVertexType(fName))
        when isForwardProdAttr(top.frame.fullName, fName, top.flowEnv) -> []
    | _ -> [mwdaWrnFromOrigin(top, s"Non-dispatch production ${q.name} has shared children in its signature, and can only be referenced by applying it in the root position of a forward or forward production attribute equation.")]
    end;
}

aspect production dispatchApplication
top::Expr ::= @e::Expr @es::AppExprs @anns::AnnoAppExprs
{
  top.errors <-
    if !top.config.warnSharing
    then []
    else
      case e.finalType of
      | dispatchType(ns) when any(map((.elementShared), ns.inputElements)) ->
        case top.decSiteVertexInfo of
        | just(forwardVertexType()) -> []
        | just(localVertexType(fName))
            when isForwardProdAttr(top.frame.fullName, fName, top.flowEnv) -> []
        | _ -> [mwdaWrnFromOrigin(e, s"Dispatch ${ns.fullName} has shared children in its signature, and can only be applied in the root position of a forward or forward production attribute equation.")]
        end
      | _ -> []
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
        !isExportedBy(top.grammarName, vertexGrammars(top.env, top.frame.fullName, v), top.compiledGrammars) ->
      [mwdaWrnFromOrigin(top, s"Orphaned sharing of ${v.vertexPP} in production ${top.frame.fullName}.")]
    | _ -> []
    end;
}

-- Grammars that can validly share a vertex
fun vertexGrammars [String] ::= env::Env prod::String v::VertexType =
  case v of
  | rhsVertexType(_) -> [substring(0, lastIndexOf(":", prod), prod)]
  | localVertexType(fName) when getValueDcl(fName, env) matches valDcl :: _ -> [valDcl.sourceGrammar]
  | transAttrVertexType(rhsVertexType(sigName), transAttr)
      when getValueDcl(prod, env) matches prdDcl :: _ ->
    prdDcl.sourceGrammar ::
    case getOccursDcl(transAttr, lookupSignatureInputElem(sigName, prdDcl.namedSignature).typerep.typeName, env) of
    | dcl :: _ -> [dcl.sourceGrammar]
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
