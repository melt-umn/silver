grammar silver:extension:strategyattr;

synthesized attribute strategyExpr :: StrategyExpr occurs on DclInfo;
attribute liftedStrategies occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.strategyExpr = error("Internal compiler error: must be defined for all strategy attribute declarations");
  top.liftedStrategies := error("Internal compiler error: must be defined for all strategy attribute declarations");
}

abstract production strategyDcl
top::DclInfo ::= sg::String sl::Location fn::String tyVar::TyVar e::Decorated StrategyExpr
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
  
  top.strategyExpr = new(e);
  top.liftedStrategies := e.liftedStrategies;
}
