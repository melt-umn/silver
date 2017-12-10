
package silver.definition.env;

// top::DclInfo ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type 
public final class PoccursDcl extends silver.definition.env.NDclInfo {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fnnt = 2;
	public static final int i_fnat = 3;
	public static final int i_ntty = 4;
	public static final int i_atty = 5;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.StringCatter.class,common.StringCatter.class,silver.definition.type.NType.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_occursDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ntty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_atty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PoccursDcl(final Object c_sg, final Object c_sl, final Object c_fnnt, final Object c_fnat, final Object c_ntty, final Object c_atty) {
		super();
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fnnt = c_fnnt;
		this.child_fnat = c_fnat;
		this.child_ntty = c_ntty;
		this.child_atty = c_atty;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_fnat;
	public final common.StringCatter getChild_fnat() {
		return (common.StringCatter) (child_fnat = common.Util.demand(child_fnat));
	}

	private Object child_ntty;
	public final silver.definition.type.NType getChild_ntty() {
		return (silver.definition.type.NType) (child_ntty = common.Util.demand(child_ntty));
	}

	private Object child_atty;
	public final silver.definition.type.NType getChild_atty() {
		return (silver.definition.type.NType) (child_atty = common.Util.demand(child_atty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fnnt: return getChild_fnnt();
			case i_fnat: return getChild_fnat();
			case i_ntty: return getChild_ntty();
			case i_atty: return getChild_atty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;
			case i_fnnt: return child_fnnt;
			case i_fnat: return child_fnat;
			case i_ntty: return child_ntty;
			case i_atty: return child_atty;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:occursDcl erroneously claimed to forward");
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
		return "silver:definition:env:occursDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_sg)); } };
		// top.sourceLocation = sl
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PoccursDcl.i_sl).undecorate(); } };
		// top.fullName = fnnt
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_fnnt)); } };
		// ntty.boundVariables = top.boundVariables ++ ntty.freeVariables
		silver.definition.env.PoccursDcl.childInheritedAttributes[silver.definition.env.PoccursDcl.i_ntty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.contextInheritedLazy(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo), context.childDecoratedSynthesizedLazy(silver.definition.env.PoccursDcl.i_ntty, silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type))); } };
		// atty.boundVariables = ntty.boundVariables
		silver.definition.env.PoccursDcl.childInheritedAttributes[silver.definition.env.PoccursDcl.i_atty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PoccursDcl.i_ntty).inherited(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type)); } };
		// top.unparse = "@(" ++ sl.unparse ++ ", '" ++ fnnt ++ "', '" ++ fnat ++ "', " ++ unparseTyVars(ntty.freeVariables, ntty.boundVariables) ++ ", " ++ ntty.unparse ++ ", " ++ atty.unparse ++ ")"
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("@(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PoccursDcl.i_sl).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_fnnt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_fnat)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.env.PunparseTyVars.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PoccursDcl.i_ntty, silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PoccursDcl.i_ntty).inherited(silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type)); } })), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PoccursDcl.i_ntty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PoccursDcl.i_atty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(")")))))))))))))); } };
		// subst = unifyDirectional(ntty, top.givenNonterminalType)
		silver.definition.env.PoccursDcl.localAttributes[silver.definition.env.Init.subst__ON__silver_definition_env_occursDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PunifyDirectional.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PoccursDcl.i_ntty)), context.contextInheritedLazy(silver.definition.env.Init.silver_definition_env_givenNonterminalType__ON__silver_definition_env_DclInfo))); } };
		// top.typerep = if subst.failure then freshenCompletely(atty) else performSubstitution(atty, subst)
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.definition.env.Init.subst__ON__silver_definition_env_occursDcl).synthesized(silver.definition.type.Init.silver_definition_type_failure__ON__silver_definition_type_Substitution)) ? ((silver.definition.type.NType)silver.definition.type.PfreshenCompletely.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PoccursDcl.i_atty)))) : ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PoccursDcl.i_atty)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.definition.env.Init.subst__ON__silver_definition_env_occursDcl))))); } };
		// top.attrOccurring = fnat
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_fnat)); } };

	}

	public static final common.NodeFactory<PoccursDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoccursDcl> {

		@Override
		public PoccursDcl invoke(final Object[] children, final Object[] annotations) {
			return new PoccursDcl(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
