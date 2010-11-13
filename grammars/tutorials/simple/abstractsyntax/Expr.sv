grammar tutorials:simple:abstractsyntax ;

nonterminal Expr with pp, env, errors ;

abstract production and e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " && " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production or e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " || " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production not e::Expr ::= ne::Expr 
{ e.pp = "( !" ++  ne.pp ++ ")" ;
  e.errors := ne.errors ;
}

abstract production eq e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " == " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production neq e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " != " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production lt e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " < " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production lte e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " <= " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production gt e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " > " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production gte e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " >= " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}

abstract production add e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " + " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production sub e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " - " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production mul e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " * " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
}
abstract production div e::Expr ::= l::Expr r::Expr 
{ e.pp = "(" ++  l.pp ++ " / " ++ r.pp ++ ")" ;
  e.errors := l.errors ++ r.errors ;
 
}

abstract production varRef  e::Expr ::= id::Id_t
{ e.pp = id.lexeme ;
  e.errors := case declTypeExpr of
                just(_)   -> [ ]
              | nothing() -> [ "Line " ++ toString(id.line) ++ ": variable \""
                               ++ id.lexeme ++ "\" not declared.\n" ] 
              end ;
  local attribute declTypeExpr :: Maybe<Decorated TypeExpr> ;
  declTypeExpr = lookup(id.lexeme, e.env) ;
}

abstract production intLit
e::Expr ::= n::IntegerLiteral_t
{ e.pp = n.lexeme ;
  e.errors := [ ] ;
}


