
package silver.definition.concrete_syntax.ast.env_parser;

// top::IProductionModifier ::= 'acode' '(' s::IString ')' 
public final class PaProductionModifierAcode extends silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_s = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIString.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifierAcode;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.env.env_parser.NIString.num_inh_attrs];

	}

	public PaProductionModifierAcode(final Object c__G_3, final Object c__G_2, final Object c_s, final Object c__G_0) {
		super();
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_s = c_s;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm getChild__G_3() {
		return (silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_2() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_s;
	public final silver.definition.env.env_parser.NIString getChild_s() {
		return (silver.definition.env.env_parser.NIString) (child_s = common.Util.demand(child_s));
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
			case i_s: return getChild_s();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_s: return child_s;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aProductionModifierAcode erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aProductionModifierAcode";
	}

	static void initProductionAttributeDefinitions() {
		// top.productionModifiers = [ prodAction(s.str) ]
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierAcode.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifier)new silver.definition.concrete_syntax.ast.PprodAction(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierAcode.i_s, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_str__ON__silver_definition_env_env_parser_IString))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaProductionModifierAcode> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaProductionModifierAcode> {

		@Override
		public PaProductionModifierAcode invoke(final Object[] children, final Object[] annotations) {
			return new PaProductionModifierAcode(children[0], children[1], children[2], children[3]);
		}
	};

}
