grammar silver:extension:strategyattr;

-- Utilities
abstract production try
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr try {
      $StrategyExpr{s} <+ id
    };
}

abstract production repeatS -- name clash with repeat from core
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr repeat {
      rec res -> try($StrategyExpr{s} <* res)
    };
}

abstract production reduce
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr reduce {
      repeat(rec res -> some(res) <+ $StrategyExpr{s})
    };
}

abstract production bottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr bottomUp {
      rec res -> all(res) <* $StrategyExpr{s}
    };
}

abstract production topDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr topDown {
      rec res -> $StrategyExpr{s} <* all(res)
    };
}

abstract production downUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr downUp {
      rec res -> $StrategyExpr{s1} <* all(res) <* $StrategyExpr{s2}
    };
}

abstract production allBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr allBottomUp {
      rec res -> all(res) <+ $StrategyExpr{s}
    };
}

abstract production allTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr allTopDown {
      rec res -> $StrategyExpr{s} <+ all(res)
    };
}

abstract production allDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr allDownUp {
      rec res -> $StrategyExpr{s1} <+ all(res) <+ $StrategyExpr{s2}
    };
}

abstract production someBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr someBottomUp {
      rec res -> some(res) <+ $StrategyExpr{s}
    };
}

abstract production someTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr someTopDown {
      rec res -> $StrategyExpr{s} <+ some(res)
    };
}

abstract production someDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr someDownUp {
      rec res -> $StrategyExpr{s1} <+ some(res) <+ $StrategyExpr{s2}
    };
}

abstract production onceBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr onceBottomUp {
      rec res -> one(res) <+ $StrategyExpr{s}
    };
}

abstract production onceTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr onceTopDown {
      rec res -> $StrategyExpr{s} <+ one(res)
    };
}

abstract production onceDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr onceDownUp {
      rec res -> $StrategyExpr{s1} <+ one(res) <+ $StrategyExpr{s2}
    };
}

abstract production innermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr innermost {
      rec res -> bottomUp(try($StrategyExpr{s} <* res))
    };
}

abstract production outermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr outermost {
      rec res -> topDown(try($StrategyExpr{s} <* res))
    };
}
