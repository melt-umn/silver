grammar silver:compiler:extension:strategyattr;

import silver:compiler:metatranslation;
import silver:compiler:modification:copper; -- print keyword

-- Debugging
abstract production printTerm
top::StrategyExpr ::=
{
  top.unparse = s"printTerm";
  
  propagate liftedStrategies;
  top.isTotal = true;
  top.totalTranslation =
    Silver_Expr {
      silver:core:unsafeTracePrint(
        ^$name{top.frame.signature.outputElement.elementName},
        silver:core:genericShow(^$name{top.frame.signature.outputElement.elementName}) ++ "\n\n")
    };
}

-- Utilities
-- Note that for the translation to work properly, we need to maintain forward.genName == top.genName
abstract production try
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      $StrategyExpr{@s} <+ id
    };
}

abstract production repeatS -- name clash with repeat from core
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_repeat_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> try($StrategyExpr{@s} <* $strategyQName{recVarName})
    };
}

abstract production reduce
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_reduce_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      repeat(rec $name{recVarName} -> some($strategyQName{recVarName}) <+ $StrategyExpr{@s})
    };
}

abstract production bottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_bottomUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> all($strategyQName{recVarName}) <* $StrategyExpr{@s}
    };
}

abstract production topDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_topDown_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s} <* all($strategyQName{recVarName})
    };
}

abstract production downUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = top.genName ++ "_downUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s1} <* all($strategyQName{recVarName}) <* $StrategyExpr{@s2}
    };
}

abstract production allBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_allBottomUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> all($strategyQName{recVarName}) <+ $StrategyExpr{@s}
    };
}

abstract production allTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_allTopDown_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s} <+ all($strategyQName{recVarName})
    };
}

abstract production allDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = top.genName ++ "_allDownUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s1} <+ all($strategyQName{recVarName}) <+ $StrategyExpr{@s2}
    };
}

abstract production someBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_someBottomUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> some($strategyQName{recVarName}) <+ $StrategyExpr{@s}
    };
}

abstract production someTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_someTopDown_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s} <+ some($strategyQName{recVarName})
    };
}

abstract production someDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = top.genName ++ "_someDownUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s1} <+ some($strategyQName{recVarName}) <+ $StrategyExpr{@s2}
    };
}

abstract production onceBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_onceBottomUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> one($strategyQName{recVarName}) <+ $StrategyExpr{@s}
    };
}

abstract production onceTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_onceTopDown_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s} <+ one($strategyQName{recVarName})
    };
}

abstract production onceDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = top.genName ++ "_onceDownUp_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{@s1} <+ one($strategyQName{recVarName}) <+ $StrategyExpr{@s2}
    };
}

abstract production innermost
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = top.genName ++ "_innermost_rec";
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> bottomUp(try($StrategyExpr{@s} <* $strategyQName{recVarName}))
    };
}

abstract production outermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      repeat(onceTopDown($StrategyExpr{@s}))
    };
}
