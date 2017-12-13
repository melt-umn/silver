
package silver.extension.bidirtransform;

public final class PfindIdxHelper extends common.FunctionNode {

	public static final int i_ls = 0;
	public static final int i_item = 1;
	public static final int i_idx = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_findIdxHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindIdxHelper(final Object c_ls, final Object c_item, final Object c_idx) {
		this.child_ls = c_ls;
		this.child_item = c_item;
		this.child_idx = c_idx;

	}

	private Object child_ls;
	public final common.ConsCell getChild_ls() {
		return (common.ConsCell) (child_ls = common.Util.demand(child_ls));
	}

	private Object child_item;
	public final common.StringCatter getChild_item() {
		return (common.StringCatter) (child_item = common.Util.demand(child_item));
	}

	private Object child_idx;
	public final Integer getChild_idx() {
		return (Integer) (child_idx = common.Util.demand(child_idx));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ls: return getChild_ls();
			case i_item: return getChild_item();
			case i_idx: return getChild_idx();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ls: return child_ls;
			case i_item: return child_item;
			case i_idx: return child_idx;

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
		return "silver:extension:bidirtransform:findIdxHelper";
	}

	public static Integer invoke(final Object c_ls, final Object c_item, final Object c_idx) {
		try {
		final common.DecoratedNode context = new PfindIdxHelper(c_ls, c_item, c_idx).decorate();
		//if null(ls) then -1 else if head(ls) == item then idx else findIdxHelper(tail(ls), item, idx + 1)
		return (Integer)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfindIdxHelper.i_ls))) ? Integer.valueOf((int)-1) : (((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfindIdxHelper.i_ls))).equals(((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PfindIdxHelper.i_item))) ? ((Integer)context.childAsIs(silver.extension.bidirtransform.PfindIdxHelper.i_idx)) : ((Integer)silver.extension.bidirtransform.PfindIdxHelper.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfindIdxHelper.i_ls))); } }, context.childAsIsLazy(silver.extension.bidirtransform.PfindIdxHelper.i_item), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.extension.bidirtransform.PfindIdxHelper.i_idx)) + Integer.valueOf((int)1)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:findIdxHelper", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindIdxHelper.invoke(children[0], children[1], children[2]);
		}
	};
}