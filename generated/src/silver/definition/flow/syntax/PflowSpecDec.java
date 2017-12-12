
package silver.definition.flow.syntax;

// top::FlowSpecInh ::= 'decorate' 
public final class PflowSpecDec extends silver.definition.flow.syntax.NFlowSpecInh {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TDecorate_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_flowSpecDec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecInh.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecInh.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PflowSpecDec(final Object c__G_0, final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:flowSpecDec erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:flowSpecDec";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "decorate"
		silver.definition.flow.syntax.PflowSpecDec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecInh] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("decorate")); } };
		// specs = getFlowTypeSpecFor(top.onNt.typeName, top.flowEnv)
		silver.definition.flow.syntax.PflowSpecDec.localAttributes[silver.definition.flow.syntax.Init.specs__ON__silver_definition_flow_syntax_flowSpecDec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PgetFlowTypeSpecFor.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpecInh)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_flow_syntax_FlowSpecInh))); } };
		// decSpec = lookupBy(stringEq, "decorate", specs)
		silver.definition.flow.syntax.PflowSpecDec.localAttributes[silver.definition.flow.syntax.Init.decSpec__ON__silver_definition_flow_syntax_flowSpecDec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)core.PlookupBy.invoke(core.PstringEq.factory, (new common.StringCatter("decorate")), context.localAsIsLazy(silver.definition.flow.syntax.Init.specs__ON__silver_definition_flow_syntax_flowSpecDec))); } };
		// top.errors := if decSpec.isJust then [] else [ err(top.location, "to use 'decorate' in a flow type for nonterminal " ++ top.onNt.typeName ++ ", 'decorate' must also have an explicit flow type") ]
		if(silver.definition.flow.syntax.PflowSpecDec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh] == null)
			silver.definition.flow.syntax.PflowSpecDec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecDec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.definition.flow.syntax.Init.decSpec__ON__silver_definition_flow_syntax_flowSpecDec).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.flow.syntax.NFlowSpecInh)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("to use 'decorate' in a flow type for nonterminal ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpecInh)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(", 'decorate' must also have an explicit flow type")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.inhList = fromMaybe([], decSpec)
		silver.definition.flow.syntax.PflowSpecDec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_syntax_inhList__ON__silver_definition_flow_syntax_FlowSpecInh] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PfromMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.flow.syntax.Init.decSpec__ON__silver_definition_flow_syntax_flowSpecDec)))); } };

	}

	public static final common.NodeFactory<PflowSpecDec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PflowSpecDec> {

		@Override
		public PflowSpecDec invoke(final Object[] children, final Object[] annotations) {
			return new PflowSpecDec(children[0], annotations[0]);
		}
	};

}
