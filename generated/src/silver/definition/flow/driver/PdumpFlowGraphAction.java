
package silver.definition.flow.driver;

// top::DriverAction ::= prodGraph::[ProductionGraph] finalGraph::[ProductionGraph] flowTypes::[Pair<String [FlowType]>] 
public final class PdumpFlowGraphAction extends silver.driver.util.NDriverAction {

	public static final int i_prodGraph = 0;
	public static final int i_finalGraph = 1;
	public static final int i_flowTypes = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_dumpFlowGraphAction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdumpFlowGraphAction(final Object c_prodGraph, final Object c_finalGraph, final Object c_flowTypes) {
		super();
		this.child_prodGraph = c_prodGraph;
		this.child_finalGraph = c_finalGraph;
		this.child_flowTypes = c_flowTypes;

	}

	private Object child_prodGraph;
	public final common.ConsCell getChild_prodGraph() {
		return (common.ConsCell) (child_prodGraph = common.Util.demand(child_prodGraph));
	}

	private Object child_finalGraph;
	public final common.ConsCell getChild_finalGraph() {
		return (common.ConsCell) (child_finalGraph = common.Util.demand(child_finalGraph));
	}

	private Object child_flowTypes;
	public final common.ConsCell getChild_flowTypes() {
		return (common.ConsCell) (child_flowTypes = common.Util.demand(child_flowTypes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prodGraph: return getChild_prodGraph();
			case i_finalGraph: return getChild_finalGraph();
			case i_flowTypes: return getChild_flowTypes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prodGraph: return child_prodGraph;
			case i_finalGraph: return child_finalGraph;
			case i_flowTypes: return child_flowTypes;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:driver:dumpFlowGraphAction erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:definition:flow:driver:dumpFlowGraphAction";
	}

	static void initProductionAttributeDefinitions() {
		// top.io = writeFile("flow-types.dot", "digraph flow {\n" ++ generateFlowDotGraph(flowTypes) ++ "}", writeFile("flow-deps-direct.dot", "digraph flow {\n" ++ generateDotGraph(prodGraph) ++ "}", writeFile("flow-deps-transitive.dot", "digraph flow {\n" ++ generateDotGraph(finalGraph) ++ "}", print("Generating flow graphs\n", top.ioIn))))
		silver.definition.flow.driver.PdumpFlowGraphAction.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke((new common.StringCatter("flow-types.dot")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("digraph flow {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.flow.driver.PgenerateFlowDotGraph.invoke(context.childAsIsLazy(silver.definition.flow.driver.PdumpFlowGraphAction.i_flowTypes))), (common.StringCatter)(new common.StringCatter("}")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke((new common.StringCatter("flow-deps-direct.dot")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("digraph flow {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.flow.driver.PgenerateDotGraph.invoke(context.childAsIsLazy(silver.definition.flow.driver.PdumpFlowGraphAction.i_prodGraph))), (common.StringCatter)(new common.StringCatter("}")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke((new common.StringCatter("flow-deps-transitive.dot")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("digraph flow {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.flow.driver.PgenerateDotGraph.invoke(context.childAsIsLazy(silver.definition.flow.driver.PdumpFlowGraphAction.i_finalGraph))), (common.StringCatter)(new common.StringCatter("}")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke((new common.StringCatter("Generating flow graphs\n")), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } })); } })); } })); } };
		// top.code = 0
		silver.definition.flow.driver.PdumpFlowGraphAction.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.order = 0
		silver.definition.flow.driver.PdumpFlowGraphAction.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };

	}

	public static final common.NodeFactory<PdumpFlowGraphAction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdumpFlowGraphAction> {

		@Override
		public PdumpFlowGraphAction invoke(final Object[] children, final Object[] annotations) {
			return new PdumpFlowGraphAction(children[0], children[1], children[2]);
		}
	};

}
