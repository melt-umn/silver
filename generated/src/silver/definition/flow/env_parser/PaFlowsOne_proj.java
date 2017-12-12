
package silver.definition.flow.env_parser;

// top::IPatternVarProjectionsInner ::= l::IPatternVarProjection 
public final class PaFlowsOne_proj extends silver.definition.flow.env_parser.NIPatternVarProjectionsInner {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {silver.definition.flow.env_parser.NIPatternVarProjection.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_aFlowsOne_proj;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIPatternVarProjectionsInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIPatternVarProjectionsInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.flow.env_parser.NIPatternVarProjection.num_inh_attrs];

	}

	public PaFlowsOne_proj(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final silver.definition.flow.env_parser.NIPatternVarProjection getChild_l() {
		return (silver.definition.flow.env_parser.NIPatternVarProjection) (child_l = common.Util.demand(child_l));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:aFlowsOne_proj erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:aFlowsOne_proj";
	}

	static void initProductionAttributeDefinitions() {
		// top.aPatternVarProjection = l.aPatternVarProjection
		silver.definition.flow.env_parser.PaFlowsOne_proj.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjectionsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.env_parser.PaFlowsOne_proj.i_l).synthesized(silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aPatternVarProjection__ON__silver_definition_flow_env_parser_IPatternVarProjection)); } };

	}

	public static final common.NodeFactory<PaFlowsOne_proj> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaFlowsOne_proj> {

		@Override
		public PaFlowsOne_proj invoke(final Object[] children, final Object[] annotations) {
			return new PaFlowsOne_proj(children[0]);
		}
	};

}
