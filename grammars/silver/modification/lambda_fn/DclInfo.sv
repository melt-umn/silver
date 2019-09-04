import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

abstract production lambdaParamDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::Type
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files");
  
  top.typerep = ty;

  top.refDispatcher = lambdaParamReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
}

function lambdaParamDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(lambdaParamDcl(sg,sl,fn,ty)));
}

