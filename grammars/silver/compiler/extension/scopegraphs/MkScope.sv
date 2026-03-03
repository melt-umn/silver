grammar silver:compiler:extension:scopegraphs;

--

production mkScope
top::ProductionStmt ::= ident::String sg::Maybe<String> datum::Expr
{
  local sgName::String = fromMaybe("_Scope_Default", sg);

  local labs::([String], [Message]) =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl(sgName, top.sgEnv) in
      case res of
      | h::[] -> (h.labelSet, [])
      | _ -> ([], [errFromOrigin(top, toString(length(res)) ++ 
                                      " scope graph declarations found named '" ++ sgName ++ "'")])
      end
    end;
  
  nondecorated local mkScopeEq::ProductionStmt = Silver_ProductionStmt {
    local attribute $Name{name(ident)}::Scope = scope($Expr{^datum});
  };

  nondecorated local undecsLst::ProductionStmt = Silver_ProductionStmt {
    production attribute $Name{name(ident ++ "_undec")}::[Scope] with ++;
  };

  nondecorated local emptyContrib::ProductionStmt = Silver_ProductionStmt {
    $QName{qName(ident ++ "_undec")} := [];
  };

  forwards to productionStmtAppend(
    mkScopeEq,
    productionStmtAppend(
      mkScopeBaseInhs(ident, labs.1),
      productionStmtAppend(
        undecsLst,
        emptyContrib
      )
    )
  );

  top.errors <- labs.2;
}

-- inh attr base exprs

fun mkScopeBaseInhs ProductionStmt ::= s::String labs::[String] =
  let baseEq::(ProductionStmt ::= String) = \lab::String ->
    Silver_ProductionStmt {
      $QName{qName(s)}.$QName{qName(lab)} := [];
    }
  in
    foldrLastElem(
      \lab::String acc::ProductionStmt -> productionStmtAppend(baseEq(lab), acc),
      \lab::String -> baseEq(lab),
      labs
    )
  end;

--

production mkScopeUndec
top::ProductionStmt ::= dl::DefLHS attr::QNameAttrOccur sg::Maybe<String> datum::Expr
{
  nondecorated local qn::QName = case attr of qNameAttrOccur(qn) -> ^qn end;

  forwards to Silver_ProductionStmt {
    $QName{qName(dl.name)}.$QName{appendToQName(qn, "_undec")} <- [scope($Expr{^datum})];
  };
}

--

production scopeExists
top::ProductionStmt ::= s::String sg::Maybe<String>
{
  local sgName::String = fromMaybe("_Scope_Default", sg);

  nondecorated local mkScopeExpr::Expr =
    Silver_Expr {
      let undecs::[Scope] = $QName{qName(s ++ "_undec")} in
        case undecs of
        | h::[] -> h.datum
        | _ -> error("Oh no! scopeExists.mkScopeExpr")
        end
      end
    };

  forwards to mkScope(s, sg, mkScopeExpr);
}