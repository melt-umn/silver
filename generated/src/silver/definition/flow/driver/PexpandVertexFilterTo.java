
package silver.definition.flow.driver;

public final class PexpandVertexFilterTo extends common.FunctionNode {

	public static final int i_ver = 0;
	public static final int i_graph = 1;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NFlowVertex.class,silver.definition.flow.driver.NProductionGraph.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_expandVertexFilterTo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ver] = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];
	childInheritedAttributes[i_graph] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	}

	public PexpandVertexFilterTo(final Object c_ver, final Object c_graph) {
		this.child_ver = c_ver;
		this.child_graph = c_graph;

	}

	private Object child_ver;
	public final silver.definition.flow.ast.NFlowVertex getChild_ver() {
		return (silver.definition.flow.ast.NFlowVertex) (child_ver = common.Util.demand(child_ver));
	}

	private Object child_graph;
	public final silver.definition.flow.driver.NProductionGraph getChild_graph() {
		return (silver.definition.flow.driver.NProductionGraph) (child_graph = common.Util.demand(child_graph));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ver: return getChild_ver();
			case i_graph: return getChild_graph();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ver: return child_ver;
			case i_graph: return child_graph;

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
		return "silver:definition:flow:driver:expandVertexFilterTo";
	}

	public static core.NPair invoke(final Object c_ver, final Object c_graph) {
		try {
		final common.DecoratedNode context = new PexpandVertexFilterTo(c_ver, c_graph).decorate();
		//pair(ver.flowTypeName, filterLhsInh(set:toList(graph.edgeMap(ver))))
		return (core.NPair)(((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PexpandVertexFilterTo.i_ver, silver.definition.flow.driver.Init.silver_definition_flow_driver_flowTypeName__ON__silver_definition_flow_ast_FlowVertex), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PfilterLhsInh.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.raw.treeset.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)context.childDecorated(silver.definition.flow.driver.PexpandVertexFilterTo.i_graph).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_edgeMap__ON__silver_definition_flow_driver_ProductionGraph)).invoke(new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PexpandVertexFilterTo.i_ver))}, null)); } })); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:expandVertexFilterTo", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexpandVertexFilterTo.invoke(children[0], children[1]);
		}
	};
}