
package silver.definition.flow.ast;

// top::FlowVertex ::= fName::String attrName::String 
public final class PanonVertex extends silver.definition.flow.ast.NFlowVertex {

	public static final int i_fName = 0;
	public static final int i_attrName = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_anonVertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowVertex.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PanonVertex(final Object c_fName, final Object c_attrName) {
		super();
		this.child_fName = c_fName;
		this.child_attrName = c_attrName;

	}

	private Object child_fName;
	public final common.StringCatter getChild_fName() {
		return (common.StringCatter) (child_fName = common.Util.demand(child_fName));
	}

	private Object child_attrName;
	public final common.StringCatter getChild_attrName() {
		return (common.StringCatter) (child_attrName = common.Util.demand(child_attrName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fName: return getChild_fName();
			case i_attrName: return getChild_attrName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fName: return child_fName;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:anonVertex erroneously claimed to forward");
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
		return "silver:definition:flow:ast:anonVertex";
	}

	static void initProductionAttributeDefinitions() {
		// top.unparse = "anonV(" ++ quoteString(fName) ++ ", " ++ quoteString(attrName) ++ ")"
		silver.definition.flow.ast.PanonVertex.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_env_unparse__ON__silver_definition_flow_ast_FlowVertex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("anonV(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PanonVertex.i_fName))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PquoteString.invoke(context.childAsIsLazy(silver.definition.flow.ast.PanonVertex.i_attrName))), (common.StringCatter)(new common.StringCatter(")")))))); } };

	}

	public static final common.NodeFactory<PanonVertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PanonVertex> {

		@Override
		public PanonVertex invoke(final Object[] children, final Object[] annotations) {
			return new PanonVertex(children[0], children[1]);
		}
	};

}
