
package silver.definition.flow.env_parser;

// top::IFlowVertexesInner ::= l::IFlowVertex 
public final class PaFlowsOne_vertex extends silver.definition.flow.env_parser.NIFlowVertexesInner {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {silver.definition.flow.env_parser.NIFlowVertex.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_aFlowsOne_vertex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertexesInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertexesInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.flow.env_parser.NIFlowVertex.num_inh_attrs];

	}

	public PaFlowsOne_vertex(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final silver.definition.flow.env_parser.NIFlowVertex getChild_l() {
		return (silver.definition.flow.env_parser.NIFlowVertex) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:aFlowsOne_vertex erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:aFlowsOne_vertex";
	}

	static void initProductionAttributeDefinitions() {
		// top.flowDeps = l.flowDeps
		silver.definition.flow.env_parser.PaFlowsOne_vertex.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertexesInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.env_parser.PaFlowsOne_vertex.i_l).synthesized(silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDeps__ON__silver_definition_flow_env_parser_IFlowVertex)); } };

	}

	public static final common.NodeFactory<PaFlowsOne_vertex> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaFlowsOne_vertex> {

		@Override
		public PaFlowsOne_vertex invoke(final Object[] children, final Object[] annotations) {
			return new PaFlowsOne_vertex(children[0]);
		}
	};

}
