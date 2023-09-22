synthesized attribute lambdaId::Integer occurs on ValueDclInfo;
synthesized attribute lambdaParamIndex::Integer occurs on ValueDclInfo;

aspect default production
top::ValueDclInfo ::=
{
  top.lambdaId = error("Should only be demanded on lambda params");
  top.lambdaParamIndex = error("Should only be demanded on lambda params");
}


abstract production lambdaParamDcl
top::ValueDclInfo ::= fn::String ty::Type id::Integer paramIndex::Integer
{
  top.fullName = fn;
  propagate isEqual;

  top.typeScheme = monoType(ty);

  top.lambdaParamIndex = paramIndex;
  top.lambdaId = id;

  top.refDispatcher = lambdaParamReference(_, location=_);
  top.defDispatcher = errorValueDef(_, _, location=_); -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS(_, location=_); -- ditto
  top.transDefLHSDispatcher = errorTransAttrDefLHS(_, _, location=_);
}

function lambdaParamDef
Def ::= sg::String sl::Location fn::String ty::Type id::Integer paramIndex::Integer
{
  return valueDef(defaultEnvItem(lambdaParamDcl(fn,ty,id,paramIndex,sourceGrammar=sg,sourceLocation=sl)));
}
