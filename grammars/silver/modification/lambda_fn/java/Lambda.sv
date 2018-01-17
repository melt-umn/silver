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
  local finTy :: Type = finalType(top);
  
  top.translation = 
s"""(new common.NodeFactory<${finTy.outputType.transType}>() {
	@Override
	public final ${finTy.outputType.transType} invoke(final Object[] args, final Object[] namedArgs) {
		${params.lambdaTranslation}
		return ${e.translation};
	}
	
	@Override
	public final common.FunctionTypeRep getType() {
		${makeTyVarDecls(finTy.freeVariables)}
		return ${finTy.transTypeRep};
	}
})""";
  top.lazyTranslation = top.translation;
  
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
top::ProductionRHSElem ::= id::Name '::' t::TypeExpr
{
  -- Args are unpacked as objects, they can either be an actual value or a Thunk.
  -- We don't know which staticly, so they are just stored as Objects until use.
  -- They are then demanded and converted to the correct type where they are needed.
  top.lambdaTranslation = s"final Object ${makeLambdaParamValueName(fName)} = args[${toString(top.accessIndex)}];\n";
}

function makeLambdaParamValueName
String ::= s::String
{
  return "__SV_LAMBDA_PARAM_" ++ makeIdName(s);
}

aspect production lambdaParamReference
top::Expr ::= q::Decorated QName
{
  top.translation = s"((${top.typerep.transType})common.Util.demand(${makeLambdaParamValueName(q.lookupValue.fullName)}))";
  top.lazyTranslation = top.translation;
}

