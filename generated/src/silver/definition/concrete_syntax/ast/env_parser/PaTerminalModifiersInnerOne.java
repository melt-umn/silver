
package silver.definition.concrete_syntax.ast.env_parser;

// top::ITerminalModifiersInner ::= d::ITerminalModifier 
public final class PaTerminalModifiersInnerOne extends silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifiersInnerOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.num_inh_attrs];

	}

	public PaTerminalModifiersInnerOne(final Object c_d) {
		super();
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier getChild_d() {
		return (silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aTerminalModifiersInnerOne erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aTerminalModifiersInnerOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.terminalModifiers = d.terminalModifiers
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersInnerOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiersInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersInnerOne.i_d).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifier)); } };

	}

	public static final common.NodeFactory<PaTerminalModifiersInnerOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaTerminalModifiersInnerOne> {

		@Override
		public PaTerminalModifiersInnerOne invoke(final Object[] children, final Object[] annotations) {
			return new PaTerminalModifiersInnerOne(children[0]);
		}
	};

}
