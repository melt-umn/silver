package silver.extension.patternmatching;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.modification.primitivepattern.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.modification.let_fix.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.extension.patternmatching.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.modification.primitivepattern.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.modification.let_fix.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.extension.list.Init.init();
		silver.extension.patternmatching.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.modification.primitivepattern.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.modification.let_fix.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.extension.patternmatching.Init.postInit();


		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PprodAppPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PwildcPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PvarPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PintPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PstrPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PtruePattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PfalsePattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PnilListPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PconsListPattern.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPattern.decorators, PlistPattern.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PcaseExpr_c.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PcaseExpr.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NMRuleList.decorators, PmRuleList_one.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NMRuleList.decorators, PmRuleList_cons.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NMatchRule.decorators, PmatchRule_c.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NAbstractMatchRule.decorators, PmatchRule.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPatternList.decorators, PpatternList_one.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPatternList.decorators, PpatternList_more.class);
		common.Decorator.applyDecorators(silver.extension.patternmatching.NPatternList.decorators, PpatternList_nil.class);
	}

	private static void setupInheritedAttributes(){
		silver.extension.patternmatching.NPattern.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_config__ON__silver_extension_patternmatching_Pattern] = "silver:definition:env:config";
		silver.extension.patternmatching.NPattern.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.patternmatching.NPattern.occurs_syn[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern] = "silver:definition:env:pp";
		silver.extension.patternmatching.NPattern.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_env__ON__silver_extension_patternmatching_Pattern] = "silver:definition:env:env";
		silver.extension.patternmatching.NPattern.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.patternmatching.NPattern.occurs_syn[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern] = "silver:definition:core:errors";
		silver.extension.patternmatching.NPattern.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternIsVariable__ON__silver_extension_patternmatching_Pattern] = "silver:extension:patternmatching:patternIsVariable";
		silver.extension.patternmatching.NPattern.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternVariableName__ON__silver_extension_patternmatching_Pattern] = "silver:extension:patternmatching:patternVariableName";
		silver.extension.patternmatching.NPattern.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern] = "silver:extension:patternmatching:patternSubPatternList";
		silver.extension.patternmatching.NPattern.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern] = "silver:extension:patternmatching:patternSortKey";
		silver.extension.patternmatching.PprodAppPattern.occurs_local[silver.extension.patternmatching.Init.parms__ON__silver_extension_patternmatching_prodAppPattern] = "silver:extension:patternmatching:prodAppPattern:local:parms";
		silver.extension.patternmatching.NPatternList.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList] = "silver:extension:patternmatching:asListPattern";
		silver.extension.patternmatching.NMRuleList.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_config__ON__silver_extension_patternmatching_MRuleList] = "silver:definition:env:config";
		silver.extension.patternmatching.NMRuleList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.patternmatching.NMRuleList.occurs_syn[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MRuleList] = "silver:definition:env:pp";
		silver.extension.patternmatching.NMRuleList.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_env__ON__silver_extension_patternmatching_MRuleList] = "silver:definition:env:env";
		silver.extension.patternmatching.NMRuleList.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.patternmatching.NMRuleList.occurs_syn[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList] = "silver:definition:core:errors";
		silver.extension.patternmatching.NMRuleList.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MRuleList] = "silver:extension:patternmatching:matchRuleList";
		silver.extension.patternmatching.NMRuleList.occurs_inh[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MRuleList] = "silver:extension:patternmatching:matchRulePatternSize";
		silver.extension.patternmatching.NMRuleList.decorators.add(silver.extension.patternmatching.DmatchRulePatternSize.singleton);
		silver.extension.patternmatching.NMatchRule.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_config__ON__silver_extension_patternmatching_MatchRule] = "silver:definition:env:config";
		silver.extension.patternmatching.NMatchRule.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.patternmatching.NMatchRule.occurs_syn[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_MatchRule] = "silver:definition:env:pp";
		silver.extension.patternmatching.NMatchRule.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_env__ON__silver_extension_patternmatching_MatchRule] = "silver:definition:env:env";
		silver.extension.patternmatching.NMatchRule.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.patternmatching.NMatchRule.occurs_syn[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule] = "silver:definition:core:errors";
		silver.extension.patternmatching.NMatchRule.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MatchRule] = "silver:extension:patternmatching:matchRuleList";
		silver.extension.patternmatching.NMatchRule.occurs_inh[silver.extension.patternmatching.Init.silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MatchRule] = "silver:extension:patternmatching:matchRulePatternSize";
		silver.extension.patternmatching.NMatchRule.decorators.add(silver.extension.patternmatching.DmatchRulePatternSize.singleton);
		silver.extension.patternmatching.NAbstractMatchRule.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule] = "silver:extension:patternmatching:headPattern";
		silver.extension.patternmatching.NAbstractMatchRule.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_isVarMatchRule__ON__silver_extension_patternmatching_AbstractMatchRule] = "silver:extension:patternmatching:isVarMatchRule";
		silver.extension.patternmatching.NAbstractMatchRule.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_expandHeadPattern__ON__silver_extension_patternmatching_AbstractMatchRule] = "silver:extension:patternmatching:expandHeadPattern";
		silver.extension.patternmatching.NPatternList.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_config__ON__silver_extension_patternmatching_PatternList] = "silver:definition:env:config";
		silver.extension.patternmatching.NPatternList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.patternmatching.NPatternList.occurs_syn[silver.extension.patternmatching.Init.silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList] = "silver:definition:env:pp";
		silver.extension.patternmatching.NPatternList.occurs_syn[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList] = "silver:extension:patternmatching:patternList";
		silver.extension.patternmatching.NPatternList.occurs_inh[silver.extension.patternmatching.Init.silver_definition_env_env__ON__silver_extension_patternmatching_PatternList] = "silver:definition:env:env";
		silver.extension.patternmatching.NPatternList.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.patternmatching.NPatternList.occurs_syn[silver.extension.patternmatching.Init.silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList] = "silver:definition:core:errors";
		//	local attribute partMRs::Pair<[AbstractMatchRule] [AbstractMatchRule]>;
		silver.extension.patternmatching.PcaseExpr.localInheritedAttributes[silver.extension.patternmatching.Init.partMRs__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.partMRs__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:partMRs";
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.varRules__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:varRules";
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.prodRules__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:prodRules";
		//	local attribute allConCase::Expr;
		silver.extension.patternmatching.PcaseExpr.localInheritedAttributes[silver.extension.patternmatching.Init.allConCase__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.allConCase__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:allConCase";
		//	local attribute allVarCase::Expr;
		silver.extension.patternmatching.PcaseExpr.localInheritedAttributes[silver.extension.patternmatching.Init.allVarCase__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.allVarCase__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:allVarCase";
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.freshFailName__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:freshFailName";
		//	local attribute mixedCase::Expr;
		silver.extension.patternmatching.PcaseExpr.localInheritedAttributes[silver.extension.patternmatching.Init.mixedCase__ON__silver_extension_patternmatching_caseExpr] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
		silver.extension.patternmatching.PcaseExpr.occurs_local[silver.extension.patternmatching.Init.mixedCase__ON__silver_extension_patternmatching_caseExpr] = "silver:extension:patternmatching:caseExpr:local:mixedCase";
		silver.extension.patternmatching.PpatternListVars.occurs_local[silver.extension.patternmatching.Init.n__ON__silver_extension_patternmatching_patternListVars] = "silver:extension:patternmatching:patternListVars:local:n";
		silver.extension.patternmatching.PallConCaseTransform.occurs_local[silver.extension.patternmatching.Init.names__ON__silver_extension_patternmatching_allConCaseTransform] = "silver:extension:patternmatching:allConCaseTransform:local:names";
		//	local attribute subcase::Expr;
		silver.extension.patternmatching.PallConCaseTransform.localInheritedAttributes[silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
		silver.extension.patternmatching.PallConCaseTransform.occurs_local[silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform] = "silver:extension:patternmatching:allConCaseTransform:local:subcase";
		//	local attribute l::Location;
		silver.extension.patternmatching.PallConCaseTransform.localInheritedAttributes[silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform] = new common.Lazy[core.NLocation.num_inh_attrs];
		silver.extension.patternmatching.PallConCaseTransform.occurs_local[silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform] = "silver:extension:patternmatching:allConCaseTransform:local:l";
		//	local attribute et::Type;
		silver.extension.patternmatching.PensureDecoratedExpr.localInheritedAttributes[silver.extension.patternmatching.Init.et__ON__silver_extension_patternmatching_ensureDecoratedExpr] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.extension.patternmatching.PensureDecoratedExpr.occurs_local[silver.extension.patternmatching.Init.et__ON__silver_extension_patternmatching_ensureDecoratedExpr] = "silver:extension:patternmatching:ensureDecoratedExpr:local:et";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.patternmatching.PprodAppPattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PwildcPattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PvarPattern.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for Pattern
		// top.patternIsVariable = false
		silver.extension.patternmatching.NPattern.defaultSynthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternIsVariable__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.patternVariableName = nothing()
		silver.extension.patternmatching.NPattern.defaultSynthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_patternVariableName__ON__silver_extension_patternmatching_Pattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		silver.extension.patternmatching.PintPattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PstrPattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PtruePattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PfalsePattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PnilListPattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PconsListPattern.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PlistPattern.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION patternList_one top ::= p::Pattern 
		// top.asListPattern = consListPattern(p, '::', nilListPattern('[', ']',location=top.location),location=top.location)
		silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)new silver.extension.patternmatching.PconsListPattern(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PpatternList_one.i_p)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)new silver.extension.patternmatching.PnilListPattern(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TLSqr_t((new common.StringCatter("[")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TRSqr_t((new common.StringCatter("]")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.undecorate()).getAnno_core_location()); } })); } };
		//ASPECT PRODUCTION patternList_more top ::= p::Pattern ',' ps1::PatternList 
		// top.asListPattern = consListPattern(p, '::', ps1.asListPattern,location=top.location)
		silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)new silver.extension.patternmatching.PconsListPattern(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PpatternList_more.i_p)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TColonColon_t((new common.StringCatter("::")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PpatternList_more.i_ps1, silver.extension.patternmatching.Init.silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.undecorate()).getAnno_core_location()); } })); } };
		//ASPECT PRODUCTION patternList_nil top ::= 
		// top.asListPattern = nilListPattern('[', ']',location=top.location)
		silver.extension.patternmatching.PpatternList_nil.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)new silver.extension.patternmatching.PnilListPattern(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TLSqr_t((new common.StringCatter("[")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.list.TRSqr_t((new common.StringCatter("]")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPatternList)context.undecorate()).getAnno_core_location()); } })); } };
		silver.extension.patternmatching.PcaseExpr_c.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PcaseExpr.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PmRuleList_one.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PmRuleList_cons.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PmatchRule_c.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PmatchRule.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PpatternList_one.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PpatternList_more.initProductionAttributeDefinitions();
		silver.extension.patternmatching.PpatternList_nil.initProductionAttributeDefinitions();
		//FUNCTION patternListVars Name ::= p::Decorated Pattern 
		// n = case p of varPattern(pvn) -> "__sv_pv_" ++ toString(genInt()) ++ "_" ++ pvn.name | h -> "__sv_tmp_pv_" ++ toString(genInt()) end
		silver.extension.patternmatching.PpatternListVars.localAttributes[silver.extension.patternmatching.Init.n__ON__silver_extension_patternmatching_patternListVars] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7337___fail_7338 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7336_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childAsIs(silver.extension.patternmatching.PpatternListVars.i_p)); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("__sv_tmp_pv_")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke())))); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, common.StringCatter>() { public final common.StringCatter eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.extension.patternmatching.PvarPattern) { final common.Thunk<Object> __SV_LOCAL___pv7340___sv_pv_7339_pvn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (common.StringCatter)((common.StringCatter)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7341_pvn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv7340___sv_pv_7339_pvn.eval())); } };
