grammar silver:compiler:extension:strategyattr;

synthesized attribute isStrategy::Boolean occurs on AttributeDclInfo;
attribute isTotal occurs on AttributeDclInfo;
synthesized attribute containsErrors::Boolean occurs on AttributeDclInfo;
synthesized attribute liftedStrategyNames::[String] occurs on AttributeDclInfo;
synthesized attribute givenRecVarNameEnv::[Pair<String String>] occurs on AttributeDclInfo;
synthesized attribute givenRecVarTotalEnv::[Pair<String Boolean>] occurs on AttributeDclInfo;
attribute partialRefs, totalRefs occurs on AttributeDclInfo;
synthesized attribute strategyExpr :: StrategyExpr occurs on AttributeDclInfo;

aspect default production
top::AttributeDclInfo ::=
{
  top.isStrategy = false;
  top.isTotal = true;
  top.containsErrors = false;
  top.liftedStrategyNames = [];
  top.givenRecVarNameEnv = [];
  top.givenRecVarTotalEnv = [];
  top.partialRefs := [];
  top.totalRefs := [];
  top.strategyExpr = error("Internal compiler error: must be defined for all strategy attribute declarations");
}

abstract production strategyDcl
top::AttributeDclInfo ::=
  fn::String isTotal::Boolean tyVar::TyVar
  containsErrors::Boolean liftedStrategyNames::[String] givenRecVarNameEnv::[Pair<String String>] givenRecVarTotalEnv::[Pair<String Boolean>] partialRefs::[String] totalRefs::[String]
  e::StrategyExpr
{
  top.fullName = fn;

  top.typeScheme = polyType([tyVar],
    if isTotal
    then varType(tyVar)
    else appType(nonterminalType("silver:core:Maybe", [starKind()], false), varType(tyVar)));
  top.isSynthesized = true;
  top.isStrategy = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = strategyAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateStrategy(_, location=_);
  
  top.isTotal = isTotal;
  top.containsErrors = containsErrors;
  top.liftedStrategyNames = liftedStrategyNames;
  top.givenRecVarNameEnv = givenRecVarNameEnv;
  top.givenRecVarTotalEnv = givenRecVarTotalEnv;
  top.partialRefs := partialRefs;
  top.totalRefs := totalRefs;
  top.strategyExpr = e;
}
