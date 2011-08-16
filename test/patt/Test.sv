grammar patt;

imports silver:testing ;
imports lib:extcore ;


nonterminal T;

abstract production a
top::T ::=
{
}
abstract production b
top::T ::=
{
}
abstract production c
top::T ::=
{
  forwards to a();
}
abstract production d
top::T ::=
{
}

function tName
String ::= t::T
{
  return match t return String with
           a() -> "a"
         | b() -> "b"
          else -> "unknown!"
         end;
}

function tNameAll
String ::= t::T
{
  return match t return String with
           a() -> "a"
         | b() -> "b"
         | c() -> "c"
         | d() -> "d"
          else -> "unknown!"
         end;
}


mainTestSuite pat_tests ;

equalityTest ( tName(a()), "a", String, pat_tests ) ;
equalityTest ( tName(b()), "b", String, pat_tests ) ;
equalityTest ( tName(c()), "a", String, pat_tests ) ; -- sees through forward!
equalityTest ( tName(d()), "unknown!", String, pat_tests ) ;

equalityTest ( tNameAll(a()), "a", String, pat_tests ) ;
equalityTest ( tNameAll(b()), "b", String, pat_tests ) ;
equalityTest ( tNameAll(c()), "c", String, pat_tests ) ; -- no seeums!
equalityTest ( tNameAll(d()), "d", String, pat_tests ) ;



--- Part 2: GADTS

nonterminal Unit;
nonterminal Arrow<a b>;

nonterminal Type<a>;
abstract production unit   t::Type<Unit> ::= {}
abstract production arrow  t::Type<Arrow<a b>> ::= Type<a> Type<b> {}

nonterminal Eq<a b>;
abstract production eq   e::Eq<a a> ::= {}

function typeEquals
Maybe<Eq<a b>> ::= ta::Type<a>  tb::Type<b>
{
  return match ta return Maybe<Eq<a b>> with
           unit() -> match tb return Maybe<Eq<a b>> with
                       unit() -> just(eq())
                       else -> nothing()
                     end
         | arrow(aa, ab) ->
                     match tb return Maybe<Eq<a b>> with
                       arrow(ba, bb) -> match decorate typeEquals(aa, ba) with {} return Maybe<Eq<a b>> with
                                          just(lrn1) -> 
                                              match decorate lrn1 with {} return Maybe<Eq<a b>> with
                                                eq() ->
                                                        match decorate typeEquals(ab, bb) with {} return Maybe<Eq<a b>> with
                                                          just(lrn2) ->
                                                             match decorate lrn2 with {} return Maybe<Eq<a b>> with
                                                               eq() -> just(eq())
                                                               else -> nothing()
                                                             end
                                                          else -> nothing()
                                                        end
                                                else -> nothing()
                                              end
                                          else -> nothing()
                                        end
                       else -> nothing()
                     end
           else -> nothing()
         end; 
}

equalityTest ( typeEquals(unit(),unit()).isJust, true, Boolean, pat_tests ) ;
equalityTest ( typeEquals(arrow(unit(),unit()),unit()).isJust, false, Boolean, pat_tests ) ;
equalityTest ( typeEquals(arrow(unit(),unit()),arrow(unit(),unit())).isJust, true, Boolean, pat_tests ) ;

nonterminal Expr<a>;
abstract production etrue  e::Expr<Boolean> ::= {}
abstract production eone   e::Expr<Integer> ::= {}

function retExpr
a ::= e::Expr<a>
{
  return match e return a with
          etrue() -> true
         |eone() -> 1
          else -> error("no value!")
         end;
}

equalityTest ( retExpr(etrue()), true, Boolean, pat_tests ) ;
equalityTest ( retExpr(eone()), 1, Integer, pat_tests ) ;

wrongCode "pattern expression should have type" {
 function wrongTypeEquals
 Maybe<Eq<a b>> ::= ta::Type<a>  tb::Type<b>
 {
  return match ta return Maybe<Eq<a b>> with
          arrow(aa, ab) ->
            match tb return Maybe<Eq<a b>> with
              arrow(ba, bb) -> just(eq())
              else -> error("")
            end
          else -> error("")
         end;
 }
}

