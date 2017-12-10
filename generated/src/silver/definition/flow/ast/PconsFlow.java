
package silver.definition.flow.ast;

// top::FlowDefs ::= h::FlowDef t::FlowDefs 
public final class PconsFlow extends silver.definition.flow.ast.NFlowDefs {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.flow.ast.NFlowDef.class,silver.definition.flow.ast.NFlowDefs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_consFlow;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.flow.ast.NFlowDef.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_inh_attrs];

	}

	public PconsFlow(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.flow.ast.NFlowDef getChild_h() {
		return (silver.definition.flow.ast.NFlowDef) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.flow.ast.NFlowDefs getChild_t() {
		return (silver.definition.flow.ast.NFlowDefs) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:consFlow erroneously claimed to forward");
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
		return "silver:definition:flow:ast:consFlow";
	}

	static void initProductionAttributeDefinitions() {
		// top.synTreeContribs = h.synTreeContribs ++ t.synTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.inhTreeContribs = h.inhTreeContribs ++ t.inhTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.defTreeContribs = h.defTreeContribs ++ t.defTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.fwdTreeContribs = h.fwdTreeContribs ++ t.fwdTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.fwdInhTreeContribs = h.fwdInhTreeContribs ++ t.fwdInhTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.prodTreeContribs = h.prodTreeContribs ++ t.prodTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.prodGraphContribs = h.prodGraphContribs ++ t.prodGraphContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.refTreeContribs = h.refTreeContribs ++ t.refTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.localInhTreeContribs = h.localInhTreeContribs ++ t.localInhTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.localTreeContribs = h.localTreeContribs ++ t.localTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.extSynTreeContribs = h.extSynTreeContribs ++ t.extSynTreeContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.nonSuspectContribs = h.nonSuspectContribs ++ t.nonSuspectContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.specContribs = h.specContribs ++ t.specContribs
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDefs))); } };
		// top.unparses = h.unparses ++ t.unparses
		silver.definition.flow.ast.PconsFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_h, silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDef), context.childDecoratedSynthesizedLazy(silver.definition.flow.ast.PconsFlow.i_t, silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDefs))); } };

	}

	public static final common.NodeFactory<PconsFlow> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsFlow> {

		@Override
		public PconsFlow invoke(final Object[] children, final Object[] annotations) {
			return new PconsFlow(children[0], children[1]);
		}
	};

}
