
package silver.definition.flow.env;

public final class PfromFlowDefs extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowDefs.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_fromFlowDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_inh_attrs];

	}

	public PfromFlowDefs(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.definition.flow.ast.NFlowDefs getChild_d() {
		return (silver.definition.flow.ast.NFlowDefs) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		return "silver:definition:flow:env:fromFlowDefs";
	}

	public static common.DecoratedNode invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PfromFlowDefs(c_d).decorate();
		//e
		return (common.DecoratedNode)(context.localDecorated(silver.definition.flow.env.Init.e__ON__silver_definition_flow_env_fromFlowDefs));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:env:fromFlowDefs", t);
		}
	}

	public static final common.NodeFactory<common.DecoratedNode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.DecoratedNode> {
		@Override
		public common.DecoratedNode invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfromFlowDefs.invoke(children[0]);
		}
	};
}