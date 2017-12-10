
package silver.extension.bidirtransform;

// rule::RewriteRule ::= name::QName '::' t::TypeExpr '->' e::Expr 
public final class PrewriteRuleType extends silver.extension.bidirtransform.NRewriteRule {

	public static final int i_name = 0;
	public static final int i__G_3 = 1;
	public static final int i_t = 2;
	public static final int i__G_1 = 3;
	public static final int i_e = 4;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.extension.patternmatching.TArrow_kwd.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_rewriteRuleType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NRewriteRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_name] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PrewriteRuleType(final Object c_name, final Object c__G_3, final Object c_t, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_name = c_name;
		this.child__G_3 = c__G_3;
		this.child_t = c_t;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_name;
	public final silver.definition.core.NQName getChild_name() {
		return (silver.definition.core.NQName) (child_name = common.Util.demand(child_name));
	}

	private Object child__G_3;
	public final silver.definition.core.TColonColon_t getChild__G_3() {
		return (silver.definition.core.TColonColon_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_t;
	public final silver.definition.type.syntax.NTypeExpr getChild_t() {
		return (silver.definition.type.syntax.NTypeExpr) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TArrow_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TArrow_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_name: return getChild_name();
			case i__G_3: return getChild__G_3();
			case i_t: return getChild_t();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_name: return child_name;
			case i__G_3: return child__G_3;
			case i_t: return child_t;
			case i__G_1: return child__G_1;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return ((silver.extension.bidirtransform.NRewriteRule)new silver.extension.bidirtransform.PrewriteRule(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PrewriteRuleType.i_e)), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleType.i_name, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleType.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PrewriteRuleType.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.bidirtransform.NRewriteProduction)new silver.extension.bidirtransform.PemptyRewriteProduction(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NRewriteRule)context.undecorate()).getAnno_core_location()); } })); } }, false, false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NRewriteRule)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:rewriteRuleType";
	}

	static void initProductionAttributeDefinitions() {
		// rule.pp = name.pp ++ "::" ++ t.pp ++ "->" ++ e.pp
		silver.extension.bidirtransform.PrewriteRuleType.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleType.i_name).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleType.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("->")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleType.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))))); } };
		// e.downSubst = rule.downSubst
		silver.extension.bidirtransform.PrewriteRuleType.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleType.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// rule.upSubst = e.upSubst
		silver.extension.bidirtransform.PrewriteRuleType.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PrewriteRuleType.i_e).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// e.finalSubst = rule.upSubst
		silver.extension.bidirtransform.PrewriteRuleType.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleType.i_e][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule)); } };
		// e.defaultInheritedAnnos = []
		silver.extension.bidirtransform.PrewriteRuleType.childInheritedAttributes[silver.extension.bidirtransform.PrewriteRuleType.i_e][silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PrewriteRuleType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrewriteRuleType> {

		@Override
		public PrewriteRuleType invoke(final Object[] children, final Object[] annotations) {
			return new PrewriteRuleType(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
