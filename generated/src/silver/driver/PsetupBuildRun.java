
package silver.driver;

public final class PsetupBuildRun extends common.FunctionNode {

	public static final int i_svParser = 0;
	public static final int i_sviParser = 1;
	public static final int i_envin = 2;
	public static final int i_ioin = 3;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.NodeFactory.class,core.NPair.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_setupBuildRun;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_envin] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PsetupBuildRun(final Object c_svParser, final Object c_sviParser, final Object c_envin, final Object c_ioin) {
		this.child_svParser = c_svParser;
		this.child_sviParser = c_sviParser;
		this.child_envin = c_envin;
		this.child_ioin = c_ioin;

	}

	private Object child_svParser;
	public final common.NodeFactory<core.NParseResult> getChild_svParser() {
		return (common.NodeFactory<core.NParseResult>) (child_svParser = common.Util.demand(child_svParser));
	}

	private Object child_sviParser;
	public final common.NodeFactory<core.NParseResult> getChild_sviParser() {
		return (common.NodeFactory<core.NParseResult>) (child_sviParser = common.Util.demand(child_sviParser));
	}

	private Object child_envin;
	public final core.NPair getChild_envin() {
		return (core.NPair) (child_envin = common.Util.demand(child_envin));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_svParser: return getChild_svParser();
			case i_sviParser: return getChild_sviParser();
			case i_envin: return getChild_envin();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_svParser: return child_svParser;
			case i_sviParser: return child_sviParser;
			case i_envin: return child_envin;
			case i_ioin: return child_ioin;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:driver:setupBuildRun";
	}

	public static core.NIOVal invoke(final Object c_svParser, final Object c_sviParser, final Object c_envin, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PsetupBuildRun(c_svParser, c_sviParser, c_envin, c_ioin).decorate();
		//if ! null(checkbuild.iovalue) then ioval(checkbuild.io, left(runError(1, implode("\n", checkbuild.iovalue)))) else if null(buildrun.iovalue.grammarList) then ioval(buildrun.io, left(runError(1, "The specified grammar (" ++ buildGrammar ++ ") could not be found.\n"))) else ioval(buildrun.io, right(buildrun.iovalue))
		return (core.NIOVal)(((!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.Init.checkbuild__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.checkbuild__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pleft(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.NRunError)new silver.driver.PrunError(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.Init.checkbuild__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } })); } })); } })) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.localDecorated(silver.driver.Init.buildrun__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_iovalue__ON__core_IOVal)).synthesized(silver.driver.util.Init.silver_driver_util_grammarList__ON__silver_driver_util_Compilation)); } })) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.buildrun__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pleft(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.driver.NRunError)new silver.driver.PrunError(Integer.valueOf((int)1), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("The specified grammar (")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.driver.Init.buildGrammar__ON__silver_driver_setupBuildRun)), (common.StringCatter)(new common.StringCatter(") could not be found.\n")))); } })); } })); } })) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.buildrun__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NEither)new core.Pright(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.localDecorated(silver.driver.Init.buildrun__ON__silver_driver_setupBuildRun).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:setupBuildRun", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsetupBuildRun.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}