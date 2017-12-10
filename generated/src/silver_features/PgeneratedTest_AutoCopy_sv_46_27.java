
package silver_features;

// t::Test ::= 
public final class PgeneratedTest_AutoCopy_sv_46_27 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_generatedTest_AutoCopy_sv_46_27;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_AutoCopy_sv_46_27() {
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
		return "silver_features:generatedTest_AutoCopy_sv_46_27";
	}

	static void initProductionAttributeDefinitions() {
		// value = decorate autoValue with {autoTest = 1;}.autoSyn
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.localAttributes[silver_features.Init.value__ON__silver_features_generatedTest_AutoCopy_sv_46_27] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)((silver_features.NAutoCopyTestNT)silver_features.Init.autoValue.eval()).decorate(context, common.Util.populateInh(silver_features.NAutoCopyTestNT.num_inh_attrs, new int[]{silver_features.Init.silver_features_autoTest__ON__silver_features_AutoCopyTestNT}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } }})).synthesized(silver_features.Init.silver_features_autoSyn__ON__silver_features_AutoCopyTestNT)); } };
		// expected = 4
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.localAttributes[silver_features.Init.expected__ON__silver_features_generatedTest_AutoCopy_sv_46_27] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)4); } };
		// t.msg = "Test at 'AutoCopy.sv', 46, 0, 46, 91, 972, 1063 failed.\nChecking that expression\n   decorate autoValue with {autoTest = 1;}.autoSyn\nshould be same as expression\n   4\nActual value:\n   " ++ toStringFromInteger(value,) ++ "\nExpected value: \n   " ++ toStringFromInteger(expected,) ++ "\n" ++ ""
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'AutoCopy.sv', 46, 0, 46, 91, 972, 1063 failed.\nChecking that expression\n   decorate autoValue with {autoTest = 1;}.autoSyn\nshould be same as expression\n   4\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_AutoCopy_sv_46_27))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromInteger.invoke(context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_AutoCopy_sv_46_27))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsInteger(value, expected,)
		silver_features.PgeneratedTest_AutoCopy_sv_46_27.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsInteger.invoke(context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_AutoCopy_sv_46_27), context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_AutoCopy_sv_46_27))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_AutoCopy_sv_46_27> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_AutoCopy_sv_46_27> {

		@Override
		public PgeneratedTest_AutoCopy_sv_46_27 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_AutoCopy_sv_46_27();
		}
	};

}
