package copper_features.test_layout;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		copper_features.test_layout.basics.Init.initAllStatics();
		copper_features.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		copper_features.test_layout.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		copper_features.test_layout.basics.Init.init();
		copper_features.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		copper_features.test_layout.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		copper_features.test_layout.basics.Init.postInit();
		copper_features.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		copper_features.test_layout.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_10_37.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_11_38.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_12_39.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_13_40.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_14_41.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_15_42.class);
	}

	private static void setupInheritedAttributes(){
		copper_features.test_layout.PgeneratedTest_Basics_sv_10_37.occurs_local[copper_features.test_layout.Init.value__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37] = "copper_features:test_layout:generatedTest_Basics_sv_10_37:local:value";
		copper_features.test_layout.PgeneratedTest_Basics_sv_10_37.occurs_local[copper_features.test_layout.Init.expected__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37] = "copper_features:test_layout:generatedTest_Basics_sv_10_37:local:expected";
		copper_features.test_layout.PgeneratedTest_Basics_sv_11_38.occurs_local[copper_features.test_layout.Init.value__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38] = "copper_features:test_layout:generatedTest_Basics_sv_11_38:local:value";
		copper_features.test_layout.PgeneratedTest_Basics_sv_11_38.occurs_local[copper_features.test_layout.Init.expected__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38] = "copper_features:test_layout:generatedTest_Basics_sv_11_38:local:expected";
		copper_features.test_layout.PgeneratedTest_Basics_sv_12_39.occurs_local[copper_features.test_layout.Init.value__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39] = "copper_features:test_layout:generatedTest_Basics_sv_12_39:local:value";
		copper_features.test_layout.PgeneratedTest_Basics_sv_12_39.occurs_local[copper_features.test_layout.Init.expected__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39] = "copper_features:test_layout:generatedTest_Basics_sv_12_39:local:expected";
		copper_features.test_layout.PgeneratedTest_Basics_sv_13_40.occurs_local[copper_features.test_layout.Init.value__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40] = "copper_features:test_layout:generatedTest_Basics_sv_13_40:local:value";
		copper_features.test_layout.PgeneratedTest_Basics_sv_13_40.occurs_local[copper_features.test_layout.Init.expected__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40] = "copper_features:test_layout:generatedTest_Basics_sv_13_40:local:expected";
		copper_features.test_layout.PgeneratedTest_Basics_sv_14_41.occurs_local[copper_features.test_layout.Init.value__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41] = "copper_features:test_layout:generatedTest_Basics_sv_14_41:local:value";
		copper_features.test_layout.PgeneratedTest_Basics_sv_14_41.occurs_local[copper_features.test_layout.Init.expected__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41] = "copper_features:test_layout:generatedTest_Basics_sv_14_41:local:expected";
		copper_features.test_layout.PgeneratedTest_Basics_sv_15_42.occurs_local[copper_features.test_layout.Init.value__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42] = "copper_features:test_layout:generatedTest_Basics_sv_15_42:local:value";
		copper_features.test_layout.PgeneratedTest_Basics_sv_15_42.occurs_local[copper_features.test_layout.Init.expected__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42] = "copper_features:test_layout:generatedTest_Basics_sv_15_42:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.test_layout.PgeneratedTest_Basics_sv_10_37.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_10_37(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.PgeneratedTest_Basics_sv_10_37()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.PgeneratedTest_Basics_sv_11_38.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_11_38(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.PgeneratedTest_Basics_sv_11_38()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.PgeneratedTest_Basics_sv_12_39.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_12_39(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.PgeneratedTest_Basics_sv_12_39()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.PgeneratedTest_Basics_sv_13_40.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_13_40(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.PgeneratedTest_Basics_sv_13_40()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.PgeneratedTest_Basics_sv_14_41.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_14_41(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.PgeneratedTest_Basics_sv_14_41()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.PgeneratedTest_Basics_sv_15_42.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_15_42(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.PgeneratedTest_Basics_sv_15_42()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__copper_features_test_layout_basic_parse = 0;
	public static int count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37 = 0;
	public static int count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38 = 0;
	public static int count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39 = 0;
	public static int count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40 = 0;
	public static int count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41 = 0;
	public static int count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42 = 0;
public static final int value__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37++;
public static final int expected__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_10_37++;
public static final int value__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38++;
public static final int expected__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_11_38++;
public static final int value__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39++;
public static final int expected__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_12_39++;
public static final int value__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40++;
public static final int expected__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_13_40++;
public static final int value__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41++;
public static final int expected__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_14_41++;
public static final int value__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42++;
public static final int expected__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42 = copper_features.test_layout.Init.count_local__ON__copper_features_test_layout_generatedTest_Basics_sv_15_42++;
}
