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

  top.errors <-
    case e.flowVertexInfo of
    -- These are errors because we assume these checks in the translation:
    | just(lhsVertexType_real()) -> [errFromOrigin(e, s"Cannot share the production LHS.")]
    | just(forwardVertexType_real()) -> [errFromOrigin(e, s"Cannot share the forward tree.")]
    | just(anonVertexType(_)) -> [errFromOrigin(e, s"Cannot share an anonymously decorated tree.")]  -- TODO: I think this works now?
    | just(v) ->
        -- Check that this tree is shared in at most one non-mutually-exclusive place.
        case lookupSharedRefs(top.frame.fullName, v, top.flowEnv) of
        | [] -> [] -- Not possible?
        | [_] -> []
        | srs -> [errFromOrigin(top,
          s"Tree ${v.vertexName} in production ${top.frame.fullName} is shared in multiple places:\n" ++
          flatMap(\ sr::SharedRefSite -> "\t" ++ sr.sourceGrammar ++ ":" ++ sr.sourceLocation.unparse ++ "\n", srs))]
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
  -- This mirrors the above, but for signature sharing:
  top.sharedRefs <-
    case e.flowVertexInfo of
    | just(v) when sigIsShared && isForwardParam ->
      [(top.frame.fullName ++ ":" ++ v.vertexName,
        sharedRefSite(
          sourceGrammar=top.grammarName,
          sourceLocation=getParsedOriginLocationOrFallback(top)
      ))]
    | _ -> []
    end;

  top.errors <-
    if sigIsShared && isForwardParam then
      case e.flowVertexInfo of
      -- These are errors because we assume these checks in the translation:
      | just(lhsVertexType_real()) -> [errFromOrigin(e, s"Cannot share the production LHS.")]
      | just(forwardVertexType_real()) -> [errFromOrigin(e, s"Cannot share the forward tree.")]
      | just(anonVertexType(_)) -> [errFromOrigin(e, s"Cannot share an anonymously decorated tree.")]  -- TODO: I think this works now?
      | just(v) ->
          -- Check that this tree is shared in at most one non-mutually-exclusive place.
          case lookupSharedRefs(top.frame.fullName, v, top.flowEnv) of
          | [] -> [] -- Not possible?
          | [_] -> []
          | srs -> [errFromOrigin(top,
            s"Tree ${v.vertexName} in production ${top.frame.fullName} is shared in multiple places:\n" ++
            flatMap(\ sr::SharedRefSite -> "\t" ++ sr.sourceGrammar ++ ":" ++ sr.sourceLocation.unparse ++ "\n", srs))]
          end
      | nothing() -> [errFromOrigin(e, s"This is not something that can be shared; shared trees must correspond to a known decoration site.")]
      end
    else [];

  top.errors <-
    case e.flowVertexInfo of
    | just(transAttrVertexType(v, transAttr)) when sigIsShared && isForwardParam ->
      case lookupSharedRefs(top.frame.fullName, v, top.flowEnv) of
      | sr :: _ ->
        [errFromOrigin(e, s"Cannot share ${v.vertexName}.${transAttr} in production ${top.frame.fullName}, because ${v.vertexPP} is also shared (at ${sr.sourceGrammar}:${sr.sourceLocation.unparse}).")]
      | _ -> []
      end
    | _ -> []
    end;
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
