
package origins_test_1;

// sum::Expr_c ::= e::Expr_c '+' t::Term_c 
public final class Padd_c extends origins_test_1.NExpr_c {

	public static final int i_e = 0;
	public static final int i__G_1 = 1;
	public static final int i_t = 2;


	public static final Class<?> childTypes[] = {origins_test_1.NExpr_c.class,origins_test_1.TPlus_t.class,origins_test_1.NTerm_c.class};

	public static final int num_local_attrs = Init.count_local__ON__origins_test_1_add_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[origins_test_1.NExpr_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[origins_test_1.NExpr_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[origins_test_1.NExpr_c.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[origins_test_1.NTerm_c.num_inh_attrs];

	}

	public Padd_c(final Object c_e, final Object c__G_1, final Object c_t) {
		super();
		this.child_e = c_e;
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child_e;
	public final origins_test_1.NExpr_c getChild_e() {
		return (origins_test_1.NExpr_c) (child_e = common.Util.demand(child_e));
	}

	private Object child__G_1;
	public final origins_test_1.TPlus_t getChild__G_1() {
		return (origins_test_1.TPlus_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final origins_test_1.NTerm_c getChild_t() {
		return (origins_test_1.NTerm_c) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
			case i__G_1: return child__G_1;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production origins_test_1:add_c erroneously claimed to forward");
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
		return "origins_test_1:add_c";
	}

	static void initProductionAttributeDefinitions() {
		// sum.pp = e.pp ++ " + " ++ t.pp
		origins_test_1.Padd_c.synthesizedAttributes[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(origins_test_1.Padd_c.i_e).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" + ")), (common.StringCatter)((common.StringCatter)context.childDecorated(origins_test_1.Padd_c.i_t).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Term_c)))); } };
		// sum.ast_Expr = add(e.ast_Expr, t.ast_Expr)
		origins_test_1.Padd_c.synthesizedAttributes[origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Expr_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((origins_test_1.NExpr)new origins_test_1.Padd(context.childDecoratedSynthesizedLazy(origins_test_1.Padd_c.i_e, origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Expr_c), context.childDecoratedSynthesizedLazy(origins_test_1.Padd_c.i_t, origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Term_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new origins_test_1.Porigin_Expr_c(context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })); } };

	}

	public static final common.NodeFactory<Padd_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Padd_c> {

		@Override
		public Padd_c invoke(final Object[] children, final Object[] annotations) {
			return new Padd_c(children[0], children[1], children[2]);
		}
	};

}
