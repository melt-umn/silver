package copper_features.token_pushing;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		copper_features.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		copper_features.token_pushing.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		copper_features.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		copper_features.token_pushing.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		copper_features.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		copper_features.token_pushing.Init.postInit();


		common.Decorator.applyDecorators(copper_features.token_pushing.NPushTokenRoot.decorators, PpushTokenRoot.class);
		common.Decorator.applyDecorators(copper_features.token_pushing.NContents.decorators, Pp_copper_features_token_pushing_TokenPush_sv_32_0.class);
		common.Decorator.applyDecorators(copper_features.token_pushing.NContents.decorators, Pp_copper_features_token_pushing_TokenPush_sv_33_0.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TokenPush_sv_41_43.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TokenPush_sv_42_44.class);
	}

	private static void setupInheritedAttributes(){
		copper_features.token_pushing.PgeneratedTest_TokenPush_sv_41_43.occurs_local[copper_features.token_pushing.Init.value__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43] = "copper_features:token_pushing:generatedTest_TokenPush_sv_41_43:local:value";
		copper_features.token_pushing.PgeneratedTest_TokenPush_sv_41_43.occurs_local[copper_features.token_pushing.Init.expected__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43] = "copper_features:token_pushing:generatedTest_TokenPush_sv_41_43:local:expected";
		copper_features.token_pushing.PgeneratedTest_TokenPush_sv_42_44.occurs_local[copper_features.token_pushing.Init.value__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44] = "copper_features:token_pushing:generatedTest_TokenPush_sv_42_44:local:value";
		copper_features.token_pushing.PgeneratedTest_TokenPush_sv_42_44.occurs_local[copper_features.token_pushing.Init.expected__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44] = "copper_features:token_pushing:generatedTest_TokenPush_sv_42_44:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.token_pushing.PpushTokenRoot.initProductionAttributeDefinitions();
		copper_features.token_pushing.Pp_copper_features_token_pushing_TokenPush_sv_32_0.initProductionAttributeDefinitions();
		copper_features.token_pushing.Pp_copper_features_token_pushing_TokenPush_sv_33_0.initProductionAttributeDefinitions();
		copper_features.token_pushing.PgeneratedTest_TokenPush_sv_41_43.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_TokenPush_sv_41_43(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.token_pushing.PgeneratedTest_TokenPush_sv_41_43()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.token_pushing.PgeneratedTest_TokenPush_sv_42_44.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_TokenPush_sv_42_44(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.token_pushing.PgeneratedTest_TokenPush_sv_42_44()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_inh__ON__PushTokenRoot = 0;
	public static int count_syn__ON__PushTokenRoot = 0;
	public static int count_inh__ON__Contents = 0;
	public static int count_syn__ON__Contents = 0;
	public static int count_local__ON__copper_features_token_pushing_pushTokenRoot = 0;
	public static int count_local__ON__copper_features_token_pushing_p_copper_features_token_pushing_TokenPush_sv_32_0 = 0;
	public static int count_local__ON__copper_features_token_pushing_p_copper_features_token_pushing_TokenPush_sv_33_0 = 0;
	public static int count_local__ON__copper_features_token_pushing_parse = 0;
	public static int count_local__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43 = 0;
	public static int count_local__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44 = 0;
public static final int value__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43 = copper_features.token_pushing.Init.count_local__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43++;
public static final int expected__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43 = copper_features.token_pushing.Init.count_local__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_41_43++;
public static final int value__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44 = copper_features.token_pushing.Init.count_local__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44++;
public static final int expected__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44 = copper_features.token_pushing.Init.count_local__ON__copper_features_token_pushing_generatedTest_TokenPush_sv_42_44++;
}
