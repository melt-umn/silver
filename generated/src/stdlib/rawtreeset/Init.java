package stdlib.rawtreeset;

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
		silver.util.raw.treeset.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		stdlib.rawtreeset.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		stdlib.Init.init();
		silver.util.raw.treeset.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		stdlib.rawtreeset.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		silver.util.raw.treeset.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		stdlib.rawtreeset.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_13_360.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_17_361.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_21_362.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_23_363.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_25_364.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_27_365.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_28_366.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_30_367.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_31_368.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_33_369.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_34_370.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_36_371.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_37_372.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_38_373.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_40_374.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_41_375.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_SetTests_sv_42_376.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_13_360.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360] = "stdlib:rawtreeset:generatedTest_SetTests_sv_13_360:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_13_360.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360] = "stdlib:rawtreeset:generatedTest_SetTests_sv_13_360:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_17_361.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361] = "stdlib:rawtreeset:generatedTest_SetTests_sv_17_361:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_17_361.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361] = "stdlib:rawtreeset:generatedTest_SetTests_sv_17_361:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_21_362.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362] = "stdlib:rawtreeset:generatedTest_SetTests_sv_21_362:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_21_362.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362] = "stdlib:rawtreeset:generatedTest_SetTests_sv_21_362:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_23_363.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363] = "stdlib:rawtreeset:generatedTest_SetTests_sv_23_363:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_23_363.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363] = "stdlib:rawtreeset:generatedTest_SetTests_sv_23_363:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_25_364.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364] = "stdlib:rawtreeset:generatedTest_SetTests_sv_25_364:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_25_364.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364] = "stdlib:rawtreeset:generatedTest_SetTests_sv_25_364:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_27_365.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365] = "stdlib:rawtreeset:generatedTest_SetTests_sv_27_365:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_27_365.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365] = "stdlib:rawtreeset:generatedTest_SetTests_sv_27_365:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_28_366.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366] = "stdlib:rawtreeset:generatedTest_SetTests_sv_28_366:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_28_366.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366] = "stdlib:rawtreeset:generatedTest_SetTests_sv_28_366:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_30_367.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367] = "stdlib:rawtreeset:generatedTest_SetTests_sv_30_367:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_30_367.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367] = "stdlib:rawtreeset:generatedTest_SetTests_sv_30_367:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_31_368.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368] = "stdlib:rawtreeset:generatedTest_SetTests_sv_31_368:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_31_368.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368] = "stdlib:rawtreeset:generatedTest_SetTests_sv_31_368:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_33_369.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369] = "stdlib:rawtreeset:generatedTest_SetTests_sv_33_369:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_33_369.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369] = "stdlib:rawtreeset:generatedTest_SetTests_sv_33_369:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_34_370.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370] = "stdlib:rawtreeset:generatedTest_SetTests_sv_34_370:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_34_370.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370] = "stdlib:rawtreeset:generatedTest_SetTests_sv_34_370:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_36_371.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371] = "stdlib:rawtreeset:generatedTest_SetTests_sv_36_371:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_36_371.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371] = "stdlib:rawtreeset:generatedTest_SetTests_sv_36_371:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372] = "stdlib:rawtreeset:generatedTest_SetTests_sv_37_372:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372] = "stdlib:rawtreeset:generatedTest_SetTests_sv_37_372:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_38_373.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373] = "stdlib:rawtreeset:generatedTest_SetTests_sv_38_373:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_38_373.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373] = "stdlib:rawtreeset:generatedTest_SetTests_sv_38_373:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_40_374.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374] = "stdlib:rawtreeset:generatedTest_SetTests_sv_40_374:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_40_374.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374] = "stdlib:rawtreeset:generatedTest_SetTests_sv_40_374:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_41_375.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375] = "stdlib:rawtreeset:generatedTest_SetTests_sv_41_375:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_41_375.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375] = "stdlib:rawtreeset:generatedTest_SetTests_sv_41_375:local:expected";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_42_376.occurs_local[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376] = "stdlib:rawtreeset:generatedTest_SetTests_sv_42_376:local:value";
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_42_376.occurs_local[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376] = "stdlib:rawtreeset:generatedTest_SetTests_sv_42_376:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION integerCompare Integer ::= l::Integer r::Integer 
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_13_360.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_13_360(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_13_360()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_17_361.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_17_361(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_17_361()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_21_362.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_21_362(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_21_362()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_23_363.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_23_363(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_23_363()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_25_364.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_25_364(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_25_364()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_27_365.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_27_365(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_27_365()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_28_366.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_28_366(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_28_366()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_30_367.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_30_367(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_30_367()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_31_368.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_31_368(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_31_368()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_33_369.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_33_369(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_33_369()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_34_370.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_34_370(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_34_370()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_36_371.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_36_371(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_36_371()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_37_372(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_38_373.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_38_373(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_38_373()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_40_374.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_40_374(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_40_374()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_41_375.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_41_375(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_41_375()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_42_376.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_SetTests_sv_42_376(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawtreeset.PgeneratedTest_SetTests_sv_42_376()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_rawtreeset_integerCompare = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375 = 0;
	public static int count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376 = 0;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_13_360++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_17_361++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_21_362++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_23_363++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_25_364++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_27_365++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_28_366++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_30_367++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_31_368++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_33_369++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_34_370++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_36_371++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_38_373++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_40_374++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_41_375++;
public static final int value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376++;
public static final int expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376 = stdlib.rawtreeset.Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_42_376++;
	public static final common.Thunk<Object> set1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)8), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Pempty.invoke(stdlib.rawtreeset.PintegerCompare.factory)); } })); } };
	public static final common.Thunk<Object> set2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)-1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)7), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)11), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Pempty.invoke(stdlib.rawtreeset.PintegerCompare.factory)); } })); } };
}
