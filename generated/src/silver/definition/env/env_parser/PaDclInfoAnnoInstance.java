
package silver.definition.env.env_parser;

// top::IDclInfo ::= 'anno@' '(' l::ILocation ',' fnnt::IName ',' fnat::IName ',' td::ITyVarDcls ',' ntt::ITypeRep ',' att::ITypeRep ')' 
public final class PaDclInfoAnnoInstance extends silver.definition.env.env_parser.NIDclInfo {

	public static final int i__G_13 = 0;
	public static final int i__G_12 = 1;
	public static final int i_l = 2;
	public static final int i__G_10 = 3;
	public static final int i_fnnt = 4;
	public static final int i__G_8 = 5;
	public static final int i_fnat = 6;
	public static final int i__G_6 = 7;
	public static final int i_td = 8;
	public static final int i__G_4 = 9;
	public static final int i_ntt = 10;
	public static final int i__G_2 = 11;
	public static final int i_att = 12;
	public static final int i__G_0 = 13;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.TAnnoAt_t.class,silver.definition.env.env_parser.TLParent_t.class,silver.definition.env.env_parser.NILocation.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITyVarDcls.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITypeRep.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.NITypeRep.class,silver.definition.env.env_parser.TRParent_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aDclInfoAnnoInstance;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NIDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NIDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[14][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.env.env_parser.NILocation.num_inh_attrs];
	childInheritedAttributes[i_fnnt] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_fnat] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];
	childInheritedAttributes[i_td] = new common.Lazy[silver.definition.env.env_parser.NITyVarDcls.num_inh_attrs];
	childInheritedAttributes[i_ntt] = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];
	childInheritedAttributes[i_att] = new common.Lazy[silver.definition.env.env_parser.NITypeRep.num_inh_attrs];

	}

	public PaDclInfoAnnoInstance(final Object c__G_13, final Object c__G_12, final Object c_l, final Object c__G_10, final Object c_fnnt, final Object c__G_8, final Object c_fnat, final Object c__G_6, final Object c_td, final Object c__G_4, final Object c_ntt, final Object c__G_2, final Object c_att, final Object c__G_0) {
		super();
		this.child__G_13 = c__G_13;
		this.child__G_12 = c__G_12;
		this.child_l = c_l;
		this.child__G_10 = c__G_10;
		this.child_fnnt = c_fnnt;
		this.child__G_8 = c__G_8;
		this.child_fnat = c_fnat;
		this.child__G_6 = c__G_6;
		this.child_td = c_td;
		this.child__G_4 = c__G_4;
		this.child_ntt = c_ntt;
		this.child__G_2 = c__G_2;
		this.child_att = c_att;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_13;
	public final silver.definition.env.env_parser.TAnnoAt_t getChild__G_13() {
		return (silver.definition.env.env_parser.TAnnoAt_t) (child__G_13 = common.Util.demand(child__G_13));
	}

	private Object child__G_12;
	public final silver.definition.env.env_parser.TLParent_t getChild__G_12() {
		return (silver.definition.env.env_parser.TLParent_t) (child__G_12 = common.Util.demand(child__G_12));
	}

	private Object child_l;
	public final silver.definition.env.env_parser.NILocation getChild_l() {
		return (silver.definition.env.env_parser.NILocation) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_10;
	public final silver.definition.env.env_parser.TComma_t getChild__G_10() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_10 = common.Util.demand(child__G_10));
	}

	private Object child_fnnt;
	public final silver.definition.env.env_parser.NIName getChild_fnnt() {
		return (silver.definition.env.env_parser.NIName) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child__G_8;
	public final silver.definition.env.env_parser.TComma_t getChild__G_8() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_8 = common.Util.demand(child__G_8));
	}

	private Object child_fnat;
	public final silver.definition.env.env_parser.NIName getChild_fnat() {
		return (silver.definition.env.env_parser.NIName) (child_fnat = common.Util.demand(child_fnat));
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

	private Object child_ntt;
	public final silver.definition.env.env_parser.NITypeRep getChild_ntt() {
		return (silver.definition.env.env_parser.NITypeRep) (child_ntt = common.Util.demand(child_ntt));
	}

	private Object child__G_2;
	public final silver.definition.env.env_parser.TComma_t getChild__G_2() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_att;
	public final silver.definition.env.env_parser.NITypeRep getChild_att() {
		return (silver.definition.env.env_parser.NITypeRep) (child_att = common.Util.demand(child_att));
	}

	private Object child__G_0;
	public final silver.definition.env.env_parser.TRParent_t getChild__G_0() {
		return (silver.definition.env.env_parser.TRParent_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_13: return getChild__G_13();
			case i__G_12: return getChild__G_12();
			case i_l: return getChild_l();
			case i__G_10: return getChild__G_10();
			case i_fnnt: return getChild_fnnt();
			case i__G_8: return getChild__G_8();
			case i_fnat: return getChild_fnat();
			case i__G_6: return getChild__G_6();
			case i_td: return getChild_td();
			case i__G_4: return getChild__G_4();
			case i_ntt: return getChild_ntt();
			case i__G_2: return getChild__G_2();
			case i_att: return getChild_att();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_13: return child__G_13;
			case i__G_12: return child__G_12;
			case i_l: return child_l;
			case i__G_10: return child__G_10;
			case i_fnnt: return child_fnnt;
			case i__G_8: return child__G_8;
			case i_fnat: return child_fnat;
			case i__G_6: return child__G_6;
			case i_td: return child_td;
			case i__G_4: return child__G_4;
			case i_ntt: return child_ntt;
			case i__G_2: return child__G_2;
			case i_att: return child_att;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 14;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aDclInfoAnnoInstance erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aDclInfoAnnoInstance";
	}

	static void initProductionAttributeDefinitions() {
		// ntt.env = newScopeEnv(td.defs, top.env)
		silver.definition.env.env_parser.PaDclInfoAnnoInstance.childInheritedAttributes[silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_ntt][silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_ITypeRep] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PnewScopeEnv.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_td, silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_ITyVarDcls), context.contextInheritedLazy(silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_IDclInfo))); } };
		// att.env = ntt.env
		silver.definition.env.env_parser.PaDclInfoAnnoInstance.childInheritedAttributes[silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_att][silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_ITypeRep] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_ntt).inherited(silver.definition.env.env_parser.Init.silver_definition_env_env__ON__silver_definition_env_env_parser_ITypeRep)); } };
		// fresh = freshTyVars(length(td.tyvars))
		silver.definition.env.env_parser.PaDclInfoAnnoInstance.localAttributes[silver.definition.env.env_parser.Init.fresh__ON__silver_definition_env_env_parser_aDclInfoAnnoInstance] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PfreshTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_td, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_tyvars__ON__silver_definition_env_env_parser_ITyVarDcls))); } })); } };
		// top.defs = [ annoInstanceDef(top.grammarName, l.alocation, fnnt.aname, fnat.aname, freshenTypeWith(ntt.typerep, td.tyvars, fresh), freshenTypeWith(att.typerep, td.tyvars, fresh)) ]
		silver.definition.env.env_parser.PaDclInfoAnnoInstance.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_defs__ON__silver_definition_env_env_parser_IDclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PannoInstanceDef.invoke(context.contextInheritedLazy(silver.definition.env.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_env_env_parser_IDclInfo), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_l, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_alocation__ON__silver_definition_env_env_parser_ILocation), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_fnnt, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_fnat, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshenTypeWith.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_ntt, silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_td, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_tyvars__ON__silver_definition_env_env_parser_ITyVarDcls), context.localAsIsLazy(silver.definition.env.env_parser.Init.fresh__ON__silver_definition_env_env_parser_aDclInfoAnnoInstance))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshenTypeWith.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_att, silver.definition.env.env_parser.Init.silver_definition_env_typerep__ON__silver_definition_env_env_parser_ITypeRep), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaDclInfoAnnoInstance.i_td, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_tyvars__ON__silver_definition_env_env_parser_ITyVarDcls), context.localAsIsLazy(silver.definition.env.env_parser.Init.fresh__ON__silver_definition_env_env_parser_aDclInfoAnnoInstance))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PaDclInfoAnnoInstance> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaDclInfoAnnoInstance> {

		@Override
		public PaDclInfoAnnoInstance invoke(final Object[] children, final Object[] annotations) {
			return new PaDclInfoAnnoInstance(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], children[10], children[11], children[12], children[13]);
		}
	};

}
