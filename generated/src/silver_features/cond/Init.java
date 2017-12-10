package silver_features.cond;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver_features.cond.e.Init.initAllStatics();
		silver_features.cond.d.Init.initAllStatics();
		silver_features.cond.c.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		silver_features.Init.initAllStatics();
		silver_features.cond.b.Init.initAllStatics();
		silver_features.cond.a.Init.initAllStatics();
		silver_features.cond.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver_features.cond.e.Init.init();
		silver_features.cond.d.Init.init();
		silver_features.cond.c.Init.init();
		core.monad.Init.init();
		core.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		silver_features.Init.init();
		silver_features.cond.b.Init.init();
		silver_features.cond.a.Init.init();
		silver_features.cond.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver_features.cond.e.Init.postInit();
		silver_features.cond.d.Init.postInit();
		silver_features.cond.c.Init.postInit();
		core.monad.Init.postInit();
		core.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		silver_features.Init.postInit();
		silver_features.cond.b.Init.postInit();
		silver_features.cond.a.Init.postInit();
		silver_features.cond.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_14_190.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_17_191.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Test_sv_20_192.class);
	}

	private static void setupInheritedAttributes(){
		silver_features.cond.PgeneratedTest_Test_sv_14_190.occurs_local[silver_features.cond.Init.value__ON__silver_features_cond_generatedTest_Test_sv_14_190] = "silver_features:cond:generatedTest_Test_sv_14_190:local:value";
		silver_features.cond.PgeneratedTest_Test_sv_14_190.occurs_local[silver_features.cond.Init.expected__ON__silver_features_cond_generatedTest_Test_sv_14_190] = "silver_features:cond:generatedTest_Test_sv_14_190:local:expected";
		silver_features.cond.PgeneratedTest_Test_sv_17_191.occurs_local[silver_features.cond.Init.value__ON__silver_features_cond_generatedTest_Test_sv_17_191] = "silver_features:cond:generatedTest_Test_sv_17_191:local:value";
		silver_features.cond.PgeneratedTest_Test_sv_17_191.occurs_local[silver_features.cond.Init.expected__ON__silver_features_cond_generatedTest_Test_sv_17_191] = "silver_features:cond:generatedTest_Test_sv_17_191:local:expected";
		silver_features.cond.PgeneratedTest_Test_sv_20_192.occurs_local[silver_features.cond.Init.value__ON__silver_features_cond_generatedTest_Test_sv_20_192] = "silver_features:cond:generatedTest_Test_sv_20_192:local:value";
		silver_features.cond.PgeneratedTest_Test_sv_20_192.occurs_local[silver_features.cond.Init.expected__ON__silver_features_cond_generatedTest_Test_sv_20_192] = "silver_features:cond:generatedTest_Test_sv_20_192:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		silver_features.cond.PgeneratedTest_Test_sv_14_190.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_14_190(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.cond.PgeneratedTest_Test_sv_14_190()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.cond.PgeneratedTest_Test_sv_17_191.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_17_191(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.cond.PgeneratedTest_Test_sv_17_191()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.cond.PgeneratedTest_Test_sv_20_192.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Test_sv_20_192(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.cond.PgeneratedTest_Test_sv_20_192()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__silver_features_cond_generatedTest_Test_sv_14_190 = 0;
	public static int count_local__ON__silver_features_cond_generatedTest_Test_sv_17_191 = 0;
	public static int count_local__ON__silver_features_cond_generatedTest_Test_sv_20_192 = 0;
public static final int value__ON__silver_features_cond_generatedTest_Test_sv_14_190 = silver_features.cond.Init.count_local__ON__silver_features_cond_generatedTest_Test_sv_14_190++;
public static final int expected__ON__silver_features_cond_generatedTest_Test_sv_14_190 = silver_features.cond.Init.count_local__ON__silver_features_cond_generatedTest_Test_sv_14_190++;
public static final int value__ON__silver_features_cond_generatedTest_Test_sv_17_191 = silver_features.cond.Init.count_local__ON__silver_features_cond_generatedTest_Test_sv_17_191++;
public static final int expected__ON__silver_features_cond_generatedTest_Test_sv_17_191 = silver_features.cond.Init.count_local__ON__silver_features_cond_generatedTest_Test_sv_17_191++;
public static final int value__ON__silver_features_cond_generatedTest_Test_sv_20_192 = silver_features.cond.Init.count_local__ON__silver_features_cond_generatedTest_Test_sv_20_192++;
public static final int expected__ON__silver_features_cond_generatedTest_Test_sv_20_192 = silver_features.cond.Init.count_local__ON__silver_features_cond_generatedTest_Test_sv_20_192++;
}
