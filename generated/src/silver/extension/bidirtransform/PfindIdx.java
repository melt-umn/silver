
package silver.extension.bidirtransform;

public final class PfindIdx extends common.FunctionNode {

	public static final int i_ls = 0;
	public static final int i_item = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_findIdx;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindIdx(final Object c_ls, final Object c_item) {
		this.child_ls = c_ls;
		this.child_item = c_item;

	}

	private Object child_ls;
	public final common.ConsCell getChild_ls() {
		return (common.ConsCell) (child_ls = common.Util.demand(child_ls));
	}

	private Object child_item;
	public final common.StringCatter getChild_item() {
		return (common.StringCatter) (child_item = common.Util.demand(child_item));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ls: return getChild_ls();
			case i_item: return getChild_item();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ls: return child_ls;
			case i_item: return child_item;

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
		return "silver:extension:bidirtransform:findIdx";
	}

	public static Integer invoke(final Object c_ls, final Object c_item) {
		try {
		final common.DecoratedNode context = new PfindIdx(c_ls, c_item).decorate();
		//findIdxHelper(ls, item, 0)
		return (Integer)(((Integer)silver.extension.bidirtransform.PfindIdxHelper.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfindIdx.i_ls), context.childAsIsLazy(silver.extension.bidirtransform.PfindIdx.i_item), Integer.valueOf((int)0))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:findIdx", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindIdx.invoke(children[0], children[1]);
		}
	};
}