package silver.modification.lambda_fn.java;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.flow.ast.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.lambda_fn.Init.initAllStatics();
		silver.modification.lambda_fn.java.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.flow.ast.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.lambda_fn.Init.init();
		silver.modification.lambda_fn.java.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.flow.ast.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.lambda_fn.Init.postInit();
		silver.modification.lambda_fn.java.Init.postInit();


	}

	private static void setupInheritedAttributes(){
		//	local attribute finTy::Type;
		silver.modification.lambda_fn.Plambdap.localInheritedAttributes[silver.modification.lambda_fn.java.Init.finTy__ON__silver_modification_lambda_fn_lambdap] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.modification.lambda_fn.Plambdap.occurs_local[silver.modification.lambda_fn.java.Init.finTy__ON__silver_modification_lambda_fn_lambdap] = "silver:modification:lambda_fn:lambdap:local:finTy";
		silver.definition.core.NProductionRHS.occurs_syn[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHS] = "silver:modification:lambda_fn:java:lambdaTranslation";
		silver.definition.core.NProductionRHSElem.occurs_syn[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHSElem] = "silver:modification:lambda_fn:java:lambdaTranslation";
		silver.definition.core.NProductionRHS.occurs_inh[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHS] = "silver:modification:lambda_fn:java:accessIndex";
		silver.definition.core.NProductionRHSElem.occurs_inh[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHSElem] = "silver:modification:lambda_fn:java:accessIndex";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION lambdap top ::= params::ProductionRHS e::Expr 
		// finTy = finalType(top)
		silver.modification.lambda_fn.Plambdap.localAttributes[silver.modification.lambda_fn.java.Init.finTy__ON__silver_modification_lambda_fn_lambdap] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.translation.java.core.PfinalType.invoke(context)); } };
		// top.translation = "(new common.NodeFactory<" ++ finTy.outputType.transType ++ ">() {\n  public final " ++ finTy.outputType.transType ++ " invoke(final Object[] args, final Object[] namedArgs) {\n    " ++ params.lambdaTranslation ++ "\n    return " ++ e.translation ++ ";\n  }\n})"
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(new common.NodeFactory<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)context.localDecorated(silver.modification.lambda_fn.java.Init.finTy__ON__silver_modification_lambda_fn_lambdap).synthesized(silver.definition.type.Init.silver_definition_type_outputType__ON__silver_definition_type_Type)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(">() {\n  public final ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)context.localDecorated(silver.modification.lambda_fn.java.Init.finTy__ON__silver_modification_lambda_fn_lambdap).synthesized(silver.definition.type.Init.silver_definition_type_outputType__ON__silver_definition_type_Type)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" invoke(final Object[] args, final Object[] namedArgs) {\n    ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_params).synthesized(silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n    return ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.lambda_fn.Plambdap.i_e).synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)), (common.StringCatter)(new common.StringCatter(";\n  }\n})")))))))))); } };
		// top.lazyTranslation = top.translation
		silver.modification.lambda_fn.Plambdap.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)); } };
		// params.accessIndex = 0
		silver.modification.lambda_fn.Plambdap.childInheritedAttributes[silver.modification.lambda_fn.Plambdap.i_params][silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		//ASPECT PRODUCTION productionRHSCons top ::= h::ProductionRHSElem t::ProductionRHS 
		// top.lambdaTranslation = h.lambdaTranslation ++ t.lambdaTranslation
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionRHSCons.i_h).synthesized(silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHSElem)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionRHSCons.i_t).synthesized(silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHS))); } };
		// t.accessIndex = top.accessIndex + 1
		silver.definition.core.PproductionRHSCons.childInheritedAttributes[silver.definition.core.PproductionRHSCons.i_t][silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.inherited(silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHS)) + Integer.valueOf((int)1)); } };
		// h.accessIndex = top.accessIndex
		silver.definition.core.PproductionRHSCons.childInheritedAttributes[silver.definition.core.PproductionRHSCons.i_h][silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHS)); } };
		//ASPECT PRODUCTION productionRHSNil top ::= 
		// top.lambdaTranslation = ""
		silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		//ASPECT PRODUCTION productionRHSElem top ::= id::Name '::' t::TypeExpr 
		// top.lambdaTranslation = "final Object " ++ makeLambdaParamValueName(fName) ++ " = args[" ++ toString(top.accessIndex) ++ "];\n"
		silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("final Object ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.lambda_fn.java.PmakeLambdaParamValueName.invoke(context.localAsIsLazy(silver.modification.lambda_fn.Init.fName__ON__silver_definition_core_productionRHSElem))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = args[")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.inherited(silver.modification.lambda_fn.java.Init.silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHSElem)))), (common.StringCatter)(new common.StringCatter("];\n")))))); } };
		//FUNCTION makeLambdaParamValueName String ::= s::String 
		//ASPECT PRODUCTION lambdaParamReference top ::= q::Decorated QName 
		// top.translation = "((" ++ top.typerep.transType ++ ")common.Util.demand(" ++ makeLambdaParamValueName(q.lookupValue.fullName) ++ "))"
		silver.modification.lambda_fn.PlambdaParamReference.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("((")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)context.synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")common.Util.demand(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.modification.lambda_fn.java.PmakeLambdaParamValueName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.lambda_fn.PlambdaParamReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } })), (common.StringCatter)(new common.StringCatter("))")))))); } };
		// top.lazyTranslation = top.translation
		silver.modification.lambda_fn.PlambdaParamReference.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)); } };
	}

	public static int count_local__ON__silver_modification_lambda_fn_java_makeLambdaParamValueName = 0;
public static final int finTy__ON__silver_modification_lambda_fn_lambdap = silver.modification.lambda_fn.Init.count_local__ON__silver_modification_lambda_fn_lambdap++;
public static final int silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHS = silver.definition.core.Init.count_syn__ON__ProductionRHS++;
public static final int silver_modification_lambda_fn_java_lambdaTranslation__ON__silver_definition_core_ProductionRHSElem = silver.definition.core.Init.count_syn__ON__ProductionRHSElem++;
public static final int silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHS = silver.definition.core.Init.count_inh__ON__ProductionRHS++;
public static final int silver_modification_lambda_fn_java_accessIndex__ON__silver_definition_core_ProductionRHSElem = silver.definition.core.Init.count_inh__ON__ProductionRHSElem++;
}
