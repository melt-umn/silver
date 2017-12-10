
package silver.testing.bin;

public final class PprintDirs extends common.FunctionNode {

	public static final int i_paths = 0;
	public static final int i_ioIn = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_printDirs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PprintDirs(final Object c_paths, final Object c_ioIn) {
		this.child_paths = c_paths;
		this.child_ioIn = c_ioIn;

	}

	private Object child_paths;
	public final common.ConsCell getChild_paths() {
		return (common.ConsCell) (child_paths = common.Util.demand(child_paths));
	}

	private Object child_ioIn;
	public final Object getChild_ioIn() {
		return (Object) (child_ioIn = common.Util.demand(child_ioIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_paths: return getChild_paths();
			case i_ioIn: return getChild_ioIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_paths: return child_paths;
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
		return "silver:testing:bin:printDirs";
	}

	public static Object invoke(final Object c_paths, final Object c_ioIn) {
		try {
		final common.DecoratedNode context = new PprintDirs(c_paths, c_ioIn).decorate();
		//traverseDirectoriesAndPerform("", paths, printNL, doNotSkip, ioval(ioIn, 0,),).io
		return (Object)(((Object)((core.NIOVal)silver.testing.bin.PtraverseDirectoriesAndPerform.invoke((new common.StringCatter("")), context.childAsIsLazy(silver.testing.bin.PprintDirs.i_paths), silver.testing.bin.PprintNL.factory, silver.testing.bin.PdoNotSkip.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.testing.bin.PprintDirs.i_ioIn), Integer.valueOf((int)0))); } })).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:printDirs", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprintDirs.invoke(children[0], children[1]);
		}
	};
}