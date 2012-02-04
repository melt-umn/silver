grammar patt;

function stringmatching
Boolean ::= s::String
{
  return case s of
           "true" -> true
         | "false" -> false
         end;
}

function stringmatchingCoqStyleVBarSyntax
Boolean ::= s::String
{
  return case s of
         | "true" -> true
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


function lookattees
[Boolean] ::= l::[T]
{
  return if null(l) then []
         else (case head(l) of
                a() -> true
              | b() -> false
              | c() -> true
              | d() -> false
              end) :: lookattees(tail(l));
}

function indivtee
Boolean ::= l::T
{
  return case l of
                a() -> true
              | b() -> false
              | c() -> true
              | d() -> false
              end;
}

equalityTest ( lookattees([a(),b(),c(),d()]), [true, false, true, false],
               [Boolean], pat_tests );

equalityTest ( map(indivtee, [a(),b(),c(),d()]), [true, false, true, false],
               [Boolean], pat_tests );

-- just checking to make sure pattern matching error get out properly
wrongCode "doesnotexist" {
 function fooDontCare
 a ::=
 {
   return case "hi" of
            "boo" -> doesnotexist()
          | "foo" -> wat()
          end;
 }
}

wrongCode "Fewer patterns than expected" {
 function fooDontCare
 String ::=
 {
   return case pair(1,2) of
            pair() -> ""
          end;
 }
}

wrongCode "More patterns than expected" {
 function fooDontCare
 String ::=
 {
   return case pair(1,2) of
            pair(b,c,d) -> ""
          end;
 }
}

wrongCode "Fewer patterns than expected" {
 function fooDontCare
 String ::=
 {
   return case pair(1,2) of
            pair(d) -> ""
          end;
 }
}

{- TODO
wrongCode "Fewer patterns than expected" {
 function fooDontCare
 String ::=
 {
   return case 1, 2 of
          | _ -> "" -- wrong!
          end;
 }
}

wrongCode "More patterns than expected" {
 function fooDontCare
 String ::=
 {
   return case 1, 2 of
          | _, _, _ -> "" -- wrong!
          end;
 }
}
-}

-- Silver used to crash because pattern used to rely on signature.
global signatureTest :: Integer = case just(1) of just(x) -> x end;

-- That toString works on a pattern expression (type information is available)
equalityTest ( toString(case just(1) of just(x) -> x end), "1", String, pat_tests ) ;


------------------------
-- Some typechecking issues popped up with:

nonterminal OrdinaryNonterminal with ordinaryAttribute;
synthesized attribute ordinaryAttribute :: String;

abstract production ordinaryProduction
top::OrdinaryNonterminal ::= ordinaryUndecorated::OrdinaryNonterminal
{}

function ordinaryFunction
String ::= undecoratedFirst::OrdinaryNonterminal  decoratedSecond::Decorated OrdinaryNonterminal
{ return ""; }
function ordinaryFunction2
String ::= undecoratedFirst::OrdinaryNonterminal  attrSecond::String
{ return ""; }

function doesThisGenerateSilverErrors
String ::= d::OrdinaryNonterminal
{ return case d of
         | ordinaryProduction(p) -> ordinaryFunction(p,p) -- This should be okay!!
         end;
}
function doesThisGenerateJavaCodeWithErrors
String ::= d::OrdinaryNonterminal
{ return case d of
         | ordinaryProduction(p) -> ordinaryFunction2(p,p.ordinaryAttribute) -- Just checking we don't have type issues in the generated Java with this 'p' used both ways.
         end;
}
-------------------
