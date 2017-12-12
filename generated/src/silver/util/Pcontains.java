
package silver.util;

public final class Pcontains extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_sl = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_contains;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pcontains(final Object c_s, final Object c_sl) {
		this.child_s = c_s;
		this.child_sl = c_sl;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
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
		return "silver:util:contains";
	}

	public static Boolean invoke(final Object c_s, final Object c_sl) {
		try {
		final common.DecoratedNode context = new Pcontains(c_s, c_sl).decorate();
		//(! null(sl)) && (s == head(sl) || contains(s, tail(sl)))
		return (Boolean)(((!((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.Pcontains.i_sl)))) && (((common.StringCatter)context.childAsIs(silver.util.Pcontains.i_s)).equals(((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.Pcontains.i_sl)))) || ((Boolean)silver.util.Pcontains.invoke(context.childAsIsLazy(silver.util.Pcontains.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.Pcontains.i_sl))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:contains", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pcontains.invoke(children[0], children[1]);
		}
	};
}