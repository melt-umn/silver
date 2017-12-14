
package paper_dc_3;

// prd::Expr_a ::= l::Expr_a rh::Expr_a 
public final class Pmul extends paper_dc_3.NExpr_a {

	public static final int i_l = 0;
	public static final int i_rh = 1;


	public static final Class<?> childTypes[] = {paper_dc_3.NExpr_a.class,paper_dc_3.NExpr_a.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_mul;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];
	childInheritedAttributes[i_rh] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	}

	public Pmul(final Object c_l, final Object c_rh, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_l = c_l;
		this.child_rh = c_rh;

	}

	private Object child_l;
	public final paper_dc_3.NExpr_a getChild_l() {
		return (paper_dc_3.NExpr_a) (child_l = common.Util.demand(child_l));
	}

	private Object child_rh;
	public final paper_dc_3.NExpr_a getChild_rh() {
		return (paper_dc_3.NExpr_a) (child_rh = common.Util.demand(child_rh));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_rh: return getChild_rh();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_rh: return child_rh;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:mul erroneously claimed to forward");
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
		return "paper_dc_3:mul";
	}

	static void initProductionAttributeDefinitions() {
		// prd.value = l.value * rh.value
		paper_dc_3.Pmul.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(paper_dc_3.Pmul.i_l).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a)) * ((Integer)context.childDecorated(paper_dc_3.Pmul.i_rh).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a))); } };

	}

	public static final common.NodeFactory<Pmul> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pmul> {

		@Override
		public Pmul invoke(final Object[] children, final Object[] annotations) {
			return new Pmul(children[0], children[1], annotations[0], annotations[1], annotations[2]);
		}
	};

}
