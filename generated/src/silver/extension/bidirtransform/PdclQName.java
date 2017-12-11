
package silver.extension.bidirtransform;

public final class PdclQName extends common.FunctionNode {

	public static final int i_loc = 0;


	public static final Class<?> childTypes[] = { core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_dclQName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_loc] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PdclQName(final Object c_loc) {
		this.child_loc = c_loc;

	}

	private Object child_loc;
	public final core.NLocation getChild_loc() {
		return (core.NLocation) (child_loc = common.Util.demand(child_loc));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_loc: return getChild_loc();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_loc: return child_loc;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:bidirtransform:dclQName";
	}

	public static common.NodeFactory<silver.definition.core.NQName> invoke(final Object c_loc) {
		try {
		final common.DecoratedNode context = new PdclQName(c_loc).decorate();
		//(\ s::String  -> qName(loc, s))
		return (common.NodeFactory<silver.definition.core.NQName>)((new common.NodeFactory<silver.definition.core.NQName>() {
  public final silver.definition.core.NQName invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_13145_s = args[0];

    return ((silver.definition.core.NQName)silver.definition.core.PqName.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PdclQName.i_loc)), ((common.StringCatter)common.Util.demand(__SV_LAMBDA_PARAM_13145_s))));
  }
}));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:dclQName", t);
		}
	}

	public static final common.NodeFactory<common.NodeFactory<silver.definition.core.NQName>> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.NodeFactory<silver.definition.core.NQName>> {
		@Override
		public common.NodeFactory<silver.definition.core.NQName> invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdclQName.invoke(children[0]);
		}
	};
}