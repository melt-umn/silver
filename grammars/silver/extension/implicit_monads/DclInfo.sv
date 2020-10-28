grammar silver:extension:implicit_monads;


abstract production restrictedSynDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedSynAttributeDef(_, _, _, location=_);

  local fwd::DclInfo = synDcl(sg, sl, fn, bound, ty);
  forwards to fwd;
}


abstract production restrictedInhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = restrictedInhAttributeDef(_, _, _, location=_);

  local fwd::DclInfo = inhDcl(sg, sl, fn, bound, ty);
  forwards to fwd;
}




abstract production implicitSynDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitSynAttributeDef(_, _, _, location=_);

  local fwd::DclInfo = synDcl(sg, sl, fn, bound, ty);
  forwards to fwd;
}


abstract production implicitInhDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.attrDefDispatcher = implicitInhAttributeDef(_, _, _, location=_);

  local fwd::DclInfo = inhDcl(sg, sl, fn, bound, ty);
  forwards to fwd;
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

