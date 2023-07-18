grammar silver:compiler:extension:autoattr;

synthesized attribute propagateDispatcher :: (ProductionStmt ::= Decorated! QName  Location) occurs on AttributeDclInfo;

synthesized attribute emptyVal::Expr occurs on AttributeDclInfo;

aspect default production
top::AttributeDclInfo ::=
{
  top.propagateDispatcher = propagateError(_, location=_);
  top.emptyVal = error("Internal compiler error: must be defined for all monoid attribute declarations");
}

aspect production inhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.propagateDispatcher = propagateInh(_, location=_);
}

abstract production functorDcl
top::AttributeDclInfo ::= fn::String
{
  top.fullName = fn;
  propagate compareKey, isEqual;

  production tyVar::TyVar = freshTyVar(starKind());
  top.typeScheme = polyType([tyVar], varType(tyVar));
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = functorAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateFunctor(_, location=_);
}

abstract production monoidDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type empty::Expr append::Operation
{
  top.fullName = fn;
  propagate compareKey;
  top.isEqual =
    case top.compareTo of
    | monoidDcl(fn2, _, _, empty2, append2) ->
      fn == fn2 && top.typeScheme == top.compareTo.typeScheme && empty.unparse == empty2.unparse && append == append2
    | _ -> false
    end;

  top.typeScheme = polyType(bound, ty);
  top.isSynthesized = true;
  top.emptyVal = empty;
  top.operation = append;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = 
    \ dl::Decorated! DefLHS  attr::Decorated! QNameAttrOccur  e::Expr  l::Location ->
      errorAttributeDef([err(l, attr.name ++ " is a monoid collection attribute, and you must use ':=' or '<-', not '='.")], dl, attr, e, location=l);
  top.attrBaseDefDispatcher = synBaseColAttributeDef(_, _, _, location=_);
  top.attrAppendDefDispatcher = synAppendColAttributeDef(_, _, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateMonoid(_, location=_);
}

abstract production destructDcl
top::AttributeDclInfo ::= fn::String
{
  top.fullName = fn;
  propagate compareKey, isEqual;

  production tyVar::TyVar = freshTyVar(starKind());
  production inhsTyVar::TyVar = freshTyVar(inhSetKind());
  top.typeScheme = polyType([tyVar, inhsTyVar], decoratedType(varType(tyVar), varType(inhsTyVar)));
  top.isInherited = true;
  
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_); -- Allow normal inh equations
  top.attributionDispatcher = destructAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateDestruct(_, location=_);
}

abstract production equalityDcl
top::AttributeDclInfo ::= inh::String syn::String
{
  top.fullName = syn;
  propagate compareKey, isEqual;

  top.typeScheme = monoType(boolType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateEquality(inh, _, location=_);
}

abstract production orderingKeyDcl
top::AttributeDclInfo ::= syn::String
{
  top.fullName = syn;
  propagate compareKey, isEqual;

  top.typeScheme = monoType(stringType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateOrderingKey(_, location=_);
}

abstract production orderingDcl
top::AttributeDclInfo ::= inh::String keySyn::String syn::String
{
  top.fullName = syn;
  propagate compareKey, isEqual;

  top.typeScheme = monoType(intType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateOrdering(inh, keySyn, _, location=_);
}

abstract production unificationPartialDcl
top::AttributeDclInfo ::= inh::String synPartial::String syn::String
{
  top.fullName = synPartial;
  propagate compareKey, isEqual;

  top.typeScheme = monoType(boolType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateUnificationSynPartial(inh, _, syn, location=_);
}

abstract production unificationDcl
top::AttributeDclInfo ::= inh::String synPartial::String syn::String
{
  top.fullName = syn;
  propagate compareKey, isEqual;

  top.typeScheme = monoType(boolType());
  top.isSynthesized = true;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher = synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateUnificationSyn(inh, synPartial, _, location=_);
}

abstract production threadedInhDcl
top::AttributeDclInfo ::= inh::String syn::String bound::[TyVar] ty::Type o::Maybe<Operation> rev::Boolean
{
  top.fullName = inh;
  propagate compareKey;
  top.isEqual =
    case top.compareTo of
    | threadedInhDcl(inh2, syn2, _, _, _, rev2) ->
      inh == inh2 && syn == syn2 && top.typeScheme == top.compareTo.typeScheme && rev == rev2
    | _ -> false
    end;

  top.typeScheme = polyType(bound, ty);
  top.isInherited = true;
  top.isCollection = o.isJust;
  top.operation = o.fromJust;
  
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.attrDefDispatcher =
    if o.isJust
    then collectionAttrDefError
    else inheritedAttributeDef(_, _, _, location=_); -- Allow normal inh equations
  top.attrBaseDefDispatcher = 
    if o.isJust
    then inhBaseColAttributeDef(_, _, _, location=_) -- Allow normal inh base equations
    else nonCollectionAttrBaseDefError;
  top.attrAppendDefDispatcher = 
    if o.isJust
    then inhAppendColAttributeDef(_, _, _, location=_)  -- Allow normal inh append equations
    else nonCollectionAttrAppendDefError;
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateThreadedInh(o.isJust, rev, _, syn, location=_);
}

abstract production threadedSynDcl
top::AttributeDclInfo ::= inh::String syn::String bound::[TyVar] ty::Type o::Maybe<Operation> rev::Boolean
{
  top.fullName = syn;
  propagate compareKey;
  top.isEqual =
    case top.compareTo of
    | threadedSynDcl(inh2, syn2, _, _, _, rev2) ->
      inh == inh2 && syn == syn2 && top.typeScheme == top.compareTo.typeScheme && rev == rev2
    | _ -> false
    end;

  top.typeScheme = polyType(bound, ty);
  top.isSynthesized = true;
  top.isCollection = o.isJust;
  top.operation = o.fromJust;
  
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attrDefDispatcher =
    if o.isJust
    then collectionAttrDefError
    else synthesizedAttributeDef(_, _, _, location=_); -- Allow normal syn equations
  top.attrBaseDefDispatcher = 
    if o.isJust
    then synBaseColAttributeDef(_, _, _, location=_) -- Allow normal syn base equations
    else nonCollectionAttrBaseDefError;
  top.attrAppendDefDispatcher = 
    if o.isJust
    then synAppendColAttributeDef(_, _, _, location=_)  -- Allow normal syn append equations
    else nonCollectionAttrAppendDefError;
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
  top.propagateDispatcher = propagateThreadedSyn(o.isJust, rev, inh, _, location=_);
}
