
package silver.extension.patternmatching;

// top::MatchRule ::= pt::PatternList '->' e::Expr 
public final class PmatchRule_c extends silver.extension.patternmatching.NMatchRule {

	public static final int i_pt = 0;
	public static final int i__G_1 = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.NPatternList.class,silver.extension.patternmatching.TArrow_kwd.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_matchRule_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NMatchRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NMatchRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pt] = new common.Lazy[silver.extension.patternmatching.NPatternList.num_inh_attrs];
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmatchRule_c(final Object c_pt, final Object c__G_1, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_pt = c_pt;
		this.child__G_1 = c__G_1;
		this.child_e = c_e;

	}

	private Object child_pt;
	public final silver.extension.patternmatching.NPatternList getChild_pt() {
		return (silver.extension.patternmatching.NPatternList) (child_pt = common.Util.demand(child_pt));
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
			case i_pt: return getChild_pt();
			case i__G_1: return getChild__G_1();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pt: return child_pt;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:matchRule_c erroneously claimed to forward");
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
		return "silver:extension:patternmatching:matchRule_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pt.pp ++ " -> " ++ e.pp
		silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MatchRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PmatchRule_c.i_pt).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" -> ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PmatchRule_c.i_e).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))); } };
		// top.errors := pt.errors
		if(silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule] == null)
			silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule);
		((common.CollectionAttribute)silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PmatchRule_c.i_pt).synthesized(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList)); } });
		// top.errors <- if length(pt.patternList) == top.matchRulePatternSize then [] else [ err(pt.location, "case expression matching against " ++ toString(top.matchRulePatternSize) ++ " values, but this rule has " ++ toString(length(pt.patternList)) ++ " patterns") ]
		if(silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule] == null)
			silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule] = new silver.definition.core.CAerrors(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule);
		((common.CollectionAttribute)silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmatchRule_c.i_pt, silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList))).equals(((Integer)context.inherited(silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MatchRule))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.childDecorated(silver.extension.patternmatching.PmatchRule_c.i_pt).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("case expression matching against ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.inherited(silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MatchRule)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" values, but this rule has ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmatchRule_c.i_pt, silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList))))), (common.StringCatter)(new common.StringCatter(" patterns")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.matchRuleList = [ matchRule(pt.patternList, e,location=top.location) ]
		silver.extension.patternmatching.PmatchRule_c.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MatchRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NAbstractMatchRule)new silver.extension.patternmatching.PmatchRule(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PmatchRule_c.i_pt, silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmatchRule_c.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NMatchRule)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PmatchRule_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmatchRule_c> {

		@Override
		public PmatchRule_c invoke(final Object[] children, final Object[] annotations) {
			return new PmatchRule_c(children[0], children[1], children[2], annotations[0]);
		}
	};

}
