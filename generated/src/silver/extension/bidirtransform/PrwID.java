
package silver.extension.bidirtransform;

public final class PrwID extends common.FunctionNode {

	public static final int i_rwrs = 0;
	public static final int i_inType = 1;
	public static final int i_outType = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rwID;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrwID(final Object c_rwrs, final Object c_inType, final Object c_outType) {
		this.child_rwrs = c_rwrs;
		this.child_inType = c_inType;
		this.child_outType = c_outType;

	}

	private Object child_rwrs;
	public final common.ConsCell getChild_rwrs() {
		return (common.ConsCell) (child_rwrs = common.Util.demand(child_rwrs));
	}

	private Object child_inType;
	public final common.StringCatter getChild_inType() {
		return (common.StringCatter) (child_inType = common.Util.demand(child_inType));
	}

	private Object child_outType;
	public final common.StringCatter getChild_outType() {
		return (common.StringCatter) (child_outType = common.Util.demand(child_outType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rwrs: return getChild_rwrs();
			case i_inType: return getChild_inType();
			case i_outType: return getChild_outType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rwrs: return child_rwrs;
			case i_inType: return child_inType;
			case i_outType: return child_outType;

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
		return "silver:extension:bidirtransform:rwID";
	}

	public static common.DecoratedNode invoke(final Object c_rwrs, final Object c_inType, final Object c_outType) {
		try {
		final common.DecoratedNode context = new PrwID(c_rwrs, c_inType, c_outType).decorate();
		//if hasRwEq(rwrs, inType, outType) then rwEq(rwrs, inType, outType) else rwOut(rwrs, outType)
		return (common.DecoratedNode)((((Boolean)silver.extension.bidirtransform.PhasRwEq.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_rwrs), context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_inType), context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_outType))) ? ((common.DecoratedNode)silver.extension.bidirtransform.PrwEq.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_rwrs), context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_inType), context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_outType))) : ((common.DecoratedNode)silver.extension.bidirtransform.PrwOut.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_rwrs), context.childAsIsLazy(silver.extension.bidirtransform.PrwID.i_outType)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:rwID", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrwID.invoke(children[0], children[1], children[2]);
		}
	};
}