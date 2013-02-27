grammar silver:modification:autocopyattr;

synthesized attribute isAutocopy :: Boolean occurs on DclInfo;

aspect default production
top::DclInfo ::=
{
  top.isAutocopy = false;
}

abstract production autocopyDcl
top::DclInfo ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  ty.boundVariables = top.boundVariables ++ bound; -- explicit to make sure it errors if we can't
  top.unparse = "autocopy(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ unparseTyVars(bound, ty.boundVariables) ++ ", " ++ ty.unparse ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  top.isInherited = true;
  top.isAutocopy = true;
  
  -- the core dispatchers
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate(inhDecoratedAccessHandler, _, _, _); -- TODO: should probably be an error handler!
  top.attrDefDispatcher = inheritedAttributeDef;
  
  forwards to inhDcl(sg,sl,fn,bound,ty);
}

-- Defs:

function autocopyDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  return attrDef(defaultEnvItem(autocopyDcl(sg,sl,fn,bound,ty)));
}

