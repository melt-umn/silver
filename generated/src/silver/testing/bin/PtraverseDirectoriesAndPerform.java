
package silver.testing.bin;

public final class PtraverseDirectoriesAndPerform extends common.FunctionNode {

	public static final int i_startDir = 0;
	public static final int i_paths = 1;
	public static final int i_f = 2;
	public static final int i_skipDir = 3;
	public static final int i_ioIn = 4;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.NodeFactory.class,common.NodeFactory.class,core.NIOVal.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_traverseDirectoriesAndPerform;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ioIn] = new common.Lazy[core.NIOVal.num_inh_attrs];

	}

	public PtraverseDirectoriesAndPerform(final Object c_startDir, final Object c_paths, final Object c_f, final Object c_skipDir, final Object c_ioIn) {
		this.child_startDir = c_startDir;
		this.child_paths = c_paths;
		this.child_f = c_f;
		this.child_skipDir = c_skipDir;
		this.child_ioIn = c_ioIn;

	}

	private Object child_startDir;
	public final common.StringCatter getChild_startDir() {
		return (common.StringCatter) (child_startDir = common.Util.demand(child_startDir));
	}

	private Object child_paths;
	public final common.ConsCell getChild_paths() {
		return (common.ConsCell) (child_paths = common.Util.demand(child_paths));
	}

	private Object child_f;
	public final common.NodeFactory<core.NIOVal> getChild_f() {
		return (common.NodeFactory<core.NIOVal>) (child_f = common.Util.demand(child_f));
	}

	private Object child_skipDir;
	public final common.NodeFactory<core.NIOVal> getChild_skipDir() {
		return (common.NodeFactory<core.NIOVal>) (child_skipDir = common.Util.demand(child_skipDir));
	}

	private Object child_ioIn;
	public final core.NIOVal getChild_ioIn() {
		return (core.NIOVal) (child_ioIn = common.Util.demand(child_ioIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_startDir: return getChild_startDir();
			case i_paths: return getChild_paths();
			case i_f: return getChild_f();
			case i_skipDir: return getChild_skipDir();
			case i_ioIn: return getChild_ioIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_startDir: return child_startDir;
			case i_paths: return child_paths;
			case i_f: return child_f;
			case i_skipDir: return child_skipDir;
			case i_ioIn: return child_ioIn;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:testing:bin:traverseDirectoriesAndPerform";
	}

	public static core.NIOVal invoke(final Object c_startDir, final Object c_paths, final Object c_f, final Object c_skipDir, final Object c_ioIn) {
		try {
		final common.DecoratedNode context = new PtraverseDirectoriesAndPerform(c_startDir, c_paths, c_f, c_skipDir, c_ioIn).decorate();
		//if null(paths,) then ioIn else traverseDirectoriesAndPerform(startDir, newStrings.iovalue ++ tail(paths,), f, skipDir, f(head(paths,), ioval(newStrings.io, ioIn.iovalue,),),)
		return (core.NIOVal)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))) ? context.childDecorated(silver.testing.bin.PtraverseDirectoriesAndPerform.i_ioIn).undecorate() : ((core.NIOVal)silver.testing.bin.PtraverseDirectoriesAndPerform.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_startDir), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.testing.bin.Init.newStrings__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } })); } }, context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_f), context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_skipDir), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NIOVal)((common.NodeFactory<core.NIOVal>)context.childAsIs(silver.testing.bin.PtraverseDirectoriesAndPerform.i_f)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_paths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.newStrings__ON__silver_testing_bin_traverseDirectoriesAndPerform).synthesized(core.Init.core_io__ON__core_IOVal)); } }, context.childDecoratedSynthesizedLazy(silver.testing.bin.PtraverseDirectoriesAndPerform.i_ioIn, core.Init.core_iovalue__ON__core_IOVal))); } }}, null)); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:traverseDirectoriesAndPerform", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtraverseDirectoriesAndPerform.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}