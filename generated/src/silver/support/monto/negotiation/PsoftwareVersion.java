
package silver.support.monto.negotiation;

// top::SoftwareVersion ::= id::String name::Maybe<String> vendor::Maybe<String> major::Maybe<Integer> minor::Maybe<Integer> patch::Maybe<Integer> 
public final class PsoftwareVersion extends silver.support.monto.negotiation.NSoftwareVersion {

	public static final int i_id = 0;
	public static final int i_name = 1;
	public static final int i_vendor = 2;
	public static final int i_major = 3;
	public static final int i_minor = 4;
	public static final int i_patch = 5;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NMaybe.class,core.NMaybe.class,core.NMaybe.class,core.NMaybe.class,core.NMaybe.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_support_monto_negotiation_softwareVersion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.support.monto.negotiation.NSoftwareVersion.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.support.monto.negotiation.NSoftwareVersion.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_name] = new common.Lazy[core.NMaybe.num_inh_attrs];
	childInheritedAttributes[i_vendor] = new common.Lazy[core.NMaybe.num_inh_attrs];
	childInheritedAttributes[i_major] = new common.Lazy[core.NMaybe.num_inh_attrs];
	childInheritedAttributes[i_minor] = new common.Lazy[core.NMaybe.num_inh_attrs];
	childInheritedAttributes[i_patch] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PsoftwareVersion(final Object c_id, final Object c_name, final Object c_vendor, final Object c_major, final Object c_minor, final Object c_patch) {
		super();
		this.child_id = c_id;
		this.child_name = c_name;
		this.child_vendor = c_vendor;
		this.child_major = c_major;
		this.child_minor = c_minor;
		this.child_patch = c_patch;

	}

	private Object child_id;
	public final common.StringCatter getChild_id() {
		return (common.StringCatter) (child_id = common.Util.demand(child_id));
	}

	private Object child_name;
	public final core.NMaybe getChild_name() {
		return (core.NMaybe) (child_name = common.Util.demand(child_name));
	}

	private Object child_vendor;
	public final core.NMaybe getChild_vendor() {
		return (core.NMaybe) (child_vendor = common.Util.demand(child_vendor));
	}

	private Object child_major;
	public final core.NMaybe getChild_major() {
		return (core.NMaybe) (child_major = common.Util.demand(child_major));
	}

	private Object child_minor;
	public final core.NMaybe getChild_minor() {
		return (core.NMaybe) (child_minor = common.Util.demand(child_minor));
	}

	private Object child_patch;
	public final core.NMaybe getChild_patch() {
		return (core.NMaybe) (child_patch = common.Util.demand(child_patch));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i_name: return getChild_name();
			case i_vendor: return getChild_vendor();
			case i_major: return getChild_major();
			case i_minor: return getChild_minor();
			case i_patch: return getChild_patch();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i_name: return child_name;
			case i_vendor: return child_vendor;
			case i_major: return child_major;
			case i_minor: return child_minor;
			case i_patch: return child_patch;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:support:monto:negotiation:softwareVersion erroneously claimed to forward");
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
		return "silver:support:monto:negotiation:softwareVersion";
	}

	static void initProductionAttributeDefinitions() {
		// opts = catMaybes([ pairMaybe("name", jsonString, name), pairMaybe("vendor", jsonString, vendor), pairMaybe("major", jsonInteger, major), pairMaybe("minor", jsonInteger, minor), pairMaybe("patch", jsonInteger, patch) ])
		silver.support.monto.negotiation.PsoftwareVersion.localAttributes[silver.support.monto.negotiation.Init.opts__ON__silver_support_monto_negotiation_softwareVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PcatMaybes.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)silver.support.monto.negotiation.PpairMaybe.invoke((new common.StringCatter("name")), silver.json.PjsonString.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.support.monto.negotiation.PsoftwareVersion.i_name)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)silver.support.monto.negotiation.PpairMaybe.invoke((new common.StringCatter("vendor")), silver.json.PjsonString.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.support.monto.negotiation.PsoftwareVersion.i_vendor)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)silver.support.monto.negotiation.PpairMaybe.invoke((new common.StringCatter("major")), silver.json.PjsonInteger.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.support.monto.negotiation.PsoftwareVersion.i_major)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)silver.support.monto.negotiation.PpairMaybe.invoke((new common.StringCatter("minor")), silver.json.PjsonInteger.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.support.monto.negotiation.PsoftwareVersion.i_minor)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)silver.support.monto.negotiation.PpairMaybe.invoke((new common.StringCatter("patch")), silver.json.PjsonInteger.factory, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.support.monto.negotiation.PsoftwareVersion.i_patch)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } };
		// top.json = jsonObject((pair("id", jsonString(id)) :: opts))
		silver.support.monto.negotiation.PsoftwareVersion.synthesizedAttributes[silver.support.monto.negotiation.Init.silver_json_json__ON__silver_support_monto_negotiation_SoftwareVersion] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonObject(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("id")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.json.NJson)new silver.json.PjsonString(context.childAsIsLazy(silver.support.monto.negotiation.PsoftwareVersion.i_id))); } })); } }, context.localAsIsLazy(silver.support.monto.negotiation.Init.opts__ON__silver_support_monto_negotiation_softwareVersion))); } })); } };

	}

	public static final common.NodeFactory<PsoftwareVersion> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsoftwareVersion> {

		@Override
		public PsoftwareVersion invoke(final Object[] children, final Object[] annotations) {
			return new PsoftwareVersion(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
