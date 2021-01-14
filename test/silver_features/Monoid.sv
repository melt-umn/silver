
monoid attribute defs::[String] with [], ++;
monoid attribute freeVars::[String] with [], ++;

propagate defs on MStmt;
propagate freeVars on MExpr;

nonterminal MStmt with defs, freeVars;
nonterminal MExpr with freeVars;

abstract production seqMStmt
top::MStmt ::= s1::MStmt s2::MStmt
{
  top.freeVars := s1.freeVars ++ removeAll(s1.defs, s2.freeVars);
}

abstract production assignMStmt
top::MStmt ::= a::String e::MExpr
{
  propagate freeVars;
  top.defs <- [a];
}

abstract production addMExpr
top::MExpr ::= e1::MExpr e2::MExpr
{}

abstract production varMExpr
top::MExpr ::= a::String
{
  top.freeVars <- [a];
}

global testMStmt::MStmt =
  seqMStmt(
    assignMStmt("a", addMExpr(varMExpr("b"), varMExpr("c"))),
    assignMStmt("d", varMExpr("a")));

equalityTest(testMStmt.defs, ["a", "d"], [String], silver_tests);
equalityTest(testMStmt.freeVars, ["b", "c"], [String], silver_tests);

-- Test errors in propagate
monoid attribute things<a>::[a] with [], ++;

nonterminal Thing1 with things<Integer>;
nonterminal Thing2 with things<Float>;

abstract production thing2Thing1
top::Thing1 ::= Thing2
{}

-- Test for both parts of 2-part error message
wrongCode "In propagate of things for production silver_features:thing2Thing1" {
  propagate things on Thing1;
}
wrongCode "things has type [Integer] but the expression being assigned to it has type [Float]" {
  propagate things on Thing1;
}
