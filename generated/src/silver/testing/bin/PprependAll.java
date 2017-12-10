
package silver.testing.bin;

public final class PprependAll extends common.FunctionNode {

	public static final int i_pre = 0;
	public static final int i_all = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_prependAll;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PprependAll(final Object c_pre, final Object c_all) {
		this.child_pre = c_pre;
		this.child_all = c_all;

	}

	private Object child_pre;
	public final common.StringCatter getChild_pre() {
		return (common.StringCatter) (child_pre = common.Util.demand(child_pre));
	}

	private Object child_all;
	public final common.ConsCell getChild_all() {
		return (common.ConsCell) (child_all = common.Util.demand(child_all));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pre: return getChild_pre();
			case i_all: return getChild_all();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pre: return child_pre;
			case i_all: return child_all;

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
		return "silver:testing:bin:prependAll";
	}

	public static common.ConsCell invoke(final Object c_pre, final Object c_all) {
		try {
		final common.DecoratedNode context = new PprependAll(c_pre, c_all).decorate();
		//if null(all,) then [] else [ pre ++ "/" ++ head(all,) ] ++ prependAll(pre, tail(all,),)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.testing.bin.PprependAll.i_all))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PprependAll.i_pre)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.testing.bin.PprependAll.i_all))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.testing.bin.PprependAll.invoke(context.childAsIsLazy(silver.testing.bin.PprependAll.i_pre), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.testing.bin.PprependAll.i_all))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:bin:prependAll", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprependAll.invoke(children[0], children[1]);
		}
	};
}