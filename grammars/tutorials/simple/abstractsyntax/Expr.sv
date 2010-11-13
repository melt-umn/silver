grammar tutorials:simple:abstractsyntax ;

nonterminal Expr ;

abstract production and e::Expr ::= l::Expr r::Expr { }
abstract production or e::Expr ::= l::Expr r::Expr { }
abstract production not e::Expr ::= l::Expr { }

abstract production eq e::Expr ::= l::Expr r::Expr { }
abstract production neq e::Expr ::= l::Expr r::Expr { }
abstract production lt e::Expr ::= l::Expr r::Expr { }
abstract production lte e::Expr ::= l::Expr r::Expr { }
abstract production gt e::Expr ::= l::Expr r::Expr { }
abstract production gte e::Expr ::= l::Expr r::Expr { }

abstract production add e::Expr ::= l::Expr r::Expr { }
abstract production sub e::Expr ::= l::Expr r::Expr { }
abstract production mul e::Expr ::= l::Expr r::Expr { }
abstract production div e::Expr ::= l::Expr r::Expr { }
abstract production varRef  e::Expr ::= id::Id_t    { }


