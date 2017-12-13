package silver.modification.lambda_fn;

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
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.flow.ast.Init.initAllStatics();
		silver.modification.lambda_fn.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.definition.flow.ast.Init.init();
		silver.modification.lambda_fn.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.flow.ast.Init.postInit();
		silver.modification.lambda_fn.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, Plambda_c.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, Plambdap.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PlambdaParamReference.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlambdaParamDcl.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.core.NProductionRHS.occurs_syn[silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHS] = "silver:modification:lambda_fn:lambdaDefs";
		silver.definition.core.NProductionRHSElem.occurs_syn[silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHSElem] = "silver:modification:lambda_fn:lambdaDefs";
		silver.definition.core.PproductionRHSElem.occurs_local[silver.modification.lambda_fn.Init.fName__ON__silver_definition_core_productionRHSElem] = "silver:definition:core:productionRHSElem:local:fName";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.lambda_fn.Plambda_c.initProductionAttributeDefinitions();
		silver.modification.lambda_fn.Plambdap.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION productionRHSCons top ::= h::ProductionRHSElem t::ProductionRHS 
		// top.lambdaDefs = h.lambdaDefs ++ t.lambdaDefs
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHS))); } };
		//ASPECT PRODUCTION productionRHSNil top ::= 
		// top.lambdaDefs = []
		silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION productionRHSElem top ::= id::Name '::' t::TypeExpr 
		// fName = toString(genInt()) ++ ":" ++ id.name
		silver.definition.core.PproductionRHSElem.localAttributes[silver.modification.lambda_fn.Init.fName__ON__silver_definition_core_productionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionRHSElem.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)))); } };
		// top.lambdaDefs = [ lambdaParamDef(top.grammarName, t.location, fName, t.typerep) ]
		silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.modification.lambda_fn.Init.silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.lambda_fn.PlambdaParamDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ProductionRHSElem), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.core.PproductionRHSElem.i_t).undecorate()).getAnno_core_location()); } }, context.localAsIsLazy(silver.modification.lambda_fn.Init.fName__ON__silver_definition_core_productionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSElem.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		silver.modification.lambda_fn.PlambdaParamReference.initProductionAttributeDefinitions();
		silver.modification.lambda_fn.PlambdaParamDcl.initProductionAttributeDefinitions();
		//FUNCTION lambdaParamDef Def ::= sg::String sl::Location fn::String ty::Type 
	}

	public static int count_local__ON__silver_modification_lambda_fn_lambda_c = 0;
	public static int count_local__ON__silver_modification_lambda_fn_lambdap = 0;
	public static int count_local__ON__silver_modification_lambda_fn_lambdaParamReference = 0;
	public static int count_local__ON__silver_modification_lambda_fn_lambdaParamDcl = 0;
	public static int count_local__ON__silver_modification_lambda_fn_lambdaParamDef = 0;
public static final int silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHS = silver.definition.core.Init.count_syn__ON__ProductionRHS++;
public static final int silver_modification_lambda_fn_lambdaDefs__ON__silver_definition_core_ProductionRHSElem = silver.definition.core.Init.count_syn__ON__ProductionRHSElem++;
public static final int fName__ON__silver_definition_core_productionRHSElem = silver.definition.core.Init.count_local__ON__silver_definition_core_productionRHSElem++;
}
