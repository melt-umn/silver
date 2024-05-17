grammar silver:compiler:extension:implicit_monads;


abstract production restrictedSynDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedSynAttributeDef;

  --copied from synDcl
  top.decoratedAccessHandler = synDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler);
  top.dataAccessHandler = synDataAccessHandler;
  top.attributionDispatcher = defaultAttributionDcl;

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = true;
  top.isInherited = false;
}


abstract production restrictedInhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedInhAttributeDef;

  --copied from inhDcl
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler;
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler;
  top.attributionDispatcher = defaultAttributionDcl;

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = false;
  top.isInherited = true;
}




abstract production implicitSynDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitSynAttributeDef;

  --copied from synDcl
  top.decoratedAccessHandler = synDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler);
  top.dataAccessHandler = synDataAccessHandler;
  top.attributionDispatcher = defaultAttributionDcl;

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = true;
  top.isInherited = false;
}


abstract production implicitInhDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitInhAttributeDef;

  --copied from inhDcl
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = inhUndecoratedAccessErrorHandler;
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler;
  top.attributionDispatcher = defaultAttributionDcl;

  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, new(ty));

  top.isSynthesized = false;
  top.isInherited = true;
}






fun restrictedSynDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type =
  attrDef(defaultEnvItem(restrictedSynDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));


fun restrictedInhDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type =
  attrDef(defaultEnvItem(restrictedInhDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));




fun implicitSynDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type =
  attrDef(defaultEnvItem(implicitSynDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));


fun implicitInhDef Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type =
  attrDef(defaultEnvItem(implicitInhDcl(fn, bound, ty, sourceGrammar=sg, sourceLocation=sl)));

