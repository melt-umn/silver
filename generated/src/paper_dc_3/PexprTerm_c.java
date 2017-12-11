
package paper_dc_3;

// e::Expr_c ::= t::Term_c 
public final class PexprTerm_c extends paper_dc_3.NExpr_c {

	public static final int i_t = 0;


	public static final Class<?> childTypes[] = {paper_dc_3.NTerm_c.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_exprTerm_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NExpr_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NExpr_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[paper_dc_3.NTerm_c.num_inh_attrs];

	}

	public PexprTerm_c(final Object c_t) {
		super();
		this.child_t = c_t;

	}

	private Object child_t;
	public final paper_dc_3.NTerm_c getChild_t() {
		return (paper_dc_3.NTerm_c) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:exprTerm_c erroneously claimed to forward");
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
		return "paper_dc_3:exprTerm_c";
	}

	static void initProductionAttributeDefinitions() {
		// e.pp = t.pp
		paper_dc_3.PexprTerm_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(paper_dc_3.PexprTerm_c.i_t).synthesized(paper_dc_3.Init.paper_dc_3_pp__ON__paper_dc_3_Term_c)); } };
		// e.ast_Expr = t.ast_Expr
		paper_dc_3.PexprTerm_c.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)context.childDecorated(paper_dc_3.PexprTerm_c.i_t).synthesized(paper_dc_3.Init.paper_dc_3_ast_Expr__ON__paper_dc_3_Term_c)); } };

	}

	public static final common.NodeFactory<PexprTerm_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PexprTerm_c> {

		@Override
		public PexprTerm_c invoke(final Object[] children, final Object[] annotations) {
			return new PexprTerm_c(children[0]);
		}
	};

}
