grammar silver:modification:annotation;

abstract production annoAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, performSubstitution(e.typerep, e.upSubst));

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
}

concrete production applicationAnno
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
}

concrete production emptyApplicationAnno
top::Expr ::= e::Expr '(' anns::AnnoAppExprs ')'
{
}


nonterminal AnnoAppExprs;
nonterminal AnnoExpr;

concrete production annoExpr
top::AnnoExpr ::= qn::QName '=' e::Expr
{
}

concrete production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
}

concrete production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
}

abstract production emptyAnnoExprs
top::AnnoAppExprs ::= l::Location
{
}


