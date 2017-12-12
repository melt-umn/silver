
package silver.support.monto;

// top::BrokerRequest ::= request::ProductIdentifier products::[Product] 
public final class PbrokerRequest extends silver.support.monto.NBrokerRequest {

	public static final int i_request = 0;
	public static final int i_products = 1;


	public static final Class<?> childTypes[] = {silver.support.monto.products.NProductIdentifier.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_brokerRequest;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.NBrokerRequest.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.NBrokerRequest.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_request] = new common.Lazy[silver.support.monto.products.NProductIdentifier.num_inh_attrs];

	}

	public PbrokerRequest(final Object c_request, final Object c_products) {
		super();
		this.child_request = c_request;
		this.child_products = c_products;

	}

	private Object child_request;
	public final silver.support.monto.products.NProductIdentifier getChild_request() {
		return (silver.support.monto.products.NProductIdentifier) (child_request = common.Util.demand(child_request));
	}

	private Object child_products;
	public final common.ConsCell getChild_products() {
		return (common.ConsCell) (child_products = common.Util.demand(child_products));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_request: return getChild_request();
			case i_products: return getChild_products();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_request: return child_request;
			case i_products: return child_products;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:brokerRequest erroneously claimed to forward");
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
		return "silver:support:monto:brokerRequest";
	}

	static void initProductionAttributeDefinitions() {
		// top.json = jsonObject([ pair("products", jsonArray(map((.json), products))), pair("request", request.json) ])
		silver.support.monto.PbrokerRequest.synthesizedAttributes[silver.support.monto.Init.silver_json_json__ON__silver_support_monto_BrokerRequest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("products")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_Product, context), context.childAsIsLazy(silver.support.monto.PbrokerRequest.i_products))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("request")), context.childDecoratedSynthesizedLazy(silver.support.monto.PbrokerRequest.i_request, silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductIdentifier))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		// top.requestIdentifier = request
		silver.support.monto.PbrokerRequest.synthesizedAttributes[silver.support.monto.Init.silver_support_monto_requestIdentifier__ON__silver_support_monto_BrokerRequest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.support.monto.PbrokerRequest.i_request).undecorate(); } };
		// top.requestProducts = products
		silver.support.monto.PbrokerRequest.synthesizedAttributes[silver.support.monto.Init.silver_support_monto_requestProducts__ON__silver_support_monto_BrokerRequest] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.support.monto.PbrokerRequest.i_products)); } };

	}

	public static final common.NodeFactory<PbrokerRequest> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbrokerRequest> {

		@Override
		public PbrokerRequest invoke(final Object[] children, final Object[] annotations) {
			return new PbrokerRequest(children[0], children[1]);
		}
	};

}
