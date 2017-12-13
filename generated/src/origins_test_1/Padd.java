
package origins_test_1;

// sum::Expr ::= l::Expr r::Expr 
public final class Padd extends origins_test_1.NExpr {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = {origins_test_1.NExpr.class,origins_test_1.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__origins_test_1_add;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[origins_test_1.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[origins_test_1.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[origins_test_1.NExpr.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[origins_test_1.NExpr.num_inh_attrs];

	}

	public Padd(final Object c_l, final Object c_r, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final origins_test_1.NExpr getChild_l() {
		return (origins_test_1.NExpr) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final origins_test_1.NExpr getChild_r() {
		return (origins_test_1.NExpr) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production origins_test_1:add erroneously claimed to forward");
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
		return "origins_test_1:add";
	}

	static void initProductionAttributeDefinitions() {
		// sum.pp = "(" ++ l.pp ++ " + " ++ r.pp ++ ")"
		origins_test_1.Padd.synthesizedAttributes[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(origins_test_1.Padd.i_l).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" + ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(origins_test_1.Padd.i_r).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr)), (common.StringCatter)(new common.StringCatter(")")))))); } };
		// sum.value = l.value + r.value
		origins_test_1.Padd.synthesizedAttributes[origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(origins_test_1.Padd.i_l).synthesized(origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Expr)) + ((Integer)context.childDecorated(origins_test_1.Padd.i_r).synthesized(origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Expr))); } };

	}

	public static final common.NodeFactory<Padd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Padd> {

		@Override
		public Padd invoke(final Object[] children, final Object[] annotations) {
			return new Padd(children[0], children[1], annotations[0], annotations[1], annotations[2]);
		}
	};

}
