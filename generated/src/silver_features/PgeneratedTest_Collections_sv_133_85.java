
package silver_features;

// t::Test ::= 
public final class PgeneratedTest_Collections_sv_133_85 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_generatedTest_Collections_sv_133_85;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Collections_sv_133_85() {
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
		return "silver_features:generatedTest_Collections_sv_133_85";
	}

	static void initProductionAttributeDefinitions() {
		// value = colTest1(,).colList
		silver_features.PgeneratedTest_Collections_sv_133_85.localAttributes[silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_133_85] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((silver_features.NColNT)new silver_features.PcolTest1()).decorate(context, (common.Lazy[])null).synthesized(silver_features.Init.silver_features_colList__ON__silver_features_ColNT)); } };
		// expected = [ "one", "two" ]
		silver_features.PgeneratedTest_Collections_sv_133_85.localAttributes[silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_133_85] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("one")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("two")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };
		// t.msg = "Test at 'Collections.sv', 133, 0, 133, 75, 3403, 3478 failed.\nChecking that expression\n   colTest1(,).colList\nshould be same as expression\n   [ \\\"one\\\", \\\"two\\\" ]\nActual value:\n   " ++ toStringFromList(toStringFromString, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromString, expected,) ++ "\n" ++ ""
		silver_features.PgeneratedTest_Collections_sv_133_85.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Collections.sv', 133, 0, 133, 75, 3403, 3478 failed.\nChecking that expression\n   colTest1(,).colList\nshould be same as expression\n   [ \\\"one\\\", \\\"two\\\" ]\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromString.factory, context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_133_85))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromString.factory, context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_133_85))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsString, value, expected,)
		silver_features.PgeneratedTest_Collections_sv_133_85.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsString.factory, context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_Collections_sv_133_85), context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_Collections_sv_133_85))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Collections_sv_133_85> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Collections_sv_133_85> {

		@Override
		public PgeneratedTest_Collections_sv_133_85 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Collections_sv_133_85();
		}
	};

}
