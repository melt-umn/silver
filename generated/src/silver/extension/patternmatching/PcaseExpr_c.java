
package silver.extension.patternmatching;

// top::Expr ::= 'case' es::Exprs 'of' Opt_Vbar_t ml::MRuleList 'end' 
public final class PcaseExpr_c extends silver.definition.core.NExpr {

	public static final int i__G_5 = 0;
	public static final int i_es = 1;
	public static final int i__G_3 = 2;
	public static final int i__G_2 = 3;
	public static final int i_ml = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.extension.patternmatching.TCase_kwd.class,silver.definition.core.NExprs.class,silver.extension.patternmatching.TOf_kwd.class,silver.extension.patternmatching.TOpt_Vbar_t.class,silver.extension.patternmatching.NMRuleList.class,silver.modification.let_fix.TEnd_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_caseExpr_c;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_es] = new common.Lazy[silver.definition.core.NExprs.num_inh_attrs];
	childInheritedAttributes[i_ml] = new common.Lazy[silver.extension.patternmatching.NMRuleList.num_inh_attrs];

	}

	public PcaseExpr_c(final Object c__G_5, final Object c_es, final Object c__G_3, final Object c__G_2, final Object c_ml, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_es = c_es;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_ml = c_ml;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.extension.patternmatching.TCase_kwd getChild__G_5() {
		return (silver.extension.patternmatching.TCase_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_es;
	public final silver.definition.core.NExprs getChild_es() {
		return (silver.definition.core.NExprs) (child_es = common.Util.demand(child_es));
	}

	private Object child__G_3;
	public final silver.extension.patternmatching.TOf_kwd getChild__G_3() {
		return (silver.extension.patternmatching.TOf_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.extension.patternmatching.TOpt_Vbar_t getChild__G_2() {
		return (silver.extension.patternmatching.TOpt_Vbar_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ml;
	public final silver.extension.patternmatching.NMRuleList getChild_ml() {
		return (silver.extension.patternmatching.NMRuleList) (child_ml = common.Util.demand(child_ml));
	}

	private Object child__G_0;
	public final silver.modification.let_fix.TEnd_kwd getChild__G_0() {
		return (silver.modification.let_fix.TEnd_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_es: return getChild_es();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_ml: return getChild_ml();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_es: return child_es;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_ml: return child_ml;
			case i__G_0: return child__G_0;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PcaseExpr_c.i_es, silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_Exprs), context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PcaseExpr_c.i_ml, silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MRuleList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.definition.core.PmkStrFunctionInvocation.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("core:error")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.definition.core.PstringConst(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TString_t(new common.StringCatter((common.StringCatter)(new common.StringCatter("\"Error: pattern match failed at ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.inherited(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_Expr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location)), (common.StringCatter)(new common.StringCatter("\\n\"")))))), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:patternmatching:caseExpr_c";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "case " ++ es.pp ++ " of " ++ ml.pp ++ " end"
		silver.extension.patternmatching.PcaseExpr_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("case ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PcaseExpr_c.i_es).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Exprs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" of ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.patternmatching.PcaseExpr_c.i_ml).synthesized(silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MRuleList)), (common.StringCatter)(new common.StringCatter(" end")))))); } };
		// ml.matchRulePatternSize = length(es.rawExprs)
		silver.extension.patternmatching.PcaseExpr_c.childInheritedAttributes[silver.extension.patternmatching.PcaseExpr_c.i_ml][silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PcaseExpr_c.i_es, silver.definition.core.Init.silver_definition_core_rawExprs__ON__silver_definition_core_Exprs))); } };
		// top.errors <- ml.errors
		if(silver.extension.patternmatching.PcaseExpr_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.extension.patternmatching.PcaseExpr_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.extension.patternmatching.PcaseExpr_c.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.patternmatching.PcaseExpr_c.i_ml).synthesized(silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList)); } });

	}

	public static final common.NodeFactory<PcaseExpr_c> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcaseExpr_c> {

		@Override
		public PcaseExpr_c invoke(final Object[] children, final Object[] annotations) {
			return new PcaseExpr_c(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
