grammar simple:extensions:implication ;

imports simple:host ;

terminal Implies_t  '=>'  precedence =  6 ;

concrete production implies_c
e::Expr_c ::= l::Expr_c '=>' r::Expr_c
{
  e.pp = "(" ++  l.pp ++ " => " ++ r.pp ++ ")" ;
  e.ast_Expr = implies(l.ast_Expr, r.ast_Expr) ;
}

abstract production implies
e::Expr ::= l::Expr r::Expr 
{
  e.pp = "(" ++  l.pp ++ " => " ++ r.pp ++ ")" ;
  --   l => r   is equivalent to   !l || r
  forwards to or ( not(l), r);
}
