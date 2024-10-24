-- Pattern matching on tuples
tracked nonterminal TuplePatternList with unparse;

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
  forwards to @ts.asTuplePattern;
}

-- Used to convert the comma-separated list of patterns 
-- that make up the tuple into a pair pattern:
translation attribute asTuplePattern::Pattern occurs on TuplePatternList;

concrete production patternTuple_two
top::TuplePatternList ::= fst::Pattern ',' snd::Pattern
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.asTuplePattern = Silver_Pattern { silver:core:pair(fst=$Pattern{@fst}, snd=$Pattern{@snd}) };
}

concrete production patternTuple_more
top::TuplePatternList ::= fst::Pattern ',' snd::TuplePatternList
{
  top.unparse = fst.unparse ++ ", " ++ snd.unparse;
  top.asTuplePattern = Silver_Pattern { silver:core:pair(fst=$Pattern{@fst}, snd=$Pattern{@snd.asTuplePattern}) };
}