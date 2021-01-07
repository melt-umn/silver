import silver:testing;

-- Testing that a tuple of two elements behaves like a pair
equalityTest((1,2).fst, 1, Integer, silver_tests);
equalityTest((3, "b").snd, "b", String, silver_tests);

-- Testing that a tuple of > two elements behaves as nested pairs
equalityTest((1,2,3).snd.fst, 2, Integer, silver_tests);
equalityTest((1,"a",2,"b").snd.snd.snd, "b", String, silver_tests);

--function tupleMatch
--Boolean ::= tuple::<Pair String <Pair Integer Integer>>
--{
--    return case tuple of
--        (_, _, 2) -> true
--        | _ -> false
--        end; 
--}

--equalityTest(tupleMatch(1,1,2), true, Boolean, silver_tests);