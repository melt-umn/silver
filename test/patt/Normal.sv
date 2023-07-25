grammar patt;

function stringmatching
Boolean ::= s::String
{
  return case s of
           "true" -> true
         | "false" -> false
         | _ -> true
         end;
}

function stringmatchingCoqStyleVBarSyntax
Boolean ::= s::String
{
  return case s of
         | "true" -> true
         | "false" -> false
         | _ -> true
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
         | _ -> false
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
         | _ -> false
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

equalityTest ( pairmatching((true,true)), true, Boolean, pat_tests ) ;
equalityTest ( pairmatching((true,false)), false, Boolean, pat_tests ) ;
equalityTest ( pairmatching((false,true)), true, Boolean, pat_tests ) ;
equalityTest ( pairmatching((false,false)), true, Boolean, pat_tests ) ;


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
equalityTest ( echotest(just("hi")).fromJust, "hi", String, pat_tests ) ;


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

nonterminal IntPair;
production intPair
top::IntPair ::= Integer Integer {}

wrongCode "intPair has 2 parameters but 0 patterns" {
 function fooDontCare
 String ::=
 {
   return case intPair(1,2) of
            intPair() -> ""
          end;
 }
}

wrongCode "intPair has 2 parameters but 3 patterns" {
 function fooDontCare
 String ::=
 {
   return case intPair(1,2) of
            intPair(b,c,d) -> ""
          end;
 }
}

wrongCode "intPair has 2 parameters but 1 patterns" {
 function fooDontCare
 String ::=
 {
   return case intPair(1,2) of
            intPair(d) -> ""
          end;
 }
}

wrongCode "2 values, but this rule has 1" {
 function fooDontCare
 String ::=
 {
   return case 1, 2 of
          | _ -> "" -- wrong!
          end;
 }
}

wrongCode "2 values, but this rule has 3" {
 function fooDontCare
 String ::=
 {
   return case 1, 2 of
          | _, _, _ -> "" -- wrong!
          end;
 }
}

-- Silver used to crash because pattern used to rely on signature.
global signatureTest :: Integer = case just(1) of just(x) -> x | _ -> 0 end;

-- That toString works on a pattern expression (type information is available)
equalityTest ( toString(case just(1) of just(x) -> x | _ -> 0 end), "1", String, pat_tests ) ;


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


wrongCode "Pattern has overlapping cases" {
  global normalCrashTest223 :: Integer =
    case (1,2) of
    | (_, _) -> 2
    | (_, _) -> 3 -- oops!
    end;
}

-- Make sure Silver doesn't crash compiling this:
wrongCode "2 parameters but 1 patterns" {
  global normalCrashTest225 :: Integer =
    case intPair(1,2) of
    | intPair(_, _) -> 2
    | intPair(_) -> 3 -- oops!
    end;
}

-- Make sure an error is raised for this:
wrongCode "2 parameters but 1 patterns" {
  global normalErrorTest234 :: Integer =
    case intPair(1,2) of
    | intPair(2, 3) -> 2
    | intPair(_) -> 3 -- oops!
    end;
}

wrongCode "2 parameters but 3 patterns" {
  global normalErrorTest243 :: Integer =
    case intPair(1,2) of
    | intPair(2, 3) -> 2
    | intPair(_, _, _) -> 3 -- oops!
    end;
}

wrongCode "2 parameters but 3 patterns" {
  global normalErrorTest257 :: Integer =
    case intPair(1,2) of
    | intPair(_, _, _) -> 3 -- oops!
    | intPair(2, 3) -> 2
    end;
}

wrongCode "Undeclared value" {
  global normalErrorTest265 :: Integer =
    case intPair(1,2) of
    | skdjhfkljshfkjsdh(_, _, _) -> 3 -- oops!
    end;
}

