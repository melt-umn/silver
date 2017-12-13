
package silver.definition.concrete_syntax.ast.env_parser;

// top::ISyntaxInner ::= l::ISyntaxInner ',' r::ISyntaxDcl 
public final class PaSyntaxCons extends silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner {

	public static final int i_l = 0;
	public static final int i__G_1 = 1;
	public static final int i_r = 2;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.num_inh_attrs];

	}

	public PaSyntaxCons(final Object c_l, final Object c__G_1, final Object c_r) {
		super();
		this.child_l = c_l;
		this.child__G_1 = c__G_1;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner getChild_l() {
		return (silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_r;
	public final silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl getChild_r() {
		return (silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i__G_1: return getChild__G_1();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i__G_1: return child__G_1;
			case i_r: return child_r;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:ast:env_parser:aSyntaxCons erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:ast:env_parser:aSyntaxCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.syntaxAst = l.syntaxAst ++ r.syntaxAst
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxCons.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxInner] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxCons.i_l, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxInner), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.env_parser.PaSyntaxCons.i_r, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl))); } };

	}

	public static final common.NodeFactory<PaSyntaxCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaSyntaxCons> {

		@Override
		public PaSyntaxCons invoke(final Object[] children, final Object[] annotations) {
			return new PaSyntaxCons(children[0], children[1], children[2]);
		}
	};

}
