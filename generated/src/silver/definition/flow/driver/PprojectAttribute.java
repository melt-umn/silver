
package silver.definition.flow.driver;

public final class PprojectAttribute extends common.FunctionNode {

	public static final int i_attr = 0;
	public static final int i_sourceType = 1;
	public static final int i_targetType = 2;
	public static final int i_prodType = 3;
	public static final int i_prod = 4;


	public static final Class<?> childTypes[] = { common.StringCatter.class,silver.definition.flow.ast.NVertexType.class,silver.definition.flow.ast.NVertexType.class,silver.definition.flow.ast.NVertexType.class,silver.definition.flow.driver.NProductionGraph.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_projectAttribute;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sourceType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_targetType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_prodType] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];
	childInheritedAttributes[i_prod] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];

	}

	public PprojectAttribute(final Object c_attr, final Object c_sourceType, final Object c_targetType, final Object c_prodType, final Object c_prod) {
		this.child_attr = c_attr;
		this.child_sourceType = c_sourceType;
		this.child_targetType = c_targetType;
		this.child_prodType = c_prodType;
		this.child_prod = c_prod;

	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
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

	private Object child_prod;
	public final silver.definition.flow.driver.NProductionGraph getChild_prod() {
		return (silver.definition.flow.driver.NProductionGraph) (child_prod = common.Util.demand(child_prod));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attr: return getChild_attr();
			case i_sourceType: return getChild_sourceType();
			case i_targetType: return getChild_targetType();
			case i_prodType: return getChild_prodType();
			case i_prod: return getChild_prod();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attr: return child_attr;
			case i_sourceType: return child_sourceType;
			case i_targetType: return child_targetType;
			case i_prodType: return child_prodType;
			case i_prod: return child_prod;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:definition:flow:driver:projectAttribute";
	}

	public static common.ConsCell invoke(final Object c_attr, final Object c_sourceType, final Object c_targetType, final Object c_prodType, final Object c_prod) {
		try {
		final common.DecoratedNode context = new PprojectAttribute(c_attr, c_sourceType, c_targetType, c_prodType, c_prod).decorate();
		//map(pair(sourceType.inhVertex(attr), _), map(targetType.inhVertex, filterLhsInh(set:toList(prod.edgeMap(prodType.inhVertex(attr))))))
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return core.Ppair.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)((common.NodeFactory<silver.definition.flow.ast.NFlowVertex>)context.childDecorated(silver.definition.flow.driver.PprojectAttribute.i_sourceType).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType)).invoke(new Object[]{context.childAsIsLazy(silver.definition.flow.driver.PprojectAttribute.i_attr)}, null)); } }}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PprojectAttribute.i_targetType, silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.driver.PfilterLhsInh.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.util.raw.treeset.PtoList.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((common.NodeFactory<Object>)context.childDecorated(silver.definition.flow.driver.PprojectAttribute.i_prod).synthesized(silver.definition.flow.driver.Init.silver_definition_flow_driver_edgeMap__ON__silver_definition_flow_driver_ProductionGraph)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)((common.NodeFactory<silver.definition.flow.ast.NFlowVertex>)context.childDecorated(silver.definition.flow.driver.PprojectAttribute.i_prodType).synthesized(silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType)).invoke(new Object[]{context.childAsIsLazy(silver.definition.flow.driver.PprojectAttribute.i_attr)}, null)); } }}, null)); } })); } })); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:projectAttribute", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprojectAttribute.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}