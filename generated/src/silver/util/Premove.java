
package silver.util;

public final class Premove extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_remove;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Premove(final Object c_n, final Object c_s) {
		this.child_n = c_n;
		this.child_s = c_s;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
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
		return "silver:util:remove";
	}

	public static common.ConsCell invoke(final Object c_n, final Object c_s) {
		try {
		final common.DecoratedNode context = new Premove(c_n, c_s).decorate();
		//if null(s) then [] else if n == head(s) then remove(n, tail(s)) else [ head(s) ] ++ remove(n, tail(s))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.Premove.i_s))) ? ((common.ConsCell)core.Pnil.invoke()) : (((common.StringCatter)context.childAsIs(silver.util.Premove.i_n)).equals(((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.Premove.i_s)))) ? ((common.ConsCell)silver.util.Premove.invoke(context.childAsIsLazy(silver.util.Premove.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.Premove.i_s))); } })) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.Premove.i_s))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.Premove.invoke(context.childAsIsLazy(silver.util.Premove.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.Premove.i_s))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:remove", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Premove.invoke(children[0], children[1]);
		}
	};
}