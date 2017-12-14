
package paper_dc_3;

// prd::Factor_c ::= 'sq' '(' e::Expr_c ')' 
public final class Psquare_c extends paper_dc_3.NFactor_c {

	public static final int i__G_3 = 0;
	public static final int i__G_2 = 1;
	public static final int i_e = 2;
	public static final int i__G_0 = 3;


	public static final Class<?> childTypes[] = {paper_dc_3.TSquare_t.class,paper_dc_3.TLParen_t.class,paper_dc_3.NExpr_c.class,paper_dc_3.TRParen_t.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_square_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NFactor_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NFactor_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[paper_dc_3.NExpr_c.num_inh_attrs];

	}

	public Psquare_c(final Object c__G_3, final Object c__G_2, final Object c_e, final Object c__G_0) {
		super();
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_e = c_e;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_3;
	public final paper_dc_3.TSquare_t getChild__G_3() {
		return (paper_dc_3.TSquare_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final paper_dc_3.TLParen_t getChild__G_2() {
		return (paper_dc_3.TLParen_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_e;
	public final paper_dc_3.NExpr_c getChild_e() {
		return (paper_dc_3.NExpr_c) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_0;
	public final paper_dc_3.TRParen_t getChild__G_0() {
		return (paper_dc_3.TRParen_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_e: return getChild_e();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_e: return child_e;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:square_c erroneously claimed to forward");
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
		return "paper_dc_3:square_c";
	}

	static void initProductionAttributeDefinitions() {
		// prd.pp = "sq(" ++ e.pp ++ ")"
		paper_dc_3.Psquare_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("sq(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(paper_dc_3.Psquare_c.i_e).synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Expr_c)), (common.StringCatter)(new common.StringCatter(")")))); } };
		// prd.ast_Expr = mul(e.ast_Expr, e.ast_Expr,origin=origin_Factor_c(prd), redex=nothing(), labels=[])
		paper_dc_3.Psquare_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Factor_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)new paper_dc_3.Pmul(context.childDecoratedSynthesizedLazy(paper_dc_3.Psquare_c.i_e, paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c), context.childDecoratedSynthesizedLazy(paper_dc_3.Psquare_c.i_e, paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new paper_dc_3.Porigin_Factor_c(context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })); } };

	}

	public static final common.NodeFactory<Psquare_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Psquare_c> {

		@Override
		public Psquare_c invoke(final Object[] children, final Object[] annotations) {
			return new Psquare_c(children[0], children[1], children[2], children[3]);
		}
	};

}
