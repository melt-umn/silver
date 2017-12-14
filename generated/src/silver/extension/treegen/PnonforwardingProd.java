
package silver.extension.treegen;

public final class PnonforwardingProd extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_fe = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_nonforwardingProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PnonforwardingProd(final Object c_d, final Object c_fe) {
		this.child_d = c_d;
		this.child_fe = c_fe;

	}

	private Object child_d;
	public final silver.definition.env.NDclInfo getChild_d() {
		return (silver.definition.env.NDclInfo) (child_d = common.Util.demand(child_d));
	}

	private Object child_fe;
	public final common.DecoratedNode getChild_fe() {
		return (common.DecoratedNode) (child_fe = common.Util.demand(child_fe));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_fe: return getChild_fe();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
			case i_fe: return child_fe;

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
		return "silver:extension:treegen:nonforwardingProd";
	}

	public static Boolean invoke(final Object c_d, final Object c_fe) {
		try {
		final common.DecoratedNode context = new PnonforwardingProd(c_d, c_fe).decorate();
		//null(lookupFwd(d.fullName, fe))
		return (Boolean)(((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PlookupFwd.invoke(context.childDecoratedSynthesizedLazy(silver.extension.treegen.PnonforwardingProd.i_d, silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo), context.childAsIsLazy(silver.extension.treegen.PnonforwardingProd.i_fe))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:nonforwardingProd", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnonforwardingProd.invoke(children[0], children[1]);
		}
	};
}