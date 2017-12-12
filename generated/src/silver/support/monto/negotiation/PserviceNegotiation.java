
package silver.support.monto.negotiation;

// top::ServiceNegotiation ::= monto::ProtocolVersion service::SoftwareVersion extensions::[String] products::[ProductDescriptor] 
public final class PserviceNegotiation extends silver.support.monto.negotiation.NServiceNegotiation {

	public static final int i_monto = 0;
	public static final int i_service = 1;
	public static final int i_extensions = 2;
	public static final int i_products = 3;


	public static final Class<?> childTypes[] = {silver.support.monto.negotiation.NProtocolVersion.class,silver.support.monto.negotiation.NSoftwareVersion.class,common.DecoratedNode.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_negotiation_serviceNegotiation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.negotiation.NServiceNegotiation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.negotiation.NServiceNegotiation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_monto] = new common.Lazy[silver.support.monto.negotiation.NProtocolVersion.num_inh_attrs];
	childInheritedAttributes[i_service] = new common.Lazy[silver.support.monto.negotiation.NSoftwareVersion.num_inh_attrs];

	}

	public PserviceNegotiation(final Object c_monto, final Object c_service, final Object c_extensions, final Object c_products) {
		super();
		this.child_monto = c_monto;
		this.child_service = c_service;
		this.child_extensions = c_extensions;
		this.child_products = c_products;

	}

	private Object child_monto;
	public final silver.support.monto.negotiation.NProtocolVersion getChild_monto() {
		return (silver.support.monto.negotiation.NProtocolVersion) (child_monto = common.Util.demand(child_monto));
	}

	private Object child_service;
	public final silver.support.monto.negotiation.NSoftwareVersion getChild_service() {
		return (silver.support.monto.negotiation.NSoftwareVersion) (child_service = common.Util.demand(child_service));
	}

	private Object child_extensions;
	public final common.ConsCell getChild_extensions() {
		return (common.ConsCell) (child_extensions = common.Util.demand(child_extensions));
	}

	private Object child_products;
	public final common.ConsCell getChild_products() {
		return (common.ConsCell) (child_products = common.Util.demand(child_products));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_monto: return getChild_monto();
			case i_service: return getChild_service();
			case i_extensions: return getChild_extensions();
			case i_products: return getChild_products();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_monto: return child_monto;
			case i_service: return child_service;
			case i_extensions: return child_extensions;
			case i_products: return child_products;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:negotiation:serviceNegotiation erroneously claimed to forward");
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
		return "silver:support:monto:negotiation:serviceNegotiation";
	}

	static void initProductionAttributeDefinitions() {
		// top.montoVersion = monto
		silver.support.monto.negotiation.PserviceNegotiation.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceNegotiation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.support.monto.negotiation.PserviceNegotiation.i_monto).undecorate(); } };
		// top.json = jsonObject([ pair("monto", top.montoVersion.json), pair("service", service.json), pair("extensions", jsonArray(map(jsonString, extensions))), pair("products", jsonArray(map((.json), products))) ])
		silver.support.monto.negotiation.PserviceNegotiation.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ServiceNegotiation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("monto")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)((silver.support.monto.negotiation.NProtocolVersion)context.synthesized(silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceNegotiation)).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ProtocolVersion)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("service")), context.childDecoratedSynthesizedLazy(silver.support.monto.negotiation.PserviceNegotiation.i_service, silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_SoftwareVersion))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("extensions")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.json.PjsonString.factory, context.childAsIsLazy(silver.support.monto.negotiation.PserviceNegotiation.i_extensions))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("products")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.support.monto.products.Init.silver_json_json__ON__silver_support_monto_products_ProductDescriptor, context), context.childAsIsLazy(silver.support.monto.negotiation.PserviceNegotiation.i_products))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PserviceNegotiation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PserviceNegotiation> {

		@Override
		public PserviceNegotiation invoke(final Object[] children, final Object[] annotations) {
			return new PserviceNegotiation(children[0], children[1], children[2], children[3]);
		}
	};

}
