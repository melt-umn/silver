
package silver.support.monto.negotiation;

public final class PnegotiationsCompatible extends common.FunctionNode {

	public static final int i_us = 0;
	public static final int i_them = 1;


	public static final Class<?> childTypes[] = { silver.support.monto.negotiation.NServiceNegotiation.class,silver.support.monto.negotiation.NServiceBrokerNegotiation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_negotiation_negotiationsCompatible;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_us] = new common.Lazy[silver.support.monto.negotiation.NServiceNegotiation.num_inh_attrs];
	childInheritedAttributes[i_them] = new common.Lazy[silver.support.monto.negotiation.NServiceBrokerNegotiation.num_inh_attrs];

	}

	public PnegotiationsCompatible(final Object c_us, final Object c_them) {
		this.child_us = c_us;
		this.child_them = c_them;

	}

	private Object child_us;
	public final silver.support.monto.negotiation.NServiceNegotiation getChild_us() {
		return (silver.support.monto.negotiation.NServiceNegotiation) (child_us = common.Util.demand(child_us));
	}

	private Object child_them;
	public final silver.support.monto.negotiation.NServiceBrokerNegotiation getChild_them() {
		return (silver.support.monto.negotiation.NServiceBrokerNegotiation) (child_them = common.Util.demand(child_them));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_us: return getChild_us();
			case i_them: return getChild_them();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_us: return child_us;
			case i_them: return child_them;

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
		return "silver:support:monto:negotiation:negotiationsCompatible";
	}

	public static Boolean invoke(final Object c_us, final Object c_them) {
		try {
		final common.DecoratedNode context = new PnegotiationsCompatible(c_us, c_them).decorate();
		//semverCompatible(us.montoVersion, them.montoVersion)
		return (Boolean)(((Boolean)silver.support.monto.negotiation.PsemverCompatible.invoke(context.childDecoratedSynthesizedLazy(silver.support.monto.negotiation.PnegotiationsCompatible.i_us, silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceNegotiation), context.childDecoratedSynthesizedLazy(silver.support.monto.negotiation.PnegotiationsCompatible.i_them, silver.support.monto.negotiation.Init.silver_support_monto_negotiation_montoVersion__ON__silver_support_monto_negotiation_ServiceBrokerNegotiation))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:support:monto:negotiation:negotiationsCompatible", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnegotiationsCompatible.invoke(children[0], children[1]);
		}
	};
}