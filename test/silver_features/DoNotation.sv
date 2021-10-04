
-- Test Maybe
global doRes1::Maybe<Integer> = do {
  a::Integer <- just(1);
  if a > 0
    then pure(2)
    else nothing();
};

equalityTest(doRes1, just(2), Maybe<Integer>, silver_tests);

-- Test list
global doRes2::[Integer] = do {
  a::Integer <- [1, 2, 3];
  b::Integer <- [4, 5, 6];
  return a * b;
};

equalityTest(foldr(\a::Integer b::Integer -> a + b, 0, doRes2), 90, Integer, silver_tests);

-- Test State
global doRes3::State<Integer Integer> = do {
  a::Integer <- getState();
  setState(a + 1);
  return a * 2;
};

global res3::Pair<Integer Integer> = runState(doRes3, 2);

equalityTest(res3.fst, 3, Integer, silver_tests);
equalityTest(res3.snd, 4, Integer, silver_tests);

-- Test IO
global doRes4::IO<String> = do {
  writeFile("test_out.txt", "Hello");
  appendFile("test_out.txt", ", World!");
  readFile("test_out.txt");
};

equalityTest(evalIO(doRes4, unsafeIOT()).iovalue, "Hello, World!", String, silver_tests);

-- Test something that is Applicative but not Monad
nonterminal AMaybe<a>;
production justAL top::AMaybe<a> ::= a {}
production noneAL top::AMaybe<a> ::= {}
instance Functor AMaybe {
  map = \ f::(b ::= a) m::AMaybe<a> ->
    case m of
    | noneAL() -> noneAL()
    | justAL(x) -> justAL(f(x))
    end;
}
instance Apply AMaybe {
  ap = \ mf::AMaybe<(b ::= a)> m::AMaybe<a> ->
    case mf, m of
    | justAL(f), justAL(x) -> justAL(f(x))
    | _, _ -> noneAL()
    end;
}
instance Applicative AMaybe {
  pure = justAL;
}

global doRes5::Maybe<Integer> = do {
  a::Integer <- just(1);
  b::Integer <- just(2);
  return a + b;
};

equalityTest(doRes5, just(3), Maybe<Integer>, silver_tests);
