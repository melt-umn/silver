
package copper_features;

// t::Test ::= 
public final class PgeneratedTest_ParserAttributes_sv_64_15 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_generatedTest_ParserAttributes_sv_64_15;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_ParserAttributes_sv_64_15() {
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
		return "copper_features:generatedTest_ParserAttributes_sv_64_15";
	}

	static void initProductionAttributeDefinitions() {
		// value = PAparse("!foo bar", "",).parseTree.unknownsused
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.localAttributes[copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_64_15] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((copper_features.NUseDcls)((core.NParseResult)copper_features.PPAparse.invoke((new common.StringCatter("!foo bar")), (new common.StringCatter("")))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_parseTree__ON__core_ParseResult)).decorate(context, (common.Lazy[])null).synthesized(copper_features.Init.copper_features_unknownsused__ON__copper_features_UseDcls)); } };
		// expected = [ "bar" ]
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.localAttributes[copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_64_15] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("bar")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// t.msg = "Test at 'ParserAttributes.sv', 64, 0, 64, 98, 1639, 1737 failed.\nChecking that expression\n   PAparse(\\\"!foo bar\\\", \\\"\\\",).parseTree.unknownsused\nshould be same as expression\n   [ \\\"bar\\\" ]\nActual value:\n   " ++ toStringFromList(toStringFromString, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromString, expected,) ++ "\n" ++ ""
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'ParserAttributes.sv', 64, 0, 64, 98, 1639, 1737 failed.\nChecking that expression\n   PAparse(\\\"!foo bar\\\", \\\"\\\",).parseTree.unknownsused\nshould be same as expression\n   [ \\\"bar\\\" ]\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromString.factory, context.localAsIsLazy(copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_64_15))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromString.factory, context.localAsIsLazy(copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_64_15))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsString, value, expected,)
		copper_features.PgeneratedTest_ParserAttributes_sv_64_15.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsString.factory, context.localAsIsLazy(copper_features.Init.value__ON__copper_features_generatedTest_ParserAttributes_sv_64_15), context.localAsIsLazy(copper_features.Init.expected__ON__copper_features_generatedTest_ParserAttributes_sv_64_15))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_ParserAttributes_sv_64_15> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_ParserAttributes_sv_64_15> {

		@Override
		public PgeneratedTest_ParserAttributes_sv_64_15 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_ParserAttributes_sv_64_15();
		}
	};

}
