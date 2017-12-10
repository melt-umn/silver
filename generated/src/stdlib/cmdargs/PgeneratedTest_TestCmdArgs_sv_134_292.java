
package stdlib.cmdargs;

// t::Test ::= 
public final class PgeneratedTest_TestCmdArgs_sv_134_292 extends silver.testing.NTest {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PgeneratedTest_TestCmdArgs_sv_134_292() {
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
		return "stdlib:cmdargs:generatedTest_TestCmdArgs_sv_134_292";
	}

	static void initProductionAttributeDefinitions() {
		// value = interpretCmdArgs(flags2, [ "-verbose", "-nosilly", "-I", "document and settings", "-silly", "-I" ],).cmdError.fromJust
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.localAttributes[stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NMaybe)((silver.util.cmdargs.NCmdArgs)silver.util.cmdargs.PinterpretCmdArgs.invoke(stdlib.cmdargs.Init.flags2, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("-verbose")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("-nosilly")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("-I")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("document and settings")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("-silly")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("-I")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })).decorate(context, (common.Lazy[])null).synthesized(silver.util.cmdargs.Init.silver_util_cmdargs_cmdError__ON__silver_util_cmdargs_CmdArgs)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fromJust__ON__core_Maybe)); } };
		// expected = "-I is missing its parameter."
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.localAttributes[stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("-I is missing its parameter.")); } };
		// t.msg = "Test at 'TestCmdArgs.sv', 134, 0, 136, 35, 4595, 4808 failed.\nChecking that expression\n   interpretCmdArgs(flags2, [ \\\"-verbose\\\", \\\"-nosilly\\\", \\\"-I\\\", \\\"document and settings\\\", \\\"-silly\\\", \\\"-I\\\" ],).cmdError.fromJust\nshould be same as expression\n   \\\"-I is missing its parameter.\\\"\nActual value:\n   " ++ toStringFromString(value,) ++ "\nExpected value: \n   " ++ toStringFromString(expected,) ++ "\n" ++ ""
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Test at 'TestCmdArgs.sv', 134, 0, 136, 35, 4595, 4808 failed.\nChecking that expression\n   interpretCmdArgs(flags2, [ \\\"-verbose\\\", \\\"-nosilly\\\", \\\"-I\\\", \\\"document and settings\\\", \\\"-silly\\\", \\\"-I\\\" ],).cmdError.fromJust\nshould be same as expression\n   \\\"-I is missing its parameter.\\\"\nActual value:\n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\nExpected value: \n   ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)lib.extcore.PtoStringFromString.invoke(context.localAsIsLazy(stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)(new common.StringCatter(""))))))); } };
		// t.pass = equalsString(value, expected,)
		stdlib.cmdargs.PgeneratedTest_TestCmdArgs_sv_134_292.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)lib.extcore.PequalsString.invoke(context.localAsIsLazy(stdlib.cmdargs.Init.value__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292), context.localAsIsLazy(stdlib.cmdargs.Init.expected__ON__stdlib_cmdargs_generatedTest_TestCmdArgs_sv_134_292))); } };

	}

	public static final common.NodeFactory<PgeneratedTest_TestCmdArgs_sv_134_292> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PgeneratedTest_TestCmdArgs_sv_134_292> {

		@Override
		public PgeneratedTest_TestCmdArgs_sv_134_292 invoke(final Object[] children, final Object[] annotations) {
			return new PgeneratedTest_TestCmdArgs_sv_134_292();
		}
	};

}
