package silver.extension.bidirtransform;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.modification.let_fix.java.Init.initAllStatics();
		silver.modification.ffi.java.Init.initAllStatics();
		silver.modification.autocopyattr.convenience.Init.initAllStatics();
		silver.modification.autocopyattr.java.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.modification.impide.Init.initAllStatics();
		silver.modification.autocopyattr.Init.initAllStatics();
		silver.modification.ffi.Init.initAllStatics();
		silver.modification.defaultattr.Init.initAllStatics();
		silver.modification.copper.Init.initAllStatics();
		silver.modification.primitivepattern.Init.initAllStatics();
		silver.modification.let_fix.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.extension.patternmatching.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.extension.convenience.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.extension.doc.core.Init.initAllStatics();
		silver.modification.copper_mda.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.definition.flow.driver.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.extension.bidirtransform.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.modification.let_fix.java.Init.init();
		silver.modification.ffi.java.Init.init();
		silver.modification.autocopyattr.convenience.Init.init();
		silver.modification.autocopyattr.java.Init.init();
		core.monad.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.definition.regex.Init.init();
		silver.modification.impide.Init.init();
		silver.modification.autocopyattr.Init.init();
		silver.modification.ffi.Init.init();
		silver.modification.defaultattr.Init.init();
		silver.modification.copper.Init.init();
		silver.modification.primitivepattern.Init.init();
		silver.modification.let_fix.Init.init();
		silver.util.Init.init();
		silver.extension.patternmatching.Init.init();
		silver.extension.list.Init.init();
		silver.extension.convenience.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.extension.doc.core.Init.init();
		silver.modification.copper_mda.Init.init();
		silver.translation.java.core.Init.init();
		silver.driver.util.Init.init();
		silver.definition.flow.driver.Init.init();
		silver.definition.flow.env.Init.init();
		silver.extension.bidirtransform.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.modification.let_fix.java.Init.postInit();
		silver.modification.ffi.java.Init.postInit();
		silver.modification.autocopyattr.convenience.Init.postInit();
		silver.modification.autocopyattr.java.Init.postInit();
		core.monad.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.modification.impide.Init.postInit();
		silver.modification.autocopyattr.Init.postInit();
		silver.modification.ffi.Init.postInit();
		silver.modification.defaultattr.Init.postInit();
		silver.modification.copper.Init.postInit();
		silver.modification.primitivepattern.Init.postInit();
		silver.modification.let_fix.Init.postInit();
		silver.util.Init.postInit();
		silver.extension.patternmatching.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.extension.convenience.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.extension.doc.core.Init.postInit();
		silver.modification.copper_mda.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.definition.flow.driver.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.extension.bidirtransform.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NQNameAttrOccur.decorators, PqAttr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PemptyFunc.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PargFunc.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PoneArgFunc.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PfullFunc.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PsynAttr.class);
		common.Decorator.applyDecorators(silver.definition.core.NDefLHS.decorators, PmkLhsDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PautocAttr.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PannoOn.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PattrOn.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoAppExprs.decorators, PconsAnnoAppExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAspectProductionSignature.decorators, PnsAspectProdSig.class);
		common.Decorator.applyDecorators(silver.definition.core.NAspectRHS.decorators, PnsAspectProdRHS.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PaspectProdStmt.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PaspectProdStmts.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmts.decorators, PprdStmtList.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionBody.decorators, PprdBody.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PattribDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PsynChdAttrDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PinhChdAttrDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PsynAttrDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NQNameType.decorators, PqnTyId.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PsTyExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PdecTyExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PqTyExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PlhsAccess.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PnamedAccess.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PlhsExprAccess.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PqAccess.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PexprAccess.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmkOrigin.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PoneApp.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PargTransAttrs.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PbuiltinAccess.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PprdRecurse.class);
		common.Decorator.applyDecorators(silver.definition.core.NQName.decorators, PmkQName.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PbaseName.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PpresentName.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PappExprList.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoAppExprs.decorators, PannoAppExprList.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoExpr.decorators, PannExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PnsApply.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PnsElemsToAppExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PnsElemToAppExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NBracketedOptTypeExprs.decorators, PbotlOneString.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionSignature.decorators, PmkProdSig.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionSignature.decorators, PmkProdSigDec.class);
		common.Decorator.applyDecorators(silver.definition.core.NAspectProductionSignature.decorators, PmkAspectProdSig.class);
		common.Decorator.applyDecorators(silver.definition.core.NAspectProductionSignature.decorators, PmkAspectProdSigDec.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmkFalse.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmkTrue.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PmkBoolTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.type.syntax.NTypeExpr.decorators, PmkMaybeTypeExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmkCond.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmkNew.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PjoinAGDcls.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PstrAppExprs.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NQNameList.decorators, PqNameListSingle.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NQNameList.decorators, PqNameListCons.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NQNameList.decorators, PqNameListEmpty.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NTransformRuleList.decorators, PtransformRuleCons.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NTransformRuleList.decorators, PtransformRuleSingle.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NTransformRule.decorators, PtransformRule.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PapplyTrans.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PrestoreExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PrestoreAppExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PrestoreAppExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PfillExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PfillStringConst.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PfillExprEnd.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PfillAppExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PfillAppExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoAppExprs.decorators, PfillAnnoExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoExpr.decorators, PfillAnnoAppExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PidxOfExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PfillExprPattern.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PfakeAspectProductionDcl.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteRuleList.decorators, PrewriteRuleCons.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteRuleList.decorators, PrewriteRuleSingle.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteRule.decorators, PrewriteRuleProd.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteRule.decorators, PrewriteRuleType.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteArrow.decorators, PshortRewriteArrow.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteArrow.decorators, PlongRewriteArrow.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOptRHSType.decorators, PoneRHSType.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOptRHSType.decorators, PnoRHSType.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteRule.decorators, PrewriteRule.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PoriginEq.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PoriginPrdStmt.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PnonterminalGroup.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NNonterminalList.decorators, PsingleNt.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NNonterminalList.decorators, PconsNt.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NFullNonterminal.decorators, PfullNt.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NProductionDef.decorators, PproductionDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmatchProd.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PmatchSingle.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PntGroupDef.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PntGroupDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PapplyRw.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PapplyRwOrigin.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PapplyRwProd.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PoriginDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PcncOriginDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PcncApplyOrigins.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PapplyOrigins.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteProduction.decorators, PrewriteProduction.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteProduction.decorators, PemptyRewriteProduction.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteProductionArgs.decorators, PrewriteProductionArgSingle.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NRewriteProductionArgs.decorators, PrewriteProductionArgMany.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PlockAGDcls.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PlockDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcls.decorators, PtransformAGDclFull.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PinjectAnnos.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PinjectApplication.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExprs.decorators, PinjectAppExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAppExpr.decorators, PinjectAppExpr.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoAppExprs.decorators, PinjectAnnoExprs.class);
		common.Decorator.applyDecorators(silver.definition.core.NAnnoExpr.decorators, PinjectAnnoExpr.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, PlocOrigin.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, PtxtOrigin.class);
		common.Decorator.applyDecorators(silver.extension.bidirtransform.NOrigin.decorators, PbottomOrigin.class);
	}

	private static void setupInheritedAttributes(){
		//	local attribute qntlName::QNameWithTL;
		silver.extension.bidirtransform.PannoOn.localInheritedAttributes[silver.extension.bidirtransform.Init.qntlName__ON__silver_extension_bidirtransform_annoOn] = new common.Lazy[silver.extension.convenience.NQNameWithTL.num_inh_attrs];
		silver.extension.bidirtransform.PannoOn.occurs_local[silver.extension.bidirtransform.Init.qntlName__ON__silver_extension_bidirtransform_annoOn] = "silver:extension:bidirtransform:annoOn:local:qntlName";
		silver.extension.bidirtransform.PannoOn.occurs_local[silver.extension.bidirtransform.Init.qntOnNames__ON__silver_extension_bidirtransform_annoOn] = "silver:extension:bidirtransform:annoOn:local:qntOnNames";
		//	local attribute hd::NamedSignatureElement;
		silver.extension.bidirtransform.PnsAspectProdRHS.localInheritedAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_nsAspectProdRHS] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];
		silver.extension.bidirtransform.PnsAspectProdRHS.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_nsAspectProdRHS] = "silver:extension:bidirtransform:nsAspectProdRHS:local:hd";
		silver.extension.bidirtransform.NQNameList.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_QNameList] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NQNameList.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList] = "silver:extension:bidirtransform:qList";
		silver.definition.core.NExpr.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = "silver:extension:bidirtransform:ppDebug";
		silver.extension.bidirtransform.NTransformRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:extension:bidirtransform:transformRules";
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:definition:env:env";
		silver.extension.bidirtransform.NTransformRuleList.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NTransformRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:extension:bidirtransform:absGroup";
		silver.extension.bidirtransform.NTransformRuleList.decorators.add(silver.extension.bidirtransform.DabsGroup.singleton);
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:extension:bidirtransform:cncGroup";
		silver.extension.bidirtransform.NTransformRuleList.decorators.add(silver.extension.bidirtransform.DcncGroup.singleton);
		silver.extension.bidirtransform.NTransformRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:analysis:typechecking:core:downSubst";
		silver.extension.bidirtransform.NTransformRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:analysis:typechecking:core:upSubst";
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:analysis:typechecking:core:finalSubst";
		silver.extension.bidirtransform.NTransformRuleList.decorators.add(silver.analysis.typechecking.core.DfinalSubst.singleton);
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:definition:env:config";
		silver.extension.bidirtransform.NTransformRuleList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NTransformRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_TransformRuleList] = "silver:extension:bidirtransform:inhProds";
		silver.extension.bidirtransform.NTransformRuleList.decorators.add(silver.extension.bidirtransform.DinhProds.singleton);
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:matchProd";
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:namedSig";
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:outputStmt";
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRule] = "silver:definition:env:env";
		silver.extension.bidirtransform.NTransformRule.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:absGroup";
		silver.extension.bidirtransform.NTransformRule.decorators.add(silver.extension.bidirtransform.DabsGroup.singleton);
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:cncGroup";
		silver.extension.bidirtransform.NTransformRule.decorators.add(silver.extension.bidirtransform.DcncGroup.singleton);
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRule] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRule] = "silver:analysis:typechecking:core:downSubst";
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRule] = "silver:analysis:typechecking:core:upSubst";
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRule] = "silver:analysis:typechecking:core:finalSubst";
		silver.extension.bidirtransform.NTransformRule.decorators.add(silver.analysis.typechecking.core.DfinalSubst.singleton);
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRule] = "silver:definition:env:config";
		silver.extension.bidirtransform.NTransformRule.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NTransformRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_asExpr__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:asExpr";
		silver.extension.bidirtransform.NTransformRule.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_TransformRule] = "silver:extension:bidirtransform:inhProds";
		silver.extension.bidirtransform.NTransformRule.decorators.add(silver.extension.bidirtransform.DinhProds.singleton);
		silver.extension.bidirtransform.PhasTrans.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasTrans] = "silver:extension:bidirtransform:hasTrans:local:hd";
		silver.extension.bidirtransform.PgetTrans.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_getTrans] = "silver:extension:bidirtransform:getTrans:local:hd";
		silver.extension.bidirtransform.PapplyTrans.occurs_local[silver.extension.bidirtransform.Init.trans__ON__silver_extension_bidirtransform_applyTrans] = "silver:extension:bidirtransform:applyTrans:local:trans";
		silver.extension.bidirtransform.PfillStringConst.occurs_local[silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_fillStringConst] = "silver:extension:bidirtransform:fillStringConst:local:idx";
		//	local attribute inputs::Pair<[Expr] [String]>;
		silver.extension.bidirtransform.PfillExprPattern.localInheritedAttributes[silver.extension.bidirtransform.Init.inputs__ON__silver_extension_bidirtransform_fillExprPattern] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.extension.bidirtransform.PfillExprPattern.occurs_local[silver.extension.bidirtransform.Init.inputs__ON__silver_extension_bidirtransform_fillExprPattern] = "silver:extension:bidirtransform:fillExprPattern:local:inputs";
		//	local attribute namedSig::NamedSignature;
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.namedSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.namedSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:namedSig";
		//	local attribute realSig::NamedSignature;
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.realSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.realSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:realSig";
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.sigDefs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:sigDefs";
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.prodAtts__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:prodAtts";
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.allLexicalTyVars__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:allLexicalTyVars";
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.myGraphs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:myGraphs";
		//	local attribute myFlowGraph::ProductionGraph;
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.myFlowGraph__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy[silver.definition.flow.driver.NProductionGraph.num_inh_attrs];
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.myFlowGraph__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:myFlowGraph";
		//	local attribute errCheck1::TypeCheck;
		silver.extension.bidirtransform.PfakeAspectProductionDcl.localInheritedAttributes[silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.extension.bidirtransform.PfakeAspectProductionDcl.occurs_local[silver.extension.bidirtransform.Init.errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl] = "silver:extension:bidirtransform:fakeAspectProductionDcl:local:errCheck1";
		silver.definition.env.NEnv.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env] = "silver:extension:bidirtransform:ppDebug";
		silver.definition.env.NEnv.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env] = "silver:extension:bidirtransform:filteredProds";
		silver.extension.bidirtransform.NRewriteRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rewriteRules__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:extension:bidirtransform:rewriteRules";
		silver.extension.bidirtransform.NRewriteRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:definition:env:env";
		silver.extension.bidirtransform.NRewriteRuleList.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NRewriteRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NRewriteRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:extension:bidirtransform:absGroup";
		silver.extension.bidirtransform.NRewriteRuleList.decorators.add(silver.extension.bidirtransform.DabsGroup.singleton);
		silver.extension.bidirtransform.NRewriteRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:extension:bidirtransform:cncGroup";
		silver.extension.bidirtransform.NRewriteRuleList.decorators.add(silver.extension.bidirtransform.DcncGroup.singleton);
		silver.extension.bidirtransform.NRewriteRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NRewriteRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:analysis:typechecking:core:downSubst";
		silver.extension.bidirtransform.NRewriteRuleList.occurs_syn[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:analysis:typechecking:core:upSubst";
		silver.extension.bidirtransform.NRewriteRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:analysis:typechecking:core:finalSubst";
		silver.extension.bidirtransform.NRewriteRuleList.decorators.add(silver.analysis.typechecking.core.DfinalSubst.singleton);
		silver.extension.bidirtransform.NRewriteRuleList.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteRuleList] = "silver:definition:env:config";
		silver.extension.bidirtransform.NRewriteRuleList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputType__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:inputType";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inputProduction__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:inputProduction";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_hasProduction__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:hasProduction";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteRule] = "silver:definition:env:typerep";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:outputStmt";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_restoreStmt__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:restoreStmt";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:shouldRestore";
		silver.extension.bidirtransform.NRewriteRule.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_RewriteRule] = "silver:definition:env:env";
		silver.extension.bidirtransform.NRewriteRule.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NRewriteRule.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:absGroup";
		silver.extension.bidirtransform.NRewriteRule.decorators.add(silver.extension.bidirtransform.DabsGroup.singleton);
		silver.extension.bidirtransform.NRewriteRule.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteRule] = "silver:extension:bidirtransform:cncGroup";
		silver.extension.bidirtransform.NRewriteRule.decorators.add(silver.extension.bidirtransform.DcncGroup.singleton);
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRule] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NRewriteRule.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule] = "silver:analysis:typechecking:core:downSubst";
		silver.extension.bidirtransform.NRewriteRule.occurs_syn[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule] = "silver:analysis:typechecking:core:upSubst";
		silver.extension.bidirtransform.NRewriteRule.occurs_inh[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRule] = "silver:analysis:typechecking:core:finalSubst";
		silver.extension.bidirtransform.NRewriteRule.decorators.add(silver.analysis.typechecking.core.DfinalSubst.singleton);
		silver.extension.bidirtransform.NRewriteRule.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteRule] = "silver:definition:env:config";
		silver.extension.bidirtransform.NRewriteRule.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NRewriteProduction.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:core:name";
		silver.extension.bidirtransform.NRewriteProduction.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:env:inputNames";
		silver.extension.bidirtransform.NRewriteProduction.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:env:typerep";
		silver.extension.bidirtransform.NRewriteProduction.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:env:env";
		silver.extension.bidirtransform.NRewriteProduction.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NRewriteProduction.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NRewriteProduction.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:extension:bidirtransform:absGroup";
		silver.extension.bidirtransform.NRewriteProduction.decorators.add(silver.extension.bidirtransform.DabsGroup.singleton);
		silver.extension.bidirtransform.NRewriteProduction.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:extension:bidirtransform:cncGroup";
		silver.extension.bidirtransform.NRewriteProduction.decorators.add(silver.extension.bidirtransform.DcncGroup.singleton);
		silver.extension.bidirtransform.NRewriteProduction.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NRewriteProduction.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:definition:env:config";
		silver.extension.bidirtransform.NRewriteProduction.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NRewriteProduction.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_decSig__ON__silver_extension_bidirtransform_RewriteProduction] = "silver:extension:bidirtransform:decSig";
		silver.extension.bidirtransform.NRewriteProductionArgs.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProductionArgs] = "silver:definition:env:inputNames";
		silver.extension.bidirtransform.NRewriteProductionArgs.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NRewriteProductionArgs.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProductionArgs] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NRewriteProductionArgs.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteProductionArgs] = "silver:definition:env:config";
		silver.extension.bidirtransform.NRewriteProductionArgs.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NRewriteArrow.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteArrow] = "silver:extension:bidirtransform:shouldRestore";
		silver.extension.bidirtransform.NRewriteArrow.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_RewriteArrow] = "silver:extension:bidirtransform:rhsType";
		silver.extension.bidirtransform.NOptRHSType.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_OptRHSType] = "silver:extension:bidirtransform:rhsType";
		//	local attribute rhsType::Type;
		silver.extension.bidirtransform.PrewriteRuleProd.localInheritedAttributes[silver.extension.bidirtransform.Init.rhsType__ON__silver_extension_bidirtransform_rewriteRuleProd] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.extension.bidirtransform.PrewriteRuleProd.occurs_local[silver.extension.bidirtransform.Init.rhsType__ON__silver_extension_bidirtransform_rewriteRuleProd] = "silver:extension:bidirtransform:rewriteRuleProd:local:rhsType";
		//	local attribute rhsType::Type;
		silver.extension.bidirtransform.PrewriteRuleType.localInheritedAttributes[silver.extension.bidirtransform.Init.rhsType__ON__silver_extension_bidirtransform_rewriteRuleType] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.extension.bidirtransform.PrewriteRuleType.occurs_local[silver.extension.bidirtransform.Init.rhsType__ON__silver_extension_bidirtransform_rewriteRuleType] = "silver:extension:bidirtransform:rewriteRuleType:local:rhsType";
		//	local attribute rhsNs::Maybe<Decorated NamedSignature>;
		silver.extension.bidirtransform.PrewriteRule.localInheritedAttributes[silver.extension.bidirtransform.Init.rhsNs__ON__silver_extension_bidirtransform_rewriteRule] = new common.Lazy[core.NMaybe.num_inh_attrs];
		silver.extension.bidirtransform.PrewriteRule.occurs_local[silver.extension.bidirtransform.Init.rhsNs__ON__silver_extension_bidirtransform_rewriteRule] = "silver:extension:bidirtransform:rewriteRule:local:rhsNs";
		//	local attribute newAnnos::AnnoAppExprs;
		silver.extension.bidirtransform.PoriginEq.localInheritedAttributes[silver.extension.bidirtransform.Init.newAnnos__ON__silver_extension_bidirtransform_originEq] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
		silver.extension.bidirtransform.PoriginEq.occurs_local[silver.extension.bidirtransform.Init.newAnnos__ON__silver_extension_bidirtransform_originEq] = "silver:extension:bidirtransform:originEq:local:newAnnos";
		silver.definition.core.NProductionStmt.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionStmt] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NProductionStmt.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NProductionStmts.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionStmts] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NProductionStmts.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NExpr.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_Expr] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NExpr.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NAppExpr.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AppExpr] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NAppExpr.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NAppExprs.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AppExprs] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NAppExprs.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NAnnoExpr.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AnnoExpr] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NAnnoExpr.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NAnnoAppExprs.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AnnoAppExprs] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NAnnoAppExprs.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NForwardInh.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ForwardInh] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NForwardInh.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NForwardInhs.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ForwardInhs] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NForwardInhs.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NExprInh.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ExprInh] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NExprInh.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NExprInhs.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ExprInhs] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NExprInhs.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.modification.let_fix.NAssignExpr.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_modification_let_fix_AssignExpr] = "silver:extension:bidirtransform:prodOutput";
		silver.modification.let_fix.NAssignExpr.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.modification.primitivepattern.NPrimPattern.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_modification_primitivepattern_PrimPattern] = "silver:extension:bidirtransform:prodOutput";
		silver.modification.primitivepattern.NPrimPattern.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.modification.primitivepattern.NPrimPatterns.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_modification_primitivepattern_PrimPatterns] = "silver:extension:bidirtransform:prodOutput";
		silver.modification.primitivepattern.NPrimPatterns.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NProductionBody.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NProductionBody.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NAGDcl.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AGDcl] = "silver:extension:bidirtransform:prodOutput";
		silver.definition.core.NAGDcl.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.extension.convenience.NProductionDclStmt.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_extension_convenience_ProductionDclStmt] = "silver:extension:bidirtransform:prodOutput";
		silver.extension.convenience.NProductionDclStmt.decorators.add(silver.extension.bidirtransform.DprodOutput.singleton);
		silver.definition.core.NAGDcl.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_grantedDefs__ON__silver_definition_core_AGDcl] = "silver:extension:bidirtransform:grantedDefs";
		silver.definition.core.NAGDcl.decorators.add(silver.extension.bidirtransform.DgrantedDefs.singleton);
		silver.extension.bidirtransform.NFullNonterminal.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_FullNonterminal] = "silver:definition:core:name";
		silver.extension.bidirtransform.NFullNonterminal.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntProds__ON__silver_extension_bidirtransform_FullNonterminal] = "silver:extension:bidirtransform:ntProds";
		silver.extension.bidirtransform.NFullNonterminal.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NFullNonterminal.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_FullNonterminal] = "silver:definition:env:env";
		silver.extension.bidirtransform.NFullNonterminal.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NFullNonterminal.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_grantedDefs__ON__silver_extension_bidirtransform_FullNonterminal] = "silver:extension:bidirtransform:grantedDefs";
		silver.extension.bidirtransform.NFullNonterminal.decorators.add(silver.extension.bidirtransform.DgrantedDefs.singleton);
		silver.extension.bidirtransform.NNonterminalList.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList] = "silver:extension:bidirtransform:ntList";
		silver.extension.bidirtransform.NNonterminalList.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NNonterminalList.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_NonterminalList] = "silver:definition:env:env";
		silver.extension.bidirtransform.NNonterminalList.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NNonterminalList.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_grantedDefs__ON__silver_extension_bidirtransform_NonterminalList] = "silver:extension:bidirtransform:grantedDefs";
		silver.extension.bidirtransform.NNonterminalList.decorators.add(silver.extension.bidirtransform.DgrantedDefs.singleton);
		silver.extension.bidirtransform.PfilterSigs.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterSigs] = "silver:extension:bidirtransform:filterSigs:local:hd";
		silver.extension.bidirtransform.PhasRwProd.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwProd] = "silver:extension:bidirtransform:hasRwProd:local:hd";
		silver.extension.bidirtransform.PhasRwEq.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwEq] = "silver:extension:bidirtransform:hasRwEq:local:hd";
		silver.extension.bidirtransform.PhasRwOut.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwOut] = "silver:extension:bidirtransform:hasRwOut:local:hd";
		silver.extension.bidirtransform.PrwProd.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_rwProd] = "silver:extension:bidirtransform:rwProd:local:hd";
		silver.extension.bidirtransform.PrwEq.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_rwEq] = "silver:extension:bidirtransform:rwEq:local:hd";
		silver.extension.bidirtransform.PrwOut.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_rwOut] = "silver:extension:bidirtransform:rwOut:local:hd";
		silver.extension.bidirtransform.NProductionDef.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_env__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:env:env";
		silver.extension.bidirtransform.NProductionDef.decorators.add(silver.definition.env.Denv.singleton);
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:core:errors";
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:namedSig";
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_patternList__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:patternList";
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:matchProd";
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_typerep__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:env:typerep";
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_inputNames__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:env:inputNames";
		silver.extension.bidirtransform.NProductionDef.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:absGroup";
		silver.extension.bidirtransform.NProductionDef.decorators.add(silver.extension.bidirtransform.DabsGroup.singleton);
		silver.extension.bidirtransform.NProductionDef.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:cncGroup";
		silver.extension.bidirtransform.NProductionDef.decorators.add(silver.extension.bidirtransform.DcncGroup.singleton);
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:env:pp";
		silver.extension.bidirtransform.NProductionDef.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_core_grammarName__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:core:grammarName";
		silver.extension.bidirtransform.NProductionDef.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.extension.bidirtransform.NProductionDef.occurs_inh[silver.extension.bidirtransform.Init.silver_definition_env_config__ON__silver_extension_bidirtransform_ProductionDef] = "silver:definition:env:config";
		silver.extension.bidirtransform.NProductionDef.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.extension.bidirtransform.NProductionDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_asExpr__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:asExpr";
		silver.extension.bidirtransform.NProductionDef.occurs_inh[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_ProductionDef] = "silver:extension:bidirtransform:inhProds";
		silver.extension.bidirtransform.NProductionDef.decorators.add(silver.extension.bidirtransform.DinhProds.singleton);
		silver.extension.bidirtransform.PproductionDef.occurs_local[silver.extension.bidirtransform.Init.prodNames__ON__silver_extension_bidirtransform_productionDef] = "silver:extension:bidirtransform:productionDef:local:prodNames";
		silver.extension.bidirtransform.PproductionDef.occurs_local[silver.extension.bidirtransform.Init.idx__ON__silver_extension_bidirtransform_productionDef] = "silver:extension:bidirtransform:productionDef:local:idx";
		silver.extension.bidirtransform.PapplyRwProd.occurs_local[silver.extension.bidirtransform.Init.fwdFunc__ON__silver_extension_bidirtransform_applyRwProd] = "silver:extension:bidirtransform:applyRwProd:local:fwdFunc";
		silver.extension.bidirtransform.PcncApplyOrigins.occurs_local[silver.extension.bidirtransform.Init.cncNames__ON__silver_extension_bidirtransform_cncApplyOrigins] = "silver:extension:bidirtransform:cncApplyOrigins:local:cncNames";
		//	local attribute agDcls::AGDcl;
		silver.extension.bidirtransform.PcncApplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls__ON__silver_extension_bidirtransform_cncApplyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PcncApplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls__ON__silver_extension_bidirtransform_cncApplyOrigins] = "silver:extension:bidirtransform:cncApplyOrigins:local:agDcls";
		//	local attribute agDcls2::AGDcl;
		silver.extension.bidirtransform.PcncApplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_cncApplyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PcncApplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_cncApplyOrigins] = "silver:extension:bidirtransform:cncApplyOrigins:local:agDcls2";
		//	local attribute agDcls3::AGDcl;
		silver.extension.bidirtransform.PcncApplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_cncApplyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PcncApplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_cncApplyOrigins] = "silver:extension:bidirtransform:cncApplyOrigins:local:agDcls3";
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.absNames__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:absNames";
		//	local attribute agDcls::AGDcl;
		silver.extension.bidirtransform.PapplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls__ON__silver_extension_bidirtransform_applyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:agDcls";
		//	local attribute agDcls2::AGDcl;
		silver.extension.bidirtransform.PapplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_applyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:agDcls2";
		//	local attribute agDcls3::AGDcl;
		silver.extension.bidirtransform.PapplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_applyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:agDcls3";
		//	local attribute agDcls4::AGDcl;
		silver.extension.bidirtransform.PapplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls4__ON__silver_extension_bidirtransform_applyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls4__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:agDcls4";
		//	local attribute agDcls5::AGDcl;
		silver.extension.bidirtransform.PapplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls5__ON__silver_extension_bidirtransform_applyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls5__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:agDcls5";
		//	local attribute agDcls6::AGDcl;
		silver.extension.bidirtransform.PapplyOrigins.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_applyOrigins] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PapplyOrigins.occurs_local[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_applyOrigins] = "silver:extension:bidirtransform:applyOrigins:local:agDcls6";
		silver.extension.bidirtransform.PrewriteProduction.occurs_local[silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_rewriteProduction] = "silver:extension:bidirtransform:rewriteProduction:local:absSig";
		silver.extension.bidirtransform.PrewriteProduction.occurs_local[silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_rewriteProduction] = "silver:extension:bidirtransform:rewriteProduction:local:cncSig";
		silver.definition.env.NDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isLock__ON__silver_definition_env_Def] = "silver:extension:bidirtransform:isLock";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.tName__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:tName";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.groupEnv__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:groupEnv";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.absGroup__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:absGroup";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.cncGroup__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:cncGroup";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.absNames__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:absNames";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.cncNames__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:cncNames";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.locCncNames__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:locCncNames";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.nonLocCncNames__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:nonLocCncNames";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.allNames__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:allNames";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.absProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:absProdDcls";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.cncProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:cncProdDcls";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.locCncProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:locCncProdDcls";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.nonLocCncProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:nonLocCncProdDcls";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.allProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:allProdDcls";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.absProdNames__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:absProdNames";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.log__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:log";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.inhRedexName__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:inhRedexName";
		//	local attribute agDcls1::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls1__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls1";
		//	local attribute agDcls2::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls2__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls2";
		//	local attribute agDcls3::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls3__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls3";
		//	local attribute agDcls4::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls4__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls4__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls4";
		//	local attribute agDcls5::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls5__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls5__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls5";
		//	local attribute agDcls6::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls6__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls6";
		//	local attribute agDcls7::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls7__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls7__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls7";
		//	local attribute agDcls8::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls8__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls8__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls8";
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.newRwRules__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:newRwRules";
		//	local attribute agDcls9::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls9__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls9__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls9";
		//	local attribute agDcls10::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls10__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls10__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls10";
		//	local attribute agDcls11::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls11__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls11__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls11";
		//	local attribute agDcls12::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls12__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls12__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls12";
		//	local attribute agDcls13::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls13__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls13__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls13";
		//	local attribute agDcls14::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls14__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls14__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls14";
		//	local attribute agDcls15::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls15__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls15__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls15";
		//	local attribute agDcls16::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.agDcls16__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.agDcls16__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:agDcls16";
		//	local attribute toForward::AGDcl;
		silver.extension.bidirtransform.PtransformAGDclFull.localInheritedAttributes[silver.extension.bidirtransform.Init.toForward__ON__silver_extension_bidirtransform_transformAGDclFull] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.extension.bidirtransform.PtransformAGDclFull.occurs_local[silver.extension.bidirtransform.Init.toForward__ON__silver_extension_bidirtransform_transformAGDclFull] = "silver:extension:bidirtransform:transformAGDclFull:local:toForward";
		silver.definition.env.NDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = "silver:extension:bidirtransform:ppDebug";
		silver.definition.env.NDefs.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Defs] = "silver:extension:bidirtransform:ppDebug";
		silver.definition.env.NDefs.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs] = "silver:extension:bidirtransform:filteredProdDefs";
		silver.definition.env.NDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_Def] = "silver:extension:bidirtransform:prodNamedSig";
		silver.definition.env.NDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_Def] = "silver:extension:bidirtransform:absProdNamedSig";
		silver.definition.env.NDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_Def] = "silver:extension:bidirtransform:cncProdNamedSig";
		silver.definition.env.NDef.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isProdDef__ON__silver_definition_env_Def] = "silver:extension:bidirtransform:isProdDef";
		//	local attribute baseAppExprs::AppExprs;
		silver.extension.bidirtransform.PinjectApplication.localInheritedAttributes[silver.extension.bidirtransform.Init.baseAppExprs__ON__silver_extension_bidirtransform_injectApplication] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];
		silver.extension.bidirtransform.PinjectApplication.occurs_local[silver.extension.bidirtransform.Init.baseAppExprs__ON__silver_extension_bidirtransform_injectApplication] = "silver:extension:bidirtransform:injectApplication:local:baseAppExprs";
		//	local attribute baseAnnoExprs::AnnoAppExprs;
		silver.extension.bidirtransform.PinjectApplication.localInheritedAttributes[silver.extension.bidirtransform.Init.baseAnnoExprs__ON__silver_extension_bidirtransform_injectApplication] = new common.Lazy[silver.definition.core.NAnnoAppExprs.num_inh_attrs];
		silver.extension.bidirtransform.PinjectApplication.occurs_local[silver.extension.bidirtransform.Init.baseAnnoExprs__ON__silver_extension_bidirtransform_injectApplication] = "silver:extension:bidirtransform:injectApplication:local:baseAnnoExprs";
		silver.extension.bidirtransform.NOrigin.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin] = "silver:extension:bidirtransform:wasTransformed";
		silver.extension.bidirtransform.NOrigin.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin] = "silver:extension:bidirtransform:concreteOrigin";
		silver.extension.bidirtransform.NOrigin.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isBottomOrigin__ON__silver_extension_bidirtransform_Origin] = "silver:extension:bidirtransform:isBottomOrigin";
		silver.definition.env.NDclInfo.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = "silver:extension:bidirtransform:ppDebug";
		silver.definition.env.NDclInfo.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo] = "silver:extension:bidirtransform:prodNamedSig";
		silver.definition.env.NDclInfo.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo] = "silver:extension:bidirtransform:absProdNamedSig";
		silver.definition.env.NDclInfo.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo] = "silver:extension:bidirtransform:cncProdNamedSig";
		//	local attribute hd::NamedSignatureElement;
		silver.extension.bidirtransform.PinhRedexNameSig.localInheritedAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_inhRedexNameSig] = new common.Lazy[silver.definition.env.NNamedSignatureElement.num_inh_attrs];
		silver.extension.bidirtransform.PinhRedexNameSig.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_inhRedexNameSig] = "silver:extension:bidirtransform:inhRedexNameSig:local:hd";
		silver.extension.bidirtransform.PinhRedexNameSig.occurs_local[silver.extension.bidirtransform.Init.def__ON__silver_extension_bidirtransform_inhRedexNameSig] = "silver:extension:bidirtransform:inhRedexNameSig:local:def";
		//	local attribute hd::Def;
		silver.extension.bidirtransform.PfilterDefs.localInheritedAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterDefs] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];
		silver.extension.bidirtransform.PfilterDefs.occurs_local[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterDefs] = "silver:extension:bidirtransform:filterDefs:local:hd";
		silver.extension.bidirtransform.PfilterDefs.occurs_local[silver.extension.bidirtransform.Init.tl__ON__silver_extension_bidirtransform_filterDefs] = "silver:extension:bidirtransform:filterDefs:local:tl";
		silver.definition.env.NEnvItem.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem] = "silver:extension:bidirtransform:ppDebug";
		silver.definition.env.NEnvItem.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem] = "silver:extension:bidirtransform:prodNamedSig";
		silver.definition.env.NEnvItem.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem] = "silver:extension:bidirtransform:absProdNamedSig";
		silver.definition.env.NEnvItem.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem] = "silver:extension:bidirtransform:cncProdNamedSig";
		silver.extension.patternmatching.NPatternList.occurs_syn[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList] = "silver:extension:bidirtransform:rawPatternList";
		silver.extension.bidirtransform.PgetProdFromGroups.occurs_local[silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_getProdFromGroups] = "silver:extension:bidirtransform:getProdFromGroups:local:absSig";
		silver.extension.bidirtransform.PgetProdFromGroups.occurs_local[silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_getProdFromGroups] = "silver:extension:bidirtransform:getProdFromGroups:local:cncSig";
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.bidirtransform.PqAttr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PemptyFunc.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PargFunc.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PoneArgFunc.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfullFunc.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PsynAttr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkLhsDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PautocAttr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PannoOn.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PattrOn.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PconsAnnoAppExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnsAspectProdSig.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnsAspectProdRHS.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PaspectProdStmt.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PaspectProdStmts.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PprdStmtList.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PprdBody.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PattribDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PsynChdAttrDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PinhChdAttrDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PsynAttrDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PqnTyId.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PsTyExpr.initProductionAttributeDefinitions();
		//FUNCTION sTyExprDec Decorated TypeExpr ::= s::String loc::Location env::Decorated Env 
		silver.extension.bidirtransform.PdecTyExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PqTyExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PlhsAccess.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnamedAccess.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PlhsExprAccess.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PqAccess.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PexprAccess.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkOrigin.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PoneApp.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PargTransAttrs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PbuiltinAccess.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PprdRecurse.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkQName.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PbaseName.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PpresentName.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PappExprList.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PannoAppExprList.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PannExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnsApply.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnsElemsToAppExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnsElemToAppExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PbotlOneString.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkProdSig.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkProdSigDec.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkAspectProdSig.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkAspectProdSigDec.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkFalse.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkTrue.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkBoolTypeExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkMaybeTypeExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkCond.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmkNew.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PjoinAGDcls.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PstrAppExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PqNameListSingle.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PqNameListCons.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PqNameListEmpty.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION errorExpr top ::= e::[Message] 
		// top.ppDebug = "errorExpr"
		silver.definition.core.PerrorExpr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorExpr")); } };
		//ASPECT PRODUCTION nestedExpr top ::= '(' e::Expr ')' 
		// top.ppDebug = "nestedExpr"
		silver.definition.core.PnestedExpr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("nestedExpr")); } };
		//ASPECT PRODUCTION baseExpr top ::= q::QName 
		// top.ppDebug = "baseExpr"
		silver.definition.core.PbaseExpr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("baseExpr")); } };
		//ASPECT PRODUCTION errorReference top ::= msg::[Message] q::Decorated QName 
		// top.ppDebug = "errorReference"
		silver.definition.core.PerrorReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorReference")); } };
		//ASPECT PRODUCTION childReference top ::= q::Decorated QName 
		// top.ppDebug = "childReference"
		silver.definition.core.PchildReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("childReference")); } };
		//ASPECT PRODUCTION lhsReference top ::= q::Decorated QName 
		// top.ppDebug = "lhsReference"
		silver.definition.core.PlhsReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("lhsReference")); } };
		//ASPECT PRODUCTION localReference top ::= q::Decorated QName 
		// top.ppDebug = "localReference"
		silver.definition.core.PlocalReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("localReference")); } };
		//ASPECT PRODUCTION forwardReference top ::= q::Decorated QName 
		// top.ppDebug = "forwardReference"
		silver.definition.core.PforwardReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("forwardReference")); } };
		//ASPECT PRODUCTION productionReference top ::= q::Decorated QName 
		// top.ppDebug = "productionReference"
		silver.definition.core.PproductionReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("productionReference")); } };
		//ASPECT PRODUCTION functionReference top ::= q::Decorated QName 
		// top.ppDebug = "functionReference"
		silver.definition.core.PfunctionReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("functionReference")); } };
		//ASPECT PRODUCTION globalValueReference top ::= q::Decorated QName 
		// top.ppDebug = "globalValueReference"
		silver.definition.core.PglobalValueReference.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("globalValueReference")); } };
		//ASPECT PRODUCTION concreteForwardExpr top ::= q :: 'forward' 
		// top.ppDebug = "concreteForwardExpr"
		silver.definition.core.PconcreteForwardExpr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("concreteForwardExpr")); } };
		//ASPECT PRODUCTION application top ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')' 
		// top.ppDebug = "application"
		silver.definition.core.Papplication.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("application")); } };
		//ASPECT PRODUCTION applicationAnno top ::= e::Expr '(' anns::AnnoAppExprs ')' 
		// top.ppDebug = "applicationAnno"
		silver.definition.core.PapplicationAnno.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("applicationAnno")); } };
		//ASPECT PRODUCTION applicationExpr top ::= e::Expr '(' es::AppExprs ')' 
		// top.ppDebug = "applicationExpr"
		silver.definition.core.PapplicationExpr.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("applicationExpr")); } };
		//ASPECT PRODUCTION applicationEmpty top ::= e::Expr '(' ')' 
		// top.ppDebug = "applicationEmpty"
		silver.definition.core.PapplicationEmpty.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("applicationEmpty")); } };
		//ASPECT PRODUCTION errorApplication top ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs 
		// top.ppDebug = "errorApplication"
		silver.definition.core.PerrorApplication.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorApplication")); } };
		//ASPECT PRODUCTION functionApplication top ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs 
		// top.ppDebug = "functionApplication"
		silver.definition.core.PfunctionApplication.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("functionApplication")); } };
		//ASPECT PRODUCTION functionInvocation top ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs 
		// top.ppDebug = "functionInvocation"
		silver.definition.core.PfunctionInvocation.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("functionInvocation")); } };
		//ASPECT PRODUCTION partialApplication top ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs 
		// top.ppDebug = "partialApplication"
		silver.definition.core.PpartialApplication.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("partialApplication")); } };
		//ASPECT PRODUCTION attributeSection top ::= '(' '.' q::QName ')' 
		// top.ppDebug = "attributeSection"
		silver.definition.core.PattributeSection.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("attributeSection")); } };
		//ASPECT PRODUCTION forwardAccess top ::= e::Expr '.' 'forward' 
		// top.ppDebug = "forwardAccess"
		silver.definition.core.PforwardAccess.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("forwardAccess")); } };
		//ASPECT PRODUCTION access top ::= e::Expr '.' q::QNameAttrOccur 
		// top.ppDebug = "access"
		silver.definition.core.Paccess.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("access")); } };
		//ASPECT PRODUCTION errorAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "errorAccessHandler"
		silver.definition.core.PerrorAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorAccessHandler")); } };
		//ASPECT PRODUCTION annoAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "annoAccessHandler"
		silver.definition.core.PannoAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("annoAccessHandler")); } };
		//ASPECT PRODUCTION terminalAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "terminalAccessHandler"
		silver.definition.core.PterminalAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("terminalAccessHandler")); } };
		//ASPECT PRODUCTION undecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "undecoratedAccessHandler"
		silver.definition.core.PundecoratedAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("undecoratedAccessHandler")); } };
		//ASPECT PRODUCTION accessBouncer top ::= target::(Expr ::= Decorated Expr Decorated QNameAttrOccur Location) e::Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "accessBouncer"
		silver.definition.core.PaccessBouncer.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("accessBouncer")); } };
		//ASPECT PRODUCTION decoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "decoratedAccessHandler"
		silver.definition.core.PdecoratedAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("decoratedAccessHandler")); } };
		//ASPECT PRODUCTION synDecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "synDecoratedAccessHandler"
		silver.definition.core.PsynDecoratedAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("synDecoratedAccessHandler")); } };
		//ASPECT PRODUCTION inhDecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "inhDecoratedAccessHandler"
		silver.definition.core.PinhDecoratedAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("inhDecoratedAccessHandler")); } };
		//ASPECT PRODUCTION errorDecoratedAccessHandler top ::= e::Decorated Expr q::Decorated QNameAttrOccur 
		// top.ppDebug = "errorDecoratedAccessHandler"
		silver.definition.core.PerrorDecoratedAccessHandler.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorDecoratedAccessHandler")); } };
		//ASPECT PRODUCTION decorateExprWithEmpty top ::= 'decorate' e::Expr 'with' '{' '}' 
		// top.ppDebug = "decorateExprWithEmpty"
		silver.definition.core.PdecorateExprWithEmpty.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("decorateExprWithEmpty")); } };
		//ASPECT PRODUCTION decorateExprWith top ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}' 
		// top.ppDebug = "decorateExprWith"
		silver.definition.core.PdecorateExprWith.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("decorateExprWith")); } };
		//ASPECT PRODUCTION trueConst top ::= 'true' 
		// top.ppDebug = "trueConst"
		silver.definition.core.PtrueConst.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("trueConst")); } };
		//ASPECT PRODUCTION falseConst top ::= 'false' 
		// top.ppDebug = "falseConst"
		silver.definition.core.PfalseConst.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("falseConst")); } };
		//ASPECT PRODUCTION and top ::= e1::Expr '&&' e2::Expr 
		// top.ppDebug = "and"
		silver.definition.core.Pand.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("and")); } };
		//ASPECT PRODUCTION or top ::= e1::Expr '||' e2::Expr 
		// top.ppDebug = "or"
		silver.definition.core.Por.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("or")); } };
		//ASPECT PRODUCTION not top ::= '!' e::Expr 
		// top.ppDebug = "not"
		silver.definition.core.Pnot.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("not")); } };
		//ASPECT PRODUCTION gt top ::= e1::Expr '>' e2::Expr 
		// top.ppDebug = "gt"
		silver.definition.core.Pgt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("gt")); } };
		//ASPECT PRODUCTION lt top ::= e1::Expr '<' e2::Expr 
		// top.ppDebug = "lt"
		silver.definition.core.Plt.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("lt")); } };
		//ASPECT PRODUCTION gteq top ::= e1::Expr '>=' e2::Expr 
		// top.ppDebug = "gteq"
		silver.definition.core.Pgteq.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("gteq")); } };
		//ASPECT PRODUCTION lteq top ::= e1::Expr '<=' e2::Expr 
		// top.ppDebug = "lteq"
		silver.definition.core.Plteq.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("lteq")); } };
		//ASPECT PRODUCTION eqeq top ::= e1::Expr '==' e2::Expr 
		// top.ppDebug = "eqeq"
		silver.definition.core.Peqeq.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("eqeq")); } };
		//ASPECT PRODUCTION neq top ::= e1::Expr '!=' e2::Expr 
		// top.ppDebug = "neq"
		silver.definition.core.Pneq.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("neq")); } };
		//ASPECT PRODUCTION ifThenElse top ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr 
		// top.ppDebug = "ifThenElse"
		silver.definition.core.PifThenElse.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("ifThenElse")); } };
		//ASPECT PRODUCTION intConst top ::= i::Int_t 
		// top.ppDebug = "intConst"
		silver.definition.core.PintConst.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("intConst")); } };
		//ASPECT PRODUCTION floatConst top ::= f::Float_t 
		// top.ppDebug = "floatConst"
		silver.definition.core.PfloatConst.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("floatConst")); } };
		//ASPECT PRODUCTION plus top ::= e1::Expr '+' e2::Expr 
		// top.ppDebug = "plus"
		silver.definition.core.Pplus.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("plus")); } };
		//ASPECT PRODUCTION minus top ::= e1::Expr '-' e2::Expr 
		// top.ppDebug = "minus"
		silver.definition.core.Pminus.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("minus")); } };
		//ASPECT PRODUCTION multiply top ::= e1::Expr '*' e2::Expr 
		// top.ppDebug = "multiply"
		silver.definition.core.Pmultiply.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("multiply")); } };
		//ASPECT PRODUCTION divide top ::= e1::Expr '/' e2::Expr 
		// top.ppDebug = "divide"
		silver.definition.core.Pdivide.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("divide")); } };
		//ASPECT PRODUCTION modulus top ::= e1::Expr '%' e2::Expr 
		// top.ppDebug = "modulus"
		silver.definition.core.Pmodulus.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("modulus")); } };
		//ASPECT PRODUCTION neg top ::= '-' e::Expr 
		// top.ppDebug = "neg"
		silver.definition.core.Pneg.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("neg")); } };
		//ASPECT PRODUCTION stringConst top ::= s::String_t 
		// top.ppDebug = "stringConst"
		silver.definition.core.PstringConst.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("stringConst")); } };
		//ASPECT PRODUCTION plusPlus top ::= e1::Expr '++' e2::Expr 
		// top.ppDebug = "plusPlus"
		silver.definition.core.PplusPlus.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("plusPlus")); } };
		//ASPECT PRODUCTION stringPlusPlus top ::= e1::Decorated Expr e2::Decorated Expr 
		// top.ppDebug = "stringPlusPlus"
		silver.definition.core.PstringPlusPlus.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("stringPlusPlus")); } };
		//ASPECT PRODUCTION errorPlusPlus top ::= e1::Decorated Expr e2::Decorated Expr 
		// top.ppDebug = "errorPlusPlus"
		silver.definition.core.PerrorPlusPlus.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorPlusPlus")); } };
		//ASPECT PRODUCTION lengthFunction top ::= 'length' '(' e::Expr ')' 
		// top.ppDebug = "lengthFunction"
		silver.definition.core.PlengthFunction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("lengthFunction")); } };
		//ASPECT PRODUCTION errorLength top ::= e::Decorated Expr 
		// top.ppDebug = "errorLength"
		silver.definition.core.PerrorLength.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("errorLength")); } };
		//ASPECT PRODUCTION stringLength top ::= e::Decorated Expr 
		// top.ppDebug = "stringLength"
		silver.definition.core.PstringLength.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("stringLength")); } };
		//ASPECT PRODUCTION toIntFunction top ::= 'toInt' '(' e::Expr ')' 
		// top.ppDebug = "toIntFunction"
		silver.definition.core.PtoIntFunction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("toIntFunction")); } };
		//ASPECT PRODUCTION toFloatFunction top ::= 'toFloat' '(' e::Expr ')' 
		// top.ppDebug = "toFloatFunction"
		silver.definition.core.PtoFloatFunction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("toFloatFunction")); } };
		//ASPECT PRODUCTION toStringFunction top ::= 'toString' '(' e::Expr ')' 
		// top.ppDebug = "toStringFunction"
		silver.definition.core.PtoStringFunction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("toStringFunction")); } };
		//ASPECT PRODUCTION newFunction top ::= 'new' '(' e::Expr ')' 
		// top.ppDebug = "newFunction"
		silver.definition.core.PnewFunction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("newFunction")); } };
		//ASPECT PRODUCTION terminalConstructor top ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')' 
		// top.ppDebug = "terminalConstructor"
		silver.definition.core.PterminalConstructor.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("terminalConstructor")); } };
		//ASPECT PRODUCTION terminalConstructorTemporaryDispatcher top ::= 'terminal' '(' t::TypeExpr ',' es::Expr ',' el::Expr ')' 
		// top.ppDebug = "terminalConstructorTemporaryDispatcher"
		silver.definition.core.PterminalConstructorTemporaryDispatcher.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("terminalConstructorTemporaryDispatcher")); } };
		//ASPECT PRODUCTION terminalFunction top ::= 'terminal' '(' t::TypeExpr ',' e::Expr ')' 
		// top.ppDebug = "terminalFunction"
		silver.definition.core.PterminalFunction.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("terminalFunction")); } };
		//ASPECT PRODUCTION terminalFunctionLineCol top ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ',' e3::Expr ')' 
		// top.ppDebug = "terminalFunctionLineCol"
		silver.definition.core.PterminalFunctionLineCol.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("terminalFunctionLineCol")); } };
		//ASPECT PRODUCTION terminalFunctionInherited top ::= 'terminal' '(' t::TypeExpr ',' e1::Expr ',' e2::Expr ')' 
		// top.ppDebug = "terminalFunctionInherited"
		silver.definition.core.PterminalFunctionInherited.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("terminalFunctionInherited")); } };
		silver.extension.bidirtransform.PtransformRuleCons.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PtransformRuleSingle.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PtransformRule.initProductionAttributeDefinitions();
		//FUNCTION hasTrans Boolean ::= rules::[Decorated TransformRule] dcl::Decorated NamedSignature 
		// hd = head(rules)
		silver.extension.bidirtransform.PhasTrans.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasTrans] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasTrans.i_rules))); } };
		//FUNCTION getTrans Decorated TransformRule ::= rules::[Decorated TransformRule] dcl::Decorated NamedSignature 
		// hd = head(rules)
		silver.extension.bidirtransform.PgetTrans.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_getTrans] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetTrans.i_rules))); } };
		silver.extension.bidirtransform.PapplyTrans.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrestoreExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrestoreAppExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrestoreAppExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillStringConst.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillExprEnd.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillAppExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillAppExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillAnnoExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfillAnnoAppExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PidxOfExprs.initProductionAttributeDefinitions();
		//FUNCTION idxOf a ::= ls::[a] idx::Integer 
		//FUNCTION findIdx Integer ::= ls::[String] item::String 
		//FUNCTION findIdxHelper Integer ::= ls::[String] item::String idx::Integer 
		silver.extension.bidirtransform.PfillExprPattern.initProductionAttributeDefinitions();
		//FUNCTION matchAppExpsToPattern Pair<[Expr] [String]> ::= appexps::AppExprs pattern::[Pattern] 
		//FUNCTION matchAppExpToPattern Pair<[Expr] [String]> ::= appexp::AppExpr pattern::Pattern 
		//FUNCTION matchExpToPattern Pair<[Expr] [String]> ::= e::Expr pattern::Pattern 
		//FUNCTION pullOutAppExprs [Expr] ::= aexprs::AppExprs 
		//FUNCTION joinPair Pair<[c] [d]> ::= a::Pair<[c] [d]> b::Pair<[c] [d]> 
		//FUNCTION lastElemPattern Pattern ::= pl::PatternList 
		//FUNCTION leftTailPattern PatternList ::= pl::PatternList 
		silver.extension.bidirtransform.PfakeAspectProductionDcl.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for Env
		// top.ppDebug = "default Env"
		silver.definition.env.NEnv.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("default Env")); } };
		// top.filteredProds = top
		silver.definition.env.NEnv.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context; } };
		//ASPECT PRODUCTION i_emptyEnv top ::= 
		// top.ppDebug = "<>"
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("<>")); } };
		// top.filteredProds = top
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context; } };
		//ASPECT PRODUCTION i_appendEnv top ::= e1::Decorated Env e2::Decorated Env 
		// top.ppDebug = "<<" ++ e1.ppDebug ++ ">\n<" ++ e2.ppDebug ++ ">>"
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.env.Pi_appendEnv.i_e1)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(">\n<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.env.Pi_appendEnv.i_e2)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env)), (common.StringCatter)(new common.StringCatter(">>")))))); } };
		// top.filteredProds = decorate i_appendEnv(e1.filteredProds, e2.filteredProds) with {}
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NEnv)new silver.definition.env.Pi_appendEnv(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env))).decorate(context, (common.Lazy[])null); } };
		//ASPECT PRODUCTION i_newScopeEnv top ::= d::Defs e::Decorated Env 
		// top.ppDebug = "<" ++ d.ppDebug ++ "," ++ e.ppDebug ++ ">"
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.Pi_newScopeEnv.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Defs)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.env.Pi_newScopeEnv.i_e)).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env)), (common.StringCatter)(new common.StringCatter(">")))))); } };
		// top.filteredProds = decorate i_newScopeEnv(d.filteredProdDefs, e.filteredProds) with {}
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NEnv)new silver.definition.env.Pi_newScopeEnv(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env))).decorate(context, (common.Lazy[])null); } };
		silver.extension.bidirtransform.PrewriteRuleCons.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteRuleSingle.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteRuleProd.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteRuleType.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PshortRewriteArrow.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PlongRewriteArrow.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PoneRHSType.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PnoRHSType.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteRule.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PoriginEq.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PoriginPrdStmt.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION mkProductionDcl top ::= id::Name ns::ProductionSignature body::ProductionBody isAbstract::Boolean 
		// body.prodOutput = ns.namedSignature.outputElement
		silver.definition.core.PmkProductionDcl.childInheritedAttributes[silver.definition.core.PmkProductionDcl.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PmkProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } };
		//ASPECT PRODUCTION aspectProductionDcl top ::= 'aspect' 'production' id::QName ns::AspectProductionSignature body::ProductionBody 
		// body.prodOutput = ns.namedSignature.outputElement
		silver.definition.core.PaspectProductionDcl.childInheritedAttributes[silver.definition.core.PaspectProductionDcl.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PaspectProductionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_AspectProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } };
		//ASPECT PRODUCTION aspectFunctionDcl top ::= 'aspect' 'function' id::QName ns::AspectFunctionSignature body::ProductionBody 
		// body.prodOutput = ns.namedSignature.outputElement
		silver.definition.core.PaspectFunctionDcl.childInheritedAttributes[silver.definition.core.PaspectFunctionDcl.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PaspectFunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_AspectFunctionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } };
		//ASPECT PRODUCTION functionDcl top ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 
		// body.prodOutput = ns.namedSignature.outputElement
		silver.definition.core.PfunctionDcl.childInheritedAttributes[silver.definition.core.PfunctionDcl.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.definition.core.PfunctionDcl.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_FunctionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } };
		//ASPECT PRODUCTION functionDclFFI top ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}' 
		// body.prodOutput = ns.namedSignature.outputElement
		silver.modification.ffi.PfunctionDclFFI.childInheritedAttributes[silver.modification.ffi.PfunctionDclFFI.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.env.NNamedSignature)context.childDecorated(silver.modification.ffi.PfunctionDclFFI.i_ns).synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_FunctionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)); } };
		//ASPECT PRODUCTION aspectDefaultProduction top ::= 'aspect' d::Default_kwd 'production' lhs::Name '::' te::TypeExpr '::=' body::ProductionBody 
		// body.prodOutput = namedSignatureElement(lhs.name, te.typerep)
		silver.modification.defaultattr.PaspectDefaultProduction.childInheritedAttributes[silver.modification.defaultattr.PaspectDefaultProduction.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)new silver.definition.env.PnamedSignatureElement(context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_lhs, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.modification.defaultattr.PaspectDefaultProduction.i_te, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } };
		//ASPECT PRODUCTION productionDclStmt top ::= optn::OptionalName v::ProdVBar rhs::ProductionRHS mods::ProductionModifiers body::ProductionBody opta::OptionalAction 
		// body.prodOutput = top.lhsdcl.outputElement
		silver.extension.convenience.PproductionDclStmt.childInheritedAttributes[silver.extension.convenience.PproductionDclStmt.i_body][silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)((silver.definition.core.NProductionLHS)context.inherited(silver.extension.convenience.Init.silver_extension_convenience_lhsdcl__ON__silver_extension_convenience_ProductionDclStmt)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.core.Init.silver_definition_env_outputElement__ON__silver_definition_core_ProductionLHS)); } };
		silver.extension.bidirtransform.PnonterminalGroup.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PsingleNt.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PconsNt.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PfullNt.initProductionAttributeDefinitions();
		//FUNCTION filterSigs [Decorated NamedSignature] ::= nm::String toFilter::[Decorated NamedSignature] 
		// hd = head(toFilter)
		silver.extension.bidirtransform.PfilterSigs.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterSigs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterSigs.i_toFilter))); } };
		//FUNCTION prodsFromDefs [Decorated NamedSignature] ::= defs::[Def] 
		//FUNCTION prodFromDef [Decorated NamedSignature] ::= def::Def 
		//FUNCTION absProdsFromDefs [Decorated NamedSignature] ::= defs::[Def] 
		//FUNCTION absProdFromDef [Decorated NamedSignature] ::= def::Def 
		//FUNCTION cncProdsFromDefs [Decorated NamedSignature] ::= defs::[Def] 
		//FUNCTION cncProdFromDef [Decorated NamedSignature] ::= def::Def 
		//FUNCTION absProdsFromDcls [Decorated NamedSignature] ::= dcls::[DclInfo] 
		//FUNCTION cncProdsFromDcls [Decorated NamedSignature] ::= dcls::[DclInfo] 
		//FUNCTION absProdFromDcl [Decorated NamedSignature] ::= dcl::DclInfo 
		//FUNCTION cncProdFromDcl [Decorated NamedSignature] ::= dcl::DclInfo 
		//FUNCTION prodsFromDcls [Decorated NamedSignature] ::= dcls::[DclInfo] 
		//FUNCTION prodFromDcl [Decorated NamedSignature] ::= dcl::DclInfo 
		//FUNCTION hasRwMatch Boolean ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature 
		//FUNCTION rwMatch Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature 
		//FUNCTION hasRwProd Boolean ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature 
		// hd = head(rwrs)
		silver.extension.bidirtransform.PhasRwProd.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwProd] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwProd.i_rwrs))); } };
		//FUNCTION hasRwID Boolean ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
		//FUNCTION hasRwEq Boolean ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
		// hd = head(rwrs)
		silver.extension.bidirtransform.PhasRwEq.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwEq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwEq.i_rwrs))); } };
		//FUNCTION hasRwOut Boolean ::= rwrs::[Decorated RewriteRule] outType::String 
		// hd = head(rwrs)
		silver.extension.bidirtransform.PhasRwOut.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_hasRwOut] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PhasRwOut.i_rwrs))); } };
		//FUNCTION rwProd Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] outType::String ns::Decorated NamedSignature 
		// hd = head(rwrs)
		silver.extension.bidirtransform.PrwProd.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_rwProd] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrwProd.i_rwrs))); } };
		//FUNCTION rwID Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
		//FUNCTION rwEq Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] inType::String outType::String 
		// hd = head(rwrs)
		silver.extension.bidirtransform.PrwEq.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_rwEq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrwEq.i_rwrs))); } };
		//FUNCTION rwOut Decorated RewriteRule ::= rwrs::[Decorated RewriteRule] outType::String 
		// hd = head(rwrs)
		silver.extension.bidirtransform.PrwOut.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_rwOut] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PrwOut.i_rwrs))); } };
		silver.extension.bidirtransform.PproductionDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmatchProd.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PmatchSingle.initProductionAttributeDefinitions();
		//FUNCTION tyCheckProd [Message] ::= loc::Location args::[Pattern] nsElems::[NamedSignatureElement] 
		silver.extension.bidirtransform.PntGroupDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PntGroupDcl.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PapplyRw.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PapplyRwOrigin.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PapplyRwProd.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PoriginDcl.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PcncOriginDcl.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PcncApplyOrigins.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PapplyOrigins.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteProduction.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PemptyRewriteProduction.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteProductionArgSingle.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PrewriteProductionArgMany.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PlockAGDcls.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for Def
		// top.isLock = false
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isLock__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.extension.bidirtransform.PlockDef.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PtransformAGDclFull.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for Def
		// top.ppDebug = "defaultDef"
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("defaultDef")); } };
		// top.isProdDef = false
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isProdDef__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.prodNamedSig = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.absProdNamedSig = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.cncProdNamedSig = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION typeDef top ::= d::EnvItem 
		// top.ppDebug = "typeDef: " ++ d.ppDebug
		silver.definition.env.PtypeDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("typeDef: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PtypeDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem))); } };
		//ASPECT PRODUCTION valueDef top ::= d::EnvItem 
		// top.ppDebug = "valueDef: " ++ d.ppDebug
		silver.definition.env.PvalueDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("valueDef: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PvalueDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem))); } };
		//ASPECT PRODUCTION attrDef top ::= d::EnvItem 
		// top.ppDebug = "attrDef: " ++ d.ppDebug
		silver.definition.env.PattrDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("attrDef: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PattrDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem))); } };
		//ASPECT PRODUCTION prodDclDef top ::= d::EnvItem 
		// top.ppDebug = "prodDclDef: " ++ d.ppDebug
		silver.definition.env.PprodDclDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prodDclDef: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PprodDclDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem))); } };
		// top.isProdDef = true
		silver.definition.env.PprodDclDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isProdDef__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.prodNamedSig = d.prodNamedSig
		silver.definition.env.PprodDclDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PprodDclDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem)); } };
		// top.absProdNamedSig = d.absProdNamedSig
		silver.definition.env.PprodDclDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PprodDclDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem)); } };
		// top.cncProdNamedSig = d.cncProdNamedSig
		silver.definition.env.PprodDclDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PprodDclDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem)); } };
		//ASPECT PRODUCTION paDef top ::= d::DclInfo 
		// top.ppDebug = "paDef: " ++ d.ppDebug
		silver.definition.env.PpaDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("paDef: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PpaDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo))); } };
		//ASPECT PRODUCTION oDef top ::= d::DclInfo 
		// top.ppDebug = "oDef: " ++ d.ppDebug
		silver.definition.env.PoDef.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("oDef: ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PoDef.i_d).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo))); } };
		//ASPECT PRODUCTION nilDefs top ::= 
		// top.ppDebug = "nilDefs"
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("nilDefs")); } };
		// top.filteredProdDefs = top
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		//ASPECT PRODUCTION consDefs top ::= e1::Def e2::Defs 
		// top.ppDebug = "consDefs(" ++ e1.ppDebug ++ "," ++ e2.ppDebug ++ ")"
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("consDefs(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PconsDefs.i_e1).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(",")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PconsDefs.i_e2).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Defs)), (common.StringCatter)(new common.StringCatter(")")))))); } };
		// top.filteredProdDefs = if e1.isProdDef then consDefs(e1, e2.filteredProdDefs) else e2.filteredProdDefs
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childDecorated(silver.definition.env.PconsDefs.i_e1).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_isProdDef__ON__silver_definition_env_Def)) ? ((silver.definition.env.NDefs)new silver.definition.env.PconsDefs(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PconsDefs.i_e1)), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs))) : ((silver.definition.env.NDefs)context.childDecorated(silver.definition.env.PconsDefs.i_e2).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs))); } };
		silver.extension.bidirtransform.PinjectAnnos.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PinjectApplication.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PinjectAppExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PinjectAppExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PinjectAnnoExprs.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PinjectAnnoExpr.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PlocOrigin.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PtxtOrigin.initProductionAttributeDefinitions();
		silver.extension.bidirtransform.PbottomOrigin.initProductionAttributeDefinitions();
		//FUNCTION wasTransformed Boolean ::= node::Origin redex::Maybe<Origin> 
		//FUNCTION getConcreteOrigin Origin ::= node::Origin root::Origin 
		//ASPECT DEFAULT PRODUCTION for DclInfo
		// top.ppDebug = "defaultDclInfo"
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("defaultDclInfo")); } };
		// top.prodNamedSig = []
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.absProdNamedSig = []
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.cncProdNamedSig = []
		silver.definition.env.NDclInfo.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION childDcl top ::= sg::String sl::Location fn::String ty::Type 
		// top.ppDebug = "childDcl " ++ fn
		silver.definition.env.PchildDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("childDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PchildDcl.i_fn))); } };
		//ASPECT PRODUCTION lhsDcl top ::= sg::String sl::Location fn::String ty::Type 
		// top.ppDebug = "lhsDcl " ++ fn
		silver.definition.env.PlhsDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("lhsDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PlhsDcl.i_fn))); } };
		//ASPECT PRODUCTION localDcl top ::= sg::String sl::Location fn::String ty::Type 
		// top.ppDebug = "localDcl " ++ fn
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("localDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PlocalDcl.i_fn))); } };
		//ASPECT PRODUCTION forwardDcl top ::= sg::String sl::Location ty::Type 
		// top.ppDebug = "forwardDcl"
		silver.definition.env.PforwardDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("forwardDcl")); } };
		//ASPECT PRODUCTION prodDcl top ::= sg::String sl::Location ns::NamedSignature isAbstract::Boolean 
		// top.ppDebug = "prodDcl, abs:" ++ if isAbstract then "Y" else "N"
		silver.definition.env.PprodDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("prodDcl, abs:")), (common.StringCatter)(((Boolean)context.childAsIs(silver.definition.env.PprodDcl.i_isAbstract)) ? (new common.StringCatter("Y")) : (new common.StringCatter("N")))); } };
		// top.prodNamedSig = [ ns ]
		silver.definition.env.PprodDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PprodDcl.i_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		// top.absProdNamedSig = if isAbstract then [ ns ] else []
		silver.definition.env.PprodDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(silver.definition.env.PprodDcl.i_isAbstract)) ? ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PprodDcl.i_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		// top.cncProdNamedSig = if ! isAbstract then [ ns ] else []
		silver.definition.env.PprodDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)context.childAsIs(silver.definition.env.PprodDcl.i_isAbstract))) ? ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PprodDcl.i_ns)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } };
		//ASPECT PRODUCTION funDcl top ::= sg::String sl::Location ns::NamedSignature 
		// top.ppDebug = "funDcl"
		silver.definition.env.PfunDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("funDcl")); } };
		//ASPECT PRODUCTION globalValueDcl top ::= sg::String sl::Location fn::String ty::Type 
		// top.ppDebug = "globalValueDcl " ++ fn
		silver.definition.env.PglobalValueDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("globalValueDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PglobalValueDcl.i_fn))); } };
		//ASPECT PRODUCTION ntDcl top ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type closed::Boolean 
		// top.ppDebug = "ntDcl " ++ fn
		silver.definition.env.PntDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("ntDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PntDcl.i_fn))); } };
		//ASPECT PRODUCTION termDcl top ::= sg::String sl::Location fn::String regex::Regex 
		// top.ppDebug = "termDcl " ++ fn
		silver.definition.env.PtermDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("termDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PtermDcl.i_fn))); } };
		//ASPECT PRODUCTION lexTyVarDcl top ::= sg::String sl::Location fn::String ty::Type 
		// top.ppDebug = "lexTyVarDcl " ++ fn
		silver.definition.env.PlexTyVarDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("lexTyVarDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PlexTyVarDcl.i_fn))); } };
		//ASPECT PRODUCTION synDcl top ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		// top.ppDebug = "synDcl " ++ fn
		silver.definition.env.PsynDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("synDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PsynDcl.i_fn))); } };
		//ASPECT PRODUCTION inhDcl top ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		// top.ppDebug = "inhDcl " ++ fn
		silver.definition.env.PinhDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("inhDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PinhDcl.i_fn))); } };
		//ASPECT PRODUCTION annoDcl top ::= sg::String sl::Location fn::String bound::[TyVar] ty::Type 
		// top.ppDebug = "annoDcl " ++ fn
		silver.definition.env.PannoDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("annoDcl ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PannoDcl.i_fn))); } };
		//ASPECT PRODUCTION paDcl top ::= sg::String sl::Location ns::NamedSignature dcls::[Def] 
		// top.ppDebug = "paDcl"
		silver.definition.env.PpaDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("paDcl")); } };
		//ASPECT PRODUCTION occursDcl top ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type 
		// top.ppDebug = "occursDcl " ++ fnnt ++ " " ++ fnat
		silver.definition.env.PoccursDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("occursDcl ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_fnnt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PoccursDcl.i_fnat))))); } };
		//ASPECT PRODUCTION annoInstanceDcl top ::= sg::String sl::Location fnnt::String fnat::String ntty::Type atty::Type 
		// top.ppDebug = "annoInstanceDcl " ++ fnnt ++ " " ++ fnat
		silver.definition.env.PannoInstanceDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("annoInstanceDcl ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PannoInstanceDcl.i_fnnt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PannoInstanceDcl.i_fnat))))); } };
		//FUNCTION dclQName (QName ::= String) ::= loc::Location 
		//FUNCTION mkOriginName String ::= typeName::String 
		//FUNCTION inhRedexNameSig String ::= ns::Decorated NamedSignature allowedTypes::[String] 
		// hd = head(ns.inputElements)
		silver.extension.bidirtransform.PinhRedexNameSig.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_inhRedexNameSig] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(context.childAsIsSynthesizedLazy(silver.extension.bidirtransform.PinhRedexNameSig.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))); } };
		// def = ns.outputElement.elementName
		silver.extension.bidirtransform.PinhRedexNameSig.localAttributes[silver.extension.bidirtransform.Init.def__ON__silver_extension_bidirtransform_inhRedexNameSig] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.env.NNamedSignatureElement)((common.DecoratedNode)context.childAsIs(silver.extension.bidirtransform.PinhRedexNameSig.i_ns)).synthesized(silver.definition.env.Init.silver_definition_env_outputElement__ON__silver_definition_env_NamedSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)); } };
		//FUNCTION allHead [a] ::= ls::[a] 
		//FUNCTION hasLocDcl Boolean ::= dcl::[DclInfo] 
		//FUNCTION hasNamedAttr Boolean ::= tyName::String env::Decorated Env attr::String 
		//FUNCTION containsAttr Boolean ::= dcl::[DclInfo] hasAttr::String 
		//FUNCTION transformNm String ::= tName::String 
		//FUNCTION restoreNm String ::= rName::String 
		//FUNCTION unFull String ::= s::String 
		//FUNCTION inhRedexNm String ::= tName::String 
		//FUNCTION filterDefs [Def] ::= input::[Def] 
		// hd = head(input)
		silver.extension.bidirtransform.PfilterDefs.localAttributes[silver.extension.bidirtransform.Init.hd__ON__silver_extension_bidirtransform_filterDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterDefs.i_input))); } };
		// tl = filterDefs(tail(input))
		silver.extension.bidirtransform.PfilterDefs.localAttributes[silver.extension.bidirtransform.Init.tl__ON__silver_extension_bidirtransform_filterDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PfilterDefs.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PfilterDefs.i_input))); } })); } };
		//FUNCTION headN [a] ::= input::[a] n::Integer 
		//ASPECT DEFAULT PRODUCTION for EnvItem
		// top.ppDebug = "defaultEnvItem"
		silver.definition.env.NEnvItem.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("defaultEnvItem")); } };
		// top.prodNamedSig = []
		silver.definition.env.NEnvItem.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.absProdNamedSig = []
		silver.definition.env.NEnvItem.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.cncProdNamedSig = []
		silver.definition.env.NEnvItem.defaultSynthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION renamedEnvItem ei ::= newname::String di::DclInfo 
		// ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug
		silver.definition.env.PrenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PrenamedEnvItem.i_newname)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PrenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo))))); } };
		// ei.prodNamedSig = di.prodNamedSig
		silver.definition.env.PrenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PrenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo)); } };
		// ei.absProdNamedSig = di.absProdNamedSig
		silver.definition.env.PrenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PrenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo)); } };
		// ei.cncProdNamedSig = di.cncProdNamedSig
		silver.definition.env.PrenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PrenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo)); } };
		//ASPECT PRODUCTION fullNameEnvItem ei ::= di::DclInfo 
		// ei.ppDebug = di.ppDebug
		silver.definition.env.PfullNameEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.env.PfullNameEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo)); } };
		// ei.prodNamedSig = di.prodNamedSig
		silver.definition.env.PfullNameEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PfullNameEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo)); } };
		// ei.absProdNamedSig = di.absProdNamedSig
		silver.definition.env.PfullNameEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PfullNameEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo)); } };
		// ei.cncProdNamedSig = di.cncProdNamedSig
		silver.definition.env.PfullNameEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PfullNameEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo)); } };
		//ASPECT PRODUCTION onlyRenamedEnvItem ei ::= newname::String di::DclInfo 
		// ei.ppDebug = "(" ++ newname ++ ") " ++ di.ppDebug
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PonlyRenamedEnvItem.i_newname)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(") ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PonlyRenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo))))); } };
		// ei.prodNamedSig = di.prodNamedSig
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PonlyRenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo)); } };
		// ei.absProdNamedSig = di.absProdNamedSig
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PonlyRenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo)); } };
		// ei.cncProdNamedSig = di.cncProdNamedSig
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.PonlyRenamedEnvItem.i_di).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo)); } };
		//ASPECT PRODUCTION patternList_one top ::= p::Pattern 
		// top.rawPatternList = [ p ]
		silver.extension.patternmatching.PpatternList_one.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PpatternList_one.i_p)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		//ASPECT PRODUCTION patternList_more top ::= p::Pattern ',' ps1::PatternList 
		// top.rawPatternList = [ p ] ++ ps1.rawPatternList
		silver.extension.patternmatching.PpatternList_more.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PpatternList_more.i_p)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.patternmatching.PpatternList_more.i_ps1, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList))); } };
		//ASPECT PRODUCTION patternList_nil top ::= 
		// top.rawPatternList = []
		silver.extension.patternmatching.PpatternList_nil.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//FUNCTION searchNtGroup [Decorated NonterminalList] ::= fnnt::String e::Decorated Env 
		//FUNCTION defsNtGroup [Decorated NonterminalList] ::= fnnt::String dfs::Defs 
		//FUNCTION skipNtToNextLock [Decorated NonterminalList] ::= fnnt::String dfs::Defs 
		//FUNCTION getProdFromGroup [Decorated NamedSignature] ::= s::String ntlst::Decorated NonterminalList 
		//FUNCTION getProdFromGroups [Decorated NamedSignature] ::= s::String absGroup::Decorated NonterminalList cncGroup::Decorated NonterminalList 
		// absSig = getProdFromGroup(s, absGroup)
		silver.extension.bidirtransform.PgetProdFromGroups.localAttributes[silver.extension.bidirtransform.Init.absSig__ON__silver_extension_bidirtransform_getProdFromGroups] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdFromGroup.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromGroups.i_s), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromGroups.i_absGroup))); } };
		// cncSig = getProdFromGroup(s, cncGroup)
		silver.extension.bidirtransform.PgetProdFromGroups.localAttributes[silver.extension.bidirtransform.Init.cncSig__ON__silver_extension_bidirtransform_getProdFromGroups] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PgetProdFromGroup.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromGroups.i_s), context.childAsIsLazy(silver.extension.bidirtransform.PgetProdFromGroups.i_cncGroup))); } };
		//FUNCTION getProdFromList [Decorated NamedSignature] ::= s::String nts::[Decorated FullNonterminal] 
		//FUNCTION getProdFromSig [Decorated NamedSignature] ::= s::String prds::[Decorated NamedSignature] 
		//FUNCTION getProdsFromNtHack [DclInfo] ::= fnnt::String e::Decorated Env skipGrammar::String 
		//FUNCTION getProdsFromDefs [DclInfo] ::= fnnt::String dfs::Defs skipGrammar::String 
		//FUNCTION getProdsFromConsDefs [DclInfo] ::= fnnt::String d::Def dfs::Defs skipGrammar::String 
		//FUNCTION skipToNextLock [DclInfo] ::= fnnt::String dfs::Defs skipGrammar::String 
		//FUNCTION getProdsFromConsDefs2 [DclInfo] ::= fnnt::String d::Def dfs::Defs skipGrammar::String 
		//FUNCTION getProdsFromConsDefs3 [DclInfo] ::= fnnt::String d::Def dfs::Defs skipGrammar::String 
		//FUNCTION getProdsFromProdDcl [DclInfo] ::= fnnt::String d::Def dfs::Defs sg::String ns::Decorated NamedSignature skipGrammar::String 
		//FUNCTION getProdsFromProdDcl2 [DclInfo] ::= fnnt::String d::Def dfs::Defs ns::Decorated NamedSignature skipGrammar::String 
		//FUNCTION getProdsFromDefs2 [DclInfo] ::= fnnt::String dfs::Defs skipGrammar::String 
		//FUNCTION nsMatchesStr Boolean ::= ns::Decorated NamedSignature fnnt::String 
		//FUNCTION nsMatchesStr2 Boolean ::= oe::NamedSignatureElement fnnt::String 
		//FUNCTION nsMatchesStr3 Boolean ::= ty::Type fnnt::String 
	}

	public static int count_local__ON__silver_extension_bidirtransform_qAttr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_emptyFunc = 0;
	public static int count_local__ON__silver_extension_bidirtransform_argFunc = 0;
	public static int count_local__ON__silver_extension_bidirtransform_oneArgFunc = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fullFunc = 0;
	public static int count_local__ON__silver_extension_bidirtransform_synAttr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkLhsDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_autocAttr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_annoOn = 0;
	public static int count_local__ON__silver_extension_bidirtransform_attrOn = 0;
	public static int count_local__ON__silver_extension_bidirtransform_consAnnoAppExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsAspectProdSig = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsAspectProdRHS = 0;
	public static int count_local__ON__silver_extension_bidirtransform_aspectProdStmt = 0;
	public static int count_local__ON__silver_extension_bidirtransform_aspectProdStmts = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prdStmtList = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prdBody = 0;
	public static int count_local__ON__silver_extension_bidirtransform_attribDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_synChdAttrDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_inhChdAttrDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_synAttrDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_qnTyId = 0;
	public static int count_local__ON__silver_extension_bidirtransform_sTyExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_sTyExprDec = 0;
	public static int count_local__ON__silver_extension_bidirtransform_decTyExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_qTyExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_lhsAccess = 0;
	public static int count_local__ON__silver_extension_bidirtransform_namedAccess = 0;
	public static int count_local__ON__silver_extension_bidirtransform_lhsExprAccess = 0;
	public static int count_local__ON__silver_extension_bidirtransform_qAccess = 0;
	public static int count_local__ON__silver_extension_bidirtransform_exprAccess = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkOrigin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_oneApp = 0;
	public static int count_local__ON__silver_extension_bidirtransform_argTransAttrs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_builtinAccess = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prdRecurse = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkQName = 0;
	public static int count_local__ON__silver_extension_bidirtransform_baseName = 0;
	public static int count_local__ON__silver_extension_bidirtransform_presentName = 0;
	public static int count_local__ON__silver_extension_bidirtransform_appExprList = 0;
	public static int count_local__ON__silver_extension_bidirtransform_annoAppExprList = 0;
	public static int count_local__ON__silver_extension_bidirtransform_annExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsApply = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsElemsToAppExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsElemToAppExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_botlOneString = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkProdSig = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkProdSigDec = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkAspectProdSig = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkAspectProdSigDec = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkFalse = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkTrue = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkBoolTypeExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkMaybeTypeExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkCond = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkNew = 0;
	public static int count_local__ON__silver_extension_bidirtransform_joinAGDcls = 0;
	public static int count_local__ON__silver_extension_bidirtransform_strAppExprs = 0;
	public static int count_inh__ON__QNameList = 0;
	public static int count_syn__ON__QNameList = 0;
	public static int count_local__ON__silver_extension_bidirtransform_qNameListSingle = 0;
	public static int count_local__ON__silver_extension_bidirtransform_qNameListCons = 0;
	public static int count_local__ON__silver_extension_bidirtransform_qNameListEmpty = 0;
	public static int count_inh__ON__TransformRuleList = 0;
	public static int count_syn__ON__TransformRuleList = 0;
	public static int count_inh__ON__TransformRule = 0;
	public static int count_syn__ON__TransformRule = 0;
	public static int count_local__ON__silver_extension_bidirtransform_transformRuleCons = 0;
	public static int count_local__ON__silver_extension_bidirtransform_transformRuleSingle = 0;
	public static int count_local__ON__silver_extension_bidirtransform_transformRule = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasTrans = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getTrans = 0;
	public static int count_local__ON__silver_extension_bidirtransform_applyTrans = 0;
	public static int count_local__ON__silver_extension_bidirtransform_restoreExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_restoreAppExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_restoreAppExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillStringConst = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillExprEnd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillAppExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillAppExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillAnnoExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillAnnoAppExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_idxOfExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_idxOf = 0;
	public static int count_local__ON__silver_extension_bidirtransform_findIdx = 0;
	public static int count_local__ON__silver_extension_bidirtransform_findIdxHelper = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fillExprPattern = 0;
	public static int count_local__ON__silver_extension_bidirtransform_matchAppExpsToPattern = 0;
	public static int count_local__ON__silver_extension_bidirtransform_matchAppExpToPattern = 0;
	public static int count_local__ON__silver_extension_bidirtransform_matchExpToPattern = 0;
	public static int count_local__ON__silver_extension_bidirtransform_pullOutAppExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_joinPair = 0;
	public static int count_local__ON__silver_extension_bidirtransform_lastElemPattern = 0;
	public static int count_local__ON__silver_extension_bidirtransform_leftTailPattern = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = 0;
	public static int count_inh__ON__RewriteRuleList = 0;
	public static int count_syn__ON__RewriteRuleList = 0;
	public static int count_inh__ON__RewriteRule = 0;
	public static int count_syn__ON__RewriteRule = 0;
	public static int count_inh__ON__RewriteProduction = 0;
	public static int count_syn__ON__RewriteProduction = 0;
	public static int count_inh__ON__RewriteProductionArgs = 0;
	public static int count_syn__ON__RewriteProductionArgs = 0;
	public static int count_inh__ON__RewriteArrow = 0;
	public static int count_syn__ON__RewriteArrow = 0;
	public static int count_inh__ON__OptRHSType = 0;
	public static int count_syn__ON__OptRHSType = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteRuleCons = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteRuleSingle = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteRuleProd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteRuleType = 0;
	public static int count_local__ON__silver_extension_bidirtransform_shortRewriteArrow = 0;
	public static int count_local__ON__silver_extension_bidirtransform_longRewriteArrow = 0;
	public static int count_local__ON__silver_extension_bidirtransform_oneRHSType = 0;
	public static int count_local__ON__silver_extension_bidirtransform_noRHSType = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteRule = 0;
	public static int count_local__ON__silver_extension_bidirtransform_originEq = 0;
	public static int count_local__ON__silver_extension_bidirtransform_originPrdStmt = 0;
	public static int count_inh__ON__FullNonterminal = 0;
	public static int count_syn__ON__FullNonterminal = 0;
	public static int count_inh__ON__NonterminalList = 0;
	public static int count_syn__ON__NonterminalList = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nonterminalGroup = 0;
	public static int count_local__ON__silver_extension_bidirtransform_singleNt = 0;
	public static int count_local__ON__silver_extension_bidirtransform_consNt = 0;
	public static int count_local__ON__silver_extension_bidirtransform_fullNt = 0;
	public static int count_local__ON__silver_extension_bidirtransform_filterSigs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prodsFromDefs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prodFromDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_absProdsFromDefs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_absProdFromDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_cncProdsFromDefs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_cncProdFromDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_absProdsFromDcls = 0;
	public static int count_local__ON__silver_extension_bidirtransform_cncProdsFromDcls = 0;
	public static int count_local__ON__silver_extension_bidirtransform_absProdFromDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_cncProdFromDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prodsFromDcls = 0;
	public static int count_local__ON__silver_extension_bidirtransform_prodFromDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasRwMatch = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rwMatch = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasRwProd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasRwID = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasRwEq = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasRwOut = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rwProd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rwID = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rwEq = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rwOut = 0;
	public static int count_inh__ON__ProductionDef = 0;
	public static int count_syn__ON__ProductionDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_productionDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_matchProd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_matchSingle = 0;
	public static int count_local__ON__silver_extension_bidirtransform_tyCheckProd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_ntGroupDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_ntGroupDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_applyRw = 0;
	public static int count_local__ON__silver_extension_bidirtransform_applyRwOrigin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_applyRwProd = 0;
	public static int count_local__ON__silver_extension_bidirtransform_originDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_cncOriginDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_cncApplyOrigins = 0;
	public static int count_local__ON__silver_extension_bidirtransform_applyOrigins = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteProduction = 0;
	public static int count_local__ON__silver_extension_bidirtransform_emptyRewriteProduction = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteProductionArgSingle = 0;
	public static int count_local__ON__silver_extension_bidirtransform_rewriteProductionArgMany = 0;
	public static int count_local__ON__silver_extension_bidirtransform_lockAGDcls = 0;
	public static int count_local__ON__silver_extension_bidirtransform_lockDef = 0;
	public static int count_local__ON__silver_extension_bidirtransform_transformAGDclFull = 0;
	public static int count_local__ON__silver_extension_bidirtransform_injectAnnos = 0;
	public static int count_local__ON__silver_extension_bidirtransform_injectApplication = 0;
	public static int count_local__ON__silver_extension_bidirtransform_injectAppExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_injectAppExpr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_injectAnnoExprs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_injectAnnoExpr = 0;
	public static int count_inh__ON__Origin = 0;
	public static int count_syn__ON__Origin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_locOrigin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_txtOrigin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_bottomOrigin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_wasTransformed = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getConcreteOrigin = 0;
	public static int count_local__ON__silver_extension_bidirtransform_dclQName = 0;
	public static int count_local__ON__silver_extension_bidirtransform_mkOriginName = 0;
	public static int count_local__ON__silver_extension_bidirtransform_inhRedexNameSig = 0;
	public static int count_local__ON__silver_extension_bidirtransform_allHead = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasLocDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_hasNamedAttr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_containsAttr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_transformNm = 0;
	public static int count_local__ON__silver_extension_bidirtransform_restoreNm = 0;
	public static int count_local__ON__silver_extension_bidirtransform_unFull = 0;
	public static int count_local__ON__silver_extension_bidirtransform_inhRedexNm = 0;
	public static int count_local__ON__silver_extension_bidirtransform_filterDefs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_headN = 0;
	public static int count_local__ON__silver_extension_bidirtransform_searchNtGroup = 0;
	public static int count_local__ON__silver_extension_bidirtransform_defsNtGroup = 0;
	public static int count_local__ON__silver_extension_bidirtransform_skipNtToNextLock = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdFromGroup = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdFromGroups = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdFromList = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdFromSig = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromNtHack = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromDefs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromConsDefs = 0;
	public static int count_local__ON__silver_extension_bidirtransform_skipToNextLock = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromConsDefs2 = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromConsDefs3 = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromProdDcl = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromProdDcl2 = 0;
	public static int count_local__ON__silver_extension_bidirtransform_getProdsFromDefs2 = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsMatchesStr = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsMatchesStr2 = 0;
	public static int count_local__ON__silver_extension_bidirtransform_nsMatchesStr3 = 0;
