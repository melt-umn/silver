grammar silver:extension:strategyattr;

import silver:metatranslation;
import silver:modification:copper; -- print keyword

-- Debugging
abstract production printTerm
top::StrategyExpr ::=
{
  top.unparse = s"printTerm";
  
  propagate liftedStrategies;
  top.translation =
    Silver_Expr {
      core:unsafeTrace(
        core:just($name{top.frame.signature.outputElement.elementName}),
        core:print(
          hackUnparse($name{top.frame.signature.outputElement.elementName}) ++ "\n\n",
          core:unsafeIO()))
    };
}

-- Utilities
abstract production try
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      $StrategyExpr{s} <+ id
    };
}

abstract production repeatS -- name clash with repeat from core
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> try($StrategyExpr{s} <* res)
    };
}

abstract production reduce
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      repeat(rec res -> some(res) <+ $StrategyExpr{s})
    };
}

abstract production bottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> all(res) <* $StrategyExpr{s}
    };
}

abstract production topDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s} <* all(res)
    };
}

abstract production downUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s1} <* all(res) <* $StrategyExpr{s2}
    };
}

abstract production allBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> all(res) <+ $StrategyExpr{s}
    };
}

abstract production allTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s} <+ all(res)
    };
}

abstract production allDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s1} <+ all(res) <+ $StrategyExpr{s2}
    };
}

abstract production someBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> some(res) <+ $StrategyExpr{s}
    };
}

abstract production someTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s} <+ some(res)
    };
}

abstract production someDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s1} <+ some(res) <+ $StrategyExpr{s2}
    };
}

abstract production onceBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> one(res) <+ $StrategyExpr{s}
    };
}

abstract production onceTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s} <+ one(res)
    };
}

abstract production onceDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> $StrategyExpr{s1} <+ one(res) <+ $StrategyExpr{s2}
    };
}

abstract production innermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> bottomUp(try($StrategyExpr{s} <* res))
    };
}

abstract production outermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec res -> topDown(try($StrategyExpr{s} <* res))
    };
}
