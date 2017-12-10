
package silver.util;

public final class Prem extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_seen = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_rem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Prem(final Object c_n, final Object c_seen) {
		this.child_n = c_n;
		this.child_seen = c_seen;

	}

	private Object child_n;
	public final common.ConsCell getChild_n() {
		return (common.ConsCell) (child_n = common.Util.demand(child_n));
	}

	private Object child_seen;
	public final common.ConsCell getChild_seen() {
		return (common.ConsCell) (child_seen = common.Util.demand(child_seen));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_seen: return getChild_seen();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_seen: return child_seen;

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
		return "silver:util:rem";
	}

	public static common.ConsCell invoke(final Object c_n, final Object c_seen) {
		try {
		final common.DecoratedNode context = new Prem(c_n, c_seen).decorate();
		//if null(n) then [] else if contains(head(n), seen) then rem(tail(n), seen) else cons(head(n), rem(tail(n), seen))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.Prem.i_n))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.Prem.i_n))); } }, context.childAsIsLazy(silver.util.Prem.i_seen))) ? ((common.ConsCell)silver.util.Prem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.Prem.i_n))); } }, context.childAsIsLazy(silver.util.Prem.i_seen))) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.Prem.i_n))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.Prem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.util.Prem.i_n))); } }, context.childAsIsLazy(silver.util.Prem.i_seen))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:rem", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Prem.invoke(children[0], children[1]);
		}
	};
}