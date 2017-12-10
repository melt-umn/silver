
package core;

public final class PmergeBy extends common.FunctionNode {

	public static final int i_lte = 0;
	public static final int i_l1 = 1;
	public static final int i_l2 = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_mergeBy;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmergeBy(final Object c_lte, final Object c_l1, final Object c_l2) {
		this.child_lte = c_lte;
		this.child_l1 = c_l1;
		this.child_l2 = c_l2;

	}

	private Object child_lte;
	public final common.NodeFactory<Boolean> getChild_lte() {
		return (common.NodeFactory<Boolean>) (child_lte = common.Util.demand(child_lte));
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
			case i_lte: return getChild_lte();
			case i_l1: return getChild_l1();
			case i_l2: return getChild_l2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lte: return child_lte;
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
		return "core:mergeBy";
	}

	public static common.ConsCell invoke(final Object c_lte, final Object c_l1, final Object c_l2) {
		try {
		final common.DecoratedNode context = new PmergeBy(c_lte, c_l1, c_l2).decorate();
		//if null(l1) then l2 else if null(l2) then l1 else if lte(head(l1), head(l2)) then (head(l1) :: mergeBy(lte, tail(l1), l2)) else (head(l2) :: mergeBy(lte, l1, tail(l2)))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PmergeBy.i_l1))) ? ((common.ConsCell)context.childAsIs(core.PmergeBy.i_l2)) : (((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PmergeBy.i_l2))) ? ((common.ConsCell)context.childAsIs(core.PmergeBy.i_l1)) : (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(core.PmergeBy.i_lte)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PmergeBy.i_l1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PmergeBy.i_l2))); } }}, null)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PmergeBy.i_l1))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PmergeBy.invoke(context.childAsIsLazy(core.PmergeBy.i_lte), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PmergeBy.i_l1))); } }, context.childAsIsLazy(core.PmergeBy.i_l2))); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PmergeBy.i_l2))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PmergeBy.invoke(context.childAsIsLazy(core.PmergeBy.i_lte), context.childAsIsLazy(core.PmergeBy.i_l1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PmergeBy.i_l2))); } })); } }))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:mergeBy", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmergeBy.invoke(children[0], children[1], children[2]);
		}
	};
}