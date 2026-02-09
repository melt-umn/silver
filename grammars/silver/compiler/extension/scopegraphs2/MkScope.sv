grammar silver:compiler:extension:scopegraphs2;

--

production mkScopeNoData
top::ProductionStmt ::= ident::String sg::IdUpper_t
{
  -- e.g. production attribute myScope::Decorated Scope with MyGraph = mkScope(datumNone());
  forwards to Silver_ProductionStmt {
    production attribute $Name{name(ident)}::Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}
    = mkScope(datumNone());
  };
}

production mkScopeWithData
top::ProductionStmt ::= ident::String sg::IdUpper_t scopeLab::String datum::Expr
{
  -- e.g. production attribute myDcl::Decorated Scope with MyGraph = mkScope(datum_dcl((id, ty)));
  forwards to Silver_ProductionStmt {
    production attribute $Name{name(ident)}::Decorated Scope with $TypeExpr{nominalTypeExpr(qNameTypeId(sg))}
    = mkScope($QName{qName("datum_" ++ scopeLab)}($Expr{@datum}));
  };
}