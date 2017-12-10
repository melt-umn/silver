
package performance;

// t::Test ::= 
public final class PgeneratedTest_Lazy_sv_26_4 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__performance_generatedTest_Lazy_sv_26_4;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Lazy_sv_26_4() {
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.testing.NTest)new silver.testing.PdefTest());
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
		return "performance:generatedTest_Lazy_sv_26_4";
	}

	static void initProductionAttributeDefinitions() {
		// value = take(5, infiniteOnes,)
		performance.PgeneratedTest_Lazy_sv_26_4.localAttributes[performance.Init.value__ON__performance_generatedTest_Lazy_sv_26_4] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptake.invoke(Integer.valueOf((int)5), performance.Init.infiniteOnes)); } };
		// expected = [ 1, 1, 1, 1, 1 ]
		performance.PgeneratedTest_Lazy_sv_26_4.localAttributes[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_26_4] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
		// t.msg = "Test at 'Lazy.sv', 26, 0, 26, 82, 573, 655 failed.\nChecking that expression\n   take(5, infiniteOnes,)\nshould be same as expression\n   [ 1, 1, 1, 1, 1 ]\nActual value:\n   " ++ toStringFromList(toStringFromInteger, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromInteger, expected,) ++ "\n" ++ ""
		performance.PgeneratedTest_Lazy_sv_26_4.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Lazy.sv', 26, 0, 26, 82, 573, 655 failed.\nChecking that expression\n   take(5, infiniteOnes,)\nshould be same as expression\n   [ 1, 1, 1, 1, 1 ]\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromInteger.factory, context.localAsIsLazy(performance.Init.value__ON__performance_generatedTest_Lazy_sv_26_4))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromInteger.factory, context.localAsIsLazy(performance.Init.expected__ON__performance_generatedTest_Lazy_sv_26_4))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsInteger, value, expected,)
		performance.PgeneratedTest_Lazy_sv_26_4.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsInteger.factory, context.localAsIsLazy(performance.Init.value__ON__performance_generatedTest_Lazy_sv_26_4), context.localAsIsLazy(performance.Init.expected__ON__performance_generatedTest_Lazy_sv_26_4))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Lazy_sv_26_4> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Lazy_sv_26_4> {

		@Override
		public PgeneratedTest_Lazy_sv_26_4 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Lazy_sv_26_4();
		}
	};

}
