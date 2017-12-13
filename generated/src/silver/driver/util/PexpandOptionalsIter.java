
package silver.driver.util;

public final class PexpandOptionalsIter extends common.FunctionNode {

	public static final int i_need = 0;
	public static final int i_seen = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_expandOptionalsIter;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PexpandOptionalsIter(final Object c_need, final Object c_seen, final Object c_e) {
		this.child_need = c_need;
		this.child_seen = c_seen;
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

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_need: return getChild_need();
			case i_seen: return getChild_seen();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_need: return child_need;
			case i_seen: return child_seen;
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
		return "silver:driver:util:expandOptionalsIter";
	}

	public static common.ConsCell invoke(final Object c_need, final Object c_seen, final Object c_e) {
		try {
		final common.DecoratedNode context = new PexpandOptionalsIter(c_need, c_seen, c_e).decorate();
		//if null(need) then seen else if contains(head(need), seen) then expandOptionalsIter(tail(need), seen, e) else if null(g) then expandOptionalsIter(tail(need), (head(need) :: seen), e) else expandOptionalsIter(tail(need) ++ head(g).optionalGrammars, (head(need) :: seen), e)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))) ? ((common.ConsCell)context.childAsIs(silver.driver.util.PexpandOptionalsIter.i_seen)) : (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_seen))) ? ((common.ConsCell)silver.driver.util.PexpandOptionalsIter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_seen), context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_e))) : (((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.driver.util.Init.g__ON__silver_driver_util_expandOptionalsIter))) ? ((common.ConsCell)silver.driver.util.PexpandOptionalsIter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_seen))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_e))) : ((common.ConsCell)silver.driver.util.PexpandOptionalsIter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.driver.util.Init.g__ON__silver_driver_util_expandOptionalsIter))).synthesized(silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_need))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_seen))); } }, context.childAsIsLazy(silver.driver.util.PexpandOptionalsIter.i_e)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:expandOptionalsIter", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexpandOptionalsIter.invoke(children[0], children[1], children[2]);
		}
	};
}