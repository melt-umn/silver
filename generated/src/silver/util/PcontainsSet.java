
package silver.util;

public final class PcontainsSet extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_sl = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_containsSet;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcontainsSet(final Object c_s, final Object c_sl) {
		this.child_s = c_s;
		this.child_sl = c_sl;

	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}

	private Object child_sl;
	public final common.ConsCell getChild_sl() {
		return (common.ConsCell) (child_sl = common.Util.demand(child_sl));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_sl: return getChild_sl();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_sl: return child_sl;

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
		return "silver:util:containsSet";
	}

	public static Boolean invoke(final Object c_s, final Object c_sl) {
		try {
		final common.DecoratedNode context = new PcontainsSet(c_s, c_sl).decorate();
		//(! null(sl)) && (equals(s, head(sl)) || containsSet(s, tail(sl)))
		return (Boolean)(((!((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.PcontainsSet.i_sl)))) && (((Boolean)silver.util.Pequals.invoke(context.childAsIsLazy(silver.util.PcontainsSet.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.util.PcontainsSet.i_sl))); } })) || ((Boolean)silver.util.PcontainsSet.invoke(context.childAsIsLazy(silver.util.PcontainsSet.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.PcontainsSet.i_sl))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:containsSet", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcontainsSet.invoke(children[0], children[1]);
		}
	};
}