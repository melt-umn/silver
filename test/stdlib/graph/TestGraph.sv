
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


-- The order of the vertices, whatever order they were first used in.
equalityTest (
  map(fst, g:adjacency(g:add([(9,1), (0,1), (4,1)], e))) == [0, 4, 9], true, Boolean, core_tests ) ;
equalityTest ( g:toList(g3) == [(5,6), (4,5), (3,4), (2,3), (1,2)], true, Boolean, core_tests ) ;

-- Closures of pseudo-random graphs, against the vertices reachable from each vertex.

-- The number after s from a linear congruential generator.
fun nextRandom Integer ::= s::Integer = (s * 75 + 74) % 65537;

-- count pseudo-random edges between the vertices 0 to n - 1, from seed.
function randomEdges
[(Integer, Integer)] ::= seed::Integer  count::Integer  n::Integer
{
  local s1::Integer = nextRandom(seed);
  local s2::Integer = nextRandom(s1);
  return if count <= 0 then [] else (s1 % n, s2 % n) :: randomEdges(s2, count - 1, n);
}

-- seen, and the vertices reachable by edges in es from the vertices in todo.
function reachableFrom
[Integer] ::= es::[(Integer, Integer)]  todo::[Integer]  seen::[Integer]
{
  local next::[Integer] =
    case todo of
    | v :: _ ->
      nub(filter(
        \ w::Integer -> !contains(w, seen),
        map(snd, filter(\ ed::(Integer, Integer) -> ed.1 == v, es))))
    | [] -> []
    end;
  return
    case todo of
    | _ :: rest -> reachableFrom(es, rest ++ next, seen ++ next)
    | [] -> seen
    end;
}

-- Whether graph is the transitive closure of es.
function isClosureOf
Boolean ::= graph::g:Graph<Integer>  es::[(Integer, Integer)]
{
  local closure::[(Integer, Integer)] =
    flatMap(
      \ v::Integer -> map(\ w::Integer -> (v, w), reachableFrom(es, [v], [])),
      nub(map(fst, es) ++ map(snd, es)));
  return length(g:toList(graph)) == length(closure) && all(map(g:contains(_, graph), closure));
}

-- For one seed: a closure, two repairs of it, each adding vertices that the other does not have
-- (graphs derived from the same graph share their vertices' indices), and a closure of more edges
-- added to the first repair.  The closure and the first repair are checked again after the other
-- graphs are built.
function closuresOk
Boolean ::= seed::Integer
{
  local es1::[(Integer, Integer)] = randomEdges(seed, 15, 10);
  local es2::[(Integer, Integer)] = randomEdges(seed + 1000, 8, 12);
  local es3::[(Integer, Integer)] =
    map(\ ed::(Integer, Integer) -> (ed.1 + 10, ed.2), randomEdges(seed + 2000, 6, 10));
  local base::g:Graph<Integer> = g:transitiveClosure(g:add(es1, g:empty()));
  local repaired1::g:Graph<Integer> = g:repairClosure(es2, base);
  local repaired2::g:Graph<Integer> = g:repairClosure(es3, base);
  return
    isClosureOf(base, es1) &&
    isClosureOf(repaired1, es1 ++ es2) &&
    isClosureOf(repaired2, es1 ++ es3) &&
    isClosureOf(g:transitiveClosure(g:add(es3, repaired1)), es1 ++ es2 ++ es3) &&
    isClosureOf(base, es1) &&
    isClosureOf(repaired1, es1 ++ es2);
}

equalityTest ( all(map(closuresOk, range(1, 41))), true, Boolean, core_tests ) ;
