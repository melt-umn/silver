
package silver.driver;

public final class PcompileGrammar extends common.FunctionNode {

	public static final int i_svParser = 0;
	public static final int i_sviParser = 1;
	public static final int i_benv = 2;
	public static final int i_grammarName = 3;
	public static final int i_clean = 4;
	public static final int i_ioin = 5;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.NodeFactory.class,silver.driver.util.NBuildEnv.class,common.StringCatter.class,Boolean.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_compileGrammar;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_benv] = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];

	}

	public PcompileGrammar(final Object c_svParser, final Object c_sviParser, final Object c_benv, final Object c_grammarName, final Object c_clean, final Object c_ioin) {
		this.child_svParser = c_svParser;
		this.child_sviParser = c_sviParser;
		this.child_benv = c_benv;
		this.child_grammarName = c_grammarName;
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

	private Object child_grammarName;
	public final common.StringCatter getChild_grammarName() {
		return (common.StringCatter) (child_grammarName = common.Util.demand(child_grammarName));
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
			case i_grammarName: return getChild_grammarName();
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
			case i_grammarName: return child_grammarName;
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
		return "silver:driver:compileGrammar";
	}

	public static core.NIOVal invoke(final Object c_svParser, final Object c_sviParser, final Object c_benv, final Object c_grammarName, final Object c_clean, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcompileGrammar(c_svParser, c_sviParser, c_benv, c_grammarName, c_clean, c_ioin).decorate();
		//if ! grammarLocation.iovalue.isJust || null(files.iovalue) then ioval(grammarLocation.io, nothing()) else ioval(join, just(rs))
		return (core.NIOVal)((((!((Boolean)((core.NMaybe)context.localDecorated(silver.driver.Init.grammarLocation__ON__silver_driver_compileGrammar).synthesized(core.Init.core_iovalue__ON__core_IOVal)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe))) || ((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.driver.Init.files__ON__silver_driver_compileGrammar).synthesized(core.Init.core_iovalue__ON__core_IOVal)); } }))) ? ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.grammarLocation__ON__silver_driver_compileGrammar).synthesized(core.Init.core_io__ON__core_IOVal)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } })) : ((core.NIOVal)new core.Pioval(context.localAsIsLazy(silver.driver.Init.join__ON__silver_driver_compileGrammar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.driver.Init.rs__ON__silver_driver_compileGrammar)))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:compileGrammar", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompileGrammar.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}