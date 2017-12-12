
package silver.definition.flow.env_parser;

// top::IFlow ::= 'implicitFwdAffects' '(' prd::IName ',' attrs::INames ')' 
public final class PaFlowImplicitFwdAffects extends silver.definition.flow.env_parser.NIFlow {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_prd = 2;
	public static final int i__G_2 = 3;
	public static final int i_attrs = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.flow.env_parser.TImplicitFwdAffectsTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINames.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_parser_aFlowImplicitFwdAffects;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlow.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.flow.env_parser.NIFlow.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_prd] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_attrs] = new common.Lazy[silver.definition.env.env_parser.NINames.num_inh_attrs];

	}

	public PaFlowImplicitFwdAffects(final Object c__G_5, final Object c__G_4, final Object c_prd, final Object c__G_2, final Object c_attrs, final Object c__G_0) {
		super();
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_prd = c_prd;
		this.child__G_2 = c__G_2;
		this.child_attrs = c_attrs;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.definition.flow.env_parser.TImplicitFwdAffectsTerm getChild__G_5() {
		return (silver.definition.flow.env_parser.TImplicitFwdAffectsTerm) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_4() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_prd;
	public final silver.definition.env.env_parser.NIName getChild_prd() {
		return (silver.definition.env.env_parser.NIName) (child_prd = common.Util.demand(child_prd));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_attrs;
	public final silver.definition.env.env_parser.NINames getChild_attrs() {
		return (silver.definition.env.env_parser.NINames) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_prd: return getChild_prd();
			case i__G_2: return getChild__G_2();
			case i_attrs: return getChild_attrs();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_prd: return child_prd;
			case i__G_2: return child__G_2;
			case i_attrs: return child_attrs;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:flow:env_parser:aFlowImplicitFwdAffects erroneously claimed to forward");
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
		return "silver:definition:flow:env_parser:aFlowImplicitFwdAffects";
	}

	static void initProductionAttributeDefinitions() {
		// top.flowDefs = [ implicitFwdAffects(prd.aname, attrs.names) ]
		silver.definition.flow.env_parser.PaFlowImplicitFwdAffects.synthesizedAttributes[silver.definition.flow.env_parser.Init.silver_definition_flow_env_flowDefs__ON__silver_definition_flow_env_parser_IFlow] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.flow.ast.NFlowDef)new silver.definition.flow.ast.PimplicitFwdAffects(context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaFlowImplicitFwdAffects.i_prd, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.flow.env_parser.PaFlowImplicitFwdAffects.i_attrs, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_names__ON__silver_definition_env_env_parser_INames))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaFlowImplicitFwdAffects> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaFlowImplicitFwdAffects> {

		@Override
		public PaFlowImplicitFwdAffects invoke(final Object[] children, final Object[] annotations) {
			return new PaFlowImplicitFwdAffects(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
