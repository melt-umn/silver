
package paper_dc_3;

// q::Expr_a ::= m::Integer 
public final class Pconst extends paper_dc_3.NExpr_a {

	public static final int i_m = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__paper_dc_3_const;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[paper_dc_3.NExpr_a.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pconst(final Object c_m, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_m = c_m;

	}

	private Object child_m;
	public final Integer getChild_m() {
		return (Integer) (child_m = common.Util.demand(child_m));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_m: return getChild_m();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_m: return child_m;

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
		throw new common.exceptions.SilverInternalError("Production paper_dc_3:const erroneously claimed to forward");
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
		return "paper_dc_3:const";
	}

	static void initProductionAttributeDefinitions() {
		// q.value = m
		paper_dc_3.Pconst.synthesizedAttributes[paper_dc_3.Init.paper_dc_3_value__ON__paper_dc_3_Expr_a] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(paper_dc_3.Pconst.i_m)); } };

	}

	public static final common.NodeFactory<Pconst> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pconst> {

		@Override
		public Pconst invoke(final Object[] children, final Object[] annotations) {
			return new Pconst(children[0], annotations[0], annotations[1], annotations[2]);
		}
	};

}
