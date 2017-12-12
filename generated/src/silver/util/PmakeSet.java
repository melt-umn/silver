
package silver.util;

public final class PmakeSet extends common.FunctionNode {

	public static final int i_list = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_makeSet;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeSet(final Object c_list) {
		this.child_list = c_list;

	}

	private Object child_list;
	public final common.ConsCell getChild_list() {
		return (common.ConsCell) (child_list = common.Util.demand(child_list));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_list: return getChild_list();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_list: return child_list;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:util:makeSet";
	}

	public static common.ConsCell invoke(final Object c_list) {
		try {
		final common.DecoratedNode context = new PmakeSet(c_list).decorate();
		//if null(list) then [] else if contains(head(list), recurse) then recurse else cons(head(list), recurse)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.PmakeSet.i_list))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.PmakeSet.i_list))); } }, context.localAsIsLazy(silver.util.Init.recurse__ON__silver_util_makeSet))) ? ((common.ConsCell)context.localAsIs(silver.util.Init.recurse__ON__silver_util_makeSet)) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.PmakeSet.i_list))); } }, context.localAsIsLazy(silver.util.Init.recurse__ON__silver_util_makeSet))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:makeSet", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeSet.invoke(children[0]);
		}
	};
}