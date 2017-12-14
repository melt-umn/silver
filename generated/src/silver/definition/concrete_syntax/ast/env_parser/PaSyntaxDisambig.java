
package silver.definition.concrete_syntax.ast.env_parser;

// top::ISyntaxDcl ::= 'disambig' '(' n::IName ',' ts::INames ',' s::IString ')' 
public final class PaSyntaxDisambig extends silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_n = 2;
	public static final int i__G_4 = 3;
	public static final int i_ts = 4;
	public static final int i__G_2 = 5;
	public static final int i_s = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.TDisTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINames.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIString.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxDisambig;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_ts] = new common.Lazy[silver.definition.env.env_parser.NINames.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.env.env_parser.NIString.num_inh_attrs];

	}

	public PaSyntaxDisambig(final Object c__G_7, final Object c__G_6, final Object c_n, final Object c__G_4, final Object c_ts, final Object c__G_2, final Object c_s, final Object c__G_0) {
		super();
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_n = c_n;
		this.child__G_4 = c__G_4;
		this.child_ts = c_ts;
		this.child__G_2 = c__G_2;
		this.child_s = c_s;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.definition.concrete_syntax.ast.env_parser.TDisTerm getChild__G_7() {
		return (silver.definition.concrete_syntax.ast.env_parser.TDisTerm) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_6() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NIName getChild_n() {
		return (silver.definition.env.env_parser.NIName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TComma_t getChild__G_4() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_ts;
	public final silver.definition.env.env_parser.NINames getChild_ts() {
		return (silver.definition.env.env_parser.NINames) (child_ts = common.Util.demand(child_ts));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
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
			case i__G_7: return getChild__G_7();
			case i__G_6: return getChild__G_6();
			case i_n: return getChild_n();
			case i__G_4: return getChild__G_4();
			case i_ts: return getChild_ts();
			case i__G_2: return getChild__G_2();
			case i_s: return getChild_s();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i_n: return child_n;
			case i__G_4: return child__G_4;
			case i_ts: return child_ts;
			case i__G_2: return child__G_2;
			case i_s: return child_s;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 8;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aSyntaxDisambig erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aSyntaxDisambig";
	}

	static void initProductionAttributeDefinitions() {
		// top.syntaxAst = [ syntaxDisambiguationGroup(n.aname, ts.names, s.str) ]
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig.i_ts, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_names__ON__silver_definition_env_env_parser_INames), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig.i_s, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_str__ON__silver_definition_env_env_parser_IString))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaSyntaxDisambig> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaSyntaxDisambig> {

		@Override
		public PaSyntaxDisambig invoke(final Object[] children, final Object[] annotations) {
			return new PaSyntaxDisambig(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7]);
		}
	};

}
