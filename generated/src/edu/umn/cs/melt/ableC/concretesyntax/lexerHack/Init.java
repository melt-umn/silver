package edu.umn.cs.melt.ableC.concretesyntax.lexerHack;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.initAllStatics();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.init();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		edu.umn.cs.melt.ableC.abstractsyntax.host.Init.postInit();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.Init.postInit();


		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.lexerHack.NIdentType.decorators, PidentType_c.class);
		common.Decorator.applyDecorators(edu.umn.cs.melt.ableC.concretesyntax.lexerHack.NIdentType.decorators, PtypenameType_c.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PidentType_c.initProductionAttributeDefinitions();
		edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PtypenameType_c.initProductionAttributeDefinitions();
		//FUNCTION openScope [[Pair<String IdentType>]] ::= l::[[Pair<String IdentType>]] 
		//FUNCTION closeScope [[Pair<String IdentType>]] ::= l::[[Pair<String IdentType>]] 
		//FUNCTION addIdentsToScope [[Pair<String IdentType>]] ::= l::[ast:Name] context::[[Pair<String IdentType>]] 
		//FUNCTION addTypenamesToScope [[Pair<String IdentType>]] ::= l::[ast:Name] context::[[Pair<String IdentType>]] 
		//FUNCTION beginFunctionScope [[Pair<String IdentType>]] ::= funName::ast:Name paramNames::Maybe<[ast:Name]> context::[[Pair<String IdentType>]] 
	}

	public static int count_inh__ON__IdentType = 0;
	public static int count_syn__ON__IdentType = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_identType_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_typenameType_c = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_openScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_closeScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_addIdentsToScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_addTypenamesToScope = 0;
	public static int count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_beginFunctionScope = 0;
	public static final common.Thunk<Object> identType = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.concretesyntax.lexerHack.NIdentType)new edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PidentType_c()); } };
	public static final common.Thunk<Object> typenameType = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.concretesyntax.lexerHack.NIdentType)new edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PtypenameType_c()); } };
}
