
package performance;

public final class Pmain extends common.FunctionNode {

	public static final int i__G_1 = 0;
	public static final int i_mainIO = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__performance_main;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pmain(final Object c__G_1, final Object c_mainIO) {
		this.child__G_1 = c__G_1;
		this.child_mainIO = c_mainIO;

	}

	private Object child__G_1;
	public final common.ConsCell getChild__G_1() {
		return (common.ConsCell) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_mainIO;
	public final Object getChild_mainIO() {
		return (Object) (child_mainIO = common.Util.demand(child_mainIO));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_mainIO: return getChild_mainIO();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_mainIO: return child_mainIO;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "performance:main";
	}

	public static core.NIOVal invoke(final Object c__G_1, final Object c_mainIO) {
		try {
		final common.DecoratedNode context = new Pmain(c__G_1, c_mainIO).decorate();
		//ioval(exit(testResults.numFailed, print("\n\n" ++ "============================================================\n" ++ "Test Results:\n" ++ testResults.msg ++ "\n\n" ++ "Passed " ++ toStringFromInteger(testResults.numPassed,) ++ " tests out of " ++ toStringFromInteger(testResults.numTests,) ++ "\n" ++ "============================================================\n" ++ "", testResults.ioOut,),), 0,)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pexit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(performance.Init.testResults__ON__performance_main).synthesized(silver.testing.Init.silver_testing_numFailed__ON__silver_testing_TestSuite)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("============================================================\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Test Results:\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(performance.Init.testResults__ON__performance_main).synthesized(silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Passed ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(performance.Init.testResults__ON__performance_main).synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" tests out of ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(performance.Init.testResults__ON__performance_main).synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("============================================================\n")), (common.StringCatter)(new common.StringCatter(""))))))))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(performance.Init.testResults__ON__performance_main).synthesized(silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite)); } })); } })); } }, Integer.valueOf((int)0))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function performance:main", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pmain.invoke(children[0], children[1]);
		}
	};
}