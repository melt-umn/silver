
package silver.definition.core;

// top::AspectProductionLHS ::= id::Name 
public final class PaspectProductionLHSId extends silver.definition.core.NAspectProductionLHS {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectProductionLHSId;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAspectProductionLHS.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAspectProductionLHS.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.definition.core.NName.num_inh_attrs];

	}

	public PaspectProductionLHSId(final Object c_id, final Object a_core_location) {
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAspectProductionLHS)new silver.definition.core.PaspectProductionLHSFull(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PaspectProductionLHSId.i_id)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.core.Init.rType__ON__silver_definition_core_aspectProductionLHSId)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAspectProductionLHS)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:aspectProductionLHSId";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.pp
		silver.definition.core.PaspectProductionLHSId.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectProductionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.core.PaspectProductionLHSId.i_id).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Name)); } };
		// rType = if null(top.realSignature) then errorType() else head(top.realSignature).typerep
		silver.definition.core.PaspectProductionLHSId.localAttributes[silver.definition.core.Init.rType__ON__silver_definition_core_aspectProductionLHSId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectProductionLHS))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_realSignature__ON__silver_definition_core_AspectProductionLHS))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement))); } };

	}

	public static final common.NodeFactory<PaspectProductionLHSId> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectProductionLHSId> {

		@Override
		public PaspectProductionLHSId invoke(final Object[] children, final Object[] annotations) {
			return new PaspectProductionLHSId(children[0], annotations[0]);
		}
	};

}
