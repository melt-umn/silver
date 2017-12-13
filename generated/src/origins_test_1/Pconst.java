
package origins_test_1;

// e::Expr ::= i::Integer 
public final class Pconst extends origins_test_1.NExpr {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__origins_test_1_const;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[origins_test_1.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[origins_test_1.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pconst(final Object c_i, final Object a_silver_extension_bidirtransform_labels, final Object a_silver_extension_bidirtransform_origin, final Object a_silver_extension_bidirtransform_redex) {
		super(a_silver_extension_bidirtransform_labels, a_silver_extension_bidirtransform_origin, a_silver_extension_bidirtransform_redex);
		this.child_i = c_i;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		throw new common.exceptions.SilverInternalError("Production origins_test_1:const erroneously claimed to forward");
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
		return "origins_test_1:const";
	}

	static void initProductionAttributeDefinitions() {
		// e.pp = toString(i)
		origins_test_1.Pconst.synthesizedAttributes[origins_test_1.Init.origins_test_1_pp__ON__origins_test_1_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter(String.valueOf(((Integer)context.childAsIs(origins_test_1.Pconst.i_i)))); } };
		// e.value = i
		origins_test_1.Pconst.synthesizedAttributes[origins_test_1.Init.origins_test_1_value__ON__origins_test_1_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(origins_test_1.Pconst.i_i)); } };

	}

	public static final common.NodeFactory<Pconst> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pconst> {

		@Override
		public Pconst invoke(final Object[] children, final Object[] annotations) {
			return new Pconst(children[0], annotations[0], annotations[1], annotations[2]);
		}
	};

}
