
-- Test Maybe
global monadRes1::Maybe<Integer> = do {
  a::Integer <- just(1);
  if a > 0
    then pure(2)
    else nothing();
};

equalityTest(monadRes1.isJust, true, Boolean, silver_tests);

-- Test list
global monadRes2::[Integer] = do {
  a::Integer <- [1, 2, 3];
  b::Integer <- [4, 5, 6];
  return a * b;
};

equalityTest(foldr(\a::Integer b::Integer -> a + b, 0, monadRes2), 90, Integer, silver_tests);

-- Test State
global monadRes3::State<Integer Integer> = do {
  a::Integer <- getState();
  setState(a + 1);
  return a * 2;
};

global res3::Pair<Integer Integer> = runState(monadRes3, 2);

equalityTest(res3.fst, 3, Integer, silver_tests);
equalityTest(res3.snd, 4, Integer, silver_tests);

-- Test IO
global monadRes4::IOMonad<String> = do {
  writeFileM("test_out.txt", "Hello");
  appendFileM("test_out.txt", ", World!");
  readFileM("test_out.txt");
};

equalityTest(evalIO(monadRes4, unsafeIO()).iovalue, "Hello, World!", String, silver_tests);