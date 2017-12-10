package silver.testing.bin;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.testing.Init.initAllStatics();
		silver.testing.bin.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.testing.Init.init();
		silver.testing.bin.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.testing.Init.postInit();
		silver.testing.bin.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.bin.NTestingResults.decorators, PtestingResults.class);
		common.Decorator.applyDecorators(silver.testing.bin.NRun.decorators, PskipRun.class);
		common.Decorator.applyDecorators(silver.testing.bin.NOptionalFail.decorators, PnoFail.class);
		common.Decorator.applyDecorators(silver.testing.bin.NOptionalFail.decorators, PdoFail.class);
		common.Decorator.applyDecorators(silver.testing.bin.NRun.decorators, Prun_alternate.class);
		common.Decorator.applyDecorators(silver.testing.bin.NRun.decorators, Prun.class);
		common.Decorator.applyDecorators(silver.testing.bin.NRun.decorators, PrunTestSuite.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PparseOnlyTestAfterCPP.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PparseOnlyTest.class);
		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PparseFailTest.class);
	}

	private static void setupInheritedAttributes(){
		silver.testing.bin.NTestingResults.occurs_syn[silver.testing.bin.Init.silver_testing_msg__ON__silver_testing_bin_TestingResults] = "silver:testing:msg";
		silver.testing.bin.NTestingResults.occurs_syn[silver.testing.bin.Init.silver_testing_numTests__ON__silver_testing_bin_TestingResults] = "silver:testing:numTests";
		silver.testing.bin.NTestingResults.occurs_syn[silver.testing.bin.Init.silver_testing_numPassed__ON__silver_testing_bin_TestingResults] = "silver:testing:numPassed";
		silver.testing.bin.NTestingResults.occurs_syn[silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults] = "silver:testing:numFailed";
		//	local attribute isDir::IOVal<Boolean>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.isDir__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.isDir__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:isDir";
		//	local attribute isF::IOVal<Boolean>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.isF__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.isF__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:isF";
		//	local attribute skip::IOVal<Boolean>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.skip__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.skip__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:skip";
		//	local attribute text::IOVal<String>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.text__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.text__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:text";
		//	local attribute parseResult::ParseResult<Run>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.parseResult__ON__silver_testing_bin_runTest] = new common.Lazy[core.NParseResult.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.parseResult__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:parseResult";
		//	local attribute r_cst::Run;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest] = new common.Lazy[silver.testing.bin.NRun.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:r_cst";
		//	local attribute parseFailure::IOVal<TestingResults>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.parseFailure__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.parseFailure__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:parseFailure";
		//	local attribute testResult::IOVal<TestingResults>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.testResult__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.testResult__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:testResult";
		//	local attribute msgAfter::IOVal<TestingResults>;
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.msgAfter__ON__silver_testing_bin_runTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTest.occurs_local[silver.testing.bin.Init.msgAfter__ON__silver_testing_bin_runTest] = "silver:testing:bin:runTest:local:msgAfter";
		silver.testing.bin.NRun.occurs_inh[silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run] = "silver:testing:bin:testFileName";
		silver.testing.bin.NRun.occurs_inh[silver.testing.bin.Init.silver_testing_bin_testFileDir__ON__silver_testing_bin_Run] = "silver:testing:bin:testFileDir";
		silver.testing.bin.NRun.occurs_inh[silver.testing.bin.Init.silver_testing_bin_ioInput__ON__silver_testing_bin_Run] = "silver:testing:bin:ioInput";
		silver.testing.bin.NRun.occurs_syn[silver.testing.bin.Init.silver_testing_bin_ioResult__ON__silver_testing_bin_Run] = "silver:testing:bin:ioResult";
		silver.testing.bin.NOptionalFail.occurs_syn[silver.testing.bin.Init.silver_testing_bin_fail__ON__silver_testing_bin_OptionalFail] = "silver:testing:bin:fail";
		silver.testing.bin.Prun.occurs_local[silver.testing.bin.Init.msgBefore__ON__silver_testing_bin_run] = "silver:testing:bin:run:local:msgBefore";
		silver.testing.bin.Prun.occurs_local[silver.testing.bin.Init.cmd__ON__silver_testing_bin_run] = "silver:testing:bin:run:local:cmd";
		//	local attribute cmdResult::IOVal<Integer>;
		silver.testing.bin.Prun.localInheritedAttributes[silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.Prun.occurs_local[silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run] = "silver:testing:bin:run:local:cmdResult";
		silver.testing.bin.PrunTestSuite.occurs_local[silver.testing.bin.Init.msgBefore__ON__silver_testing_bin_runTestSuite] = "silver:testing:bin:runTestSuite:local:msgBefore";
		//	local attribute testSuiteResults::IOVal<Integer>;
		silver.testing.bin.PrunTestSuite.localInheritedAttributes[silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PrunTestSuite.occurs_local[silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite] = "silver:testing:bin:runTestSuite:local:testSuiteResults";
		silver.testing.bin.PrunTestSuite.occurs_local[silver.testing.bin.Init.afterMsg__ON__silver_testing_bin_runTestSuite] = "silver:testing:bin:runTestSuite:local:afterMsg";
		//	local attribute newStrings::IOVal<[String]>;
		silver.testing.bin.PtraverseDirectoriesAndPerform.localInheritedAttributes[silver.testing.bin.Init.newStrings__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PtraverseDirectoriesAndPerform.occurs_local[silver.testing.bin.Init.newStrings__ON__silver_testing_bin_traverseDirectoriesAndPerform] = "silver:testing:bin:traverseDirectoriesAndPerform:local:newStrings";
		//	local attribute headIsLink::IOVal<Boolean>;
		silver.testing.bin.PtraverseDirectoriesAndPerform.localInheritedAttributes[silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PtraverseDirectoriesAndPerform.occurs_local[silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform] = "silver:testing:bin:traverseDirectoriesAndPerform:local:headIsLink";
		//	local attribute headIsDir::IOVal<Boolean>;
		silver.testing.bin.PtraverseDirectoriesAndPerform.localInheritedAttributes[silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PtraverseDirectoriesAndPerform.occurs_local[silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform] = "silver:testing:bin:traverseDirectoriesAndPerform:local:headIsDir";
		//	local attribute skipIt::IOVal<Boolean>;
		silver.testing.bin.PtraverseDirectoriesAndPerform.localInheritedAttributes[silver.testing.bin.Init.skipIt__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PtraverseDirectoriesAndPerform.occurs_local[silver.testing.bin.Init.skipIt__ON__silver_testing_bin_traverseDirectoriesAndPerform] = "silver:testing:bin:traverseDirectoriesAndPerform:local:skipIt";
		//	local attribute dirContents::IOVal<[String]>;
		silver.testing.bin.PtraverseDirectoriesAndPerform.localInheritedAttributes[silver.testing.bin.Init.dirContents__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PtraverseDirectoriesAndPerform.occurs_local[silver.testing.bin.Init.dirContents__ON__silver_testing_bin_traverseDirectoriesAndPerform] = "silver:testing:bin:traverseDirectoriesAndPerform:local:dirContents";
		//	local attribute exists::IOVal<Boolean>;
		silver.testing.bin.PparseOnlyTestAfterCPP.localInheritedAttributes[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseOnlyTestAfterCPP.occurs_local[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP] = "silver:testing:bin:parseOnlyTestAfterCPP:local:exists";
		silver.testing.bin.PparseOnlyTestAfterCPP.occurs_local[silver.testing.bin.Init.cppCommand__ON__silver_testing_bin_parseOnlyTestAfterCPP] = "silver:testing:bin:parseOnlyTestAfterCPP:local:cppCommand";
		//	local attribute mkCPPfile::IOVal<Integer>;
		silver.testing.bin.PparseOnlyTestAfterCPP.localInheritedAttributes[silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseOnlyTestAfterCPP.occurs_local[silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP] = "silver:testing:bin:parseOnlyTestAfterCPP:local:mkCPPfile";
		//	local attribute text::IOVal<String>;
		silver.testing.bin.PparseOnlyTestAfterCPP.localInheritedAttributes[silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseOnlyTestAfterCPP.occurs_local[silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTestAfterCPP] = "silver:testing:bin:parseOnlyTestAfterCPP:local:text";
		//	local attribute pr::ParseResult<a>;
		silver.testing.bin.PparseOnlyTestAfterCPP.localInheritedAttributes[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy[core.NParseResult.num_inh_attrs];
		silver.testing.bin.PparseOnlyTestAfterCPP.occurs_local[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTestAfterCPP] = "silver:testing:bin:parseOnlyTestAfterCPP:local:pr";
		//	local attribute exists::IOVal<Boolean>;
		silver.testing.bin.PparseOnlyTest.localInheritedAttributes[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseOnlyTest.occurs_local[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTest] = "silver:testing:bin:parseOnlyTest:local:exists";
		//	local attribute text::IOVal<String>;
		silver.testing.bin.PparseOnlyTest.localInheritedAttributes[silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseOnlyTest.occurs_local[silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTest] = "silver:testing:bin:parseOnlyTest:local:text";
		//	local attribute pr::ParseResult<a>;
		silver.testing.bin.PparseOnlyTest.localInheritedAttributes[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTest] = new common.Lazy[core.NParseResult.num_inh_attrs];
		silver.testing.bin.PparseOnlyTest.occurs_local[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTest] = "silver:testing:bin:parseOnlyTest:local:pr";
		//	local attribute result::Maybe<String>;
		silver.testing.bin.PparseOnlyTest.localInheritedAttributes[silver.testing.bin.Init.result__ON__silver_testing_bin_parseOnlyTest] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.testing.bin.PparseOnlyTest.occurs_local[silver.testing.bin.Init.result__ON__silver_testing_bin_parseOnlyTest] = "silver:testing:bin:parseOnlyTest:local:result";
		//	local attribute exists::IOVal<Boolean>;
		silver.testing.bin.PparseFailTest.localInheritedAttributes[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseFailTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseFailTest.occurs_local[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseFailTest] = "silver:testing:bin:parseFailTest:local:exists";
		//	local attribute text::IOVal<String>;
		silver.testing.bin.PparseFailTest.localInheritedAttributes[silver.testing.bin.Init.text__ON__silver_testing_bin_parseFailTest] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.PparseFailTest.occurs_local[silver.testing.bin.Init.text__ON__silver_testing_bin_parseFailTest] = "silver:testing:bin:parseFailTest:local:text";
		//	local attribute pr::ParseResult<a>;
		silver.testing.bin.PparseFailTest.localInheritedAttributes[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseFailTest] = new common.Lazy[core.NParseResult.num_inh_attrs];
		silver.testing.bin.PparseFailTest.occurs_local[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseFailTest] = "silver:testing:bin:parseFailTest:local:pr";
		//	local attribute runTests::IOVal<TestingResults>;
		silver.testing.bin.Pmain.localInheritedAttributes[silver.testing.bin.Init.runTests__ON__silver_testing_bin_main] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.Pmain.occurs_local[silver.testing.bin.Init.runTests__ON__silver_testing_bin_main] = "silver:testing:bin:main:local:runTests";
		//	local attribute startDir::IOVal<String>;
		silver.testing.bin.Pmain.localInheritedAttributes[silver.testing.bin.Init.startDir__ON__silver_testing_bin_main] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.testing.bin.Pmain.occurs_local[silver.testing.bin.Init.startDir__ON__silver_testing_bin_main] = "silver:testing:bin:main:local:startDir";
		silver.testing.bin.Pmain.occurs_local[silver.testing.bin.Init.initDirs__ON__silver_testing_bin_main] = "silver:testing:bin:main:local:initDirs";
	}

	private static void initProductionAttributeDefinitions(){
		silver.testing.bin.PtestingResults.initProductionAttributeDefinitions();
		//FUNCTION runTest IOVal<TestingResults> ::= absoluteFilePath::String ioIn::IOVal<TestingResults> 
		// isDir = isDirectory(absoluteFilePath, ioIn.io,)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.isDir__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisDirectory.invoke(context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath), context.childDecoratedSynthesizedLazy(silver.testing.bin.PrunTest.i_ioIn, core.Init.core_io__ON__core_IOVal))); } };
		// isF = isFile(absoluteFilePath, ioIn.io,)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.isF__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisFile.invoke(context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath), context.childDecoratedSynthesizedLazy(silver.testing.bin.PrunTest.i_ioIn, core.Init.core_io__ON__core_IOVal))); } };
		// skip = isFile(dirNameInFilePath(absoluteFilePath,) ++ "/tests.skip", ioIn.io,)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.skip__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisFile.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.PdirNameInFilePath.invoke(context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath))), (common.StringCatter)(new common.StringCatter("/tests.skip"))); } }, context.childDecoratedSynthesizedLazy(silver.testing.bin.PrunTest.i_ioIn, core.Init.core_io__ON__core_IOVal))); } };
		// text = readFile(absoluteFilePath, isF.io,)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.text__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PreadFile.invoke(context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.isF__ON__silver_testing_bin_runTest).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// parseResult = parse(text.iovalue, absoluteFilePath,)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.parseResult__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)silver.testing.bin.Pparse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.testing.bin.Init.text__ON__silver_testing_bin_runTest).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }, context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath))); } };
		// r_cst = parseResult.parseTree
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.testing.bin.NRun)context.localDecorated(silver.testing.bin.Init.parseResult__ON__silver_testing_bin_runTest).synthesized(core.Init.core_parseTree__ON__core_ParseResult)); } };
		// parseFailure = ioval(print("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" ++ "Parsing of .test file \n   " ++ absoluteFilePath ++ "\n" ++ "failed.\n" ++ parseResult.parseErrors ++ "\n" ++ "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n", text.io,), testingResults(ioIn.iovalue.numFailed + 1,),)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.parseFailure__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Parsing of .test file \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PrunTest.i_absoluteFilePath)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("failed.\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.testing.bin.Init.parseResult__ON__silver_testing_bin_runTest).synthesized(core.Init.core_parseErrors__ON__core_ParseResult)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"))))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.text__ON__silver_testing_bin_runTest).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.bin.NTestingResults)new silver.testing.bin.PtestingResults(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)((silver.testing.bin.NTestingResults)context.childDecorated(silver.testing.bin.PrunTest.i_ioIn).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults)) + Integer.valueOf((int)1)); } })); } })); } };
		// r_cst.ioInput = ioval(text.io, ioIn.iovalue.numFailed,)
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest][silver.testing.bin.Init.silver_testing_bin_ioInput__ON__silver_testing_bin_Run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.text__ON__silver_testing_bin_runTest).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((silver.testing.bin.NTestingResults)context.childDecorated(silver.testing.bin.PrunTest.i_ioIn).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults)); } })); } };
		// r_cst.testFileName = fileNameInFilePath(absoluteFilePath,)
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest][silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PfileNameInFilePath.invoke(context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath))); } };
		// r_cst.testFileDir = dirNameInFilePath(absoluteFilePath,)
		silver.testing.bin.PrunTest.localInheritedAttributes[silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest][silver.testing.bin.Init.silver_testing_bin_testFileDir__ON__silver_testing_bin_Run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PdirNameInFilePath.invoke(context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath))); } };
		// testResult = ioval(r_cst.ioResult.io, testingResults(r_cst.ioResult.iovalue,),)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.testResult__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NIOVal)context.localDecorated(silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest).synthesized(silver.testing.bin.Init.silver_testing_bin_ioResult__ON__silver_testing_bin_Run)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.bin.NTestingResults)new silver.testing.bin.PtestingResults(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((core.NIOVal)context.localDecorated(silver.testing.bin.Init.r_cst__ON__silver_testing_bin_runTest).synthesized(silver.testing.bin.Init.silver_testing_bin_ioResult__ON__silver_testing_bin_Run)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } })); } };
		// msgAfter = ioval(testResult.io, testingResults(testResult.iovalue.numFailed + ioIn.iovalue.numFailed,),)
		silver.testing.bin.PrunTest.localAttributes[silver.testing.bin.Init.msgAfter__ON__silver_testing_bin_runTest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.testResult__ON__silver_testing_bin_runTest).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.bin.NTestingResults)new silver.testing.bin.PtestingResults(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)((silver.testing.bin.NTestingResults)context.localDecorated(silver.testing.bin.Init.testResult__ON__silver_testing_bin_runTest).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults)) + ((Integer)((silver.testing.bin.NTestingResults)context.childDecorated(silver.testing.bin.PrunTest.i_ioIn).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults))); } })); } })); } };
		//FUNCTION prettyDirName String ::= dn::String 
		//FUNCTION dirSkip IOVal<Boolean> ::= absoluteFilePath::String ioIn::IO 
		silver.testing.bin.PskipRun.initProductionAttributeDefinitions();
		silver.testing.bin.PnoFail.initProductionAttributeDefinitions();
		silver.testing.bin.PdoFail.initProductionAttributeDefinitions();
		silver.testing.bin.Prun_alternate.initProductionAttributeDefinitions();
		silver.testing.bin.Prun.initProductionAttributeDefinitions();
		silver.testing.bin.PrunTestSuite.initProductionAttributeDefinitions();
		//FUNCTION printDirs IO ::= paths::[String] ioIn::IO 
		//FUNCTION printNL IOVal<a> ::= absoluteFilePath::String ioIn::IOVal<a> 
		//FUNCTION doNotSkip IOVal<Boolean> ::= absoluteFilePath::String ioIn::IO 
		//FUNCTION traverseDirectoriesAndPerform IOVal<a> ::= startDir::String paths::[String] f::(IOVal<a> ::= String IOVal<a>) skipDir::(IOVal<Boolean> ::= String IO) ioIn::IOVal<a> 
		// newStrings = if ! headIsDir.iovalue || skipIt.iovalue then ioval(headIsDir.io, [],) else ioval(dirContents.io, sortBy(stringLte, prependAll(head(paths,), dirContents.iovalue,),),)
		silver.testing.bin.PtraverseDirectoriesAndPerform.localAttributes[silver.testing.bin.Init.newStrings__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)context.localDecorated(silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_iovalue__ON__core_IOVal))) || ((Boolean)context.localDecorated(silver.testing.bin.Init.skipIt__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_iovalue__ON__core_IOVal))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.dirContents__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortBy.invoke(core.PstringLte.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.testing.bin.PprependAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.testing.bin.Init.dirContents__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } })); } }))); } };
		// headIsLink = ioval(ioIn.io, false,)
		silver.testing.bin.PtraverseDirectoriesAndPerform.localAttributes[silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(context.childDecoratedSynthesizedLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_ioIn, core.Init.core_io__ON__core_IOVal), false)); } };
		// headIsDir = isDirectory(head(paths,), headIsLink.io,)
		silver.testing.bin.PtraverseDirectoriesAndPerform.localAttributes[silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisDirectory.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// skipIt = if endsWith("/generated", head(paths,),) then ioval(headIsLink.io, true,) else if headIsLink.iovalue then ioval(headIsLink.io, true,) else if headIsDir.iovalue then skipDir(head(paths,), headIsDir.io,) else ioval(headIsDir.io, false,)
		silver.testing.bin.PtraverseDirectoriesAndPerform.localAttributes[silver.testing.bin.Init.skipIt__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.PendsWith.invoke((new common.StringCatter("/generated")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } })) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }, true)) : (((Boolean)context.localDecorated(silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }, true)) : (((Boolean)context.localDecorated(silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_iovalue__ON__core_IOVal)) ? ((core.NIOVal)((common.NodeFactory<core.NIOVal>)context.childAsIs(silver.testing.bin.PtraverseDirectoriesAndPerform.i_skipDir)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }}, null)) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }, false))))); } };
		// dirContents = listContents(head(paths,), headIsDir.io,)
		silver.testing.bin.PtraverseDirectoriesAndPerform.localAttributes[silver.testing.bin.Init.dirContents__ON__silver_testing_bin_traverseDirectoriesAndPerform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PlistContents.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		//FUNCTION prependAll [String] ::= pre::String all::[String] 
		//FUNCTION isSpecialFile Boolean ::= name::String 
		silver.testing.bin.PparseOnlyTestAfterCPP.initProductionAttributeDefinitions();
		silver.testing.bin.PparseOnlyTest.initProductionAttributeDefinitions();
		silver.testing.bin.PparseFailTest.initProductionAttributeDefinitions();
		//FUNCTION main IOVal<Integer> ::= args::[String] ioIn::IO 
		// runTests = traverseDirectoriesAndPerform(startDir.iovalue, initDirs, runTest, dirSkip, ioval(startDir.io, testingResults(0,),),)
		silver.testing.bin.Pmain.localAttributes[silver.testing.bin.Init.runTests__ON__silver_testing_bin_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)silver.testing.bin.PtraverseDirectoriesAndPerform.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.testing.bin.Init.startDir__ON__silver_testing_bin_main).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }, context.localAsIsLazy(silver.testing.bin.Init.initDirs__ON__silver_testing_bin_main), silver.testing.bin.PrunTest.factory, silver.testing.bin.PdirSkip.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.startDir__ON__silver_testing_bin_main).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.bin.NTestingResults)new silver.testing.bin.PtestingResults(Integer.valueOf((int)0))); } })); } })); } };
		// startDir = cwd(ioIn,)
		silver.testing.bin.Pmain.localAttributes[silver.testing.bin.Init.startDir__ON__silver_testing_bin_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.Pcwd.invoke(context.childAsIsLazy(silver.testing.bin.Pmain.i_ioIn))); } };
		// initDirs = map(cleanDirName, args,)
		silver.testing.bin.Pmain.localAttributes[silver.testing.bin.Init.initDirs__ON__silver_testing_bin_main] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.testing.bin.PcleanDirName.factory, context.childAsIsLazy(silver.testing.bin.Pmain.i_args))); } };
		//FUNCTION cleanDirName String ::= dirName::String 
	}

	public static int count_inh__ON__TestingResults = 0;
	public static int count_syn__ON__TestingResults = 0;
	public static int count_local__ON__silver_testing_bin_testingResults = 0;
	public static int count_local__ON__silver_testing_bin_runTest = 0;
	public static int count_local__ON__silver_testing_bin_prettyDirName = 0;
	public static int count_local__ON__silver_testing_bin_dirSkip = 0;
	public static int count_inh__ON__Run = 0;
	public static int count_syn__ON__Run = 0;
	public static int count_local__ON__silver_testing_bin_parse = 0;
	public static int count_local__ON__silver_testing_bin_skipRun = 0;
	public static int count_inh__ON__OptionalFail = 0;
	public static int count_syn__ON__OptionalFail = 0;
	public static int count_local__ON__silver_testing_bin_noFail = 0;
	public static int count_local__ON__silver_testing_bin_doFail = 0;
	public static int count_local__ON__silver_testing_bin_run_alternate = 0;
	public static int count_local__ON__silver_testing_bin_run = 0;
	public static int count_local__ON__silver_testing_bin_runTestSuite = 0;
	public static int count_local__ON__silver_testing_bin_printDirs = 0;
	public static int count_local__ON__silver_testing_bin_printNL = 0;
	public static int count_local__ON__silver_testing_bin_doNotSkip = 0;
	public static int count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform = 0;
	public static int count_local__ON__silver_testing_bin_prependAll = 0;
	public static int count_local__ON__silver_testing_bin_isSpecialFile = 0;
	public static int count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP = 0;
	public static int count_local__ON__silver_testing_bin_parseOnlyTest = 0;
	public static int count_local__ON__silver_testing_bin_parseFailTest = 0;
	public static int count_local__ON__silver_testing_bin_main = 0;
	public static int count_local__ON__silver_testing_bin_cleanDirName = 0;
