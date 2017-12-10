
package paper_dc_3;

// sum::Expr_a ::= l::Expr_a rhs::Expr_a 
public final class Padd extends paper_dc_3.NExpr_a {

	public static final int i_l = 0;
	public static final int i_rhs = 1;


	public static final Class<?> childTypes[] = {paper_dc_3.NExpr_a.class,paper_dc_3.NExpr_a.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_add;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	}

	public Padd(final Object c_l, final Object c_rhs, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_l = c_l;
		this.child_rhs = c_rhs;

	}

	private Object child_l;
	public final paper_dc_3.NExpr_a getChild_l() {
		return (paper_dc_3.NExpr_a) (child_l = common.Util.demand(child_l));
	}

	private Object child_rhs;
	public final paper_dc_3.NExpr_a getChild_rhs() {
		return (paper_dc_3.NExpr_a) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_rhs: return child_rhs;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:add erroneously claimed to forward");
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
		return "paper_dc_3:add";
	}

	static void initProductionAttributeDefinitions() {
		// sum.value = l.value + rhs.value
		paper_dc_3.Padd.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(paper_dc_3.Padd.i_l).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a)) + ((Integer)context.childDecorated(paper_dc_3.Padd.i_rhs).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a))); } };

	}

	public static final common.NodeFactory<Padd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Padd> {

		@Override
		public Padd invoke(final Object[] children, final Object[] annotations) {
			return new Padd(children[0], children[1], annotations[0], annotations[1], annotations[2]);
		}
	};

}
