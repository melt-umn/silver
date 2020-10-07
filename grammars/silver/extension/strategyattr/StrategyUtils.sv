grammar silver:extension:strategyattr;

import silver:metatranslation;
import silver:modification:copper; -- print keyword

-- Debugging
abstract production printTerm
top::StrategyExpr ::=
{
  top.unparse = s"printTerm";
  
  propagate liftedStrategies;
  top.isTotal = true;
  top.totalTranslation =
    Silver_Expr {
      core:unsafeTrace(
        $name{top.frame.signature.outputElement.elementName},
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
  local recVarName::String = "repeat_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> try($StrategyExpr{s} <* $strategyQName{recVarName})
    };
}

abstract production reduce
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "reduce_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      repeat(rec $name{recVarName} -> some($strategyQName{recVarName}) <+ $StrategyExpr{s})
    };
}

abstract production bottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "bottomUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> all($strategyQName{recVarName}) <* $StrategyExpr{s}
    };
}

abstract production topDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "topDown_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s} <* all($strategyQName{recVarName})
    };
}

abstract production downUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = "downUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s1} <* all($strategyQName{recVarName}) <* $StrategyExpr{s2}
    };
}

abstract production allBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "allBottomUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> all($strategyQName{recVarName}) <+ $StrategyExpr{s}
    };
}

abstract production allTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "allTopDown_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s} <+ all($strategyQName{recVarName})
    };
}

abstract production allDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = "allDownUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s1} <+ all($strategyQName{recVarName}) <+ $StrategyExpr{s2}
    };
}

abstract production someBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "someBottomUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> some($strategyQName{recVarName}) <+ $StrategyExpr{s}
    };
}

abstract production someTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "someTopDown_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s} <+ some($strategyQName{recVarName})
    };
}

abstract production someDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = "someDownUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s1} <+ some($strategyQName{recVarName}) <+ $StrategyExpr{s2}
    };
}

abstract production onceBottomUp
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "onceBottomUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> one($strategyQName{recVarName}) <+ $StrategyExpr{s}
    };
}

abstract production onceTopDown
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "onceTopDown_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s} <+ one($strategyQName{recVarName})
    };
}

abstract production onceDownUp
top::StrategyExpr ::= s1::StrategyExpr s2::StrategyExpr
{
  local recVarName::String = "onceDownUp_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> $StrategyExpr{s1} <+ one($strategyQName{recVarName}) <+ $StrategyExpr{s2}
    };
}

abstract production innermost
top::StrategyExpr ::= s::StrategyExpr
{
  local recVarName::String = "innermost_" ++ toString(genInt());
  forwards to
    Silver_StrategyExpr (top.genName) {
      rec $name{recVarName} -> bottomUp(try($StrategyExpr{s} <* $strategyQName{recVarName}))
    };
}

abstract production outermost
top::StrategyExpr ::= s::StrategyExpr
{
  forwards to
    Silver_StrategyExpr (top.genName) {
      repeat(onceTopDown($StrategyExpr{s}))
    };
}
