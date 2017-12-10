
package silver.testing.bin;

public final class Pmain extends common.FunctionNode {

	public static final int i_args = 0;
	public static final int i_ioIn = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_main;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pmain(final Object c_args, final Object c_ioIn) {
		this.child_args = c_args;
		this.child_ioIn = c_ioIn;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_ioIn;
	public final Object getChild_ioIn() {
		return (Object) (child_ioIn = common.Util.demand(child_ioIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();
			case i_ioIn: return getChild_ioIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;
			case i_ioIn: return child_ioIn;

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
		return "silver:testing:bin:main";
	}

	public static core.NIOVal invoke(final Object c_args, final Object c_ioIn) {
		try {
		final common.DecoratedNode context = new Pmain(c_args, c_ioIn).decorate();
		//ioval(print("============================================================\n" ++ (if runTests.iovalue.numFailed == 0 then "All tests passed. \n" else toString(runTests.iovalue.numFailed) ++ " tests failed. \n") ++ "============================================================\n", runTests.io,), runTests.iovalue.numFailed,)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("============================================================\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Integer)((silver.testing.bin.NTestingResults)context.localDecorated(silver.testing.bin.Init.runTests__ON__silver_testing_bin_main).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults)).equals(Integer.valueOf((int)0)) ? (new common.StringCatter("All tests passed. \n")) : new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)((silver.testing.bin.NTestingResults)context.localDecorated(silver.testing.bin.Init.runTests__ON__silver_testing_bin_main).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults)))), (common.StringCatter)(new common.StringCatter(" tests failed. \n")))), (common.StringCatter)(new common.StringCatter("============================================================\n")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.runTests__ON__silver_testing_bin_main).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((silver.testing.bin.NTestingResults)context.localDecorated(silver.testing.bin.Init.runTests__ON__silver_testing_bin_main).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults)); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:main", t);
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