grammar silver:compiler:analysis:uniqueness;

attribute sharedRefs occurs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern;
propagate sharedRefs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern
  excluding ifThenElse, matchPrimitiveReal, consPattern, letp;

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
    case top.decSiteVertexInfo of
    | just(_) -> []
    | nothing() -> [errFromOrigin(top, s"Cannot share a tree here; can only share in known positions of local, forward, and translation attribute equations.")]
    end;

  top.errors <-
    case e.flowVertexInfo of
    -- These are errors because we assume these checks in the translation:
    | just(lhsVertexType()) -> [errFromOrigin(e, s"Cannot share the production LHS.")]
    | just(forwardVertexType()) -> [errFromOrigin(e, s"Cannot share the forward tree.")]
    | just(anonVertexType(_, _, _)) -> [errFromOrigin(e, s"Cannot share an anonymously decorated tree.")]  -- TODO: I think this works now?
    | just(subtermVertexType(_, _, _)) -> [errFromOrigin(e, s"Cannot share a pattern variable.")]  -- Only way this can happen
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
    | just(vt) when sharedTransBase(top.frame.fullName, vt, top.flowEnv) matches just((v, sr)) ->
      [errFromOrigin(e, s"Cannot share ${vt.vertexName} in production ${top.frame.fullName}, because ${v.vertexPP} is also shared (at ${sr.sourceGrammar}:${sr.sourceLocation.unparse}).")]
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
      | just(lhsVertexType()) -> [errFromOrigin(e, s"Cannot share the production LHS.")]
      | just(forwardVertexType()) -> [errFromOrigin(e, s"Cannot share the forward tree.")]
      | just(anonVertexType(_, _, _)) -> [errFromOrigin(e, s"Cannot share an anonymously decorated tree.")]  -- TODO: I think this works now?
      | just(subtermVertexType(_, _, _)) -> [errFromOrigin(e, s"Cannot share a pattern variable.")]  -- Only way this can happen
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
    | just(vt) when sigIsShared && isForwardParam ->
      case sharedTransBase(top.frame.fullName, vt, top.flowEnv) of
      | just((v, sr)) ->
        [errFromOrigin(e, s"Cannot share ${vt.vertexName} in production ${top.frame.fullName}, because ${v.vertexPP} is also shared (at ${sr.sourceGrammar}:${sr.sourceLocation.unparse}).")]
      | nothing() -> []
      end
    | _ -> []
    end;
}

{--
 - If vt is a translation attribute (possibly of a translation attribute, and so on) of a tree that
 - is also shared in this production, that tree and one of its sharing sites.  Sharing both would
 - give the translation a second decoration site.
 -}
fun sharedTransBase Maybe<(VertexType, SharedRefSite)> ::= prod::String  vt::VertexType  e::FlowEnv =
  case vt of
  | transAttrVertexType(v, _) ->
    case lookupSharedRefs(prod, v, e) of
    | sr :: _ -> just((v, sr))
    | [] -> sharedTransBase(prod, v, e)
    end
  | _ -> nothing()
  end;

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.sharedRefs :=
    e1.sharedRefs ++
    unionMutuallyExclusiveRefs(e2.sharedRefs, e3.sharedRefs);
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

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  top.sharedRefs := e.sharedRefs;
}

aspect production lexicalLocalReference
top::Expr ::= @q::QName _ _ sr::[(String, SharedRefSite)]
{
  top.sharedRefs <- sr;
}
