
package silver_features;

// t::Test ::= 
public final class PgeneratedTest_Templating_sv_19_55 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_generatedTest_Templating_sv_19_55;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Templating_sv_19_55() {
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
		return "silver_features:generatedTest_Templating_sv_19_55";
	}

	static void initProductionAttributeDefinitions() {
		// value = "hello" ++ toString(1)
		silver_features.PgeneratedTest_Templating_sv_19_55.localAttributes[silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_19_55] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("hello")), (common.StringCatter)new common.StringCatter(String.valueOf(Integer.valueOf((int)1)))); } };
		// expected = "hello1"
		silver_features.PgeneratedTest_Templating_sv_19_55.localAttributes[silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_19_55] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("hello1")); } };
		// t.msg = "Test at 'Templating.sv', 19, 0, 19, 77, 468, 545 failed.\nChecking that expression\n   \\\"hello\\\" ++ toString(1)\nshould be same as expression\n   \\\"hello1\\\"\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		silver_features.PgeneratedTest_Templating_sv_19_55.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Templating.sv', 19, 0, 19, 77, 468, 545 failed.\nChecking that expression\n   \\\"hello\\\" ++ toString(1)\nshould be same as expression\n   \\\"hello1\\\"\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_19_55))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_19_55))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		silver_features.PgeneratedTest_Templating_sv_19_55.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_Templating_sv_19_55), context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_Templating_sv_19_55))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Templating_sv_19_55> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Templating_sv_19_55> {

		@Override
		public PgeneratedTest_Templating_sv_19_55 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Templating_sv_19_55();
		}
	};

}
