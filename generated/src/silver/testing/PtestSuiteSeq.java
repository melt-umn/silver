
package silver.testing;

// ts::TestSuite ::= ts1::TestSuite ts2::TestSuite 
public final class PtestSuiteSeq extends silver.testing.NTestSuite {

	public static final int i_ts1 = 0;
	public static final int i_ts2 = 1;


	public static final Class<?> childTypes[] = {silver.testing.NTestSuite.class,silver.testing.NTestSuite.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_testSuiteSeq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTestSuite.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ts1] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];
	childInheritedAttributes[i_ts2] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	}

	public PtestSuiteSeq(final Object c_ts1, final Object c_ts2) {
		super();
		this.child_ts1 = c_ts1;
		this.child_ts2 = c_ts2;

	}

	private Object child_ts1;
	public final silver.testing.NTestSuite getChild_ts1() {
		return (silver.testing.NTestSuite) (child_ts1 = common.Util.demand(child_ts1));
	}

	private Object child_ts2;
	public final silver.testing.NTestSuite getChild_ts2() {
		return (silver.testing.NTestSuite) (child_ts2 = common.Util.demand(child_ts2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ts1: return getChild_ts1();
			case i_ts2: return getChild_ts2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ts1: return child_ts1;
			case i_ts2: return child_ts2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:testing:testSuiteSeq erroneously claimed to forward");
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
		return "silver:testing:testSuiteSeq";
	}

	static void initProductionAttributeDefinitions() {
		// ts.msg = ts1.msg ++ ts2.msg
		silver.testing.PtestSuiteSeq.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts1).synthesized(silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts2).synthesized(silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite))); } };
		// ts.numTests = ts1.numTests + ts2.numTests
		silver.testing.PtestSuiteSeq.synthesizedAttributes[silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts1).synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite)) + ((Integer)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts2).synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite))); } };
		// ts.numPassed = ts1.numPassed + ts2.numPassed
		silver.testing.PtestSuiteSeq.synthesizedAttributes[silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts1).synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite)) + ((Integer)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts2).synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite))); } };
		// ts.numFailed = ts.numTests - ts.numPassed
		silver.testing.PtestSuiteSeq.synthesizedAttributes[silver.testing.Init.silver_testing_numFailed__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite)) - ((Integer)context.synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite))); } };
		// ts1.ioIn = ts.ioIn
		silver.testing.PtestSuiteSeq.childInheritedAttributes[silver.testing.PtestSuiteSeq.i_ts1][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite)); } };
		// ts2.ioIn = ts1.ioOut
		silver.testing.PtestSuiteSeq.childInheritedAttributes[silver.testing.PtestSuiteSeq.i_ts2][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts1).synthesized(silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite)); } };
		// ts.ioOut = ts2.ioOut
		silver.testing.PtestSuiteSeq.synthesizedAttributes[silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.testing.PtestSuiteSeq.i_ts2).synthesized(silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite)); } };

	}

	public static final common.NodeFactory<PtestSuiteSeq> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtestSuiteSeq> {

		@Override
		public PtestSuiteSeq invoke(final Object[] children, final Object[] annotations) {
			return new PtestSuiteSeq(children[0], children[1]);
		}
	};

}
