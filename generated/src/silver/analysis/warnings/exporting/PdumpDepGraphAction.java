
package silver.analysis.warnings.exporting;

// top::DriverAction ::= specs::[Decorated RootSpec] 
public final class PdumpDepGraphAction extends silver.driver.util.NDriverAction {

	public static final int i_specs = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_exporting_dumpDepGraphAction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdumpDepGraphAction(final Object c_specs) {
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:analysis:warnings:exporting:dumpDepGraphAction erroneously claimed to forward");
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
		return "silver:analysis:warnings:exporting:dumpDepGraphAction";
	}

	static void initProductionAttributeDefinitions() {
		// top.io = writeFile("deps.dot", "digraph deps {\n" ++ generateDotGraph(specs) ++ "}", print("Generating import graph\n", top.ioIn))
		silver.analysis.warnings.exporting.PdumpDepGraphAction.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.PwriteFile.invoke((new common.StringCatter("deps.dot")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("digraph deps {\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.analysis.warnings.exporting.PgenerateDotGraph.invoke(context.childAsIsLazy(silver.analysis.warnings.exporting.PdumpDepGraphAction.i_specs))), (common.StringCatter)(new common.StringCatter("}")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke((new common.StringCatter("Generating import graph\n")), context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction))); } })); } };
		// top.code = 0
		silver.analysis.warnings.exporting.PdumpDepGraphAction.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.order = 0
		silver.analysis.warnings.exporting.PdumpDepGraphAction.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };

	}

	public static final common.NodeFactory<PdumpDepGraphAction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdumpDepGraphAction> {

		@Override
		public PdumpDepGraphAction invoke(final Object[] children, final Object[] annotations) {
			return new PdumpDepGraphAction(children[0]);
		}
	};

}
