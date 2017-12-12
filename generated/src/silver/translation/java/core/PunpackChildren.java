
package silver.translation.java.core;

public final class PunpackChildren extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_ns = 1;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_translation_java_core_unpackChildren;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunpackChildren(final Object c_i, final Object c_ns) {
		this.child_i = c_i;
		this.child_ns = c_ns;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}

	private Object child_ns;
	public final common.ConsCell getChild_ns() {
		return (common.ConsCell) (child_ns = common.Util.demand(child_ns));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_ns: return getChild_ns();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
			case i_ns: return child_ns;

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
		return "silver:translation:java:core:unpackChildren";
	}

	public static common.ConsCell invoke(final Object c_i, final Object c_ns) {
		try {
		final common.DecoratedNode context = new PunpackChildren(c_i, c_ns).decorate();
		//if null(ns) then [] else (("children[" ++ toString(i) ++ "]") :: unpackChildren(i + 1, tail(ns)))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.translation.java.core.PunpackChildren.i_ns))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("children[")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(silver.translation.java.core.PunpackChildren.i_i)))), (common.StringCatter)(new common.StringCatter("]")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.translation.java.core.PunpackChildren.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.translation.java.core.PunpackChildren.i_i)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.translation.java.core.PunpackChildren.i_ns))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:translation:java:core:unpackChildren", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunpackChildren.invoke(children[0], children[1]);
		}
	};
}