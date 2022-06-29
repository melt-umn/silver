grammar silver:compiler:modification:autocopyattr;

synthesized attribute isAutocopy :: Boolean occurs on AttributeDclInfo;

aspect default production
top::AttributeDclInfo ::=
{
  top.isAutocopy = false;
}

abstract production autocopyDcl
top::AttributeDclInfo ::= fn::String bound::[TyVar] ty::Type
{
  top.fullName = fn;
  propagate compareKey;

  top.typeScheme = polyType(bound, ty);

  top.isInherited = true;
  top.isAutocopy = true;
  
  -- the core dispatchers
  top.decoratedAccessHandler = inhDecoratedAccessHandler(_, _, location=_);
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler(_, _, location=_), _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = inheritedAttributeDef(_, _, _, location=_);
  top.attributionDispatcher = defaultAttributionDcl(_, _, _, _, location=_);
}

-- Defs:

function autocopyDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type
{
  return attrDef(defaultEnvItem(autocopyDcl(fn,bound,ty, sourceGrammar=sg, sourceLocation=sl)));
}

