grammar silver:compiler:extension:tuple;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;

terminal Comma_t ',' ;

-- Question: What attribute occurences should I include w/ TupleList?
nonterminal TupleList with location, config, unparse, env, frame, errors, translation;
synthesized attribute translation :: Expr;

-- We consider only tuples containing two or more elements

concrete production tupleExpr_c
top::Expr ::= '(' tl::TupleList ')'
{
    top.unparse = "(" ++ tl.unparse ++ ")";
    forwards to tupleExpr_a(tl, location = top.location);

}

abstract production tupleExpr_a
top::Expr ::= tl::TupleList
{
    top.unparse = "(" ++ tl.unparse ++ ")";
    forwards to tl.translation;
}

-- TupleList cases:
--   There are two elements in the tuple
concrete production tupleList_2Elements
top::TupleList ::= fst::Expr ',' snd::Expr
{
    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    forwards to tupleList_2a(fst, snd, location = top.location);

}

abstract production tupleList_2a
top::TupleList ::= fst::Expr snd::Expr
{

    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd}) };

}

--   There are more than two elements in the tuple
concrete production tupleList_nElements
top::TupleList ::= fst::Expr ',' snd::TupleList
{

    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    forwards to tupleList_na(fst, snd, location = top.location);

}

abstract production tupleList_na
top::TupleList ::= fst::Expr snd::TupleList
{
    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd.translation}) };
}