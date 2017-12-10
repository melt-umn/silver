
package silver.driver;

// top::DriverAction ::= specs::[Decorated RootSpec] 
public final class PprintAllParsingErrors extends silver.driver.util.NDriverAction {

	public static final int i_specs = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_printAllParsingErrors;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.driver.util.NDriverAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprintAllParsingErrors(final Object c_specs) {
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
		throw new common.exceptions.SilverInternalError("Production silver:driver:printAllParsingErrors erroneously claimed to forward");
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
		return "silver:driver:printAllParsingErrors";
	}

	static void initProductionAttributeDefinitions() {
		// errs = head(specs).parsingErrors
		silver.driver.PprintAllParsingErrors.localAttributes[silver.driver.Init.errs__ON__silver_driver_printAllParsingErrors] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.driver.PprintAllParsingErrors.i_specs))).synthesized(silver.driver.util.Init.silver_driver_util_parsingErrors__ON__silver_driver_util_RootSpec)); } };
		// i = if null(errs) then top.ioIn else print("Errors for " ++ head(specs).declaredName ++ "\n" ++ sflatMap(renderMessages(head(specs).grammarSource, _), errs) ++ "\n", top.ioIn)
		silver.driver.PprintAllParsingErrors.localAttributes[silver.driver.Init.i__ON__silver_driver_printAllParsingErrors] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.Init.errs__ON__silver_driver_printAllParsingErrors))) ? ((Object)context.inherited(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction)) : ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Errors for ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.driver.PprintAllParsingErrors.i_specs))).synthesized(silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.PsflatMap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.driver.PrenderMessages.factory.invokePartial(new int[]{0}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.driver.PprintAllParsingErrors.i_specs))).synthesized(silver.driver.util.Init.silver_definition_env_grammarSource__ON__silver_driver_util_RootSpec)); } }}); } }, context.localAsIsLazy(silver.driver.Init.errs__ON__silver_driver_printAllParsingErrors))), (common.StringCatter)(new common.StringCatter("\n")))))); } }, context.contextInheritedLazy(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction)))); } };
		// recurse = printAllParsingErrors(tail(specs))
		silver.driver.PprintAllParsingErrors.localAttributes[silver.driver.Init.recurse__ON__silver_driver_printAllParsingErrors] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.driver.util.NDriverAction)new silver.driver.PprintAllParsingErrors(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PprintAllParsingErrors.i_specs))); } })); } };
		// recurse.ioIn = i
		silver.driver.PprintAllParsingErrors.localInheritedAttributes[silver.driver.Init.recurse__ON__silver_driver_printAllParsingErrors][silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.localAsIs(silver.driver.Init.i__ON__silver_driver_printAllParsingErrors)); } };
		// top.io = if null(specs) then top.ioIn else recurse.io
		silver.driver.PprintAllParsingErrors.synthesizedAttributes[silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PprintAllParsingErrors.i_specs))) ? ((Object)context.inherited(silver.driver.util.Init.silver_driver_util_ioIn__ON__silver_driver_util_DriverAction)) : ((Object)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_printAllParsingErrors).synthesized(silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction))); } };
		// top.code = if null(specs) || (null(errs) && recurse.code == 0) then 0 else 21
		silver.driver.PprintAllParsingErrors.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PprintAllParsingErrors.i_specs))) || (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.Init.errs__ON__silver_driver_printAllParsingErrors))) && ((Integer)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_printAllParsingErrors).synthesized(silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction)).equals(Integer.valueOf((int)0)))) ? Integer.valueOf((int)0) : Integer.valueOf((int)21)); } };
		// top.order = 0
		silver.driver.PprintAllParsingErrors.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_order__ON__silver_driver_util_DriverAction] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };

	}

	public static final common.NodeFactory<PprintAllParsingErrors> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprintAllParsingErrors> {

		@Override
		public PprintAllParsingErrors invoke(final Object[] children, final Object[] annotations) {
			return new PprintAllParsingErrors(children[0]);
		}
	};

}
