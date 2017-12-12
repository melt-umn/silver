
package silver.definition.flow.ast;

// top::VertexType ::= sigName::String 
public final class PrhsVertexType extends silver.definition.flow.ast.NVertexType {

	public static final int i_sigName = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_rhsVertexType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NVertexType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrhsVertexType(final Object c_sigName) {
		super();
		this.child_sigName = c_sigName;

	}

	private Object child_sigName;
	public final common.StringCatter getChild_sigName() {
		return (common.StringCatter) (child_sigName = common.Util.demand(child_sigName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sigName: return getChild_sigName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sigName: return child_sigName;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:rhsVertexType erroneously claimed to forward");
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
		return "silver:definition:flow:ast:rhsVertexType";
	}

	static void initProductionAttributeDefinitions() {
		// top.synVertex = rhsVertex(sigName, _)
		silver.definition.flow.ast.PrhsVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_synVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.flow.ast.PrhsVertex.factory.invokePartial(new int[]{0}, new Object[]{context.childAsIsLazy(silver.definition.flow.ast.PrhsVertexType.i_sigName)}); } };
		// top.inhVertex = rhsVertex(sigName, _)
		silver.definition.flow.ast.PrhsVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.flow.ast.PrhsVertex.factory.invokePartial(new int[]{0}, new Object[]{context.childAsIsLazy(silver.definition.flow.ast.PrhsVertexType.i_sigName)}); } };
		// top.fwdVertex = rhsVertex(sigName, "forward")
		silver.definition.flow.ast.PrhsVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PrhsVertex(context.childAsIsLazy(silver.definition.flow.ast.PrhsVertexType.i_sigName), (new common.StringCatter("forward")))); } };
		// top.eqVertex = []
		silver.definition.flow.ast.PrhsVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_eqVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.unparse = "rhs('" ++ sigName ++ "')"
		silver.definition.flow.ast.PrhsVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("rhs('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.ast.PrhsVertexType.i_sigName)), (common.StringCatter)(new common.StringCatter("')")))); } };

	}

	public static final common.NodeFactory<PrhsVertexType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrhsVertexType> {

		@Override
		public PrhsVertexType invoke(final Object[] children, final Object[] annotations) {
			return new PrhsVertexType(children[0]);
		}
	};

}