public static final int silver_testing_msg__ON__silver_testing_bin_TestingResults = silver.testing.bin.Init.count_syn__ON__TestingResults++;
public static final int silver_testing_numTests__ON__silver_testing_bin_TestingResults = silver.testing.bin.Init.count_syn__ON__TestingResults++;
public static final int silver_testing_numPassed__ON__silver_testing_bin_TestingResults = silver.testing.bin.Init.count_syn__ON__TestingResults++;
public static final int silver_testing_numFailed__ON__silver_testing_bin_TestingResults = silver.testing.bin.Init.count_syn__ON__TestingResults++;
public static final int isDir__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int isF__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int skip__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int text__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int parseResult__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int r_cst__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int parseFailure__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int testResult__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int msgAfter__ON__silver_testing_bin_runTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTest++;
public static final int silver_testing_bin_testFileName__ON__silver_testing_bin_Run = silver.testing.bin.Init.count_inh__ON__Run++;
public static final int silver_testing_bin_testFileDir__ON__silver_testing_bin_Run = silver.testing.bin.Init.count_inh__ON__Run++;
public static final int silver_testing_bin_ioInput__ON__silver_testing_bin_Run = silver.testing.bin.Init.count_inh__ON__Run++;
public static final int silver_testing_bin_ioResult__ON__silver_testing_bin_Run = silver.testing.bin.Init.count_syn__ON__Run++;
public static final int silver_testing_bin_fail__ON__silver_testing_bin_OptionalFail = silver.testing.bin.Init.count_syn__ON__OptionalFail++;
public static final int msgBefore__ON__silver_testing_bin_run = silver.testing.bin.Init.count_local__ON__silver_testing_bin_run++;
public static final int cmd__ON__silver_testing_bin_run = silver.testing.bin.Init.count_local__ON__silver_testing_bin_run++;
public static final int cmdResult__ON__silver_testing_bin_run = silver.testing.bin.Init.count_local__ON__silver_testing_bin_run++;
public static final int msgBefore__ON__silver_testing_bin_runTestSuite = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTestSuite++;
public static final int testSuiteResults__ON__silver_testing_bin_runTestSuite = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTestSuite++;
public static final int afterMsg__ON__silver_testing_bin_runTestSuite = silver.testing.bin.Init.count_local__ON__silver_testing_bin_runTestSuite++;
public static final int newStrings__ON__silver_testing_bin_traverseDirectoriesAndPerform = silver.testing.bin.Init.count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform++;
public static final int headIsLink__ON__silver_testing_bin_traverseDirectoriesAndPerform = silver.testing.bin.Init.count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform++;
public static final int headIsDir__ON__silver_testing_bin_traverseDirectoriesAndPerform = silver.testing.bin.Init.count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform++;
public static final int skipIt__ON__silver_testing_bin_traverseDirectoriesAndPerform = silver.testing.bin.Init.count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform++;
public static final int dirContents__ON__silver_testing_bin_traverseDirectoriesAndPerform = silver.testing.bin.Init.count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform++;
public static final int exists__ON__silver_testing_bin_parseOnlyTestAfterCPP = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP++;
public static final int cppCommand__ON__silver_testing_bin_parseOnlyTestAfterCPP = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP++;
public static final int mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP++;
public static final int text__ON__silver_testing_bin_parseOnlyTestAfterCPP = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP++;
public static final int pr__ON__silver_testing_bin_parseOnlyTestAfterCPP = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP++;
public static final int exists__ON__silver_testing_bin_parseOnlyTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTest++;
public static final int text__ON__silver_testing_bin_parseOnlyTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTest++;
public static final int pr__ON__silver_testing_bin_parseOnlyTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTest++;
public static final int result__ON__silver_testing_bin_parseOnlyTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseOnlyTest++;
public static final int exists__ON__silver_testing_bin_parseFailTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseFailTest++;
public static final int text__ON__silver_testing_bin_parseFailTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseFailTest++;
public static final int pr__ON__silver_testing_bin_parseFailTest = silver.testing.bin.Init.count_local__ON__silver_testing_bin_parseFailTest++;
public static final int runTests__ON__silver_testing_bin_main = silver.testing.bin.Init.count_local__ON__silver_testing_bin_main++;
public static final int startDir__ON__silver_testing_bin_main = silver.testing.bin.Init.count_local__ON__silver_testing_bin_main++;
public static final int initDirs__ON__silver_testing_bin_main = silver.testing.bin.Init.count_local__ON__silver_testing_bin_main++;
}
