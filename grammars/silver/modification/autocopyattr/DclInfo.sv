grammar silver:modification:autocopyattr;

import silver:definition:env;
import silver:definition:core;
import silver:definition:type;

abstract production autocopyDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "autocopy(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ somehowUnparseTyVars(bound) ++ ", " ++ prettyTypeWith(ty, bound) ++ ")";
  
  top.typerep = ty;
  top.dclBoundVars = bound;

  -- the core dispatchers
  top.attrAccessDispatcher = inhDNTAccessDispatcher;
  top.attrDefDispatcher = inheritedAttributeDef;
  forwards to defaultDcl();
}

-- Defs:

function addAutocopyDcl
Defs ::= sg::String sl::Decorated Location fn::String bound::[TyVar] ty::TypeExp defs::Defs
{
  return consAttrDef(defaultEnvItem(decorate autocopyDcl(sg,sl,fn,bound,ty) with {}), defs);
}
