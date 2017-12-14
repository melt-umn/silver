
package core;

public final class PunionsBy extends common.FunctionNode {

	public static final int i_eq = 0;
	public static final int i_ss = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_unionsBy;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunionsBy(final Object c_eq, final Object c_ss) {
		this.child_eq = c_eq;
		this.child_ss = c_ss;

	}

	private Object child_eq;
	public final common.NodeFactory<Boolean> getChild_eq() {
		return (common.NodeFactory<Boolean>) (child_eq = common.Util.demand(child_eq));
	}

	private Object child_ss;
	public final common.ConsCell getChild_ss() {
		return (common.ConsCell) (child_ss = common.Util.demand(child_ss));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eq: return getChild_eq();
			case i_ss: return getChild_ss();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eq: return child_eq;
			case i_ss: return child_ss;

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
		return "core:unionsBy";
	}

	public static common.ConsCell invoke(final Object c_eq, final Object c_ss) {
		try {
		final common.DecoratedNode context = new PunionsBy(c_eq, c_ss).decorate();
		//nubBy(eq, concat(ss))
		return (common.ConsCell)(((common.ConsCell)core.PnubBy.invoke(context.childAsIsLazy(core.PunionsBy.i_eq), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pconcat.invoke(context.childAsIsLazy(core.PunionsBy.i_ss))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:unionsBy", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunionsBy.invoke(children[0], children[1]);
		}
	};
}