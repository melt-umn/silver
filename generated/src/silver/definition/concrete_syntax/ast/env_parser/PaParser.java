
package silver.definition.concrete_syntax.ast.env_parser;

// top::IParser ::= 'parser' '(' l::ILocation ',' g::IName ',' n::IName ',' snt::IName ',' gr::INames ',' fstPfxs::INames ',' sndPfxs::INames ',' addedDcls::ISyntax ')' 
public final class PaParser extends silver.definition.concrete_syntax.ast.env_parser.NIParser {

	public static final int i__G_17 = 0;
	public static final int i__G_16 = 1;
	public static final int i_l = 2;
	public static final int i__G_14 = 3;
	public static final int i_g = 4;
	public static final int i__G_12 = 5;
	public static final int i_n = 6;
	public static final int i__G_10 = 7;
	public static final int i_snt = 8;
	public static final int i__G_8 = 9;
	public static final int i_gr = 10;
	public static final int i__G_6 = 11;
	public static final int i_fstPfxs = 12;
	public static final int i__G_4 = 13;
	public static final int i_sndPfxs = 14;
	public static final int i__G_2 = 15;
	public static final int i_addedDcls = 16;
	public static final int i__G_0 = 17;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.TParserTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NILocation.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINames.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINames.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINames.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.concrete_syntax.ast.env_parser.NISyntax.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParser;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIParser.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIParser.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[18][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.env.env_parser.NILocation.num_inh_attrs];
	childInheritedAttributes[i_g] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_n] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_snt] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_gr] = new common.Lazy[silver.definition.env.env_parser.NINames.num_inh_attrs];
	childInheritedAttributes[i_fstPfxs] = new common.Lazy[silver.definition.env.env_parser.NINames.num_inh_attrs];
	childInheritedAttributes[i_sndPfxs] = new common.Lazy[silver.definition.env.env_parser.NINames.num_inh_attrs];
	childInheritedAttributes[i_addedDcls] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntax.num_inh_attrs];

	}

	public PaParser(final Object c__G_17, final Object c__G_16, final Object c_l, final Object c__G_14, final Object c_g, final Object c__G_12, final Object c_n, final Object c__G_10, final Object c_snt, final Object c__G_8, final Object c_gr, final Object c__G_6, final Object c_fstPfxs, final Object c__G_4, final Object c_sndPfxs, final Object c__G_2, final Object c_addedDcls, final Object c__G_0) {
		super();
		this.child__G_17 = c__G_17;
		this.child__G_16 = c__G_16;
		this.child_l = c_l;
		this.child__G_14 = c__G_14;
		this.child_g = c_g;
		this.child__G_12 = c__G_12;
		this.child_n = c_n;
		this.child__G_10 = c__G_10;
		this.child_snt = c_snt;
		this.child__G_8 = c__G_8;
		this.child_gr = c_gr;
		this.child__G_6 = c__G_6;
		this.child_fstPfxs = c_fstPfxs;
		this.child__G_4 = c__G_4;
		this.child_sndPfxs = c_sndPfxs;
		this.child__G_2 = c__G_2;
		this.child_addedDcls = c_addedDcls;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_17;
	public final silver.definition.concrete_syntax.ast.env_parser.TParserTerm getChild__G_17() {
		return (silver.definition.concrete_syntax.ast.env_parser.TParserTerm) (child__G_17 = common.Util.demand(child__G_17));
	}

	private Object child__G_16;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_16() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_16 = common.Util.demand(child__G_16));
	}

	private Object child_l;
	public final silver.definition.env.env_parser.NILocation getChild_l() {
		return (silver.definition.env.env_parser.NILocation) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_14;
	public final silver.definition.env.env_parser.TComma_t getChild__G_14() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_14 = common.Util.demand(child__G_14));
	}

	private Object child_g;
	public final silver.definition.env.env_parser.NIName getChild_g() {
		return (silver.definition.env.env_parser.NIName) (child_g = common.Util.demand(child_g));
	}

	private Object child__G_12;
	public final silver.definition.env.env_parser.TComma_t getChild__G_12() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_12 = common.Util.demand(child__G_12));
	}

	private Object child_n;
	public final silver.definition.env.env_parser.NIName getChild_n() {
		return (silver.definition.env.env_parser.NIName) (child_n = common.Util.demand(child_n));
	}

	private Object child__G_10;
	public final silver.definition.env.env_parser.TComma_t getChild__G_10() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_10 = common.Util.demand(child__G_10));
	}

	private Object child_snt;
	public final silver.definition.env.env_parser.NIName getChild_snt() {
		return (silver.definition.env.env_parser.NIName) (child_snt = common.Util.demand(child_snt));
	}

	private Object child__G_8;
	public final silver.definition.env.env_parser.TComma_t getChild__G_8() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_gr;
	public final silver.definition.env.env_parser.NINames getChild_gr() {
		return (silver.definition.env.env_parser.NINames) (child_gr = common.Util.demand(child_gr));
	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TComma_t getChild__G_6() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_fstPfxs;
	public final silver.definition.env.env_parser.NINames getChild_fstPfxs() {
		return (silver.definition.env.env_parser.NINames) (child_fstPfxs = common.Util.demand(child_fstPfxs));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TComma_t getChild__G_4() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_sndPfxs;
	public final silver.definition.env.env_parser.NINames getChild_sndPfxs() {
		return (silver.definition.env.env_parser.NINames) (child_sndPfxs = common.Util.demand(child_sndPfxs));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_addedDcls;
	public final silver.definition.concrete_syntax.ast.env_parser.NISyntax getChild_addedDcls() {
		return (silver.definition.concrete_syntax.ast.env_parser.NISyntax) (child_addedDcls = common.Util.demand(child_addedDcls));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_17: return getChild__G_17();
			case i__G_16: return getChild__G_16();
			case i_l: return getChild_l();
			case i__G_14: return getChild__G_14();
			case i_g: return getChild_g();
			case i__G_12: return getChild__G_12();
			case i_n: return getChild_n();
			case i__G_10: return getChild__G_10();
			case i_snt: return getChild_snt();
			case i__G_8: return getChild__G_8();
			case i_gr: return getChild_gr();
			case i__G_6: return getChild__G_6();
			case i_fstPfxs: return getChild_fstPfxs();
			case i__G_4: return getChild__G_4();
			case i_sndPfxs: return getChild_sndPfxs();
			case i__G_2: return getChild__G_2();
			case i_addedDcls: return getChild_addedDcls();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_17: return child__G_17;
			case i__G_16: return child__G_16;
			case i_l: return child_l;
			case i__G_14: return child__G_14;
			case i_g: return child_g;
			case i__G_12: return child__G_12;
			case i_n: return child_n;
			case i__G_10: return child__G_10;
			case i_snt: return child_snt;
			case i__G_8: return child__G_8;
			case i_gr: return child_gr;
			case i__G_6: return child__G_6;
			case i_fstPfxs: return child_fstPfxs;
			case i__G_4: return child__G_4;
			case i_sndPfxs: return child_sndPfxs;
			case i__G_2: return child__G_2;
			case i_addedDcls: return child_addedDcls;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 18;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aParser erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aParser";
	}

	static void initProductionAttributeDefinitions() {
		// top.parserSpecs = [ parserSpec(l.alocation, g.aname, n.aname, snt.aname, gr.names, zipWith(pair, fstPfxs.names, sndPfxs.names), addedDcls.syntaxAst) ]
		silver.definition.concrete_syntax.ast.env_parser.PaParser.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParser] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.NParserSpec)new silver.definition.concrete_syntax.PparserSpec(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_l, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_alocation__ON__silver_definition_env_env_parser_ILocation), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_g, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_n, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_snt, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_gr, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_names__ON__silver_definition_env_env_parser_INames), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_fstPfxs, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_names__ON__silver_definition_env_env_parser_INames), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_sndPfxs, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_names__ON__silver_definition_env_env_parser_INames))); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaParser.i_addedDcls, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntax))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaParser> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaParser> {

		@Override
		public PaParser invoke(final Object[] children, final Object[] annotations) {
			return new PaParser(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], children[10], children[11], children[12], children[13], children[14], children[15], children[16], children[17]);
		}
	};

}
