
package silver.extension.bidirtransform;

// rrl::RewriteRuleList ::= Vbar_kwd rule::RewriteRule 
public final class PrewriteRuleSingle extends silver.extension.bidirtransform.NRewriteRuleList {

	public static final int i__G_1 = 0;
	public static final int i_rule = 1;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.TVbar_kwd.class,silver.extension.bidirtransform.NRewriteRule.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteRuleSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRuleList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRuleList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rule] = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_inh_attrs];

	}

	public PrewriteRuleSingle(final Object c__G_1, final Object c_rule, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child_rule = c_rule;

	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TVbar_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TVbar_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_rule;
	public final silver.extension.bidirtransform.NRewriteRule getChild_rule() {
		return (silver.extension.bidirtransform.NRewriteRule) (child_rule = common.Util.demand(child_rule));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_rule: return getChild_rule();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_rule: return child_rule;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:rewriteRuleSingle erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:rewriteRuleSingle";
	}

	static void initProductionAttributeDefinitions() {
		// rule.downSubst = rrl.downSubst
		silver.extension.bidirtransform.PrewriteRuleSingle.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleSingle.i_rule][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRuleList)); } };
		// rrl.upSubst = rule.upSubst
		silver.extension.bidirtransform.PrewriteRuleSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleSingle.i_rule).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rule.finalSubst = rrl.upSubst
		silver.extension.bidirtransform.PrewriteRuleSingle.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleSingle.i_rule][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList)); } };
		// rrl.pp = "| " ++ rule.pp
		silver.extension.bidirtransform.PrewriteRuleSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("| ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleSingle.i_rule).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRule))); } };
		// rrl.rewriteRules = [ rule ]
		silver.extension.bidirtransform.PrewriteRuleSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rewriteRules__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRuleSingle.i_rule), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// rrl.errors := rule.errors
		if(silver.extension.bidirtransform.PrewriteRuleSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList] == null)
			silver.extension.bidirtransform.PrewriteRuleSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PrewriteRuleSingle.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleSingle.i_rule).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule)); } });

	}

	public static final common.NodeFactory<PrewriteRuleSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteRuleSingle> {

		@Override
		public PrewriteRuleSingle invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteRuleSingle(children[0], children[1], annotations[0]);
		}
	};

}
