
package stdlib;

// t::Test ::= 
public final class PgeneratedTest_String_sv_121_131 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_generatedTest_String_sv_121_131;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_String_sv_121_131() {
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
		return "stdlib:generatedTest_String_sv_121_131";
	}

	static void initProductionAttributeDefinitions() {
		// value = replaceChars(".", "_", ".1.2.3.",)
		stdlib.PgeneratedTest_String_sv_121_131.localAttributes[stdlib.Init.value__ON__stdlib_generatedTest_String_sv_121_131] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)lib.extcore.PreplaceChars.invoke((new common.StringCatter(".")), (new common.StringCatter("_")), (new common.StringCatter(".1.2.3.")))); } };
		// expected = "_1_2_3_"
		stdlib.PgeneratedTest_String_sv_121_131.localAttributes[stdlib.Init.expected__ON__stdlib_generatedTest_String_sv_121_131] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("_1_2_3_")); } };
		// t.msg = "Test at 'String.sv', 121, 0, 121, 84, 4377, 4461 failed.\nChecking that expression\n   replaceChars(\\\".\\\", \\\"_\\\", \\\".1.2.3.\\\",)\nshould be same as expression\n   \\\"_1_2_3_\\\"\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		stdlib.PgeneratedTest_String_sv_121_131.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'String.sv', 121, 0, 121, 84, 4377, 4461 failed.\nChecking that expression\n   replaceChars(\\\".\\\", \\\"_\\\", \\\".1.2.3.\\\",)\nshould be same as expression\n   \\\"_1_2_3_\\\"\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.Init.value__ON__stdlib_generatedTest_String_sv_121_131))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.Init.expected__ON__stdlib_generatedTest_String_sv_121_131))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		stdlib.PgeneratedTest_String_sv_121_131.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(stdlib.Init.value__ON__stdlib_generatedTest_String_sv_121_131), context.localAsIsLazy(stdlib.Init.expected__ON__stdlib_generatedTest_String_sv_121_131))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_String_sv_121_131> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_String_sv_121_131> {

		@Override
		public PgeneratedTest_String_sv_121_131 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_String_sv_121_131();
		}
	};

}
