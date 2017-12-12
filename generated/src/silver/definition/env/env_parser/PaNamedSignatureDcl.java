
package silver.definition.env.env_parser;

// top::INamedSignature ::= 'signature' '(' fn::IName ',' i::INamedSignatureElements ',' o::INamedSignatureElement ',' n::INamedSignatureElements ')' 
public final class PaNamedSignatureDcl extends silver.definition.env.env_parser.NINamedSignature {

	public static final int i__G_9 = 0;
	public static final int i__G_8 = 1;
	public static final int i_fn = 2;
	public static final int i__G_6 = 3;
	public static final int i_i = 4;
	public static final int i__G_4 = 5;
	public static final int i_o = 6;
	public static final int i__G_2 = 7;
	public static final int i_n = 8;
	public static final int i__G_0 = 9;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TSignatureTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINamedSignatureElements.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINamedSignatureElement.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINamedSignatureElements.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aNamedSignatureDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedSignature.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NINamedSignature.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[10][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fn] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_i] = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElements.num_inh_attrs];
	childInheritedAttributes[i_o] = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElement.num_inh_attrs];
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NINamedSignatureElements.num_inh_attrs];

	}

	public PaNamedSignatureDcl(final Object c__G_9, final Object c__G_8, final Object c_fn, final Object c__G_6, final Object c_i, final Object c__G_4, final Object c_o, final Object c__G_2, final Object c_n, final Object c__G_0) {
		super();
		this.child__G_9 = c__G_9;
		this.child__G_8 = c__G_8;
		this.child_fn = c_fn;
		this.child__G_6 = c__G_6;
		this.child_i = c_i;
		this.child__G_4 = c__G_4;
		this.child_o = c_o;
		this.child__G_2 = c__G_2;
		this.child_n = c_n;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_9;
	public final silver.definition.env.env_parser.TSignatureTerm getChild__G_9() {
		return (silver.definition.env.env_parser.TSignatureTerm) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child__G_8;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_8() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_fn;
	public final silver.definition.env.env_parser.NIName getChild_fn() {
		return (silver.definition.env.env_parser.NIName) (child_fn = common.Util.demand(child_fn));
	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TComma_t getChild__G_6() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_i;
	public final silver.definition.env.env_parser.NINamedSignatureElements getChild_i() {
		return (silver.definition.env.env_parser.NINamedSignatureElements) (child_i = common.Util.demand(child_i));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TComma_t getChild__G_4() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_o;
	public final silver.definition.env.env_parser.NINamedSignatureElement getChild_o() {
		return (silver.definition.env.env_parser.NINamedSignatureElement) (child_o = common.Util.demand(child_o));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NINamedSignatureElements getChild_n() {
		return (silver.definition.env.env_parser.NINamedSignatureElements) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_9: return getChild__G_9();
			case i__G_8: return getChild__G_8();
			case i_fn: return getChild_fn();
			case i__G_6: return getChild__G_6();
			case i_i: return getChild_i();
			case i__G_4: return getChild__G_4();
			case i_o: return getChild_o();
			case i__G_2: return getChild__G_2();
			case i_n: return getChild_n();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_9: return child__G_9;
			case i__G_8: return child__G_8;
			case i_fn: return child_fn;
			case i__G_6: return child__G_6;
			case i_i: return child_i;
			case i__G_4: return child__G_4;
			case i_o: return child_o;
			case i__G_2: return child__G_2;
			case i_n: return child_n;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 10;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aNamedSignatureDcl erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aNamedSignatureDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.signature = namedSignature(fn.aname, i.elements, o.element, n.elements)
		silver.definition.env.env_parser.PaNamedSignatureDcl.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_signature__ON__silver_definition_env_env_parser_INamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)new silver.definition.env.PnamedSignature(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureDcl.i_fn, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureDcl.i_i, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_elements__ON__silver_definition_env_env_parser_INamedSignatureElements), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureDcl.i_o, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_element__ON__silver_definition_env_env_parser_INamedSignatureElement), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaNamedSignatureDcl.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_elements__ON__silver_definition_env_env_parser_INamedSignatureElements))); } };

	}

	public static final common.NodeFactory<PaNamedSignatureDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaNamedSignatureDcl> {

		@Override
		public PaNamedSignatureDcl invoke(final Object[] children, final Object[] annotations) {
			return new PaNamedSignatureDcl(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9]);
		}
	};

}
