grammar silver:compiler:modification:lambda_fn:java;

import silver:compiler:modification:lambda_fn;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:analysis:typechecking:core only finalType;

import silver:compiler:translation:java:core;
import silver:compiler:translation:java:type;

aspect production lambdap_new
top::Expr ::= params::LambdaRHS e::Expr
{
  -- Attempt to solve a context `runtimeTypeable ${top.finalType)}`, from which the runtime TypeRep translation is computed.
  -- If the type somehow contains a skolem (e.g. through scoped type variables),
  -- then we will attempt to use the more specific runtime TypeRep from the context,
  -- but will otherwise fall back to rigid skolem constant TypeReps.
  local context :: Context = typeableContext(top.finalType);
  context.env = top.env;
  
  local lambdaTrans :: (String ::= String) = \ runtimeTypeTrans::String ->
s"""(new common.NodeFactory<${top.finalType.outputType.transType}>() {
				@Override
				public final ${top.finalType.outputType.transType} invoke(final common.OriginContext originCtx, final Object[] lambda_${toString(params.givenLambdaId)}_args, final Object[] namedArgs) {
					return ${e.translation};
				}

				@Override
				public final common.TypeRep getType() {
${makeTyVarDecls(5, top.finalType.freeVariables)}
					return ${runtimeTypeTrans};
				}

				@Override
				public final String toString() {
					return "lambda at ${top.grammarName}:${getParsedOriginLocationOrFallback(top).unparse}";
				}
			})""";

  top.translation = lambdaTrans(context.transTypeableContext);
  top.lazyTranslation = top.translation;

  -- Same as above, except that we know that this is a top-level generalized binding
  -- (e.g. global id :: (a ::= a) = \ x::a -> x;)
  -- so freshen all skolem type vars in the run-time type representation.
  top.generalizedTranslation = lambdaTrans(transFreshTypeRep(top.finalType));

  propagate initTransDecSites;
}

aspect production lambdaParamReference
top::Expr ::= q::Decorated! QName
{
  top.translation = s"common.Util.<${top.finalType.transType}>demandIndex(lambda_${toString(q.lookupValue.dcl.lambdaId)}_args, ${toString(q.lookupValue.dcl.lambdaParamIndex)})";
  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication);
  top.initTransDecSites := "";
}
