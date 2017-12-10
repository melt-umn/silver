package silver.testing;

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

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.testing.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.testing.Init.postInit();


		common.Decorator.applyDecorators(silver.testing.NTest.decorators, PdefTest.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, PtestsCollect.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, Ptests.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, PtestNone.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, PtestCons.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, PtestSuiteNone.class);
		common.Decorator.applyDecorators(silver.testing.NTestSuite.decorators, PtestSuiteSeq.class);
	}

	private static void setupInheritedAttributes(){
		silver.testing.NTest.occurs_syn[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = "silver:testing:msg";
		silver.testing.NTest.occurs_syn[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = "silver:testing:pass";
		silver.testing.NTest.occurs_inh[silver.testing.Init.silver_testing_ioIn__ON__silver_testing_Test] = "silver:testing:ioIn";
		silver.testing.NTest.occurs_syn[silver.testing.Init.silver_testing_ioOut__ON__silver_testing_Test] = "silver:testing:ioOut";
		silver.testing.NTestSuite.occurs_syn[silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite] = "silver:testing:msg";
		silver.testing.NTestSuite.occurs_syn[silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite] = "silver:testing:numTests";
		silver.testing.NTestSuite.occurs_syn[silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite] = "silver:testing:numPassed";
		silver.testing.NTestSuite.occurs_syn[silver.testing.Init.silver_testing_numFailed__ON__silver_testing_TestSuite] = "silver:testing:numFailed";
		silver.testing.NTestSuite.occurs_inh[silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = "silver:testing:ioIn";
		silver.testing.NTestSuite.occurs_syn[silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite] = "silver:testing:ioOut";
	}

	private static void initProductionAttributeDefinitions(){
		silver.testing.PdefTest.initProductionAttributeDefinitions();
		silver.testing.PtestsCollect.initProductionAttributeDefinitions();
		silver.testing.Ptests.initProductionAttributeDefinitions();
		//FUNCTION testsAsNT TestSuite ::= ts::[Test] 
		//FUNCTION consolidateTestSuite TestSuite ::= ts::[TestSuite] 
		silver.testing.PtestNone.initProductionAttributeDefinitions();
		silver.testing.PtestCons.initProductionAttributeDefinitions();
		silver.testing.PtestSuiteNone.initProductionAttributeDefinitions();
		silver.testing.PtestSuiteSeq.initProductionAttributeDefinitions();
		//FUNCTION repeatTestTimes Boolean ::= f::(Boolean ::=) times::Integer 
	}

	public static int count_inh__ON__Test = 0;
	public static int count_syn__ON__Test = 0;
	public static int count_inh__ON__TestSuite = 0;
	public static int count_syn__ON__TestSuite = 0;
	public static int count_local__ON__silver_testing_defTest = 0;
	public static int count_local__ON__silver_testing_testsCollect = 0;
	public static int count_local__ON__silver_testing_tests = 0;
	public static int count_local__ON__silver_testing_testsAsNT = 0;
	public static int count_local__ON__silver_testing_consolidateTestSuite = 0;
	public static int count_local__ON__silver_testing_testNone = 0;
	public static int count_local__ON__silver_testing_testCons = 0;
	public static int count_local__ON__silver_testing_testSuiteNone = 0;
	public static int count_local__ON__silver_testing_testSuiteSeq = 0;
	public static int count_local__ON__silver_testing_repeatTestTimes = 0;
public static final int silver_testing_msg__ON__silver_testing_Test = silver.testing.Init.count_syn__ON__Test++;
public static final int silver_testing_pass__ON__silver_testing_Test = silver.testing.Init.count_syn__ON__Test++;
public static final int silver_testing_ioIn__ON__silver_testing_Test = silver.testing.Init.count_inh__ON__Test++;
public static final int silver_testing_ioOut__ON__silver_testing_Test = silver.testing.Init.count_syn__ON__Test++;
public static final int silver_testing_msg__ON__silver_testing_TestSuite = silver.testing.Init.count_syn__ON__TestSuite++;
public static final int silver_testing_numTests__ON__silver_testing_TestSuite = silver.testing.Init.count_syn__ON__TestSuite++;
public static final int silver_testing_numPassed__ON__silver_testing_TestSuite = silver.testing.Init.count_syn__ON__TestSuite++;
public static final int silver_testing_numFailed__ON__silver_testing_TestSuite = silver.testing.Init.count_syn__ON__TestSuite++;
public static final int silver_testing_ioIn__ON__silver_testing_TestSuite = silver.testing.Init.count_inh__ON__TestSuite++;
public static final int silver_testing_ioOut__ON__silver_testing_TestSuite = silver.testing.Init.count_syn__ON__TestSuite++;
}
