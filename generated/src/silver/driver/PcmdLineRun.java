
package silver.driver;

public final class PcmdLineRun extends common.FunctionNode {

	public static final int i_args = 0;
	public static final int i_svParser = 1;
	public static final int i_sviParser = 2;
	public static final int i_ioin = 3;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.NodeFactory.class,common.NodeFactory.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_cmdLineRun;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcmdLineRun(final Object c_args, final Object c_svParser, final Object c_sviParser, final Object c_ioin) {
		this.child_args = c_args;
		this.child_svParser = c_svParser;
		this.child_sviParser = c_sviParser;
		this.child_ioin = c_ioin;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_svParser;
	public final common.NodeFactory<core.NParseResult> getChild_svParser() {
		return (common.NodeFactory<core.NParseResult>) (child_svParser = common.Util.demand(child_svParser));
	}

	private Object child_sviParser;
	public final common.NodeFactory<core.NParseResult> getChild_sviParser() {
		return (common.NodeFactory<core.NParseResult>) (child_sviParser = common.Util.demand(child_sviParser));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();
			case i_svParser: return getChild_svParser();
			case i_sviParser: return getChild_sviParser();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;
			case i_svParser: return child_svParser;
			case i_sviParser: return child_sviParser;
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
		return "silver:driver:cmdLineRun";
	}

	public static core.NIOVal invoke(final Object c_args, final Object c_svParser, final Object c_sviParser, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcmdLineRun(c_args, c_svParser, c_sviParser, c_ioin).decorate();
		//performActions(unit)
		return (core.NIOVal)(((core.NIOVal)silver.driver.PperformActions.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.driver.Init.unit__ON__silver_driver_cmdLineRun)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:cmdLineRun", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcmdLineRun.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}