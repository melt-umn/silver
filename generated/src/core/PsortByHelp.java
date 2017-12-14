
package core;

public final class PsortByHelp extends common.FunctionNode {

	public static final int i_lte = 0;
	public static final int i_lst = 1;
	public static final int i_upTo = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__core_sortByHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsortByHelp(final Object c_lte, final Object c_lst, final Object c_upTo) {
		this.child_lte = c_lte;
		this.child_lst = c_lst;
		this.child_upTo = c_upTo;

	}

	private Object child_lte;
	public final common.NodeFactory<Boolean> getChild_lte() {
		return (common.NodeFactory<Boolean>) (child_lte = common.Util.demand(child_lte));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}

	private Object child_upTo;
	public final Integer getChild_upTo() {
		return (Integer) (child_upTo = common.Util.demand(child_upTo));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lte: return getChild_lte();
			case i_lst: return getChild_lst();
			case i_upTo: return getChild_upTo();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lte: return child_lte;
			case i_lst: return child_lst;
			case i_upTo: return child_upTo;

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
		return "core:sortByHelp";
	}

	public static common.ConsCell invoke(final Object c_lte, final Object c_lst, final Object c_upTo) {
		try {
		final common.DecoratedNode context = new PsortByHelp(c_lte, c_lst, c_upTo).decorate();
		//if upTo == 0 then [] else if upTo == 1 then [ head(lst) ] else mergeBy(lte, front_half, back_half)
		return (common.ConsCell)((((Integer)context.childAsIs(core.PsortByHelp.i_upTo)).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : (((Integer)context.childAsIs(core.PsortByHelp.i_upTo)).equals(Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PsortByHelp.i_lst))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.PmergeBy.invoke(context.childAsIsLazy(core.PsortByHelp.i_lte), context.localAsIsLazy(core.Init.front_half__ON__core_sortByHelp), context.localAsIsLazy(core.Init.back_half__ON__core_sortByHelp))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:sortByHelp", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsortByHelp.invoke(children[0], children[1], children[2]);
		}
	};
}