grammar silver:extension:implicit_monads;


abstract production restrictedSynDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedSynAttributeDef(_, _, _, location=_);

  --copied from synDcl
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;

  top.dclBoundVars = bound;
  top.isSynthesized = true;
  top.isInherited = false;
}


abstract production restrictedInhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedInhAttributeDef(_, _, _, location=_);

  --copied from inhDcl
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;

  top.dclBoundVars = bound;
  top.isSynthesized = false;
  top.isInherited = true;
}




abstract production implicitSynDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitSynAttributeDef(_, _, _, location=_);

  --copied from synDcl
  top.decoratedAccessHandler = synDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(synDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;

  top.dclBoundVars = bound;
  top.isSynthesized = true;
  top.isInherited = false;
}


abstract production implicitInhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitInhAttributeDef(_, _, _, location=_);

  --copied from inhDcl
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);

  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;

  top.dclBoundVars = bound;
  top.isSynthesized = false;
  top.isInherited = true;
}






function restrictedSynDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(restrictedSynDcl(sg, sl, fn, bound, ty)));
}


function restrictedInhDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(restrictedInhDcl(sg, sl, fn, bound, ty)));
}




function implicitSynDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(implicitSynDcl(sg, sl, fn, bound, ty)));
}


function implicitInhDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(implicitInhDcl(sg, sl, fn, bound, ty)));
}

