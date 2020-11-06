grammar silver:modification:autocopyattr;

synthesized attribute isAutocopy :: Boolean occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.isAutocopy = false;
}

abstract production autocopyDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.typerep = ty;
  top.dclBoundVars = bound;

  top.isInherited = true;
  top.isAutocopy = true;
  
  -- the core dispatchers
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_);
  
  forwards to inhDcl(sg,sl,fn,bound,ty);
}

-- Defs:

function autocopyDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(autocopyDcl(sg,sl,fn,bound,ty)));
}

