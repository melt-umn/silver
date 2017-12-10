
package lib.extcore;

public final class PtoStringFromListHelper extends common.FunctionNode {

	public static final int i_toStr = 0;
	public static final int i_xs = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_toStringFromListHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoStringFromListHelper(final Object c_toStr, final Object c_xs) {
		this.child_toStr = c_toStr;
		this.child_xs = c_xs;

	}

	private Object child_toStr;
	public final common.NodeFactory<common.StringCatter> getChild_toStr() {
		return (common.NodeFactory<common.StringCatter>) (child_toStr = common.Util.demand(child_toStr));
	}

	private Object child_xs;
	public final common.ConsCell getChild_xs() {
		return (common.ConsCell) (child_xs = common.Util.demand(child_xs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toStr: return getChild_toStr();
			case i_xs: return getChild_xs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toStr: return child_toStr;
			case i_xs: return child_xs;

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
		return "lib:extcore:toStringFromListHelper";
	}

	public static common.StringCatter invoke(final Object c_toStr, final Object c_xs) {
		try {
		final common.DecoratedNode context = new PtoStringFromListHelper(c_toStr, c_xs).decorate();
		//if null(xs) then "" else toStr(head(xs)) ++ if null(tail(xs)) then "" else ", " ++ toStringFromListHelper(toStr, tail(xs))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(lib.extcore.PtoStringFromListHelper.i_xs))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)((common.StringCatter)((common.NodeFactory<common.StringCatter>)context.childAsIs(lib.extcore.PtoStringFromListHelper.i_toStr)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(lib.extcore.PtoStringFromListHelper.i_xs))); } }}, null)), (common.StringCatter)(((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PtoStringFromListHelper.i_xs))); } })) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromListHelper.invoke(context.childAsIsLazy(lib.extcore.PtoStringFromListHelper.i_toStr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(lib.extcore.PtoStringFromListHelper.i_xs))); } })))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:toStringFromListHelper", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoStringFromListHelper.invoke(children[0], children[1]);
		}
	};
}