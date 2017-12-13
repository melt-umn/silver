
package silver.definition.flow.env_parser;

// top::IFlowVertex ::= 'rhsV' '(' sigName::IName ',' attr::IName ')' 
public final class PaFlowRhs_vertex extends silver.definition.flow.env_parser.NIFlowVertex {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_sigName = 2;
	public static final int i__G_2 = 3;
	public static final int i_attr = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.flow.env_parser.TRhsVTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_aFlowRhs_vertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertex.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertex.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sigName] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_attr] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];

	}

	public PaFlowRhs_vertex(final Object c__G_5, final Object c__G_4, final Object c_sigName, final Object c__G_2, final Object c_attr, final Object c__G_0) {
		super();
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_sigName = c_sigName;
		this.child__G_2 = c__G_2;
		this.child_attr = c_attr;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.definition.flow.env_parser.TRhsVTerm getChild__G_5() {
		return (silver.definition.flow.env_parser.TRhsVTerm) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_4() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_sigName;
	public final silver.definition.env.env_parser.NIName getChild_sigName() {
		return (silver.definition.env.env_parser.NIName) (child_sigName = common.Util.demand(child_sigName));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_attr;
	public final silver.definition.env.env_parser.NIName getChild_attr() {
		return (silver.definition.env.env_parser.NIName) (child_attr = common.Util.demand(child_attr));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_sigName: return getChild_sigName();
			case i__G_2: return getChild__G_2();
			case i_attr: return getChild_attr();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_sigName: return child_sigName;
			case i__G_2: return child__G_2;
			case i_attr: return child_attr;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:aFlowRhs_vertex erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:aFlowRhs_vertex";
	}

	static void initProductionAttributeDefinitions() {
		// top.flowDeps = [ rhsVertex(sigName.aname, attr.aname) ]
		silver.definition.flow.env_parser.PaFlowRhs_vertex.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowVertex)new silver.definition.flow.ast.PrhsVertex(context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaFlowRhs_vertex.i_sigName, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaFlowRhs_vertex.i_attr, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaFlowRhs_vertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaFlowRhs_vertex> {

		@Override
		public PaFlowRhs_vertex invoke(final Object[] children, final Object[] annotations) {
			return new PaFlowRhs_vertex(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
