
package silver.driver;

public final class PparseArgs extends common.FunctionNode {

	public static final int i_args = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_parseArgs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PparseArgs(final Object c_args) {
		this.child_args = c_args;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:driver:parseArgs";
	}

	public static core.NEither invoke(final Object c_args) {
		try {
		final common.DecoratedNode context = new PparseArgs(c_args).decorate();
		//if ! null(errors) then left(implode("\n", errors) ++ "\n\n" ++ usage) else right(a)
		return (core.NEither)(((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.Init.errors__ON__silver_driver_parseArgs)))) ? ((core.NEither)new core.Pleft(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), context.localAsIsLazy(silver.driver.Init.errors__ON__silver_driver_parseArgs))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\n")), (common.StringCatter)((common.StringCatter)context.localAsIs(silver.driver.Init.usage__ON__silver_driver_parseArgs)))); } })) : ((core.NEither)new core.Pright(context.localDecoratedLazy(silver.driver.Init.a__ON__silver_driver_parseArgs)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:parseArgs", t);
		}
	}

	public static final common.NodeFactory<core.NEither> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NEither> {
		@Override
		public core.NEither invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PparseArgs.invoke(children[0]);
		}
	};
}