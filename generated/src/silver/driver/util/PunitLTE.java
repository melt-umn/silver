
package silver.driver.util;

public final class PunitLTE extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = { silver.driver.util.NDriverAction.class,silver.driver.util.NDriverAction.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_unitLTE;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	}

	public PunitLTE(final Object c_l, final Object c_r) {
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver.driver.util.NDriverAction getChild_l() {
		return (silver.driver.util.NDriverAction) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.driver.util.NDriverAction getChild_r() {
		return (silver.driver.util.NDriverAction) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:driver:util:unitLTE";
	}

	public static Boolean invoke(final Object c_l, final Object c_r) {
		try {
		final common.DecoratedNode context = new PunitLTE(c_l, c_r).decorate();
		//l.order <= r.order
		return (Boolean)((((Integer)context.childDecorated(silver.driver.util.PunitLTE.i_l).synthesized(silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction)) <= ((Integer)context.childDecorated(silver.driver.util.PunitLTE.i_r).synthesized(silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:unitLTE", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunitLTE.invoke(children[0], children[1]);
		}
	};
}