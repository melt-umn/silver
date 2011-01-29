grammar lib:langproc:tests ;

function foo
String ::= e::Decorated Expr
{ return e.pp ; }

nonterminal TreeAndDecorated<t dt> ;

function bar
String ::= t::TreeAndDecorated<Expr  Decorated Expr> e::Decorated Expr
{ return e.pp ; }

abstract production treeDec
tdt::TreeAndDecorated<t dt> ::= tree::t  dtree::dt
{ }

abstract production treeDecExpr
tdt::TreeAndDecorated<Expr  Decorated Expr> ::= tree::Expr  dtree::Decorated Expr
{ }
