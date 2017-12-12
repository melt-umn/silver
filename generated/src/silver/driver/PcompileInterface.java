
package silver.driver;

public final class PcompileInterface extends common.FunctionNode {

	public static final int i_sviParser = 0;
	public static final int i_grammarName = 1;
	public static final int i_genPath = 2;
	public static final int i_ioin = 3;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.StringCatter.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_compileInterface;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcompileInterface(final Object c_sviParser, final Object c_grammarName, final Object c_genPath, final Object c_ioin) {
		this.child_sviParser = c_sviParser;
		this.child_grammarName = c_grammarName;
		this.child_genPath = c_genPath;
		this.child_ioin = c_ioin;

	}

	private Object child_sviParser;
	public final common.NodeFactory<core.NParseResult> getChild_sviParser() {
		return (common.NodeFactory<core.NParseResult>) (child_sviParser = common.Util.demand(child_sviParser));
	}

	private Object child_grammarName;
	public final common.StringCatter getChild_grammarName() {
		return (common.StringCatter) (child_grammarName = common.Util.demand(child_grammarName));
	}

	private Object child_genPath;
	public final common.StringCatter getChild_genPath() {
		return (common.StringCatter) (child_genPath = common.Util.demand(child_genPath));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sviParser: return getChild_sviParser();
			case i_grammarName: return getChild_grammarName();
			case i_genPath: return getChild_genPath();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sviParser: return child_sviParser;
			case i_grammarName: return child_grammarName;
			case i_genPath: return child_genPath;
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
		return "silver:driver:compileInterface";
	}

	public static core.NIOVal invoke(final Object c_sviParser, final Object c_grammarName, final Object c_genPath, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PcompileInterface(c_sviParser, c_grammarName, c_genPath, c_ioin).decorate();
		//ioval(text.io, ir)
		return (core.NIOVal)(((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.localDecorated(silver.driver.Init.text__ON__silver_driver_compileInterface).synthesized(core.Init.core_io__ON__core_IOVal)); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.driver.Init.ir__ON__silver_driver_compileInterface)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:compileInterface", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompileInterface.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}