grammar silver:extension:bidirtransform;

synthesized attribute rawPatternList::[Pattern] occurs on PatternList;

aspect production patternList_one
top::PatternList ::= p::Pattern
{
    top.rawPatternList = [p];
}
aspect production patternList_more
top::PatternList ::= p::Pattern ',' ps1::PatternList
{
    top.rawPatternList = [p] ++ ps1.rawPatternList;
}
aspect production patternList_nil
top::PatternList ::=
{  
    top.rawPatternList = [];
}
