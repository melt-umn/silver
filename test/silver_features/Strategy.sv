grammar silver_features;

import core:monad;

strategy attribute elimPlusZero =
  bottomUp(try(rule on SExpr of addSExpr(e, constSExpr(0)) -> e end));

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
  hackUnparse(addSExpr(addSExpr(constSExpr(42), constSExpr(0)), constSExpr(0)).elimPlusZero),
  "core:just(silver_features:constSExpr(42))",
  String, silver_tests);

equalityTest(
  hackUnparse(
    seqSStmt(
      assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
      assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(0)), constSExpr(0)))).elimPlusZero),
  "core:just(silver_features:seqSStmt(silver_features:assignSStmt(\"a\", silver_features:constSExpr(42)), silver_features:assignSStmt(\"b\", silver_features:idSExpr(\"a\"))))",
  String, silver_tests);

strategy attribute removeLastStmt =
    rule on SStmt of
    | seqSStmt(s, assignSStmt(_, _)) -> s
    end <+
    seqSStmt(id, removeLastStmt)
  occurs on SStmt, SExpr;
propagate removeLastStmt on SStmt, SExpr;

equalityTest(
  hackUnparse(
    seqSStmt(
      assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
      assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(0)), constSExpr(0)))).removeLastStmt),
  "core:just(silver_features:assignSStmt(\"a\", silver_features:addSExpr(silver_features:constSExpr(42), silver_features:constSExpr(0))))",
  String, silver_tests);

equalityTest(
  hackUnparse(
    assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))).removeLastStmt),
  "core:nothing()",
  String, silver_tests);

equalityTest(
  hackUnparse(
    addSExpr(constSExpr(42), constSExpr(0)).removeLastStmt),
  "core:nothing()",
  String, silver_tests);


functor attribute incAllConsts occurs on SStmt, SExpr;
propagate incAllConsts on SStmt, SExpr excluding constSExpr;
aspect production constSExpr
top::SExpr ::= i::Integer
{ top.incAllConsts = constSExpr(i + 1); }

strategy attribute incTwice = incAllConsts <* incAllConsts
  occurs on SStmt, SExpr;
propagate incTwice on SStmt, SExpr;

equalityTest(
  hackUnparse(
    assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))).incTwice),
  "core:just(silver_features:assignSStmt(\"a\", silver_features:addSExpr(silver_features:constSExpr(44), silver_features:constSExpr(2))))",
  String, silver_tests);


autocopy attribute target::String occurs on SStmt, SExpr;
strategy attribute incTargetConsts =
  allTopDown(
    rule on top::SStmt of
    | assignSStmt(n, _) when n == top.target -> top
    end <* incAllConsts)
  occurs on SStmt, SExpr;
propagate incTargetConsts on SStmt, SExpr;

equalityTest(
  hackUnparse(
    decorate
      seqSStmt(
        assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
        assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(2)), constSExpr(17))))
    with {target = "b";}.incTargetConsts),
  "core:just(silver_features:seqSStmt(silver_features:assignSStmt(\"a\", silver_features:addSExpr(silver_features:constSExpr(42), silver_features:constSExpr(0))), silver_features:assignSStmt(\"b\", silver_features:addSExpr(silver_features:addSExpr(silver_features:idSExpr(\"a\"), silver_features:constSExpr(3)), silver_features:constSExpr(18)))))",
  String, silver_tests);

strategy attribute incThenElim = incAllConsts <* elimPlusZero
  occurs on SStmt, SExpr;
propagate incThenElim on SStmt, SExpr;

equalityTest(
  hackUnparse(
    assignSStmt("a", addSExpr(constSExpr(42), constSExpr(-1))).incThenElim),
  "core:just(silver_features:assignSStmt(\"a\", silver_features:constSExpr(43)))",
  String, silver_tests);
