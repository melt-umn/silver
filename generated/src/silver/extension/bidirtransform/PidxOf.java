
package silver.extension.bidirtransform;

public final class PidxOf extends common.FunctionNode {

	public static final int i_ls = 0;
	public static final int i_idx = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_idxOf;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PidxOf(final Object c_ls, final Object c_idx) {
		this.child_ls = c_ls;
		this.child_idx = c_idx;

	}

	private Object child_ls;
	public final common.ConsCell getChild_ls() {
		return (common.ConsCell) (child_ls = common.Util.demand(child_ls));
	}

	private Object child_idx;
	public final Integer getChild_idx() {
		return (Integer) (child_idx = common.Util.demand(child_idx));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ls: return getChild_ls();
			case i_idx: return getChild_idx();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ls: return child_ls;
			case i_idx: return child_idx;

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
		return "silver:extension:bidirtransform:idxOf";
	}

	public static Object invoke(final Object c_ls, final Object c_idx) {
		try {
		final common.DecoratedNode context = new PidxOf(c_ls, c_idx).decorate();
		//if idx == 0 then head(ls) else idxOf(tail(ls), idx - 1)
		return (Object)((((Integer)context.childAsIs(silver.extension.bidirtransform.PidxOf.i_idx)).equals(Integer.valueOf((int)0)) ? ((Object)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PidxOf.i_ls))) : ((Object)silver.extension.bidirtransform.PidxOf.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PidxOf.i_ls))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.extension.bidirtransform.PidxOf.i_idx)) - Integer.valueOf((int)1)); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:idxOf", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PidxOf.invoke(children[0], children[1]);
		}
	};
}