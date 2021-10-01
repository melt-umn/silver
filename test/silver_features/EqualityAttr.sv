grammar silver_features;

nonterminal EqExpr with compareTo, isEqual, compareKey, compare;

abstract production addEqExpr
top::EqExpr ::= e1::EqExpr e2::EqExpr
{
  propagate compareTo, isEqual, compareKey, compare;
}

abstract production intEqExpr
top::EqExpr ::= i::Integer
{
  propagate compareTo, isEqual, compareKey, compare;
}

abstract production appEqExpr
top::EqExpr ::= n::String e::EqExpr
{
  propagate compareTo, isEqual, compareKey, compare;
}

{- TODO: This should give an error, but the error is in generated code at the moment so we can't test for it
abstract production polyEqExpr
Eq a => top::EqExpr ::= x::a
{
  propagate compareTo, isEqual, compare;
}
-}

global ee1::EqExpr = addEqExpr(intEqExpr(42), appEqExpr("abc", intEqExpr(5)));
global ee2::EqExpr = addEqExpr(intEqExpr(42), appEqExpr("c", intEqExpr(5)));
global ee3::EqExpr = addEqExpr(appEqExpr("c", intEqExpr(5)), intEqExpr(42));

equalityTest(ee1 == ee1, true, Boolean, silver_tests);
equalityTest(ee1 == ee2, false, Boolean, silver_tests);
equalityTest(ee1 == ee3, false, Boolean, silver_tests);
equalityTest(ee2 == ee1, false, Boolean, silver_tests);
equalityTest(ee2 == ee2, true, Boolean, silver_tests);
equalityTest(ee2 == ee3, false, Boolean, silver_tests);
equalityTest(ee3 == ee1, false, Boolean, silver_tests);
equalityTest(ee3 == ee2, false, Boolean, silver_tests);
equalityTest(ee3 == ee3, true, Boolean, silver_tests);

equalityTest(ee1 < ee1, false, Boolean, silver_tests);
equalityTest(ee1 < ee2, true, Boolean, silver_tests);
equalityTest(ee1 < ee3, false, Boolean, silver_tests);
equalityTest(ee2 < ee1, false, Boolean, silver_tests);
equalityTest(ee2 < ee2, false, Boolean, silver_tests);
equalityTest(ee2 < ee3, false, Boolean, silver_tests);
equalityTest(ee3 < ee1, true, Boolean, silver_tests);
equalityTest(ee3 < ee2, true, Boolean, silver_tests);
equalityTest(ee3 < ee3, false, Boolean, silver_tests);
