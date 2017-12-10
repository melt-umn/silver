package stdlib.rawgraph;

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
		silver.util.raw.graph.Init.initAllStatics();
		lib.extcore.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		stdlib.rawgraph.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		stdlib.Init.init();
		silver.util.raw.treeset.Init.init();
		silver.util.raw.graph.Init.init();
		lib.extcore.Init.init();
		silver.testing.Init.init();
		stdlib.rawgraph.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		stdlib.Init.postInit();
		silver.util.raw.treeset.Init.postInit();
		silver.util.raw.graph.Init.postInit();
		lib.extcore.Init.postInit();
		silver.testing.Init.postInit();
		stdlib.rawgraph.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_36_415.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_37_416.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_39_417.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_40_418.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_41_419.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_42_420.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_43_421.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_45_422.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_46_423.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_47_424.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_49_425.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_52_426.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_53_427.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_54_428.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_55_429.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_58_430.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_59_431.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_89_432.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_90_433.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_91_434.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_92_435.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_93_436.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PgeneratedTest_TestGraph_sv_94_437.class);
	}

	private static void setupInheritedAttributes(){
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_36_415.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415] = "stdlib:rawgraph:generatedTest_TestGraph_sv_36_415:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_36_415.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415] = "stdlib:rawgraph:generatedTest_TestGraph_sv_36_415:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_37_416.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416] = "stdlib:rawgraph:generatedTest_TestGraph_sv_37_416:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_37_416.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416] = "stdlib:rawgraph:generatedTest_TestGraph_sv_37_416:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_39_417.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417] = "stdlib:rawgraph:generatedTest_TestGraph_sv_39_417:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_39_417.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417] = "stdlib:rawgraph:generatedTest_TestGraph_sv_39_417:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_40_418.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418] = "stdlib:rawgraph:generatedTest_TestGraph_sv_40_418:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_40_418.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418] = "stdlib:rawgraph:generatedTest_TestGraph_sv_40_418:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_41_419.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419] = "stdlib:rawgraph:generatedTest_TestGraph_sv_41_419:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_41_419.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419] = "stdlib:rawgraph:generatedTest_TestGraph_sv_41_419:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_42_420.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420] = "stdlib:rawgraph:generatedTest_TestGraph_sv_42_420:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_42_420.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420] = "stdlib:rawgraph:generatedTest_TestGraph_sv_42_420:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_43_421.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421] = "stdlib:rawgraph:generatedTest_TestGraph_sv_43_421:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_43_421.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421] = "stdlib:rawgraph:generatedTest_TestGraph_sv_43_421:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_45_422.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422] = "stdlib:rawgraph:generatedTest_TestGraph_sv_45_422:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_45_422.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422] = "stdlib:rawgraph:generatedTest_TestGraph_sv_45_422:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_46_423.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423] = "stdlib:rawgraph:generatedTest_TestGraph_sv_46_423:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_46_423.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423] = "stdlib:rawgraph:generatedTest_TestGraph_sv_46_423:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_47_424.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424] = "stdlib:rawgraph:generatedTest_TestGraph_sv_47_424:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_47_424.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424] = "stdlib:rawgraph:generatedTest_TestGraph_sv_47_424:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_49_425.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425] = "stdlib:rawgraph:generatedTest_TestGraph_sv_49_425:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_49_425.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425] = "stdlib:rawgraph:generatedTest_TestGraph_sv_49_425:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_52_426.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426] = "stdlib:rawgraph:generatedTest_TestGraph_sv_52_426:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_52_426.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426] = "stdlib:rawgraph:generatedTest_TestGraph_sv_52_426:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_53_427.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427] = "stdlib:rawgraph:generatedTest_TestGraph_sv_53_427:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_53_427.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427] = "stdlib:rawgraph:generatedTest_TestGraph_sv_53_427:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_54_428.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428] = "stdlib:rawgraph:generatedTest_TestGraph_sv_54_428:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_54_428.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428] = "stdlib:rawgraph:generatedTest_TestGraph_sv_54_428:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_55_429.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429] = "stdlib:rawgraph:generatedTest_TestGraph_sv_55_429:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_55_429.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429] = "stdlib:rawgraph:generatedTest_TestGraph_sv_55_429:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_58_430.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430] = "stdlib:rawgraph:generatedTest_TestGraph_sv_58_430:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_58_430.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430] = "stdlib:rawgraph:generatedTest_TestGraph_sv_58_430:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_59_431.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431] = "stdlib:rawgraph:generatedTest_TestGraph_sv_59_431:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_59_431.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431] = "stdlib:rawgraph:generatedTest_TestGraph_sv_59_431:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_89_432.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432] = "stdlib:rawgraph:generatedTest_TestGraph_sv_89_432:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_89_432.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432] = "stdlib:rawgraph:generatedTest_TestGraph_sv_89_432:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_90_433.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433] = "stdlib:rawgraph:generatedTest_TestGraph_sv_90_433:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_90_433.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433] = "stdlib:rawgraph:generatedTest_TestGraph_sv_90_433:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_91_434.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434] = "stdlib:rawgraph:generatedTest_TestGraph_sv_91_434:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_91_434.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434] = "stdlib:rawgraph:generatedTest_TestGraph_sv_91_434:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_92_435.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435] = "stdlib:rawgraph:generatedTest_TestGraph_sv_92_435:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_92_435.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435] = "stdlib:rawgraph:generatedTest_TestGraph_sv_92_435:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_93_436.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436] = "stdlib:rawgraph:generatedTest_TestGraph_sv_93_436:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_93_436.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436] = "stdlib:rawgraph:generatedTest_TestGraph_sv_93_436:local:expected";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_94_437.occurs_local[stdlib.rawgraph.Init.value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437] = "stdlib:rawgraph:generatedTest_TestGraph_sv_94_437:local:value";
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_94_437.occurs_local[stdlib.rawgraph.Init.expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437] = "stdlib:rawgraph:generatedTest_TestGraph_sv_94_437:local:expected";
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION compareInteger Integer ::= a::Integer b::Integer 
		//FUNCTION iset set:Set<Integer> ::= l::[Integer] 
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_36_415.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_36_415(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_36_415()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_37_416.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_37_416(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_37_416()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_39_417.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_39_417(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_39_417()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_40_418.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_40_418(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_40_418()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_41_419.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_41_419(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_41_419()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_42_420.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_42_420(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_42_420()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_43_421.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_43_421(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_43_421()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_45_422.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_45_422(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_45_422()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_46_423.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_46_423(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_46_423()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_47_424.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_47_424(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_47_424()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_49_425.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_49_425(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_49_425()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_52_426.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_52_426(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_52_426()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_53_427.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_53_427(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_53_427()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_54_428.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_54_428(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_54_428()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_55_429.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_55_429(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_55_429()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_58_430.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_58_430(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_58_430()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_59_431.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_59_431(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_59_431()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_89_432.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_89_432(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_89_432()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_90_433.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_90_433(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_90_433()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_91_434.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_91_434(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_91_434()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_92_435.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_92_435(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_92_435()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_93_436.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_93_436(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_93_436()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		stdlib.rawgraph.PgeneratedTest_TestGraph_sv_94_437.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION core_tests t ::= 
		// testsToPerform <- [ generatedTest_TestGraph_sv_94_437(,) ]
		((common.CollectionAttribute)stdlib.Pcore_tests.localAttributes[stdlib.Init.testsToPerform__ON__stdlib_core_tests]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)new stdlib.rawgraph.PgeneratedTest_TestGraph_sv_94_437()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
	}

	public static int count_local__ON__stdlib_rawgraph_compareInteger = 0;
	public static int count_local__ON__stdlib_rawgraph_iset = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436 = 0;
	public static int count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437 = 0;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_36_415++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_37_416++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_39_417++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_40_418++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_41_419++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_42_420++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_43_421++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_45_422++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_46_423++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_47_424++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_49_425++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_52_426++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_53_427++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_54_428++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_55_429++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_58_430++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_59_431++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_89_432++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_90_433++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_91_434++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_92_435++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_93_436++;
public static final int value__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437++;
public static final int expected__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437 = stdlib.rawgraph.Init.count_local__ON__stdlib_rawgraph_generatedTest_TestGraph_sv_94_437++;
	public static final common.Thunk<Object> e = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Pempty.invoke(stdlib.rawgraph.PcompareInteger.factory)); } };
	public static final common.Thunk<Object> g1 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)1), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)1), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)2), Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)3), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)5), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } }, stdlib.rawgraph.Init.e)); } };
	public static final common.Thunk<Object> g2 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)6), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)1), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)3), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, stdlib.rawgraph.Init.g1)); } };
	public static final common.Thunk<Object> g3 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)1), Integer.valueOf((int)2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)2), Integer.valueOf((int)3))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)3), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)4), Integer.valueOf((int)5))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)5), Integer.valueOf((int)6))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } }, stdlib.rawgraph.Init.e)); } };
	public static final common.Thunk<Object> g4 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.PtransitiveClosure.invoke(stdlib.rawgraph.Init.g3)); } };
	public static final common.Thunk<Object> g5 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.PtransitiveClosure.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.Padd.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)6), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, stdlib.rawgraph.Init.g3)); } })); } };
	public static final common.Thunk<Object> g6 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.PrepairClosure.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)6), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, stdlib.rawgraph.Init.g4)); } };
	public static final common.Thunk<Object> g7 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.PrepairClosure.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)3), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, stdlib.rawgraph.Init.g4)); } };
	public static final common.Thunk<Object> g8 = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.graph.PrepairClosure.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)3), Integer.valueOf((int)1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(Integer.valueOf((int)6), Integer.valueOf((int)4))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, stdlib.rawgraph.Init.g4)); } };
}
