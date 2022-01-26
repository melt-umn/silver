grammar silver:compiler:modification:lambda_fn:java;

import silver:compiler:modification:lambda_fn;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;

import silver:compiler:translation:java:core;
import silver:compiler:translation:java:type;

import silver:compiler:definition:flow:ast only ExprVertexInfo, FlowVertex;

aspect production lambdap
top::Expr ::= params::ProductionRHS e::Expr
{
  local finTy :: Type = finalType(top);
  
  -- Attempt to solve a context `runtimeTypeable ${finType}`, from which the runtime TypeRep translation is computed.
  -- If the type somehow contains a skolem (e.g. through scoped type variables),
  -- then we will attempt to use the more specific runtime TypeRep from the context,
  -- but will otherwise fall back to rigid skolem constant TypeReps.
  local context :: Context = typeableContext(finTy);
  context.env = top.env;
  
  local lambdaTrans :: (String ::= String) = \ runtimeTypeTrans::String ->
s"""(new common.NodeFactory<${finTy.outputType.transType}>() {
				@Override
				public final ${finTy.outputType.transType} invoke(final common.OriginContext originCtx, final Object[] args, final Object[] namedArgs) {
${params.lambdaTranslation}
					return ${e.translation};
				}

				@Override
				public final common.TypeRep getType() {
${makeTyVarDecls(5, finTy.freeVariables)}
					return ${runtimeTypeTrans};
				}

				@Override
				public final String toString() {
					return "lambda at ${top.grammarName}:${top.location.unparse}";
				}
			})""";

  top.translation = lambdaTrans(context.transTypeableContext);
  top.lazyTranslation = top.translation;

  -- Same as above, except that we know that this is a top-level generalized binding
  -- (e.g. global id :: (a ::= a) = \ x::a -> x;)
  -- so freshen all skolem type vars in the run-time type representation.
  top.generalizedTranslation = lambdaTrans(transFreshTypeRep(finTy));
  
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
  top.lambdaTranslation = s"\t\t\t\t\tfinal Object ${makeLambdaParamValueName(fName)} = args[${toString(top.accessIndex)}];\n";
}

function makeLambdaParamValueName
String ::= s::String
{
  return "lambdaParam_" ++ makeIdName(s);
}

aspect production lambdaParamReference
top::Expr ::= q::PartiallyDecorated QName
{
  top.translation = s"((${top.typerep.transType})common.Util.demand(${makeLambdaParamValueName(q.lookupValue.fullName)}))";
  top.lazyTranslation = top.translation;
}

