package stdlib.fixedmap;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		stdlib.Init.initAllStatics();
		silver.util.fixedmap.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		stdlib.fixedmap.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		stdlib.Init.init();
		silver.util.fixedmap.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		stdlib.fixedmap.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		silver.util.fixedmap.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		stdlib.fixedmap.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_12_250.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_13_251.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_14_252.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_15_253.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_16_254.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_17_255.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_18_256.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_19_257.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_20_258.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_22_259.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_23_260.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_24_261.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_25_262.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_26_263.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_27_264.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_28_265.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_37_266.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_38_267.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_39_268.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_40_269.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_42_270.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_43_271.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_44_272.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_45_273.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_FixedMap_sv_47_274.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_12_250.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250] = "stdlib:fixedmap:generatedTest_FixedMap_sv_12_250:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_12_250.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250] = "stdlib:fixedmap:generatedTest_FixedMap_sv_12_250:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_13_251.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251] = "stdlib:fixedmap:generatedTest_FixedMap_sv_13_251:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_13_251.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251] = "stdlib:fixedmap:generatedTest_FixedMap_sv_13_251:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_14_252.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252] = "stdlib:fixedmap:generatedTest_FixedMap_sv_14_252:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_14_252.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252] = "stdlib:fixedmap:generatedTest_FixedMap_sv_14_252:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_15_253.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253] = "stdlib:fixedmap:generatedTest_FixedMap_sv_15_253:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_15_253.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253] = "stdlib:fixedmap:generatedTest_FixedMap_sv_15_253:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_16_254.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254] = "stdlib:fixedmap:generatedTest_FixedMap_sv_16_254:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_16_254.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254] = "stdlib:fixedmap:generatedTest_FixedMap_sv_16_254:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_17_255.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255] = "stdlib:fixedmap:generatedTest_FixedMap_sv_17_255:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_17_255.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255] = "stdlib:fixedmap:generatedTest_FixedMap_sv_17_255:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_18_256.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256] = "stdlib:fixedmap:generatedTest_FixedMap_sv_18_256:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_18_256.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256] = "stdlib:fixedmap:generatedTest_FixedMap_sv_18_256:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_19_257.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257] = "stdlib:fixedmap:generatedTest_FixedMap_sv_19_257:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_19_257.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257] = "stdlib:fixedmap:generatedTest_FixedMap_sv_19_257:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_20_258.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258] = "stdlib:fixedmap:generatedTest_FixedMap_sv_20_258:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_20_258.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258] = "stdlib:fixedmap:generatedTest_FixedMap_sv_20_258:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_22_259.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259] = "stdlib:fixedmap:generatedTest_FixedMap_sv_22_259:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_22_259.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259] = "stdlib:fixedmap:generatedTest_FixedMap_sv_22_259:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_23_260.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260] = "stdlib:fixedmap:generatedTest_FixedMap_sv_23_260:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_23_260.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260] = "stdlib:fixedmap:generatedTest_FixedMap_sv_23_260:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_24_261.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261] = "stdlib:fixedmap:generatedTest_FixedMap_sv_24_261:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_24_261.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261] = "stdlib:fixedmap:generatedTest_FixedMap_sv_24_261:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_25_262.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262] = "stdlib:fixedmap:generatedTest_FixedMap_sv_25_262:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_25_262.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262] = "stdlib:fixedmap:generatedTest_FixedMap_sv_25_262:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_26_263.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263] = "stdlib:fixedmap:generatedTest_FixedMap_sv_26_263:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_26_263.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263] = "stdlib:fixedmap:generatedTest_FixedMap_sv_26_263:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_27_264.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264] = "stdlib:fixedmap:generatedTest_FixedMap_sv_27_264:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_27_264.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264] = "stdlib:fixedmap:generatedTest_FixedMap_sv_27_264:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_28_265.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265] = "stdlib:fixedmap:generatedTest_FixedMap_sv_28_265:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_28_265.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265] = "stdlib:fixedmap:generatedTest_FixedMap_sv_28_265:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_37_266.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266] = "stdlib:fixedmap:generatedTest_FixedMap_sv_37_266:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_37_266.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266] = "stdlib:fixedmap:generatedTest_FixedMap_sv_37_266:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_38_267.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267] = "stdlib:fixedmap:generatedTest_FixedMap_sv_38_267:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_38_267.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267] = "stdlib:fixedmap:generatedTest_FixedMap_sv_38_267:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_39_268.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268] = "stdlib:fixedmap:generatedTest_FixedMap_sv_39_268:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_39_268.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268] = "stdlib:fixedmap:generatedTest_FixedMap_sv_39_268:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_40_269.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269] = "stdlib:fixedmap:generatedTest_FixedMap_sv_40_269:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_40_269.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269] = "stdlib:fixedmap:generatedTest_FixedMap_sv_40_269:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_42_270.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270] = "stdlib:fixedmap:generatedTest_FixedMap_sv_42_270:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_42_270.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270] = "stdlib:fixedmap:generatedTest_FixedMap_sv_42_270:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_43_271.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271] = "stdlib:fixedmap:generatedTest_FixedMap_sv_43_271:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_43_271.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271] = "stdlib:fixedmap:generatedTest_FixedMap_sv_43_271:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_44_272.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272] = "stdlib:fixedmap:generatedTest_FixedMap_sv_44_272:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_44_272.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272] = "stdlib:fixedmap:generatedTest_FixedMap_sv_44_272:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_45_273.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273] = "stdlib:fixedmap:generatedTest_FixedMap_sv_45_273:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_45_273.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273] = "stdlib:fixedmap:generatedTest_FixedMap_sv_45_273:local:expected";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_47_274.occurs_local[stdlib.fixedmap.Init.value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274] = "stdlib:fixedmap:generatedTest_FixedMap_sv_47_274:local:value";
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_47_274.occurs_local[stdlib.fixedmap.Init.expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274] = "stdlib:fixedmap:generatedTest_FixedMap_sv_47_274:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_12_250.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_12_250(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_12_250()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_13_251.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_13_251(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_13_251()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_14_252.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_14_252(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_14_252()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_15_253.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_15_253(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_15_253()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_16_254.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_16_254(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_16_254()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_17_255.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_17_255(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_17_255()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_18_256.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_18_256(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_18_256()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_19_257.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_19_257(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_19_257()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_20_258.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_20_258(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_20_258()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_22_259.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_22_259(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_22_259()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_23_260.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_23_260(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_23_260()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_24_261.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_24_261(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_24_261()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_25_262.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_25_262(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_25_262()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_26_263.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_26_263(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_26_263()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_27_264.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_27_264(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_27_264()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_28_265.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_28_265(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_28_265()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_37_266.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_37_266(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_37_266()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_38_267.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_38_267(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_38_267()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_39_268.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_39_268(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_39_268()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_40_269.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_40_269(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_40_269()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_42_270.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_42_270(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_42_270()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_43_271.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_43_271(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_43_271()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_44_272.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_44_272(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_44_272()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_45_273.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_45_273(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_45_273()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.fixedmap.PgeneratedTest_FixedMap_sv_47_274.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_FixedMap_sv_47_274(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.fixedmap.PgeneratedTest_FixedMap_sv_47_274()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273 = 0;
	public static int count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274 = 0;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_12_250++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_13_251++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_14_252++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_15_253++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_16_254++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_17_255++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_18_256++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_19_257++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_20_258++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_22_259++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_23_260++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_24_261++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_25_262++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_26_263++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_27_264++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_28_265++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_37_266++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_38_267++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_39_268++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_40_269++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_42_270++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_43_271++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_44_272++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_45_273++;
public static final int value__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274++;
public static final int expected__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274 = stdlib.fixedmap.Init.count_local__ON__stdlib_fixedmap_generatedTest_FixedMap_sv_47_274++;
	public static final common.Thunk<Object> t2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.fixedmap.NMap)silver.util.fixedmap.Pcreate.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("g")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("f")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("d")), Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("s")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("a")), Integer.valueOf((int)5))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("p")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("q")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("h")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("i")), Integer.valueOf((int)0))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("q")), Integer.valueOf((int)7))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("1")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("2")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("3")), Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("4")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("5")), Integer.valueOf((int)5))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("6")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("1")), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("2")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("0")), Integer.valueOf((int)0))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("7")), Integer.valueOf((int)7))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> l1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hi")), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hello")), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("hola")), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("quepasa")), Integer.valueOf((int)11))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> l2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.fixedmap.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.fixedmap.NMap)silver.util.fixedmap.Pcreate.invoke(stdlib.fixedmap.Init.l1)); } })); } };
}
