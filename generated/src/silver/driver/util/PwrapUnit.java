
package silver.driver.util;

// top::DriverAction ::= f::(IOVal<Integer> ::= IO) order::Integer 
public final class PwrapUnit extends silver.driver.util.NDriverAction {

	public static final int i_f = 0;
	public static final int i_order = 1;


	public static final Class<?> childTypes[] = {common.NodeFactory.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_wrapUnit;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PwrapUnit(final Object c_f, final Object c_order) {
		super();
		this.child_f = c_f;
		this.child_order = c_order;

	}

	private Object child_f;
	public final common.NodeFactory<core.NIOVal> getChild_f() {
		return (common.NodeFactory<core.NIOVal>) (child_f = common.Util.demand(child_f));
	}

	private Object child_order;
	public final Integer getChild_order() {
		return (Integer) (child_order = common.Util.demand(child_order));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_order: return getChild_order();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_order: return child_order;

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
		throw new common.exceptions.SilverInternalError("Production silver:driver:util:wrapUnit erroneously claimed to forward");
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
		return "silver:driver:util:wrapUnit";
	}

	static void initProductionAttributeDefinitions() {
		// call = f(top.ioIn)
		silver.driver.util.PwrapUnit.localAttributes[silver.driver.util.Init.call__ON__silver_driver_util_wrapUnit] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)((common.NodeFactory<core.NIOVal>)context.childAsIs(silver.driver.util.PwrapUnit.i_f)).invoke(new Object[]{context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction)}, null)); } };
		// top.io = call.io
		silver.driver.util.PwrapUnit.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.call__ON__silver_driver_util_wrapUnit).synthesized(core.Init.core_io__ON__core_IOVal)); } };
		// top.code = call.iovalue
		silver.driver.util.PwrapUnit.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(silver.driver.util.Init.call__ON__silver_driver_util_wrapUnit).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } };
		// top.order = order
		silver.driver.util.PwrapUnit.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.driver.util.PwrapUnit.i_order)); } };

	}

	public static final common.NodeFactory<PwrapUnit> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PwrapUnit> {

		@Override
		public PwrapUnit invoke(final Object[] children, final Object[] annotations) {
			return new PwrapUnit(children[0], children[1]);
		}
	};

}
