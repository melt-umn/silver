grammar silver:extension:strategyattr;

synthesized attribute containsErrors::Boolean occurs on DclInfo;
attribute isRecursive occurs on DclInfo;
synthesized attribute liftedStrategyNames::[String] occurs on DclInfo;
synthesized attribute givenRecVarEnv::[Pair<String String>] occurs on DclInfo;
synthesized attribute strategyExpr :: StrategyExpr occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.containsErrors = false;
  top.isRecursive := false;
  top.liftedStrategyNames = [];
  top.givenRecVarEnv = [];
  top.strategyExpr = error("Internal compiler error: must be defined for all strategy attribute declarations");
}

abstract production strategyDcl
top::DclInfo ::=
  sg::String sl::Location fn::String tyVar::TyVar
  containsErrors::Boolean isRecursive::Boolean liftedStrategyNames::[String] givenRecVarEnv::[Pair<String String>]
  e::StrategyExpr
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = nonterminalType("core:Maybe", [varType(tyVar)]);
  top.dclBoundVars = [tyVar];
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = strategyAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateStrategy(_, location=_);
  
  top.containsErrors = containsErrors;
  top.isRecursive := isRecursive;
  top.liftedStrategyNames = liftedStrategyNames;
  top.givenRecVarEnv = givenRecVarEnv;
  top.strategyExpr = e;
}
