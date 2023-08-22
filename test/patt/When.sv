grammar patt;


function testVariableMatches
Integer ::= input::Integer
{
  return  case input of
          | vara when vara > 3 -> 0
          | varb when varb < 0 -> 1
          | varc -> 2
          end;
}

equalityTest ( testVariableMatches(5), 0, Integer, pat_tests ) ;
equalityTest ( testVariableMatches(-1), 1, Integer, pat_tests ) ;
equalityTest ( testVariableMatches(2), 2, Integer, pat_tests ) ;


nonterminal Nonsense;

abstract production n1
top::Nonsense ::=
{}

abstract production n2
top::Nonsense ::= c1::Nonsense t2::Nonsense
{}

abstract production n3
top::Nonsense ::= c1::Nonsense t2::Integer
{}

abstract production n4
top::Nonsense ::=
{}

function testNonterminalMatches
Integer ::= nn1::Nonsense nn2::Nonsense
{
  return  case nn1, nn2 of
          | n3(n1(), i1), n3(_, i2) when i1 == i2 -> 0
          | n3(_, i1), n3(_, i2) when i1 <= i2 -> 1
          | n2(vara, n3(varb, i)), _ when i > 5 -> 2
          | n1(), n1() -> 3
          | n2(_, n1()), n2(_, n1()) -> 4
          | _, _ -> 5
          end;
}

equalityTest ( testNonterminalMatches( n3(n1(), 5),   n3(n4(), 5) ),
               0, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatches( n3(n4(), 4),   n3(n4(), 5) ),
               1, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatches( n3(n1(), 3),   n3(n4(), 5) ),
               1, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatches( n2(n4(), n3(n4(), 6)),   n4() ),
               2, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatches( n2(n4(),   n3(n4(), 0)), n4() ),
               5, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatches( n1(),   n1() ),
               3, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatches( n2(n4(), n1()),   n2(n4(), n1()) ),
               4, Integer, pat_tests ) ;


function testMultipleVars
Boolean ::= p1::Pair<Integer Integer> p2::Pair<Integer Integer>
{
  return  case p1, p2 of
          | (vara,varb), (varc,vard) when vara + varb == varc + vard -> true
          | (vara,varb), (varc,vard) -> false
          end;
}

equalityTest ( testMultipleVars((3,4), (7,0)), true, Boolean, pat_tests ) ;
equalityTest ( testMultipleVars((3,4), (3,3)), false, Boolean, pat_tests ) ;



wrongCode "Pattern has overlapping cases" {
  global whenCrashTest1 :: Integer =
    case 5 of
    | a when a > 3 -> 0
    --the last two are overlapping patterns
    | b -> b
    | c -> c
    end;
}


--Test that things work correctly with the matches keyword for whens
function testNonterminalMatchesWhenMatches
Integer ::= nn1::Nonsense nn2::Nonsense
{
  return  case nn1, nn2 of
          | n3(n1(), i1), n3(c1, i2) when c1 matches n1() -> 0
          | n3(c1, i1), n3(_, i2) when c1 matches n2(a1, b1) -> 1
          | n3(_, i1), n3(_, i2) -> 2
          | n2(vara, n3(varb, i)), _ when i > 5 matches true -> 3
          | _, _ -> 4
          end;
}

equalityTest ( testNonterminalMatchesWhenMatches( n3(n1(), 5),   n3(n1(), 5) ),
               0, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatchesWhenMatches( n3(n2(n1(), n1()), 5),   n3(n4(), 5) ),
               1, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatchesWhenMatches( n3(n1(), 5),   n3(n4(), 5) ),
               2, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatchesWhenMatches( n2(n4(), n3(n4(), 6)),   n4() ),
               3, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatchesWhenMatches( n2(n4(),  n3(n4(), 0)), n4() ),
               4, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatchesWhenMatches( n1(),   n1() ),
               4, Integer, pat_tests ) ;
equalityTest ( testNonterminalMatchesWhenMatches( n2(n4(), n1()),   n2(n4(), n1()) ),
               4, Integer, pat_tests ) ;


