
package silver.definition.flow.driver;

public final class PgenerateFlowDotGraph extends common.FunctionNode {

	public static final int i_flowTypes = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_generateFlowDotGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgenerateFlowDotGraph(final Object c_flowTypes) {
		this.child_flowTypes = c_flowTypes;

	}

	private Object child_flowTypes;
	public final common.ConsCell getChild_flowTypes() {
		return (common.ConsCell) (child_flowTypes = common.Util.demand(child_flowTypes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_flowTypes: return getChild_flowTypes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_flowTypes: return child_flowTypes;

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
		return "silver:definition:flow:driver:generateFlowDotGraph";
	}

	public static common.StringCatter invoke(final Object c_flowTypes) {
		try {
		final common.DecoratedNode context = new PgenerateFlowDotGraph(c_flowTypes).decorate();
		//if null(flowTypes) then "" else "subgraph \"cluster:" ++ nt ++ "\" {\nlabel=\"" ++ substring(lastIndexOf(":", nt) + 1, length(nt), nt) ++ "\";\n" ++ implode("", map(makeLabelDcls(nt, _), nubBy(stringEq, expandLabels(edges)))) ++ implode("", map(makeNtFlow(nt, _), edges)) ++ "}\n" ++ generateFlowDotGraph(tail(flowTypes))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PgenerateFlowDotGraph.i_flowTypes))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("subgraph \"cluster:")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_generateFlowDotGraph)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" {\nlabel=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)core.PlastIndexOf.invoke((new common.StringCatter(":")), context.localAsIsLazy(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_generateFlowDotGraph))) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_generateFlowDotGraph))).length()); } }, context.localAsIsLazy(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_generateFlowDotGraph))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\";\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PmakeLabelDcls.factory.invokePartial(new int[]{0}, new Object[]{context.localAsIsLazy(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_generateFlowDotGraph)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PnubBy.invoke(core.PstringEq.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PexpandLabels.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.edges__ON__silver_definition_flow_driver_generateFlowDotGraph))); } })); } })); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PmakeNtFlow.factory.invokePartial(new int[]{0}, new Object[]{context.localAsIsLazy(silver.definition.flow.driver.Init.nt__ON__silver_definition_flow_driver_generateFlowDotGraph)}); } }, context.localAsIsLazy(silver.definition.flow.driver.Init.edges__ON__silver_definition_flow_driver_generateFlowDotGraph))); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("}\n")), (common.StringCatter)((common.StringCatter)silver.definition.flow.driver.PgenerateFlowDotGraph.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.flow.driver.PgenerateFlowDotGraph.i_flowTypes))); } }))))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:generateFlowDotGraph", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateFlowDotGraph.invoke(children[0]);
		}
	};
}