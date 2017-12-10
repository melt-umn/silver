
package silver.testing.bin;

// t::Test ::= fn::String parseF::(ParseResult<a> ::= String String) 
public final class PparseOnlyTestAfterCPP extends silver.testing.NTest {

	public static final int i_fn = 0;
	public static final int i_parseF = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_parseOnlyTestAfterCPP;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PparseOnlyTestAfterCPP(final Object c_fn, final Object c_parseF) {
		super();
		this.child_fn = c_fn;
		this.child_parseF = c_parseF;

	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_parseF;
	public final common.NodeFactory<core.NParseResult> getChild_parseF() {
		return (common.NodeFactory<core.NParseResult>) (child_parseF = common.Util.demand(child_parseF));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fn: return getChild_fn();
			case i_parseF: return getChild_parseF();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fn: return child_fn;
			case i_parseF: return child_parseF;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:testing:bin:parseOnlyTestAfterCPP erroneously claimed to forward");
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
		return "silver:testing:bin:parseOnlyTestAfterCPP";
	}

	static void initProductionAttributeDefinitions() {
		// exists = isFile(fn, t.ioIn,)
		silver.testing.bin.PparseOnlyTestAfterCPP.localAttributes[silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PisFile.invoke(context.childAsIsLazy(silver.testing.bin.PparseOnlyTestAfterCPP.i_fn), context.contextInheritedLazy(silver.testing.Init.silver_testing_ioIn__ON__silver_testing_Test))); } };
		// cppCommand = "cpp " ++ fn ++ " > " ++ fn ++ ".cpp"
		silver.testing.bin.PparseOnlyTestAfterCPP.localAttributes[silver.testing.bin.Init.cppCommand__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("cpp ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PparseOnlyTestAfterCPP.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" > ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PparseOnlyTestAfterCPP.i_fn)), (common.StringCatter)(new common.StringCatter(".cpp")))))); } };
		// mkCPPfile = system(cppCommand, exists.io,)
		silver.testing.bin.PparseOnlyTestAfterCPP.localAttributes[silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.Psystem.invoke(context.localAsIsLazy(silver.testing.bin.Init.cppCommand__ON__silver_testing_bin_parseOnlyTestAfterCPP), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// text = readFile(fn ++ ".cpp", mkCPPfile.io,)
		silver.testing.bin.PparseOnlyTestAfterCPP.localAttributes[silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)core.PreadFile.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PparseOnlyTestAfterCPP.i_fn)), (common.StringCatter)(new common.StringCatter(".cpp"))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } };
		// pr = parseF(text.iovalue, fn,)
		silver.testing.bin.PparseOnlyTestAfterCPP.localAttributes[silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTestAfterCPP] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NParseResult)((common.NodeFactory<core.NParseResult>)context.childAsIs(silver.testing.bin.PparseOnlyTestAfterCPP.i_parseF)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }, context.childAsIsLazy(silver.testing.bin.PparseOnlyTestAfterCPP.i_fn)}, null)); } };
		// t.pass = exists.iovalue && mkCPPfile.iovalue == 0 && pr.parseSuccess
		silver.testing.bin.PparseOnlyTestAfterCPP.synthesizedAttributes[silver.testing.Init.silver_testing_pass__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)context.localDecorated(silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal)) && ((Integer)context.localDecorated(silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(Integer.valueOf((int)0))) && ((Boolean)context.localDecorated(silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult))); } };
		// t.msg = if ! exists.iovalue then "File \"" ++ fn ++ "\" not found.\n" else if ! mkCPPfile.iovalue == 0 then "The cpp process failed with error code " ++ toString(mkCPPfile.iovalue) ++ "\n" ++ "The cpp command was:\n" ++ cppCommand ++ "\n" else if ! pr.parseSuccess then "Parser error: " ++ pr.parseErrors ++ "\n" else ""
		silver.testing.bin.PparseOnlyTestAfterCPP.synthesizedAttributes[silver.testing.Init.silver_testing_msg__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localDecorated(silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("File \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.testing.bin.PparseOnlyTestAfterCPP.i_fn)), (common.StringCatter)(new common.StringCatter("\" not found.\n")))) : ((!((Integer)context.localDecorated(silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal)).equals(Integer.valueOf((int)0))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("The cpp process failed with error code ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.localDecorated(silver.testing.bin.Init.mkCPPfile__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("The cpp command was:\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.testing.bin.Init.cppCommand__ON__silver_testing_bin_parseOnlyTestAfterCPP)), (common.StringCatter)(new common.StringCatter("\n"))))))) : ((!((Boolean)context.localDecorated(silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_parseSuccess__ON__core_ParseResult))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("Parser error: ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.testing.bin.Init.pr__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_parseErrors__ON__core_ParseResult)), (common.StringCatter)(new common.StringCatter("\n")))) : (new common.StringCatter(""))))); } };
		// t.ioOut = if ! exists.iovalue then exists.io else text.io
		silver.testing.bin.PparseOnlyTestAfterCPP.synthesizedAttributes[silver.testing.Init.silver_testing_ioOut__ON__silver_testing_Test] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.localDecorated(silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_iovalue__ON__core_IOVal))) ? ((Object)context.localDecorated(silver.testing.bin.Init.exists__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_io__ON__core_IOVal)) : ((Object)context.localDecorated(silver.testing.bin.Init.text__ON__silver_testing_bin_parseOnlyTestAfterCPP).synthesized(core.Init.core_io__ON__core_IOVal))); } };

	}

	public static final common.NodeFactory<PparseOnlyTestAfterCPP> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparseOnlyTestAfterCPP> {

		@Override
		public PparseOnlyTestAfterCPP invoke(final Object[] children, final Object[] annotations) {
			return new PparseOnlyTestAfterCPP(children[0], children[1]);
		}
	};

}
