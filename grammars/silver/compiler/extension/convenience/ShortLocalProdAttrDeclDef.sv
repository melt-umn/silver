grammar silver:compiler:extension:convenience;

concrete production shortLocalDecl
top::ProductionStmt ::= lk::'local' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      localAttributeDcl(lk, 'attribute', a, ht, te, sm, location=top.location),
      valueEq(qNameId(a, location=a.location), eq, v, sm, location=v.location),
      location=top.location);
}

concrete production shortLocalDeclwKwds
top::ProductionStmt ::= lk::'local' ak::'attribute' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      localAttributeDcl(lk, ak, a, ht, te, sm, location=top.location),
      valueEq(qNameId(a, location=a.location), eq, v, sm, location=v.location),
      location=top.location);
}

concrete production shortProductionDecl
top::ProductionStmt ::= pk::'production' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      productionAttributeDcl(pk, 'attribute', a, ht, te, sm, location=top.location),
      valueEq(qNameId(a, location=a.location), eq, v, sm, location=v.location),
      location=top.location);
}

concrete production shortProductionDeclwKwds
top::ProductionStmt ::= pk::'production' ak::'attribute' 
                        a::Name ht::'::' te::TypeExpr eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      productionAttributeDcl(pk, ak, a, ht, te, sm, location=top.location),
      valueEq(qNameId(a, location=a.location), eq, v, sm, location=v.location),
      location=top.location);
}

concrete production shortForwardProductionDecl
top::ProductionStmt ::= fk::'forward' a::Name
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      forwardProductionAttributeDcl(fk, 'production', 'attribute', a, sm, location=top.location),
      valueEq(qNameId(a, location=a.location), eq, v, sm, location=v.location),
      location=top.location);
}

concrete production shortForwardProductionDeclwKwds
top::ProductionStmt ::= fk::'forward' pk::'production' ak::'attribute' 
                        a::Name eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      forwardProductionAttributeDcl(fk, pk, ak, a, sm, location=top.location),
      valueEq(qNameId(a, location=a.location), eq, v, sm, location=v.location),
      location=top.location);
}

