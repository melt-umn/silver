
package silver.definition.flow.syntax;

// top::AGDcl ::= 'flowtype' nt::QName '=' specs::FlowSpecs ';' 
public final class PflowtypeDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i_nt = 1;
	public static final int i__G_2 = 2;
	public static final int i_specs = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.definition.flow.syntax.TFlowtype.class,silver.definition.core.NQName.class,silver.definition.core.TEqual_t.class,silver.definition.flow.syntax.NFlowSpecs.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_flowtypeDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_specs] = new common.Lazy[silver.definition.flow.syntax.NFlowSpecs.num_inh_attrs];

	}

	public PflowtypeDcl(final Object c__G_4, final Object c_nt, final Object c__G_2, final Object c_specs, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_nt = c_nt;
		this.child__G_2 = c__G_2;
		this.child_specs = c_specs;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.definition.flow.syntax.TFlowtype getChild__G_4() {
		return (silver.definition.flow.syntax.TFlowtype) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_nt;
	public final silver.definition.core.NQName getChild_nt() {
		return (silver.definition.core.NQName) (child_nt = common.Util.demand(child_nt));
	}

	private Object child__G_2;
	public final silver.definition.core.TEqual_t getChild__G_2() {
		return (silver.definition.core.TEqual_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_specs;
	public final silver.definition.flow.syntax.NFlowSpecs getChild_specs() {
		return (silver.definition.flow.syntax.NFlowSpecs) (child_specs = common.Util.demand(child_specs));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_nt: return getChild_nt();
			case i__G_2: return getChild__G_2();
			case i_specs: return getChild_specs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_nt: return child_nt;
			case i__G_2: return child__G_2;
			case i_specs: return child_specs;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:flowtypeDcl erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:flowtypeDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "flowtype " ++ nt.pp ++ " = " ++ specs.pp ++ ";"
		silver.definition.flow.syntax.PflowtypeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("flowtype ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_specs).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpecs)), (common.StringCatter)(new common.StringCatter(";")))))); } };
		// top.errors := if null(nt.lookupType.errors) then specs.errors else nt.lookupType.errors
		if(silver.definition.flow.syntax.PflowtypeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.flow.syntax.PflowtypeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowtypeDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_specs).synthesized(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_FlowSpecs)) : ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup))); } });
		// top.flowDefs = if null(nt.lookupType.errors) then specs.flowDefs else []
		silver.definition.flow.syntax.PflowtypeDcl.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) ? ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_specs).synthesized(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_FlowSpecs)) : ((common.ConsCell)core.Pnil.invoke())); } };
		// specs.onNt = nt.lookupType.typerep
		silver.definition.flow.syntax.PflowtypeDcl.childInheritedAttributes[silver.definition.flow.syntax.PflowtypeDcl.i_specs][silver.definition.flow.syntax.Init.silver_definition_flow_syntax_onNt__ON__silver_definition_flow_syntax_FlowSpecs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)context.childDecorated(silver.definition.flow.syntax.PflowtypeDcl.i_nt).synthesized(silver.definition.core.Init.silver_definition_core_lookupType__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } };

	}

	public static final common.NodeFactory<PflowtypeDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PflowtypeDcl> {

		@Override
		public PflowtypeDcl invoke(final Object[] children, final Object[] annotations) {
			return new PflowtypeDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
