
package silver.definition.flow.ast;

// top::FlowDefs ::= 
public final class PnilFlow extends silver.definition.flow.ast.NFlowDefs {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_ast_nilFlow;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.ast.NFlowDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilFlow() {
		super();

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:ast:nilFlow erroneously claimed to forward");
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
		return "silver:definition:flow:ast:nilFlow";
	}

	static void initProductionAttributeDefinitions() {
		// top.synTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_synTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.inhTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_inhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.defTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_defTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.fwdTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.fwdInhTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_fwdInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodGraphContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_prodGraphContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.refTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_refTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.localInhTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_localInhTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.localTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_localTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.extSynTreeContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_extSynTreeContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.nonSuspectContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_nonSuspectContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.specContribs = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_specContribs__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.unparses = []
		silver.definition.flow.ast.PnilFlow.synthesizedAttributes[silver.definition.flow.ast.Init.silver_definition_flow_ast_unparses__ON__silver_definition_flow_ast_FlowDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilFlow> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilFlow> {

		@Override
		public PnilFlow invoke(final Object[] children, final Object[] annotations) {
			return new PnilFlow();
		}
	};

}