public static final int qntlName__ON__silver_extension_bidirtransform_annoOn = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_annoOn++;
public static final int qntOnNames__ON__silver_extension_bidirtransform_annoOn = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_annoOn++;
public static final int hd__ON__silver_extension_bidirtransform_nsAspectProdRHS = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_nsAspectProdRHS++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_QNameList = silver.extension.bidirtransform.Init.count_syn__ON__QNameList++;
public static final int silver_extension_bidirtransform_qList__ON__silver_extension_bidirtransform_QNameList = silver.extension.bidirtransform.Init.count_syn__ON__QNameList++;
public static final int silver_extension_bidirtransform_ppDebug__ON__silver_definition_core_Expr = silver.definition.core.Init.count_syn__ON__Expr++;
public static final int silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_syn__ON__TransformRuleList++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_syn__ON__TransformRuleList++;
public static final int silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_syn__ON__TransformRuleList++;
public static final int silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_syn__ON__TransformRuleList++;
public static final int silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_TransformRuleList = silver.extension.bidirtransform.Init.count_inh__ON__TransformRuleList++;
public static final int silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int silver_extension_bidirtransform_asExpr__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_syn__ON__TransformRule++;
public static final int silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_TransformRule = silver.extension.bidirtransform.Init.count_inh__ON__TransformRule++;
public static final int hd__ON__silver_extension_bidirtransform_hasTrans = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_hasTrans++;
public static final int hd__ON__silver_extension_bidirtransform_getTrans = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_getTrans++;
public static final int trans__ON__silver_extension_bidirtransform_applyTrans = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyTrans++;
public static final int idx__ON__silver_extension_bidirtransform_fillStringConst = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fillStringConst++;
public static final int inputs__ON__silver_extension_bidirtransform_fillExprPattern = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fillExprPattern++;
public static final int namedSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int realSig__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int sigDefs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int prodAtts__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int allLexicalTyVars__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int myGraphs__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int myFlowGraph__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int errCheck1__ON__silver_extension_bidirtransform_fakeAspectProductionDcl = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_fakeAspectProductionDcl++;
public static final int silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_extension_bidirtransform_filteredProds__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_extension_bidirtransform_rewriteRules__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRuleList++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRuleList++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRuleList++;
public static final int silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRuleList++;
public static final int silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRuleList++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRuleList++;
public static final int silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRuleList++;
public static final int silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRuleList++;
public static final int silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRuleList++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteRuleList = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRuleList++;
public static final int silver_extension_bidirtransform_inputType__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_inputProduction__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_hasProduction__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_outputStmt__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_restoreStmt__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRule++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRule++;
public static final int silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRule++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRule++;
public static final int silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_syn__ON__RewriteRule++;
public static final int silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRule++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteRule = silver.extension.bidirtransform.Init.count_inh__ON__RewriteRule++;
public static final int silver_definition_core_name__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProduction++;
public static final int silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProduction++;
public static final int silver_definition_env_typerep__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProduction++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_inh__ON__RewriteProduction++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProduction++;
public static final int silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_inh__ON__RewriteProduction++;
public static final int silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_inh__ON__RewriteProduction++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProduction++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_inh__ON__RewriteProduction++;
public static final int silver_extension_bidirtransform_decSig__ON__silver_extension_bidirtransform_RewriteProduction = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProduction++;
public static final int silver_definition_env_inputNames__ON__silver_extension_bidirtransform_RewriteProductionArgs = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProductionArgs++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_RewriteProductionArgs = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProductionArgs++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_RewriteProductionArgs = silver.extension.bidirtransform.Init.count_syn__ON__RewriteProductionArgs++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_RewriteProductionArgs = silver.extension.bidirtransform.Init.count_inh__ON__RewriteProductionArgs++;
public static final int silver_extension_bidirtransform_shouldRestore__ON__silver_extension_bidirtransform_RewriteArrow = silver.extension.bidirtransform.Init.count_syn__ON__RewriteArrow++;
public static final int silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_RewriteArrow = silver.extension.bidirtransform.Init.count_syn__ON__RewriteArrow++;
public static final int silver_extension_bidirtransform_rhsType__ON__silver_extension_bidirtransform_OptRHSType = silver.extension.bidirtransform.Init.count_syn__ON__OptRHSType++;
public static final int rhsType__ON__silver_extension_bidirtransform_rewriteRuleProd = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rewriteRuleProd++;
public static final int rhsType__ON__silver_extension_bidirtransform_rewriteRuleType = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rewriteRuleType++;
public static final int rhsNs__ON__silver_extension_bidirtransform_rewriteRule = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rewriteRule++;
public static final int newAnnos__ON__silver_extension_bidirtransform_originEq = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_originEq++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionStmt = silver.definition.core.Init.count_inh__ON__ProductionStmt++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionStmts = silver.definition.core.Init.count_inh__ON__ProductionStmts++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_Expr = silver.definition.core.Init.count_inh__ON__Expr++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AppExpr = silver.definition.core.Init.count_inh__ON__AppExpr++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AppExprs = silver.definition.core.Init.count_inh__ON__AppExprs++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AnnoExpr = silver.definition.core.Init.count_inh__ON__AnnoExpr++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AnnoAppExprs = silver.definition.core.Init.count_inh__ON__AnnoAppExprs++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ForwardInh = silver.definition.core.Init.count_inh__ON__ForwardInh++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ForwardInhs = silver.definition.core.Init.count_inh__ON__ForwardInhs++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ExprInh = silver.definition.core.Init.count_inh__ON__ExprInh++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ExprInhs = silver.definition.core.Init.count_inh__ON__ExprInhs++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_modification_let_fix_AssignExpr = silver.modification.let_fix.Init.count_inh__ON__AssignExpr++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_modification_primitivepattern_PrimPattern = silver.modification.primitivepattern.Init.count_inh__ON__PrimPattern++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_modification_primitivepattern_PrimPatterns = silver.modification.primitivepattern.Init.count_inh__ON__PrimPatterns++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_ProductionBody = silver.definition.core.Init.count_inh__ON__ProductionBody++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_inh__ON__AGDcl++;
public static final int silver_extension_bidirtransform_prodOutput__ON__silver_extension_convenience_ProductionDclStmt = silver.extension.convenience.Init.count_inh__ON__ProductionDclStmt++;
public static final int silver_extension_bidirtransform_grantedDefs__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_inh__ON__AGDcl++;
public static final int silver_definition_core_name__ON__silver_extension_bidirtransform_FullNonterminal = silver.extension.bidirtransform.Init.count_syn__ON__FullNonterminal++;
public static final int silver_extension_bidirtransform_ntProds__ON__silver_extension_bidirtransform_FullNonterminal = silver.extension.bidirtransform.Init.count_syn__ON__FullNonterminal++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_FullNonterminal = silver.extension.bidirtransform.Init.count_syn__ON__FullNonterminal++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_FullNonterminal = silver.extension.bidirtransform.Init.count_inh__ON__FullNonterminal++;
public static final int silver_extension_bidirtransform_grantedDefs__ON__silver_extension_bidirtransform_FullNonterminal = silver.extension.bidirtransform.Init.count_inh__ON__FullNonterminal++;
public static final int silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList = silver.extension.bidirtransform.Init.count_syn__ON__NonterminalList++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_NonterminalList = silver.extension.bidirtransform.Init.count_syn__ON__NonterminalList++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_NonterminalList = silver.extension.bidirtransform.Init.count_inh__ON__NonterminalList++;
public static final int silver_extension_bidirtransform_grantedDefs__ON__silver_extension_bidirtransform_NonterminalList = silver.extension.bidirtransform.Init.count_inh__ON__NonterminalList++;
public static final int hd__ON__silver_extension_bidirtransform_filterSigs = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_filterSigs++;
public static final int hd__ON__silver_extension_bidirtransform_hasRwProd = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_hasRwProd++;
public static final int hd__ON__silver_extension_bidirtransform_hasRwEq = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_hasRwEq++;
public static final int hd__ON__silver_extension_bidirtransform_hasRwOut = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_hasRwOut++;
public static final int hd__ON__silver_extension_bidirtransform_rwProd = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rwProd++;
public static final int hd__ON__silver_extension_bidirtransform_rwEq = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rwEq++;
public static final int hd__ON__silver_extension_bidirtransform_rwOut = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rwOut++;
public static final int silver_definition_env_env__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_inh__ON__ProductionDef++;
public static final int silver_definition_core_errors__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_namedSig__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_patternList__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_matchProd__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_definition_env_typerep__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_definition_env_inputNames__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_absGroup__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_inh__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_cncGroup__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_inh__ON__ProductionDef++;
public static final int silver_definition_env_pp__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_definition_core_grammarName__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_inh__ON__ProductionDef++;
public static final int silver_definition_env_config__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_inh__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_asExpr__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_syn__ON__ProductionDef++;
public static final int silver_extension_bidirtransform_inhProds__ON__silver_extension_bidirtransform_ProductionDef = silver.extension.bidirtransform.Init.count_inh__ON__ProductionDef++;
public static final int prodNames__ON__silver_extension_bidirtransform_productionDef = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_productionDef++;
public static final int idx__ON__silver_extension_bidirtransform_productionDef = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_productionDef++;
public static final int fwdFunc__ON__silver_extension_bidirtransform_applyRwProd = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyRwProd++;
public static final int cncNames__ON__silver_extension_bidirtransform_cncApplyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_cncApplyOrigins++;
public static final int agDcls__ON__silver_extension_bidirtransform_cncApplyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_cncApplyOrigins++;
public static final int agDcls2__ON__silver_extension_bidirtransform_cncApplyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_cncApplyOrigins++;
public static final int agDcls3__ON__silver_extension_bidirtransform_cncApplyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_cncApplyOrigins++;
public static final int absNames__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int agDcls__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int agDcls2__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int agDcls3__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int agDcls4__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int agDcls5__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int agDcls6__ON__silver_extension_bidirtransform_applyOrigins = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_applyOrigins++;
public static final int absSig__ON__silver_extension_bidirtransform_rewriteProduction = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rewriteProduction++;
public static final int cncSig__ON__silver_extension_bidirtransform_rewriteProduction = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_rewriteProduction++;
public static final int silver_extension_bidirtransform_isLock__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int tName__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int groupEnv__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int absGroup__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int cncGroup__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int absNames__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int cncNames__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int locCncNames__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int nonLocCncNames__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int allNames__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int absProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int cncProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int locCncProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int nonLocCncProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int allProdDcls__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int absProdNames__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int log__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int inhRedexName__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls1__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls2__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls3__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls4__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls5__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls6__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls7__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls8__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int newRwRules__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls9__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls10__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls11__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls12__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls13__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls14__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls15__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int agDcls16__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int toForward__ON__silver_extension_bidirtransform_transformAGDclFull = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_transformAGDclFull++;
public static final int silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_extension_bidirtransform_filteredProdDefs__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_extension_bidirtransform_isProdDef__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int baseAppExprs__ON__silver_extension_bidirtransform_injectApplication = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_injectApplication++;
public static final int baseAnnoExprs__ON__silver_extension_bidirtransform_injectApplication = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_injectApplication++;
public static final int silver_extension_bidirtransform_wasTransformed__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int silver_extension_bidirtransform_concreteOrigin__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int silver_extension_bidirtransform_isBottomOrigin__ON__silver_extension_bidirtransform_Origin = silver.extension.bidirtransform.Init.count_syn__ON__Origin++;
public static final int silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_DclInfo = silver.definition.env.Init.count_syn__ON__DclInfo++;
public static final int hd__ON__silver_extension_bidirtransform_inhRedexNameSig = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_inhRedexNameSig++;
public static final int def__ON__silver_extension_bidirtransform_inhRedexNameSig = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_inhRedexNameSig++;
public static final int hd__ON__silver_extension_bidirtransform_filterDefs = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_filterDefs++;
public static final int tl__ON__silver_extension_bidirtransform_filterDefs = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_filterDefs++;
public static final int silver_extension_bidirtransform_ppDebug__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int silver_extension_bidirtransform_prodNamedSig__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int silver_extension_bidirtransform_absProdNamedSig__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int silver_extension_bidirtransform_cncProdNamedSig__ON__silver_definition_env_EnvItem = silver.definition.env.Init.count_syn__ON__EnvItem++;
public static final int silver_extension_bidirtransform_rawPatternList__ON__silver_extension_patternmatching_PatternList = silver.extension.patternmatching.Init.count_syn__ON__PatternList++;
public static final int absSig__ON__silver_extension_bidirtransform_getProdFromGroups = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_getProdFromGroups++;
public static final int cncSig__ON__silver_extension_bidirtransform_getProdFromGroups = silver.extension.bidirtransform.Init.count_local__ON__silver_extension_bidirtransform_getProdFromGroups++;
}
