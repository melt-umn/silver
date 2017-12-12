
package silver.driver.util;

public final class PisOutOfDate extends common.FunctionNode {

	public static final int i_mine = 0;
	public static final int i_l = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_isOutOfDate;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisOutOfDate(final Object c_mine, final Object c_l, final Object c_e) {
		this.child_mine = c_mine;
		this.child_l = c_l;
		this.child_e = c_e;

	}

	private Object child_mine;
	public final Integer getChild_mine() {
		return (Integer) (child_mine = common.Util.demand(child_mine));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_mine: return getChild_mine();
			case i_l: return getChild_l();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_mine: return child_mine;
			case i_l: return child_l;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:driver:util:isOutOfDate";
	}

	public static Boolean invoke(final Object c_mine, final Object c_l, final Object c_e) {
		try {
		final common.DecoratedNode context = new PisOutOfDate(c_mine, c_l, c_e).decorate();
		//if null(l) then false else if null(n) || mine >= head(n).grammarTime then isOutOfDate(mine, tail(l), e) else true
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PisOutOfDate.i_l))) ? false : ((((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.util.Init.n__ON__silver_driver_util_isOutOfDate))) || (((Integer)context.childAsIs(silver.driver.util.PisOutOfDate.i_mine)) >= ((Integer)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.driver.util.Init.n__ON__silver_driver_util_isOutOfDate))).synthesized(silver.driver.util.Init.silver_definition_env_grammarTime__ON__silver_driver_util_RootSpec)))) ? ((Boolean)silver.driver.util.PisOutOfDate.invoke(context.childAsIsLazy(silver.driver.util.PisOutOfDate.i_mine), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PisOutOfDate.i_l))); } }, context.childAsIsLazy(silver.driver.util.PisOutOfDate.i_e))) : true)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:isOutOfDate", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisOutOfDate.invoke(children[0], children[1], children[2]);
		}
	};
}