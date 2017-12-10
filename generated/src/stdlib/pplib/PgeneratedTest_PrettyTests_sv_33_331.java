
package stdlib.pplib;

// t::Test ::= 
public final class PgeneratedTest_PrettyTests_sv_33_331 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_PrettyTests_sv_33_331() {
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
		return "stdlib:pplib:generatedTest_PrettyTests_sv_33_331";
	}

	static void initProductionAttributeDefinitions() {
		// value = show(1, doc2,)
		stdlib.pplib.PgeneratedTest_PrettyTests_sv_33_331.localAttributes[stdlib.pplib.Init.value__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.langutil.pp.Pshow.invoke(Integer.valueOf((int)1), stdlib.pplib.Init.doc2)); } };
		// expected = "int var1\nint var2\nint var3"
		stdlib.pplib.PgeneratedTest_PrettyTests_sv_33_331.localAttributes[stdlib.pplib.Init.expected__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("int var1\nint var2\nint var3")); } };
		// t.msg = "Test at 'PrettyTests.sv', 33, 0, 33, 84, 1322, 1406 failed.\nChecking that expression\n   show(1, doc2,)\nshould be same as expression\n   \\\"int var1\\\\nint var2\\\\nint var3\\\"\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		stdlib.pplib.PgeneratedTest_PrettyTests_sv_33_331.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'PrettyTests.sv', 33, 0, 33, 84, 1322, 1406 failed.\nChecking that expression\n   show(1, doc2,)\nshould be same as expression\n   \\\"int var1\\\\nint var2\\\\nint var3\\\"\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.pplib.Init.value__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.pplib.Init.expected__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		stdlib.pplib.PgeneratedTest_PrettyTests_sv_33_331.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(stdlib.pplib.Init.value__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331), context.localAsIsLazy(stdlib.pplib.Init.expected__ON__stdlib_pplib_generatedTest_PrettyTests_sv_33_331))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_PrettyTests_sv_33_331> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_PrettyTests_sv_33_331> {

		@Override
		public PgeneratedTest_PrettyTests_sv_33_331 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_PrettyTests_sv_33_331();
		}
	};

}
