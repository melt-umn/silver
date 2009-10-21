grammar tutorials:dc ;
-- export tutorials:dc ;

-- Concrete Syntax --
---------------------

nonterminal Root_c with pp ;

synthesized attribute ast_Root :: Root occurs on Root_c ;

concrete production root_c
r::Root_c ::= e::Expr_c 
{
 r.pp = e.pp ;
 r.ast_Root = root(e.ast_Expr);
}


-- Abstract Syntax --
---------------------

nonterminal Root ;

synthesized attribute pp :: String ;

attribute pp occurs on Root ;

synthesized attribute value :: Integer ;

attribute value occurs on Root ;

abstract production root
r::Root ::= e::Expr
{
 r.pp = e.pp ;
 r.value = e.value ;
}
