grammar silver_features:rewrite;

imports silver:testing;
imports silver_features;

imports silver:rewrite as s;
imports silver:reflect;
imports silver:langutil;
imports silver:langutil:pp;

global s1::s:Strategy =
  rule on [Integer] of
  | [1, 2, 3] -> [4, 5, 6]
  end;

equalityTest(rewriteWith(s1, [1, 2, 3]), just([4, 5, 6]), Maybe<[Integer]>, silver_tests);
equalityTest(rewriteWith(s1, [3, 2, 1]), nothing(), Maybe<[Integer]>, silver_tests);

global s2::s:Strategy =
  rule on [a] of
  | a :: [_, b, c] -> b :: [c, a]
  end;

equalityTest(rewriteWith(s2, [1, 2, 3, 4]), just([3, 4, 1]), Maybe<[Integer]>, silver_tests);
equalityTest(rewriteWith(s2, [true, false, true, false]), just([true, false, true]), Maybe<[Boolean]>, silver_tests);
equalityTest(rewriteWith(s2, ["a", "b", "c", "d"]), just(["c", "d", "a"]), Maybe<[String]>, silver_tests);

global s3::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) when a != b -> pair(b, a)
  | pair(a, b) when a == b -> pair(a, 17)
  end;

equalityTest(rewriteWith(s3, pair(1, 2)), just(pair(2, 1)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s3, pair(42, 42)), just(pair(42, 17)), Maybe<Pair<Integer Integer>>, silver_tests);

global s4::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) when a == b -> pair(a, 17)
  | pair(a, b) when a != b -> pair(b, a)
  end;

equalityTest(rewriteWith(s4, pair(1, 2)), just(pair(2, 1)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s4, pair(42, 42)), just(pair(42, 17)), Maybe<Pair<Integer Integer>>, silver_tests);

