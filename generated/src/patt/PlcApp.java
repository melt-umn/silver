
package patt;

// e::LCE ::= fun::LCE arg::LCE 
public final class PlcApp extends patt.NLCE {

	public static final int i_fun = 0;
	public static final int i_arg = 1;


	public static final Class<?> childTypes[] = {patt.NLCE.class,patt.NLCE.class};

	public static final int num_local_attrs = Init.count_local__ON__patt_lcApp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[patt.NLCE.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[patt.NLCE.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fun] = new common.Lazy[patt.NLCE.num_inh_attrs];
	childInheritedAttributes[i_arg] = new common.Lazy[patt.NLCE.num_inh_attrs];

	}

	public PlcApp(final Object c_fun, final Object c_arg) {
		super();
		this.child_fun = c_fun;
		this.child_arg = c_arg;

	}

	private Object child_fun;
	public final patt.NLCE getChild_fun() {
		return (patt.NLCE) (child_fun = common.Util.demand(child_fun));
	}

	private Object child_arg;
	public final patt.NLCE getChild_arg() {
		return (patt.NLCE) (child_arg = common.Util.demand(child_arg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fun: return getChild_fun();
			case i_arg: return getChild_arg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fun: return child_fun;
			case i_arg: return child_arg;

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
		throw new common.exceptions.SilverInternalError("Production patt:lcApp erroneously claimed to forward");
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
		return "patt:lcApp";
	}

	static void initProductionAttributeDefinitions() {
		// e.value = false
		patt.PlcApp.synthesizedAttributes[patt.Init.patt_value__ON__patt_LCE] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };

	}

	public static final common.NodeFactory<PlcApp> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlcApp> {

		@Override
		public PlcApp invoke(final Object[] children, final Object[] annotations) {
			return new PlcApp(children[0], children[1]);
		}
	};

}
