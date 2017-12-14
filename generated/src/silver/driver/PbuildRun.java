
package silver.driver;

public final class PbuildRun extends common.FunctionNode {

	public static final int i_svParser = 0;
	public static final int i_sviParser = 1;
	public static final int i_a = 2;
	public static final int i_benv = 3;
	public static final int i_buildGrammar = 4;
	public static final int i_ioin = 5;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.NodeFactory.class,common.DecoratedNode.class,silver.driver.util.NBuildEnv.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_buildRun;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_benv] = new common.Lazy[silver.driver.util.NBuildEnv.num_inh_attrs];

	}

	public PbuildRun(final Object c_svParser, final Object c_sviParser, final Object c_a, final Object c_benv, final Object c_buildGrammar, final Object c_ioin) {
		this.child_svParser = c_svParser;
		this.child_sviParser = c_sviParser;
		this.child_a = c_a;
		this.child_benv = c_benv;
		this.child_buildGrammar = c_buildGrammar;
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

	private Object child_a;
	public final common.DecoratedNode getChild_a() {
		return (common.DecoratedNode) (child_a = common.Util.demand(child_a));
	}

	private Object child_benv;
	public final silver.driver.util.NBuildEnv getChild_benv() {
		return (silver.driver.util.NBuildEnv) (child_benv = common.Util.demand(child_benv));
	}

	private Object child_buildGrammar;
	public final common.StringCatter getChild_buildGrammar() {
		return (common.StringCatter) (child_buildGrammar = common.Util.demand(child_buildGrammar));
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
			case i_a: return getChild_a();
			case i_benv: return getChild_benv();
			case i_buildGrammar: return getChild_buildGrammar();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_svParser: return child_svParser;
			case i_sviParser: return child_sviParser;
			case i_a: return child_a;
			case i_benv: return child_benv;
			case i_buildGrammar: return child_buildGrammar;
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
		return "silver:driver:buildRun";
	}

	public static core.NIOVal invoke(final Object c_svParser, final Object c_sviParser, final Object c_a, final Object c_benv, final Object c_buildGrammar, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PbuildRun(c_svParser, c_sviParser, c_a, c_benv, c_buildGrammar, c_ioin).decorate();
		//ioval(reRootStream.io, unit)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.reRootStream__ON__silver_driver_buildRun).synthesized(core.Init.core_io__ON__core_IOVal)); } }, context.localDecoratedLazy(silver.driver.Init.unit__ON__silver_driver_buildRun))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:buildRun", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbuildRun.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}