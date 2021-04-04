grammar silver_features;

function eqA
attribute isEqualTo occurs on a, attribute isEqual {isEqualTo} occurs on a =>
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