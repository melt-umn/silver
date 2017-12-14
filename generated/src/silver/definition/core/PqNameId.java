
package silver.definition.core;

// top::QName ::= id::Name 
public final class PqNameId extends silver.definition.core.NQName {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_qNameId;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NQName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PqNameId(final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;

	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:qNameId erroneously claimed to forward");
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
		return "silver:definition:core:qNameId";
	}

	static void initProductionAttributeDefinitions() {
		// top.name = id.name
		silver.definition.core.PqNameId.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PqNameId.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)); } };
		// top.pp = id.pp
		silver.definition.core.PqNameId.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PqNameId.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)); } };
		// top.lookupValue = decorate customLookup("value", getValueDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameId.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("value")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		// top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameId.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		// top.lookupAttribute = decorate customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameId.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("attribute")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };

	}

	public static final common.NodeFactory<PqNameId> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameId> {

		@Override
		public PqNameId invoke(final Object[] children, final Object[] annotations) {
			return new PqNameId(children[0], annotations[0]);
		}
	};

}
