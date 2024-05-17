grammar silver:compiler:extension:convenience;

concrete production shortLocalDecl
top::ProductionStmt ::= lk::'local' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      localAttributeDcl(lk, 'attribute', @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortLocalDeclwKwds
top::ProductionStmt ::= lk::'local' ak::'attribute' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      localAttributeDcl(lk, ak, @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortProductionDecl
top::ProductionStmt ::= pk::'production' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      productionAttributeDcl(pk, 'attribute', @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortProductionDeclwKwds
top::ProductionStmt ::= pk::'production' ak::'attribute' 
                        a::Name ht::'::' te::TypeExpr eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      productionAttributeDcl(pk, ak, @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortNondecDecl
top::ProductionStmt ::= nk::'nondecorated' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      nondecLocalAttributeDcl(nk, 'local', 'attribute', @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortNondecLocalDecl
top::ProductionStmt ::= nk::'nondecorated' lk::'local' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      nondecLocalAttributeDcl(nk, lk, 'attribute', @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortNondecLocalDeclwKwds
top::ProductionStmt ::= nk::'nondecorated' lk::'local' ak::'attribute' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      nondecLocalAttributeDcl(nk, lk, ak, @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortNondecProductionDecl
top::ProductionStmt ::= nd::'nondecorated' pk::'production' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      nondecProductionAttributeDcl(nd, pk, 'attribute', @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortNondecProductionDeclwKwds
top::ProductionStmt ::= nd::'nondecorated' pk::'production' ak::'attribute' a::Name ht::'::' te::TypeExpr 
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      nondecProductionAttributeDcl(nd, pk, ak, @a, ht, @te, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortForwardProductionDecl
top::ProductionStmt ::= fk::'forward' a::Name
                        eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      forwardProductionAttributeDcl(fk, 'production', 'attribute', @a, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

concrete production shortForwardProductionDeclwKwds
top::ProductionStmt ::= fk::'forward' pk::'production' ak::'attribute' 
                        a::Name eq::'=' v::Expr sm::';'
{
  forwards to
    productionStmtAppend(
      forwardProductionAttributeDcl(fk, pk, ak, @a, sm),
      valueEq(qNameId(new(a)), eq, @v, sm));
}

