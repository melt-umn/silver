
package lib.extcore;

public final class PtoStringFromList extends common.FunctionNode {

	public static final int i_toStr = 0;
	public static final int i_xs = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__lib_extcore_toStringFromList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoStringFromList(final Object c_toStr, final Object c_xs) {
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
		return "lib:extcore:toStringFromList";
	}

	public static common.StringCatter invoke(final Object c_toStr, final Object c_xs) {
		try {
		final common.DecoratedNode context = new PtoStringFromList(c_toStr, c_xs).decorate();
		//"[" ++ toStringFromListHelper(toStr, xs) ++ "]"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromListHelper.invoke(context.childAsIsLazy(lib.extcore.PtoStringFromList.i_toStr), context.childAsIsLazy(lib.extcore.PtoStringFromList.i_xs))), (common.StringCatter)(new common.StringCatter("]")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function lib:extcore:toStringFromList", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoStringFromList.invoke(children[0], children[1]);
		}
	};
}