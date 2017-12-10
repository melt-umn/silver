package silver_features.global_sub;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		silver_features.Init.initAllStatics();
		silver_features.global_sub.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		silver_features.Init.init();
		silver_features.global_sub.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		silver_features.Init.postInit();
		silver_features.global_sub.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_MoreGlobals_sv_11_143.class);
	}

	private static void setupInheritedAttributes(){
		silver_features.global_sub.PgeneratedTest_MoreGlobals_sv_11_143.occurs_local[silver_features.global_sub.Init.value__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143] = "silver_features:global_sub:generatedTest_MoreGlobals_sv_11_143:local:value";
		silver_features.global_sub.PgeneratedTest_MoreGlobals_sv_11_143.occurs_local[silver_features.global_sub.Init.expected__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143] = "silver_features:global_sub:generatedTest_MoreGlobals_sv_11_143:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		silver_features.global_sub.PgeneratedTest_MoreGlobals_sv_11_143.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_MoreGlobals_sv_11_143(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.global_sub.PgeneratedTest_MoreGlobals_sv_11_143()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143 = 0;
public static final int value__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143 = silver_features.global_sub.Init.count_local__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143++;
public static final int expected__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143 = silver_features.global_sub.Init.count_local__ON__silver_features_global_sub_generatedTest_MoreGlobals_sv_11_143++;
	public static final common.Thunk<Object> globalint1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(Integer.valueOf((int)1) + Integer.valueOf((int)2)) + Integer.valueOf((int)3)); } };
	public static final common.Thunk<Object> globalbool2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)silver_features.Init.globalbool1.eval()) && ((Boolean)silver_features.Init.globalbool1.eval())); } };
}