global s5::s:Strategy =
  rule on Pair<Integer String> of
  | pair(n, s) when all(map(containsBy(eqString, _, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) -> -- TODO: can't use contains here
    pair(toInteger(s), toString(n))
  | a -> a
  end;

equalityTest(rewriteWith(s5, pair(123, "4")), just(pair(4, "123")), Maybe<Pair<Integer String>>, silver_tests);
equalityTest(rewriteWith(s5, pair(467, "foo")), just(pair(467, "foo")), Maybe<Pair<Integer String>>, silver_tests);

global s6::s:Strategy =
  rule on Pair<Integer String> of
  | p -> case p of
    | pair(n, s) when all(map(contains(_, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) ->
      pair(toInteger(s), toString(n))
    | a -> a
    end
  end;

equalityTest(rewriteWith(s6, pair(123, "4")), just(pair(4, "123")), Maybe<Pair<Integer String>>, silver_tests);
equalityTest(rewriteWith(s6, pair(467, "foo")), just(pair(467, "foo")), Maybe<Pair<Integer String>>, silver_tests);

global s7::s:Strategy =
  rule on Pair<Integer Integer> of
  | p -> pair(p.snd, p.fst)
  end;

equalityTest(rewriteWith(s7, pair(123, 456)), just(pair(456, 123)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s7, pair(123, "hello")), nothing(), Maybe<Pair<Integer String>>, silver_tests);

nonterminal Foo with isEqual, isEqualTo;
abstract production foo
top::Foo ::= n::Integer
{}

propagate isEqualTo, isEqual on Foo;
-- TODO: Remove this instance once default exists
instance Eq Foo {
  eq = \ f1::Foo f2::Foo -> decorate f1 with {isEqualTo = f2;}.isEqual;
}

global s8::s:Strategy =
  rule on [Foo] of
  | f1 :: f2 :: rest when decorate f1 with {isEqualTo = f2;}.isEqual -> f1 :: rest 
  end;

equalityTest(rewriteWith(s8, [foo(1), foo(2), foo(3)]), nothing(), Maybe<[Foo]>, silver_tests);
equalityTest(rewriteWith(s8, [foo(2), foo(2), foo(3)]), just([foo(2), foo(3)]), Maybe<[Foo]>, silver_tests);

global s9::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) -> pair(b, _)(a)
  end;

equalityTest(rewriteWith(s9, pair(123, 456)), just(pair(456, 123)), Maybe<Pair<Integer Integer>>, silver_tests);

annotation a1::Integer;
annotation a2::Integer;

nonterminal Bar with a1, a2;

abstract production barI
top::Bar ::= Integer
{}

instance Eq Bar {
  eq = \ a::Bar b::Bar -> a.a1 == b.a1 && a.a2 == b.a2;
}

global s10::s:Strategy =
  rule on Bar of
  | barI(x, a1=y, a2=z) -> barI(_, a1=x, a2=_)(z, y)
  end;

equalityTest(rewriteWith(s10, barI(1, a1=2, a2=3)), just(barI(3, a1=1, a2=2)), Maybe<Bar>, silver_tests);

global s11::s:Strategy =
  rule on Pair<Integer Pair<Integer Integer>> of
  | pair(a, b) when b.fst < 10 -> pair(b.fst, pair(b.snd, a))
  end;

equalityTest(rewriteWith(s11, pair(1, pair(2, 3))), just(pair(2, pair(3, 1))), Maybe<Pair<Integer Pair<Integer Integer>>>, silver_tests);

global s12::s:Strategy =
  rule on Maybe<Decorated Pair<Integer Integer>> of
  | just(p) -> just(decorate pair(p.snd, p.fst) with {})
  end;

-- Result contains a decorated node, so tricky to test exactly.
-- Mostly just concerned that this one compiles properly.
equalityTest(rewriteWith(s12, just(decorate pair(123, 456) with {})).isJust, true, Boolean, silver_tests);

global s13::s:Strategy =
  rule on Pair<a a> of
  | pair(a, b) -> pair(b, a)
  end;

equalityTest(rewriteWith(s13, pair(123, 456)), just(pair(456, 123)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s13, pair(123, "hello")), nothing(), Maybe<Pair<Integer String>>, silver_tests);

global s14::s:Strategy =
  rule on [[Integer]] of
  | [n] :: rest -> rest ++ [[n + 1]]
  end;

equalityTest(rewriteWith(s14, [[2], [1, 2]]), just([[1, 2], [3]]), Maybe<[[Integer]]>, silver_tests);
equalityTest(rewriteWith(s13, [[]]), nothing(), Maybe<[[Integer]]>, silver_tests);
equalityTest(rewriteWith(s13, [["a"]]), nothing(), Maybe<[[String]]>, silver_tests);

global inc::s:Strategy = rule on Integer of i -> i + 1 end;

global s15::s:Strategy = traverse pair(_, inc);

equalityTest(rewriteWith(s15, pair(1, 2)), just(pair(1, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s15, pair("a", "b")), nothing(), Maybe<Pair<String String>>, silver_tests);
equalityTest(rewriteWith(s15, [["a"]]), nothing(), Maybe<[[String]]>, silver_tests);

global s16::s:Strategy = traverse barI(inc, a1=inc, a1=_, a1=inc);

equalityTest(rewriteWith(s16, barI(1, a1=2, a2=3)), just(barI(2, a1=4, a2=3)), Maybe<Bar>, silver_tests);

global s17::s:Strategy = s:rec(\ s::s:Strategy -> traverse pair(s, s) <+ s:try(inc));

equalityTest(rewriteWith(s17, pair(1, 2)), just(pair(2, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(
  rewriteWith(s17, pair(pair(pair("a", 1), pair("b", 2)), pair(true, 3))),
              just(pair(pair(pair("a", 2), pair("b", 3)), pair(true, 4))),
  Maybe<Pair<Pair<Pair<String Integer> Pair<String Integer>> Pair<Boolean Integer>>>, silver_tests);

global s18::s:Strategy = s:rec(\ s::s:Strategy -> traverse (s :: s) <+ traverse [] <+ s:try(inc));
equalityTest(rewriteWith(s18, [[1], [], [2, 3]]), just([[2], [], [3, 4]]), Maybe<[[Integer]]>, silver_tests);
equalityTest(rewriteWith(s18, [[just(1)]]), just([[just(1)]]), Maybe<[[Maybe<Integer>]]>, silver_tests);

global s19::s:Strategy = s:rec(\ s::s:Strategy -> traverse (_ :: s) <+ rule on [Integer] of [] -> [42] end);
equalityTest(rewriteWith(s19, [1, 2, 3]), just([1, 2, 3, 42]), Maybe<[Integer]>, silver_tests);

global s20::s:Strategy = s:rec(\ s::s:Strategy -> traverse [_, s, _] <+ s:try(inc));
equalityTest(rewriteWith(s20, [1, 2, 3]), just([1, 3, 3]), Maybe<[Integer]>, silver_tests);
equalityTest(rewriteWith(s20, [1, 2, 3, 4]), just([1, 2, 3, 4]), Maybe<[Integer]>, silver_tests);
equalityTest(rewriteWith(s20, [[1, 2, 3], [4, 5, 6], [7, 8, 9]]), just([[1, 2, 3], [4, 6, 6], [7, 8, 9]]), Maybe<[[Integer]]>, silver_tests);

global s21::s:Strategy = s:all(inc);
equalityTest(rewriteWith(s21, pair(1, 2)), just(pair(2, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s21, pair(true, 2)), nothing(), Maybe<Pair<Boolean Integer>>, silver_tests);
equalityTest(rewriteWith(s21, pair(true, false)), nothing(), Maybe<Pair<Boolean Boolean>>, silver_tests);
equalityTest(rewriteWith(s21, [1, 2, 3]), nothing(), Maybe<[Integer]>, silver_tests);

global s22::s:Strategy = s:some(inc);
equalityTest(rewriteWith(s22, pair(1, 2)), just(pair(2, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s22, pair(true, 2)), just(pair(true, 3)), Maybe<Pair<Boolean Integer>>, silver_tests);
equalityTest(rewriteWith(s22, pair(true, false)), nothing(), Maybe<Pair<Boolean Boolean>>, silver_tests);
equalityTest(rewriteWith(s22, [1, 2, 3]), just([2, 2, 3]), Maybe<[Integer]>, silver_tests);

global s23::s:Strategy = s:one(inc);
equalityTest(rewriteWith(s23, pair(1, 2)), just(pair(2, 2)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(rewriteWith(s23, pair(true, 2)), just(pair(true, 3)), Maybe<Pair<Boolean Integer>>, silver_tests);
equalityTest(rewriteWith(s23, pair(true, false)), nothing(), Maybe<Pair<Boolean Boolean>>, silver_tests);
equalityTest(rewriteWith(s23, [1, 2, 3]), just([2, 2, 3]), Maybe<[Integer]>, silver_tests);

