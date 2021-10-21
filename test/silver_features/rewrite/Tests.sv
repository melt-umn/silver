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

equalityTest(s:rewriteWith(s1, [1, 2, 3]), just([4, 5, 6]), Maybe<[Integer]>, silver_tests);
equalityTest(s:rewriteWith(s1, [3, 2, 1]), nothing(), Maybe<[Integer]>, silver_tests);

global s2::s:Strategy =
  rule on [a] of
  | a :: [_, b, c] -> b :: [c, a]
  end;

equalityTest(s:rewriteWith(s2, [1, 2, 3, 4]), just([3, 4, 1]), Maybe<[Integer]>, silver_tests);
equalityTest(s:rewriteWith(s2, [true, false, true, false]), just([true, false, true]), Maybe<[Boolean]>, silver_tests);
equalityTest(s:rewriteWith(s2, ["a", "b", "c", "d"]), just(["c", "d", "a"]), Maybe<[String]>, silver_tests);

global s3::s:Strategy =
  rule on Pair<Integer Integer> of
  | (a, b) when a != b -> (b, a)
  | (a, b) when a == b -> (a, 17)
  end;

equalityTest(s:rewriteWith(s3, (1, 2)), just((2, 1)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s3, (42, 42)), just((42, 17)), Maybe<Pair<Integer Integer>>, silver_tests);

global s4::s:Strategy =
  rule on Pair<Integer Integer> of
  | (a, b) when a == b -> (a, 17)
  | (a, b) when a != b -> (b, a)
  end;

equalityTest(s:rewriteWith(s4, (1, 2)), just((2, 1)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s4, (42, 42)), just((42, 17)), Maybe<Pair<Integer Integer>>, silver_tests);

global isChars::(Boolean ::= [String]) =
  \ cs::[String] -> all(map(contains(_, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), cs)); -- TODO: can't use type class functions inside a rule
global s5::s:Strategy =
  rule on Pair<Integer String> of
  | (n, s) when isChars(explode("", s)) ->
    (toInteger(s), toString(n))
  | a -> a
  end;

equalityTest(s:rewriteWith(s5, (123, "4")), just((4, "123")), Maybe<Pair<Integer String>>, silver_tests);
equalityTest(s:rewriteWith(s5, (467, "foo")), just((467, "foo")), Maybe<Pair<Integer String>>, silver_tests);

