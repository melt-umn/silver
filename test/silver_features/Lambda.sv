
function makeInc
(Integer ::= Integer) ::= i::Integer
{
  return lambda x::Integer -> x + i;
}

global addThree::(Integer ::= Integer) = makeInc(3);

equalityTest(addThree(4), 7, Integer, silver_tests);

global sumTwo::(Integer ::= Integer Integer) = lambda x::Integer y::Integer -> x + y;

equalityTest(sumTwo(4, 5), 9, Integer, silver_tests);

global curriedSum::((Integer ::= Integer) ::= Integer) = lambda x::Integer -> lambda y::Integer -> x + y;

equalityTest(curriedSum(1)(2), 3, Integer, silver_tests);

global differentTypes::((String ::= String) ::= Integer) = lambda x::Integer -> lambda x::String -> x;

equalityTest(differentTypes(1)("abcd"), "abcd", String, silver_tests);