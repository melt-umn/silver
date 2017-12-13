
package silver.definition.concrete_syntax.ast.env_parser;

// top::ISyntaxDcl ::= 'prod' '(' td::ITyVarDcls ',' ns::INamedSignature ',' pm::IProductionModifiers ')' 
public final class PaSyntaxProd extends silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl {

	public static final int i__G_7 = 0;
	public static final int i__G_6 = 1;
	public static final int i_td = 2;
	public static final int i__G_4 = 3;
	public static final int i_ns = 4;
	public static final int i__G_2 = 5;
	public static final int i_pm = 6;
	public static final int i__G_0 = 7;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TProductionTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NITyVarDcls.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINamedSignature.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[8][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_td] = new common.Lazy[silver.definition.env.env_parser.NITyVarDcls.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.env_parser.NINamedSignature.num_inh_attrs];
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers.num_inh_attrs];

	}

	public PaSyntaxProd(final Object c__G_7, final Object c__G_6, final Object c_td, final Object c__G_4, final Object c_ns, final Object c__G_2, final Object c_pm, final Object c__G_0) {
		super();
		this.child__G_7 = c__G_7;
		this.child__G_6 = c__G_6;
		this.child_td = c_td;
		this.child__G_4 = c__G_4;
		this.child_ns = c_ns;
		this.child__G_2 = c__G_2;
		this.child_pm = c_pm;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_7;
	public final silver.definition.env.env_parser.TProductionTerm getChild__G_7() {
		return (silver.definition.env.env_parser.TProductionTerm) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_6() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_td;
	public final silver.definition.env.env_parser.NITyVarDcls getChild_td() {
		return (silver.definition.env.env_parser.NITyVarDcls) (child_td = common.Util.demand(child_td));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TComma_t getChild__G_4() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_ns;
	public final silver.definition.env.env_parser.NINamedSignature getChild_ns() {
		return (silver.definition.env.env_parser.NINamedSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_pm;
	public final silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers getChild_pm() {
		return (silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers) (child_pm = common.Util.demand(child_pm));
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
			case i_td: return getChild_td();
			case i__G_4: return getChild__G_4();
			case i_ns: return getChild_ns();
			case i__G_2: return getChild__G_2();
			case i_pm: return getChild_pm();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_7: return child__G_7;
			case i__G_6: return child__G_6;
			case i_td: return child_td;
			case i__G_4: return child__G_4;
			case i_ns: return child_ns;
			case i__G_2: return child__G_2;
			case i_pm: return child_pm;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aSyntaxProd erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aSyntaxProd";
	}

	static void initProductionAttributeDefinitions() {
		// ns.env = toEnv(td.defs)
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.childInheritedAttributes[silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.i_ns][silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_INamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PtoEnv.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.i_td, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_ITyVarDcls))); } };
		// top.syntaxAst = [ syntaxProduction(ns.signature, foldr(consProductionMod, nilProductionMod(), pm.productionModifiers)) ]
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxProduction(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.i_ns, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_signature__ON__silver_definition_env_env_parser_INamedSignature), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsProductionMod.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers)new silver.definition.concrete_syntax.ast.PnilProductionMod()); } }, context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.i_pm, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifiers))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaSyntaxProd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaSyntaxProd> {

		@Override
		public PaSyntaxProd invoke(final Object[] children, final Object[] annotations) {
			return new PaSyntaxProd(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7]);
		}
	};

}
