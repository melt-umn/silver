import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;

abstract production lambdaParamDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;

  top.typeScheme = monoType(ty);

  top.refDispatcher = lambdaParamReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
}

function lambdaParamDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(lambdaParamDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
