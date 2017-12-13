
package silver.definition.flow.driver;

public final class PupdateGraph extends common.FunctionNode {

	public static final int i_graph = 0;
	public static final int i_prodEnv = 1;
	public static final int i_ntEnv = 2;


	public static final Class<?> childTypes[] = { silver.definition.flow.driver.NProductionGraph.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_updateGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_graph] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	}

	public PupdateGraph(final Object c_graph, final Object c_prodEnv, final Object c_ntEnv) {
		this.child_graph = c_graph;
		this.child_prodEnv = c_prodEnv;
		this.child_ntEnv = c_ntEnv;

	}

	private Object child_graph;
	public final silver.definition.flow.driver.NProductionGraph getChild_graph() {
		return (silver.definition.flow.driver.NProductionGraph) (child_graph = common.Util.demand(child_graph));
	}

	private Object child_prodEnv;
	public final Object getChild_prodEnv() {
		return (Object) (child_prodEnv = common.Util.demand(child_prodEnv));
	}

	private Object child_ntEnv;
	public final Object getChild_ntEnv() {
		return (Object) (child_ntEnv = common.Util.demand(child_ntEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_graph: return getChild_graph();
			case i_prodEnv: return getChild_prodEnv();
			case i_ntEnv: return getChild_ntEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_graph: return child_graph;
			case i_prodEnv: return child_prodEnv;
			case i_ntEnv: return child_ntEnv;

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
		return "silver:definition:flow:driver:updateGraph";
	}

	public static silver.definition.flow.driver.NProductionGraph invoke(final Object c_graph, final Object c_prodEnv, final Object c_ntEnv) {
		try {
		final common.DecoratedNode context = new PupdateGraph(c_graph, c_prodEnv, c_ntEnv).decorate();
		//stitchedGraph.cullSuspect
		return (silver.definition.flow.driver.NProductionGraph)(((silver.definition.flow.driver.NProductionGraph)context.localDecorated(silver.definition.flow.driver.Init.stitchedGraph__ON__silver_definition_flow_driver_updateGraph).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_cullSuspect__ON__silver_definition_flow_driver_ProductionGraph)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:updateGraph", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.driver.NProductionGraph> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.driver.NProductionGraph> {
		@Override
		public silver.definition.flow.driver.NProductionGraph invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PupdateGraph.invoke(children[0], children[1], children[2]);
		}
	};
}