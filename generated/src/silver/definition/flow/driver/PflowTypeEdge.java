
package silver.definition.flow.driver;

public final class PflowTypeEdge extends common.FunctionNode {

	public static final int i_vt = 0;
	public static final int i_edge = 1;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NVertexType.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_flowTypeEdge;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_vt] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_edge] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PflowTypeEdge(final Object c_vt, final Object c_edge) {
		this.child_vt = c_vt;
		this.child_edge = c_edge;

	}

	private Object child_vt;
	public final silver.definition.flow.ast.NVertexType getChild_vt() {
		return (silver.definition.flow.ast.NVertexType) (child_vt = common.Util.demand(child_vt));
	}

	private Object child_edge;
	public final core.NPair getChild_edge() {
		return (core.NPair) (child_edge = common.Util.demand(child_edge));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_vt: return getChild_vt();
			case i_edge: return getChild_edge();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_vt: return child_vt;
			case i_edge: return child_edge;

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
		return "silver:definition:flow:driver:flowTypeEdge";
	}

	public static core.NPair invoke(final Object c_vt, final Object c_edge) {
		try {
		final common.DecoratedNode context = new PflowTypeEdge(c_vt, c_edge).decorate();
		//if edge.fst == "forward" then pair(vt.fwdVertex, vt.inhVertex(edge.snd)) else pair(vt.synVertex(edge.fst), vt.inhVertex(edge.snd))
		return (core.NPair)((((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PflowTypeEdge.i_edge).synthesized(core.Init.core_fst__ON__core_Pair)).equals((new common.StringCatter("forward"))) ? ((core.NPair)new core.Ppair(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PflowTypeEdge.i_vt, silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdVertex__ON__silver_definition_flow_ast_VertexType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)((common.NodeFactory<silver.definition.flow.ast.NFlowVertex>)context.childDecorated(silver.definition.flow.driver.PflowTypeEdge.i_vt).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType)).invoke(new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PflowTypeEdge.i_edge, core.Init.core_snd__ON__core_Pair)}, null)); } })) : ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)((common.NodeFactory<silver.definition.flow.ast.NFlowVertex>)context.childDecorated(silver.definition.flow.driver.PflowTypeEdge.i_vt).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_synVertex__ON__silver_definition_flow_ast_VertexType)).invoke(new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PflowTypeEdge.i_edge, core.Init.core_fst__ON__core_Pair)}, null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)((common.NodeFactory<silver.definition.flow.ast.NFlowVertex>)context.childDecorated(silver.definition.flow.driver.PflowTypeEdge.i_vt).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType)).invoke(new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PflowTypeEdge.i_edge, core.Init.core_snd__ON__core_Pair)}, null)); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:flowTypeEdge", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PflowTypeEdge.invoke(children[0], children[1]);
		}
	};
}