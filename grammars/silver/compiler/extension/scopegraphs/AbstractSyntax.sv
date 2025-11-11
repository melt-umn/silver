grammar silver:compiler:extension:scopegraphs;

--

global nondecScopeType::Type = 
  nonterminalType("silver:compiler:extension:scopegraphs:Scope", [], false, false);

--

nonterminal Scope;

abstract production mkScope
top::Scope ::=
{}

--

abstract production absScopeAssertion
top::ProductionStmt ::= inhs::TypeExpr a::Name e::Expr hasExpr::Boolean
{
  propagate flowEnv, env, grammarName, config, compiledGrammars, frame,
            finalSubst, downSubst, boundVariables;

  e.dispatchFlowDeps = [];
  e.isRoot = false;
  e.alwaysDecorated = false;
  e.decSiteVertexInfo = nothing();
  e.appDecSiteVertexInfo = nothing();

  --

  -- list type for scopes with inherited attributes `inhs`
  local scopeListTypeExpr::Decorated TypeExpr with {env, grammarName, flowEnv} = 
    decorate
      Silver_TypeExpr {[Decorated silver:compiler:extension:scopegraphs:Scope with $TypeExpr{^inhs}]}
    with {env = top.env; grammarName = top.grammarName; flowEnv = top.flowEnv;};

  -- names of inherited attributes corresponding to SG edges
  local inhNames::[String] = inhs.typerep.inhSetMembers;

  -- types of the inherited attributes in the set defined by `inhs`
  local inhTypes::[Type] =
    let attrLookups::[QNameLookup<AttributeDclInfo>] = 
      map (\i::String -> customLookup("attribute", getAttrDcl(i, top.env), i),
           inhNames)
    in
    let attrDcls::[AttributeDclInfo] =
      concat(map((.dcls), attrLookups))
    in
      filterMap(\a::AttributeDclInfo -> case a of inhDcl(_, _, t) -> just(^t) | _ -> nothing() end,
                attrDcls)
    end end;

  -- true if types of all inhs in the set from `inhs` have type `scopeListTypeExpr`
  local inhTypesOk::Boolean = foldr(
    \t::Type ok::Boolean -> ok && !unify(t, scopeListTypeExpr.typerep).failure,
    true, inhTypes);

  -- produce an inherited attribute definition for when a new scope is decorated
  local mkExprInh::(ExprInh ::= String) = \inhName::String ->
    exprInh(exprLhsExpr(qNameAttrOccur(qName(inhName))), '=', Silver_Expr{$QName{qNameId(name(a.name ++ "_" ++ inhName))}}, ';');

  -- produce inherited attribute definitions for when a new scope is decorated
  local decorateScopeExpr::Expr = Silver_Expr {
    decorate mkScope() with {
      $ExprInhs{
        foldrLastElem(
          \inhName::String acc::ExprInhs -> exprInhsCons(mkExprInh(inhName), acc),
          \lastInhName::String -> exprInhsOne(mkExprInh(lastInhName)),
          inhNames
        )
      }
    }
  };

  -- for a given edge inh attr, define and initialize collection attribute
  local inhEdgeDefStmt::(ProductionStmt ::= String) = \inhName::String ->
    productionStmtAppend(
      Silver_ProductionStmt{production attribute $Name{name(a.name ++ "_" ++ inhName)}::$TypeExpr{^scopeListTypeExpr} with ++;},
      Silver_ProductionStmt{$QName{qName(a.name ++ "_" ++ inhName)} := [];}
    );

  -- for every edge inh attr, define and initialize collection attribute
  local inhEdgeDefStmts::ProductionStmt = foldrLastElem(
    \inhName::String acc::ProductionStmt -> productionStmtAppend(inhEdgeDefStmt(inhName), acc),
    \lastInhName::String -> inhEdgeDefStmt(lastInhName),
    inhNames
  );

  --

  top.errors :=
    if inhs.typerep.kindrep != inhSetKind()
    then [errFromOrigin(top, "Type argument for scope must to have kind " ++ 
                             "InhSet an argument was given of kind " ++ 
                             inhs.typerep.kindrep.typepp)]
    else if !inhTypesOk
    then [errFromOrigin(top, "Inherited attributes for scope<" ++ inhs.unparse ++ "> " ++ 
                                "must have type " ++  scopeListTypeExpr.unparse)]
    else [];

  --

  top.unparse = "scope <" ++ inhs.unparse ++ ">" ++ a.unparse ++ " -> " ++ e.unparse ++ ";";
    
  forwards to productionStmtAppend(
    Silver_ProductionStmt{production attribute $Name{^a}::Decorated Scope with $TypeExpr{^inhs} = $Expr{^decorateScopeExpr};},
    ^inhEdgeDefStmts -- removing this resolves cycle error
  );

}


--

-- `s -[ lab ]-> _` for some production attribute scope (or function local) `s`
abstract production absEdgeAssertionLocal
top::ProductionStmt ::= n::QName lab::String e::Expr
{
  -- TODO: check that n is not qualified
  -- TODO: check if lab is an inh attr - maybe should be a QName too

  {-propagate flowDefs, flowEnv, env, config, compiledGrammars, grammarName,
            frame, finalSubst, upSubst2, downSubst2, boundVariables;

  e.dispatchFlowDeps = [];
  e.isRoot = true; e.alwaysDecorated = true;
  e.decSiteVertexInfo = nothing(); e.appDecSiteVertexInfo = nothing();

  thread downSubst, upSubst on top, e, top;

  --

  local nIsQualified::Boolean =
    case n of qNameId(_) -> false | _ -> true end;

  local nTy::Decorated Type with {boundVariables, flowEnv} =
    decorate n.lookupValue.dcl.refDispatcher(n).typerep
    with { boundVariables = []; };

  local eTy::Decorated Type with {boundVariables} =
    decorate e.typerep
      with { boundVariables = []; };

  local scopeTyUnify::Type = decoratedType(nondecScopeType, errorType());
  scopeTyUnify.boundVariables = [];

  --

  top.errors <-
    if nIsQualified
    then [errFromOrigin(top, "Left side of Scope edge assertion must be unqualified")]
    else if unify(^nTy, scopeTyUnify).failure
    then [errFromOrigin(top, "Left side of Scope edge assertion has type '" ++ 
                             nTy.typepp ++ "' but must have type '" ++ 
                             scopeTyUnify.typepp ++ "'")]
    else if unify(^eTy, scopeTyUnify).failure
    then [errFromOrigin(top, "Right side of Scope edge assertion has type '" ++ 
                             eTy.typepp ++ "' but must have type '" ++ 
                             scopeTyUnify.typepp ++ "'")]
    else [];

  --

  top.unparse = n.unparse ++ " -[ " ++ lab ++ " ]-> " ++ e.unparse ++ ";";

  forwards to Silver_ProductionStmt {
    $QName{qName(n.name ++ "_" ++ lab)} <- [$Expr{^e}];
  };-}

  forwards to Silver_ProductionStmt {
    local attribute $Name{name(n.name)}::Integer;
  };

}
