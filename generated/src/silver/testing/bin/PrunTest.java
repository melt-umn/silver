
package silver.testing.bin;

public final class PrunTest extends common.FunctionNode {

	public static final int i_absoluteFilePath = 0;
	public static final int i_ioIn = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NIOVal.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_runTest;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ioIn] = new common.Lazy[core.NIOVal.num_inh_attrs];

	}

	public PrunTest(final Object c_absoluteFilePath, final Object c_ioIn) {
		this.child_absoluteFilePath = c_absoluteFilePath;
		this.child_ioIn = c_ioIn;

	}

	private Object child_absoluteFilePath;
	public final common.StringCatter getChild_absoluteFilePath() {
		return (common.StringCatter) (child_absoluteFilePath = common.Util.demand(child_absoluteFilePath));
	}

	private Object child_ioIn;
	public final core.NIOVal getChild_ioIn() {
		return (core.NIOVal) (child_ioIn = common.Util.demand(child_ioIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_absoluteFilePath: return getChild_absoluteFilePath();
			case i_ioIn: return getChild_ioIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_absoluteFilePath: return child_absoluteFilePath;
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
		return "silver:testing:bin:runTest";
	}

	public static core.NIOVal invoke(final Object c_absoluteFilePath, final Object c_ioIn) {
		try {
		final common.DecoratedNode context = new PrunTest(c_absoluteFilePath, c_ioIn).decorate();
		//if ! endsWith(".test", absoluteFilePath,) || isDir.iovalue || skip.iovalue then ioIn else if ! isF.iovalue then ioval(exit(4, print("\n\nFile \"" ++ absoluteFilePath ++ "\" not found.\n", isF.io,),), testingResults(999,),) else if ! parseResult.parseSuccess then parseFailure else msgAfter
		return (core.NIOVal)(((((!((Boolean)core.PendsWith.invoke((new common.StringCatter(".test")), context.childAsIsLazy(silver.testing.bin.PrunTest.i_absoluteFilePath)))) || ((Boolean)context.localDecorated(silver.testing.bin.Init.isDir__ON__silver_testing_bin_runTest).synthesized(core.Init.core_iovalue__ON__core_IOVal))) || ((Boolean)context.localDecorated(silver.testing.bin.Init.skip__ON__silver_testing_bin_runTest).synthesized(core.Init.core_iovalue__ON__core_IOVal))) ? context.childDecorated(silver.testing.bin.PrunTest.i_ioIn).undecorate() : ((!((Boolean)context.localDecorated(silver.testing.bin.Init.isF__ON__silver_testing_bin_runTest).synthesized(core.Init.core_iovalue__ON__core_IOVal))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pexit.invoke(Integer.valueOf((int)4), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\nFile \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PrunTest.i_absoluteFilePath)), (common.StringCatter)(new common.StringCatter("\" not found.\n")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.isF__ON__silver_testing_bin_runTest).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.bin.NTestingResults)new silver.testing.bin.PtestingResults(Integer.valueOf((int)999))); } })) : ((!((Boolean)context.localDecorated(silver.testing.bin.Init.parseResult__ON__silver_testing_bin_runTest).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult))) ? context.localDecorated(silver.testing.bin.Init.parseFailure__ON__silver_testing_bin_runTest).undecorate() : context.localDecorated(silver.testing.bin.Init.msgAfter__ON__silver_testing_bin_runTest).undecorate()))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:runTest", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrunTest.invoke(children[0], children[1]);
		}
	};
}