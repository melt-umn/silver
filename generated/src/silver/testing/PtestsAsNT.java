
package silver.testing;

public final class PtestsAsNT extends common.FunctionNode {

	public static final int i_ts = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_testsAsNT;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtestsAsNT(final Object c_ts) {
		this.child_ts = c_ts;

	}

	private Object child_ts;
	public final common.ConsCell getChild_ts() {
		return (common.ConsCell) (child_ts = common.Util.demand(child_ts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ts: return getChild_ts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ts: return child_ts;

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
		return "silver:testing:testsAsNT";
	}

	public static silver.testing.NTestSuite invoke(final Object c_ts) {
		try {
		final common.DecoratedNode context = new PtestsAsNT(c_ts).decorate();
		//if null(ts,) then testNone(,) else testCons(head(ts,), testsAsNT(tail(ts,),),)
		return (silver.testing.NTestSuite)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.testing.PtestsAsNT.i_ts))) ? ((silver.testing.NTestSuite)new silver.testing.PtestNone()) : ((silver.testing.NTestSuite)new silver.testing.PtestCons(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTest)core.Phead.invoke(context.childAsIsLazy(silver.testing.PtestsAsNT.i_ts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)silver.testing.PtestsAsNT.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.testing.PtestsAsNT.i_ts))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:testsAsNT", t);
		}
	}

	public static final common.NodeFactory<silver.testing.NTestSuite> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.testing.NTestSuite> {
		@Override
		public silver.testing.NTestSuite invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtestsAsNT.invoke(children[0]);
		}
	};
}