
package silver.testing;

// ts::TestSuite ::= t::Test rest::TestSuite 
public final class PtestCons extends silver.testing.NTestSuite {

	public static final int i_t = 0;
	public static final int i_rest = 1;


	public static final Class<?> childTypes[] = {silver.testing.NTest.class,silver.testing.NTestSuite.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_testCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTestSuite.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.testing.NTest.num_inh_attrs];
	childInheritedAttributes[i_rest] = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	}

	public PtestCons(final Object c_t, final Object c_rest) {
		super();
		this.child_t = c_t;
		this.child_rest = c_rest;

	}

	private Object child_t;
	public final silver.testing.NTest getChild_t() {
		return (silver.testing.NTest) (child_t = common.Util.demand(child_t));
	}

	private Object child_rest;
	public final silver.testing.NTestSuite getChild_rest() {
		return (silver.testing.NTestSuite) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_rest: return child_rest;

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
		throw new common.exceptions.SilverInternalError("Production silver:testing:testCons erroneously claimed to forward");
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
		return "silver:testing:testCons";
	}

	static void initProductionAttributeDefinitions() {
		// ts.msg = (if t.pass then "." else "\n" ++ t.msg) ++ rest.msg
		silver.testing.PtestCons.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(((Boolean)context.childDecorated(silver.testing.PtestCons.i_t).synthesized(silver.testing.Init.silver_testing_pass__ON__silver_testing_Test)) ? (new common.StringCatter(".")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.testing.PtestCons.i_t).synthesized(silver.testing.Init.silver_testing_msg__ON__silver_testing_Test)))), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.testing.PtestCons.i_rest).synthesized(silver.testing.Init.silver_testing_msg__ON__silver_testing_TestSuite))); } };
		// ts.numTests = 1 + rest.numTests
		silver.testing.PtestCons.synthesizedAttributes[silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf((int)1) + ((Integer)context.childDecorated(silver.testing.PtestCons.i_rest).synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite))); } };
		// ts.numPassed = (if t.pass then 1 else 0) + rest.numPassed
		silver.testing.PtestCons.synthesizedAttributes[silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((((Boolean)context.childDecorated(silver.testing.PtestCons.i_t).synthesized(silver.testing.Init.silver_testing_pass__ON__silver_testing_Test)) ? Integer.valueOf((int)1) : Integer.valueOf((int)0)) + ((Integer)context.childDecorated(silver.testing.PtestCons.i_rest).synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite))); } };
		// ts.numFailed = ts.numTests - ts.numPassed
		silver.testing.PtestCons.synthesizedAttributes[silver.testing.Init.silver_testing_numFailed__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.synthesized(silver.testing.Init.silver_testing_numTests__ON__silver_testing_TestSuite)) - ((Integer)context.synthesized(silver.testing.Init.silver_testing_numPassed__ON__silver_testing_TestSuite))); } };
		// rest.ioIn = ts.ioIn
		silver.testing.PtestCons.childInheritedAttributes[silver.testing.PtestCons.i_rest][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.testing.Init.silver_testing_ioIn__ON__silver_testing_TestSuite)); } };
		// t.ioIn = rest.ioOut
		silver.testing.PtestCons.childInheritedAttributes[silver.testing.PtestCons.i_t][silver.testing.Init.silver_testing_ioIn__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.testing.PtestCons.i_rest).synthesized(silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite)); } };
		// ts.ioOut = t.ioOut
		silver.testing.PtestCons.synthesizedAttributes[silver.testing.Init.silver_testing_ioOut__ON__silver_testing_TestSuite] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.testing.PtestCons.i_t).synthesized(silver.testing.Init.silver_testing_ioOut__ON__silver_testing_Test)); } };

	}

	public static final common.NodeFactory<PtestCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtestCons> {

		@Override
		public PtestCons invoke(final Object[] children, final Object[] annotations) {
			return new PtestCons(children[0], children[1]);
		}
	};

}
