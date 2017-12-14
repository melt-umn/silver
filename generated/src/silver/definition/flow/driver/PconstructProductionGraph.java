
package silver.definition.flow.driver;

public final class PconstructProductionGraph extends common.FunctionNode {

	public static final int i_dcl = 0;
	public static final int i_defs = 1;
	public static final int i_flowEnv = 2;
	public static final int i_realEnv = 3;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_constructProductionGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_dcl] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PconstructProductionGraph(final Object c_dcl, final Object c_defs, final Object c_flowEnv, final Object c_realEnv) {
		this.child_dcl = c_dcl;
		this.child_defs = c_defs;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;

	}

	private Object child_dcl;
	public final silver.definition.env.NDclInfo getChild_dcl() {
		return (silver.definition.env.NDclInfo) (child_dcl = common.Util.demand(child_dcl));
	}

	private Object child_defs;
	public final common.ConsCell getChild_defs() {
		return (common.ConsCell) (child_defs = common.Util.demand(child_defs));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_dcl: return getChild_dcl();
			case i_defs: return getChild_defs();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_dcl: return child_dcl;
			case i_defs: return child_defs;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:definition:flow:driver:constructProductionGraph";
	}

	public static silver.definition.flow.driver.NProductionGraph invoke(final Object c_dcl, final Object c_defs, final Object c_flowEnv, final Object c_realEnv) {
		try {
		final common.DecoratedNode context = new PconstructProductionGraph(c_dcl, c_defs, c_flowEnv, c_realEnv).decorate();
		//productionGraph(prod, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure
		return (silver.definition.flow.driver.NProductionGraph)(((silver.definition.flow.driver.NProductionGraph)((silver.definition.flow.driver.NProductionGraph)new silver.definition.flow.driver.PproductionGraph(context.localAsIsLazy(silver.definition.flow.driver.Init.prod__ON__silver_definition_flow_driver_constructProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_constructProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.flowTypeVertexes__ON__silver_definition_flow_driver_constructProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.initialGraph__ON__silver_definition_flow_driver_constructProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.suspectEdges__ON__silver_definition_flow_driver_constructProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.stitchPoints__ON__silver_definition_flow_driver_constructProductionGraph))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_transitiveClosure__ON__silver_definition_flow_driver_ProductionGraph)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:constructProductionGraph", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.driver.NProductionGraph> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.driver.NProductionGraph> {
		@Override
		public silver.definition.flow.driver.NProductionGraph invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconstructProductionGraph.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}