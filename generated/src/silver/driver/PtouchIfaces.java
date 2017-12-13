
package silver.driver;

// top::DriverAction ::= r::[Decorated RootSpec] genPath::String 
public final class PtouchIfaces extends silver.driver.util.NDriverAction {

	public static final int i_r = 0;
	public static final int i_genPath = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_touchIfaces;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtouchIfaces(final Object c_r, final Object c_genPath) {
		super();
		this.child_r = c_r;
		this.child_genPath = c_genPath;

	}

	private Object child_r;
	public final common.ConsCell getChild_r() {
		return (common.ConsCell) (child_r = common.Util.demand(child_r));
	}

	private Object child_genPath;
	public final common.StringCatter getChild_genPath() {
		return (common.StringCatter) (child_genPath = common.Util.demand(child_genPath));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();
			case i_genPath: return getChild_genPath();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;
			case i_genPath: return child_genPath;

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
		throw new common.exceptions.SilverInternalError("Production silver:driver:touchIfaces erroneously claimed to forward");
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
		return "silver:driver:touchIfaces";
	}

	static void initProductionAttributeDefinitions() {
		// top.io = touchFiles(map(sviPath(_, genPath), r), top.ioIn)
		silver.driver.PtouchIfaces.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PtouchFiles.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.driver.PsviPath.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.driver.PtouchIfaces.i_genPath)}); } }, context.childAsIsLazy(silver.driver.PtouchIfaces.i_r))); } }, context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };
		// top.code = 0
		silver.driver.PtouchIfaces.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.order = 3
		silver.driver.PtouchIfaces.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)3); } };

	}

	public static final common.NodeFactory<PtouchIfaces> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtouchIfaces> {

		@Override
		public PtouchIfaces invoke(final Object[] children, final Object[] annotations) {
			return new PtouchIfaces(children[0], children[1]);
		}
	};

}
