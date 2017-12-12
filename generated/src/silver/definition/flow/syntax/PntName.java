
package silver.definition.flow.syntax;

// top::NtName ::= nt::QName 
public final class PntName extends silver.definition.flow.syntax.NNtName {

	public static final int i_nt = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_ntName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NNtName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NNtName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];

	}

	public PntName(final Object c_nt, final Object a_core_location) {
		super(a_core_location);
		this.child_nt = c_nt;

	}

	private Object child_nt;
	public final silver.definition.core.NQName getChild_nt() {
		return (silver.definition.core.NQName) (child_nt = common.Util.demand(child_nt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:ntName erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:ntName";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = nt.pp
		silver.definition.flow.syntax.PntName.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_NtName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PntName.i_nt).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.errors := if null(nt.lookupType.errors) then myCopy.errors else nt.lookupType.errors
		if(silver.definition.flow.syntax.PntName.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtName] == null)
			silver.definition.flow.syntax.PntName.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtName] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtName);
		((common.CollectionAttribute)silver.definition.flow.syntax.PntName.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtName]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PntName.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)context.localDecorated(silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName).synthesized(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpec)) : ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PntName.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup))); } });
		// top.flowDefs = if null(nt.lookupType.errors) then myCopy.flowDefs else []
		silver.definition.flow.syntax.PntName.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_NtName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PntName.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)context.localDecorated(silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName).synthesized(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_FlowSpec)) : ((common.ConsCell)core.Pnil.invoke())); } };
		// myCopy = top.flowSpecSpec
		silver.definition.flow.syntax.PntName.localAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.flow.syntax.NFlowSpec)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_flowSpecSpec__ON__silver_definition_flow_syntax_NtName)); } };
		// myCopy.config = top.config
		silver.definition.flow.syntax.PntName.localInheritedAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName][silver.definition.flow.syntax.Init.silver_definition_env_config__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.syntax.Init.silver_definition_env_config__ON__silver_definition_flow_syntax_NtName)); } };
		// myCopy.grammarName = top.grammarName
		silver.definition.flow.syntax.PntName.localInheritedAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName][silver.definition.flow.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.inherited(silver.definition.flow.syntax.Init.silver_definition_core_grammarName__ON__silver_definition_flow_syntax_NtName)); } };
		// myCopy.env = top.env
		silver.definition.flow.syntax.PntName.localInheritedAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName][silver.definition.flow.syntax.Init.silver_definition_env_env__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.syntax.Init.silver_definition_env_env__ON__silver_definition_flow_syntax_NtName)); } };
		// myCopy.compiledGrammars = top.compiledGrammars
		silver.definition.flow.syntax.PntName.localInheritedAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName][silver.definition.flow.syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Object)context.inherited(silver.definition.flow.syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_flow_syntax_NtName)); } };
		// myCopy.flowEnv = top.flowEnv
		silver.definition.flow.syntax.PntName.localInheritedAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName][silver.definition.flow.syntax.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_flow_syntax_NtName)); } };
		// myCopy.onNt = nt.lookupType.typerep
		silver.definition.flow.syntax.PntName.localInheritedAttributes[silver.definition.flow.syntax.Init.myCopy__ON__silver_definition_flow_syntax_ntName][silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PntName.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } };

	}

	public static final common.NodeFactory<PntName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PntName> {

		@Override
		public PntName invoke(final Object[] children, final Object[] annotations) {
			return new PntName(children[0], annotations[0]);
		}
	};

}
