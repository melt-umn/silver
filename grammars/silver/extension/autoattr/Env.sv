grammar silver:extension:autoattr;

synthesized attribute propagateDispatcher :: (ProductionStmt ::= Decorated QName  Location) occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.propagateDispatcher = propagateError(_, location=_);
}

abstract production functorDcl
top::DclInfo ::= sg::String sl::Location fn::String tyVar::TyVar
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = varType(tyVar);
  top.dclBoundVars = [tyVar];
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = functorAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateFunctor(_, location=_);
}
