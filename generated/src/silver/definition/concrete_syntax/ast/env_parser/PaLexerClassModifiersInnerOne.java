
package silver.definition.concrete_syntax.ast.env_parser;

// top::ILexerClassModifiersInner ::= d::ILexerClassModifier 
public final class PaLexerClassModifiersInnerOne extends silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifiersInnerOne;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier.num_inh_attrs];

	}

	public PaLexerClassModifiersInnerOne(final Object c_d) {
		super();
		this.child_d = c_d;

	}

	private Object child_d;
	public final silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier getChild_d() {
		return (silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier) (child_d = common.Util.demand(child_d));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aLexerClassModifiersInnerOne erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aLexerClassModifiersInnerOne";
	}

	static void initProductionAttributeDefinitions() {
		// top.lexerClassModifiers = d.lexerClassModifiers
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersInnerOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifiersInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersInnerOne.i_d).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifier)); } };

	}

	public static final common.NodeFactory<PaLexerClassModifiersInnerOne> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaLexerClassModifiersInnerOne> {

		@Override
		public PaLexerClassModifiersInnerOne invoke(final Object[] children, final Object[] annotations) {
			return new PaLexerClassModifiersInnerOne(children[0]);
		}
	};

}
