grammar silver:definition:concrete_syntax;

function precSpecMergeSort
[Decorated ParserPrecSpec] ::= c1::[Decorated ParserPrecSpec]
{
  return precSpecMergeSortHelp(mapParserPrecSpecList(c1));
}

function precSpecMergeSortHelp
[Decorated ParserPrecSpec] ::= c1::[[Decorated ParserPrecSpec]]
{
  return if null(c1) then []
         else if null(tail(c1)) then head(c1)
         else precSpecMergeSortHelp(psMergePairs(c1));
}

function psMergePairs
[[Decorated ParserPrecSpec]] ::= c1::[[Decorated ParserPrecSpec]]
{
  return if null(c1) then []
         else if null(tail(c1)) then c1
         else cons(psMergeParserPrecSpec(head(c1), head(tail(c1))), psMergePairs(tail(tail(c1))));
}

function psMergeParserPrecSpec
[Decorated ParserPrecSpec] ::= c1::[Decorated ParserPrecSpec] c2::[Decorated ParserPrecSpec]
{
  return if null(c2)
         then c1
         else if null(c1)
         then c2
         else if  head(c1).parserPrecedence  >  head(c2).parserPrecedence
              then cons ( head(c2) , merge_c1_c2_tail )
              else cons ( head(c1) , merge_c1_tail_c2 ) ;

  local attribute merge_c1_c2_tail :: [Decorated ParserPrecSpec] ;
  merge_c1_c2_tail = psMergeParserPrecSpec ( c1, tail(c2) ) ;

  local attribute merge_c1_tail_c2 :: [Decorated ParserPrecSpec] ;
  merge_c1_tail_c2 = psMergeParserPrecSpec ( tail(c1), c2 ) ;
}

----------------------------------------------------------------
-- List of References to AGSpec that are terminal modifiers

function mapParserPrecSpecList
[[Decorated ParserPrecSpec]] ::= c1::[Decorated ParserPrecSpec]
{
  return if null(c1)
         then []
         else cons([head(c1)], mapParserPrecSpecList (tail(c1)) ) ;
}



