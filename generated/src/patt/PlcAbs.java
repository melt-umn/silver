
package patt;

// e::LCE ::= v::String e1::LCE 
public final class PlcAbs extends patt.NLCE {

	public static final int i_v = 0;
	public static final int i_e1 = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,patt.NLCE.class};

	public static final int num_local_attrs = Init.count_local__ON__patt_lcAbs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[patt.NLCE.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[patt.NLCE.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e1] = new common.Lazy[patt.NLCE.num_inh_attrs];

	}

	public PlcAbs(final Object c_v, final Object c_e1) {
		super();
		this.child_v = c_v;
		this.child_e1 = c_e1;

	}

	private Object child_v;
	public final common.StringCatter getChild_v() {
		return (common.StringCatter) (child_v = common.Util.demand(child_v));
	}

	private Object child_e1;
	public final patt.NLCE getChild_e1() {
		return (patt.NLCE) (child_e1 = common.Util.demand(child_e1));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i_e1: return getChild_e1();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i_e1: return child_e1;

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
		throw new common.exceptions.SilverInternalError("Production patt:lcAbs erroneously claimed to forward");
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
		return "patt:lcAbs";
	}

	static void initProductionAttributeDefinitions() {
		// e.value = true
		patt.PlcAbs.synthesizedAttributes[patt.Init.patt_value__ON__patt_LCE] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PlcAbs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlcAbs> {

		@Override
		public PlcAbs invoke(final Object[] children, final Object[] annotations) {
			return new PlcAbs(children[0], children[1]);
		}
	};

}
