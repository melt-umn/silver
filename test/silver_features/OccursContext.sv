grammar silver_features;

inherited attribute extraInh1::String occurs on EqExpr;
inherited attribute extraInh2::Integer occurs on EqExpr;

function eqA
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::a y::a
{
  x.isEqualTo = y;
  return x.isEqual;
}

equalityTest(eqA(ee1, ee1), true, Boolean, silver_tests);
equalityTest(eqA(ee1, ee2), false, Boolean, silver_tests);
equalityTest(eqA(ee1, ee3), false, Boolean, silver_tests);
equalityTest(eqA(ee2, ee1), false, Boolean, silver_tests);
equalityTest(eqA(ee2, ee2), true, Boolean, silver_tests);
equalityTest(eqA(ee2, ee3), false, Boolean, silver_tests);
equalityTest(eqA(ee3, ee1), false, Boolean, silver_tests);
equalityTest(eqA(ee3, ee2), false, Boolean, silver_tests);
equalityTest(eqA(ee3, ee3), true, Boolean, silver_tests);

function eqB
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::a y::a
{
  production z::a = x;
  production w::a = z;
  w.isEqualTo = y;
  return w.isEqual;
}

equalityTest(eqB(ee1, ee1), true, Boolean, silver_tests);
equalityTest(eqB(ee1, ee2), false, Boolean, silver_tests);
equalityTest(eqB(ee1, ee3), false, Boolean, silver_tests);
equalityTest(eqB(ee2, ee1), false, Boolean, silver_tests);
equalityTest(eqB(ee2, ee2), true, Boolean, silver_tests);
equalityTest(eqB(ee2, ee3), false, Boolean, silver_tests);
equalityTest(eqB(ee3, ee1), false, Boolean, silver_tests);
equalityTest(eqB(ee3, ee2), false, Boolean, silver_tests);
equalityTest(eqB(ee3, ee3), true, Boolean, silver_tests);

function eqC
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a =>
Boolean ::= x::(a ::= ) y::(a ::= )
{
  production z::a = x();
  production w::a = y();
  w.isEqualTo = z;
  return w.isEqual;
}

equalityTest(eqC(\ -> ee1, \ -> ee1), true, Boolean, silver_tests);
equalityTest(eqC(\ -> ee1, \ -> ee2), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee1, \ -> ee3), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee2, \ -> ee1), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee2, \ -> ee2), true, Boolean, silver_tests);
equalityTest(eqC(\ -> ee2, \ -> ee3), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee3, \ -> ee1), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee3, \ -> ee2), false, Boolean, silver_tests);
equalityTest(eqC(\ -> ee3, \ -> ee3), true, Boolean, silver_tests);

class OCEq a {
  ocEq :: (Boolean ::= a a);
}

instance OCEq Integer
{
  ocEq = \ x::Integer y::Integer -> x == y;
}

instance OCEq String
{
  ocEq = \ x::String y::String -> x == y;
}

instance attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a => OCEq a {
  ocEq = \ x::a y::a -> decorate x with {isEqualTo = y;}.isEqual; 
}

equalityTest(ocEq(ee1, ee1), true, Boolean, silver_tests);
equalityTest(ocEq(ee1, ee2), false, Boolean, silver_tests);
equalityTest(ocEq(ee1, ee3), false, Boolean, silver_tests);
equalityTest(ocEq(ee2, ee1), false, Boolean, silver_tests);
equalityTest(ocEq(ee2, ee2), true, Boolean, silver_tests);
equalityTest(ocEq(ee2, ee3), false, Boolean, silver_tests);
equalityTest(ocEq(ee3, ee1), false, Boolean, silver_tests);
equalityTest(ocEq(ee3, ee2), false, Boolean, silver_tests);
equalityTest(ocEq(ee3, ee3), true, Boolean, silver_tests);

nonterminal OCEqPair<a b> with isEqualTo, isEqual;
production ocEqPair
attribute isEqualTo<a> occurs on a, attribute isEqual {isEqualTo} occurs on a,
attribute isEqualTo<b> occurs on b, attribute isEqual {isEqualTo} occurs on b =>
top::OCEqPair<a b> ::= x::a y::b
{
  propagate isEqualTo, isEqual;
  {-
  x.isEqualTo = case top.isEqualTo of ocEqPair(a, _) -> a end;
  y.isEqualTo = case top.isEqualTo of ocEqPair(_, a) -> a end;
  top.isEqual = x.isEqual && y.isEqual;
  -}
}

equalityTest(ocEq(ocEqPair(ee1, ee2), ocEqPair(ee1, ee2)), true, Boolean, silver_tests);
equalityTest(ocEq(ocEqPair(ee1, ee2), ocEqPair(ee1, ee3)), false, Boolean, silver_tests);

wrongCode "Could not find an instance for attribute silver:core:isEqual {silver:core:isEqualTo} occurs on String (arising from the use of ocEqPair)" {
  global err::OCEqPair<EqExpr String> = ocEqPair(ee1, "abc");
}
wrongCode "Could not find an instance for attribute silver:core:isEqualTo<b> occurs on String (arising from the use of ocEqPair)" {
  global err::OCEqPair<EqExpr String> = ocEqPair(ee1, "abc");
}
