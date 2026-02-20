grammar silver:compiler:extension:scopegraphs2;

--

production mkScope
top::ProductionStmt ::= ident::String sg::IdUpper_t datum::Expr
{
  local labs::([String], [Message]) =
    let res::[ScopeGraphDclInfo] = lookupGraphDcl(sg.lexeme, top.sgEnv) in
      case res of
      | h::[] -> (h.labelSet, [])
      | _ -> ([], [errFromOrigin(top, toString(length(res)) ++ 
                                      " scope graph declarations found named '" ++ sg.lexeme ++ "'")])
      end
    end;
  
  nondecorated local mkScopeEq::ProductionStmt = Silver_ProductionStmt {
    local attribute $Name{name(ident)}::Scope = scope($Expr{^datum});
  };

  forwards to productionStmtAppend(
    mkScopeEq,
    mkScopeBaseInhs(ident, labs.1)
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