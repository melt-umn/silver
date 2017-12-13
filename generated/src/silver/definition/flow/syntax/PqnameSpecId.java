
package silver.definition.flow.syntax;

// top::FlowSpecId ::= syn::QNameAttrOccur 
public final class PqnameSpecId extends silver.definition.flow.syntax.NFlowSpecId {

	public static final int i_syn = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQNameAttrOccur.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_qnameSpecId;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecId.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecId.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_syn] = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_inh_attrs];

	}

	public PqnameSpecId(final Object c_syn, final Object a_core_location) {
		super(a_core_location);
		this.child_syn = c_syn;

	}

	private Object child_syn;
	public final silver.definition.core.NQNameAttrOccur getChild_syn() {
		return (silver.definition.core.NQNameAttrOccur) (child_syn = common.Util.demand(child_syn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_syn: return getChild_syn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_syn: return child_syn;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:qnameSpecId erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:qnameSpecId";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = syn.pp
		silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)); } };
		// top.errors := syn.errors
		if(silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId] == null)
			silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId);
		((common.CollectionAttribute)silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur)); } });
		// top.synName = syn.attrDcl.fullName
		silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_syntax_synName__ON__silver_definition_flow_syntax_FlowSpecId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).synthesized(silver.definition.core.Init.silver_definition_core_attrDcl__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } };
		// top.authorityGrammar = syn.dcl.sourceGrammar
		silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_syntax_authorityGrammar__ON__silver_definition_flow_syntax_FlowSpecId] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).synthesized(silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo)); } };
		// syn.attrFor = top.onNt
		silver.definition.flow.syntax.PqnameSpecId.childInheritedAttributes[silver.definition.flow.syntax.PqnameSpecId.i_syn][silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpecId)); } };
		// top.errors <- if ! null(syn.errors) || syn.attrDcl.isSynthesized then [] else [ err(syn.location, syn.pp ++ " is not a synthesized attribute, and so cannot have a flow type") ]
		if(silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId] == null)
			silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId);
		((common.CollectionAttribute)silver.definition.flow.syntax.PqnameSpecId.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecId]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PqnameSpecId.i_syn, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur)))) || ((Boolean)((silver.definition.env.NDclInfo)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).synthesized(silver.definition.core.Init.silver_definition_core_attrDcl__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isSynthesized__ON__silver_definition_env_DclInfo))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQNameAttrOccur)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PqnameSpecId.i_syn).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)(new common.StringCatter(" is not a synthesized attribute, and so cannot have a flow type"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PqnameSpecId> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqnameSpecId> {

		@Override
		public PqnameSpecId invoke(final Object[] children, final Object[] annotations) {
			return new PqnameSpecId(children[0], annotations[0]);
		}
	};

}
