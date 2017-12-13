
package silver.definition.flow.driver;

// top::StitchPoint ::= nt::String vertexType::VertexType 
public final class PnonterminalStitchPoint extends silver.definition.flow.driver.NStitchPoint {

	public static final int i_nt = 0;
	public static final int i_vertexType = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.flow.ast.NVertexType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_nonterminalStitchPoint;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.driver.NStitchPoint.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.driver.NStitchPoint.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_vertexType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	}

	public PnonterminalStitchPoint(final Object c_nt, final Object c_vertexType) {
		super();
		this.child_nt = c_nt;
		this.child_vertexType = c_vertexType;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_vertexType;
	public final silver.definition.flow.ast.NVertexType getChild_vertexType() {
		return (silver.definition.flow.ast.NVertexType) (child_vertexType = common.Util.demand(child_vertexType));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_vertexType: return getChild_vertexType();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_vertexType: return child_vertexType;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:driver:nonterminalStitchPoint erroneously claimed to forward");
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
		return "silver:definition:flow:driver:nonterminalStitchPoint";
	}

	static void initProductionAttributeDefinitions() {
		// top.stitchEdges = map(flowTypeEdge(vertexType, _), g:toList(findFlowType(nt, top.flowTypes)))
		silver.definition.flow.driver.PnonterminalStitchPoint.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_stitchEdges__ON__silver_definition_flow_driver_StitchPoint] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PflowTypeEdge.factory.invokePartial(new int[]{0}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PnonterminalStitchPoint.i_vertexType))}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.raw.graph.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PfindFlowType.invoke(context.childAsIsLazy(silver.definition.flow.driver.PnonterminalStitchPoint.i_nt), context.contextInheritedLazy(silver.definition.flow.driver.Init.silver_definition_flow_driver_flowTypes__ON__silver_definition_flow_driver_StitchPoint))); } })); } })); } };

	}

	public static final common.NodeFactory<PnonterminalStitchPoint> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnonterminalStitchPoint> {

		@Override
		public PnonterminalStitchPoint invoke(final Object[] children, final Object[] annotations) {
			return new PnonterminalStitchPoint(children[0], children[1]);
		}
	};

}
