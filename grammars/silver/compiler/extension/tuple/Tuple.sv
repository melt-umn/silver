-- Note: We consider only tuples containing two or more elements

grammar silver:compiler:extension:tuple;

imports silver:compiler:definition:core;
imports silver:compiler:definition:env;
imports silver:compiler:definition:type:syntax;
imports silver:compiler:definition:type;

imports silver:compiler:analysis:typechecking:core;

imports silver:compiler:extension:patternmatching;
--imports silver:compiler:extension:list;

terminal Comma_t ',' ;

abstract production tupleType
top::Type ::= ts::[Type]
{
  top.typepp = "(" ++ printTupleTypeList(ts) ++ ")";
  forwards to foldr1(\ t1::Type t2::Type -> appType(appType(nonterminalType("silver:core:Pair", 2, false), t1), t2), ts);
}

function printTupleTypeList
String ::= ts::[Type]
{
  return case ts of
  | [ty] -> ty.typepp
  | ty::tys -> ty.typepp ++ ", " ++ printTupleTypeList(tys)
  end;
}

nonterminal TupleList with location, unparse, env, downSubst, typelist, translation;
synthesized attribute translation :: Expr;
synthesized attribute typelist :: [Type];

concrete production emptyTuple
top::Expr ::= '(' ')'
{
  top.unparse = "()";
  forwards to Silver_Expr { silver:core:unit() };
}

concrete production tupleExpr
top::Expr ::= '(' tl::TupleList ')'
{
  top.unparse = "(" ++ tl.unparse ++ ")";
  top.typerep = tupleType(tl.typelist);
  forwards to tl.translation;
}

-- TupleList cases:
-- There are two elements in the tuple
concrete production tupleList_2Elements
top::TupleList ::= fst::Expr ',' snd::Expr
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.typelist = [fst.typerep, snd.typerep];
  top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd}) };
}

-- There are more than two elements in the tuple
concrete production tupleList_nElements
top::TupleList ::= fst::Expr ',' snd::TupleList
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.typelist = fst.typerep::snd.typelist;
  top.translation = Silver_Expr { silver:core:pair($Expr{fst}, $Expr{snd.translation}) };
}

-- Pattern matching on tuples
nonterminal TuplePatternList with location, unparse, patternList;
-- Turns TuplePatternList into [Pattern]
synthesized attribute patternList :: [Decorated Pattern];

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