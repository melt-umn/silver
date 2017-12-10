package copper_features.test_layout.basics;

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
		copper_features.test_layout.basics.Init.initAllStatics();

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
		copper_features.test_layout.basics.Init.init();

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
		copper_features.test_layout.basics.Init.postInit();


		common.Decorator.applyDecorators(copper_features.test_layout.basics.NBRoot.decorators, PanASDF.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_20_50.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_21_51.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_23_52.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_24_53.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_25_54.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_26_55.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_29_56.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_30_57.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_32_58.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_33_59.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_34_60.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_35_61.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Basics_sv_36_62.class);
	}

	private static void setupInheritedAttributes(){
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_20_50.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50] = "copper_features:test_layout:basics:generatedTest_Basics_sv_20_50:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_20_50.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50] = "copper_features:test_layout:basics:generatedTest_Basics_sv_20_50:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_21_51.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51] = "copper_features:test_layout:basics:generatedTest_Basics_sv_21_51:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_21_51.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51] = "copper_features:test_layout:basics:generatedTest_Basics_sv_21_51:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_23_52.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52] = "copper_features:test_layout:basics:generatedTest_Basics_sv_23_52:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_23_52.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52] = "copper_features:test_layout:basics:generatedTest_Basics_sv_23_52:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_24_53.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53] = "copper_features:test_layout:basics:generatedTest_Basics_sv_24_53:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_24_53.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53] = "copper_features:test_layout:basics:generatedTest_Basics_sv_24_53:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_25_54.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54] = "copper_features:test_layout:basics:generatedTest_Basics_sv_25_54:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_25_54.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54] = "copper_features:test_layout:basics:generatedTest_Basics_sv_25_54:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_26_55.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55] = "copper_features:test_layout:basics:generatedTest_Basics_sv_26_55:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_26_55.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55] = "copper_features:test_layout:basics:generatedTest_Basics_sv_26_55:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_29_56.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56] = "copper_features:test_layout:basics:generatedTest_Basics_sv_29_56:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_29_56.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56] = "copper_features:test_layout:basics:generatedTest_Basics_sv_29_56:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_30_57.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57] = "copper_features:test_layout:basics:generatedTest_Basics_sv_30_57:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_30_57.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57] = "copper_features:test_layout:basics:generatedTest_Basics_sv_30_57:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_32_58.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58] = "copper_features:test_layout:basics:generatedTest_Basics_sv_32_58:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_32_58.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58] = "copper_features:test_layout:basics:generatedTest_Basics_sv_32_58:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_33_59.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59] = "copper_features:test_layout:basics:generatedTest_Basics_sv_33_59:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_33_59.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59] = "copper_features:test_layout:basics:generatedTest_Basics_sv_33_59:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_34_60.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60] = "copper_features:test_layout:basics:generatedTest_Basics_sv_34_60:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_34_60.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60] = "copper_features:test_layout:basics:generatedTest_Basics_sv_34_60:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_35_61.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61] = "copper_features:test_layout:basics:generatedTest_Basics_sv_35_61:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_35_61.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61] = "copper_features:test_layout:basics:generatedTest_Basics_sv_35_61:local:expected";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_36_62.occurs_local[copper_features.test_layout.basics.Init.value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62] = "copper_features:test_layout:basics:generatedTest_Basics_sv_36_62:local:value";
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_36_62.occurs_local[copper_features.test_layout.basics.Init.expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62] = "copper_features:test_layout:basics:generatedTest_Basics_sv_36_62:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.test_layout.basics.PanASDF.initProductionAttributeDefinitions();
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_20_50.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_20_50(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_20_50()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_21_51.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_21_51(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_21_51()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_23_52.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_23_52(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_23_52()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_24_53.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_24_53(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_24_53()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_25_54.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_25_54(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_25_54()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_26_55.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_26_55(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_26_55()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_29_56.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_29_56(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_29_56()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_30_57.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_30_57(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_30_57()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_32_58.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_32_58(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_32_58()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_33_59.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_33_59(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_33_59()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_34_60.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_34_60(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_34_60()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_35_61.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_35_61(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_35_61()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		copper_features.test_layout.basics.PgeneratedTest_Basics_sv_36_62.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION copper_tests t ::= 
		// testsToPerform <- [ generatedTest_Basics_sv_36_62(,) ]
		((common.CollectionAttribute)copper_features.Pcopper_tests.localAttributes[copper_features.Init.testsToPerform__ON__copper_features_copper_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new copper_features.test_layout.basics.PgeneratedTest_Basics_sv_36_62()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_inh__ON__BRoot = 0;
	public static int count_syn__ON__BRoot = 0;
	public static int count_local__ON__copper_features_test_layout_basics_anASDF = 0;
	public static int count_local__ON__copper_features_test_layout_basics_basic_parse = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61 = 0;
	public static int count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62 = 0;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_20_50++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_21_51++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_23_52++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_24_53++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_25_54++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_26_55++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_29_56++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_30_57++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_32_58++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_33_59++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_34_60++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_35_61++;
public static final int value__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62++;
public static final int expected__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62 = copper_features.test_layout.basics.Init.count_local__ON__copper_features_test_layout_basics_generatedTest_Basics_sv_36_62++;
}
