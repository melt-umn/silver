
package silver.definition.env.env_parser;

// top::IDclInfo ::= 'p@' '(' l::ILocation ',' td::ITyVarDcls ',' s::INamedSignature ',' t::IDefs ')' 
public final class PaDclInfoProdAttr extends silver.definition.env.env_parser.NIDclInfo {

	public static final int i__G_9 = 0;
	public static final int i__G_8 = 1;
	public static final int i_l = 2;
	public static final int i__G_6 = 3;
	public static final int i_td = 4;
	public static final int i__G_4 = 5;
	public static final int i_s = 6;
	public static final int i__G_2 = 7;
	public static final int i_t = 8;
	public static final int i__G_0 = 9;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TProdAttrTerm.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NILocation.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITyVarDcls.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NINamedSignature.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIDefs.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aDclInfoProdAttr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[10][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.env.env_parser.NILocation.num_inh_attrs];
	childInheritedAttributes[i_td] = new common.Lazy[silver.definition.env.env_parser.NITyVarDcls.num_inh_attrs];
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.env.env_parser.NINamedSignature.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.env.env_parser.NIDefs.num_inh_attrs];

	}

	public PaDclInfoProdAttr(final Object c__G_9, final Object c__G_8, final Object c_l, final Object c__G_6, final Object c_td, final Object c__G_4, final Object c_s, final Object c__G_2, final Object c_t, final Object c__G_0) {
		super();
		this.child__G_9 = c__G_9;
		this.child__G_8 = c__G_8;
		this.child_l = c_l;
		this.child__G_6 = c__G_6;
		this.child_td = c_td;
		this.child__G_4 = c__G_4;
		this.child_s = c_s;
		this.child__G_2 = c__G_2;
		this.child_t = c_t;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_9;
	public final silver.definition.env.env_parser.TProdAttrTerm getChild__G_9() {
		return (silver.definition.env.env_parser.TProdAttrTerm) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child__G_8;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_8() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_l;
	public final silver.definition.env.env_parser.NILocation getChild_l() {
		return (silver.definition.env.env_parser.NILocation) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_6;
	public final silver.definition.env.env_parser.TComma_t getChild__G_6() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_td;
	public final silver.definition.env.env_parser.NITyVarDcls getChild_td() {
		return (silver.definition.env.env_parser.NITyVarDcls) (child_td = common.Util.demand(child_td));
	}

	private Object child__G_4;
	public final silver.definition.env.env_parser.TComma_t getChild__G_4() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_s;
	public final silver.definition.env.env_parser.NINamedSignature getChild_s() {
		return (silver.definition.env.env_parser.NINamedSignature) (child_s = common.Util.demand(child_s));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_t;
	public final silver.definition.env.env_parser.NIDefs getChild_t() {
		return (silver.definition.env.env_parser.NIDefs) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_9: return getChild__G_9();
			case i__G_8: return getChild__G_8();
			case i_l: return getChild_l();
			case i__G_6: return getChild__G_6();
			case i_td: return getChild_td();
			case i__G_4: return getChild__G_4();
			case i_s: return getChild_s();
			case i__G_2: return getChild__G_2();
			case i_t: return getChild_t();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_9: return child__G_9;
			case i__G_8: return child__G_8;
			case i_l: return child_l;
			case i__G_6: return child__G_6;
			case i_td: return child_td;
			case i__G_4: return child__G_4;
			case i_s: return child_s;
			case i__G_2: return child__G_2;
			case i_t: return child_t;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 10;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aDclInfoProdAttr erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aDclInfoProdAttr";
	}

	static void initProductionAttributeDefinitions() {
		// s.env = newScopeEnv(td.defs, top.env)
		silver.definition.env.env_parser.PaDclInfoProdAttr.childInheritedAttributes[silver.definition.env.env_parser.PaDclInfoProdAttr.i_s][silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_INamedSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoProdAttr.i_td, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_ITyVarDcls), context.contextInheritedLazy(silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_IDclInfo))); } };
		// top.defs = [ prodOccursDef(top.grammarName, l.alocation, s.signature, t.defs) ]
		silver.definition.env.env_parser.PaDclInfoProdAttr.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IDclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PprodOccursDef.invoke(context.contextInheritedLazy(silver.definition.env.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_env_env_parser_IDclInfo), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoProdAttr.i_l, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_alocation__ON__silver_definition_env_env_parser_ILocation), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoProdAttr.i_s, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_signature__ON__silver_definition_env_env_parser_INamedSignature), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoProdAttr.i_t, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IDefs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaDclInfoProdAttr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaDclInfoProdAttr> {

		@Override
		public PaDclInfoProdAttr invoke(final Object[] children, final Object[] annotations) {
			return new PaDclInfoProdAttr(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9]);
		}
	};

}
