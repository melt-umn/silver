
package silver.extension.patternmatching;

// top::MRuleList ::= m::MatchRule 
public final class PmRuleList_one extends silver.extension.patternmatching.NMRuleList {

	public static final int i_m = 0;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.NMatchRule.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_mRuleList_one;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NMRuleList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NMRuleList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_m] = new common.Lazy[silver.extension.patternmatching.NMatchRule.num_inh_attrs];

	}

	public PmRuleList_one(final Object c_m, final Object a_core_location) {
		super(a_core_location);
		this.child_m = c_m;

	}

	private Object child_m;
	public final silver.extension.patternmatching.NMatchRule getChild_m() {
		return (silver.extension.patternmatching.NMatchRule) (child_m = common.Util.demand(child_m));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_m: return getChild_m();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_m: return child_m;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:mRuleList_one erroneously claimed to forward");
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
		return "silver:extension:patternmatching:mRuleList_one";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = m.pp
		silver.extension.patternmatching.PmRuleList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PmRuleList_one.i_m).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MatchRule)); } };
		// top.errors := m.errors
		if(silver.extension.patternmatching.PmRuleList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList] == null)
			silver.extension.patternmatching.PmRuleList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList);
		((common.CollectionAttribute)silver.extension.patternmatching.PmRuleList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PmRuleList_one.i_m).synthesized(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule)); } });
		// top.matchRuleList = m.matchRuleList
		silver.extension.patternmatching.PmRuleList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PmRuleList_one.i_m).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MatchRule)); } };

	}

	public static final common.NodeFactory<PmRuleList_one> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmRuleList_one> {

		@Override
		public PmRuleList_one invoke(final Object[] children, final Object[] annotations) {
			return new PmRuleList_one(children[0], annotations[0]);
		}
	};

}
