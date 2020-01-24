grammar stdlib:rewrite;

imports silver:testing;
imports lib:extcore;
imports stdlib;

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

equalityTest(showRes(rewriteWith(s1, [1, 2, 3])), "[4, 5, 6]", String, core_tests);
equalityTest(showRes(rewriteWith(s1, [3, 2, 1])), "fail", String, core_tests);

global s2::s:Strategy =
  rule on [a] of
  | a :: [_, b, c] -> b :: [c, a]
  end;

equalityTest(showRes(rewriteWith(s2, [1, 2, 3, 4])), "[3, 4, 1]", String, core_tests);
equalityTest(showRes(rewriteWith(s2, [true, false, true, false])), "[true, false, true]", String, core_tests);
equalityTest(showRes(rewriteWith(s2, ["a", "b", "c", "d"])), "[\"c\", \"d\", \"a\"]", String, core_tests);

global s3::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) when a != b -> pair(b, a)
  | pair(a, b) when a == b -> pair(a, 17)
  end;

equalityTest(showRes(rewriteWith(s3, pair(1, 2))), "core:pair(2, 1)", String, core_tests);
equalityTest(showRes(rewriteWith(s3, pair(42, 42))), "core:pair(42, 17)", String, core_tests);

global s4::s:Strategy =
  rule on Pair<Integer Integer> of
  | pair(a, b) when a == b -> pair(a, 17)
  | pair(a, b) when a != b -> pair(b, a)
  end;

equalityTest(showRes(rewriteWith(s4, pair(1, 2))), "core:pair(2, 1)", String, core_tests);
equalityTest(showRes(rewriteWith(s4, pair(42, 42))), "core:pair(42, 17)", String, core_tests);

global s5::s:Strategy =
  rule on Pair<Integer String> of
  | pair(n, s) when all(map(containsBy(stringEq, _, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) ->
    pair(toInteger(s), toString(n))
  | a -> a
  end;

equalityTest(showRes(rewriteWith(s5, pair(123, "4"))), "core:pair(4, \"123\")", String, core_tests);
equalityTest(showRes(rewriteWith(s5, pair(467, "foo"))), "core:pair(467, \"foo\")", String, core_tests);

global s6::s:Strategy =
  rule on Pair<Integer String> of
  | p -> case p of
    | pair(n, s) when all(map(containsBy(stringEq, _, ["1", "2", "3", "4", "5", "6", "7", "8", "9"]), explode("", s))) ->
      pair(toInteger(s), toString(n))
    | a -> a
    end
  end;

equalityTest(showRes(rewriteWith(s6, pair(123, "4"))), "core:pair(4, \"123\")", String, core_tests);
equalityTest(showRes(rewriteWith(s6, pair(467, "foo"))), "core:pair(467, \"foo\")", String, core_tests);

global s7::s:Strategy =
  rule on Pair<Integer Integer> of
  | p -> pair(p.snd, p.fst)
  end;

equalityTest(showRes(rewriteWith(s7, pair(123, 456))), "core:pair(456, 123)", String, core_tests);

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

equalityTest(showRes(rewriteWith(s8, [foo(1), foo(2), foo(3)])), "fail", String, core_tests);
equalityTest(showRes(rewriteWith(s8, [foo(2), foo(2), foo(3)])), "[stdlib:rewrite:foo(2), stdlib:rewrite:foo(3)]", String, core_tests);

