-- Note: We consider only tuples containing two or more elements

grammar silver:compiler:extension:tuple;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:type;

imports silver:compiler:extension:patternmatching;

nonterminal TupleList with location, unparse, translation;
synthesized attribute translation :: Expr;

concrete production emptyTuple
top::Expr ::= '(' ')'
{
  top.unparse = "()";
  top.typerep = tupleType([]);
  forwards to Silver_Expr { silver:core:unit() };
}

concrete production tupleExpr
top::Expr ::= '(' tl::TupleList ')'
{
  top.unparse = "(" ++ tl.unparse ++ ")";
  top.typerep = tupleType(performSubstitution(forward.typerep, forward.upSubst).tupleElems);
  forwards to tl.translation;
}

-- TupleList cases:
-- There are two elements in the tuple
concrete production tupleList_2Elements
top::TupleList ::= fst::Expr ',' snd::Expr
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd}) };
}

-- There are more than two elements in the tuple
concrete production tupleList_nElements
top::TupleList ::= fst::Expr ',' snd::TupleList
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd.translation}) };
}

-- Pattern matching on tuples
nonterminal TuplePatternList with location, unparse;

concrete production emptyTuplePattern
top::Pattern ::= '(' ')'
{
  top.unparse = "()";
  forwards to Silver_Pattern { silver:core:unit() };
}

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