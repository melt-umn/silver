
package silver.definition.flow.env_parser;

// top::IVertexType ::= 'rhs' '(' child::IName ')' 
public final class ParhsVertexType extends silver.definition.flow.env_parser.NIVertexType {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_child = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.flow.env_parser.TRhsFlow.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_arhsVertexType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIVertexType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIVertexType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_child] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];

	}

	public ParhsVertexType(final Object c__G_3, final Object c__G_2, final Object c_child, final Object c__G_0) {
		super();
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_child = c_child;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.definition.flow.env_parser.TRhsFlow getChild__G_3() {
		return (silver.definition.flow.env_parser.TRhsFlow) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_2() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_child;
	public final silver.definition.env.env_parser.NIName getChild_child() {
		return (silver.definition.env.env_parser.NIName) (child_child = common.Util.demand(child_child));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_child: return getChild_child();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_child: return child_child;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:arhsVertexType erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:arhsVertexType";
	}

	static void initProductionAttributeDefinitions() {
		// top.aVertexType = rhsVertexType(child.aname)
		silver.definition.flow.env_parser.ParhsVertexType.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_parser_aVertexType__ON__silver_definition_flow_env_parser_IVertexType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NVertexType)new silver.definition.flow.ast.PrhsVertexType(context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.ParhsVertexType.i_child, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName))); } };

	}

	public static final common.NodeFactory<ParhsVertexType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<ParhsVertexType> {

		@Override
		public ParhsVertexType invoke(final Object[] children, final Object[] annotations) {
			return new ParhsVertexType(children[0], children[1], children[2], children[3]);
		}
	};

}
