
import silver:testing;
import silver:util:graph as g;
import silver:util:treeset as set;
import stdlib;

fun iset set:Set<Integer> ::= l::[Integer] = set:add(l, set:empty());

global e :: g:Graph<Integer> = g:empty();

global g1 :: g:Graph<Integer> =
  g:add([
    (1,1),
    (1,2),
    (2,3),
    (3,4),
    (5,6)], e);

global g2 :: g:Graph<Integer> =
  g:add([
    (6,1),
    (1,1),
    (3,2)], g1);

-- we test g2 first, to ensure it is evaluated, then we test g1, to make sure it didn't change.
equalityTest ( g:contains((6,1), g2), true, Boolean, core_tests ) ;
equalityTest ( g:contains((3,2), g2), true, Boolean, core_tests ) ;

equalityTest ( g:contains((1,1), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains((1,2), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains((2,3), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains((3,4), g1), true, Boolean, core_tests ) ;
equalityTest ( g:contains((5,6), g1), true, Boolean, core_tests ) ;

equalityTest ( g:contains((6,1), g1), false, Boolean, core_tests ) ;
equalityTest ( g:contains((1,6), g1), false, Boolean, core_tests ) ;
equalityTest ( g:contains((3,2), g1), false, Boolean, core_tests ) ;

equalityTest ( g:contains((99,87), g1), false, Boolean, core_tests ) ;

-- set equality on edges from...
equalityTest ( g:edgesFrom(1, g1) == iset([1,2]), true, Boolean, core_tests ) ;
equalityTest ( g:edgesFrom(1, g2) == iset([1,2]), true, Boolean, core_tests ) ;
equalityTest ( g:edgesFrom(6, g1) == iset([]), true, Boolean, core_tests ) ;
equalityTest ( g:edgesFrom(56, g1) == iset([]), true, Boolean, core_tests ) ;

-- test length as a proxy for correctness...
equalityTest ( length(g:toList(g1)), 5, Integer, core_tests ) ;
equalityTest ( length(g:toList(g2)), 7, Integer, core_tests ) ;

-- Line graph
global g3 :: g:Graph<Integer> =
  g:add([
    (1,2),
    (2,3),
    (3,4),
    (4,5),
    (5,6)], e);

-- Deps on all later vertexes
global g4 :: g:Graph<Integer> =
  g:transitiveClosure(g3);

-- All deps on all
global g5 :: g:Graph<Integer> =
  g:transitiveClosure(
    g:add([(6,1)], g3));

-- Should be same as g5
global g6 :: g:Graph<Integer> =
  g:repairClosure([(6,1)], g4);

global g7 :: g:Graph<Integer> =
  g:repairClosure([(3,1)], g4);

global g8 :: g:Graph<Integer> =
  g:repairClosure([(3,1), (6, 4)], g4);

equalityTest ( length(g:toList(g3)), 5, Integer, core_tests ) ;
equalityTest ( length(g:toList(g4)), (0 + 1 + 2 + 3 + 4 + 5), Integer, core_tests ) ;
equalityTest ( length(g:toList(g5)), (6 * 6), Integer, core_tests ) ;
equalityTest ( length(g:toList(g6)), (6 * 6), Integer, core_tests ) ;
equalityTest ( length(g:toList(g7)), 21, Integer, core_tests ) ;
equalityTest ( length(g:toList(g8)), 27, Integer, core_tests ) ;

