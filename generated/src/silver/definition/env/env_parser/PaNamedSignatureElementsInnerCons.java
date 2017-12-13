
package silver.definition.env.env_parser;

// top::INamedSignatureElementsInner ::= t1::INamedSignatureElement ',' t2::INamedSignatureElementsInner 
public final class PaNamedSignatureElementsInnerCons extends silver.definition.env.env_parser.NINamedSignatureElementsInner {

	public static final int i_t1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_t2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NINamedSignatureElement.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINamedSignatureElementsInner.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aNamedSignatureElementsInnerCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElementsInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElementsInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t1] = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElement.num_inh_attrs];
	childInheritedAttributes[i_t2] = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElementsInner.num_inh_attrs];

	}

	public PaNamedSignatureElementsInnerCons(final Object c_t1, final Object c__G_1, final Object c_t2) {
		super();
		this.child_t1 = c_t1;
		this.child__G_1 = c__G_1;
		this.child_t2 = c_t2;

	}

	private Object child_t1;
	public final silver.definition.env.env_parser.NINamedSignatureElement getChild_t1() {
		return (silver.definition.env.env_parser.NINamedSignatureElement) (child_t1 = common.Util.demand(child_t1));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t2;
	public final silver.definition.env.env_parser.NINamedSignatureElementsInner getChild_t2() {
		return (silver.definition.env.env_parser.NINamedSignatureElementsInner) (child_t2 = common.Util.demand(child_t2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t1: return getChild_t1();
			case i__G_1: return getChild__G_1();
			case i_t2: return getChild_t2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t1: return child_t1;
			case i__G_1: return child__G_1;
			case i_t2: return child_t2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aNamedSignatureElementsInnerCons erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aNamedSignatureElementsInnerCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.elements = [ t1.element ] ++ t2.elements
		silver.definition.env.env_parser.PaNamedSignatureElementsInnerCons.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_elements__ON__silver_definition_env_env_parser_INamedSignatureElementsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureElementsInnerCons.i_t1, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_element__ON__silver_definition_env_env_parser_INamedSignatureElement), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureElementsInnerCons.i_t2, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_elements__ON__silver_definition_env_env_parser_INamedSignatureElementsInner))); } };

	}

	public static final common.NodeFactory<PaNamedSignatureElementsInnerCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaNamedSignatureElementsInnerCons> {

		@Override
		public PaNamedSignatureElementsInnerCons invoke(final Object[] children, final Object[] annotations) {
			return new PaNamedSignatureElementsInnerCons(children[0], children[1], children[2]);
		}
	};

}
