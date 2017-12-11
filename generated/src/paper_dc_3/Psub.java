
package paper_dc_3;

// dff::Expr_a ::= l::Expr_a rs::Expr_a 
public final class Psub extends paper_dc_3.NExpr_a {

	public static final int i_l = 0;
	public static final int i_rs = 1;


	public static final Class<?> childTypes[] = {paper_dc_3.NExpr_a.class,paper_dc_3.NExpr_a.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_sub;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];
	childInheritedAttributes[i_rs] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	}

	public Psub(final Object c_l, final Object c_rs, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_l = c_l;
		this.child_rs = c_rs;

	}

	private Object child_l;
	public final paper_dc_3.NExpr_a getChild_l() {
		return (paper_dc_3.NExpr_a) (child_l = common.Util.demand(child_l));
	}

	private Object child_rs;
	public final paper_dc_3.NExpr_a getChild_rs() {
		return (paper_dc_3.NExpr_a) (child_rs = common.Util.demand(child_rs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_rs: return getChild_rs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_rs: return child_rs;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:sub erroneously claimed to forward");
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
		return "paper_dc_3:sub";
	}

	static void initProductionAttributeDefinitions() {
		// dff.value = l.value - rs.value
		paper_dc_3.Psub.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(paper_dc_3.Psub.i_l).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a)) - ((Integer)context.childDecorated(paper_dc_3.Psub.i_rs).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a))); } };

	}

	public static final common.NodeFactory<Psub> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Psub> {

		@Override
		public Psub invoke(final Object[] children, final Object[] annotations) {
			return new Psub(children[0], children[1], annotations[0], annotations[1], annotations[2]);
		}
	};

}
