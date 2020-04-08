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

abstract production repeatS
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr repeat {
      rec res -> try($StrategyExpr{s} <* res)
    };
}
{-
abstract production reduce
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to repeat(rec(\ x::StrategyExpr -> some(x) <+ s));
}
-}
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
{-
abstract production innermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to bottomUp(try(s <* innermost(s)));
}

abstract production outermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to topDown(try(s <* outermost(s)));
}
-}