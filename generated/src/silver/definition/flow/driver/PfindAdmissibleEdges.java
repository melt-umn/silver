
package silver.definition.flow.driver;

public final class PfindAdmissibleEdges extends common.FunctionNode {

	public static final int i_edge = 0;
	public static final int i_graph = 1;
	public static final int i_ft = 2;


	public static final Class<?> childTypes[] = { core.NPair.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_findAdmissibleEdges;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_edge] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PfindAdmissibleEdges(final Object c_edge, final Object c_graph, final Object c_ft) {
		this.child_edge = c_edge;
		this.child_graph = c_graph;
		this.child_ft = c_ft;

	}

	private Object child_edge;
	public final core.NPair getChild_edge() {
		return (core.NPair) (child_edge = common.Util.demand(child_edge));
	}

	private Object child_graph;
	public final Object getChild_graph() {
		return (Object) (child_graph = common.Util.demand(child_graph));
	}

	private Object child_ft;
	public final Object getChild_ft() {
		return (Object) (child_ft = common.Util.demand(child_ft));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_edge: return getChild_edge();
			case i_graph: return getChild_graph();
			case i_ft: return getChild_ft();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_edge: return child_edge;
			case i_graph: return child_graph;
			case i_ft: return child_ft;

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
		return "silver:definition:flow:driver:findAdmissibleEdges";
	}

	public static common.ConsCell invoke(final Object c_edge, final Object c_graph, final Object c_ft) {
		try {
		final common.DecoratedNode context = new PfindAdmissibleEdges(c_edge, c_graph, c_ft).decorate();
		//if set:isEmpty(currentDeps) then [] else map(pair(edge.fst, _), validDeps)
		return (common.ConsCell)((((Boolean)silver.util.raw.treeset.PisEmpty.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.currentDeps__ON__silver_definition_flow_driver_findAdmissibleEdges))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return core.Ppair.factory.invokePartial(new int[]{0}, new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PfindAdmissibleEdges.i_edge, core.Init.core_fst__ON__core_Pair)}); } }, context.localAsIsLazy(silver.definition.flow.driver.Init.validDeps__ON__silver_definition_flow_driver_findAdmissibleEdges)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:findAdmissibleEdges", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindAdmissibleEdges.invoke(children[0], children[1], children[2]);
		}
	};
}