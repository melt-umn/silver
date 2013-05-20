
import silver:testing;
import lib:extcore;
import silver:util:raw:graph as g;
import silver:util:raw:treeset as set;
import stdlib;

function compareInteger
Integer ::= a::Integer b::Integer
{
  return a - b;
}
function iset
set:Set<Integer> ::= l::[Integer]
{
  return set:add(l, set:empty(compareInteger));
}

global e :: g:Graph<Integer> = g:empty(compareInteger);

global g1 :: g:Graph<Integer> =
  g:add([
    pair(1,1),
    pair(1,2),
    pair(2,3),
    pair(3,4),
    pair(5,6)], e);

global g2 :: g:Graph<Integer> =
  g:add([
    pair(6,1),
    pair(1,1),
    pair(3,2)], g1);

-- we test g2 first, to ensure it is evaluated, then we test g1, to make sure it didn't change.
equalityTest ( g:contains(pair(6,1), g2), true, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(3,2), g2), true, Boolean, core_tests ) ;

equalityTest ( g:contains(pair(1,1), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(1,2), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(2,3), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(3,4), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(5,6), g1), true, Boolean, core_tests ) ;

equalityTest ( g:contains(pair(6,1), g1), false, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(1,6), g1), false, Boolean, core_tests ) ;
equalityTest ( g:contains(pair(3,2), g1), false, Boolean, core_tests ) ;

equalityTest ( g:contains(pair(99,87), g1), false, Boolean, core_tests ) ;

-- set equality on edges from...
equalityTest ( set:equals(g:edgesFrom(1, g1), iset([1,2])), true, Boolean, core_tests ) ;
equalityTest ( set:equals(g:edgesFrom(1, g2), iset([1,2])), true, Boolean, core_tests ) ;
equalityTest ( set:equals(g:edgesFrom(6, g1), iset([])), true, Boolean, core_tests ) ;
equalityTest ( set:equals(g:edgesFrom(56, g1), iset([])), true, Boolean, core_tests ) ;

-- test length as a proxy for correctness...
equalityTest ( length(g:toList(g1)), 5, Integer, core_tests ) ;
equalityTest ( length(g:toList(g2)), 7, Integer, core_tests ) ;

-- Line graph
global g3 :: g:Graph<Integer> =
  g:add([
    pair(1,2),
    pair(2,3),
    pair(3,4),
    pair(4,5),
    pair(5,6)], e);

-- Deps on all later vertexes
global g4 :: g:Graph<Integer> =
  g:transitiveClosure(g3);

-- All deps on all
global g5 :: g:Graph<Integer> =
  g:transitiveClosure(
    g:add([pair(6,1)], g3));

-- Should be same as g5
global g6 :: g:Graph<Integer> =
  g:repairClosure([pair(6,1)], g4);

equalityTest ( length(g:toList(g3)), 5, Integer, core_tests ) ;
equalityTest ( length(g:toList(g4)), (0 + 1 + 2 + 3 + 4 + 5), Integer, core_tests ) ;
equalityTest ( length(g:toList(g5)), (6 * 6), Integer, core_tests ) ;
equalityTest ( length(g:toList(g6)), (6 * 6), Integer, core_tests ) ;


