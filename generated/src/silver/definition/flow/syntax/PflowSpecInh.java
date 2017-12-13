
package silver.definition.flow.syntax;

// top::FlowSpecInh ::= inh::QNameAttrOccur 
public final class PflowSpecInh extends silver.definition.flow.syntax.NFlowSpecInh {

	public static final int i_inh = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.NQNameAttrOccur.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_flowSpecInh;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecInh.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.syntax.NFlowSpecInh.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_inh] = new common.Lazy[silver.definition.core.NQNameAttrOccur.num_inh_attrs];

	}

	public PflowSpecInh(final Object c_inh, final Object a_core_location) {
		super(a_core_location);
		this.child_inh = c_inh;

	}

	private Object child_inh;
	public final silver.definition.core.NQNameAttrOccur getChild_inh() {
		return (silver.definition.core.NQNameAttrOccur) (child_inh = common.Util.demand(child_inh));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_inh: return getChild_inh();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_inh: return child_inh;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:flowSpecInh erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:flowSpecInh";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = inh.pp
		silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecInh] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecInh.i_inh).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)); } };
		// top.errors := inh.errors
		if(silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh] == null)
			silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PflowSpecInh.i_inh).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur)); } });
		// top.inhList = if null(inh.errors) then [ inh.attrDcl.fullName ] else []
		silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_flow_syntax_inhList__ON__silver_definition_flow_syntax_FlowSpecInh] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecInh.i_inh, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NDclInfo)context.childDecorated(silver.definition.flow.syntax.PflowSpecInh.i_inh).synthesized(silver.definition.core.Init.silver_definition_core_attrDcl__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// inh.attrFor = top.onNt
		silver.definition.flow.syntax.PflowSpecInh.childInheritedAttributes[silver.definition.flow.syntax.PflowSpecInh.i_inh][silver.definition.core.Init.silver_definition_core_attrFor__ON__silver_definition_core_QNameAttrOccur] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)context.inherited(silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpecInh)); } };
		// top.errors <- if ! null(inh.errors) || inh.attrDcl.isInherited then [] else [ err(inh.location, inh.pp ++ " is not an inherited attribute and so cannot be within a flow type") ]
		if(silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh] == null)
			silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh] = new silver.definition.core.CAerrors(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowSpecInh.synthesizedAttributes[silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecInh]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((!((Boolean)core.Pnull.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.syntax.PflowSpecInh.i_inh, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameAttrOccur)))) || ((Boolean)((silver.definition.env.NDclInfo)context.childDecorated(silver.definition.flow.syntax.PflowSpecInh.i_inh).synthesized(silver.definition.core.Init.silver_definition_core_attrDcl__ON__silver_definition_core_QNameAttrOccur)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_isInherited__ON__silver_definition_env_DclInfo))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQNameAttrOccur)context.childDecorated(silver.definition.flow.syntax.PflowSpecInh.i_inh).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowSpecInh.i_inh).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QNameAttrOccur)), (common.StringCatter)(new common.StringCatter(" is not an inherited attribute and so cannot be within a flow type"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });

	}

	public static final common.NodeFactory<PflowSpecInh> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PflowSpecInh> {

		@Override
		public PflowSpecInh invoke(final Object[] children, final Object[] annotations) {
			return new PflowSpecInh(children[0], annotations[0]);
		}
	};

}
