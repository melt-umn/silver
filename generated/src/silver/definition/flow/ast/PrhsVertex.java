
package silver.definition.flow.ast;

// top::FlowVertex ::= sigName::String attrName::String 
public final class PrhsVertex extends silver.definition.flow.ast.NFlowVertex {

	public static final int i_sigName = 0;
	public static final int i_attrName = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_rhsVertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrhsVertex(final Object c_sigName, final Object c_attrName) {
		super();
		this.child_sigName = c_sigName;
		this.child_attrName = c_attrName;

	}

	private Object child_sigName;
	public final common.StringCatter getChild_sigName() {
		return (common.StringCatter) (child_sigName = common.Util.demand(child_sigName));
	}

	private Object child_attrName;
	public final common.StringCatter getChild_attrName() {
		return (common.StringCatter) (child_attrName = common.Util.demand(child_attrName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sigName: return getChild_sigName();
			case i_attrName: return getChild_attrName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sigName: return child_sigName;
			case i_attrName: return child_attrName;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:rhsVertex erroneously claimed to forward");
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
		return "silver:definition:flow:ast:rhsVertex";
	}

	static void initProductionAttributeDefinitions() {
		// top.unparse = "rhsV(" ++ quoteString(sigName) ++ ", " ++ quoteString(attrName) ++ ")"
		silver.definition.flow.ast.PrhsVertex.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("rhsV(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PrhsVertex.i_sigName))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PrhsVertex.i_attrName))), (common.StringCatter)(new common.StringCatter(")")))))); } };

	}

	public static final common.NodeFactory<PrhsVertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrhsVertex> {

		@Override
		public PrhsVertex invoke(final Object[] children, final Object[] annotations) {
			return new PrhsVertex(children[0], children[1]);
		}
	};

}
