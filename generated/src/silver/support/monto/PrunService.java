
package silver.support.monto;

public final class PrunService extends common.FunctionNode {

	public static final int i_service = 0;
	public static final int i_port = 1;
	public static final int i_ioIn = 2;


	public static final Class<?> childTypes[] = { silver.support.monto.NService.class,Integer.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_runService;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_service] = new common.Lazy[silver.support.monto.NService.num_inh_attrs];

	}

	public PrunService(final Object c_service, final Object c_port, final Object c_ioIn) {
		this.child_service = c_service;
		this.child_port = c_port;
		this.child_ioIn = c_ioIn;

	}

	private Object child_service;
	public final silver.support.monto.NService getChild_service() {
		return (silver.support.monto.NService) (child_service = common.Util.demand(child_service));
	}

	private Object child_port;
	public final Integer getChild_port() {
		return (Integer) (child_port = common.Util.demand(child_port));
	}

	private Object child_ioIn;
	public final Object getChild_ioIn() {
		return (Object) (child_ioIn = common.Util.demand(child_ioIn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_service: return getChild_service();
			case i_port: return getChild_port();
			case i_ioIn: return getChild_ioIn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_service: return child_service;
			case i_port: return child_port;
			case i_ioIn: return child_ioIn;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:support:monto:runService";
	}

	public static Object invoke(final Object c_service, final Object c_port, final Object c_ioIn) {
		try {
		return (Object)common.Util.io(((Object)common.Util.demand(c_ioIn)), monto.Server.run(((silver.support.monto.NService)common.Util.demand(c_service)), ((Integer)common.Util.demand(c_port))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:support:monto:runService", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrunService.invoke(children[0], children[1], children[2]);
		}
	};
}