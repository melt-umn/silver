

--fromMaybe
equalityTest ( fromMaybe(true, just(false)), false,
               Boolean, core_tests ) ;
equalityTest ( fromMaybe(true, nothing()), true,
               Boolean, core_tests ) ;

--orElse
equalityTest ( orElse(just(false), just(true)).fromJust, false,
               Boolean, core_tests ) ;
equalityTest ( orElse(nothing(), just(true)).fromJust, true,
               Boolean, core_tests ) ;
equalityTest ( orElse(just(false), nothing()).fromJust, false,
               Boolean, core_tests ) ;
equalityTest ( orElse(nothing(), nothing()).isJust, false,
               Boolean, core_tests ) ;

-- MaybeT
global mts::MaybeT<State<Integer _> String> =
  do {
    x::Integer <- lift(getState());
    lift(setState(x + 2));
    if x < 0 then fail("negative") else pure(unit());
    when_(x % 2 != 0, lift(setState(x + 1)));
    y::Integer <- lift(getState());
    return toString(y / (x + 1));
  };

equalityTest(runState(mts.run, -4), pair(-2, nothing()), Pair<Integer Maybe<String>>, core_tests);
equalityTest(runState(mts.run, -1), pair(1, nothing()), Pair<Integer Maybe<String>>, core_tests);
equalityTest(runState(mts.run, 0), pair(2, just("2")), Pair<Integer Maybe<String>>, core_tests);
equalityTest(runState(mts.run, 1), pair(2, just("1")), Pair<Integer Maybe<String>>, core_tests);
equalityTest(runState(mts.run, 2), pair(4, just("1")), Pair<Integer Maybe<String>>, core_tests);
equalityTest(runState(mts.run, 3), pair(4, just("1")), Pair<Integer Maybe<String>>, core_tests);
equalityTest(runState(mts.run, 5), pair(6, just("1")), Pair<Integer Maybe<String>>, core_tests);
