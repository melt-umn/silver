
package silver.util;

public final class PstartsWithAny extends common.FunctionNode {

	public static final int i_pre = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_startsWithAny;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PstartsWithAny(final Object c_pre, final Object c_s) {
		this.child_pre = c_pre;
		this.child_s = c_s;

	}

	private Object child_pre;
	public final common.ConsCell getChild_pre() {
		return (common.ConsCell) (child_pre = common.Util.demand(child_pre));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pre: return getChild_pre();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pre: return child_pre;
			case i_s: return child_s;

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
		return "silver:util:startsWithAny";
	}

	public static Boolean invoke(final Object c_pre, final Object c_s) {
		try {
		final common.DecoratedNode context = new PstartsWithAny(c_pre, c_s).decorate();
		//! null(pre) && (startsWith(head(pre), s) || startsWithAny(tail(pre), s))
		return (Boolean)(((!((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.PstartsWithAny.i_pre)))) && (((Boolean)core.PstartsWith.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.PstartsWithAny.i_pre))); } }, context.childAsIsLazy(silver.util.PstartsWithAny.i_s))) || ((Boolean)silver.util.PstartsWithAny.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.PstartsWithAny.i_pre))); } }, context.childAsIsLazy(silver.util.PstartsWithAny.i_s))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:startsWithAny", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PstartsWithAny.invoke(children[0], children[1]);
		}
	};
}