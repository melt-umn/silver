
package stdlib.rawtreeset;

// t::Test ::= 
public final class PgeneratedTest_SetTests_sv_37_372 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_SetTests_sv_37_372() {
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
		return "stdlib:rawtreeset:generatedTest_SetTests_sv_37_372";
	}

	static void initProductionAttributeDefinitions() {
		// value = ts:subset(set1, ts:union(set1, set2,),)
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.localAttributes[stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)silver.util.raw.treeset.Psubset.invoke(stdlib.rawtreeset.Init.set1, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.util.raw.treeset.Punion.invoke(stdlib.rawtreeset.Init.set1, stdlib.rawtreeset.Init.set2)); } })); } };
		// expected = true
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.localAttributes[stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// t.msg = "Test at 'SetTests.sv', 37, 0, 37, 82, 1287, 1369 failed.\nChecking that expression\n   ts:subset(set1, ts:union(set1, set2,),)\nshould be same as expression\n   true\nActual value:\n   " ++ toStringFromBoolean(value,) ++ "\nExpected value: \n   " ++ toStringFromBoolean(expected,) ++ "\n" ++ ""
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'SetTests.sv', 37, 0, 37, 82, 1287, 1369 failed.\nChecking that expression\n   ts:subset(set1, ts:union(set1, set2,),)\nshould be same as expression\n   true\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsBoolean(value, expected,)
		stdlib.rawtreeset.PgeneratedTest_SetTests_sv_37_372.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsBoolean.invoke(context.localAsIsLazy(stdlib.rawtreeset.Init.value__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372), context.localAsIsLazy(stdlib.rawtreeset.Init.expected__ON__stdlib_rawtreeset_generatedTest_SetTests_sv_37_372))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_SetTests_sv_37_372> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_SetTests_sv_37_372> {

		@Override
		public PgeneratedTest_SetTests_sv_37_372 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_SetTests_sv_37_372();
		}
	};

}
