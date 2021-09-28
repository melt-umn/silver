grammar silver_features;

nonterminal EqExpr with compareTo, isEqual;

abstract production addEqExpr
top::EqExpr ::= e1::EqExpr e2::EqExpr
{
  propagate compareTo, isEqual;
}

abstract production intEqExpr
top::EqExpr ::= i::Integer
{
  propagate compareTo, isEqual;
}

abstract production appEqExpr
top::EqExpr ::= n::String e::EqExpr
{
  propagate compareTo, isEqual;
}

{- TODO: This gives an error in generated code
abstract production polyEqExpr
Eq a => top::EqExpr ::= x::a
{
  propagate compareTo, isEqual;
}
-}

global ee1::EqExpr = addEqExpr(intEqExpr(42), appEqExpr("abc", intEqExpr(5)));
global ee2::EqExpr = addEqExpr(intEqExpr(42), appEqExpr("c", intEqExpr(5)));
global ee3::EqExpr = addEqExpr(appEqExpr("c", intEqExpr(5)), intEqExpr(42));

equalityTest(decorate ee1 with {compareTo = ee1;}.isEqual, true, Boolean, silver_tests);
equalityTest(decorate ee1 with {compareTo = ee2;}.isEqual, false, Boolean, silver_tests);
equalityTest(decorate ee1 with {compareTo = ee3;}.isEqual, false, Boolean, silver_tests);
equalityTest(decorate ee2 with {compareTo = ee1;}.isEqual, false, Boolean, silver_tests);
equalityTest(decorate ee2 with {compareTo = ee2;}.isEqual, true, Boolean, silver_tests);
equalityTest(decorate ee2 with {compareTo = ee3;}.isEqual, false, Boolean, silver_tests);
equalityTest(decorate ee3 with {compareTo = ee1;}.isEqual, false, Boolean, silver_tests);
equalityTest(decorate ee3 with {compareTo = ee2;}.isEqual, false, Boolean, silver_tests);
equalityTest(decorate ee3 with {compareTo = ee3;}.isEqual, true, Boolean, silver_tests);
