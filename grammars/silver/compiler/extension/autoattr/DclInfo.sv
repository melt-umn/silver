grammar silver:compiler:extension:autoattr;

synthesized attribute propagateDispatcher :: (ProductionStmt ::= Decorated QName  Location) occurs on DclInfo;

synthesized attribute emptyVal::Expr occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.propagateDispatcher = propagateError(_, location=_);
  top.emptyVal = error("Internal compiler error: must be defined for all monoid attribute declarations");
}

aspect production inhDcl
top::DclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.propagateDispatcher = propagateInh(_, location=_);
}

abstract production functorDcl
top::DclInfo ::= fn::String tyVar::TyVar
{
  top.fullName = fn;

  top.typeScheme = polyType([tyVar], varType(tyVar));
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = functorAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateFunctor(_, location=_);
}

abstract production monoidDcl
top::DclInfo ::= fn::String bound::[TyVar] ty::Type empty::Expr append::Operation
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);
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

abstract production destructDcl
top::DclInfo ::= fn::String tyVar::TyVar
{
  top.fullName = fn;

  top.typeScheme = polyType([tyVar], varType(tyVar));
  top.isInherited = true;
  
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler! access inh from undecorated?
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_); -- Allow normal inh equations
  top.attributionDispatcher = functorAttributionDcl(_, _, _, _, location=_); -- Same as functor
  top.propagateDispatcher = propagateDestruct(_, location=_);
}

abstract production equalityDcl
top::DclInfo ::= inh::String syn::String
{
  top.fullName = syn;

  top.typeScheme = monoType(boolType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateEquality(inh, _, location=_);
}

abstract production orderingKeyDcl
top::DclInfo ::= syn::String
{
  top.fullName = syn;

  top.typeScheme = monoType(stringType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateOrderingKey(_, location=_);
}

abstract production orderingDcl
top::DclInfo ::= inh::String keySyn::String syn::String
{
  top.fullName = syn;

  top.typeScheme = monoType(intType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateOrdering(inh, keySyn, _, location=_);
}

abstract production unificationInhDcl
top::DclInfo ::= fn::String tyVar::TyVar inhs::[String]
{
  top.fullName = fn;

  top.typeScheme = polyType([tyVar], decoratedType(varType(tyVar), inhSetType(sort(nub(fn :: inhs)))));
  top.isInherited = true;
  
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler! access inh from undecorated?
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_); -- Allow normal inh equations
  top.attributionDispatcher = unificationInhAttributionDcl(_, _, _, _, location=_); -- Same as functor, except decorated
  top.propagateDispatcher = propagateDestruct(_, location=_);
}

abstract production unificationSynPartialDcl
top::DclInfo ::= inh::String synPartial::String syn::String
{
  top.fullName = synPartial;

  top.typeScheme = monoType(boolType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateUnificationSynPartial(inh, _, syn, location=_);
}

abstract production unificationSynDcl
top::DclInfo ::= inh::String synPartial::String syn::String
{
  top.fullName = syn;

  top.typeScheme = monoType(boolType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateUnificationSyn(inh, synPartial, _, location=_);
}

abstract production threadedInhDcl
top::DclInfo ::= inh::String syn::String bound::[TyVar] ty::Type
{
  top.fullName = inh;

  top.typeScheme = polyType(bound, ty);
  top.isInherited = true;
  
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_); -- Allow normal inh equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateThreadedInh(_, syn, location=_);
}

abstract production threadedSynDcl
top::DclInfo ::= inh::String syn::String bound::[TyVar] ty::Type
{
  top.fullName = syn;

  top.typeScheme = polyType(bound, ty);
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateThreadedSyn(inh, _, location=_);
}
