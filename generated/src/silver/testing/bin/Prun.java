
package silver.testing.bin;

// r::Run ::= f::OptionalFail 'run' c::Command_t 
public final class Prun extends silver.testing.bin.NRun {

	public static final int i_f = 0;
	public static final int i__G_1 = 1;
	public static final int i_c = 2;


	public static final Class<?> childTypes[] = {silver.testing.bin.NOptionalFail.class,silver.testing.bin.TRun_t.class,silver.testing.bin.TCommand_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_run;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.bin.NRun.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.bin.NRun.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_f] = new common.Lazy[silver.testing.bin.NOptionalFail.num_inh_attrs];

	}

	public Prun(final Object c_f, final Object c__G_1, final Object c_c) {
		super();
		this.child_f = c_f;
		this.child__G_1 = c__G_1;
		this.child_c = c_c;

	}

	private Object child_f;
	public final silver.testing.bin.NOptionalFail getChild_f() {
		return (silver.testing.bin.NOptionalFail) (child_f = common.Util.demand(child_f));
	}

	private Object child__G_1;
	public final silver.testing.bin.TRun_t getChild__G_1() {
		return (silver.testing.bin.TRun_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_c;
	public final silver.testing.bin.TCommand_t getChild_c() {
		return (silver.testing.bin.TCommand_t) (child_c = common.Util.demand(child_c));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i__G_1: return getChild__G_1();
			case i_c: return getChild_c();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i__G_1: return child__G_1;
			case i_c: return child_c;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:testing:bin:run erroneously claimed to forward");
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
		return "silver:testing:bin:run";
	}

	static void initProductionAttributeDefinitions() {
		// msgBefore = print("............................................................\n" ++ "Test \n  " ++ r.testFileName ++ " in directory \n  " ++ prettyDirName(r.testFileDir,) ++ "\n", r.ioInput.io,)
		silver.testing.bin.Prun.localAttributes[silver.testing.bin.Init.msgBefore__ON__silver_testing_bin_run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("............................................................\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("Test \n  ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" in directory \n  ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.testing.bin.PprettyDirName.invoke(context.contextInheritedLazy(silver.testing.bin.Init.silver_testing_bin_testFileDir__ON__silver_testing_bin_Run))), (common.StringCatter)(new common.StringCatter("\n"))))))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NIOVal)context.inherited(silver.testing.bin.Init.silver_testing_bin_ioInput__ON__silver_testing_bin_Run)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// cmd = substring(1, length(c.lexeme) - 1, c.lexeme,)
		silver.testing.bin.Prun.localAttributes[silver.testing.bin.Init.cmd__ON__silver_testing_bin_run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.testing.bin.TCommand_t)context.childAsIs(silver.testing.bin.Prun.i_c)).lexeme)).length()) - Integer.valueOf((int)1)); } }, ((common.StringCatter)((silver.testing.bin.TCommand_t)context.childAsIs(silver.testing.bin.Prun.i_c)).lexeme))); } };
		// cmdResult = system("cd " ++ r.testFileDir ++ ";" ++ "rm -f " ++ r.testFileName ++ ".output ; " ++ cmd ++ " >& " ++ r.testFileName ++ ".output", msgBefore,)
		silver.testing.bin.Prun.localAttributes[silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.Psystem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("cd ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileDir__ON__silver_testing_bin_Run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("rm -f ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".output ; ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.testing.bin.Init.cmd__ON__silver_testing_bin_run)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" >& ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.testing.bin.Init.silver_testing_bin_testFileName__ON__silver_testing_bin_Run)), (common.StringCatter)(new common.StringCatter(".output"))))))))))); } }, context.localAsIsLazy(silver.testing.bin.Init.msgBefore__ON__silver_testing_bin_run))); } };
		// r.ioResult = if (cmdResult.iovalue == 0 && ! f.fail) || (cmdResult.iovalue != 0 && f.fail) then ioval(print("passed (rc = 0).\n", cmdResult.io,), 0,) else ioval(print("failed (rc = " ++ toString(cmdResult.iovalue) ++ ").\n", cmdResult.io,), 1,)
		silver.testing.bin.Prun.synthesizedAttributes[silver.testing.bin.Init.silver_testing_bin_ioResult__ON__silver_testing_bin_Run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((((Integer)context.localDecorated(silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(Integer.valueOf((int)0)) && (!((Boolean)context.childDecorated(silver.testing.bin.Prun.i_f).synthesized(silver.testing.bin.Init.silver_testing_bin_fail__ON__silver_testing_bin_OptionalFail)))) || (!((Integer)context.localDecorated(silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(Integer.valueOf((int)0)) && ((Boolean)context.childDecorated(silver.testing.bin.Prun.i_f).synthesized(silver.testing.bin.Init.silver_testing_bin_fail__ON__silver_testing_bin_OptionalFail)))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke((new common.StringCatter("passed (rc = 0).\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } }, Integer.valueOf((int)0))) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pprint.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("failed (rc = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.localDecorated(silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run).synthesized(core.Init.core_iovalue__ON__core_IOVal)))), (common.StringCatter)(new common.StringCatter(").\n")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.cmdResult__ON__silver_testing_bin_run).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } }, Integer.valueOf((int)1)))); } };

	}

	public static final common.NodeFactory<Prun> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Prun> {

		@Override
		public Prun invoke(final Object[] children, final Object[] annotations) {
			return new Prun(children[0], children[1], children[2]);
		}
	};

}
