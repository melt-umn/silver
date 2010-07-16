grammar silver:modification:autocopyattr;

import silver:definition:env;
import silver:definition:core;

abstract production autocopyDcl
top::DclInfo ::= sg::String sl::Decorated Location fn::String ty::Decorated TypeRep
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = "autocopy(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ")";
  
  top.typerep = ty;

  -- the core dispatchers
  top.attrAccessDispatcher = inhDNTAccessDispatcher;
  top.attrDefDispatcher = inheritedAttributeDef;
  forwards to defaultDcl();
}

-- Defs:

function addAutocopyDcl
Defs ::= sg::String sl::Decorated Location fn::String ty::Decorated TypeRep defs::Defs
{
  return consAttrDef(defaultEnvItem(decorate autocopyDcl(sg,sl,fn,ty) with {}), defs);
}
