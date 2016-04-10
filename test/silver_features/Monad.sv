import core:monad;

global monadRes1::[[Integer]] =  do (bindList, returnList) {
  a::Integer <- [1, 2, 3];
  b::Integer <- [4, 5, 6];
  return a * b;
};

global res::Integer = foldr(\a::Integer b::Integer -> a + b, 0, monadRes1);

equalityTest(res, 90, Integer, silver_tests);