
package silver.definition.flow.driver;

// top::StitchPoint ::= prod::String sourceType::VertexType targetType::VertexType prodType::VertexType attrs::[String] 
public final class PprojectionStitchPoint extends silver.definition.flow.driver.NStitchPoint {

	public static final int i_prod = 0;
	public static final int i_sourceType = 1;
	public static final int i_targetType = 2;
	public static final int i_prodType = 3;
	public static final int i_attrs = 4;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.flow.ast.NVertexType.class,silver.definition.flow.ast.NVertexType.class,silver.definition.flow.ast.NVertexType.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_projectionStitchPoint;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.driver.NStitchPoint.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.driver.NStitchPoint.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sourceType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_targetType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_prodType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	}

	public PprojectionStitchPoint(final Object c_prod, final Object c_sourceType, final Object c_targetType, final Object c_prodType, final Object c_attrs) {
		super();
		this.child_prod = c_prod;
		this.child_sourceType = c_sourceType;
		this.child_targetType = c_targetType;
		this.child_prodType = c_prodType;
		this.child_attrs = c_attrs;

	}

	private Object child_prod;
	public final common.StringCatter getChild_prod() {
		return (common.StringCatter) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_sourceType;
	public final silver.definition.flow.ast.NVertexType getChild_sourceType() {
		return (silver.definition.flow.ast.NVertexType) (child_sourceType = common.Util.demand(child_sourceType));
	}

	private Object child_targetType;
	public final silver.definition.flow.ast.NVertexType getChild_targetType() {
		return (silver.definition.flow.ast.NVertexType) (child_targetType = common.Util.demand(child_targetType));
	}

	private Object child_prodType;
	public final silver.definition.flow.ast.NVertexType getChild_prodType() {
		return (silver.definition.flow.ast.NVertexType) (child_prodType = common.Util.demand(child_prodType));
	}

	private Object child_attrs;
	public final common.ConsCell getChild_attrs() {
		return (common.ConsCell) (child_attrs = common.Util.demand(child_attrs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_sourceType: return getChild_sourceType();
			case i_targetType: return getChild_targetType();
			case i_prodType: return getChild_prodType();
			case i_attrs: return getChild_attrs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_sourceType: return child_sourceType;
			case i_targetType: return child_targetType;
			case i_prodType: return child_prodType;
			case i_attrs: return child_attrs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:driver:projectionStitchPoint erroneously claimed to forward");
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
		return "silver:definition:flow:driver:projectionStitchPoint";
	}

	static void initProductionAttributeDefinitions() {
		// top.stitchEdges = flatMap(projectAttribute(_, sourceType, targetType, prodType, findProductionGraph(prod, top.prodGraphs)), attrs)
		silver.definition.flow.driver.PprojectionStitchPoint.synthesizedAttributes[silver.definition.flow.driver.Init.silver_definition_flow_driver_stitchEdges__ON__silver_definition_flow_driver_StitchPoint] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PprojectAttribute.factory.invokePartial(new int[]{1, 2, 3, 4}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PprojectionStitchPoint.i_sourceType)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PprojectionStitchPoint.i_targetType)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PprojectionStitchPoint.i_prodType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.driver.NProductionGraph)silver.definition.flow.driver.PfindProductionGraph.invoke(context.childAsIsLazy(silver.definition.flow.driver.PprojectionStitchPoint.i_prod), context.contextInheritedLazy(silver.definition.flow.driver.Init.silver_definition_flow_driver_prodGraphs__ON__silver_definition_flow_driver_StitchPoint))); } }}); } }, context.childAsIsLazy(silver.definition.flow.driver.PprojectionStitchPoint.i_attrs))); } };

	}

	public static final common.NodeFactory<PprojectionStitchPoint> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprojectionStitchPoint> {

		@Override
		public PprojectionStitchPoint invoke(final Object[] children, final Object[] annotations) {
			return new PprojectionStitchPoint(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
