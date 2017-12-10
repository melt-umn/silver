
package copper_features;

// t::Test ::= 
public final class PgeneratedTest_DisambiguationGroups_sv_32_5 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_DisambiguationGroups_sv_32_5() {
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
		return "copper_features:generatedTest_DisambiguationGroups_sv_32_5";
	}

	static void initProductionAttributeDefinitions() {
		// value = dgparse("!#@$%", "ASDF",).parseSuccess
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.localAttributes[copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)((core.NParseResult)copper_features.Pdgparse.invoke((new common.StringCatter("!#@$%")), (new common.StringCatter("ASDF")))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult)); } };
		// expected = false
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.localAttributes[copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// t.msg = "Test at 'DisambiguationGroups.sv', 32, 0, 32, 86, 693, 779 failed.\nChecking that expression\n   dgparse(\\\"!#@$%\\\", \\\"ASDF\\\",).parseSuccess\nshould be same as expression\n   false\nActual value:\n   " ++ toStringFromBoolean(value,) ++ "\nExpected value: \n   " ++ toStringFromBoolean(expected,) ++ "\n" ++ ""
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'DisambiguationGroups.sv', 32, 0, 32, 86, 693, 779 failed.\nChecking that expression\n   dgparse(\\\"!#@$%\\\", \\\"ASDF\\\",).parseSuccess\nshould be same as expression\n   false\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromBoolean.invoke(context.localAsIsLazy(copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsBoolean(value, expected,)
		copper_features.PgeneratedTest_DisambiguationGroups_sv_32_5.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsBoolean.invoke(context.localAsIsLazy(copper_features.Init.value__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5), context.localAsIsLazy(copper_features.Init.expected__ON__copper_features_generatedTest_DisambiguationGroups_sv_32_5))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_DisambiguationGroups_sv_32_5> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_DisambiguationGroups_sv_32_5> {

		@Override
		public PgeneratedTest_DisambiguationGroups_sv_32_5 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_DisambiguationGroups_sv_32_5();
		}
	};

}
