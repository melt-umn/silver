grammar silver_features;

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

attribute compareTo, isEqual occurs on SExpr, SStmt;
propagate compareTo, isEqual on SExpr, SStmt;

equalityTest(
  addSExpr(constSExpr(42), constSExpr(0)).elimPlusZero,
  constSExpr(42),
  SExpr, silver_tests);

equalityTest(
  addSExpr(addSExpr(constSExpr(42), constSExpr(0)), constSExpr(0)).elimPlusZero,
  constSExpr(42),
  SExpr, silver_tests);

equalityTest(
  seqSStmt(
    assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
    assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(0)), constSExpr(0)))).elimPlusZero,
  seqSStmt(assignSStmt("a", constSExpr(42)), assignSStmt("b", idSExpr("a"))),
  SStmt, silver_tests);

partial strategy attribute removeLastStmt =
    rule on SStmt of
    | seqSStmt(s, assignSStmt(_, _)) -> s
    end <+
    seqSStmt(id, removeLastStmt)
  occurs on SStmt, SExpr;
propagate removeLastStmt on SStmt, SExpr;

equalityTest(
  seqSStmt(
    assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
    assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(0)), constSExpr(0)))).removeLastStmt,
  just(assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0)))),
  Maybe<SStmt>, silver_tests);

equalityTest(
  assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))).removeLastStmt,
  nothing(),
  Maybe<SStmt>, silver_tests);

equalityTest(
  addSExpr(constSExpr(42), constSExpr(0)).removeLastStmt,
  nothing(),
  Maybe<SExpr>, silver_tests);


functor attribute incConstsF occurs on SStmt, SExpr;
propagate incConstsF on SStmt, SExpr excluding constSExpr;
aspect production constSExpr
top::SExpr ::= i::Integer
{ top.incConstsF = constSExpr(i + 1); }

strategy attribute incConsts =
  (fail <+ id <+ fail) <*
  allTopDown(
    rule on SExpr of
    | constSExpr(i) -> constSExpr(i + 1)
    end) occurs on SStmt, SExpr;
propagate incConsts on SStmt, SExpr;

strategy attribute incTwice = incConstsF <* incConsts
  occurs on SStmt, SExpr;
propagate incTwice on SStmt, SExpr;

equalityTest(
  assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))).incTwice,
  assignSStmt("a", addSExpr(constSExpr(44), constSExpr(2))),
  SStmt, silver_tests);


autocopy attribute target::String occurs on SStmt, SExpr;
strategy attribute incTargetConsts =
  allTopDown(
    rule on top::SStmt of
    | assignSStmt(n, _) when n == top.target -> top
    end <* incConsts)
  occurs on SStmt, SExpr;
propagate incTargetConsts on SStmt, SExpr;

equalityTest(
  decorate
    seqSStmt(
      assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))),
      assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(2)), constSExpr(17))))
  with {target = "b";}.incTargetConsts,
  seqSStmt(assignSStmt("a", addSExpr(constSExpr(42), constSExpr(0))), assignSStmt("b", addSExpr(addSExpr(idSExpr("a"), constSExpr(3)), constSExpr(18)))),
  SStmt, silver_tests);

strategy attribute incThenElim = incConsts <* elimPlusZero
  occurs on SStmt, SExpr;
propagate incThenElim on SStmt, SExpr;

equalityTest(
  assignSStmt("a", addSExpr(constSExpr(42), constSExpr(-1))).incThenElim,
  assignSStmt("a", constSExpr(43)),
  SStmt, silver_tests);


strategy attribute incAll = all(incConsts) occurs on SStmt, SExpr;
partial strategy attribute incSome = some(incConsts) occurs on SStmt, SExpr;
partial strategy attribute incOne = one(incConsts) occurs on SStmt, SExpr;
partial strategy attribute incFstElimSnd = seqSStmt(incConsts, elimPlusZero) occurs on SStmt, SExpr;
propagate incAll, incSome, incOne, incFstElimSnd on SStmt, SExpr;

equalityTest(
  seqSStmt(
    assignSStmt("a", constSExpr(1)),
    assignSStmt("b", constSExpr(2))).incAll,
  seqSStmt(assignSStmt("a", constSExpr(2)), assignSStmt("b", constSExpr(3))),
  SStmt, silver_tests);
equalityTest(
  seqSStmt(
    assignSStmt("a", constSExpr(1)),
    assignSStmt("b", constSExpr(2))).incSome,
  just(seqSStmt(assignSStmt("a", constSExpr(2)), assignSStmt("b", constSExpr(3)))),
  Maybe<SStmt>, silver_tests);
equalityTest(
  seqSStmt(
    assignSStmt("a", constSExpr(1)),
    assignSStmt("b", constSExpr(2))).incOne,
  just(seqSStmt(assignSStmt("a", constSExpr(2)), assignSStmt("b", constSExpr(2)))),
  Maybe<SStmt>, silver_tests);
equalityTest(
  seqSStmt(
    assignSStmt("a", addSExpr(constSExpr(1), constSExpr(0))),
    assignSStmt("b", addSExpr(constSExpr(2), constSExpr(0)))).incFstElimSnd,
  just(seqSStmt(assignSStmt("a", addSExpr(constSExpr(2), constSExpr(1))), assignSStmt("b", constSExpr(2)))),
  Maybe<SStmt>, silver_tests);

-- Negative tests
inherited attribute badInh<a>::a;
wrongCode "cannot be used as a total strategy" {
  strategy attribute badInhS = badInh;
}

synthesized attribute badSyn::Boolean;
wrongCode "cannot be used as a total strategy" {
  strategy attribute badSynS = badSyn;
}

warnCode "is not total" {
  strategy attribute notTotal = rule on SExpr of constSExpr(i) -> constSExpr(i + 1) end;
}
