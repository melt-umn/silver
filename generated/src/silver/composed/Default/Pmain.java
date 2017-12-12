
package silver.composed.Default;

public final class Pmain extends common.FunctionNode {

	public static final int i_args = 0;
	public static final int i_ioin = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_composed_Default_main;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pmain(final Object c_args, final Object c_ioin) {
		this.child_args = c_args;
		this.child_ioin = c_ioin;

	}

	private Object child_args;
	public final common.ConsCell getChild_args() {
		return (common.ConsCell) (child_args = common.Util.demand(child_args));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_args: return getChild_args();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_args: return child_args;
			case i_ioin: return child_ioin;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:composed:Default:main";
	}

	public static core.NIOVal invoke(final Object c_args, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new Pmain(c_args, c_ioin).decorate();
		//cmdLineRun(args, svParse, sviParse, ioin)
		return (core.NIOVal)(((core.NIOVal)silver.driver.PcmdLineRun.invoke(context.childAsIsLazy(silver.composed.Default.Pmain.i_args), silver.composed.Default.PsvParse.factory, silver.composed.Default.PsviParse.factory, context.childAsIsLazy(silver.composed.Default.Pmain.i_ioin))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:composed:Default:main", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pmain.invoke(children[0], children[1]);
		}
	};
}