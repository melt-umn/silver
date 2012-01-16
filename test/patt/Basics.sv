grammar patt;

function basic1 -- Just maybe :)
Integer ::= s::Maybe<Boolean>
{
  return case s of
          just(v) -> 1
         |nothing() -> 2
         end;
}

equalityTest ( basic1(just(true)), 1, Integer, pat_tests ) ;
equalityTest ( basic1(nothing()), 2, Integer, pat_tests ) ;


function basic2 -- nest translation
Boolean ::= s::Pair<Maybe<Boolean> Maybe<Pair<Boolean String>>>
{
  return case s of
          pair(just(bv), just(pair(sbv, ssv))) -> bv && sbv
         |pair(just(bv), nothing()) -> !bv
         |pair(nothing(), _) -> false
         end;
}

equalityTest ( basic2(pair(just(true), just(pair(true, "")))), true, Boolean, pat_tests ) ;
equalityTest ( basic2(pair(just(false), just(pair(true, "")))), false, Boolean, pat_tests ) ;
equalityTest ( basic2(pair(just(true), just(pair(false, "")))), false, Boolean, pat_tests ) ;
equalityTest ( basic2(pair(just(true), nothing())), false, Boolean, pat_tests ) ;
equalityTest ( basic2(pair(just(false), nothing())), true, Boolean, pat_tests ) ;
equalityTest ( basic2(pair(nothing(), nothing())), false, Boolean, pat_tests ) ;
equalityTest ( basic2(pair(nothing(), just(pair(true, "")))), false, Boolean, pat_tests ) ;


function basic3 -- "nondeterministic" multiple matching
String ::= s::Maybe<String>  t::Maybe<String>  u::Maybe<String>
{
  return case s, t, u of
    aa, just(bb), cc -> bb
  | just(aa), bb, cc -> aa
  | aa, bb, just(cc) -> cc
  | _, _, _ -> "oh noes"
  end;
}

equalityTest ( basic3(nothing(), nothing(), nothing()), "oh noes", String, pat_tests ) ;
equalityTest ( basic3(nothing(), just("w"), nothing()), "w", String, pat_tests ) ;
equalityTest ( basic3(just("w"), nothing(), nothing()), "w", String, pat_tests ) ;
equalityTest ( basic3(nothing(), nothing(), just("w")), "w", String, pat_tests ) ;

-- TODO: Well, we do left-to-right preferred above all. Haskell preferrs top-to-bottom above all....
equalityTest ( basic3(just("g"), just("w"), just("h")), "g", String, pat_tests ) ;

function basic4 -- using integers
Integer ::= p::Pair<Integer Maybe<Integer>>
{
  return case p of
           pair(1, nothing()) -> 1
         | pair(1, just(_)) -> 2
         | pair(2, nothing()) -> 3
         | pair(_, _) -> 4
         end;
}

equalityTest ( basic4(pair(1, nothing())), 1, Integer, pat_tests ) ;
equalityTest ( basic4(pair(1, just(1))), 2, Integer, pat_tests ) ;
equalityTest ( basic4(pair(2, nothing())), 3, Integer, pat_tests ) ;
equalityTest ( basic4(pair(2, just(1))), 4, Integer, pat_tests ) ;
equalityTest ( basic4(pair(77, just(1))), 4, Integer, pat_tests ) ;

function basic5 -- using strings
Integer ::= p::Pair<String Maybe<Integer>>
{
  return case p of
           pair("1", nothing()) -> 1
         | pair("1", just(_)) -> 2
         | pair("2", nothing()) -> 3
         | pair(_, _) -> 4
         end;
}

equalityTest ( basic5(pair("1", nothing())), 1, Integer, pat_tests ) ;
equalityTest ( basic5(pair("1", just(1))), 2, Integer, pat_tests ) ;
equalityTest ( basic5(pair("2", nothing())), 3, Integer, pat_tests ) ;
equalityTest ( basic5(pair("2", just(1))), 4, Integer, pat_tests ) ;
equalityTest ( basic5(pair("77", just(1))), 4, Integer, pat_tests ) ;

function basic6 -- using _
Integer ::= p::Pair<String String>
{
  return case p of
           pair("1", _) -> 1
         | pair("2", _) -> 2
         | pair(_, "1") -> 3
         | pair(_, _) -> 4
         end;
}

equalityTest ( basic6(pair("1", "1")), 1, Integer, pat_tests ) ;
equalityTest ( basic6(pair("1", "2")), 1, Integer, pat_tests ) ;
equalityTest ( basic6(pair("2", "1")), 2, Integer, pat_tests ) ;
equalityTest ( basic6(pair("2", "2")), 2, Integer, pat_tests ) ;
equalityTest ( basic6(pair("77", "1")), 3, Integer, pat_tests ) ;
equalityTest ( basic6(pair("77", "2")), 4, Integer, pat_tests ) ;

nonterminal MyTriple<a b c>;
abstract production mytriple
t::MyTriple<a b c> ::= a b c {}

function basic7 -- using the same names of pattern variables
Integer ::= p::MyTriple<Integer Maybe<Integer> Maybe<Integer>>
{
return case p of
  mytriple(aa, bb, just(cc)) -> aa + cc
| mytriple(bb, just(aa), cc) -> aa + bb
end;
}

-- once, this test returned 40, just to clarify what we're testing here.
equalityTest ( basic7(mytriple(1,just(20),just(300))), 21, Integer, pat_tests ) ;
equalityTest ( basic7(mytriple(1,nothing(),just(300))), 301, Integer, pat_tests ) ;

function basic8 -- using mixed name/fullnames
Integer ::= p::Pair<Integer Integer>
{
return case p of
| pair(1,2) -> 1
| core:pair(1,_) -> 2
| pair(2,1) -> 3
| core:pair(_,1) -> 4
end;
}

equalityTest ( basic8(pair(1,2)), 1, Integer, pat_tests );
equalityTest ( basic8(pair(1,3)), 2, Integer, pat_tests );
equalityTest ( basic8(pair(2,1)), 3, Integer, pat_tests );
equalityTest ( basic8(pair(3,1)), 4, Integer, pat_tests );

