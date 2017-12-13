
package origins_test_1;

// prd::Term_c ::= t::Term_c '*' f::Factor_c 
public final class Pmul_c extends origins_test_1.NTerm_c {

	public static final int i_t = 0;
	public static final int i__G_1 = 1;
	public static final int i_f = 2;


	public static final Class<?> childTypes[] = {origins_test_1.NTerm_c.class,origins_test_1.TStar_t.class,origins_test_1.NFactor_c.class};

	public static final int num_local_attrs = Init.count_local__ON__origins_test_1_mul_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[origins_test_1.NTerm_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[origins_test_1.NTerm_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[origins_test_1.NTerm_c.num_inh_attrs];
	childInheritedAttributes[i_f] = new common.Lazy[origins_test_1.NFactor_c.num_inh_attrs];

	}

	public Pmul_c(final Object c_t, final Object c__G_1, final Object c_f) {
		super();
		this.child_t = c_t;
		this.child__G_1 = c__G_1;
		this.child_f = c_f;

	}

	private Object child_t;
	public final origins_test_1.NTerm_c getChild_t() {
		return (origins_test_1.NTerm_c) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_1;
	public final origins_test_1.TStar_t getChild__G_1() {
		return (origins_test_1.TStar_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_f;
	public final origins_test_1.NFactor_c getChild_f() {
		return (origins_test_1.NFactor_c) (child_f = common.Util.demand(child_f));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i__G_1: return getChild__G_1();
			case i_f: return getChild_f();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i__G_1: return child__G_1;
			case i_f: return child_f;

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
		throw new common.exceptions.SilverInternalError("Production origins_test_1:mul_c erroneously claimed to forward");
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
		return "origins_test_1:mul_c";
	}

	static void initProductionAttributeDefinitions() {
		// prd.pp = t.pp ++ " * " ++ f.pp
		origins_test_1.Pmul_c.synthesizedAttributes[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(origins_test_1.Pmul_c.i_t).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Term_c)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" * ")), (common.StringCatter)((common.StringCatter)context.childDecorated(origins_test_1.Pmul_c.i_f).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Factor_c)))); } };
		// prd.ast_Expr = mul(t.ast_Expr, f.ast_Expr)
		origins_test_1.Pmul_c.synthesizedAttributes[origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Term_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((origins_test_1.NExpr)new origins_test_1.Pmul(context.childDecoratedSynthesizedLazy(origins_test_1.Pmul_c.i_t, origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Term_c), context.childDecoratedSynthesizedLazy(origins_test_1.Pmul_c.i_f, origins_test_1.Init.origins_test_1_ast_Expr__ON__origins_test_1_Factor_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NOrigin)new origins_test_1.Porigin_Term_c(context)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })); } };

	}

	public static final common.NodeFactory<Pmul_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pmul_c> {

		@Override
		public Pmul_c invoke(final Object[] children, final Object[] annotations) {
			return new Pmul_c(children[0], children[1], children[2]);
		}
	};

}
