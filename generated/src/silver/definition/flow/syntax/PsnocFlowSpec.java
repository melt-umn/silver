
package silver.definition.flow.syntax;

// top::FlowSpecs ::= h::FlowSpecs ',' t::FlowSpec 
public final class PsnocFlowSpec extends silver.definition.flow.syntax.NFlowSpecs {

	public static final int i_h = 0;
	public static final int i__G_1 = 1;
	public static final int i_t = 2;


	public static final Class<?> childTypes[] = {silver.definition.flow.syntax.NFlowSpecs.class,silver.definition.core.TComma_t.class,silver.definition.flow.syntax.NFlowSpec.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_snocFlowSpec;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.flow.syntax.NFlowSpecs.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.flow.syntax.NFlowSpec.num_inh_attrs];

	}

	public PsnocFlowSpec(final Object c_h, final Object c__G_1, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.flow.syntax.NFlowSpecs getChild_h() {
		return (silver.definition.flow.syntax.NFlowSpecs) (child_h = common.Util.demand(child_h));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final silver.definition.flow.syntax.NFlowSpec getChild_t() {
		return (silver.definition.flow.syntax.NFlowSpec) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i__G_1: return child__G_1;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:snocFlowSpec erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:snocFlowSpec";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ ", " ++ t.pp
		silver.definition.flow.syntax.PsnocFlowSpec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PsnocFlowSpec.i_h).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PsnocFlowSpec.i_t).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpec)))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.definition.flow.syntax.PsnocFlowSpec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecs] == null)
			silver.definition.flow.syntax.PsnocFlowSpec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecs] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecs);
		((common.CollectionAttribute)silver.definition.flow.syntax.PsnocFlowSpec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecs]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PsnocFlowSpec.i_h, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecs), context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PsnocFlowSpec.i_t, silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec))); } });
		// top.flowDefs = h.flowDefs ++ t.flowDefs
		silver.definition.flow.syntax.PsnocFlowSpec.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_FlowSpecs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PsnocFlowSpec.i_h, silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_FlowSpecs), context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PsnocFlowSpec.i_t, silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_FlowSpec))); } };

	}

	public static final common.NodeFactory<PsnocFlowSpec> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsnocFlowSpec> {

		@Override
		public PsnocFlowSpec invoke(final Object[] children, final Object[] annotations) {
			return new PsnocFlowSpec(children[0], children[1], children[2], annotations[0]);
		}
	};

}
