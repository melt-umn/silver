grammar silver:modification:annotation;

abstract production annoAccessHandler
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  production occursCheck :: OccursCheck =
    occursCheckQName(q, performSubstitution(e.typerep, e.upSubst));

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
}

