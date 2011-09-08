grammar silver:extension:convenience;

concrete production shortLocalDecl
top::ProductionStmt ::= lk::'local' a::Name ht::'::' te::Type 
                        eq::'=' v::Expr sm::';'
{
 forwards to
    productionStmtAppend (
       localAttributeDcl (lk, ak, a, ht, te, sm ) ,
       valueDef ( qNameId(a), eq, v, sm ) ) ;

 --local attribute lk :: Local_kwd ;
 --lk = terminal(Local_kwd, "local");

 local attribute ak :: Attribute_kwd ;
 ak = terminal(Attribute_kwd, "attribute");
}

concrete production shortLocalDeclwKwds
top::ProductionStmt ::= lk::'local' ak::'attribute' a::Name ht::'::' te::Type 
                        eq::'=' v::Expr sm::';'
{
 forwards to
    productionStmtAppend (
       localAttributeDcl (lk, ak, a, ht, te, sm ) ,
       valueDef ( qNameId(a), eq, v, sm ) ) ;
}

concrete production shortProductionDecl
top::ProductionStmt ::= pk::'production' a::Name ht::'::' te::Type 
                        eq::'=' v::Expr sm::';'
{
 forwards to
    productionStmtAppend (
       productionAttributeDcl (pk, ak, a, ht, te, sm ) ,
       valueDef ( qNameId(a), eq, v, sm ) ) ;

 local attribute ak :: Attribute_kwd ;
 ak = terminal(Attribute_kwd, "attribute");
}

concrete production shortProductionDeclwKwds
top::ProductionStmt ::= pk::'production' ak::'attribute' 
                        a::Name ht::'::' te::Type eq::'=' v::Expr sm::';'
{
 forwards to
    productionStmtAppend (
       productionAttributeDcl (pk, ak, a, ht, te, sm ) ,
       valueDef ( qNameId(a), eq, v, sm ) ) ;
}
