
package silver.definition.env.env_parser;

// top::IDefsInner ::= d1::IDclInfo ',' d2::IDefsInner 
public final class PaDefsInnerCons extends silver.definition.env.env_parser.NIDefsInner {

	public static final int i_d1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_d2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NIDclInfo.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIDefsInner.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aDefsInnerCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIDefsInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIDefsInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d1] = new common.Lazy[silver.definition.env.env_parser.NIDclInfo.num_inh_attrs];
	childInheritedAttributes[i_d2] = new common.Lazy[silver.definition.env.env_parser.NIDefsInner.num_inh_attrs];

	}

	public PaDefsInnerCons(final Object c_d1, final Object c__G_1, final Object c_d2) {
		super();
		this.child_d1 = c_d1;
		this.child__G_1 = c__G_1;
		this.child_d2 = c_d2;

	}

	private Object child_d1;
	public final silver.definition.env.env_parser.NIDclInfo getChild_d1() {
		return (silver.definition.env.env_parser.NIDclInfo) (child_d1 = common.Util.demand(child_d1));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_d2;
	public final silver.definition.env.env_parser.NIDefsInner getChild_d2() {
		return (silver.definition.env.env_parser.NIDefsInner) (child_d2 = common.Util.demand(child_d2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d1: return getChild_d1();
			case i__G_1: return getChild__G_1();
			case i_d2: return getChild_d2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d1: return child_d1;
			case i__G_1: return child__G_1;
			case i_d2: return child_d2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aDefsInnerCons erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aDefsInnerCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.defs = d1.defs ++ d2.defs
		silver.definition.env.env_parser.PaDefsInnerCons.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IDefsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDefsInnerCons.i_d1, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IDclInfo), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDefsInnerCons.i_d2, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IDefsInner))); } };

	}

	public static final common.NodeFactory<PaDefsInnerCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaDefsInnerCons> {

		@Override
		public PaDefsInnerCons invoke(final Object[] children, final Object[] annotations) {
			return new PaDefsInnerCons(children[0], children[1], children[2]);
		}
	};

}
