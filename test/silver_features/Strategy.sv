grammar silver_features;

strategy attribute elimPlusZero =
  all(elimPlusZero) <*
  try(rule on SExpr of addSExpr(e, constSExpr(0)) -> e end);

nonterminal SExpr with elimPlusZero;

abstract production addSExpr
top::SExpr ::= e1::SExpr e2::SExpr
{
  propagate elimPlusZero;
}
abstract production constSExpr
top::SExpr ::= i::Integer
{
  propagate elimPlusZero;
}
abstract production idSExpr
top::SExpr ::= id::String
{
  propagate elimPlusZero;
}

nonterminal SStmt with elimPlusZero;
abstract production seqSStmt
top::SStmt ::= s1::SStmt s2::SStmt
{
  propagate elimPlusZero;
}
abstract production assignSStmt
top::SStmt ::= n::String e::SExpr
{
  propagate elimPlusZero;
}

equalityTest(
  hackUnparse(addSExpr(constSExpr(42), constSExpr(0)).elimPlusZero),
  "core:just(silver_features:constSExpr(42))",
  String, silver_tests);

equalityTest(
  hackUnparse(
    seqSStmt(
      assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
      assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(0)), constSExpr(0)))).elimPlusZero),
  "core:just(silver_features:seqSStmt(silver_features:assignSStmt(\"a\", silver_features:constSExpr(42)), silver_features:assignSStmt(\"b\", silver_features:idSExpr(\"a\"))))",
  String, silver_tests);
