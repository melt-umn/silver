
package silver_features;

// t::Test ::= 
public final class PgeneratedTest_FuncManipulation_sv_72_14 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_72_14;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_FuncManipulation_sv_72_14() {
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
		return "silver_features:generatedTest_FuncManipulation_sv_72_14";
	}

	static void initProductionAttributeDefinitions() {
		// value = map((.sec_valid), sections,)
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.localAttributes[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_72_14] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver_features.Init.silver_features_sec_valid__ON__silver_features_Section, context), silver_features.Init.sections)); } };
		// expected = [ 2, 2 ]
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.localAttributes[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_72_14] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(Integer.valueOf((int)2), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
		// t.msg = "Test at 'FuncManipulation.sv', 72, 0, 72, 76, 1663, 1739 failed.\nChecking that expression\n   map((.sec_valid), sections,)\nshould be same as expression\n   [ 2, 2 ]\nActual value:\n   " ++ toStringFromList(toStringFromInteger, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromInteger, expected,) ++ "\n" ++ ""
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'FuncManipulation.sv', 72, 0, 72, 76, 1663, 1739 failed.\nChecking that expression\n   map((.sec_valid), sections,)\nshould be same as expression\n   [ 2, 2 ]\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromInteger.factory, context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_72_14))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromInteger.factory, context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_72_14))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsInteger, value, expected,)
		silver_features.PgeneratedTest_FuncManipulation_sv_72_14.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsInteger.factory, context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_72_14), context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_72_14))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_FuncManipulation_sv_72_14> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_FuncManipulation_sv_72_14> {

		@Override
		public PgeneratedTest_FuncManipulation_sv_72_14 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_FuncManipulation_sv_72_14();
		}
	};

}
