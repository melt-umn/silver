
package silver.definition.flow.syntax;

// top::AGDcl ::= 'flowtype' attr::FlowSpec 'on' nts::NtList ';' 
public final class PflowtypeAttrDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i_attr = 1;
	public static final int i__G_2 = 2;
	public static final int i_nts = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.definition.flow.syntax.TFlowtype.class,silver.definition.flow.syntax.NFlowSpec.class,silver.definition.core.TOn_kwd.class,silver.definition.flow.syntax.NNtList.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_syntax_flowtypeAttrDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_attr] = new common.Lazy[silver.definition.flow.syntax.NFlowSpec.num_inh_attrs];
	childInheritedAttributes[i_nts] = new common.Lazy[silver.definition.flow.syntax.NNtList.num_inh_attrs];

	}

	public PflowtypeAttrDcl(final Object c__G_4, final Object c_attr, final Object c__G_2, final Object c_nts, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child_attr = c_attr;
		this.child__G_2 = c__G_2;
		this.child_nts = c_nts;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.definition.flow.syntax.TFlowtype getChild__G_4() {
		return (silver.definition.flow.syntax.TFlowtype) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_attr;
	public final silver.definition.flow.syntax.NFlowSpec getChild_attr() {
		return (silver.definition.flow.syntax.NFlowSpec) (child_attr = common.Util.demand(child_attr));
	}

	private Object child__G_2;
	public final silver.definition.core.TOn_kwd getChild__G_2() {
		return (silver.definition.core.TOn_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_nts;
	public final silver.definition.flow.syntax.NNtList getChild_nts() {
		return (silver.definition.flow.syntax.NNtList) (child_nts = common.Util.demand(child_nts));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i_attr: return getChild_attr();
			case i__G_2: return getChild__G_2();
			case i_nts: return getChild_nts();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i_attr: return child_attr;
			case i__G_2: return child__G_2;
			case i_nts: return child_nts;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:syntax:flowtypeAttrDcl erroneously claimed to forward");
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
		return "silver:definition:flow:syntax:flowtypeAttrDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "flowtype " ++ attr.pp ++ " on " ++ nts.pp ++ ";"
		silver.definition.flow.syntax.PflowtypeAttrDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("flowtype ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowtypeAttrDcl.i_attr).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_FlowSpec)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" on ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.syntax.PflowtypeAttrDcl.i_nts).synthesized(silver.definition.flow.syntax.Init.silver_definition_env_pp__ON__silver_definition_flow_syntax_NtList)), (common.StringCatter)(new common.StringCatter(";")))))); } };
		// top.errors := nts.errors
		if(silver.definition.flow.syntax.PflowtypeAttrDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] == null)
			silver.definition.flow.syntax.PflowtypeAttrDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.definition.flow.syntax.PflowtypeAttrDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PflowtypeAttrDcl.i_nts).synthesized(silver.definition.flow.syntax.Init.silver_definition_core_errors__ON__silver_definition_flow_syntax_NtList)); } });
		// top.flowDefs = nts.flowDefs
		silver.definition.flow.syntax.PflowtypeAttrDcl.synthesizedAttributes[silver.definition.flow.env.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.flow.syntax.PflowtypeAttrDcl.i_nts).synthesized(silver.definition.flow.syntax.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_syntax_NtList)); } };
		// nts.flowSpecSpec = attr
		silver.definition.flow.syntax.PflowtypeAttrDcl.childInheritedAttributes[silver.definition.flow.syntax.PflowtypeAttrDcl.i_nts][silver.definition.flow.syntax.Init.silver_definition_flow_syntax_flowSpecSpec__ON__silver_definition_flow_syntax_NtList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.flow.syntax.PflowtypeAttrDcl.i_attr).undecorate(); } };

	}

	public static final common.NodeFactory<PflowtypeAttrDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PflowtypeAttrDcl> {

		@Override
		public PflowtypeAttrDcl invoke(final Object[] children, final Object[] annotations) {
			return new PflowtypeAttrDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
