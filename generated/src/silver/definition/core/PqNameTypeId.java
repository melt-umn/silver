
package silver.definition.core;

// top::QNameType ::= id::IdUpper_t 
public final class PqNameTypeId extends silver.definition.core.NQNameType {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TIdUpper_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_qNameTypeId;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NQNameType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NQNameType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PqNameTypeId(final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;

	}

	private Object child_id;
	public final silver.definition.core.TIdUpper_t getChild_id() {
		return (silver.definition.core.TIdUpper_t) (child_id = common.Util.demand(child_id));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:qNameTypeId erroneously claimed to forward");
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
		return "silver:definition:core:qNameTypeId";
	}

	static void initProductionAttributeDefinitions() {
		// top.name = id.lexeme
		silver.definition.core.PqNameTypeId.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TIdUpper_t)context.childAsIs(silver.definition.core.PqNameTypeId.i_id)).lexeme); } };
		// top.pp = id.lexeme
		silver.definition.core.PqNameTypeId.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TIdUpper_t)context.childAsIs(silver.definition.core.PqNameTypeId.i_id)).lexeme); } };
		// top.lookupType = decorate customLookup("type", getTypeDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameTypeId.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QNameType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("type")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetTypeDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameType), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QNameType))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QNameType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQNameType)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };

	}

	public static final common.NodeFactory<PqNameTypeId> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNameTypeId> {

		@Override
		public PqNameTypeId invoke(final Object[] children, final Object[] annotations) {
			return new PqNameTypeId(children[0], annotations[0]);
		}
	};

}
