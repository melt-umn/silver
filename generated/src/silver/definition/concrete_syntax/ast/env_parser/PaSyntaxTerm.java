
package silver.definition.concrete_syntax.ast.env_parser;

// top::ISyntaxDcl ::= 'term' '(' n::IName ',' '/' r::Regex '/' ',' tm::ITerminalModifiers ')' 
public final class PaSyntaxTerm extends silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl {

	public static final int i__G_9 = 0;
	public static final int i__G_8 = 1;
	public static final int i_n = 2;
	public static final int i__G_6 = 3;
	public static final int i__G_5 = 4;
	public static final int i_r = 5;
	public static final int i__G_3 = 6;
	public static final int i__G_2 = 7;
	public static final int i_tm = 8;
	public static final int i__G_0 = 9;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TTerminalTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TRegExprDelim.class,silver.definition.regex.NRegex.class,silver.definition.env.env_parser.TRegExprDelim.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxTerm;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[10][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.regex.NRegex.num_inh_attrs];
	childInheritedAttributes[i_tm] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.num_inh_attrs];

	}

	public PaSyntaxTerm(final Object c__G_9, final Object c__G_8, final Object c_n, final Object c__G_6, final Object c__G_5, final Object c_r, final Object c__G_3, final Object c__G_2, final Object c_tm, final Object c__G_0) {
		super();
		this.child__G_9 = c__G_9;
		this.child__G_8 = c__G_8;
		this.child_n = c_n;
		this.child__G_6 = c__G_6;
		this.child__G_5 = c__G_5;
		this.child_r = c_r;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_tm = c_tm;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_9;
	public final silver.definition.env.env_parser.TTerminalTerm getChild__G_9() {
		return (silver.definition.env.env_parser.TTerminalTerm) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child__G_8;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_8() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NIName getChild_n() {
		return (silver.definition.env.env_parser.NIName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TComma_t getChild__G_6() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child__G_5;
	public final silver.definition.env.env_parser.TRegExprDelim getChild__G_5() {
		return (silver.definition.env.env_parser.TRegExprDelim) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_r;
	public final silver.definition.regex.NRegex getChild_r() {
		return (silver.definition.regex.NRegex) (child_r = common.Util.demand(child_r));
	}

	private Object child__G_3;
	public final silver.definition.env.env_parser.TRegExprDelim getChild__G_3() {
		return (silver.definition.env.env_parser.TRegExprDelim) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_tm;
	public final silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers getChild_tm() {
		return (silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers) (child_tm = common.Util.demand(child_tm));
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
			case i_n: return getChild_n();
			case i__G_6: return getChild__G_6();
			case i__G_5: return getChild__G_5();
			case i_r: return getChild_r();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_tm: return getChild_tm();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_9: return child__G_9;
			case i__G_8: return child__G_8;
			case i_n: return child_n;
			case i__G_6: return child__G_6;
			case i__G_5: return child__G_5;
			case i_r: return child_r;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_tm: return child_tm;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aSyntaxTerm erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aSyntaxTerm";
	}

	static void initProductionAttributeDefinitions() {
		// top.syntaxAst = [ syntaxTerminal(n.aname, r, foldr(consTerminalMod, nilTerminalMod(), tm.terminalModifiers)) ]
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxTerminal(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm.i_r)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsTerminalMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers)new silver.definition.concrete_syntax.ast.PnilTerminalMod()); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm.i_tm, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiers))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaSyntaxTerm> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaSyntaxTerm> {

		@Override
		public PaSyntaxTerm invoke(final Object[] children, final Object[] annotations) {
			return new PaSyntaxTerm(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9]);
		}
	};

}
