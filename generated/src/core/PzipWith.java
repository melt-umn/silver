
package core;

public final class PzipWith extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_l1 = 1;
	public static final int i_l2 = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_zipWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PzipWith(final Object c_f, final Object c_l1, final Object c_l2) {
		this.child_f = c_f;
		this.child_l1 = c_l1;
		this.child_l2 = c_l2;

	}

	private Object child_f;
	public final common.NodeFactory<Object> getChild_f() {
		return (common.NodeFactory<Object>) (child_f = common.Util.demand(child_f));
	}

	private Object child_l1;
	public final common.ConsCell getChild_l1() {
		return (common.ConsCell) (child_l1 = common.Util.demand(child_l1));
	}

	private Object child_l2;
	public final common.ConsCell getChild_l2() {
		return (common.ConsCell) (child_l2 = common.Util.demand(child_l2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_l1: return getChild_l1();
			case i_l2: return getChild_l2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_l1: return child_l1;
			case i_l2: return child_l2;

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
		return "core:zipWith";
	}

	public static common.ConsCell invoke(final Object c_f, final Object c_l1, final Object c_l2) {
		try {
		final common.DecoratedNode context = new PzipWith(c_f, c_l1, c_l2).decorate();
		//if null(l1) || null(l2) then [] else (f(head(l1), head(l2)) :: zipWith(f, tail(l1), tail(l2)))
		return (common.ConsCell)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PzipWith.i_l1))) || ((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PzipWith.i_l2)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)context.childAsIs(core.PzipWith.i_f)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PzipWith.i_l1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PzipWith.i_l2))); } }}, null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(context.childAsIsLazy(core.PzipWith.i_f), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PzipWith.i_l1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PzipWith.i_l2))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:zipWith", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PzipWith.invoke(children[0], children[1], children[2]);
		}
	};
}