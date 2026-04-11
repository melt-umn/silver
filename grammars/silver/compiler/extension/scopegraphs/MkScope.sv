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
  top.errors := locqn.lookupValue.errors;
  top.errors <- datum.errors;

  top.defs := [];

  forwards to Silver_ProductionStmt {
    $QName{appendToQName(^locqn, "_undec")} <-
      [scope($Expr{^datum})];
  };
}

production mkScopeInherited
top::ProductionStmt ::= lhsqn::QName attrqn::QName sg::Maybe<String> datum::Expr
{
  top.defs := [];

  forwards to Silver_ProductionStmt {
    $QName{^lhsqn}.$QName{appendToQName(^attrqn, "_undec")} <-
      [scope($Expr{^datum})];
  };
}

--

production scopeExists
top::ProductionStmt ::= s::Name sg::Maybe<String>
{
  local ident::String = s.name;
  local sgName::String = fromMaybe("_Scope_Default", sg);

  nondecorated local mkScopeExpr::Expr =
    Silver_Expr {
      let undecs::[Scope] = $QName{qName(ident ++ "_undec")} in
        case undecs of
        | [s_undec] -> s_undec
        | _ -> error("Oh no! scopeExists.mkScopeExpr")
        end
      end
    };

  local labs::([String], [Message]) =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl(sgName, top.sgEnv) in
      case res of
      | h::[] -> (h.labelSet, [])
      | _ -> ([], [errFromOrigin(top, toString(length(res)) ++ 
                                      " scope graph declarations found named '"
                                      ++ sgName ++ "'")])
      end
    end;

  forwards to productionStmtAppend(
    Silver_ProductionStmt {local attribute $Name{name(ident)}::Scope = $Expr{mkScopeExpr};},
    productionStmtAppend(
      mkScopeBaseInhs(ident, labs.1),
      productionStmtAppend(
        Silver_ProductionStmt {production attribute $Name{name(ident ++ "_undec")}::[Scope] with ++;},
        Silver_ProductionStmt {$QName{qName(ident ++ "_undec")} := [];}
      )
    )
  );

  top.errors <- labs.2;

}
