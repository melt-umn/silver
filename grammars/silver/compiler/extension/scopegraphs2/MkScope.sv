grammar silver:compiler:extension:scopegraphs2;

--

global fnScopeNt::String = "silver:compiler:extension:scopegraphs2:Scope";

--

production mkScopeNoData
top::ProductionStmt ::= ident::String sg::IdUpper_t
{
  local labs::[String] = getLabAttrNames(top.grammarName, top.env);

  nondecorated local stmts::ProductionStmt = productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(ident)}::$TypeExpr{scopeTyExpr(sg)}
      = decorate mkScope(datumNone()) with { $ExprInhs{exprInhsLabels(ident, labs)} };
    },
    mkScopeProdAttrs(ident, sg, labs)
  );

  forwards to stmts;
  
}

production mkScopeWithData
top::ProductionStmt ::= ident::String sg::IdUpper_t scopeLab::String datum::Expr
{
  local labs::[String] = getLabAttrNames(top.grammarName, top.env);

  nondecorated local inhs::ExprInhs = exprInhsLabels(ident, labs);

  forwards to productionStmtAppend(
    Silver_ProductionStmt {
      production attribute $Name{name(ident)}::$TypeExpr{scopeTyExpr(sg)}
      = decorate mkScope($QName{qName("datum_" ++ scopeLab)}($Expr{@datum}))
        with { $ExprInhs{inhs} };
    },
    mkScopeProdAttrs(ident, sg, labs)
  );
}

--

production mkScopeProdAttrs
top::ProductionStmt ::= ident::String sg::IdUpper_t labs::[String]
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

fun getLabAttrNames [String] ::= gram::String e::Env =
  filterMap(\s::String -> if startsWith(gram, s) 
                          then just(last(explode(":", s)))
                          else nothing(),
    map((.attrOccurring), getAttrOccursOn(fnScopeNt, e))
  )
;