return new common.StringCatter((common.StringCatter)(new common.StringCatter("__sv_pv_")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)core.PgenInt.invoke()))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)((common.StringCatter)((common.DecoratedNode)(__SV_LOCAL_7341_pvn.eval())).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.StringCatter)(__SV_LOCAL_7337___fail_7338.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.extension.patternmatching.PpatternListVars.i_p))); } }).eval()); } };
		//FUNCTION convStringsToVarBinders VarBinders ::= s::[Name] l::Location 
		//FUNCTION exprFromName Expr ::= n::Name 
		//FUNCTION allConCaseTransform PrimPattern ::= restExprs::[Expr] failCase::Expr retType::Type mrs::[AbstractMatchRule] 
		// names = map(patternListVars, head(mrs).headPattern.patternSubPatternList)
		silver.extension.patternmatching.PallConCaseTransform.localAttributes[silver.extension.patternmatching.Init.names__ON__silver_extension_patternmatching_allConCaseTransform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.extension.patternmatching.PpatternListVars.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((silver.extension.patternmatching.NAbstractMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PallConCaseTransform.i_mrs))).decorate(context, (common.Lazy[])null).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule)).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern)); } })); } };
		// subcase = caseExpr(map(exprFromName, names) ++ restExprs, map((.expandHeadPattern), mrs), failCase, retType,location=head(mrs).location)
		silver.extension.patternmatching.PallConCaseTransform.localAttributes[silver.extension.patternmatching.Init.subcase__ON__silver_extension_patternmatching_allConCaseTransform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)new silver.extension.patternmatching.PcaseExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.extension.patternmatching.PexprFromName.factory, context.localAsIsLazy(silver.extension.patternmatching.Init.names__ON__silver_extension_patternmatching_allConCaseTransform))); } }, context.childAsIsLazy(silver.extension.patternmatching.PallConCaseTransform.i_restExprs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.extension.patternmatching.Init.silver_extension_patternmatching_expandHeadPattern__ON__silver_extension_patternmatching_AbstractMatchRule, context), context.childAsIsLazy(silver.extension.patternmatching.PallConCaseTransform.i_mrs))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PallConCaseTransform.i_failCase)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PallConCaseTransform.i_retType)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NAbstractMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PallConCaseTransform.i_mrs))).getAnno_core_location()); } })); } };
		// l = head(mrs).headPattern.location
		silver.extension.patternmatching.PallConCaseTransform.localAttributes[silver.extension.patternmatching.Init.l__ON__silver_extension_patternmatching_allConCaseTransform] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPattern)((common.DecoratedNode)((silver.extension.patternmatching.NAbstractMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PallConCaseTransform.i_mrs))).decorate(context, (common.Lazy[])null).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule)).undecorate()).getAnno_core_location()); } };
		//FUNCTION foldPrimPatterns PrimPatterns ::= l::[PrimPattern] 
		//FUNCTION bindHeadPattern AbstractMatchRule ::= headExpr::Expr headType::Type rule::AbstractMatchRule 
		//FUNCTION makeLet Expr ::= l::Location s::String t::Type e::Expr o::Expr 
		//FUNCTION ensureDecoratedExpr Expr ::= e::Decorated Expr 
		// et = performSubstitution(e.typerep, e.upSubst)
		silver.extension.patternmatching.PensureDecoratedExpr.localAttributes[silver.extension.patternmatching.Init.et__ON__silver_extension_patternmatching_ensureDecoratedExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(context.childAsIsSynthesizedLazy(silver.extension.patternmatching.PensureDecoratedExpr.i_e, silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr), context.childAsIsSynthesizedLazy(silver.extension.patternmatching.PensureDecoratedExpr.i_e, silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr))); } };
		//FUNCTION mruleEqForGrouping Boolean ::= a::AbstractMatchRule b::AbstractMatchRule 
		//FUNCTION mruleLTEForSorting Boolean ::= a::AbstractMatchRule b::AbstractMatchRule 
		//FUNCTION groupMRules [[AbstractMatchRule]] ::= l::[AbstractMatchRule] 
	}

	public static int count_inh__ON__Pattern = 0;
	public static int count_syn__ON__Pattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_prodAppPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_wildcPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_varPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_intPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_strPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_truePattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_falsePattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_nilListPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_consListPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_listPattern = 0;
	public static int count_inh__ON__MRuleList = 0;
	public static int count_syn__ON__MRuleList = 0;
	public static int count_inh__ON__MatchRule = 0;
	public static int count_syn__ON__MatchRule = 0;
	public static int count_inh__ON__AbstractMatchRule = 0;
	public static int count_syn__ON__AbstractMatchRule = 0;
	public static int count_inh__ON__PatternList = 0;
	public static int count_syn__ON__PatternList = 0;
	public static int count_local__ON__silver_extension_patternmatching_caseExpr_c = 0;
	public static int count_local__ON__silver_extension_patternmatching_caseExpr = 0;
	public static int count_local__ON__silver_extension_patternmatching_mRuleList_one = 0;
	public static int count_local__ON__silver_extension_patternmatching_mRuleList_cons = 0;
	public static int count_local__ON__silver_extension_patternmatching_matchRule_c = 0;
	public static int count_local__ON__silver_extension_patternmatching_matchRule = 0;
	public static int count_local__ON__silver_extension_patternmatching_patternList_one = 0;
	public static int count_local__ON__silver_extension_patternmatching_patternList_more = 0;
	public static int count_local__ON__silver_extension_patternmatching_patternList_nil = 0;
	public static int count_local__ON__silver_extension_patternmatching_patternListVars = 0;
	public static int count_local__ON__silver_extension_patternmatching_convStringsToVarBinders = 0;
	public static int count_local__ON__silver_extension_patternmatching_exprFromName = 0;
	public static int count_local__ON__silver_extension_patternmatching_allConCaseTransform = 0;
	public static int count_local__ON__silver_extension_patternmatching_foldPrimPatterns = 0;
	public static int count_local__ON__silver_extension_patternmatching_bindHeadPattern = 0;
	public static int count_local__ON__silver_extension_patternmatching_makeLet = 0;
	public static int count_local__ON__silver_extension_patternmatching_ensureDecoratedExpr = 0;
	public static int count_local__ON__silver_extension_patternmatching_mruleEqForGrouping = 0;
	public static int count_local__ON__silver_extension_patternmatching_mruleLTEForSorting = 0;
	public static int count_local__ON__silver_extension_patternmatching_groupMRules = 0;
