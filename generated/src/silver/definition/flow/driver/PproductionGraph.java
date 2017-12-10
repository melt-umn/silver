
package silver.definition.flow.driver;

// top::ProductionGraph ::= prod::String lhsNt::String flowTypeVertexes::[FlowVertex] graph::g:Graph<FlowVertex> suspectEdges::[Pair<FlowVertex FlowVertex>] stitchPoints::[StitchPoint] 
public final class PproductionGraph extends silver.definition.flow.driver.NProductionGraph {

	public static final int i_prod = 0;
	public static final int i_lhsNt = 1;
	public static final int i_flowTypeVertexes = 2;
	public static final int i_graph = 3;
	public static final int i_suspectEdges = 4;
	public static final int i_stitchPoints = 5;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class,Object.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_productionGraph;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PproductionGraph(final Object c_prod, final Object c_lhsNt, final Object c_flowTypeVertexes, final Object c_graph, final Object c_suspectEdges, final Object c_stitchPoints) {
		super();
		this.child_prod = c_prod;
		this.child_lhsNt = c_lhsNt;
		this.child_flowTypeVertexes = c_flowTypeVertexes;
		this.child_graph = c_graph;
		this.child_suspectEdges = c_suspectEdges;
		this.child_stitchPoints = c_stitchPoints;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_lhsNt;
	public final common.StringCatter getChild_lhsNt() {
		return (common.StringCatter) (child_lhsNt = common.Util.demand(child_lhsNt));
	}

	private Object child_flowTypeVertexes;
	public final common.ConsCell getChild_flowTypeVertexes() {
		return (common.ConsCell) (child_flowTypeVertexes = common.Util.demand(child_flowTypeVertexes));
	}

	private Object child_graph;
	public final Object getChild_graph() {
		return (Object) (child_graph = common.Util.demand(child_graph));
	}

	private Object child_suspectEdges;
	public final common.ConsCell getChild_suspectEdges() {
		return (common.ConsCell) (child_suspectEdges = common.Util.demand(child_suspectEdges));
	}

	private Object child_stitchPoints;
	public final common.ConsCell getChild_stitchPoints() {
		return (common.ConsCell) (child_stitchPoints = common.Util.demand(child_stitchPoints));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_lhsNt: return getChild_lhsNt();
			case i_flowTypeVertexes: return getChild_flowTypeVertexes();
			case i_graph: return getChild_graph();
			case i_suspectEdges: return getChild_suspectEdges();
			case i_stitchPoints: return getChild_stitchPoints();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_lhsNt: return child_lhsNt;
			case i_flowTypeVertexes: return child_flowTypeVertexes;
			case i_graph: return child_graph;
			case i_suspectEdges: return child_suspectEdges;
			case i_stitchPoints: return child_stitchPoints;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:driver:productionGraph erroneously claimed to forward");
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
		return "silver:definition:flow:driver:productionGraph";
	}

	static void initProductionAttributeDefinitions() {
		// top.prod = prod
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_prod__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PproductionGraph.i_prod)); } };
		// top.lhsNt = lhsNt
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_lhsNt__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PproductionGraph.i_lhsNt)); } };
		// top.flowTypeVertexes = flowTypeVertexes
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_flowTypeVertexes__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.definition.flow.driver.PproductionGraph.i_flowTypeVertexes)); } };
		// top.stitchedGraph = let newEdges :: [Pair<FlowVertex FlowVertex>] = filter(edgeIsNew(_, graph), flatMap(stitchEdgesFor(_, top.flowTypes, top.prodGraphs), stitchPoints)) in let repaired :: g:Graph<FlowVertex> = repairClosure(newEdges, graph) in if null(newEdges) then top else productionGraph(prod, lhsNt, flowTypeVertexes, repaired, suspectEdges, stitchPoints) end end
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_stitchedGraph__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NProductionGraph)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5928_newEdges = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PedgeIsNew.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_graph)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PstitchEdgesFor.factory.invokePartial(new int[]{1, 2}, new Object[]{context.contextInheritedLazy(silver.definition.flow.driver.Init.silver_definition_flow_driver_flowTypes__ON__silver_definition_flow_driver_ProductionGraph), context.contextInheritedLazy(silver.definition.flow.driver.Init.silver_definition_flow_driver_prodGraphs__ON__silver_definition_flow_driver_ProductionGraph)}); } }, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_stitchPoints))); } })); } };
return ((silver.definition.flow.driver.NProductionGraph)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5929_repaired = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PrepairClosure.invoke(__SV_LOCAL_5928_newEdges, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_graph))); } };
return (((Boolean)core.Pnull.invoke(__SV_LOCAL_5928_newEdges)) ? context.undecorate() : ((silver.definition.flow.driver.NProductionGraph)new silver.definition.flow.driver.PproductionGraph(context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_prod), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_lhsNt), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_flowTypeVertexes), __SV_LOCAL_5929_repaired, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_suspectEdges), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_stitchPoints)))); } }).eval()); } }).eval()); } };
		// top.transitiveClosure = let transitiveClosure :: g:Graph<FlowVertex> = transitiveClose(graph) in productionGraph(prod, lhsNt, flowTypeVertexes, transitiveClosure, suspectEdges, stitchPoints) end
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_transitiveClosure__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NProductionGraph)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5930_transitiveClosure = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PtransitiveClose.invoke(context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_graph))); } };
return ((silver.definition.flow.driver.NProductionGraph)new silver.definition.flow.driver.PproductionGraph(context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_prod), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_lhsNt), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_flowTypeVertexes), __SV_LOCAL_5930_transitiveClosure, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_suspectEdges), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_stitchPoints))); } }).eval()); } };
		// top.edgeMap = g:edgesFrom(_, graph)
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_edgeMap__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.util.raw.graph.PedgesFrom.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_graph)}); } };
		// top.cullSuspect = let newEdges :: [Pair<FlowVertex FlowVertex>] = flatMap(findAdmissibleEdges(_, graph, findFlowType(lhsNt, top.flowTypes)), suspectEdges) in let repaired :: g:Graph<FlowVertex> = repairClosure(newEdges, graph) in if null(newEdges) then top else productionGraph(prod, lhsNt, flowTypeVertexes, repaired, suspectEdges, stitchPoints) end end
		silver.definition.flow.driver.PproductionGraph.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_cullSuspect__ON__silver_definition_flow_driver_ProductionGraph] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NProductionGraph)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5931_newEdges = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PfindAdmissibleEdges.factory.invokePartial(new int[]{1, 2}, new Object[]{context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_graph), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PfindFlowType.invoke(context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_lhsNt), context.contextInheritedLazy(silver.definition.flow.driver.Init.silver_definition_flow_driver_flowTypes__ON__silver_definition_flow_driver_ProductionGraph))); } }}); } }, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_suspectEdges))); } };
return ((silver.definition.flow.driver.NProductionGraph)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5932_repaired = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PrepairClosure.invoke(__SV_LOCAL_5931_newEdges, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_graph))); } };
return (((Boolean)core.Pnull.invoke(__SV_LOCAL_5931_newEdges)) ? context.undecorate() : ((silver.definition.flow.driver.NProductionGraph)new silver.definition.flow.driver.PproductionGraph(context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_prod), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_lhsNt), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_flowTypeVertexes), __SV_LOCAL_5932_repaired, context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_suspectEdges), context.childAsIsLazy(silver.definition.flow.driver.PproductionGraph.i_stitchPoints)))); } }).eval()); } }).eval()); } };

	}

	public static final common.NodeFactory<PproductionGraph> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionGraph> {

		@Override
		public PproductionGraph invoke(final Object[] children, final Object[] annotations) {
			return new PproductionGraph(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
