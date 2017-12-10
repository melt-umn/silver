
package silver.testing;

public final class PconsolidateTestSuite extends common.FunctionNode {

	public static final int i_ts = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_consolidateTestSuite;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PconsolidateTestSuite(final Object c_ts) {
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
		return "silver:testing:consolidateTestSuite";
	}

	public static silver.testing.NTestSuite invoke(final Object c_ts) {
		try {
		final common.DecoratedNode context = new PconsolidateTestSuite(c_ts).decorate();
		//if null(ts,) then testSuiteNone(,) else testSuiteSeq(head(ts,), consolidateTestSuite(tail(ts,),),)
		return (silver.testing.NTestSuite)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.testing.PconsolidateTestSuite.i_ts))) ? ((silver.testing.NTestSuite)new silver.testing.PtestSuiteNone()) : ((silver.testing.NTestSuite)new silver.testing.PtestSuiteSeq(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)core.Phead.invoke(context.childAsIsLazy(silver.testing.PconsolidateTestSuite.i_ts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.testing.NTestSuite)silver.testing.PconsolidateTestSuite.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.testing.PconsolidateTestSuite.i_ts))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:consolidateTestSuite", t);
		}
	}

	public static final common.NodeFactory<silver.testing.NTestSuite> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.testing.NTestSuite> {
		@Override
		public silver.testing.NTestSuite invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconsolidateTestSuite.invoke(children[0]);
		}
	};
}