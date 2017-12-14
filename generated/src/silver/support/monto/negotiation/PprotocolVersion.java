
package silver.support.monto.negotiation;

// top::ProtocolVersion ::= major::Integer minor::Integer patch::Integer 
public final class PprotocolVersion extends silver.support.monto.negotiation.NProtocolVersion {

	public static final int i_major = 0;
	public static final int i_minor = 1;
	public static final int i_patch = 2;


	public static final Class<?> childTypes[] = {Integer.class,Integer.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_negotiation_protocolVersion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.negotiation.NProtocolVersion.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.negotiation.NProtocolVersion.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PprotocolVersion(final Object c_major, final Object c_minor, final Object c_patch) {
		super();
		this.child_major = c_major;
		this.child_minor = c_minor;
		this.child_patch = c_patch;

	}

	private Object child_major;
	public final Integer getChild_major() {
		return (Integer) (child_major = common.Util.demand(child_major));
	}

	private Object child_minor;
	public final Integer getChild_minor() {
		return (Integer) (child_minor = common.Util.demand(child_minor));
	}

	private Object child_patch;
	public final Integer getChild_patch() {
		return (Integer) (child_patch = common.Util.demand(child_patch));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_major: return getChild_major();
			case i_minor: return getChild_minor();
			case i_patch: return getChild_patch();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_major: return child_major;
			case i_minor: return child_minor;
			case i_patch: return child_patch;

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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:negotiation:protocolVersion erroneously claimed to forward");
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
		return "silver:support:monto:negotiation:protocolVersion";
	}

	static void initProductionAttributeDefinitions() {
		// top.major = major
		silver.support.monto.negotiation.PprotocolVersion.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_major__ON__silver_support_monto_negotiation_ProtocolVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.support.monto.negotiation.PprotocolVersion.i_major)); } };
		// top.minor = minor
		silver.support.monto.negotiation.PprotocolVersion.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_minor__ON__silver_support_monto_negotiation_ProtocolVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.support.monto.negotiation.PprotocolVersion.i_minor)); } };
		// top.patch = patch
		silver.support.monto.negotiation.PprotocolVersion.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_support_monto_negotiation_patch__ON__silver_support_monto_negotiation_ProtocolVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.support.monto.negotiation.PprotocolVersion.i_patch)); } };
		// top.json = jsonObject([ pair("major", jsonInteger(top.major)), pair("minor", jsonInteger(top.minor)), pair("patch", jsonInteger(top.patch)) ])
		silver.support.monto.negotiation.PprotocolVersion.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_ProtocolVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("major")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.contextSynthesizedLazy(silver.support.monto.negotiation.Init.silver_support_monto_negotiation_major__ON__silver_support_monto_negotiation_ProtocolVersion))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("minor")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.contextSynthesizedLazy(silver.support.monto.negotiation.Init.silver_support_monto_negotiation_minor__ON__silver_support_monto_negotiation_ProtocolVersion))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("patch")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)silver.json.PjsonInteger.invoke(context.contextSynthesizedLazy(silver.support.monto.negotiation.Init.silver_support_monto_negotiation_patch__ON__silver_support_monto_negotiation_ProtocolVersion))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };

	}

	public static final common.NodeFactory<PprotocolVersion> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PprotocolVersion> {

		@Override
		public PprotocolVersion invoke(final Object[] children, final Object[] annotations) {
			return new PprotocolVersion(children[0], children[1], children[2]);
		}
	};

}
