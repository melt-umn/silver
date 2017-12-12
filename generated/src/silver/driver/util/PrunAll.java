
package silver.driver.util;

public final class PrunAll extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_i = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_runAll;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrunAll(final Object c_l, final Object c_i) {
		this.child_l = c_l;
		this.child_i = c_i;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_i: return child_i;

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
		return "silver:driver:util:runAll";
	}

	public static core.NIOVal invoke(final Object c_l, final Object c_i) {
		try {
		final common.DecoratedNode context = new PrunAll(c_l, c_i).decorate();
		//if unsafeTrace(null(l), i) then ioval(i, 0) else if now.code != 0 then ioval(now.io, now.code) else runAll(tail(l), now.io)
		return (core.NIOVal)((((Boolean)core.PunsafeTrace.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PrunAll.i_l))); } }, context.childAsIsLazy(silver.driver.util.PrunAll.i_i))) ? ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.util.PrunAll.i_i), Integer.valueOf((int)0))) : (!((Integer)context.localDecorated(silver.driver.util.Init.now__ON__silver_driver_util_runAll).synthesized(silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction)).equals(Integer.valueOf((int)0)) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.now__ON__silver_driver_util_runAll).synthesized(silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)context.localDecorated(silver.driver.util.Init.now__ON__silver_driver_util_runAll).synthesized(silver.driver.util.Init.silver_driver_util_code__ON__silver_driver_util_DriverAction)); } })) : ((core.NIOVal)silver.driver.util.PrunAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PrunAll.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.util.Init.now__ON__silver_driver_util_runAll).synthesized(silver.driver.util.Init.core_io__ON__silver_driver_util_DriverAction)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:runAll", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrunAll.invoke(children[0], children[1]);
		}
	};
}