
package performance;

// t::Test ::= 
public final class PgeneratedTest_Lazy_sv_21_3 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__performance_generatedTest_Lazy_sv_21_3;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Lazy_sv_21_3() {
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
		return "performance:generatedTest_Lazy_sv_21_3";
	}

	static void initProductionAttributeDefinitions() {
		// value = q.a_q.a_q.a_q.str
		performance.PgeneratedTest_Lazy_sv_21_3.localAttributes[performance.Init.value__ON__performance_generatedTest_Lazy_sv_21_3] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((performance.NQ)((performance.NQ)((performance.NQ)((performance.NQ)performance.Init.q.eval()).decorate(context, (common.Lazy[])null).synthesized(performance.Init.performance_a_q__ON__performance_Q)).decorate(context, (common.Lazy[])null).synthesized(performance.Init.performance_a_q__ON__performance_Q)).decorate(context, (common.Lazy[])null).synthesized(performance.Init.performance_a_q__ON__performance_Q)).decorate(context, (common.Lazy[])null).synthesized(performance.Init.performance_str__ON__performance_Q)); } };
		// expected = "q"
		performance.PgeneratedTest_Lazy_sv_21_3.localAttributes[performance.Init.expected__ON__performance_generatedTest_Lazy_sv_21_3] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("q")); } };
		// t.msg = "Test at 'Lazy.sv', 21, 0, 21, 68, 428, 496 failed.\nChecking that expression\n   q.a_q.a_q.a_q.str\nshould be same as expression\n   \\\"q\\\"\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		performance.PgeneratedTest_Lazy_sv_21_3.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Lazy.sv', 21, 0, 21, 68, 428, 496 failed.\nChecking that expression\n   q.a_q.a_q.a_q.str\nshould be same as expression\n   \\\"q\\\"\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(performance.Init.value__ON__performance_generatedTest_Lazy_sv_21_3))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(performance.Init.expected__ON__performance_generatedTest_Lazy_sv_21_3))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		performance.PgeneratedTest_Lazy_sv_21_3.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(performance.Init.value__ON__performance_generatedTest_Lazy_sv_21_3), context.localAsIsLazy(performance.Init.expected__ON__performance_generatedTest_Lazy_sv_21_3))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Lazy_sv_21_3> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Lazy_sv_21_3> {

		@Override
		public PgeneratedTest_Lazy_sv_21_3 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Lazy_sv_21_3();
		}
	};

}
