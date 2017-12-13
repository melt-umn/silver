
package silver.definition.flow.env_parser;

// top::IFlowVertexes ::= '[' l::IFlowVertexesInner ']' 
public final class PaFlowsSome_vertex extends silver.definition.flow.env_parser.NIFlowVertexes {

	public static final int i__G_2 = 0;
	public static final int i_l = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TLB_t.class,silver.definition.flow.env_parser.NIFlowVertexesInner.class,silver.definition.env.env_parser.TRB_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_aFlowsSome_vertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertexes.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertexes.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertexesInner.num_inh_attrs];

	}

	public PaFlowsSome_vertex(final Object c__G_2, final Object c_l, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_l = c_l;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TLB_t getChild__G_2() {
		return (silver.definition.env.env_parser.TLB_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_l;
	public final silver.definition.flow.env_parser.NIFlowVertexesInner getChild_l() {
		return (silver.definition.flow.env_parser.NIFlowVertexesInner) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRB_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRB_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_l: return getChild_l();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_l: return child_l;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:aFlowsSome_vertex erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:aFlowsSome_vertex";
	}

	static void initProductionAttributeDefinitions() {
		// top.flowDeps = l.flowDeps
		silver.definition.flow.env_parser.PaFlowsSome_vertex.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.env_parser.PaFlowsSome_vertex.i_l).synthesized(silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexesInner)); } };

	}

	public static final common.NodeFactory<PaFlowsSome_vertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaFlowsSome_vertex> {

		@Override
		public PaFlowsSome_vertex invoke(final Object[] children, final Object[] annotations) {
			return new PaFlowsSome_vertex(children[0], children[1], children[2]);
		}
	};

}
