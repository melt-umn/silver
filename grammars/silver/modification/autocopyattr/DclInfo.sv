grammar silver:modification:autocopyattr;

import silver:definition:env;
import silver:definition:core;
import silver:definition:type;

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

  -- the core dispatchers
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.attrDefDispatcher = inheritedAttributeDef;
  forwards to inhDcl(sg,sl,fn,bound,ty);
}

-- Defs:

function autocopyDef
Def ::= sg::String sl::Location fn::String bound::[TyVar] ty::TypeExp
{
  return attrDef(defaultEnvItem(autocopyDcl(sg,sl,fn,bound,ty)));
}
