grammar silver:modification:lambda_fn:java;

import silver:modification:lambda_fn;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;

import silver:translation:java:core;
import silver:translation:java:type;

import silver:definition:flow:ast only ExprVertexInfo, FlowVertex;

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  local finTy :: TypeExp = finalType(top);
  
  top.translation = 
s"""(new common.NodeFactory<${e.typerep.transType}>() {
  public final ${e.typerep.transType} invoke(final Object[] args, final Object[] namedArgs) {
    ${params.lambdaTranslation}
    return ${e.translation};
  }
})""";
  top.lazyTranslation = top.translation; -- TODO: is this correct?
  
  params.accessIndex = 0;
}

synthesized attribute lambdaTranslation::String occurs on ProductionRHS, ProductionRHSElem;
inherited attribute accessIndex::Integer occurs on ProductionRHS, ProductionRHSElem;

aspect production productionRHSCons
top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS
{
  top.lambdaTranslation = h.lambdaTranslation ++ t.lambdaTranslation;
  t.accessIndex = top.accessIndex + 1;
  h.accessIndex = top.accessIndex;
}
aspect production productionRHSNil
top::ProductionRHS ::= 
{
  top.lambdaTranslation = "";
}

aspect production productionRHSElem
top::ProductionRHSElem ::= id::Name '::' t::Type
{
  top.lambdaTranslation = makeLambdaParamBinding(fName, t.typerep, top.accessIndex);
}

function makeLambdaParamValueName
String ::= s::String
{
  return "__SV_LAMBDA_PARAM_" ++ makeIdName(s);
}

function makeLambdaParamBinding
String ::= fn::String ty::TypeExp index::Integer
{
  return s"final ${ty.transType} ${makeLambdaParamValueName(fn)} = (${ty.transType})args[${toString(index)}];\n";
}

aspect production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.translation = makeLambdaParamValueName(q.lookupValue.fullName);
  top.lazyTranslation = top.translation;
}

