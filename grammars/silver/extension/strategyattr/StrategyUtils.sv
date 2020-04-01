grammar silver:extension:strategyattr;


{-
-- Utilities
abstract production recStrategy
top::StrategyExpr ::= ctr::(StrategyExpr ::= StrategyExpr)
{
  forwards to ctr(top);
}

abstract production try
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to s <+ id();
}

abstract production repeat
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to try(s <* repeat(s));
}

abstract production reduce
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to repeat(rec(\ x::StrategyExpr -> some(x) <+ s));
}

abstract production bottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to all(bottomUp(s)) <* s;
}

abstract production topDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to s <* all(topDown(s));
}

abstract production onceBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to one(onceBottomUp(s)) <+ s;
}

abstract production onceTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to s <+ one(onceTopDown(s));
}

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