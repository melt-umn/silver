
package silver.definition.flow.ast;

// top::VertexType ::= 
public final class PforwardVertexType_real extends silver.definition.flow.ast.NVertexType {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_forwardVertexType_real;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NVertexType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PforwardVertexType_real() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:forwardVertexType_real erroneously claimed to forward");
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
		return "silver:definition:flow:ast:forwardVertexType_real";
	}

	static void initProductionAttributeDefinitions() {
		// top.synVertex = localVertex("forward", _)
		silver.definition.flow.ast.PforwardVertexType_real.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_synVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.flow.ast.PlocalVertex.factory.invokePartial(new int[]{0}, new Object[]{(new common.StringCatter("forward"))}); } };
		// top.inhVertex = localVertex("forward", _)
		silver.definition.flow.ast.PforwardVertexType_real.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.flow.ast.PlocalVertex.factory.invokePartial(new int[]{0}, new Object[]{(new common.StringCatter("forward"))}); } };
		// top.fwdVertex = localVertex("forward", "forward")
		silver.definition.flow.ast.PforwardVertexType_real.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlocalVertex((new common.StringCatter("forward")), (new common.StringCatter("forward")))); } };
		// top.eqVertex = [ forwardEqVertex_singleton ]
		silver.definition.flow.ast.PforwardVertexType_real.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_eqVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(silver.definition.flow.ast.Init.forwardEqVertex_singleton, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.unparse = "fwd"
		silver.definition.flow.ast.PforwardVertexType_real.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("fwd")); } };

	}

	public static final common.NodeFactory<PforwardVertexType_real> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PforwardVertexType_real> {

		@Override
		public PforwardVertexType_real invoke(final Object[] children, final Object[] annotations) {
			return new PforwardVertexType_real();
		}
	};

}
