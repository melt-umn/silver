grammar patt;

function stringmatching
Boolean ::= s::String
{
  return case s of
           "true" -> true
         | "false" -> false
         end;
}

equalityTest ( stringmatching("true"), true, Boolean, pat_tests ) ;
equalityTest ( stringmatching("false"), false, Boolean, pat_tests ) ;

function smaybematching
Boolean ::= s::Maybe<String>
{
  return case s of
           just("true") -> true
         | just("false") -> false
         | nothing() -> false
         end;
}

equalityTest ( smaybematching(just("true")), true, Boolean, pat_tests ) ;
equalityTest ( smaybematching(just("false")), false, Boolean, pat_tests ) ;
equalityTest ( smaybematching(nothing()), false, Boolean, pat_tests ) ;

function smaybemaybematching
Boolean ::= s::Maybe<Maybe<String>>
{
  return case s of
           just(just("true")) -> true
         | just(nothing()) -> false
         | nothing() -> false
         end;
}

equalityTest ( smaybemaybematching(just(just("true"))), true, Boolean, pat_tests ) ;
equalityTest ( smaybemaybematching(just(nothing())), false, Boolean, pat_tests ) ;
equalityTest ( smaybemaybematching(nothing()), false, Boolean, pat_tests ) ;


function pairmatching
Boolean ::= p::Pair<Boolean Boolean>
{
  return case p.fst, p.snd of
           true, true -> true
         | true, false -> false
         | false, _ -> true
         | _, _ -> error("wat")
         end;
}

equalityTest ( pairmatching(pair(true,true)), true, Boolean, pat_tests ) ;
equalityTest ( pairmatching(pair(true,false)), false, Boolean, pat_tests ) ;
equalityTest ( pairmatching(pair(false,true)), true, Boolean, pat_tests ) ;
equalityTest ( pairmatching(pair(false,false)), true, Boolean, pat_tests ) ;


nonterminal Echo<a> with input<a>, output<a>;
synthesized attribute output<a>::a;
inherited attribute input<a>::a;

abstract production echo
t::Echo<a> ::=
{
  t.output = t.input;
}

function echotest
a ::= s::a
{
  return case decorate echo() with {input=s;} of
           t -> t.output
         end;
}

equalityTest ( echotest("foo"), "foo", String, pat_tests ) ;
equalityTest ( echotest(decorate just("hi") with {}).fromJust, "hi", String, pat_tests ) ;



