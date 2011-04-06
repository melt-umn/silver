grammar patt;

import lib:testing ;
import lib:extcore ;


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

