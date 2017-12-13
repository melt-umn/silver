
package silver.support.monto;

// top::ServiceProvider ::= descriptor::ProductDescriptor func::(Pair<Either<[ServiceError] Product> [ServiceNotice]> ::= String [Product]) 
public final class PserviceProvider extends silver.support.monto.NServiceProvider {

	public static final int i_descriptor = 0;
	public static final int i_func = 1;


	public static final Class<?> childTypes[] = {silver.support.monto.products.NProductDescriptor.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_serviceProvider;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.NServiceProvider.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.NServiceProvider.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_descriptor] = new common.Lazy[silver.support.monto.products.NProductDescriptor.num_inh_attrs];

	}

	public PserviceProvider(final Object c_descriptor, final Object c_func) {
		super();
		this.child_descriptor = c_descriptor;
		this.child_func = c_func;

	}

	private Object child_descriptor;
	public final silver.support.monto.products.NProductDescriptor getChild_descriptor() {
		return (silver.support.monto.products.NProductDescriptor) (child_descriptor = common.Util.demand(child_descriptor));
	}

	private Object child_func;
	public final common.NodeFactory<core.NPair> getChild_func() {
		return (common.NodeFactory<core.NPair>) (child_func = common.Util.demand(child_func));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_descriptor: return getChild_descriptor();
			case i_func: return getChild_func();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_descriptor: return child_descriptor;
			case i_func: return child_func;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:serviceProvider erroneously claimed to forward");
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
		return "silver:support:monto:serviceProvider";
	}

	static void initProductionAttributeDefinitions() {
		// top.descriptor = descriptor
		silver.support.monto.PserviceProvider.synthesizedAttributes[silver.support.monto.Init.silver_support_monto_descriptor__ON__silver_support_monto_ServiceProvider] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.support.monto.PserviceProvider.i_descriptor).undecorate(); } };
		// top.processService = func
		silver.support.monto.PserviceProvider.synthesizedAttributes[silver.support.monto.Init.silver_support_monto_processService__ON__silver_support_monto_ServiceProvider] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.NodeFactory<core.NPair>)context.childAsIs(silver.support.monto.PserviceProvider.i_func)); } };

	}

	public static final common.NodeFactory<PserviceProvider> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PserviceProvider> {

		@Override
		public PserviceProvider invoke(final Object[] children, final Object[] annotations) {
			return new PserviceProvider(children[0], children[1]);
		}
	};

}
