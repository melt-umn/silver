grammar silver:compiler:extension:tuple;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type;

imports silver:compiler:extension:patternmatching;

terminal Comma_t ',' ;

nonterminal ListOfTypeExprs with location, unparse, te_translation;
synthesized attribute te_translation :: TypeExpr;
nonterminal TupleList with location, unparse, translation;
synthesized attribute translation :: Expr;

-- Note: We consider only tuples containing two or more elements

concrete production tupleTypeExpr
top::TypeExpr ::= '(' tes::ListOfTypeExprs ')'
{
    top.unparse = '(' ++ tes.unparse ++ ')';
    forwards to tes.translation;
}

concrete production tupleTypeExpr2
top::ListOfTypeExprs ::= te1::TypeExpr ',' te2::TypeExpr
{
    top.unparse = te1.unparse ++ ',' ++ te2.unparse;
    --TO DO: top.te_translation = $TypeExpr { silver:core:pair($Expr{te1}, $Expr{te2}) };

}

concrete production tupleTypeExprn
top::ListOfTypeExprs ::= te::TypeExpr ',' tes::ListOfTypeExprs
{
    top.unparse = te1.unparse ++ ',' ++ te2.unparse;
    --TO DO: top.te_translation = $TypeExpr { silver:core:pair($Expr{te1}, $Expr{te2.translation}) };
}


concrete production tuple_c
top::Expr ::= '(' tl::TupleList ')'
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
    top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd}) };

}

--   There are more than two elements in the tuple
concrete production tupleList_nElements
top::TupleList ::= fst::Expr ',' snd::TupleList
{

    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd.translation}) };

}

-- Pattern matching on tuples

nonterminal TuplePatternList with location, unparse, patternList;

-- Turns TuplePatternList into [Pattern]
synthesized attribute patternList :: [Decorated Pattern];

concrete production tuplePattern
top::Pattern ::= '(' ts::TuplePatternList ')'
{
    top.unparse = s"(${ts.unparse})";
    forwards to ts.asTuplePattern;
}

synthesized attribute asTuplePattern::Pattern occurs on TuplePatternList;

concrete production patternTuple_two
top::TuplePatternList ::= fst::Pattern ',' snd::Pattern
{
    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    top.asTuplePattern = Silver_Pattern { silver:core:pair($Pattern{fst}, $Pattern{snd}) };
}

concrete production patternTuple_more
top::TuplePatternList ::= fst::Pattern ',' snd::TuplePatternList
{
    top.unparse = fst.unparse ++ ", " ++ snd.unparse;
    top.asTuplePattern = Silver_Pattern { silver:core:pair($Pattern{fst}, $Pattern{snd.asTuplePattern}) };
}