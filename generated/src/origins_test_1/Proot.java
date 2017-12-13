
package origins_test_1;

// r::Root ::= e::Expr 
public final class Proot extends origins_test_1.NRoot {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = {origins_test_1.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__origins_test_1_root;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[origins_test_1.NRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[origins_test_1.NRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[origins_test_1.NExpr.num_inh_attrs];

	}

	public Proot(final Object c_e, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_e = c_e;

	}

	private Object child_e;
	public final origins_test_1.NExpr getChild_e() {
		return (origins_test_1.NExpr) (child_e = common.Util.demand(child_e));
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
		throw new common.exceptions.SilverInternalError("Production origins_test_1:root erroneously claimed to forward");
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
		return "origins_test_1:root";
	}

	static void initProductionAttributeDefinitions() {
		// r.pp = e.pp
		origins_test_1.Proot.synthesizedAttributes[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(origins_test_1.Proot.i_e).synthesized(origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr)); } };
		// r.value = e.value
		origins_test_1.Proot.synthesizedAttributes[origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childDecorated(origins_test_1.Proot.i_e).synthesized(origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Expr)); } };

	}

	public static final common.NodeFactory<Proot> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Proot> {

		@Override
		public Proot invoke(final Object[] children, final Object[] annotations) {
			return new Proot(children[0], annotations[0], annotations[1], annotations[2]);
		}
	};

}
