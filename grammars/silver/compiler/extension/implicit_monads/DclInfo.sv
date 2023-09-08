grammar silver:compiler:extension:implicit_monads;


abstract production restrictedSynDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedSynAttributeDef(_, _, _, location=_);

  --copied from synDcl
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, ty);

  top.isSynthesized = true;
  top.isInherited = false;
}


abstract production restrictedInhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedInhAttributeDef(_, _, _, location=_);

  --copied from inhDcl
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = false;
  top.isInherited = true;
}




abstract production implicitSynDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitSynAttributeDef(_, _, _, location=_);

  --copied from synDcl
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.dataAccessHandler = synDataAccessHandler(_, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = true;
  top.isInherited = false;
}


abstract production implicitInhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitInhAttributeDef(_, _, _, location=_);

  --copied from inhDcl
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler(_, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = false;
  top.isInherited = true;
}






function restrictedSynDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(restrictedSynDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));
}


function restrictedInhDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(restrictedInhDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));
}




function implicitSynDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(implicitSynDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));
}


function implicitInhDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(implicitInhDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));
}

