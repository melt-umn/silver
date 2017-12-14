
package silver.driver.util;

public final class PexpandCondBuilds extends common.FunctionNode {

	public static final int i_need = 0;
	public static final int i_seen = 1;
	public static final int i_triggers = 2;
	public static final int i_e = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_expandCondBuilds;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PexpandCondBuilds(final Object c_need, final Object c_seen, final Object c_triggers, final Object c_e) {
		this.child_need = c_need;
		this.child_seen = c_seen;
		this.child_triggers = c_triggers;
		this.child_e = c_e;

	}

	private Object child_need;
	public final common.ConsCell getChild_need() {
		return (common.ConsCell) (child_need = common.Util.demand(child_need));
	}

	private Object child_seen;
	public final common.ConsCell getChild_seen() {
		return (common.ConsCell) (child_seen = common.Util.demand(child_seen));
	}

	private Object child_triggers;
	public final common.ConsCell getChild_triggers() {
		return (common.ConsCell) (child_triggers = common.Util.demand(child_triggers));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_need: return getChild_need();
			case i_seen: return getChild_seen();
			case i_triggers: return getChild_triggers();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_need: return child_need;
			case i_seen: return child_seen;
			case i_triggers: return child_triggers;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:driver:util:expandCondBuilds";
	}

	public static common.ConsCell invoke(final Object c_need, final Object c_seen, final Object c_triggers, final Object c_e) {
		try {
		final common.DecoratedNode context = new PexpandCondBuilds(c_need, c_seen, c_triggers, c_e).decorate();
		//if null(need) || null(triggered) then newset else expandCondBuilds(expandExports(triggered, newset, e), newset, newtriggers, e)
		return (common.ConsCell)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_need))) || ((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.util.Init.triggered__ON__silver_driver_util_expandCondBuilds)))) ? ((common.ConsCell)context.localAsIs(silver.driver.util.Init.newset__ON__silver_driver_util_expandCondBuilds)) : ((common.ConsCell)silver.driver.util.PexpandCondBuilds.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PexpandExports.invoke(context.localAsIsLazy(silver.driver.util.Init.triggered__ON__silver_driver_util_expandCondBuilds), context.localAsIsLazy(silver.driver.util.Init.newset__ON__silver_driver_util_expandCondBuilds), context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_e))); } }, context.localAsIsLazy(silver.driver.util.Init.newset__ON__silver_driver_util_expandCondBuilds), context.localAsIsLazy(silver.driver.util.Init.newtriggers__ON__silver_driver_util_expandCondBuilds), context.childAsIsLazy(silver.driver.util.PexpandCondBuilds.i_e)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:expandCondBuilds", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexpandCondBuilds.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}