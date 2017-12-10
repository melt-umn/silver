
package silver.testing.bin;

// ts::Run ::= 'test' 'suite' jar::Jar_t 
public final class PrunTestSuite extends silver.testing.bin.NRun {

	public static final int i__G_2 = 0;
	public static final int i__G_1 = 1;
	public static final int i_jar = 2;


	public static final Class<?> childTypes[] = {silver.testing.bin.TTest_t.class,silver.testing.bin.TSuite_t.class,silver.testing.bin.TJar_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_runTestSuite;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.bin.NRun.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.bin.NRun.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrunTestSuite(final Object c__G_2, final Object c__G_1, final Object c_jar) {
		super();
		this.child__G_2 = c__G_2;
		this.child__G_1 = c__G_1;
		this.child_jar = c_jar;

	}

	private Object child__G_2;
	public final silver.testing.bin.TTest_t getChild__G_2() {
		return (silver.testing.bin.TTest_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child__G_1;
	public final silver.testing.bin.TSuite_t getChild__G_1() {
		return (silver.testing.bin.TSuite_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_jar;
	public final silver.testing.bin.TJar_t getChild_jar() {
		return (silver.testing.bin.TJar_t) (child_jar = common.Util.demand(child_jar));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i__G_1: return getChild__G_1();
			case i_jar: return getChild_jar();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i__G_1: return child__G_1;
			case i_jar: return child_jar;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:testing:bin:runTestSuite erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "silver:testing:bin:runTestSuite";
	}

	static void initProductionAttributeDefinitions() {
		// msgBefore = print("............................................................\n" ++ "Test Suite jar \"" ++ jar.lexeme ++ "\" in \n  " ++ ts.testFileName ++ " in directory \n  " ++ prettyDirName(ts.testFileDir,) ++ "\n", ts.ioInput.io,)
		silver.testing.bin.PrunTestSuite.localAttributes[silver.testing.bin.Init.msgBefore__ON__silver_testing_bin_runTestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("............................................................\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Test Suite jar \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.testing.bin.TJar_t)context.childAsIs(silver.testing.bin.PrunTestSuite.i_jar)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" in \n  ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" in directory \n  ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.testing.bin.PprettyDirName.invoke(context.contextInheritedLazy(silver.testing.bin.Init.silver_testing_bin_testFileDir__ON__silver_testing_bin_Run))), (common.StringCatter)(new common.StringCatter("\n"))))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NIOVal)context.inherited(silver.testing.bin.Init.silver_testing_bin_ioInput__ON__silver_testing_bin_Run)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// testSuiteResults = system("cd " ++ ts.testFileDir ++ ";" ++ "rm -f " ++ ts.testFileName ++ ".output ; " ++ " java -jar " ++ jar.lexeme ++ " >& " ++ ts.testFileName ++ ".output", msgBefore,)
		silver.testing.bin.PrunTestSuite.localAttributes[silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.Psystem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("cd ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileDir__ON__silver_testing_bin_Run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("rm -f ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".output ; ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" java -jar ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.testing.bin.TJar_t)context.childAsIs(silver.testing.bin.PrunTestSuite.i_jar)).lexeme), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" >& ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run)), (common.StringCatter)(new common.StringCatter(".output")))))))))))); } }, context.localAsIsLazy(silver.testing.bin.Init.msgBefore__ON__silver_testing_bin_runTestSuite))); } };
		// afterMsg = print(if testSuiteResults.iovalue == 0 then "all tests passed (rc = 0).\n" else toString(testSuiteResults.iovalue) ++ if testSuiteResults.iovalue == 1 then " test in suite failed.\n" else " tests in suite failed.\n", testSuiteResults.io,)
		silver.testing.bin.PrunTestSuite.localAttributes[silver.testing.bin.Init.afterMsg__ON__silver_testing_bin_runTestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Integer)context.localDecorated(silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(Integer.valueOf((int)0)) ? (new common.StringCatter("all tests passed (rc = 0).\n")) : new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.localDecorated(silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite).synthesized(core.Init.core_iovalue__ON__core_IOVal)))), (common.StringCatter)(((Integer)context.localDecorated(silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(Integer.valueOf((int)1)) ? (new common.StringCatter(" test in suite failed.\n")) : (new common.StringCatter(" tests in suite failed.\n"))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// ts.ioResult = ioval(afterMsg, testSuiteResults.iovalue,)
		silver.testing.bin.PrunTestSuite.synthesizedAttributes[silver.testing.bin.Init.silver_testing_bin_ioResult__ON__silver_testing_bin_Run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(context.localAsIsLazy(silver.testing.bin.Init.afterMsg__ON__silver_testing_bin_runTestSuite), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(silver.testing.bin.Init.testSuiteResults__ON__silver_testing_bin_runTestSuite).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } };

	}

	public static final common.NodeFactory<PrunTestSuite> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrunTestSuite> {

		@Override
		public PrunTestSuite invoke(final Object[] children, final Object[] annotations) {
			return new PrunTestSuite(children[0], children[1], children[2]);
		}
	};

}
