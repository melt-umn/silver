
package silver.definition.flow.env_parser;

// top::IVertexType ::= 'lhs' 
public final class PalhsVertexType extends silver.definition.flow.env_parser.NIVertexType {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.flow.env_parser.TLhsFlow.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_alhsVertexType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIVertexType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIVertexType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PalhsVertexType(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.flow.env_parser.TLhsFlow getChild__G_0() {
		return (silver.definition.flow.env_parser.TLhsFlow) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:alhsVertexType erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:alhsVertexType";
	}

	static void initProductionAttributeDefinitions() {
		// top.aVertexType = lhsVertexType
		silver.definition.flow.env_parser.PalhsVertexType.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aVertexType__ON__silver_definition_flow_env_parser_IVertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)silver.definition.flow.ast.Init.lhsVertexType.eval()); } };

	}

	public static final common.NodeFactory<PalhsVertexType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PalhsVertexType> {

		@Override
		public PalhsVertexType invoke(final Object[] children, final Object[] annotations) {
			return new PalhsVertexType(children[0]);
		}
	};

}
