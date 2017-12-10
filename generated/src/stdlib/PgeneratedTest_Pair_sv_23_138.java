
package stdlib;

// t::Test ::= 
public final class PgeneratedTest_Pair_sv_23_138 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_generatedTest_Pair_sv_23_138;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Pair_sv_23_138() {
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
		return "stdlib:generatedTest_Pair_sv_23_138";
	}

	static void initProductionAttributeDefinitions() {
		// value = lookupBy(stringEq, "2", pairtester,).fromJust
		stdlib.PgeneratedTest_Pair_sv_23_138.localAttributes[stdlib.Init.value__ON__stdlib_generatedTest_Pair_sv_23_138] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, (new common.StringCatter("2")), stdlib.Init.pairtester)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)); } };
		// expected = 2
		stdlib.PgeneratedTest_Pair_sv_23_138.localAttributes[stdlib.Init.expected__ON__stdlib_generatedTest_Pair_sv_23_138] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)2); } };
		// t.msg = "Test at 'Pair.sv', 23, 0, 24, 38, 529, 631 failed.\nChecking that expression\n   lookupBy(stringEq, \\\"2\\\", pairtester,).fromJust\nshould be same as expression\n   2\nActual value:\n   " ++ toStringFromInteger(value,) ++ "\nExpected value: \n   " ++ toStringFromInteger(expected,) ++ "\n" ++ ""
		stdlib.PgeneratedTest_Pair_sv_23_138.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Pair.sv', 23, 0, 24, 38, 529, 631 failed.\nChecking that expression\n   lookupBy(stringEq, \\\"2\\\", pairtester,).fromJust\nshould be same as expression\n   2\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(context.localAsIsLazy(stdlib.Init.value__ON__stdlib_generatedTest_Pair_sv_23_138))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(context.localAsIsLazy(stdlib.Init.expected__ON__stdlib_generatedTest_Pair_sv_23_138))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsInteger(value, expected,)
		stdlib.PgeneratedTest_Pair_sv_23_138.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsInteger.invoke(context.localAsIsLazy(stdlib.Init.value__ON__stdlib_generatedTest_Pair_sv_23_138), context.localAsIsLazy(stdlib.Init.expected__ON__stdlib_generatedTest_Pair_sv_23_138))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Pair_sv_23_138> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Pair_sv_23_138> {

		@Override
		public PgeneratedTest_Pair_sv_23_138 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Pair_sv_23_138();
		}
	};

}
