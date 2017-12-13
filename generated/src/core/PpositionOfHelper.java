
package core;

public final class PpositionOfHelper extends common.FunctionNode {

	public static final int i_eq = 0;
	public static final int i_x = 1;
	public static final int i_xs = 2;
	public static final int i_currentPos = 3;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,Object.class,common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__core_positionOfHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PpositionOfHelper(final Object c_eq, final Object c_x, final Object c_xs, final Object c_currentPos) {
		this.child_eq = c_eq;
		this.child_x = c_x;
		this.child_xs = c_xs;
		this.child_currentPos = c_currentPos;

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

	private Object child_currentPos;
	public final Integer getChild_currentPos() {
		return (Integer) (child_currentPos = common.Util.demand(child_currentPos));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eq: return getChild_eq();
			case i_x: return getChild_x();
			case i_xs: return getChild_xs();
			case i_currentPos: return getChild_currentPos();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eq: return child_eq;
			case i_x: return child_x;
			case i_xs: return child_xs;
			case i_currentPos: return child_currentPos;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "core:positionOfHelper";
	}

	public static Integer invoke(final Object c_eq, final Object c_x, final Object c_xs, final Object c_currentPos) {
		try {
		final common.DecoratedNode context = new PpositionOfHelper(c_eq, c_x, c_xs, c_currentPos).decorate();
		//if null(xs) then -1 else if eq(x, head(xs)) then currentPos else positionOfHelper(eq, x, tail(xs), currentPos + 1)
		return (Integer)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PpositionOfHelper.i_xs))) ? Integer.valueOf((int)-1) : (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(core.PpositionOfHelper.i_eq)).invoke(new Object[]{context.childAsIsLazy(core.PpositionOfHelper.i_x), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.PpositionOfHelper.i_xs))); } }}, null)) ? ((Integer)context.childAsIs(core.PpositionOfHelper.i_currentPos)) : ((Integer)core.PpositionOfHelper.invoke(context.childAsIsLazy(core.PpositionOfHelper.i_eq), context.childAsIsLazy(core.PpositionOfHelper.i_x), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PpositionOfHelper.i_xs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(core.PpositionOfHelper.i_currentPos)) + Integer.valueOf((int)1)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:positionOfHelper", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpositionOfHelper.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}