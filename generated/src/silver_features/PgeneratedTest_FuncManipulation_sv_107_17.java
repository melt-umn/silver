
package silver_features;

// t::Test ::= 
public final class PgeneratedTest_FuncManipulation_sv_107_17 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_generatedTest_FuncManipulation_sv_107_17;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_FuncManipulation_sv_107_17() {
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
		return "silver_features:generatedTest_FuncManipulation_sv_107_17";
	}

	static void initProductionAttributeDefinitions() {
		// value = map(invoker, repeat("", 5,),)
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.localAttributes[silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_107_17] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver_features.Init.invoker, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Prepeat.invoke((new common.StringCatter("")), Integer.valueOf((int)5))); } })); } };
		// expected = repeat(invoker("",), 5,)
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.localAttributes[silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_107_17] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Prepeat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((common.NodeFactory<Integer>)silver_features.Init.invoker.eval()).invoke(new Object[]{(new common.StringCatter(""))}, null)); } }, Integer.valueOf((int)5))); } };
		// t.msg = "Test at 'FuncManipulation.sv', 107, 0, 107, 94, 2729, 2823 failed.\nChecking that expression\n   map(invoker, repeat(\\\"\\\", 5,),)\nshould be same as expression\n   repeat(invoker(\\\"\\\",), 5,)\nActual value:\n   " ++ toStringFromList(toStringFromInteger, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromInteger, expected,) ++ "\n" ++ ""
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'FuncManipulation.sv', 107, 0, 107, 94, 2729, 2823 failed.\nChecking that expression\n   map(invoker, repeat(\\\"\\\", 5,),)\nshould be same as expression\n   repeat(invoker(\\\"\\\",), 5,)\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromInteger.factory, context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_107_17))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromInteger.factory, context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_107_17))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsInteger, value, expected,)
		silver_features.PgeneratedTest_FuncManipulation_sv_107_17.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsInteger.factory, context.localAsIsLazy(silver_features.Init.value__ON__silver_features_generatedTest_FuncManipulation_sv_107_17), context.localAsIsLazy(silver_features.Init.expected__ON__silver_features_generatedTest_FuncManipulation_sv_107_17))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_FuncManipulation_sv_107_17> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_FuncManipulation_sv_107_17> {

		@Override
		public PgeneratedTest_FuncManipulation_sv_107_17 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_FuncManipulation_sv_107_17();
		}
	};

}
