
package silver.driver;

public final class PcompileGrammars extends common.FunctionNode {

	public static final int i_svParser = 0;
	public static final int i_sviParser = 1;
	public static final int i_benv = 2;
	public static final int i_need = 3;
	public static final int i_clean = 4;
	public static final int i_ioin = 5;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.NodeFactory.class,silver.driver.util.NBuildEnv.class,common.DecoratedNode.class,Boolean.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_compileGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_benv] = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];

	}

	public PcompileGrammars(final Object c_svParser, final Object c_sviParser, final Object c_benv, final Object c_need, final Object c_clean, final Object c_ioin) {
		this.child_svParser = c_svParser;
		this.child_sviParser = c_sviParser;
		this.child_benv = c_benv;
		this.child_need = c_need;
		this.child_clean = c_clean;
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

	private Object child_benv;
	public final silver.driver.util.NBuildEnv getChild_benv() {
		return (silver.driver.util.NBuildEnv) (child_benv = common.Util.demand(child_benv));
	}

	private Object child_need;
	public final common.ConsCell getChild_need() {
		return (common.ConsCell) (child_need = common.Util.demand(child_need));
	}

	private Object child_clean;
	public final Boolean getChild_clean() {
		return (Boolean) (child_clean = common.Util.demand(child_clean));
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
			case i_benv: return getChild_benv();
			case i_need: return getChild_need();
			case i_clean: return getChild_clean();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_svParser: return child_svParser;
			case i_sviParser: return child_sviParser;
			case i_benv: return child_benv;
			case i_need: return child_need;
			case i_clean: return child_clean;
			case i_ioin: return child_ioin;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return "silver:driver:compileGrammars";
	}

	public static core.NIOVal invoke(final Object c_svParser, final Object c_sviParser, final Object c_benv, final Object c_need, final Object c_clean, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcompileGrammars(c_svParser, c_sviParser, c_benv, c_need, c_clean, c_ioin).decorate();
		//if null(need) then ioval(ioin, []) else ioval(recurse.io, (unsafeTrace(now.iovalue, now.io) :: recurse.iovalue))
		return (core.NIOVal)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PcompileGrammars.i_need))) ? ((core.NIOVal)new core.Pioval(context.childAsIsLazy(silver.driver.PcompileGrammars.i_ioin), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileGrammars).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)core.PunsafeTrace.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)context.localDecorated(silver.driver.Init.now__ON__silver_driver_compileGrammars).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.now__ON__silver_driver_compileGrammars).synthesized(core.Init.core_io__ON__core_IOVal)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.Init.recurse__ON__silver_driver_compileGrammars).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:compileGrammars", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompileGrammars.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}