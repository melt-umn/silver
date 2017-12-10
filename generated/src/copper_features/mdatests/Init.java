package copper_features.mdatests;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		copper_features.mdatests.ext2.Init.initAllStatics();
		copper_features.mdatests.ext.Init.initAllStatics();
		copper_features.mdatests.host.Init.initAllStatics();
		copper_features.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		copper_features.mdatests.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		copper_features.mdatests.ext2.Init.init();
		copper_features.mdatests.ext.Init.init();
		copper_features.mdatests.host.Init.init();
		copper_features.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		copper_features.mdatests.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		copper_features.mdatests.ext2.Init.postInit();
		copper_features.mdatests.ext.Init.postInit();
		copper_features.mdatests.host.Init.postInit();
		copper_features.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		copper_features.mdatests.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_23_0.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_24_1.class);
	}

	private static void setupInheritedAttributes(){
		copper_features.mdatests.PgeneratedTest_Test_sv_23_0.occurs_local[copper_features.mdatests.Init.value__ON__copper_features_mdatests_generatedTest_Test_sv_23_0] = "copper_features:mdatests:generatedTest_Test_sv_23_0:local:value";
		copper_features.mdatests.PgeneratedTest_Test_sv_23_0.occurs_local[copper_features.mdatests.Init.expected__ON__copper_features_mdatests_generatedTest_Test_sv_23_0] = "copper_features:mdatests:generatedTest_Test_sv_23_0:local:expected";
		copper_features.mdatests.PgeneratedTest_Test_sv_24_1.occurs_local[copper_features.mdatests.Init.value__ON__copper_features_mdatests_generatedTest_Test_sv_24_1] = "copper_features:mdatests:generatedTest_Test_sv_24_1:local:value";
		copper_features.mdatests.PgeneratedTest_Test_sv_24_1.occurs_local[copper_features.mdatests.Init.expected__ON__copper_features_mdatests_generatedTest_Test_sv_24_1] = "copper_features:mdatests:generatedTest_Test_sv_24_1:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.mdatests.PgeneratedTest_Test_sv_23_0.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_23_0(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.mdatests.PgeneratedTest_Test_sv_23_0()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.mdatests.PgeneratedTest_Test_sv_24_1.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_24_1(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.mdatests.PgeneratedTest_Test_sv_24_1()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__copper_features_mdatests_shouldSucceed = 0;
	public static int count_local__ON__copper_features_mdatests_generatedTest_Test_sv_23_0 = 0;
	public static int count_local__ON__copper_features_mdatests_generatedTest_Test_sv_24_1 = 0;
public static final int value__ON__copper_features_mdatests_generatedTest_Test_sv_23_0 = copper_features.mdatests.Init.count_local__ON__copper_features_mdatests_generatedTest_Test_sv_23_0++;
public static final int expected__ON__copper_features_mdatests_generatedTest_Test_sv_23_0 = copper_features.mdatests.Init.count_local__ON__copper_features_mdatests_generatedTest_Test_sv_23_0++;
public static final int value__ON__copper_features_mdatests_generatedTest_Test_sv_24_1 = copper_features.mdatests.Init.count_local__ON__copper_features_mdatests_generatedTest_Test_sv_24_1++;
public static final int expected__ON__copper_features_mdatests_generatedTest_Test_sv_24_1 = copper_features.mdatests.Init.count_local__ON__copper_features_mdatests_generatedTest_Test_sv_24_1++;
}
