grammar simple:extensions:implication;

imports silver:langutil;
imports silver:langutil:pp;
imports simple:concretesyntax as cst;
imports simple:abstractsyntax;

terminal Implies  '=>'  precedence = 6;

concrete productions e::cst:Expr
 | l::cst:Expr '=>' r::cst:Expr
     { e.unparse = s"(${l.unparse} => ${r.unparse})";
       e.ast = implies(l.ast, r.ast); }

abstract production implies
e::Expr ::= l::Expr r::Expr 
{
  e.pp = pp"(${l} => ${r})";
  --   l => r   is equivalent to   !l || r
  forwards to or(not(l), r);
}
