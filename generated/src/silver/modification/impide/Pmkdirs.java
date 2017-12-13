
package silver.modification.impide;

public final class Pmkdirs extends common.FunctionNode {

	public static final int i_path = 0;
	public static final int i_paths = 1;
	public static final int i_i = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_mkdirs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pmkdirs(final Object c_path, final Object c_paths, final Object c_i) {
		this.child_path = c_path;
		this.child_paths = c_paths;
		this.child_i = c_i;

	}

	private Object child_path;
	public final common.StringCatter getChild_path() {
		return (common.StringCatter) (child_path = common.Util.demand(child_path));
	}

	private Object child_paths;
	public final common.ConsCell getChild_paths() {
		return (common.ConsCell) (child_paths = common.Util.demand(child_paths));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_path: return getChild_path();
			case i_paths: return getChild_paths();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_path: return child_path;
			case i_paths: return child_paths;
			case i_i: return child_i;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:modification:impide:mkdirs";
	}

	public static Object invoke(final Object c_path, final Object c_paths, final Object c_i) {
		try {
		final common.DecoratedNode context = new Pmkdirs(c_path, c_paths, c_i).decorate();
		//if null(paths) then i else mkdirs(path, tail(paths), mkdir(path ++ head(paths), i).io)
		return (Object)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.impide.Pmkdirs.i_paths))) ? ((Object)context.childAsIs(silver.modification.impide.Pmkdirs.i_i)) : ((Object)silver.modification.impide.Pmkdirs.invoke(context.childAsIsLazy(silver.modification.impide.Pmkdirs.i_path), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.impide.Pmkdirs.i_paths))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NIOVal)core.Pmkdir.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.Pmkdirs.i_path)), (common.StringCatter)((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.modification.impide.Pmkdirs.i_paths)))); } }, context.childAsIsLazy(silver.modification.impide.Pmkdirs.i_i))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:mkdirs", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pmkdirs.invoke(children[0], children[1], children[2]);
		}
	};
}