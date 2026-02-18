grammar silver:compiler:extension:scopegraphs2;

--

production mkScopeBoth
top::ProductionStmt ::= ident::String sg::IdUpper_t datum::Maybe<Expr>
{
  forwards to Silver_ProductionStmt {
    local attribute foo::Integer;
  };
}

production mkScopeNoData
top::ProductionStmt ::= ident::String sg::IdUpper_t
{
  forwards to mkScopeBoth(ident, sg, nothing());
}

production mkScopeWithData
top::ProductionStmt ::= ident::String sg::IdUpper_t datum::Expr
{
  forwards to mkScopeBoth(ident, sg, just(^datum));
}
