
package silver.extension.bidirtransform;

// tr::TransformRule ::= l::ProductionDef '->' r::Expr 
public final class PtransformRule extends silver.extension.bidirtransform.NTransformRule {

	public static final int i_l = 0;
	public static final int i__G_1 = 1;
	public static final int i_r = 2;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.NProductionDef.class,silver.extension.patternmatching.TArrow_kwd.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_transformRule;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.extension.bidirtransform.NProductionDef.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PtransformRule(final Object c_l, final Object c__G_1, final Object c_r, final Object a_core_location) {
		super(a_core_location);
		this.child_l = c_l;
		this.child__G_1 = c__G_1;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver.extension.bidirtransform.NProductionDef getChild_l() {
		return (silver.extension.bidirtransform.NProductionDef) (child_l = common.Util.demand(child_l));
	}

	private Object child__G_1;
	public final silver.extension.patternmatching.TArrow_kwd getChild__G_1() {
		return (silver.extension.patternmatching.TArrow_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_r;
	public final silver.definition.core.NExpr getChild_r() {
		return (silver.definition.core.NExpr) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i__G_1: return getChild__G_1();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i__G_1: return child__G_1;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:transformRule erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:transformRule";
	}

	static void initProductionAttributeDefinitions() {
		// r.downSubst = tr.downSubst
		silver.extension.bidirtransform.PtransformRule.childInheritedAttributes[silver.extension.bidirtransform.PtransformRule.i_r][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRule)); } };
		// tr.upSubst = r.upSubst
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_r).synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr)); } };
		// r.finalSubst = tr.upSubst
		silver.extension.bidirtransform.PtransformRule.childInheritedAttributes[silver.extension.bidirtransform.PtransformRule.i_r][silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRule)); } };
		// r.defaultInheritedAnnos = []
		silver.extension.bidirtransform.PtransformRule.childInheritedAttributes[silver.extension.bidirtransform.PtransformRule.i_r][silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// tr.pp = l.pp ++ "->" ++ r.pp
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_l).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_ProductionDef)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("->")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_r).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr)))); } };
		// tr.trIndex = genInt()
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_trIndex__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PgenInt.invoke()); } };
		// tr.namedSig = l.namedSig
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_l).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef)); } };
		// tr.matchProd = l.matchProd
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_l).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_ProductionDef)); } };
		// tr.asExpr = l.asExpr
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_asExpr__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_l).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_asExpr__ON__silver_extension_bidirtransform_ProductionDef)); } };
		// tr.errors := l.errors
		if(silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule] == null)
			silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule);
		((common.CollectionAttribute)silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_l).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef)); } });
		// tr.outputStmt = (\ e::Expr  -> case e of application(_, _, aexpr, _, _, _) -> fillExprPattern(r, aexpr, l.patternList.rawPatternList,location=e.location) end)
		silver.extension.bidirtransform.PtransformRule.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_TransformRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<silver.definition.core.NExpr>() {
  public final silver.definition.core.NExpr invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_11709_e = args[0];

    return new common.PatternLazy<common.DecoratedNode, silver.definition.core.NExpr>() { public final silver.definition.core.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Papplication) { final common.Thunk<Object> __SV_LOCAL___pv11711___sv_tmp_pv_11712 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv11713___sv_tmp_pv_11714 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TLParen_t)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv11715___sv_pv_11710_aexpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv11716___sv_tmp_pv_11717 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TComma_t)scrutinee.childAsIs(3); } };
final common.Thunk<Object> __SV_LOCAL___pv11718___sv_tmp_pv_11719 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
final common.Thunk<Object> __SV_LOCAL___pv11720___sv_tmp_pv_11721 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (silver.definition.core.TRParen_t)scrutinee.childAsIs(5); } };
 return (silver.definition.core.NExpr)((silver.definition.core.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_11722_aexpr = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv11715___sv_pv_11710_aexpr.eval())); } };
return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfillExprPattern(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PtransformRule.i_r)), common.Thunk.transformUndecorate(__SV_LOCAL_11722_aexpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.extension.patternmatching.NPatternList)context.childDecorated(silver.extension.bidirtransform.PtransformRule.i_l).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_patternList__ON__silver_extension_bidirtransform_ProductionDef)).decorate(context, (common.Lazy[])null).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_11709_e)).getAnno_core_location()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.core.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:extension:bidirtransform 'TransformRule.sv', 60, 8, 64, 11, 1986, 2258\n"))));}}.eval(context, (common.DecoratedNode)((silver.definition.core.NExpr)common.Util.demand(__SV_LAMBDA_PARAM_11709_e)).decorate(context, (common.Lazy[])null));
  }
}); } };

	}

	public static final common.NodeFactory<PtransformRule> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtransformRule> {

		@Override
		public PtransformRule invoke(final Object[] children, final Object[] annotations) {
			return new PtransformRule(children[0], children[1], children[2], annotations[0]);
		}
	};

}
