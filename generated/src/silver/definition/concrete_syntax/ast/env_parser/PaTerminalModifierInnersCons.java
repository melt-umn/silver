
package silver.definition.concrete_syntax.ast.env_parser;

// top::ITerminalModifiersInner ::= d1::ITerminalModifier ',' d2::ITerminalModifiersInner 
public final class PaTerminalModifierInnersCons extends silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner {

	public static final int i_d1 = 0;
	public static final int i__G_1 = 1;
	public static final int i_d2 = 2;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierInnersCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d1] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.num_inh_attrs];
	childInheritedAttributes[i_d2] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.num_inh_attrs];

	}

	public PaTerminalModifierInnersCons(final Object c_d1, final Object c__G_1, final Object c_d2) {
		super();
		this.child_d1 = c_d1;
		this.child__G_1 = c__G_1;
		this.child_d2 = c_d2;

	}

	private Object child_d1;
	public final silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier getChild_d1() {
		return (silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier) (child_d1 = common.Util.demand(child_d1));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_d2;
	public final silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner getChild_d2() {
		return (silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner) (child_d2 = common.Util.demand(child_d2));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aTerminalModifierInnersCons erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aTerminalModifierInnersCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.terminalModifiers = d1.terminalModifiers ++ d2.terminalModifiers
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierInnersCons.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiersInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierInnersCons.i_d1, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierInnersCons.i_d2, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiersInner))); } };

	}

	public static final common.NodeFactory<PaTerminalModifierInnersCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTerminalModifierInnersCons> {

		@Override
		public PaTerminalModifierInnersCons invoke(final Object[] children, final Object[] annotations) {
			return new PaTerminalModifierInnersCons(children[0], children[1], children[2]);
		}
	};

}
