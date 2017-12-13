
package silver.support.monto.negotiation;

// top::ServiceBrokerNegotiation ::= monto::ProtocolVersion broker::SoftwareVersion extensions::[String] 
public final class PserviceBrokerNegotiation extends silver.support.monto.negotiation.NServiceBrokerNegotiation {

	public static final int i_monto = 0;
	public static final int i_broker = 1;
	public static final int i_extensions = 2;


	public static final Class<?> childTypes[] = {silver.support.monto.negotiation.NProtocolVersion.class,silver.support.monto.negotiation.NSoftwareVersion.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_negotiation_serviceBrokerNegotiation;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.negotiation.NServiceBrokerNegotiation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.negotiation.NServiceBrokerNegotiation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_monto] = new common.Lazy[silver.support.monto.negotiation.NProtocolVersion.num_inh_attrs];
	childInheritedAttributes[i_broker] = new common.Lazy[silver.support.monto.negotiation.NSoftwareVersion.num_inh_attrs];

	}

	public PserviceBrokerNegotiation(final Object c_monto, final Object c_broker, final Object c_extensions) {
		super();
		this.child_monto = c_monto;
		this.child_broker = c_broker;
		this.child_extensions = c_extensions;

	}

	private Object child_monto;
	public final silver.support.monto.negotiation.NProtocolVersion getChild_monto() {
		return (silver.support.monto.negotiation.NProtocolVersion) (child_monto = common.Util.demand(child_monto));
	}

	private Object child_broker;
	public final silver.support.monto.negotiation.NSoftwareVersion getChild_broker() {
		return (silver.support.monto.negotiation.NSoftwareVersion) (child_broker = common.Util.demand(child_broker));
	}

	private Object child_extensions;
	public final common.ConsCell getChild_extensions() {
		return (common.ConsCell) (child_extensions = common.Util.demand(child_extensions));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_monto: return getChild_monto();
			case i_broker: return getChild_broker();
			case i_extensions: return getChild_extensions();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_monto: return child_monto;
			case i_broker: return child_broker;
			case i_extensions: return child_extensions;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:negotiation:serviceBrokerNegotiation erroneously claimed to forward");
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
		return "silver:support:monto:negotiation:serviceBrokerNegotiation";
	}

	static void initProductionAttributeDefinitions() {
		// top.montoVersion = monto
		silver.support.monto.negotiation.PserviceBrokerNegotiation.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.support.monto.negotiation.PserviceBrokerNegotiation.i_monto).undecorate(); } };
		// top.json = jsonObject([ pair("monto", top.montoVersion.json), pair("broker", broker.json), pair("extensions", jsonArray(map(jsonString, extensions))) ])
		silver.support.monto.negotiation.PserviceBrokerNegotiation.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("monto")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)((silver.support.monto.negotiation.NProtocolVersion)context.synthesized(silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation)).decorate(context, (common.Lazy[])null).synthesized(silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ProtocolVersion)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("broker")), context.childDecoratedSynthesizedLazy(silver.support.monto.negotiation.PserviceBrokerNegotiation.i_broker, silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_SoftwareVersion))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("extensions")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonArray(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.json.PjsonString.factory, context.childAsIsLazy(silver.support.monto.negotiation.PserviceBrokerNegotiation.i_extensions))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PserviceBrokerNegotiation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PserviceBrokerNegotiation> {

		@Override
		public PserviceBrokerNegotiation invoke(final Object[] children, final Object[] annotations) {
			return new PserviceBrokerNegotiation(children[0], children[1], children[2]);
		}
	};

}
