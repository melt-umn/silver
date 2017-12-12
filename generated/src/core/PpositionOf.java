
package core;

public final class PpositionOf extends common.FunctionNode {

	public static final int i_eq = 0;
	public static final int i_x = 1;
	public static final int i_xs = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,Object.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_positionOf;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpositionOf(final Object c_eq, final Object c_x, final Object c_xs) {
		this.child_eq = c_eq;
		this.child_x = c_x;
		this.child_xs = c_xs;

	}

	private Object child_eq;
	public final common.NodeFactory<Boolean> getChild_eq() {
		return (common.NodeFactory<Boolean>) (child_eq = common.Util.demand(child_eq));
	}

	private Object child_x;
	public final Object getChild_x() {
		return (Object) (child_x = common.Util.demand(child_x));
	}

	private Object child_xs;
	public final common.ConsCell getChild_xs() {
		return (common.ConsCell) (child_xs = common.Util.demand(child_xs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eq: return getChild_eq();
			case i_x: return getChild_x();
			case i_xs: return getChild_xs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eq: return child_eq;
			case i_x: return child_x;
			case i_xs: return child_xs;

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
		return "core:positionOf";
	}

	public static Integer invoke(final Object c_eq, final Object c_x, final Object c_xs) {
		try {
		final common.DecoratedNode context = new PpositionOf(c_eq, c_x, c_xs).decorate();
		//positionOfHelper(eq, x, xs, 0)
		return (Integer)(((Integer)core.PpositionOfHelper.invoke(context.childAsIsLazy(core.PpositionOf.i_eq), context.childAsIsLazy(core.PpositionOf.i_x), context.childAsIsLazy(core.PpositionOf.i_xs), Integer.valueOf((int)0))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:positionOf", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpositionOf.invoke(children[0], children[1], children[2]);
		}
	};
}