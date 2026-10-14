grammar silver:compiler:analysis:uniqueness;

attribute sharedRefs occurs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern;
propagate sharedRefs on Expr, Exprs, AppExprs, AppExpr, PrimPatterns, PrimPattern
  excluding ifThenElse, matchPrimitiveReal, consPattern, letp;

aspect production decorationSiteExpr
top::Expr ::=  '@' e::Expr
{
  local refLoc::Location = getParsedOriginLocationOrFallback(top);
  top.sharedRefs <-
    case e.flowVertexInfo of
    | just(v) -> [(top.frame.fullName ++ ":" ++ transRootVertex(v).vertexName, sharedRefSite(
        sourceGrammar=top.grammarName,
        sourceLocation=refLoc,
        sharedVertex=v,
        conflictsWith=[]
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
    | just(forwardParentVertexType()) -> [errFromOrigin(e, s"Cannot share the forward parent.")]  -- Decorated by its own parent, like the LHS
    | just(v) ->
        -- A translation attribute can only be shared if the tree it is a translation attribute of can be.
        case transRootVertex(v) of
        | lhsVertexType() -> [errFromOrigin(e, s"Cannot share a translation attribute of the production LHS.")]
        | forwardVertexType() -> [errFromOrigin(e, s"Cannot share a translation attribute of the forward tree.")]
        | anonVertexType(_, _, _) -> [errFromOrigin(e, s"Cannot share a translation attribute of an anonymously decorated tree.")]
        | subtermVertexType(_, _, _) -> [errFromOrigin(e, s"Cannot share a translation attribute of a pattern variable.")]
        | forwardParentVertexType() -> [errFromOrigin(e, s"Cannot share a translation attribute of the forward parent.")]
        -- Check that this tree is shared in at most one non-mutually-exclusive place.
        | _ -> sharedRefConflictErrors(top.frame.fullName, v, top.grammarName, refLoc, top.flowEnv)
        end
    | nothing() -> [errFromOrigin(e, s"This is not something that can be shared; shared trees must correspond to a known decoration site.")]
    end;
}

aspect production presentAppExpr
top::AppExpr ::= e::Expr
{
  -- This mirrors the above, but for signature sharing:
  local refLoc::Location = getParsedOriginLocationOrFallback(top);
  top.sharedRefs <-
    case e.flowVertexInfo of
    | just(v) when sigIsShared && isForwardParam ->
      [(top.frame.fullName ++ ":" ++ transRootVertex(v).vertexName,
        sharedRefSite(
          sourceGrammar=top.grammarName,
          sourceLocation=refLoc,
          sharedVertex=v,
          conflictsWith=[]
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
      | just(forwardParentVertexType()) -> [errFromOrigin(e, s"Cannot share the forward parent.")]  -- Decorated by its own parent, like the LHS
      | just(v) ->
          case transRootVertex(v) of
          | lhsVertexType() -> [errFromOrigin(e, s"Cannot share a translation attribute of the production LHS.")]
          | forwardVertexType() -> [errFromOrigin(e, s"Cannot share a translation attribute of the forward tree.")]
          | anonVertexType(_, _, _) -> [errFromOrigin(e, s"Cannot share a translation attribute of an anonymously decorated tree.")]
          | subtermVertexType(_, _, _) -> [errFromOrigin(e, s"Cannot share a translation attribute of a pattern variable.")]
          | forwardParentVertexType() -> [errFromOrigin(e, s"Cannot share a translation attribute of the forward parent.")]
          -- Check that this tree is shared in at most one non-mutually-exclusive place.
          | _ -> sharedRefConflictErrors(top.frame.fullName, v, top.grammarName, refLoc, top.flowEnv)
          end
      | nothing() -> [errFromOrigin(e, s"This is not something that can be shared; shared trees must correspond to a known decoration site.")]
      end
    else [];
}

{--
 - Errors for the reference to v taken at loc in grammar gram, in production prod, for the other references
 - that it conflicts with.  Sharing a tree also shares its translation attributes, so two references conflict
 - if they share the same tree, or one shares a translation attribute (of a translation attribute, and so on)
 - of the tree that the other shares - unless they are in mutually exclusive branches of the same equation.
 - The conflicts within this grammar are recorded where the references are combined (see appendSharedRefs);
 - references in other grammars are in other equations, so they always conflict.
 -}
function sharedRefConflictErrors
[Message] ::= prod::String  v::VertexType  gram::String  loc::Location  e::FlowEnv
{
  local refs::[SharedRefSite] = lookupSharedRefs(prod, v, e);
  local conflicts::[(String, Location, VertexType)] =
    nub(
      flatMap((.conflictsWith), filter(\ r::SharedRefSite -> sharedRefId(r) == (gram, loc, v), refs)) ++
      map(sharedRefId,
        filter(\ r::SharedRefSite -> r.sourceGrammar != gram && sharedVerticesConflict(r.sharedVertex, v), refs)));
  -- The places where references were taken, in order
  local showSites::([String] ::= [(String, Location, VertexType)]) =
    \ rs::[(String, Location, VertexType)] ->
      map(
        \ r::(String, Location, VertexType) -> r.1 ++ ":" ++ r.2.unparse,
        sortByKey(\ r::(String, Location, VertexType) -> (r.1, r.2), rs));
  local sameTreeConflicts::[(String, Location, VertexType)] =
    filter(\ r::(String, Location, VertexType) -> r.3 == v, conflicts);
  local transConflicts::[(String, Location, VertexType)] =
    filter(\ r::(String, Location, VertexType) -> r.3 != v, conflicts);

  return
    (if null(sameTreeConflicts) then [] else [err(loc,
      s"Tree ${v.vertexName} in production ${prod} is shared in multiple places:\n" ++
      flatMap(\ site::String -> "\t" ++ site ++ "\n", nub(showSites((gram, loc, v) :: sameTreeConflicts))))]) ++
    map(
      \ w::VertexType ->
        err(loc,
          s"Cannot share ${v.vertexName} in production ${prod}, because ${w.vertexPP} is also shared (at " ++
          implode(", ", nub(showSites(filter(\ r::(String, Location, VertexType) -> r.3 == w, transConflicts)))) ++
          ")."),
      nub(map(\ r::(String, Location, VertexType) -> r.3, transConflicts)));
}

aspect production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
{
  top.sharedRefs := appendSharedRefs(e1.sharedRefs, unionMutuallyExclusiveRefs(e2.sharedRefs, e3.sharedRefs));
}

aspect production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  top.sharedRefs := appendSharedRefs(e.sharedRefs, unionMutuallyExclusiveRefs(pr.sharedRefs, f.sharedRefs));
}

aspect production consPattern
top::PrimPatterns ::= p::PrimPattern _ ps::PrimPatterns
{
  top.sharedRefs := unionMutuallyExclusiveRefs(p.sharedRefs, ps.sharedRefs);
}

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  -- References taken in la are recorded where the bound names are used in e.
  top.sharedRefs := e.sharedRefs;
}

aspect production lexicalLocalReference
top::Expr ::= @q::QName _ _ sr::[(String, SharedRefSite)]
{
  top.sharedRefs <- sr;
}
