
package silver.definition.env.env_parser;

// top::INamedSignatureElementsInner ::= t::INamedSignatureElement 
public final class PaNamedSignatureElementsInnerOne extends silver.definition.env.env_parser.NINamedSignatureElementsInner {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NINamedSignatureElement.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aNamedSignatureElementsInnerOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElementsInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElementsInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElement.num_inh_attrs];

	}

	public PaNamedSignatureElementsInnerOne(final Object c_t) {
		super();
		this.child_t = c_t;

	}

	private Object child_t;
	public final silver.definition.env.env_parser.NINamedSignatureElement getChild_t() {
		return (silver.definition.env.env_parser.NINamedSignatureElement) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aNamedSignatureElementsInnerOne erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aNamedSignatureElementsInnerOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.elements = [ t.element ]
		silver.definition.env.env_parser.PaNamedSignatureElementsInnerOne.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_elements__ON__silver_definition_env_env_parser_INamedSignatureElementsInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureElementsInnerOne.i_t, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_element__ON__silver_definition_env_env_parser_INamedSignatureElement), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaNamedSignatureElementsInnerOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaNamedSignatureElementsInnerOne> {

		@Override
		public PaNamedSignatureElementsInnerOne invoke(final Object[] children, final Object[] annotations) {
			return new PaNamedSignatureElementsInnerOne(children[0]);
		}
	};

}
