
package silver.extension.bidirtransform;

// trl::TransformRuleList ::= Vbar_kwd l::TransformRule r::TransformRuleList 
public final class PtransformRuleCons extends silver.extension.bidirtransform.NTransformRuleList {

	public static final int i__G_2 = 0;
	public static final int i_l = 1;
	public static final int i_r = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.TVbar_kwd.class,silver.extension.bidirtransform.NTransformRule.class,silver.extension.bidirtransform.NTransformRuleList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_transformRuleCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformRuleList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformRuleList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.extension.bidirtransform.NTransformRule.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.extension.bidirtransform.NTransformRuleList.num_inh_attrs];

	}

	public PtransformRuleCons(final Object c__G_2, final Object c_l, final Object c_r, final Object a_core_location) {
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
	public final silver.extension.bidirtransform.NTransformRule getChild_l() {
		return (silver.extension.bidirtransform.NTransformRule) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.extension.bidirtransform.NTransformRuleList getChild_r() {
		return (silver.extension.bidirtransform.NTransformRuleList) (child_r = common.Util.demand(child_r));
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:transformRuleCons erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:transformRuleCons";
	}

	static void initProductionAttributeDefinitions() {
		// l.config = trl.config
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_l][silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// r.config = trl.config
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_r][silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// l.env = trl.env
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_l][silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// r.env = trl.env
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_r][silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.inherited(silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// l.downSubst = trl.downSubst
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_l][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// r.downSubst = l.upSubst
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_r][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PtransformRuleCons.i_l).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRule)); } };
		// l.finalSubst = r.upSubst
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_l][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PtransformRuleCons.i_r).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// r.finalSubst = l.finalSubst
		silver.extension.bidirtransform.PtransformRuleCons.childInheritedAttributes[silver.extension.bidirtransform.PtransformRuleCons.i_r][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PtransformRuleCons.i_l).inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRule)); } };
		// trl.pp = "|" ++ l.pp ++ r.pp
		silver.extension.bidirtransform.PtransformRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("|")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformRuleCons.i_l).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRule)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformRuleCons.i_r).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRuleList)))); } };
		// trl.errors := l.errors ++ r.errors
		if(silver.extension.bidirtransform.PtransformRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList] == null)
			silver.extension.bidirtransform.PtransformRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList);
		((common.CollectionAttribute)silver.extension.bidirtransform.PtransformRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformRuleCons.i_l, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformRuleCons.i_r, silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList))); } });
		// trl.transformRules = [ l ] ++ r.transformRules
		silver.extension.bidirtransform.PtransformRuleCons.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PtransformRuleCons.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformRuleCons.i_r, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformRuleList))); } };

	}

	public static final common.NodeFactory<PtransformRuleCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtransformRuleCons> {

		@Override
		public PtransformRuleCons invoke(final Object[] children, final Object[] annotations) {
			return new PtransformRuleCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
