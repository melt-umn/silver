
package silver.definition.flow.driver;

public final class PfindProductionGraph extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_findProductionGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfindProductionGraph(final Object c_n, final Object c_l) {
		this.child_n = c_n;
		this.child_l = c_l;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_l;
	public final Object getChild_l() {
		return (Object) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_l: return child_l;

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
		return "silver:definition:flow:driver:findProductionGraph";
	}

	public static silver.definition.flow.driver.NProductionGraph invoke(final Object c_n, final Object c_l) {
		try {
		final common.DecoratedNode context = new PfindProductionGraph(c_n, c_l).decorate();
		//head(lookup)
		return (silver.definition.flow.driver.NProductionGraph)(((silver.definition.flow.driver.NProductionGraph)core.Phead.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.lookup__ON__silver_definition_flow_driver_findProductionGraph))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:findProductionGraph", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.driver.NProductionGraph> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.driver.NProductionGraph> {
		@Override
		public silver.definition.flow.driver.NProductionGraph invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindProductionGraph.invoke(children[0], children[1]);
		}
	};
}