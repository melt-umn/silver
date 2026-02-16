grammar silver:compiler:extension:scopegraphs2;

--

production mkScopeNoData
top::ProductionStmt ::= ident::String sg::IdUpper_t
{
  local graphDcl::Maybe<ScopeGraphDclInfo> = 
    let
      res::[ScopeGraphDclInfo] = lookupGraphDcl(sg.lexeme, top.sgEnv)
    in
      case res of
      | h::[] -> just(h)
      | _ -> nothing() -- todo err msgs
      end
    end;

  nondecorated local combinedTe::TypeExpr = if graphDcl.isJust then graphDcl.fromJust.combinedTe
                                                               else Silver_TypeExpr{Unit};
  local labs::[String] = if graphDcl.isJust then map(fst(_), graphDcl.fromJust.labelSet)
                                            else [];

  nondecorated local scopeDatumTe::TypeExpr = combinedTe;

  nondecorated local stmts::ProductionStmt = productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(ident)}::$TypeExpr{scopeTyExpr(sg, scopeDatumTe)}
      = decorate mkScope(left(())) with { $ExprInhs{exprInhsLabels(ident, labs)} };
    },
    mkScopeProdAttrs(ident, sg, labs, combinedTe)
  );

  forwards to stmts;
  
}

production mkScopeWithData
top::ProductionStmt ::= ident::String sg::IdUpper_t scopeLab::String datum::Expr
{
  local graphDcl::Maybe<ScopeGraphDclInfo> = 
    let
      res::[ScopeGraphDclInfo] = lookupGraphDcl(sg.lexeme, top.sgEnv)
    in
      case res of
      | h::[] -> just(h)
      | _ -> nothing() -- todo err msgs
      end
    end;

  nondecorated local combinedTe::TypeExpr = if graphDcl.isJust then graphDcl.fromJust.combinedTe
                                                  else Silver_TypeExpr{Unit};
  local labs::[String] = if graphDcl.isJust then map(fst(_), graphDcl.fromJust.labelSet)
                                            else [];

  nondecorated local scopeDatumTe::TypeExpr =
    case filter(\p::(String, Maybe<TypeExpr>) -> p.1 == scopeLab, if graphDcl.isJust then graphDcl.fromJust.labelSet else []) of
    | (l, just(t))::[] -> t
    | _ -> Silver_TypeExpr{Unit}
    end;

  nondecorated local inhs::ExprInhs = exprInhsLabels(ident, labs);

  forwards to productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(ident)}::$TypeExpr{scopeTyExpr(sg, scopeDatumTe)}
      = decorate mkScope($Expr{@datum}) with { $ExprInhs{inhs} };
    },
    mkScopeProdAttrs(ident, sg, labs, combinedTe)
  );
}

--

production mkScopeProdAttrs
top::ProductionStmt ::= ident::String sg::IdUpper_t labs::[String] combinedTe::TypeExpr
{
  nondecorated local prodAttrs::ProductionStmt = foldrLastElem(
    \lab::String acc::ProductionStmt ->
      productionStmtAppend(acc, mkScopeProdAttr(ident, sg, lab, ^combinedTe)),
    \lab::String -> mkScopeProdAttr(ident, sg, lab, ^combinedTe),
    labs
  );

  forwards to prodAttrs;
}

production mkScopeProdAttr
top::ProductionStmt ::= ident::String sg::IdUpper_t lab::String combinedTe::TypeExpr
{
  forwards to productionStmtAppend (
    Silver_ProductionStmt {
      production attribute $Name{nScopeAttr(ident, lab)}::$TypeExpr{scopeListTyExpr(sg, ^combinedTe)} with ++;
    },
    Silver_ProductionStmt {
      $Name{nScopeAttr(ident, lab)} := [];
    }
  );
}

--

fun nScopeAttr Name ::= s::String l::String =
  name(s ++ "_" ++ l)
;

fun exprInhsLabels ExprInhs ::= s::String labs::[String] =
  foldrLastElem(
    \lab::String acc::ExprInhs -> exprInhsCons(oneExprInh(s, lab), acc),
    \lab::String -> exprInhsOne(oneExprInh(s, lab)),
    labs
  )
;

fun oneExprInh ExprInh ::= s::String lab::String =
  exprInh(exprLhsExpr(qNameAttrOccur(qName(lab))), '=', 
                      --baseExpr(qnScopeAttr(s, lab))
                      Silver_Expr{
                        $QName{qName("coerce_" ++ lab)}($QName{qnScopeAttr(s, lab)})
                      },
                      ';')
;

fun scopeListTyExpr TypeExpr ::= sg::IdUpper_t combDatumTe::TypeExpr =
  Silver_TypeExpr { [$TypeExpr{scopeTyExpr(sg, combDatumTe)}] }
;

fun scopeTyExpr TypeExpr ::= sg::IdUpper_t datumTe::TypeExpr =
  Silver_TypeExpr {
    Decorated Scope<$TypeExpr{datumTe}> with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}
  }
;