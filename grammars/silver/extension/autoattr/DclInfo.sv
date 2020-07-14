grammar silver:extension:autoattr;

synthesized attribute propagateDispatcher :: (ProductionStmt ::= Decorated QName  Location) occurs on DclInfo;

synthesized attribute emptyVal::Expr occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.propagateDispatcher = propagateError(_, location=_);
  top.emptyVal = error("Internal compiler error: must be defined for all monoid attribute declarations");
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

abstract production monoidDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type empty::Expr append::Operation
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;
  top.isSynthesized = true;
  top.emptyVal = empty;
  top.operation = append;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = 
    \ dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, attr.name ++ " is a monoid collection attribute, and you must use ':=' or '<-', not '='.")], dl, attr, e, location=l);
  top.attrBaseDefDispatcher = synBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = synAppendColAttributeDef(_, _, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateMonoid(_, location=_);
}
