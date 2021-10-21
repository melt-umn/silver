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
          (just(bv), just((sbv, ssv))) -> bv && sbv
         |(just(bv), nothing()) -> !bv
         |(nothing(), _) -> false
         end;
}

equalityTest ( basic2((just(true), just((true, "")))), true, Boolean, pat_tests ) ;
equalityTest ( basic2((just(false), just((true, "")))), false, Boolean, pat_tests ) ;
equalityTest ( basic2((just(true), just((false, "")))), false, Boolean, pat_tests ) ;
equalityTest ( basic2((just(true), nothing())), false, Boolean, pat_tests ) ;
equalityTest ( basic2((just(false), nothing())), true, Boolean, pat_tests ) ;
equalityTest ( basic2((nothing(), nothing())), false, Boolean, pat_tests ) ;
equalityTest ( basic2((nothing(), just((true, "")))), false, Boolean, pat_tests ) ;


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

-- test top-to-bottom matching
equalityTest ( basic3(just("g"), just("w"), just("h")), "w", String, pat_tests ) ;

function basic4 -- using integers
Integer ::= p::Pair<Integer Maybe<Integer>>
{
  return case p of
           (1, nothing()) -> 1
         | (1, just(_)) -> 2
         | (2, nothing()) -> 3
         | (_, _) -> 4
         end;
}

equalityTest ( basic4((1, nothing())), 1, Integer, pat_tests ) ;
equalityTest ( basic4((1, just(1))), 2, Integer, pat_tests ) ;
equalityTest ( basic4((2, nothing())), 3, Integer, pat_tests ) ;
equalityTest ( basic4((2, just(1))), 4, Integer, pat_tests ) ;
equalityTest ( basic4((77, just(1))), 4, Integer, pat_tests ) ;

function basic5 -- using strings
Integer ::= p::Pair<String Maybe<Integer>>
{
  return case p of
           ("1", nothing()) -> 1
         | ("1", just(_)) -> 2
         | ("2", nothing()) -> 3
         | (_, _) -> 4
         end;
}

equalityTest ( basic5(("1", nothing())), 1, Integer, pat_tests ) ;
equalityTest ( basic5(("1", just(1))), 2, Integer, pat_tests ) ;
equalityTest ( basic5(("2", nothing())), 3, Integer, pat_tests ) ;
equalityTest ( basic5(("2", just(1))), 4, Integer, pat_tests ) ;
equalityTest ( basic5(("77", just(1))), 4, Integer, pat_tests ) ;

function basic6 -- using _
Integer ::= p::Pair<String String>
{
  return case p of
           ("1", _) -> 1
         | ("2", _) -> 2
         | (_, "1") -> 3
         | (_, _) -> 4
         end;
}

equalityTest ( basic6(("1", "1")), 1, Integer, pat_tests ) ;
equalityTest ( basic6(("1", "2")), 1, Integer, pat_tests ) ;
equalityTest ( basic6(("2", "1")), 2, Integer, pat_tests ) ;
equalityTest ( basic6(("2", "2")), 2, Integer, pat_tests ) ;
equalityTest ( basic6(("77", "1")), 3, Integer, pat_tests ) ;
equalityTest ( basic6(("77", "2")), 4, Integer, pat_tests ) ;

nonterminal MyTriple<a b c>;
abstract production mytriple
t::MyTriple<a b c> ::= a b c {}

function basic7 -- using the same names of pattern variables
Integer ::= p::MyTriple<Integer Maybe<Integer> Maybe<Integer>>
{
return case p of
  mytriple(aa, bb, just(cc)) -> aa + cc
| mytriple(bb, just(aa), cc) -> aa + bb
| _ -> error("Added to make this a complete match to avoid a warning")
end;
}

-- once, this test returned 40, just to clarify what we're testing here.
equalityTest ( basic7(mytriple(1,just(20),just(300))), 301, Integer, pat_tests ) ;
equalityTest ( basic7(mytriple(1,nothing(),just(300))), 301, Integer, pat_tests ) ;

function basic8 -- using mixed name/fullnames
Integer ::= p::Pair<Integer Integer>
{
return case p of
| (1,2) -> 1
| silver:core:pair(1,_) -> 2
| (2,1) -> 3
| silver:core:pair(_,1) -> 4
| _ -> 5
end;
}

equalityTest ( basic8((1,2)), 1, Integer, pat_tests );
equalityTest ( basic8((1,3)), 2, Integer, pat_tests );
equalityTest ( basic8((2,1)), 3, Integer, pat_tests );
equalityTest ( basic8((3,1)), 4, Integer, pat_tests );


-- more testing mixing variable and constructor patterns
function basic9
Integer ::= a::Maybe<Integer> b::Maybe<Integer> c::Maybe<Integer>
{
return case a, b, c of
| aa, just(bb), nothing() -> bb
| just(aa), bb, cc -> aa
| aa, just(bb), just(cc) -> bb + cc
| nothing(), bb, cc -> 0
end;
}

equalityTest ( basic9(just(1), just(2), just(5)), 1, Integer, pat_tests ) ;
equalityTest ( basic9(just(1), just(2), nothing()), 2, Integer, pat_tests ) ;
equalityTest ( basic9(just(1), nothing(), just(5)), 1, Integer, pat_tests ) ;
equalityTest ( basic9(just(1), nothing(), nothing()), 1, Integer, pat_tests ) ;
equalityTest ( basic9(nothing(), just(2), just(5)), 7, Integer, pat_tests ) ;
equalityTest ( basic9(nothing(), just(2), nothing()), 2, Integer, pat_tests ) ;
equalityTest ( basic9(nothing(), nothing(), just(5)), 0, Integer, pat_tests ) ;
equalityTest ( basic9(nothing(), nothing(), nothing()), 0, Integer, pat_tests ) ;

