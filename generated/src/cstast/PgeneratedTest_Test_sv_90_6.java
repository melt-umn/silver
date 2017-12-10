
package cstast;

// t::Test ::= 
public final class PgeneratedTest_Test_sv_90_6 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__cstast_generatedTest_Test_sv_90_6;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_Test_sv_90_6() {
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
		return "cstast:generatedTest_Test_sv_90_6";
	}

	static void initProductionAttributeDefinitions() {
		// value = obj3_again.unparse
		cstast.PgeneratedTest_Test_sv_90_6.localAttributes[cstast.Init.value__ON__cstast_generatedTest_Test_sv_90_6] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.concrete_syntax.ast.NSyntaxRoot)cstast.Init.obj3_again.eval()).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } };
		// expected = obj3.unparse
		cstast.PgeneratedTest_Test_sv_90_6.localAttributes[cstast.Init.expected__ON__cstast_generatedTest_Test_sv_90_6] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.concrete_syntax.ast.NSyntaxRoot)cstast.Init.obj3.eval()).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ast_SyntaxRoot)); } };
		// t.msg = "Test at 'Test.sv', 90, 0, 90, 67, 3222, 3289 failed.\nChecking that expression\n   obj3_again.unparse\nshould be same as expression\n   obj3.unparse\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		cstast.PgeneratedTest_Test_sv_90_6.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'Test.sv', 90, 0, 90, 67, 3222, 3289 failed.\nChecking that expression\n   obj3_again.unparse\nshould be same as expression\n   obj3.unparse\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(cstast.Init.value__ON__cstast_generatedTest_Test_sv_90_6))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(cstast.Init.expected__ON__cstast_generatedTest_Test_sv_90_6))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		cstast.PgeneratedTest_Test_sv_90_6.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(cstast.Init.value__ON__cstast_generatedTest_Test_sv_90_6), context.localAsIsLazy(cstast.Init.expected__ON__cstast_generatedTest_Test_sv_90_6))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_Test_sv_90_6> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_Test_sv_90_6> {

		@Override
		public PgeneratedTest_Test_sv_90_6 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_Test_sv_90_6();
		}
	};

}
