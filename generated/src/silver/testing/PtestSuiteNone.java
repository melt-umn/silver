
package silver.testing;

// ts::TestSuite ::= 
public final class PtestSuiteNone extends silver.testing.NTestSuite {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_testSuiteNone;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTestSuite.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtestSuiteNone() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:testing:testSuiteNone erroneously claimed to forward");
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
		return "silver:testing:testSuiteNone";
	}

	static void initProductionAttributeDefinitions() {
		// ts.msg = ""
		silver.testing.PtestSuiteNone.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// ts.numTests = 0
		silver.testing.PtestSuiteNone.synthesizedAttributes[silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// ts.numPassed = 0
		silver.testing.PtestSuiteNone.synthesizedAttributes[silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// ts.numFailed = ts.numTests - ts.numPassed
		silver.testing.PtestSuiteNone.synthesizedAttributes[silver.testing.Init.silver_testing_numFailed__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite)) - ((Integer)context.synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite))); } };
		// ts.ioOut = ts.ioIn
		silver.testing.PtestSuiteNone.synthesizedAttributes[silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite)); } };

	}

	public static final common.NodeFactory<PtestSuiteNone> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtestSuiteNone> {

		@Override
		public PtestSuiteNone invoke(final Object[] children, final Object[] annotations) {
			return new PtestSuiteNone();
		}
	};

}
