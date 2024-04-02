grammar silver:compiler:analysis:uniqueness;

attribute sharedRefs occurs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern;
propagate sharedRefs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern
  excluding ifThenElse, matchPrimitiveReal, consPattern;

aspect production decorationSiteExpr
top::Expr ::=  '@' e::Expr
{
  top.sharedRefs <-
    case e.flowVertexInfo of
    | just(v) -> [(top.frame.fullName ++ ":" ++ v.vertexName, sharedRefSite(
        sourceGrammar=top.grammarName,
        sourceLocation=getParsedOriginLocationOrFallback(top)
      ))]
    | nothing() -> []
    end;

  -- Grammars that can validly share this vertex
  local vertexGrammars::[String] =
    case e.flowVertexInfo of
    | just(rhsVertexType(_)) -> [top.frame.sourceGrammar]
    | just(localVertexType(fName)) when getValueDcl(fName, top.env) matches valDcl :: _ -> [valDcl.sourceGrammar]
    | just(transAttrVertexType(rhsVertexType(sigName), transAttr)) ->
      top.frame.sourceGrammar ::
      case lookup(sigName, zip(top.frame.signature.inputNames, top.frame.signature.inputTypes)) of
      | just(t) when getOccursDcl(transAttr, t.typeName, top.env) matches dcl :: _ ->
        [dcl.sourceGrammar]
      | _ -> []
      end
    | just(transAttrVertexType(localVertexType(fName), transAttr)) ->
      implode(":", init(explode(":", fName))) ::
      case getValueDcl(fName, top.env) of
      | valDcl :: _ when getOccursDcl(transAttr, valDcl.typeScheme.monoType.typeName, top.env) matches dcl :: _ ->
        [dcl.sourceGrammar]
      | _ -> []
      end
    | _ -> []
    end;

  top.errors <-
    case e.flowVertexInfo of
    | just(lhsVertexType_real()) -> [errFromOrigin(e, s"Cannot share the production LHS.")]
    | just(forwardVertexType_real()) -> [errFromOrigin(e, s"Cannot share the forward tree.")]
    | just(anonVertexType(_)) -> [errFromOrigin(e, s"Cannot share an anonymously decorated tree.")]  -- TODO: Not too hard to support?
    | just(v) ->
      -- Check that we are exported by the decoration site.
      if !isExportedBy(top.grammarName, vertexGrammars, top.compiledGrammars)
      then [errFromOrigin(top, s"Orphaned sharing of ${v.vertexPP} in production ${top.frame.fullName}.")]
      -- Check that there is at most one unique reference taken to this decoration site.
      else
        case lookupSharedRefs(top.frame.fullName, v, top.flowEnv) of
        | [] -> [] -- Not possible?
        | [_] -> []
        | srs -> [errFromOrigin(top,
          s"Tree ${v.vertexName} in production ${top.frame.fullName} is shared in multiple places:\n" ++
          implode("\n", map(\ sr::SharedRefSite -> sr.sourceGrammar ++ ":" ++ sr.sourceLocation.unparse ++ "\n", srs)))]
        end
    | nothing() -> [errFromOrigin(e, s"This is not something that can be shared; shared trees must correspond to a known decoration site.")]
    end;
  
  top.errors <-
    case e.flowVertexInfo of
    | just(transAttrVertexType(v, transAttr))
        when lookupSharedRefs(top.frame.fullName, v, top.flowEnv) matches sr :: _ ->
      [errFromOrigin(e, s"Cannot share ${v.vertexName}.${transAttr} in production ${top.frame.fullName}, because ${v.vertexPP} is also shared (at ${sr.sourceGrammar}:${sr.sourceLocation.unparse}).")]
    | _ -> []
    end;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  -- TODO: Handle signature sharing
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.sharedRefs :=
    e1.sharedRefs ++
    unionMutuallyExclusiveRefs(e1.sharedRefs, e2.sharedRefs);
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  top.sharedRefs := e.sharedRefs ++ unionMutuallyExclusiveRefs(pr.sharedRefs, f.sharedRefs);
}
aspect production consPattern
top::PrimPatterns ::= p::PrimPattern _ ps::PrimPatterns
{
  top.sharedRefs := unionMutuallyExclusiveRefs(p.sharedRefs, ps.sharedRefs);
}
