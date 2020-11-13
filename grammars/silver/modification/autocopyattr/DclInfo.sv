grammar silver:modification:autocopyattr;

synthesized attribute isAutocopy :: Boolean occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.isAutocopy = false;
}

abstract production autocopyDcl
top::DclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;

  top.typeScheme = polyType(bound, ty);

  top.isInherited = true;
  top.isAutocopy = true;
  
  -- the core dispatchers
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_);
  
  forwards to inhDcl(fn,bound,ty,sourceGrammar=top.sourceGrammar,location=top.location); -- TODO: Interference...
}

-- Defs:

function autocopyDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(autocopyDcl(fn,bound,ty, sourceGrammar=sg, location=sl)));
}

