grammar silver:compiler:extension:scopegraphs;

--

production scopeExists
top::ProductionStmt ::= a::Name
{
  nondecorated local e::Expr = Silver_Expr {
    let undecs::[Scope] = $QName{qName(a.name ++ "_undec")} in
      case undecs of
      | [s_undec] -> s_undec
      | _ -> error("Scope is built more than once in this tree.")
      end
    end
  };

  forwards to produceDecoratedScope(^a, e);
}

--

production mkScopeLocal
top::ProductionStmt ::= a::Name e::Expr
{
  forwards to produceDecoratedScope(^a, Silver_Expr{scope($Expr{^e})});
}

production mkScopeInherited
top::ProductionStmt ::= lhsqn::QName attrqn::QName sg::Maybe<String> e::Expr
{
  -- Contribution to undecs list for scopes introduced by existsScope
  forwards to Silver_ProductionStmt {
    $QName{^lhsqn}.$QName{appendToQName(^attrqn, "_undec")} <- [scope($Expr{^e})];
  };
}

--

production produceDecoratedScope
top::ProductionStmt ::= a::Name e::Expr
{
  -- Could be refactored away, but useful if we decide to allow multiple named scope graphs
  local graph::Maybe<ScopeGraphDclInfo> = lookupGraphDclOpt("_Scope_Default", top.sgEnv);
  -- What are the raw label names that graph defines 
  local labs::[String] = mapOrElse([], (.labels), graph);

  -- Declare attribute for built scope
  local localScopeDcl::ProductionStmt = 
    Silver_ProductionStmt { local attribute $Name{^a}::Scope; };
  -- Definition for built scope, separated to make dupl errors cleaner 
  local localScopeDef::ProductionStmt =
    Silver_ProductionStmt { $Name{^a} = $Expr{^e}; };

  nondecorated local undecAttrName::Name = name(a.name ++ "_undec");
  -- Definition of a_undec attribute for contributions of nondecorated scopes from subtrees
  nondecorated local undecAttrDcl::ProductionStmt = 
    productionStmtAppend(
      Silver_ProductionStmt{production attribute $Name{undecAttrName}::[Scope] with ++;},
      Silver_ProductionStmt{$Name{undecAttrName} := [];}
    );

  -- Equation a.lab := [], for every label lab in labs
  nondecorated local baseInhDefs::ProductionStmt = mkScopeBaseInhs(a.name, labs);

  forwards to 
    productionStmtAppend(
      @localScopeDcl, 
      productionStmtAppend(
        @localScopeDef,
        productionStmtAppend(
          undecAttrDcl,
          baseInhDefs
        )
      )
    );

  -- Avoids errors which expose agtix-generated attributes
  top.errors :=
    if length(getValueDclInScope(a.name, top.env)) > 1 
    then [errFromOrigin(a, "Scope '" ++ a.name ++ "' is already bound.")]
    else localScopeDef.errors;
}
