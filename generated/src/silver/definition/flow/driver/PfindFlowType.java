
package silver.definition.flow.driver;

public final class PfindFlowType extends common.FunctionNode {

	public static final int i_prod = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_findFlowType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindFlowType(final Object c_prod, final Object c_e) {
		this.child_prod = c_prod;
		this.child_e = c_e;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_e: return child_e;

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
		return "silver:definition:flow:driver:findFlowType";
	}

	public static Object invoke(final Object c_prod, final Object c_e) {
		try {
		final common.DecoratedNode context = new PfindFlowType(c_prod, c_e).decorate();
		//if null(lookup) then g:empty(compareString) else head(lookup)
		return (Object)((((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.lookup__ON__silver_definition_flow_driver_findFlowType))) ? ((Object)silver.util.raw.graph.Pempty.invoke(core.PcompareString.factory)) : ((Object)core.Phead.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.lookup__ON__silver_definition_flow_driver_findFlowType)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:findFlowType", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindFlowType.invoke(children[0], children[1]);
		}
	};
}