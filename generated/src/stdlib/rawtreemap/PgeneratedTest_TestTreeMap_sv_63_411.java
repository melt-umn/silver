
package stdlib.rawtreemap;

// t::Test ::= 
public final class PgeneratedTest_TestTreeMap_sv_63_411 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_TestTreeMap_sv_63_411() {
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
		return "stdlib:rawtreemap:generatedTest_TestTreeMap_sv_63_411";
	}

	static void initProductionAttributeDefinitions() {
		// value = head(tail(l2,),).snd
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.localAttributes[stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)((core.NPair)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(stdlib.rawtreemap.Init.l2)); } })).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } };
		// expected = 2
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.localAttributes[stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)2); } };
		// t.msg = "Test at 'TestTreeMap.sv', 63, 0, 63, 61, 3059, 3120 failed.\nChecking that expression\n   head(tail(l2,),).snd\nshould be same as expression\n   2\nActual value:\n   " ++ toStringFromInteger(value,) ++ "\nExpected value: \n   " ++ toStringFromInteger(expected,) ++ "\n" ++ ""
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'TestTreeMap.sv', 63, 0, 63, 61, 3059, 3120 failed.\nChecking that expression\n   head(tail(l2,),).snd\nshould be same as expression\n   2\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(context.localAsIsLazy(stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(context.localAsIsLazy(stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsInteger(value, expected,)
		stdlib.rawtreemap.PgeneratedTest_TestTreeMap_sv_63_411.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsInteger.invoke(context.localAsIsLazy(stdlib.rawtreemap.Init.value__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411), context.localAsIsLazy(stdlib.rawtreemap.Init.expected__ON__stdlib_rawtreemap_generatedTest_TestTreeMap_sv_63_411))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_TestTreeMap_sv_63_411> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_TestTreeMap_sv_63_411> {

		@Override
		public PgeneratedTest_TestTreeMap_sv_63_411 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_TestTreeMap_sv_63_411();
		}
	};

}
