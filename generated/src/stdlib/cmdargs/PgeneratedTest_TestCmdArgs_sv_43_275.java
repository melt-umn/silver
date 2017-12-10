
package stdlib.cmdargs;

// t::Test ::= 
public final class PgeneratedTest_TestCmdArgs_sv_43_275 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_TestCmdArgs_sv_43_275() {
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
		return "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_43_275";
	}

	static void initProductionAttributeDefinitions() {
		// value = interpretCmdArgs(flags1, [ "a", "b", "c" ],).cmdRemaining
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.localAttributes[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.util.cmdargs.NCmdArgs)silver.util.cmdargs.PinterpretCmdArgs.invoke(stdlib.cmdargs.Init.flags1, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("a")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("b")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("c")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })).decorate(context, (common.Lazy[])null).synthesized(silver.util.cmdargs.Init.silver_util_cmdargs_cmdRemaining__ON__silver_util_cmdargs_CmdArgs)); } };
		// expected = [ "a", "b", "c" ]
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.localAttributes[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("a")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("b")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("c")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		// t.msg = "Test at 'TestCmdArgs.sv', 43, 0, 45, 37, 838, 978 failed.\nChecking that expression\n   interpretCmdArgs(flags1, [ \\\"a\\\", \\\"b\\\", \\\"c\\\" ],).cmdRemaining\nshould be same as expression\n   [ \\\"a\\\", \\\"b\\\", \\\"c\\\" ]\nActual value:\n   " ++ toStringFromList(toStringFromString, value,) ++ "\nExpected value: \n   " ++ toStringFromList(toStringFromString, expected,) ++ "\n" ++ ""
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'TestCmdArgs.sv', 43, 0, 45, 37, 838, 978 failed.\nChecking that expression\n   interpretCmdArgs(flags1, [ \\\"a\\\", \\\"b\\\", \\\"c\\\" ],).cmdRemaining\nshould be same as expression\n   [ \\\"a\\\", \\\"b\\\", \\\"c\\\" ]\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromString.factory, context.localAsIsLazy(stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromList.invoke(lib.extcore.PtoStringFromString.factory, context.localAsIsLazy(stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsList(equalsString, value, expected,)
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_43_275.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsList.invoke(lib.extcore.PequalsString.factory, context.localAsIsLazy(stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275), context.localAsIsLazy(stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_43_275))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_TestCmdArgs_sv_43_275> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_TestCmdArgs_sv_43_275> {

		@Override
		public PgeneratedTest_TestCmdArgs_sv_43_275 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_TestCmdArgs_sv_43_275();
		}
	};

}
