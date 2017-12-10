
package core;

public final class PremoveAllBy extends common.FunctionNode {

	public static final int i_eq = 0;
	public static final int i_ys = 1;
	public static final int i_xs = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_removeAllBy;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PremoveAllBy(final Object c_eq, final Object c_ys, final Object c_xs) {
		this.child_eq = c_eq;
		this.child_ys = c_ys;
		this.child_xs = c_xs;

	}

	private Object child_eq;
	public final common.NodeFactory<Boolean> getChild_eq() {
		return (common.NodeFactory<Boolean>) (child_eq = common.Util.demand(child_eq));
	}

	private Object child_ys;
	public final common.ConsCell getChild_ys() {
		return (common.ConsCell) (child_ys = common.Util.demand(child_ys));
	}

	private Object child_xs;
	public final common.ConsCell getChild_xs() {
		return (common.ConsCell) (child_xs = common.Util.demand(child_xs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eq: return getChild_eq();
			case i_ys: return getChild_ys();
			case i_xs: return getChild_xs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eq: return child_eq;
			case i_ys: return child_ys;
			case i_xs: return child_xs;

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
		return "core:removeAllBy";
	}

	public static common.ConsCell invoke(final Object c_eq, final Object c_ys, final Object c_xs) {
		try {
		final common.DecoratedNode context = new PremoveAllBy(c_eq, c_ys, c_xs).decorate();
		//if null(ys) then xs else removeAllBy(eq, tail(ys), removeBy(eq, head(ys), xs))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PremoveAllBy.i_ys))) ? ((common.ConsCell)context.childAsIs(core.PremoveAllBy.i_xs)) : ((common.ConsCell)core.PremoveAllBy.invoke(context.childAsIsLazy(core.PremoveAllBy.i_eq), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PremoveAllBy.i_ys))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PremoveBy.invoke(context.childAsIsLazy(core.PremoveAllBy.i_eq), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PremoveAllBy.i_ys))); } }, context.childAsIsLazy(core.PremoveAllBy.i_xs))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:removeAllBy", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PremoveAllBy.invoke(children[0], children[1], children[2]);
		}
	};
}