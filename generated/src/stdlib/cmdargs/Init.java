package stdlib.cmdargs;

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
		silver.util.cmdargs.Init.initAllStatics();
		stdlib.cmdargs.Init.initAllStatics();

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
		silver.util.cmdargs.Init.init();
		stdlib.cmdargs.Init.init();

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
		silver.util.cmdargs.Init.postInit();
		stdlib.cmdargs.Init.postInit();


		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PverboseFlag.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PsillyFlag.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PnoSillyFlag.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_43_275.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_47_276.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_51_277.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_56_278.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_60_279.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_65_280.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_69_281.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_73_282.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_77_283.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_81_284.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PincludeFlag.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_105_285.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_109_286.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_113_287.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_117_288.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_120_289.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_126_290.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_130_291.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_134_292.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_138_293.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestCmdArgs_sv_142_294.class);
	}

	private static void setupInheritedAttributes(){
		silver.util.cmdargs.NCmdArgs.occurs_syn[stdlib.cmdargs.Init.stdlib_cmdargs_isVerbose__ON__silver_util_cmdargs_CmdArgs] = "stdlib:cmdargs:isVerbose";
		silver.util.cmdargs.NCmdArgs.occurs_syn[stdlib.cmdargs.Init.stdlib_cmdargs_isSilly__ON__silver_util_cmdargs_CmdArgs] = "stdlib:cmdargs:isSilly";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_43_275:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_43_275:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_47_276.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_47_276:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_47_276.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_47_276:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_51_277.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_51_277:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_51_277.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_51_277:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_56_278.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_56_278:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_56_278.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_56_278:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_60_279.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_60_279:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_60_279.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_60_279:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_65_280.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_65_280:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_65_280.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_65_280:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_69_281.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_69_281:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_69_281.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_69_281:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_73_282.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_73_282:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_73_282.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_73_282:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_77_283.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_77_283:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_77_283.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_77_283:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_81_284.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_81_284:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_81_284.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_81_284:local:expected";
		silver.util.cmdargs.NCmdArgs.occurs_syn[stdlib.cmdargs.Init.stdlib_cmdargs_includePaths__ON__silver_util_cmdargs_CmdArgs] = "stdlib:cmdargs:includePaths";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_105_285.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_105_285:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_105_285.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_105_285:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_109_286.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_109_286:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_109_286.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_109_286:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_113_287.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_113_287:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_113_287.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_113_287:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_117_288.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_117_288:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_117_288.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_117_288:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_120_289.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_120_289:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_120_289.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_120_289:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_126_290.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_126_290:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_126_290.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_126_290:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_130_291.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_130_291:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_130_291.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_130_291:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_134_292:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_134_292:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_138_293.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_138_293:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_138_293.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_138_293:local:expected";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_142_294.occurs_local[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_142_294:local:value";
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_142_294.occurs_local[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294] = "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_142_294:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION endCmdArgs top ::= _ 
		// top.isVerbose = false
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[stdlib.cmdargs.Init.stdlib_cmdargs_isVerbose__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isSilly = true
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[stdlib.cmdargs.Init.stdlib_cmdargs_isSilly__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		stdlib.cmdargs.PverboseFlag.initProductionAttributeDefinitions();
		stdlib.cmdargs.PsillyFlag.initProductionAttributeDefinitions();
		stdlib.cmdargs.PnoSillyFlag.initProductionAttributeDefinitions();
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_43_275(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_47_276.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_47_276(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_47_276()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_51_277.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_51_277(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_51_277()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_56_278.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_56_278(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_56_278()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_60_279.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_60_279(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_60_279()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_65_280.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_65_280(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_65_280()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_69_281.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_69_281(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_69_281()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_73_282.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_73_282(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_73_282()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_77_283.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_77_283(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_77_283()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_81_284.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_81_284(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_81_284()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//ASPECT PRODUCTION endCmdArgs top ::= _ 
		// top.includePaths = []
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[stdlib.cmdargs.Init.stdlib_cmdargs_includePaths__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		stdlib.cmdargs.PincludeFlag.initProductionAttributeDefinitions();
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_105_285.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_105_285(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_105_285()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_109_286.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_109_286(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_109_286()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_113_287.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_113_287(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_113_287()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_117_288.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_117_288(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_117_288()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_120_289.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_120_289(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_120_289()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_126_290.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_126_290(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_126_290()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_130_291.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_130_291(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_130_291()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_134_292(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_138_293.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_138_293(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_138_293()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_142_294.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestCmdArgs_sv_142_294(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_142_294()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_cmdargs_verboseFlag = 0;
	public static int count_local__ON__stdlib_cmdargs_sillyFlag = 0;
	public static int count_local__ON__stdlib_cmdargs_noSillyFlag = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284 = 0;
	public static int count_local__ON__stdlib_cmdargs_includeFlag = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293 = 0;
	public static int count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294 = 0;
public static final int stdlib_cmdargs_isVerbose__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int stdlib_cmdargs_isSilly__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_47_276++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_51_277++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_56_278++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_60_279++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_65_280++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_69_281++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_73_282++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_77_283++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_81_284++;
public static final int stdlib_cmdargs_includePaths__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_105_285++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_109_286++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_113_287++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_117_288++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_120_289++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_126_290++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_130_291++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_138_293++;
public static final int value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294++;
public static final int expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294 = stdlib.cmdargs.Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_142_294++;
	public static final common.Thunk<Object> flags1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("-verbose")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(stdlib.cmdargs.PverboseFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("-silly")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(stdlib.cmdargs.PsillyFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("-nosilly")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(stdlib.cmdargs.PnoSillyFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
	public static final common.Thunk<Object> flags2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("-I")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Poption(stdlib.cmdargs.PincludeFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, stdlib.cmdargs.Init.flags1)); } };
}
