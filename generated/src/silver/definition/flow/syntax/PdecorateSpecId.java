
package silver.definition.flow.syntax;

// top::FlowSpecId ::= 'decorate' 
public final class PdecorateSpecId extends silver.definition.flow.syntax.NFlowSpecId {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TDecorate_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_decorateSpecId;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecId.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecId.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PdecorateSpecId(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.core.TDecorate_kwd getChild__G_0() {
		return (silver.definition.core.TDecorate_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:decorateSpecId erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:decorateSpecId";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "decorate"
		silver.definition.flow.syntax.PdecorateSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("decorate")); } };
		// top.errors := []
		if(silver.definition.flow.syntax.PdecorateSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId] == null)
			silver.definition.flow.syntax.PdecorateSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId);
		((common.CollectionAttribute)silver.definition.flow.syntax.PdecorateSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.synName = "decorate"
		silver.definition.flow.syntax.PdecorateSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_syntax_synName__ON__silver_definition_flow_syntax_FlowSpecId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("decorate")); } };
		// top.authorityGrammar = hackGramFromFName(top.onNt.typeName)
		silver.definition.flow.syntax.PdecorateSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_syntax_authorityGrammar__ON__silver_definition_flow_syntax_FlowSpecId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.flow.env.PhackGramFromFName.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpecId)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } })); } };

	}

	public static final common.NodeFactory<PdecorateSpecId> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdecorateSpecId> {

		@Override
		public PdecorateSpecId invoke(final Object[] children, final Object[] annotations) {
			return new PdecorateSpecId(children[0], annotations[0]);
		}
	};

}
