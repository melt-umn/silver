
package silver.driver.util;

public final class PcompleteDependencyClosure extends common.FunctionNode {

	public static final int i_init = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_completeDependencyClosure;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcompleteDependencyClosure(final Object c_init, final Object c_e) {
		this.child_init = c_init;
		this.child_e = c_e;

	}

	private Object child_init;
	public final common.ConsCell getChild_init() {
		return (common.ConsCell) (child_init = common.Util.demand(child_init));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_init: return getChild_init();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_init: return child_init;
			case i_e: return child_e;

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
		return "silver:driver:util:completeDependencyClosure";
	}

	public static common.ConsCell invoke(final Object c_init, final Object c_e) {
		try {
		final common.DecoratedNode context = new PcompleteDependencyClosure(c_init, c_e).decorate();
		//if null(n) then init else completeDependencyClosure(computeOptionalDeps(n ++ init, e), e)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.util.Init.n__ON__silver_driver_util_completeDependencyClosure))) ? ((common.ConsCell)context.childAsIs(silver.driver.util.PcompleteDependencyClosure.i_init)) : ((common.ConsCell)silver.driver.util.PcompleteDependencyClosure.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PcomputeOptionalDeps.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.driver.util.Init.n__ON__silver_driver_util_completeDependencyClosure), context.childAsIsLazy(silver.driver.util.PcompleteDependencyClosure.i_init))); } }, context.childAsIsLazy(silver.driver.util.PcompleteDependencyClosure.i_e))); } }, context.childAsIsLazy(silver.driver.util.PcompleteDependencyClosure.i_e)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:completeDependencyClosure", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompleteDependencyClosure.invoke(children[0], children[1]);
		}
	};
}