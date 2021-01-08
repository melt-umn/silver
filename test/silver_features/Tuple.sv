import silver:testing;

-- Testing that a tuple of two elements behaves like a pair
equalityTest((1,2).fst, 1, Integer, silver_tests);
equalityTest((3, "b").snd, "b", String, silver_tests);

-- Testing that a tuple of > two elements behaves as nested pairs
equalityTest((1,2,3).snd.fst, 2, Integer, silver_tests);
equalityTest((1,"a",2,"b").snd.snd.snd, "b", String, silver_tests);

-- Pattern matching tests

function tupleMatch1
Boolean ::= tuple::Pair <String Pair <Integer Integer>>
{
    return case tuple of
        | (_, _, 2) -> true
        | _ -> false
        end; 
}

equalityTest(tupleMatch1(("a",1,2)), true, Boolean, silver_tests);
equalityTest(tupleMatch1(("b", 3, 4)), false, Boolean, silver_tests);

function tupleMatch3rd
Integer ::= tuple::Pair <String Pair <Integer Integer>>
{
    return case tuple of
        | (_, _, x) -> x
        end; 
}

equalityTest(tupleMatch3rd(("a",1,2)), 2, Integer, silver_tests);
equalityTest(tupleMatch3rd(("a",1,49)), 49, Integer, silver_tests);

function tupleMatch2nd
String ::= tuple::Pair <String Pair <String Integer>>
{
    return case tuple of
        | (_, x, _) -> x
        end; 
}

equalityTest(tupleMatch2nd(("a","hello",2)), "hello", String, silver_tests);
equalityTest(tupleMatch2nd(("a","I like dogs",49)), "I like dogs", String, silver_tests);

function tupleMatch4
Integer ::= tuple::Pair <Integer Pair <String Pair <String Integer>>>
{
    return case tuple of
        | (fst, _, _, 4) -> fst
        | (8, _, _, frth) -> frth
        end; 
}

equalityTest(tupleMatch4((1, "test", "more test", 4)), 1, Integer, silver_tests);
equalityTest(tupleMatch4((8, "test", "more test", 4)), 8, Integer, silver_tests);
equalityTest(tupleMatch4((8, "test", "more test", 3)), 3, Integer, silver_tests);