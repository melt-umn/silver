grammar tutorials:simple:abstractsyntax ;

nonterminal Stmt ;

abstract production declStmt s::Stmt ::= d::Decl { }

abstract production seq s::Stmt ::= s1::Stmt s2::Stmt { }

abstract production printStmt s::Stmt ::= e::Expr { }
abstract production skip s::Stmt ::= {}

abstract production while s::Stmt ::= c::Expr b::Stmt { }
abstract production ifthen s::Stmt ::= c::Expr t::Stmt { }
abstract production ifelse s::Stmt ::= c::Expr t::Stmt e::Stmt { }

abstract production assignment  s::Stmt ::= id::Id_t e::Expr { }
