
package silver.definition.flow.ast;

// top::VertexType ::= fName::String 
public final class PlocalVertexType extends silver.definition.flow.ast.NVertexType {

	public static final int i_fName = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_localVertexType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NVertexType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PlocalVertexType(final Object c_fName) {
		super();
		this.child_fName = c_fName;

	}

	private Object child_fName;
	public final common.StringCatter getChild_fName() {
		return (common.StringCatter) (child_fName = common.Util.demand(child_fName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fName: return getChild_fName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fName: return child_fName;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:localVertexType erroneously claimed to forward");
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
		return "silver:definition:flow:ast:localVertexType";
	}

	static void initProductionAttributeDefinitions() {
		// top.synVertex = localVertex(fName, _)
		silver.definition.flow.ast.PlocalVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_synVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.flow.ast.PlocalVertex.factory.invokePartial(new int[]{0}, new Object[]{context.childAsIsLazy(silver.definition.flow.ast.PlocalVertexType.i_fName)}); } };
		// top.inhVertex = localVertex(fName, _)
		silver.definition.flow.ast.PlocalVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.flow.ast.PlocalVertex.factory.invokePartial(new int[]{0}, new Object[]{context.childAsIsLazy(silver.definition.flow.ast.PlocalVertexType.i_fName)}); } };
		// top.fwdVertex = localVertex(fName, "forward")
		silver.definition.flow.ast.PlocalVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlocalVertex(context.childAsIsLazy(silver.definition.flow.ast.PlocalVertexType.i_fName), (new common.StringCatter("forward")))); } };
		// top.eqVertex = [ localEqVertex(fName) ]
		silver.definition.flow.ast.PlocalVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_eqVertex__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PlocalEqVertex(context.childAsIsLazy(silver.definition.flow.ast.PlocalVertexType.i_fName))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.unparse = "local('" ++ fName ++ "')"
		silver.definition.flow.ast.PlocalVertexType.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_VertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("local('")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.ast.PlocalVertexType.i_fName)), (common.StringCatter)(new common.StringCatter("')")))); } };

	}

	public static final common.NodeFactory<PlocalVertexType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlocalVertexType> {

		@Override
		public PlocalVertexType invoke(final Object[] children, final Object[] annotations) {
			return new PlocalVertexType(children[0]);
		}
	};

}
