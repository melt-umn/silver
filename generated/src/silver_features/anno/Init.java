package silver_features.anno;

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
		silver_features.anno.Init.initAllStatics();

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
		silver_features.anno.Init.init();

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
		silver_features.anno.Init.postInit();


		common.Decorator.applyDecorators(silver_features.anno.NAnnoNT.decorators, PanAnnoNT.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_19_193.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_21_194.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_27_195.class);
		common.Decorator.applyDecorators(silver_features.anno.NAnnoNT.decorators, PmoreAnnoNT.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_70_196.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_71_197.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_72_198.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_83_199.class);
		common.Decorator.applyDecorators(silver_features.anno.NAnnoNT2.decorators, PannoNT2.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_101_200.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_102_201.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_103_202.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_104_203.class);
		common.Decorator.applyDecorators(silver_features.anno.NAnnoNT2.decorators, PannoNT2partialAppProd.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_118_204.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_119_205.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_120_206.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_Anno_sv_121_207.class);
	}

	private static void setupInheritedAttributes(){
		silver_features.anno.PgeneratedTest_Anno_sv_19_193.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_19_193] = "silver_features:anno:generatedTest_Anno_sv_19_193:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_19_193.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_19_193] = "silver_features:anno:generatedTest_Anno_sv_19_193:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_21_194.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_21_194] = "silver_features:anno:generatedTest_Anno_sv_21_194:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_21_194.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_21_194] = "silver_features:anno:generatedTest_Anno_sv_21_194:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_27_195.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_27_195] = "silver_features:anno:generatedTest_Anno_sv_27_195:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_27_195.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_27_195] = "silver_features:anno:generatedTest_Anno_sv_27_195:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_70_196.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_70_196] = "silver_features:anno:generatedTest_Anno_sv_70_196:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_70_196.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_70_196] = "silver_features:anno:generatedTest_Anno_sv_70_196:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_71_197.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_71_197] = "silver_features:anno:generatedTest_Anno_sv_71_197:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_71_197.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_71_197] = "silver_features:anno:generatedTest_Anno_sv_71_197:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_72_198.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_72_198] = "silver_features:anno:generatedTest_Anno_sv_72_198:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_72_198.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_72_198] = "silver_features:anno:generatedTest_Anno_sv_72_198:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_83_199.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_83_199] = "silver_features:anno:generatedTest_Anno_sv_83_199:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_83_199.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_83_199] = "silver_features:anno:generatedTest_Anno_sv_83_199:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_101_200.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_101_200] = "silver_features:anno:generatedTest_Anno_sv_101_200:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_101_200.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_101_200] = "silver_features:anno:generatedTest_Anno_sv_101_200:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_102_201.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_102_201] = "silver_features:anno:generatedTest_Anno_sv_102_201:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_102_201.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_102_201] = "silver_features:anno:generatedTest_Anno_sv_102_201:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_103_202.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_103_202] = "silver_features:anno:generatedTest_Anno_sv_103_202:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_103_202.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_103_202] = "silver_features:anno:generatedTest_Anno_sv_103_202:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_104_203.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_104_203] = "silver_features:anno:generatedTest_Anno_sv_104_203:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_104_203.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_104_203] = "silver_features:anno:generatedTest_Anno_sv_104_203:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_118_204.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_118_204] = "silver_features:anno:generatedTest_Anno_sv_118_204:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_118_204.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_118_204] = "silver_features:anno:generatedTest_Anno_sv_118_204:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_119_205.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_119_205] = "silver_features:anno:generatedTest_Anno_sv_119_205:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_119_205.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_119_205] = "silver_features:anno:generatedTest_Anno_sv_119_205:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_120_206.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_120_206] = "silver_features:anno:generatedTest_Anno_sv_120_206:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_120_206.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_120_206] = "silver_features:anno:generatedTest_Anno_sv_120_206:local:expected";
		silver_features.anno.PgeneratedTest_Anno_sv_121_207.occurs_local[silver_features.anno.Init.value__ON__silver_features_anno_generatedTest_Anno_sv_121_207] = "silver_features:anno:generatedTest_Anno_sv_121_207:local:value";
		silver_features.anno.PgeneratedTest_Anno_sv_121_207.occurs_local[silver_features.anno.Init.expected__ON__silver_features_anno_generatedTest_Anno_sv_121_207] = "silver_features:anno:generatedTest_Anno_sv_121_207:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		silver_features.anno.PanAnnoNT.initProductionAttributeDefinitions();
		silver_features.anno.PgeneratedTest_Anno_sv_19_193.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_19_193(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_19_193()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_21_194.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_21_194(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_21_194()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_27_195.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_27_195(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_27_195()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//FUNCTION ordinaryFun AnnoNT ::= 
		silver_features.anno.PmoreAnnoNT.initProductionAttributeDefinitions();
		silver_features.anno.PgeneratedTest_Anno_sv_70_196.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_70_196(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_70_196()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_71_197.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_71_197(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_71_197()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_72_198.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_72_198(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_72_198()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_83_199.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_83_199(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_83_199()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PannoNT2.initProductionAttributeDefinitions();
		silver_features.anno.PgeneratedTest_Anno_sv_101_200.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_101_200(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_101_200()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_102_201.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_102_201(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_102_201()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_103_202.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_103_202(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_103_202()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_104_203.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_104_203(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_104_203()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PannoNT2partialAppProd.initProductionAttributeDefinitions();
		silver_features.anno.PgeneratedTest_Anno_sv_118_204.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_118_204(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_118_204()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_119_205.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_119_205(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_119_205()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_120_206.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_120_206(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_120_206()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		silver_features.anno.PgeneratedTest_Anno_sv_121_207.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION silver_tests t ::= 
		// testsToPerform <- [ generatedTest_Anno_sv_121_207(,) ]
		((common.CollectionAttribute)silver_features.Psilver_tests.localAttributes[silver_features.Init.testsToPerform__ON__silver_features_silver_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new silver_features.anno.PgeneratedTest_Anno_sv_121_207()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_inh__ON__AnnoNT = 0;
	public static int count_syn__ON__AnnoNT = 0;
	public static int count_local__ON__silver_features_anno_anAnnoNT = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_19_193 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_21_194 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_27_195 = 0;
	public static int count_local__ON__silver_features_anno_ordinaryFun = 0;
	public static int count_local__ON__silver_features_anno_moreAnnoNT = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_70_196 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_71_197 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_72_198 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_83_199 = 0;
	public static int count_inh__ON__AnnoNT2 = 0;
	public static int count_syn__ON__AnnoNT2 = 0;
	public static int count_local__ON__silver_features_anno_annoNT2 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_101_200 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_102_201 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_103_202 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_104_203 = 0;
	public static int count_local__ON__silver_features_anno_annoNT2partialAppProd = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_118_204 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_119_205 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_120_206 = 0;
	public static int count_local__ON__silver_features_anno_generatedTest_Anno_sv_121_207 = 0;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_19_193 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_19_193++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_19_193 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_19_193++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_21_194 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_21_194++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_21_194 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_21_194++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_27_195 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_27_195++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_27_195 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_27_195++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_70_196 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_70_196++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_70_196 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_70_196++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_71_197 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_71_197++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_71_197 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_71_197++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_72_198 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_72_198++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_72_198 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_72_198++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_83_199 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_83_199++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_83_199 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_83_199++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_101_200 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_101_200++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_101_200 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_101_200++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_102_201 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_102_201++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_102_201 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_102_201++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_103_202 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_103_202++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_103_202 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_103_202++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_104_203 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_104_203++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_104_203 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_104_203++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_118_204 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_118_204++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_118_204 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_118_204++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_119_205 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_119_205++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_119_205 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_119_205++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_120_206 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_120_206++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_120_206 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_120_206++;
public static final int value__ON__silver_features_anno_generatedTest_Anno_sv_121_207 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_121_207++;
public static final int expected__ON__silver_features_anno_generatedTest_Anno_sv_121_207 = silver_features.anno.Init.count_local__ON__silver_features_anno_generatedTest_Anno_sv_121_207++;
	public static final common.Thunk<Object> nt1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT)new silver_features.anno.PanAnnoNT(Integer.valueOf((int)1))); } };
	public static final common.Thunk<Object> dnt1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT)silver_features.anno.Init.nt1.eval()).decorate(context, (common.Lazy[])null); } };
	public static final common.Thunk<Object> intFun = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.anno.PanAnnoNT.factory.invokeNamedPartial(new int[]{0}, null, null); } };
	public static final common.Thunk<Object> nt2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT)((common.NodeFactory<silver_features.anno.NAnnoNT>)silver_features.anno.Init.intFun.eval()).invoke(new Object[]{Integer.valueOf((int)2)}, null)); } };
	public static final common.Thunk<Object> fun2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.anno.PmoreAnnoNT.factory.invokeNamedPartial(new int[]{0}, null, null); } };
	public static final common.Thunk<Object> fun3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.anno.PmoreAnnoNT.factory.invokePartial(new int[]{0}, new Object[]{(new common.StringCatter("hi"))}).invokeNamedPartial(new int[]{0}, null, null); } };
	public static final common.Thunk<Object> fun4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.anno.PmoreAnnoNT.factory.invokeNamedPartial(null, new int[]{0}, new Object[]{Integer.valueOf((int)7)}); } };
	public static final common.Thunk<Object> nt3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT)((common.NodeFactory<silver_features.anno.NAnnoNT>)silver_features.anno.Init.fun2.eval()).invoke(new Object[]{(new common.StringCatter("hi")), Integer.valueOf((int)5)}, null)); } };
	public static final common.Thunk<Object> nt4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT)((common.NodeFactory<silver_features.anno.NAnnoNT>)silver_features.anno.Init.fun3.eval()).invoke(new Object[]{Integer.valueOf((int)6)}, null)); } };
	public static final common.Thunk<Object> nt5 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT)((common.NodeFactory<silver_features.anno.NAnnoNT>)silver_features.anno.Init.fun4.eval()).invoke(new Object[]{(new common.StringCatter("str"))}, null)); } };
	public static final common.Thunk<Object> grabstr = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver_features.anno.PmoreAnnoNT) { final common.Thunk<Object> __SV_LOCAL___pv1854___sv_pv_1853_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_1855_s = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv1854___sv_pv_1853_s.eval())); } };