public static final int silver_definition_env_config__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_inh__ON__Pattern++;
public static final int silver_definition_env_pp__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_syn__ON__Pattern++;
public static final int silver_definition_env_env__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_inh__ON__Pattern++;
public static final int silver_definition_core_errors__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_syn__ON__Pattern++;
public static final int silver_extension_patternmatching_patternIsVariable__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_syn__ON__Pattern++;
public static final int silver_extension_patternmatching_patternVariableName__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_syn__ON__Pattern++;
public static final int silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_syn__ON__Pattern++;
public static final int silver_extension_patternmatching_patternSortKey__ON__silver_extension_patternmatching_Pattern = silver.extension.patternmatching.Init.count_syn__ON__Pattern++;
public static final int parms__ON__silver_extension_patternmatching_prodAppPattern = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_prodAppPattern++;
public static final int silver_extension_patternmatching_asListPattern__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_syn__ON__PatternList++;
public static final int silver_definition_env_config__ON__silver_extension_patternmatching_MRuleList = silver.extension.patternmatching.Init.count_inh__ON__MRuleList++;
public static final int silver_definition_env_pp__ON__silver_extension_patternmatching_MRuleList = silver.extension.patternmatching.Init.count_syn__ON__MRuleList++;
public static final int silver_definition_env_env__ON__silver_extension_patternmatching_MRuleList = silver.extension.patternmatching.Init.count_inh__ON__MRuleList++;
public static final int silver_definition_core_errors__ON__silver_extension_patternmatching_MRuleList = silver.extension.patternmatching.Init.count_syn__ON__MRuleList++;
public static final int silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MRuleList = silver.extension.patternmatching.Init.count_syn__ON__MRuleList++;
public static final int silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MRuleList = silver.extension.patternmatching.Init.count_inh__ON__MRuleList++;
public static final int silver_definition_env_config__ON__silver_extension_patternmatching_MatchRule = silver.extension.patternmatching.Init.count_inh__ON__MatchRule++;
public static final int silver_definition_env_pp__ON__silver_extension_patternmatching_MatchRule = silver.extension.patternmatching.Init.count_syn__ON__MatchRule++;
public static final int silver_definition_env_env__ON__silver_extension_patternmatching_MatchRule = silver.extension.patternmatching.Init.count_inh__ON__MatchRule++;
public static final int silver_definition_core_errors__ON__silver_extension_patternmatching_MatchRule = silver.extension.patternmatching.Init.count_syn__ON__MatchRule++;
public static final int silver_extension_patternmatching_matchRuleList__ON__silver_extension_patternmatching_MatchRule = silver.extension.patternmatching.Init.count_syn__ON__MatchRule++;
public static final int silver_extension_patternmatching_matchRulePatternSize__ON__silver_extension_patternmatching_MatchRule = silver.extension.patternmatching.Init.count_inh__ON__MatchRule++;
public static final int silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule = silver.extension.patternmatching.Init.count_syn__ON__AbstractMatchRule++;
public static final int silver_extension_patternmatching_isVarMatchRule__ON__silver_extension_patternmatching_AbstractMatchRule = silver.extension.patternmatching.Init.count_syn__ON__AbstractMatchRule++;
public static final int silver_extension_patternmatching_expandHeadPattern__ON__silver_extension_patternmatching_AbstractMatchRule = silver.extension.patternmatching.Init.count_syn__ON__AbstractMatchRule++;
public static final int silver_definition_env_config__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_inh__ON__PatternList++;
public static final int silver_definition_env_pp__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_syn__ON__PatternList++;
public static final int silver_extension_patternmatching_patternList__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_syn__ON__PatternList++;
public static final int silver_definition_env_env__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_inh__ON__PatternList++;
public static final int silver_definition_core_errors__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_syn__ON__PatternList++;
public static final int partMRs__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int varRules__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int prodRules__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int allConCase__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int allVarCase__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int freshFailName__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int mixedCase__ON__silver_extension_patternmatching_caseExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_caseExpr++;
public static final int n__ON__silver_extension_patternmatching_patternListVars = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_patternListVars++;
public static final int names__ON__silver_extension_patternmatching_allConCaseTransform = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_allConCaseTransform++;
public static final int subcase__ON__silver_extension_patternmatching_allConCaseTransform = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_allConCaseTransform++;
public static final int l__ON__silver_extension_patternmatching_allConCaseTransform = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_allConCaseTransform++;
public static final int et__ON__silver_extension_patternmatching_ensureDecoratedExpr = silver.extension.patternmatching.Init.count_local__ON__silver_extension_patternmatching_ensureDecoratedExpr++;
}
