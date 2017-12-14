
package silver.definition.concrete_syntax.ast.env_parser;

// top::ISyntaxDcl ::= 'lclass' '(' n::IName ',' lm::ILexerClassModifiers ')' 
public final class PaSyntaxLclass extends silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_n = 2;
	public static final int i__G_2 = 3;
	public static final int i_lm = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.TLClassTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxLclass;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_lm] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers.num_inh_attrs];

	}

	public PaSyntaxLclass(final Object c__G_5, final Object c__G_4, final Object c_n, final Object c__G_2, final Object c_lm, final Object c__G_0) {
		super();
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_n = c_n;
		this.child__G_2 = c__G_2;
		this.child_lm = c_lm;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.definition.concrete_syntax.ast.env_parser.TLClassTerm getChild__G_5() {
		return (silver.definition.concrete_syntax.ast.env_parser.TLClassTerm) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_4() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NIName getChild_n() {
		return (silver.definition.env.env_parser.NIName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_lm;
	public final silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers getChild_lm() {
		return (silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers) (child_lm = common.Util.demand(child_lm));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_n: return getChild_n();
			case i__G_2: return getChild__G_2();
			case i_lm: return getChild_lm();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_n: return child_n;
			case i__G_2: return child__G_2;
			case i_lm: return child_lm;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aSyntaxLclass erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aSyntaxLclass";
	}

	static void initProductionAttributeDefinitions() {
		// top.syntaxAst = [ syntaxLexerClass(n.aname, foldr(consLexerClassMod, nilLexerClassMod(), lm.lexerClassModifiers)) ]
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxLclass.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxLexerClass(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxLclass.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsLexerClassMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers)new silver.definition.concrete_syntax.ast.PnilLexerClassMod()); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxLclass.i_lm, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifiers))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaSyntaxLclass> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaSyntaxLclass> {

		@Override
		public PaSyntaxLclass invoke(final Object[] children, final Object[] annotations) {
			return new PaSyntaxLclass(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