return ((common.StringCatter)(__SV_LOCAL_1855_s.eval())); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver_features:anno 'Anno.sv', 82, 27, 82, 61, 1994, 2028\n"))));}}.eval(context, (common.DecoratedNode)((silver_features.anno.NAnnoNT)silver_features.anno.Init.nt5.eval()).decorate(context, (common.Lazy[])null)); } };
	public static final common.Thunk<Object> annoNT2a = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT2)new silver_features.anno.PannoNT2(Integer.valueOf((int)1), (new common.StringCatter("2")))); } };
	public static final common.Thunk<Object> annoNT2b = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT2)new silver_features.anno.PannoNT2(Integer.valueOf((int)4), (new common.StringCatter("3")))); } };
	public static final common.Thunk<Object> partialApp1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.anno.PannoNT2partialAppProd.factory.invokeNamedPartial(null, new int[]{0, 1}, new Object[]{Integer.valueOf((int)5), (new common.StringCatter("6"))}); } };
	public static final common.Thunk<Object> partialApp2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return silver_features.anno.PannoNT2partialAppProd.factory.invokeNamedPartial(null, new int[]{1, 0}, new Object[]{(new common.StringCatter("7")), Integer.valueOf((int)8)}); } };
	public static final common.Thunk<Object> partialApp1val = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT2)((common.NodeFactory<silver_features.anno.NAnnoNT2>)silver_features.anno.Init.partialApp1.eval()).invoke(new Object[]{(new common.StringCatter("doesn't matter"))}, null)); } };
	public static final common.Thunk<Object> partialApp2val = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver_features.anno.NAnnoNT2)((common.NodeFactory<silver_features.anno.NAnnoNT2>)silver_features.anno.Init.partialApp2.eval()).invoke(new Object[]{(new common.StringCatter("doesn't matter"))}, null)); } };
}
