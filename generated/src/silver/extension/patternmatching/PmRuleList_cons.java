
package silver.extension.patternmatching;

// top::MRuleList ::= h::MatchRule '|' t::MRuleList 
public final class PmRuleList_cons extends silver.extension.patternmatching.NMRuleList {

	public static final int i_h = 0;
	public static final int i__G_1 = 1;
	public static final int i_t = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.NMatchRule.class,silver.extension.patternmatching.TVbar_kwd.class,silver.extension.patternmatching.NMRuleList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_mRuleList_cons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NMRuleList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NMRuleList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.extension.patternmatching.NMatchRule.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.extension.patternmatching.NMRuleList.num_inh_attrs];

	}

	public PmRuleList_cons(final Object c_h, final Object c__G_1, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child__G_1 = c__G_1;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.extension.patternmatching.NMatchRule getChild_h() {
		return (silver.extension.patternmatching.NMatchRule) (child_h = common.Util.demand(child_h));
	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TVbar_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TVbar_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_t;
	public final silver.extension.patternmatching.NMRuleList getChild_t() {
		return (silver.extension.patternmatching.NMRuleList) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i__G_1: return getChild__G_1();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i__G_1: return child__G_1;
			case i_t: return child_t;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:mRuleList_cons erroneously claimed to forward");
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
		return "silver:extension:patternmatching:mRuleList_cons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ " | " ++ t.pp
		silver.extension.patternmatching.PmRuleList_cons.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PmRuleList_cons.i_h).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MatchRule)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" | ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PmRuleList_cons.i_t).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MRuleList)))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.extension.patternmatching.PmRuleList_cons.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList] == null)
			silver.extension.patternmatching.PmRuleList_cons.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList);
		((common.CollectionAttribute)silver.extension.patternmatching.PmRuleList_cons.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmRuleList_cons.i_h, silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule), context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmRuleList_cons.i_t, silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList))); } });
		// top.matchRuleList = h.matchRuleList ++ t.matchRuleList
		silver.extension.patternmatching.PmRuleList_cons.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmRuleList_cons.i_h, silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MatchRule), context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmRuleList_cons.i_t, silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MRuleList))); } };

	}

	public static final common.NodeFactory<PmRuleList_cons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmRuleList_cons> {

		@Override
		public PmRuleList_cons invoke(final Object[] children, final Object[] annotations) {
			return new PmRuleList_cons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
