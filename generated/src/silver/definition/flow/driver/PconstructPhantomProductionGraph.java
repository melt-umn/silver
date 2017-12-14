
package silver.definition.flow.driver;

public final class PconstructPhantomProductionGraph extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_flowEnv = 1;
	public static final int i_realEnv = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_constructPhantomProductionGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PconstructPhantomProductionGraph(final Object c_nt, final Object c_flowEnv, final Object c_realEnv) {
		this.child_nt = c_nt;
		this.child_flowEnv = c_flowEnv;
		this.child_realEnv = c_realEnv;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
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
			case i_nt: return getChild_nt();
			case i_flowEnv: return getChild_flowEnv();
			case i_realEnv: return getChild_realEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_flowEnv: return child_flowEnv;
			case i_realEnv: return child_realEnv;

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
		return "silver:definition:flow:driver:constructPhantomProductionGraph";
	}

	public static silver.definition.flow.driver.NProductionGraph invoke(final Object c_nt, final Object c_flowEnv, final Object c_realEnv) {
		try {
		final common.DecoratedNode context = new PconstructPhantomProductionGraph(c_nt, c_flowEnv, c_realEnv).decorate();
		//productionGraph("Phantom for " ++ nt, nt, flowTypeVertexes, initialGraph, suspectEdges, stitchPoints).transitiveClosure
		return (silver.definition.flow.driver.NProductionGraph)(((silver.definition.flow.driver.NProductionGraph)((silver.definition.flow.driver.NProductionGraph)new silver.definition.flow.driver.PproductionGraph(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Phantom for ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PconstructPhantomProductionGraph.i_nt))); } }, context.childAsIsLazy(silver.definition.flow.driver.PconstructPhantomProductionGraph.i_nt), context.localAsIsLazy(silver.definition.flow.driver.Init.flowTypeVertexes__ON__silver_definition_flow_driver_constructPhantomProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.initialGraph__ON__silver_definition_flow_driver_constructPhantomProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.suspectEdges__ON__silver_definition_flow_driver_constructPhantomProductionGraph), context.localAsIsLazy(silver.definition.flow.driver.Init.stitchPoints__ON__silver_definition_flow_driver_constructPhantomProductionGraph))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_transitiveClosure__ON__silver_definition_flow_driver_ProductionGraph)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:constructPhantomProductionGraph", t);
		}
	}

	public static final common.NodeFactory<silver.definition.flow.driver.NProductionGraph> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.flow.driver.NProductionGraph> {
		@Override
		public silver.definition.flow.driver.NProductionGraph invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconstructPhantomProductionGraph.invoke(children[0], children[1], children[2]);
		}
	};
}