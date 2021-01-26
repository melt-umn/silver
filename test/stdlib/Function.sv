grammar stdlib;

global inc::(Integer ::= Integer) = \ i::Integer -> i + 1;
global incTwice::(Integer ::= Integer) = compose(inc, inc);

equalityTest(incTwice(42), 44, Integer, core_tests);
