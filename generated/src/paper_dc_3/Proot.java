
package paper_dc_3;

// r::Root_a ::= e::Expr_a 
public final class Proot extends paper_dc_3.NRoot_a {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {paper_dc_3.NExpr_a.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_root;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NRoot_a.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NRoot_a.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	}

	public Proot(final Object c_e, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_e = c_e;

	}

	private Object child_e;
	public final paper_dc_3.NExpr_a getChild_e() {
		return (paper_dc_3.NExpr_a) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:root erroneously claimed to forward");
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
		return "paper_dc_3:root";
	}

	static void initProductionAttributeDefinitions() {
		// r.value = e.value
		paper_dc_3.Proot.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Root_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(paper_dc_3.Proot.i_e).synthesized(paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a)); } };
		// r.expd = e.expd
		paper_dc_3.Proot.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Root_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((paper_dc_3.NExpr_a)context.childDecorated(paper_dc_3.Proot.i_e).synthesized(paper_dc_3.Init.paper_dc_3_expd__ON__paper_dc_3_Expr_a)); } };

	}

	public static final common.NodeFactory<Proot> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Proot> {

		@Override
		public Proot invoke(final Object[] children, final Object[] annotations) {
			return new Proot(children[0], annotations[0], annotations[1], annotations[2]);
		}
	};

}
