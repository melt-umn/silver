
package silver.definition.core;

// top::QName ::= id::Name ':' qn::QName 
public final class PqNameCons extends silver.definition.core.NQName {

	public static final int i_id = 0;
	public static final int i__G_1 = 1;
	public static final int i_qn = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class,silver.definition.core.TColon_t.class,silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_qNameCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NQName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PqNameCons(final Object c_id, final Object c__G_1, final Object c_qn, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;
		this.child__G_1 = c__G_1;
		this.child_qn = c_qn;

	}

	private Object child_id;
	public final silver.definition.core.NName getChild_id() {
		return (silver.definition.core.NName) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_1;
	public final silver.definition.core.TColon_t getChild__G_1() {
		return (silver.definition.core.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();
			case i__G_1: return getChild__G_1();
			case i_qn: return getChild_qn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;
			case i__G_1: return child__G_1;
			case i_qn: return child_qn;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:qNameCons erroneously claimed to forward");
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
		return "silver:definition:core:qNameCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.name = id.name ++ ":" ++ qn.name
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameCons.i_id).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameCons.i_qn).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)))); } };
		// top.pp = id.pp ++ ":" ++ qn.pp
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameCons.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(":")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PqNameCons.i_qn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)))); } };
		// top.lookupValue = decorate customLookup("value", getValueDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("value")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetValueDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		// top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		// top.lookupAttribute = decorate customLookup("attribute", getAttrDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("attribute")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };

	}

	public static final common.NodeFactory<PqNameCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameCons> {

		@Override
		public PqNameCons invoke(final Object[] children, final Object[] annotations) {
			return new PqNameCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
