
package silver.extension.bidirtransform;

// rrl::RewriteRuleList ::= Vbar_kwd l::RewriteRule r::RewriteRuleList 
public final class PrewriteRuleCons extends silver.extension.bidirtransform.NRewriteRuleList {

	public static final int i__G_2 = 0;
	public static final int i_l = 1;
	public static final int i_r = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.TVbar_kwd.class,silver.extension.bidirtransform.NRewriteRule.class,silver.extension.bidirtransform.NRewriteRuleList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteRuleCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRuleList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRuleList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.extension.bidirtransform.NRewriteRuleList.num_inh_attrs];

	}

	public PrewriteRuleCons(final Object c__G_2, final Object c_l, final Object c_r, final Object a_core_location) {
		super(a_core_location);
		this.child__G_2 = c__G_2;
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child__G_2;
	public final silver.extension.patternmatching.TVbar_kwd getChild__G_2() {
		return (silver.extension.patternmatching.TVbar_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_l;
	public final silver.extension.bidirtransform.NRewriteRule getChild_l() {
		return (silver.extension.bidirtransform.NRewriteRule) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.extension.bidirtransform.NRewriteRuleList getChild_r() {
		return (silver.extension.bidirtransform.NRewriteRuleList) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_2: return getChild__G_2();
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_2: return child__G_2;
			case i_l: return child_l;
			case i_r: return child_r;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:rewriteRuleCons erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:rewriteRuleCons";
	}

	static void initProductionAttributeDefinitions() {
		// l.downSubst = rrl.downSubst
		silver.extension.bidirtransform.PrewriteRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleCons.i_l][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRuleList)); } };
		// r.downSubst = l.upSubst
		silver.extension.bidirtransform.PrewriteRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleCons.i_r][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleCons.i_l).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rrl.upSubst = r.upSubst
		silver.extension.bidirtransform.PrewriteRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleCons.i_r).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList)); } };
		// l.finalSubst = r.upSubst
		silver.extension.bidirtransform.PrewriteRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleCons.i_l][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleCons.i_r).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList)); } };
		// r.finalSubst = l.finalSubst
		silver.extension.bidirtransform.PrewriteRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleCons.i_r][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleCons.i_l).inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rrl.pp = "| " ++ l.pp ++ r.pp
		silver.extension.bidirtransform.PrewriteRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("| ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleCons.i_l).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRule)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleCons.i_r).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRuleList)))); } };
		// rrl.errors := l.errors ++ r.errors
		if(silver.extension.bidirtransform.PrewriteRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList] == null)
			silver.extension.bidirtransform.PrewriteRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PrewriteRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleCons.i_l, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleCons.i_r, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList))); } });
		// rrl.rewriteRules = r.rewriteRules ++ [ l ]
		silver.extension.bidirtransform.PrewriteRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rewriteRules__ON__silver_extension_bidirtransform_RewriteRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleCons.i_r, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rewriteRules__ON__silver_extension_bidirtransform_RewriteRuleList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRuleCons.i_l), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } };

	}

	public static final common.NodeFactory<PrewriteRuleCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteRuleCons> {

		@Override
		public PrewriteRuleCons invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteRuleCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
