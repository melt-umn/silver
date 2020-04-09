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
