
package paper_dc_3;

// e::Factor_c ::= '(' inner::Expr_c ')' 
public final class Pnested_c extends paper_dc_3.NFactor_c {

	public static final int i__G_2 = 0;
	public static final int i_inner = 1;
	public static final int i__G_0 = 2;


	public static final Class<?> childTypes[] = {paper_dc_3.TLParen_t.class,paper_dc_3.NExpr_c.class,paper_dc_3.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_nested_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NFactor_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NFactor_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_inner] = new common.Lazy[paper_dc_3.NExpr_c.num_inh_attrs];

	}

	public Pnested_c(final Object c__G_2, final Object c_inner, final Object c__G_0) {
		super();
		this.child__G_2 = c__G_2;
		this.child_inner = c_inner;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_2;
	public final paper_dc_3.TLParen_t getChild__G_2() {
		return (paper_dc_3.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_inner;
	public final paper_dc_3.NExpr_c getChild_inner() {
		return (paper_dc_3.NExpr_c) (child_inner = common.Util.demand(child_inner));
	}

	private Object child__G_0;
	public final paper_dc_3.TRParen_t getChild__G_0() {
		return (paper_dc_3.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_inner: return getChild_inner();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_inner: return child_inner;
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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:nested_c erroneously claimed to forward");
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
		return "paper_dc_3:nested_c";
	}

	static void initProductionAttributeDefinitions() {
		// e.pp = "(" ++ inner.pp ++ ")"
		paper_dc_3.Pnested_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Pnested_c.i_inner).synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Expr_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		// e.ast_Expr = inner.ast_Expr
		paper_dc_3.Pnested_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)context.childDecorated(paper_dc_3.Pnested_c.i_inner).synthesized(paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c)); } };

	}

	public static final common.NodeFactory<Pnested_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pnested_c> {

		@Override
		public Pnested_c invoke(final Object[] children, final Object[] annotations) {
			return new Pnested_c(children[0], children[1], children[2]);
		}
	};

}
