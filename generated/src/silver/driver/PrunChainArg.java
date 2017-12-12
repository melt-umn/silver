
package silver.driver;

public final class PrunChainArg extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_r = 1;
	public static final int i_x = 2;
	public static final int i_ioin = 3;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.NodeFactory.class,Object.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_runChainArg;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrunChainArg(final Object c_l, final Object c_r, final Object c_x, final Object c_ioin) {
		this.child_l = c_l;
		this.child_r = c_r;
		this.child_x = c_x;
		this.child_ioin = c_ioin;

	}

	private Object child_l;
	public final common.NodeFactory<core.NIOVal> getChild_l() {
		return (common.NodeFactory<core.NIOVal>) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final common.NodeFactory<core.NIOVal> getChild_r() {
		return (common.NodeFactory<core.NIOVal>) (child_r = common.Util.demand(child_r));
	}

	private Object child_x;
	public final Object getChild_x() {
		return (Object) (child_x = common.Util.demand(child_x));
	}

	private Object child_ioin;
	public final Object getChild_ioin() {
		return (Object) (child_ioin = common.Util.demand(child_ioin));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();
			case i_x: return getChild_x();
			case i_ioin: return getChild_ioin();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;
			case i_x: return child_x;
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
		return "silver:driver:runChainArg";
	}

	public static core.NIOVal invoke(final Object c_l, final Object c_r, final Object c_x, final Object c_ioin) {
		try {
		final common.DecoratedNode context = new PrunChainArg(c_l, c_r, c_x, c_ioin).decorate();
		//rcall
		return (core.NIOVal)(context.localDecorated(silver.driver.Init.rcall__ON__silver_driver_runChainArg).undecorate());

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:runChainArg", t);
		}
	}

	public static final common.NodeFactory<core.NIOVal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NIOVal> {
		@Override
		public core.NIOVal invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrunChainArg.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}