
package silver.driver;

// top::DriverAction ::= specs::[Decorated RootSpec] 
public final class PprintAllBindingErrors extends silver.driver.util.NDriverAction {

	public static final int i_specs = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_printAllBindingErrors;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprintAllBindingErrors(final Object c_specs) {
		super();
		this.child_specs = c_specs;

	}

	private Object child_specs;
	public final common.ConsCell getChild_specs() {
		return (common.ConsCell) (child_specs = common.Util.demand(child_specs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_specs: return getChild_specs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_specs: return child_specs;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.driver.util.NDriverAction)new silver.driver.PprintAllBindingErrorsHelp(context.childAsIsLazy(silver.driver.PprintAllBindingErrors.i_specs)));
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
		return "silver:driver:printAllBindingErrors";
	}

	static void initProductionAttributeDefinitions() {
		// top.code = unsafeTrace(forward.code, forward.ioIn)
		silver.driver.PprintAllBindingErrors.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PunsafeTrace.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.forward().synthesized(silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.forward().inherited(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction)); } })); } };
		//ioIn = print("Checking For Errors.\n", top.ioIn);
		silver.driver.PprintAllBindingErrors.forwardInheritedAttributes[silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke((new common.StringCatter("Checking For Errors.\n")), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } };

	}

	public static final common.NodeFactory<PprintAllBindingErrors> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprintAllBindingErrors> {

		@Override
		public PprintAllBindingErrors invoke(final Object[] children, final Object[] annotations) {
			return new PprintAllBindingErrors(children[0]);
		}
	};

}
