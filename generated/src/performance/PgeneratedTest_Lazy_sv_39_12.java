
package performance;

// t::Test ::= 
public final class PgeneratedTest_Lazy_sv_39_12 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__performance_generatedTest_Lazy_sv_39_12;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Lazy_sv_39_12() {
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
		return "performance:generatedTest_Lazy_sv_39_12";
	}

	static void initProductionAttributeDefinitions() {
		// value = null((head(nolist,) :: tail(nolist,)),)
		performance.PgeneratedTest_Lazy_sv_39_12.localAttributes[performance.Init.value__ON__performance_generatedTest_Lazy_sv_39_12] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.Phead.invoke(performance.Init.nolist)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(performance.Init.nolist)); } })); } })); } };
		// expected = false
		performance.PgeneratedTest_Lazy_sv_39_12.localAttributes[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_39_12] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// t.msg = "Test at 'Lazy.sv', 39, 0, 39, 88, 1529, 1617 failed.\nChecking that expression\n   null((head(nolist,) :: tail(nolist,)),)\nshould be same as expression\n   false\nActual value:\n   " ++ toStringFromBoolean(value,) ++ "\nExpected value: \n   " ++ toStringFromBoolean(expected,) ++ "\n" ++ ""
		performance.PgeneratedTest_Lazy_sv_39_12.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Lazy.sv', 39, 0, 39, 88, 1529, 1617 failed.\nChecking that expression\n   null((head(nolist,) :: tail(nolist,)),)\nshould be same as expression\n   false\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(performance.Init.value__ON__performance_generatedTest_Lazy_sv_39_12))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(performance.Init.expected__ON__performance_generatedTest_Lazy_sv_39_12))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsBoolean(value, expected,)
		performance.PgeneratedTest_Lazy_sv_39_12.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsBoolean.invoke(context.localAsIsLazy(performance.Init.value__ON__performance_generatedTest_Lazy_sv_39_12), context.localAsIsLazy(performance.Init.expected__ON__performance_generatedTest_Lazy_sv_39_12))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Lazy_sv_39_12> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Lazy_sv_39_12> {

		@Override
		public PgeneratedTest_Lazy_sv_39_12 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Lazy_sv_39_12();
		}
	};

}
