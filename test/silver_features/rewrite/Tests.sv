grammar silver_features:rewrite;

imports silver:testing;
imports lib:extcore;
imports silver_features;

imports silver:rewrite as s;
imports silver:reflect;
imports silver:langutil;
imports silver:langutil:pp;

function showRes
String ::= res::Maybe<a>
{
  return
    case res of
    | just(a) -> show(80, reflect(a).pp)
    | nothing() -> "fail"
    end;
}

global s1::s:Strategy =
  rule on [Integer] of
  | [1, 2, 3] -> [4, 5, 6]
  end;

equalityTest(showRes(rewriteWith(s1, [1, 2, 3])), "[4, 5, 6]", String, silver_tests);
equalityTest(showRes(rewriteWith(s1, [3, 2, 1])), "fail", String, silver_tests);

global s2::s:Strategy =
  rule on [a] of
  | a :: [_, b, c] -> b :: [c, a]
  end;

equalityTest(showRes(rewriteWith(s2, [1, 2, 3, 4])), "[3, 4, 1]", String, silver_tests);
equalityTest(showRes(rewriteWith(s2, [true, false, true, false])), "[true, false, true]", String, silver_tests);
equalityTest(showRes(rewriteWith(s2, ["a", "b", "c", "d"])), "[\"c\", \"d\", \"a\"]", String, silver_tests);

global s3::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) when a != b -> pair(b, a)
  | pair(a, b) when a == b -> pair(a, 17)
  end;

equalityTest(showRes(rewriteWith(s3, pair(1, 2))), "core:pair(2, 1)", String, silver_tests);
equalityTest(showRes(rewriteWith(s3, pair(42, 42))), "core:pair(42, 17)", String, silver_tests);

global s4::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) when a == b -> pair(a, 17)
  | pair(a, b) when a != b -> pair(b, a)
  end;

equalityTest(showRes(rewriteWith(s4, pair(1, 2))), "core:pair(2, 1)", String, silver_tests);
equalityTest(showRes(rewriteWith(s4, pair(42, 42))), "core:pair(42, 17)", String, silver_tests);

global s5::s:Strategy =
  rule on Pair<Integer String> of
  | pair(n, s) when all(map(containsBy(stringEq, _, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) ->
    pair(toInteger(s), toString(n))
  | a -> a
  end;

equalityTest(showRes(rewriteWith(s5, pair(123, "4"))), "core:pair(4, \"123\")", String, silver_tests);
equalityTest(showRes(rewriteWith(s5, pair(467, "foo"))), "core:pair(467, \"foo\")", String, silver_tests);

global s6::s:Strategy =
  rule on Pair<Integer String> of
  | p -> case p of
    | pair(n, s) when all(map(containsBy(stringEq, _, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) ->
      pair(toInteger(s), toString(n))
    | a -> a
    end
  end;

equalityTest(showRes(rewriteWith(s6, pair(123, "4"))), "core:pair(4, \"123\")", String, silver_tests);
equalityTest(showRes(rewriteWith(s6, pair(467, "foo"))), "core:pair(467, \"foo\")", String, silver_tests);

global s7::s:Strategy =
  rule on Pair<Integer Integer> of
  | p -> pair(p.snd, p.fst)
  end;

equalityTest(showRes(rewriteWith(s7, pair(123, 456))), "core:pair(456, 123)", String, silver_tests);
equalityTest(showRes(rewriteWith(s7, pair(123, "hello"))), "fail", String, silver_tests);


synthesized attribute isEqual::Boolean;
inherited attribute isEqualTo::Foo;

nonterminal Foo with isEqual, isEqualTo;

abstract production foo
top::Foo ::= n::Integer
{
  top.isEqual = case top.isEqualTo of foo(n1) -> n1 == n end;
}

global s8::s:Strategy =
  rule on [Foo] of
  | f1 :: f2 :: rest when decorate f1 with {isEqualTo = f2;}.isEqual -> f1 :: rest 
  end;

equalityTest(showRes(rewriteWith(s8, [foo(1), foo(2), foo(3)])), "fail", String, silver_tests);
equalityTest(showRes(rewriteWith(s8, [foo(2), foo(2), foo(3)])), "[silver_features:rewrite:foo(2), silver_features:rewrite:foo(3)]", String, silver_tests);

global s9::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) -> pair(b, _)(a)
  end;

equalityTest(showRes(rewriteWith(s9, pair(123, 456))), "core:pair(456, 123)", String, silver_tests);

annotation a1::Integer;
annotation a2::Integer;

nonterminal Bar with a1, a2;

abstract production barI
top::Bar ::= Integer
{}

global s10::s:Strategy =
  rule on Bar of
  | barI(x, a1=y, a2=z) -> barI(_, a1=x, a2=_)(z, y)
  end;

equalityTest(showRes(rewriteWith(s10, barI(1, a1=2, a2=3))), "silver_features:rewrite:barI(3, silver_features:rewrite:a1=1, silver_features:rewrite:a2=2)", String, silver_tests);

global s11::s:Strategy =
  rule on Pair<Integer Pair<Integer Integer>> of
  | pair(a, b) when b.fst < 10 -> pair(b.fst, pair(b.snd, a))
  end;

equalityTest(showRes(rewriteWith(s11, pair(1, pair(2, 3)))), "core:pair(2, core:pair(3, 1))", String, silver_tests);

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

equalityTest(showRes(rewriteWith(s13, pair(123, 456))), "core:pair(456, 123)", String, silver_tests);
equalityTest(showRes(rewriteWith(s13, pair(123, "hello"))), "fail", String, silver_tests);

global s14::s:Strategy =
  rule on [[Integer]] of
  | [n] :: rest -> rest ++ [[n + 1]]
  end;

equalityTest(showRes(rewriteWith(s14, [[2], [1, 2]])), "[[1, 2], [3]]", String, silver_tests);
equalityTest(showRes(rewriteWith(s13, [[]])), "fail", String, silver_tests);
equalityTest(showRes(rewriteWith(s13, [["a"]])), "fail", String, silver_tests);
