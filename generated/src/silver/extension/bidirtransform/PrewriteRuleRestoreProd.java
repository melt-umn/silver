
package silver.extension.bidirtransform;

// rule::RewriteRule ::= prd::RewriteProduction '~~>' e::Expr 
public final class PrewriteRuleRestoreProd extends silver.extension.bidirtransform.NRewriteRule {

	public static final int i_prd = 0;
	public static final int i__G_1 = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NRewriteProduction.class,silver.extension.bidirtransform.TRestoreArrow_t.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteRuleRestoreProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_prd] = new common.Lazy[silver.extension.bidirtransform.NRewriteProduction.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PrewriteRuleRestoreProd(final Object c_prd, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_prd = c_prd;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_prd;
	public final silver.extension.bidirtransform.NRewriteProduction getChild_prd() {
		return (silver.extension.bidirtransform.NRewriteProduction) (child_prd = common.Util.demand(child_prd));
	}

	private Object child__G_1;
	public final silver.extension.bidirtransform.TRestoreArrow_t getChild__G_1() {
		return (silver.extension.bidirtransform.TRestoreArrow_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prd: return getChild_prd();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prd: return child_prd;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.extension.bidirtransform.NRewriteRule)new silver.extension.bidirtransform.PrewriteRule(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e)), (new common.StringCatter("")), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_prd, silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteProduction), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_prd)), true, true, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NRewriteRule)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:rewriteRuleRestoreProd";
	}

	static void initProductionAttributeDefinitions() {
		// rule.pp = prd.pp ++ "~~>" ++ e.pp
		silver.extension.bidirtransform.PrewriteRuleRestoreProd.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_prd).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProduction)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("~~>")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))); } };
		// e.downSubst = rule.downSubst
		silver.extension.bidirtransform.PrewriteRuleRestoreProd.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rule.upSubst = e.upSubst
		silver.extension.bidirtransform.PrewriteRuleRestoreProd.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// e.finalSubst = rule.upSubst
		silver.extension.bidirtransform.PrewriteRuleRestoreProd.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// e.defaultInheritedAnnos = []
		silver.extension.bidirtransform.PrewriteRuleRestoreProd.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleRestoreProd.i_e][silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PrewriteRuleRestoreProd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteRuleRestoreProd> {

		@Override
		public PrewriteRuleRestoreProd invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteRuleRestoreProd(children[0], children[1], children[2], annotations[0]);
		}
	};

}
