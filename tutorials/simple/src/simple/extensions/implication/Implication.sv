grammar simple:extensions:implication;

imports silver:langutil;
imports silver:langutil:pp;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;

terminal Implies  '=>'  precedence = 6;

concrete production implies_c
e::cst:Expr ::= l::cst:Expr '=>' r::cst:Expr
{
  e.unparse = "(" ++  l.unparse ++ " => " ++ r.unparse ++ ")";
  e.ast = implies(l.ast, r.ast);
}

abstract production implies
e::Expr ::= l::Expr r::Expr 
{
  e.pp = ppoperator(l.pp, "=>", r.pp);
  --   l => r   is equivalent to   !l || r
  forwards to or(not(l), r);
}
