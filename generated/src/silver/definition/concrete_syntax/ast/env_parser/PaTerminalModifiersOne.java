
package silver.definition.concrete_syntax.ast.env_parser;

// top::ITerminalModifiers ::= '[' d::ITerminalModifiersInner ']' 
public final class PaTerminalModifiersOne extends silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers {

	public static final int i__G_2 = 0;
	public static final int i_d = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TLB_t.class,silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.class,silver.definition.env.env_parser.TRB_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifiersOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.num_inh_attrs];

	}

	public PaTerminalModifiersOne(final Object c__G_2, final Object c_d, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_d = c_d;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TLB_t getChild__G_2() {
		return (silver.definition.env.env_parser.TLB_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_d;
	public final silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner getChild_d() {
		return (silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner) (child_d = common.Util.demand(child_d));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRB_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRB_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_d: return getChild_d();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_d: return child_d;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aTerminalModifiersOne erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aTerminalModifiersOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.terminalModifiers = d.terminalModifiers
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersOne.i_d).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiersInner)); } };

	}

	public static final common.NodeFactory<PaTerminalModifiersOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTerminalModifiersOne> {

		@Override
		public PaTerminalModifiersOne invoke(final Object[] children, final Object[] annotations) {
			return new PaTerminalModifiersOne(children[0], children[1], children[2]);
		}
	};

}