global s6::s:Strategy =
  rule on Pair<Integer String> of
  | p -> case p of
    | (n, s) when all(map(contains(_, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) ->
      (toInteger(s), toString(n))
    | a -> a
    end
  end;

equalityTest(s:rewriteWith(s6, (123, "4")), just((4, "123")), Maybe<Pair<Integer String>>, silver_tests);
equalityTest(s:rewriteWith(s6, (467, "foo")), just((467, "foo")), Maybe<Pair<Integer String>>, silver_tests);

global s7::s:Strategy =
  rule on Pair<Integer Integer> of
  | p -> (p.snd, p.fst)
  end;

equalityTest(s:rewriteWith(s7, (123, 456)), just((456, 123)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s7, (123, "hello")), nothing(), Maybe<Pair<Integer String>>, silver_tests);

nonterminal Foo with isEqual, compareTo;
abstract production foo
top::Foo ::= n::Integer
{}

propagate compareTo, isEqual on Foo;

global s8::s:Strategy =
  rule on [Foo] of
  | f1 :: f2 :: rest when decorate f1 with {compareTo = decorate f2 with {};}.isEqual -> f1 :: rest 
  end;

equalityTest(s:rewriteWith(s8, [foo(1), foo(2), foo(3)]), nothing(), Maybe<[Foo]>, silver_tests);
equalityTest(s:rewriteWith(s8, [foo(2), foo(2), foo(3)]), just([foo(2), foo(3)]), Maybe<[Foo]>, silver_tests);

global s9::s:Strategy =
  rule on Pair<Integer Integer> of
  | (a, b) -> pair(fst=b, snd=_)(a)
  end;

equalityTest(s:rewriteWith(s9, (123, 456)), just((456, 123)), Maybe<Pair<Integer Integer>>, silver_tests);

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

equalityTest(s:rewriteWith(s10, barI(1, a1=2, a2=3)), just(barI(3, a1=1, a2=2)), Maybe<Bar>, silver_tests);

global s11::s:Strategy =
  rule on Pair<Integer Pair<Integer Integer>> of
  | (a, b) when b.fst < 10 -> (b.fst, (b.snd, a))
  end;

equalityTest(s:rewriteWith(s11, (1, (2, 3))), just((2, (3, 1))), Maybe<Pair<Integer Pair<Integer Integer>>>, silver_tests);

global s12::s:Strategy =
  rule on Maybe<Decorated Pair<Integer Integer>> of
  | just(p) -> just(decorate (p.snd, p.fst) with {})
  end;

-- Result contains a decorated node, so tricky to test exactly.
-- Mostly just concerned that this one compiles properly.
equalityTest(s:rewriteWith(s12, just(decorate (123, 456) with {})).isJust, true, Boolean, silver_tests);

global s13::s:Strategy =
  rule on Pair<a a> of
  | (a, b) -> (b, a)
  end;

equalityTest(s:rewriteWith(s13, (123, 456)), just((456, 123)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s13, (123, "hello")), nothing(), Maybe<Pair<Integer String>>, silver_tests);

global s14::s:Strategy =
  rule on [[Integer]] of
  | [n] :: rest -> rest ++ [[n + 1]]
  end;

equalityTest(s:rewriteWith(s14, [[2], [1, 2]]), just([[1, 2], [3]]), Maybe<[[Integer]]>, silver_tests);
equalityTest(s:rewriteWith(s13, [[]]), nothing(), Maybe<[[Integer]]>, silver_tests);
equalityTest(s:rewriteWith(s13, [["a"]]), nothing(), Maybe<[[String]]>, silver_tests);

global inc::s:Strategy = rule on Integer of i -> i + 1 end;

global s15::s:Strategy = traverse pair(fst=_, snd=inc);

equalityTest(s:rewriteWith(s15, (1, 2)), just((1, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s15, ("a", "b")), nothing(), Maybe<Pair<String String>>, silver_tests);
equalityTest(s:rewriteWith(s15, [["a"]]), nothing(), Maybe<[[String]]>, silver_tests);

global s16::s:Strategy = traverse barI(inc, a1=inc, a1=_, a1=inc);

equalityTest(s:rewriteWith(s16, barI(1, a1=2, a2=3)), just(barI(2, a1=4, a2=3)), Maybe<Bar>, silver_tests);

global s17::s:Strategy = s:rec(\ s::s:Strategy -> traverse pair(fst=s, snd=s) <+ s:try(inc));

equalityTest(s:rewriteWith(s17, (1, 2)), just((2, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(
  s:rewriteWith(s17, ((("a", 1), ("b", 2)), (true, 3))),
              just(((("a", 2), ("b", 3)), (true, 4))),
  Maybe<Pair<Pair<Pair<String Integer> Pair<String Integer>> Pair<Boolean Integer>>>, silver_tests);

global s18::s:Strategy = s:rec(\ s::s:Strategy -> traverse (s :: s) <+ traverse [] <+ s:try(inc));
equalityTest(s:rewriteWith(s18, [[1], [], [2, 3]]), just([[2], [], [3, 4]]), Maybe<[[Integer]]>, silver_tests);
equalityTest(s:rewriteWith(s18, [[just(1)]]), just([[just(1)]]), Maybe<[[Maybe<Integer>]]>, silver_tests);

global s19::s:Strategy = s:rec(\ s::s:Strategy -> traverse (_ :: s) <+ rule on [Integer] of [] -> [42] end);
equalityTest(s:rewriteWith(s19, [1, 2, 3]), just([1, 2, 3, 42]), Maybe<[Integer]>, silver_tests);

global s20::s:Strategy = s:rec(\ s::s:Strategy -> traverse [_, s, _] <+ s:try(inc));
equalityTest(s:rewriteWith(s20, [1, 2, 3]), just([1, 3, 3]), Maybe<[Integer]>, silver_tests);
equalityTest(s:rewriteWith(s20, [1, 2, 3, 4]), just([1, 2, 3, 4]), Maybe<[Integer]>, silver_tests);
equalityTest(s:rewriteWith(s20, [[1, 2, 3], [4, 5, 6], [7, 8, 9]]), just([[1, 2, 3], [4, 6, 6], [7, 8, 9]]), Maybe<[[Integer]]>, silver_tests);

global s21::s:Strategy = s:all(inc);
equalityTest(s:rewriteWith(s21, (1, 2)), just((2, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s21, (true, 2)), nothing(), Maybe<Pair<Boolean Integer>>, silver_tests);
equalityTest(s:rewriteWith(s21, (true, false)), nothing(), Maybe<Pair<Boolean Boolean>>, silver_tests);
equalityTest(s:rewriteWith(s21, [1, 2, 3]), nothing(), Maybe<[Integer]>, silver_tests);

global s22::s:Strategy = s:some(inc);
equalityTest(s:rewriteWith(s22, (1, 2)), just((2, 3)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s22, (true, 2)), just((true, 3)), Maybe<Pair<Boolean Integer>>, silver_tests);
equalityTest(s:rewriteWith(s22, (true, false)), nothing(), Maybe<Pair<Boolean Boolean>>, silver_tests);
equalityTest(s:rewriteWith(s22, [1, 2, 3]), just([2, 2, 3]), Maybe<[Integer]>, silver_tests);

global s23::s:Strategy = s:one(inc);
equalityTest(s:rewriteWith(s23, (1, 2)), just((2, 2)), Maybe<Pair<Integer Integer>>, silver_tests);
equalityTest(s:rewriteWith(s23, (true, 2)), just((true, 3)), Maybe<Pair<Boolean Integer>>, silver_tests);
equalityTest(s:rewriteWith(s23, (true, false)), nothing(), Maybe<Pair<Boolean Boolean>>, silver_tests);
equalityTest(s:rewriteWith(s23, [1, 2, 3]), just([2, 2, 3]), Maybe<[Integer]>, silver_tests);

