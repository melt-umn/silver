grammar silver_features;

equality attribute isEqualToTest, isEqualTest;

nonterminal EqExpr with isEqualToTest, isEqualTest;

abstract production addEqExpr
top::EqExpr ::= e1::EqExpr e2::EqExpr
{
  propagate isEqualToTest, isEqualTest;
}

abstract production intEqExpr
top::EqExpr ::= i::Integer
{
  propagate isEqualToTest, isEqualTest;
}

abstract production appEqExpr
top::EqExpr ::= n::String e::EqExpr
{
  propagate isEqualToTest, isEqualTest;
}

global ee1::EqExpr = addEqExpr(intEqExpr(42), appEqExpr("abc", intEqExpr(5)));
global ee2::EqExpr = addEqExpr(intEqExpr(42), appEqExpr("c", intEqExpr(5)));
global ee3::EqExpr = addEqExpr(appEqExpr("c", intEqExpr(5)), intEqExpr(42));

equalityTest(decorate ee1 with {isEqualToTest = ee1;}.isEqualTest, true, Boolean, silver_tests);
equalityTest(decorate ee1 with {isEqualToTest = ee2;}.isEqualTest, false, Boolean, silver_tests);
equalityTest(decorate ee1 with {isEqualToTest = ee3;}.isEqualTest, false, Boolean, silver_tests);
equalityTest(decorate ee2 with {isEqualToTest = ee1;}.isEqualTest, false, Boolean, silver_tests);
equalityTest(decorate ee2 with {isEqualToTest = ee2;}.isEqualTest, true, Boolean, silver_tests);
equalityTest(decorate ee2 with {isEqualToTest = ee3;}.isEqualTest, false, Boolean, silver_tests);
equalityTest(decorate ee3 with {isEqualToTest = ee1;}.isEqualTest, false, Boolean, silver_tests);
equalityTest(decorate ee3 with {isEqualToTest = ee2;}.isEqualTest, false, Boolean, silver_tests);
equalityTest(decorate ee3 with {isEqualToTest = ee3;}.isEqualTest, true, Boolean, silver_tests);
