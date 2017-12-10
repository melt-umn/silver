package stdlib.deque;

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
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		silver.util.deque.Init.initAllStatics();
		stdlib.deque.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		stdlib.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		silver.util.deque.Init.init();
		stdlib.deque.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		silver.util.deque.Init.postInit();
		stdlib.deque.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_7_295.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_11_296.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_14_297.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_15_298.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_18_299.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_19_300.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_24_301.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_25_302.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_27_303.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_28_304.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_30_305.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_31_306.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_36_307.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_37_308.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_39_309.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_40_310.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_42_311.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_43_312.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_48_313.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_49_314.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_54_315.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_55_316.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_60_317.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_61_318.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestDeque_sv_65_319.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.deque.PgeneratedTest_TestDeque_sv_7_295.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295] = "stdlib:deque:generatedTest_TestDeque_sv_7_295:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_7_295.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295] = "stdlib:deque:generatedTest_TestDeque_sv_7_295:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_11_296.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296] = "stdlib:deque:generatedTest_TestDeque_sv_11_296:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_11_296.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296] = "stdlib:deque:generatedTest_TestDeque_sv_11_296:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_14_297.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297] = "stdlib:deque:generatedTest_TestDeque_sv_14_297:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_14_297.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297] = "stdlib:deque:generatedTest_TestDeque_sv_14_297:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_15_298.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298] = "stdlib:deque:generatedTest_TestDeque_sv_15_298:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_15_298.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298] = "stdlib:deque:generatedTest_TestDeque_sv_15_298:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_18_299.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299] = "stdlib:deque:generatedTest_TestDeque_sv_18_299:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_18_299.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299] = "stdlib:deque:generatedTest_TestDeque_sv_18_299:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_19_300.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300] = "stdlib:deque:generatedTest_TestDeque_sv_19_300:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_19_300.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300] = "stdlib:deque:generatedTest_TestDeque_sv_19_300:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_24_301.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301] = "stdlib:deque:generatedTest_TestDeque_sv_24_301:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_24_301.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301] = "stdlib:deque:generatedTest_TestDeque_sv_24_301:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_25_302.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302] = "stdlib:deque:generatedTest_TestDeque_sv_25_302:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_25_302.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302] = "stdlib:deque:generatedTest_TestDeque_sv_25_302:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_27_303.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303] = "stdlib:deque:generatedTest_TestDeque_sv_27_303:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_27_303.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303] = "stdlib:deque:generatedTest_TestDeque_sv_27_303:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_28_304.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304] = "stdlib:deque:generatedTest_TestDeque_sv_28_304:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_28_304.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304] = "stdlib:deque:generatedTest_TestDeque_sv_28_304:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_30_305.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305] = "stdlib:deque:generatedTest_TestDeque_sv_30_305:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_30_305.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305] = "stdlib:deque:generatedTest_TestDeque_sv_30_305:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_31_306.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306] = "stdlib:deque:generatedTest_TestDeque_sv_31_306:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_31_306.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306] = "stdlib:deque:generatedTest_TestDeque_sv_31_306:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_36_307.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307] = "stdlib:deque:generatedTest_TestDeque_sv_36_307:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_36_307.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307] = "stdlib:deque:generatedTest_TestDeque_sv_36_307:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_37_308.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308] = "stdlib:deque:generatedTest_TestDeque_sv_37_308:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_37_308.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308] = "stdlib:deque:generatedTest_TestDeque_sv_37_308:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_39_309.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309] = "stdlib:deque:generatedTest_TestDeque_sv_39_309:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_39_309.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309] = "stdlib:deque:generatedTest_TestDeque_sv_39_309:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_40_310.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310] = "stdlib:deque:generatedTest_TestDeque_sv_40_310:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_40_310.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310] = "stdlib:deque:generatedTest_TestDeque_sv_40_310:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_42_311.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311] = "stdlib:deque:generatedTest_TestDeque_sv_42_311:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_42_311.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311] = "stdlib:deque:generatedTest_TestDeque_sv_42_311:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_43_312.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312] = "stdlib:deque:generatedTest_TestDeque_sv_43_312:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_43_312.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312] = "stdlib:deque:generatedTest_TestDeque_sv_43_312:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_48_313.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313] = "stdlib:deque:generatedTest_TestDeque_sv_48_313:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_48_313.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313] = "stdlib:deque:generatedTest_TestDeque_sv_48_313:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_49_314.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314] = "stdlib:deque:generatedTest_TestDeque_sv_49_314:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_49_314.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314] = "stdlib:deque:generatedTest_TestDeque_sv_49_314:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_54_315.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315] = "stdlib:deque:generatedTest_TestDeque_sv_54_315:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_54_315.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315] = "stdlib:deque:generatedTest_TestDeque_sv_54_315:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_55_316.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316] = "stdlib:deque:generatedTest_TestDeque_sv_55_316:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_55_316.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316] = "stdlib:deque:generatedTest_TestDeque_sv_55_316:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_60_317.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317] = "stdlib:deque:generatedTest_TestDeque_sv_60_317:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_60_317.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317] = "stdlib:deque:generatedTest_TestDeque_sv_60_317:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_61_318.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318] = "stdlib:deque:generatedTest_TestDeque_sv_61_318:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_61_318.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318] = "stdlib:deque:generatedTest_TestDeque_sv_61_318:local:expected";
		stdlib.deque.PgeneratedTest_TestDeque_sv_65_319.occurs_local[stdlib.deque.Init.value__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319] = "stdlib:deque:generatedTest_TestDeque_sv_65_319:local:value";
		stdlib.deque.PgeneratedTest_TestDeque_sv_65_319.occurs_local[stdlib.deque.Init.expected__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319] = "stdlib:deque:generatedTest_TestDeque_sv_65_319:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		stdlib.deque.PgeneratedTest_TestDeque_sv_7_295.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_7_295(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_7_295()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_11_296.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_11_296(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_11_296()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_14_297.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_14_297(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_14_297()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_15_298.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_15_298(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_15_298()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_18_299.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_18_299(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_18_299()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_19_300.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_19_300(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_19_300()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_24_301.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_24_301(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_24_301()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_25_302.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_25_302(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_25_302()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_27_303.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_27_303(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_27_303()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_28_304.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_28_304(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_28_304()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_30_305.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_30_305(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_30_305()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_31_306.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_31_306(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_31_306()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_36_307.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_36_307(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_36_307()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_37_308.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_37_308(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_37_308()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_39_309.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_39_309(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_39_309()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_40_310.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_40_310(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_40_310()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_42_311.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_42_311(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_42_311()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_43_312.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_43_312(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_43_312()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_48_313.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_48_313(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_48_313()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_49_314.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_49_314(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_49_314()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_54_315.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_54_315(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_54_315()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_55_316.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_55_316(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_55_316()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_60_317.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_60_317(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_60_317()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_61_318.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_61_318(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_61_318()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.deque.PgeneratedTest_TestDeque_sv_65_319.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestDeque_sv_65_319(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.deque.PgeneratedTest_TestDeque_sv_65_319()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318 = 0;
	public static int count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319 = 0;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_7_295++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_11_296++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_14_297++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_15_298++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_18_299++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_19_300++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_24_301++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_25_302++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_27_303++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_28_304++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_30_305++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_31_306++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_36_307++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_37_308++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_39_309++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_40_310++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_42_311++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_43_312++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_48_313++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_49_314++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_54_315++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_55_316++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_60_317++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_61_318++;
public static final int value__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319++;
public static final int expected__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319 = stdlib.deque.Init.count_local__ON__stdlib_deque_generatedTest_TestDeque_sv_65_319++;
	public static final common.Thunk<Object> dq1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqEmpty.invoke()); } })); } };
	public static final common.Thunk<Object> dq2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)2), stdlib.deque.Init.dq1)); } };
	public static final common.Thunk<Object> dq3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqSnoc.invoke(stdlib.deque.Init.dq2, Integer.valueOf((int)3))); } };
	public static final common.Thunk<Object> dq4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)12), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)11), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)10), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)9), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)8), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)7), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)6), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)5), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqCons.invoke(Integer.valueOf((int)4), stdlib.deque.Init.dq3)); } })); } })); } })); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> dq5 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqInit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqInit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqInit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqInit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqInit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqInit.invoke(stdlib.deque.Init.dq4)); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> dq6 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(stdlib.deque.Init.dq4)); } })); } })); } })); } })); } })); } };
	public static final common.Thunk<Object> dq7 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(stdlib.deque.Init.dq5)); } })); } })); } })); } })); } })); } };
}
