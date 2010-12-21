grammar xrobots:concretesyntax ;

 import xrobots:terminals ;
-- import tutorials:xrobots:abstractsyntax ;

nonterminal ArgList_c with pp;
--synthesized attribute ast_ArgList :: ArgList occurs on ArgList_c;


concrete production ArgList_Emtpy_c
a::ArgList_c ::=
{
	a.pp = "Empty ArgList";
--	a.ast_ArgList = argList_empty();
}

concrete production ArgList_cons_c
a::ArgList_c ::= aIn::ArgList_c ',' n::Id_t ':' ':' e::Expr_c
{
	a.pp = aIn.pp ++ e.pp;
--	a.ast_ArgList = argList_cons(aIn.ast_ArgList, e.ast_Expr, n.lexeme);
}

concrete production ArgList_one_c
a::ArgList_c ::=  n::Id_t ':' ':' e::Expr_c
{
	a.pp =  e.pp;
--	a.ast_ArgList = argList_one( e.ast_Expr, n.lexeme);
}
