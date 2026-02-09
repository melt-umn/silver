grammar silver:compiler:extension:scopegraphs2;

--

-- todo, label env
global labs::[String] = ["lex", "var"];

--

production mkScopeNoData
top::ProductionStmt ::= ident::String sg::IdUpper_t
{
  nondecorated local inhs::ExprInhs = exprInhsLabels(ident, labs);

  forwards to productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(ident)}::$TypeExpr{scopeTyExpr(sg)}
      = decorate mkScope(datumNone()) with { $ExprInhs{inhs} };
    },
    mkScopeProdAttrs(ident, sg)
  );
  
}

production mkScopeWithData
top::ProductionStmt ::= ident::String sg::IdUpper_t scopeLab::String datum::Expr
{
  nondecorated local inhs::ExprInhs = exprInhsLabels(ident, labs);

  forwards to productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(ident)}::$TypeExpr{scopeTyExpr(sg)}
      = decorate mkScope($QName{qName("datum_" ++ scopeLab)}($Expr{@datum}))
        with { $ExprInhs{inhs} };
    },
    mkScopeProdAttrs(ident, sg)
  );
}

--

production mkScopeProdAttrs
top::ProductionStmt ::= ident::String sg::IdUpper_t
{
  nondecorated local prodAttrs::ProductionStmt = foldrLastElem(
    \lab::String acc::ProductionStmt ->
      productionStmtAppend(acc, mkScopeProdAttr(ident, sg, lab)),
    mkScopeProdAttr(ident, sg, _),
    labs
  );

  forwards to prodAttrs;
}

production mkScopeProdAttr
top::ProductionStmt ::= ident::String sg::IdUpper_t lab::String
{
  forwards to productionStmtAppend (
    Silver_ProductionStmt {
      production attribute $Name{nScopeAttr(ident, lab)}::$TypeExpr{scopeListTyExpr(sg)} with ++;
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
                      baseExpr(qnScopeAttr(s, lab)), ';')
;

fun scopeListTyExpr TypeExpr ::= sg::IdUpper_t =
  Silver_TypeExpr { [$TypeExpr{scopeTyExpr(sg)}] }
;

fun scopeTyExpr TypeExpr ::= sg::IdUpper_t =
  Silver_TypeExpr {
    Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}
  }
;