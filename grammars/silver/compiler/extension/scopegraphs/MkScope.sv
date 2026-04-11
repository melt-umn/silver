grammar silver:compiler:extension:scopegraphs;

--

production mkScopeLocal
top::ProductionStmt ::= locqn::QName sg::Maybe<String> datum::Expr
{
  propagate env, flowEnv, config, compiledGrammars, grammarName, frame, finalSubst;
  thread downSubst, upSubst on top, datum, top;

  datum.isRoot = true;
  datum.decSiteVertexInfo = nothing();
  datum.appDecSiteVertexInfo = nothing();

  -- avoid errors from forward, hide '_undec' attrs
  -- todo: new dcl info type for scopes instead? then locqn.lookupScope.dcl here, 
  -- or locqn.lookupScope.errors
  top.errors := 
    let dcls::[ValueDclInfo] = locqn.lookupValue.dcls in
      if null(dcls)
      then [errFromOrigin(top, "Undeclared scope '" ++ locqn.name ++ "'.")]
      else if length(dcls) > 1
      then [errFromOrigin(top, "Ambiguous reference to scope '" ++ locqn.name ++ 
                               "'. Possibilities are:\n" ++ printPossibilities(dcls))]
      else []
    end;
  top.errors <- datum.errors;

  forwards to Silver_ProductionStmt {
    $QName{appendToQName(^locqn, "_undec")} <- [scope($Expr{^datum})];
  };
}

production mkScopeInherited
top::ProductionStmt ::= lhsqn::QName attrqn::QName sg::Maybe<String> datum::Expr
{
  forwards to Silver_ProductionStmt {
    $QName{^lhsqn}.$QName{appendToQName(^attrqn, "_undec")} <- [scope($Expr{^datum})];
  };
}

--

production scopeExists
top::ProductionStmt ::= s::Name sg::Maybe<String>
{
  local ident::String = s.name;
  local sgName::String = fromMaybe("_Scope_Default", sg);

  nondecorated local qn_undec::QName = qName(ident ++ "_undec");

  nondecorated local mkScopeExpr::Expr = Silver_Expr {
    let undecs::[Scope] = $QName{qn_undec} in
      case undecs of
      | [s_undec] -> s_undec
      | _ -> error("Oh no! scopeExists.mkScopeExpr")
      end
    end
  };

  local labs::([String], [Message]) =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl(sgName, top.sgEnv) in
      case res of
      | h::[] -> (h.labels, [])
      | _ -> ([], [errFromOrigin(top, toString(length(res)) ++ 
                                      " scope graph declarations found named '"
                                      ++ sgName ++ "'")])
      end
    end;

  local localDef::ProductionStmt = Silver_ProductionStmt {
    local attribute $Name{name(ident)}::Scope = $Expr{mkScopeExpr};
  };

  -- hide errors related to defining expression, _undec, and inherited attributes
  top.errors := localDef.errors;
  top.errors <- labs.2;

  forwards to productionStmtAppend(
    @localDef,
    --productionStmtAppend(
      --Silver_ProductionStmt{$QName{qName(ident)} = $Expr{mkScopeExpr};},
      productionStmtAppend(
        mkScopeBaseInhs(ident, labs.1),
        productionStmtAppend(
          Silver_ProductionStmt {production attribute $Name{name(qn_undec.name)}::[Scope] with ++;},
          Silver_ProductionStmt {$QName{qn_undec} := [];}
        )
      )
    --)
  );

}
