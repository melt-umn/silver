grammar silver:extension:strategyattr;

synthesized attribute isStrategy::Boolean occurs on DclInfo;
attribute isTotal occurs on DclInfo;
synthesized attribute containsErrors::Boolean occurs on DclInfo;
synthesized attribute liftedStrategyNames::[String] occurs on DclInfo;
synthesized attribute givenRecVarEnv::[Pair<String Pair<Boolean String>>] occurs on DclInfo;
attribute totalRefs occurs on DclInfo;
synthesized attribute strategyExpr :: StrategyExpr occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.isStrategy = false;
  top.isTotal = true;
  top.containsErrors = false;
  top.liftedStrategyNames = [];
  top.givenRecVarEnv = [];
  top.totalRefs := [];
  top.strategyExpr = error("Internal compiler error: must be defined for all strategy attribute declarations");
}

abstract production strategyDcl
top::DclInfo ::=
  sg::String sl::Location fn::String isTotal::Boolean tyVar::TyVar
  containsErrors::Boolean liftedStrategyNames::[String] givenRecVarEnv::[Pair<String Pair<Boolean String>>] totalRefs::[String]
  e::StrategyExpr
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep =
    if isTotal
    then varType(tyVar)
    else nonterminalType("core:Maybe", [varType(tyVar)]);
  top.dclBoundVars = [tyVar];
  top.isSynthesized = true;
  top.isStrategy = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = strategyAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateStrategy(_, location=_);
  
  top.isTotal = isTotal;
  top.containsErrors = containsErrors;
  top.liftedStrategyNames = liftedStrategyNames;
  top.givenRecVarEnv = givenRecVarEnv;
  top.totalRefs := totalRefs;
  top.strategyExpr = e;
}
