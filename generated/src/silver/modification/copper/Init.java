package silver.modification.copper;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.extension.easyterminal.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.util.cmdargs.Init.initAllStatics();
		silver.translation.java.driver.Init.initAllStatics();
		silver.driver.Init.initAllStatics();
		silver.util.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.modification.copper.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.extension.easyterminal.Init.init();
		silver.definition.regex.Init.init();
		core.monad.Init.init();
		silver.util.cmdargs.Init.init();
		silver.translation.java.driver.Init.init();
		silver.driver.Init.init();
		silver.util.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.type.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.env.Init.init();
		silver.definition.core.Init.init();
		silver.driver.util.Init.init();
		silver.modification.copper.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.extension.easyterminal.Init.postInit();
		silver.definition.regex.Init.postInit();
		core.monad.Init.postInit();
		silver.util.cmdargs.Init.postInit();
		silver.translation.java.driver.Init.postInit();
		silver.driver.Init.postInit();
		silver.util.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.modification.copper.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PconcreteProductionDclAction.class);
		common.Decorator.applyDecorators(silver.modification.copper.NActionCode_c.decorators, PactionCode_c.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PparserAttrDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PpluckTermDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PdisambigLexemeDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PlexerClassDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PtermAttrValueDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PactionChildDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PparserLocalDcl.class);
		common.Decorator.applyDecorators(silver.definition.env.NDclInfo.decorators, PprefixSeparatorDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NBlockContext.decorators, PactionContext.class);
		common.Decorator.applyDecorators(silver.definition.core.NBlockContext.decorators, PdisambiguationContext.class);
		common.Decorator.applyDecorators(silver.definition.core.NBlockContext.decorators, PreduceActionContext.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PparserDcl.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponents.decorators, PnilParserComponent.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponents.decorators, PconsParserComponent.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponent.decorators, PparserComponent.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponentModifiers.decorators, PnilParserComponentModifier.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponentModifiers.decorators, PconsParserComponentModifier.class);
		common.Decorator.applyDecorators(silver.definition.env.NDef.decorators, PlxrClsDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PattributeDclParser.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierDominates.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierSubmitsTo.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierClassSpec.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierActionCode.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermPrecList.decorators, PtermPrecListOne.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermPrecList.decorators, PtermPrecListCons.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermPrecList.decorators, PtermPrecList.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermPrecList.decorators, PtermPrecListNull.class);
		common.Decorator.applyDecorators(silver.modification.copper.NClassList.decorators, PlexerClassesOne.class);
		common.Decorator.applyDecorators(silver.modification.copper.NClassList.decorators, PlexerClassesCons.class);
		common.Decorator.applyDecorators(silver.modification.copper.NClassList.decorators, PlexerClassesMain.class);
		common.Decorator.applyDecorators(silver.modification.copper.NClassList.decorators, PlexerClassesNull.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PdisambiguationGroupDcl.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PactionChildReference.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PpluckTerminalReference.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PdisambigLexemeReference.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PparserAttributeReference.class);
		common.Decorator.applyDecorators(silver.definition.core.NExpr.decorators, PtermAttrValueReference.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PlexerClassDclEmpty.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PlexerClassDecl.class);
		common.Decorator.applyDecorators(silver.modification.copper.NLexerClassModifiers.decorators, PlexerClassModifiersNone.class);
		common.Decorator.applyDecorators(silver.modification.copper.NLexerClassModifiers.decorators, PlexerClassModifierSingle.class);
		common.Decorator.applyDecorators(silver.modification.copper.NLexerClassModifiers.decorators, PlexerClassModifiersCons.class);
		common.Decorator.applyDecorators(silver.modification.copper.NLexerClassModifier.decorators, PlexerClassModifierDominates.class);
		common.Decorator.applyDecorators(silver.modification.copper.NLexerClassModifier.decorators, PlexerClassModifierSubmitsTo.class);
		common.Decorator.applyDecorators(silver.util.cmdargs.NCmdArgs.decorators, PcopperdumpFlag.class);
		common.Decorator.applyDecorators(silver.driver.util.NDriverAction.decorators, PparserSpecUnit.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifier.decorators, PproductionModifierLayout.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifier.decorators, PproductionModifierLayoutNone.class);
		common.Decorator.applyDecorators(silver.definition.core.NName.decorators, PnamePrint.class);
		common.Decorator.applyDecorators(silver.definition.core.NName.decorators, PnamePluck.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PpluckDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PprintStmt.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PparserAttributeValueDef.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PpushTokenStmt.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PpushTokenIfStmt.class);
		common.Decorator.applyDecorators(silver.definition.core.NDefLHS.decorators, PparserAttributeDefLHS.class);
		common.Decorator.applyDecorators(silver.definition.core.NProductionStmt.decorators, PtermAttrValueValueDef.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponentModifier.decorators, PprefixParserComponentModifier.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefix.decorators, PnameTerminalPrefix.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefix.decorators, PnewTermModifiersTerminalPrefix.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefix.decorators, PnewTermTerminalPrefix.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefix.decorators, PseperatedTerminalPrefix.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefixItems.decorators, PconsTerminalPrefixItem.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefixItems.decorators, PoneTerminalPrefixItem.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefixItems.decorators, PallTerminalPrefixItem.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefixItem.decorators, PqNameTerminalPrefixItem.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTerminalPrefixItem.decorators, PeasyTerminalRefTerminalPrefixItem.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponent.decorators, PdisambiguateParserComponent.class);
		common.Decorator.applyDecorators(silver.modification.copper.NParserComponent.decorators, PprefixSeparatorParserComponent.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PprefixSeparatorAGDcl.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermList.decorators, PtermListOne.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermList.decorators, PtermListCons.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermList.decorators, PtermList.class);
		common.Decorator.applyDecorators(silver.modification.copper.NTermList.decorators, PtermListNull.class);
	}

	private static void setupInheritedAttributes(){
		silver.modification.copper.PconcreteProductionDclAction.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_concreteProductionDclAction] = "silver:modification:copper:concreteProductionDclAction:local:fName";
		silver.modification.copper.NActionCode_c.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ActionCode_c] = "silver:definition:env:config";
		silver.modification.copper.NActionCode_c.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NActionCode_c.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ActionCode_c] = "silver:definition:env:pp";
		silver.modification.copper.NActionCode_c.occurs_syn[silver.modification.copper.Init.silver_modification_copper_actionCode__ON__silver_modification_copper_ActionCode_c] = "silver:modification:copper:actionCode";
		silver.modification.copper.NActionCode_c.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ActionCode_c] = "silver:definition:env:env";
		silver.modification.copper.NActionCode_c.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NActionCode_c.occurs_syn[silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_ActionCode_c] = "silver:definition:env:defs";
		silver.modification.copper.NActionCode_c.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_ActionCode_c] = "silver:definition:core:grammarName";
		silver.modification.copper.NActionCode_c.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NActionCode_c.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ActionCode_c] = "silver:definition:core:errors";
		silver.modification.copper.NActionCode_c.occurs_inh[silver.modification.copper.Init.silver_definition_core_frame__ON__silver_modification_copper_ActionCode_c] = "silver:definition:core:frame";
		silver.modification.copper.NActionCode_c.decorators.add(silver.definition.core.Dframe.singleton);
		silver.modification.copper.NActionCode_c.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_ActionCode_c] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NActionCode_c.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NActionCode_c.occurs_inh[silver.modification.copper.Init.silver_definition_flow_env_flowEnv__ON__silver_modification_copper_ActionCode_c] = "silver:definition:flow:env:flowEnv";
		silver.modification.copper.NActionCode_c.decorators.add(silver.definition.flow.env.DflowEnv.singleton);
		silver.definition.core.NProductionSignature.occurs_syn[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionSignature] = "silver:modification:copper:actionDefs";
		silver.definition.core.NProductionRHS.occurs_syn[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHS] = "silver:modification:copper:actionDefs";
		silver.definition.core.NProductionRHSElem.occurs_syn[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHSElem] = "silver:modification:copper:actionDefs";
		silver.definition.core.NBlockContext.occurs_syn[silver.modification.copper.Init.silver_modification_copper_permitActions__ON__silver_definition_core_BlockContext] = "silver:modification:copper:permitActions";
		silver.definition.core.NBlockContext.occurs_syn[silver.modification.copper.Init.silver_modification_copper_permitPluck__ON__silver_definition_core_BlockContext] = "silver:modification:copper:permitPluck";
		//	local attribute liftedAGDcls::AGDcl;
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl] = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:liftedAGDcls";
		//	local attribute med::ModuleExportedDefs;
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.med__ON__silver_modification_copper_parserDcl] = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_inh_attrs];
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.med__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:med";
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:fName";
		//	local attribute namedSig::NamedSignature;
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.namedSig__ON__silver_modification_copper_parserDcl] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.namedSig__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:namedSig";
		//	local attribute spec::ParserSpec;
		silver.modification.copper.PparserDcl.localInheritedAttributes[silver.modification.copper.Init.spec__ON__silver_modification_copper_parserDcl] = new common.Lazy[silver.definition.concrete_syntax.NParserSpec.num_inh_attrs];
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.spec__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:spec";
		silver.modification.copper.NParserComponents.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ParserComponents] = "silver:definition:env:config";
		silver.modification.copper.NParserComponents.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NParserComponents.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponents] = "silver:definition:env:env";
		silver.modification.copper.NParserComponents.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NParserComponents.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponents] = "silver:definition:core:grammarName";
		silver.modification.copper.NParserComponents.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NParserComponents.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponents] = "silver:definition:env:pp";
		silver.modification.copper.NParserComponents.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponents] = "silver:definition:core:errors";
		silver.modification.copper.NParserComponents.occurs_syn[silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents] = "silver:definition:env:moduleNames";
		silver.modification.copper.NParserComponents.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponents] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NParserComponents.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NParserComponents.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponents] = "silver:definition:core:grammarDependencies";
		silver.modification.copper.NParserComponents.decorators.add(silver.definition.core.DgrammarDependencies.singleton);
		silver.modification.copper.NParserComponents.occurs_syn[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponents] = "silver:definition:concrete_syntax:terminalPrefixes";
		silver.modification.copper.NParserComponents.occurs_syn[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponents] = "silver:modification:copper:liftedAGDcls";
		silver.modification.copper.NParserComponent.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ParserComponent] = "silver:definition:env:config";
		silver.modification.copper.NParserComponent.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NParserComponent.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponent] = "silver:definition:env:env";
		silver.modification.copper.NParserComponent.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NParserComponent.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponent] = "silver:definition:core:grammarName";
		silver.modification.copper.NParserComponent.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NParserComponent.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponent] = "silver:definition:env:pp";
		silver.modification.copper.NParserComponent.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponent] = "silver:definition:core:errors";
		silver.modification.copper.NParserComponent.occurs_syn[silver.modification.copper.Init.silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponent] = "silver:definition:env:moduleNames";
		silver.modification.copper.NParserComponent.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponent] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NParserComponent.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NParserComponent.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponent] = "silver:definition:core:grammarDependencies";
		silver.modification.copper.NParserComponent.decorators.add(silver.definition.core.DgrammarDependencies.singleton);
		silver.modification.copper.NParserComponent.occurs_syn[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponent] = "silver:definition:concrete_syntax:terminalPrefixes";
		silver.modification.copper.NParserComponent.occurs_syn[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponent] = "silver:modification:copper:liftedAGDcls";
		silver.modification.copper.NParserComponentModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:env:config";
		silver.modification.copper.NParserComponentModifiers.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NParserComponentModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:env:env";
		silver.modification.copper.NParserComponentModifiers.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NParserComponentModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:core:grammarName";
		silver.modification.copper.NParserComponentModifiers.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NParserComponentModifiers.occurs_inh[silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_ParserComponentModifiers] = "silver:modification:copper:componentGrammarName";
		silver.modification.copper.NParserComponentModifiers.decorators.add(silver.modification.copper.DcomponentGrammarName.singleton);
		silver.modification.copper.NParserComponentModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NParserComponentModifiers.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NParserComponentModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:core:grammarDependencies";
		silver.modification.copper.NParserComponentModifiers.decorators.add(silver.definition.core.DgrammarDependencies.singleton);
		silver.modification.copper.NParserComponentModifiers.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:env:pp";
		silver.modification.copper.NParserComponentModifiers.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:core:errors";
		silver.modification.copper.NParserComponentModifiers.occurs_syn[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifiers] = "silver:definition:concrete_syntax:terminalPrefixes";
		silver.modification.copper.NParserComponentModifiers.occurs_syn[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifiers] = "silver:modification:copper:liftedAGDcls";
		silver.modification.copper.NParserComponentModifier.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:env:config";
		silver.modification.copper.NParserComponentModifier.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NParserComponentModifier.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:env:env";
		silver.modification.copper.NParserComponentModifier.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NParserComponentModifier.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:core:grammarName";
		silver.modification.copper.NParserComponentModifier.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NParserComponentModifier.occurs_inh[silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_ParserComponentModifier] = "silver:modification:copper:componentGrammarName";
		silver.modification.copper.NParserComponentModifier.decorators.add(silver.modification.copper.DcomponentGrammarName.singleton);
		silver.modification.copper.NParserComponentModifier.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NParserComponentModifier.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NParserComponentModifier.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:core:grammarDependencies";
		silver.modification.copper.NParserComponentModifier.decorators.add(silver.definition.core.DgrammarDependencies.singleton);
		silver.modification.copper.NParserComponentModifier.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:env:pp";
		silver.modification.copper.NParserComponentModifier.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:core:errors";
		silver.modification.copper.NParserComponentModifier.occurs_syn[silver.modification.copper.Init.silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifier] = "silver:definition:concrete_syntax:terminalPrefixes";
		silver.modification.copper.NParserComponentModifier.occurs_syn[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifier] = "silver:modification:copper:liftedAGDcls";
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.className__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:className";
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.packageName__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:packageName";
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.parserName__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:parserName";
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.localVar__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:localVar";
		silver.modification.copper.PparserDcl.occurs_local[silver.modification.copper.Init.parseResult__ON__silver_modification_copper_parserDcl] = "silver:modification:copper:parserDcl:local:parseResult";
		silver.definition.env.NDefs.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Defs] = "silver:modification:copper:lexerClassList";
		silver.definition.env.NDef.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Def] = "silver:modification:copper:lexerClassList";
		silver.definition.env.NEnv.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env] = "silver:modification:copper:lexerClassTree";
		silver.definition.core.NQName.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lookupLexerClass__ON__silver_definition_core_QName] = "silver:modification:copper:lookupLexerClass";
		silver.modification.copper.PattributeDclParser.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_attributeDclParser] = "silver:modification:copper:attributeDclParser:local:fName";
		silver.modification.copper.NTermPrecList.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_TermPrecList] = "silver:definition:env:config";
		silver.modification.copper.NTermPrecList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NTermPrecList.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_TermPrecList] = "silver:definition:core:grammarName";
		silver.modification.copper.NTermPrecList.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NTermPrecList.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermPrecList] = "silver:definition:env:pp";
		silver.modification.copper.NTermPrecList.occurs_syn[silver.modification.copper.Init.silver_modification_copper_precTermList__ON__silver_modification_copper_TermPrecList] = "silver:modification:copper:precTermList";
		silver.modification.copper.NTermPrecList.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermPrecList] = "silver:definition:core:errors";
		silver.modification.copper.NTermPrecList.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_TermPrecList] = "silver:definition:env:env";
		silver.modification.copper.NTermPrecList.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.PtermPrecList.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_termPrecList] = "silver:modification:copper:termPrecList:local:fName";
		silver.modification.copper.NClassList.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_ClassList] = "silver:definition:env:config";
		silver.modification.copper.NClassList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NClassList.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_ClassList] = "silver:definition:env:pp";
		silver.modification.copper.NClassList.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_modification_copper_ClassList] = "silver:modification:copper:lexerClasses";
		silver.modification.copper.NClassList.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_ClassList] = "silver:definition:core:errors";
		silver.modification.copper.NClassList.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_ClassList] = "silver:definition:env:env";
		silver.modification.copper.NClassList.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.concrete_syntax.NTerminalModifier.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:modification:copper:lexerClasses";
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:modification:copper:lexerClasses";
		silver.modification.copper.PdisambiguationGroupDcl.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_disambiguationGroupDcl] = "silver:modification:copper:disambiguationGroupDcl:local:fName";
		silver.modification.copper.PlexerClassDecl.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_lexerClassDecl] = "silver:modification:copper:lexerClassDecl:local:fName";
		silver.modification.copper.NLexerClassModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:env:config";
		silver.modification.copper.NLexerClassModifiers.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NLexerClassModifiers.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:env:pp";
		silver.modification.copper.NLexerClassModifiers.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifiers] = "silver:modification:copper:lexerClassModifiers";
		silver.modification.copper.NLexerClassModifiers.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:core:errors";
		silver.modification.copper.NLexerClassModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:env:env";
		silver.modification.copper.NLexerClassModifiers.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NLexerClassModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:core:grammarName";
		silver.modification.copper.NLexerClassModifiers.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NLexerClassModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NLexerClassModifiers.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NLexerClassModifiers.occurs_inh[silver.modification.copper.Init.silver_definition_flow_env_flowEnv__ON__silver_modification_copper_LexerClassModifiers] = "silver:definition:flow:env:flowEnv";
		silver.modification.copper.NLexerClassModifiers.decorators.add(silver.definition.flow.env.DflowEnv.singleton);
		silver.modification.copper.NLexerClassModifier.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:env:config";
		silver.modification.copper.NLexerClassModifier.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NLexerClassModifier.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:env:pp";
		silver.modification.copper.NLexerClassModifier.occurs_syn[silver.modification.copper.Init.silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifier] = "silver:modification:copper:lexerClassModifiers";
		silver.modification.copper.NLexerClassModifier.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:core:errors";
		silver.modification.copper.NLexerClassModifier.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:env:env";
		silver.modification.copper.NLexerClassModifier.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NLexerClassModifier.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:core:grammarName";
		silver.modification.copper.NLexerClassModifier.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NLexerClassModifier.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NLexerClassModifier.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NLexerClassModifier.occurs_inh[silver.modification.copper.Init.silver_definition_flow_env_flowEnv__ON__silver_modification_copper_LexerClassModifier] = "silver:definition:flow:env:flowEnv";
		silver.modification.copper.NLexerClassModifier.decorators.add(silver.definition.flow.env.DflowEnv.singleton);
		silver.util.cmdargs.NCmdArgs.occurs_syn[silver.modification.copper.Init.silver_modification_copper_forceCopperDump__ON__silver_util_cmdargs_CmdArgs] = "silver:modification:copper:forceCopperDump";
		silver.driver.util.Pcompilation.occurs_local[silver.modification.copper.Init.allParsers__ON__silver_driver_util_compilation] = "silver:driver:util:compilation:local:allParsers";
		silver.modification.copper.PbuildAntParserPart.occurs_local[silver.modification.copper.Init.parserName__ON__silver_modification_copper_buildAntParserPart] = "silver:modification:copper:buildAntParserPart:local:parserName";
		silver.modification.copper.PbuildAntParserPart.occurs_local[silver.modification.copper.Init.packagepath__ON__silver_modification_copper_buildAntParserPart] = "silver:modification:copper:buildAntParserPart:local:packagepath";
		silver.modification.copper.PbuildAntParserPart.occurs_local[silver.modification.copper.Init.varyingopts__ON__silver_modification_copper_buildAntParserPart] = "silver:modification:copper:buildAntParserPart:local:varyingopts";
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.file__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:file";
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.newSpec__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:newSpec";
		//	local attribute specCst::SyntaxRoot;
		silver.modification.copper.PparserSpecUnit.localInheritedAttributes[silver.modification.copper.Init.specCst__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntaxRoot.num_inh_attrs];
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.specCst__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:specCst";
		//	local attribute ex::IOVal<Boolean>;
		silver.modification.copper.PparserSpecUnit.localInheritedAttributes[silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.ex__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:ex";
		//	local attribute oldSpec::IOVal<String>;
		silver.modification.copper.PparserSpecUnit.localInheritedAttributes[silver.modification.copper.Init.oldSpec__ON__silver_modification_copper_parserSpecUnit] = new common.Lazy[core.NIOVal.num_inh_attrs];
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.oldSpec__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:oldSpec";
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.join__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:join";
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.err__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:err";
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.doUTD__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:doUTD";
		silver.modification.copper.PparserSpecUnit.occurs_local[silver.modification.copper.Init.doWR__ON__silver_modification_copper_parserSpecUnit] = "silver:modification:copper:parserSpecUnit:local:doWR";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.copper.PprintStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.copper.PprintStmt.occurs_local[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_printStmt] = "silver:modification:copper:printStmt:local:errCheck1";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.copper.PparserAttributeValueDef.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_parserAttributeValueDef] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.copper.PparserAttributeValueDef.occurs_local[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_parserAttributeValueDef] = "silver:modification:copper:parserAttributeValueDef:local:errCheck1";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.copper.PpushTokenIfStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.copper.PpushTokenIfStmt.occurs_local[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_pushTokenIfStmt] = "silver:modification:copper:pushTokenIfStmt:local:errCheck1";
		//	local attribute errCheck2::TypeCheck;
		silver.modification.copper.PpushTokenIfStmt.localInheritedAttributes[silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.copper.PpushTokenIfStmt.occurs_local[silver.modification.copper.Init.errCheck2__ON__silver_modification_copper_pushTokenIfStmt] = "silver:modification:copper:pushTokenIfStmt:local:errCheck2";
		silver.modification.copper.PtermAttrValueValueDef.occurs_local[silver.modification.copper.Init.memberfunc__ON__silver_modification_copper_termAttrValueValueDef] = "silver:modification:copper:termAttrValueValueDef:local:memberfunc";
		//	local attribute errCheck1::TypeCheck;
		silver.modification.copper.PtermAttrValueValueDef.localInheritedAttributes[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef] = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];
		silver.modification.copper.PtermAttrValueValueDef.occurs_local[silver.modification.copper.Init.errCheck1__ON__silver_modification_copper_termAttrValueValueDef] = "silver:modification:copper:termAttrValueValueDef:local:errCheck1";
		silver.modification.copper.NTerminalPrefix.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_TerminalPrefix] = "silver:definition:env:config";
		silver.modification.copper.NTerminalPrefix.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NTerminalPrefix.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_TerminalPrefix] = "silver:definition:env:env";
		silver.modification.copper.NTerminalPrefix.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NTerminalPrefix.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_TerminalPrefix] = "silver:definition:core:grammarName";
		silver.modification.copper.NTerminalPrefix.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NTerminalPrefix.occurs_inh[silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefix] = "silver:modification:copper:componentGrammarName";
		silver.modification.copper.NTerminalPrefix.decorators.add(silver.modification.copper.DcomponentGrammarName.singleton);
		silver.modification.copper.NTerminalPrefix.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefix] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NTerminalPrefix.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NTerminalPrefix.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix] = "silver:definition:env:pp";
		silver.modification.copper.NTerminalPrefix.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix] = "silver:definition:core:errors";
		silver.modification.copper.NTerminalPrefix.occurs_syn[silver.modification.copper.Init.silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_TerminalPrefix] = "silver:modification:copper:liftedAGDcls";
		silver.modification.copper.NTerminalPrefix.occurs_syn[silver.modification.copper.Init.silver_modification_copper_prefixFullName__ON__silver_modification_copper_TerminalPrefix] = "silver:modification:copper:prefixFullName";
		silver.modification.copper.PnewTermModifiersTerminalPrefix.occurs_local[silver.modification.copper.Init.terminalName__ON__silver_modification_copper_newTermModifiersTerminalPrefix] = "silver:modification:copper:newTermModifiersTerminalPrefix:local:terminalName";
		silver.modification.copper.PseperatedTerminalPrefix.occurs_local[silver.modification.copper.Init.seperatorLookup__ON__silver_modification_copper_seperatedTerminalPrefix] = "silver:modification:copper:seperatedTerminalPrefix:local:seperatorLookup";
		silver.modification.copper.PseperatedTerminalPrefix.occurs_local[silver.modification.copper.Init.seperator__ON__silver_modification_copper_seperatedTerminalPrefix] = "silver:modification:copper:seperatedTerminalPrefix:local:seperator";
		silver.modification.copper.PseperatedTerminalPrefix.occurs_local[silver.modification.copper.Init.givenPrefix__ON__silver_modification_copper_seperatedTerminalPrefix] = "silver:modification:copper:seperatedTerminalPrefix:local:givenPrefix";
		//	local attribute regex::RegExpr;
		silver.modification.copper.PseperatedTerminalPrefix.localInheritedAttributes[silver.modification.copper.Init.regex__ON__silver_modification_copper_seperatedTerminalPrefix] = new common.Lazy[silver.definition.concrete_syntax.NRegExpr.num_inh_attrs];
		silver.modification.copper.PseperatedTerminalPrefix.occurs_local[silver.modification.copper.Init.regex__ON__silver_modification_copper_seperatedTerminalPrefix] = "silver:modification:copper:seperatedTerminalPrefix:local:regex";
		silver.modification.copper.NTerminalPrefixItems.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:env:config";
		silver.modification.copper.NTerminalPrefixItems.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NTerminalPrefixItems.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:env:env";
		silver.modification.copper.NTerminalPrefixItems.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NTerminalPrefixItems.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:core:grammarName";
		silver.modification.copper.NTerminalPrefixItems.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NTerminalPrefixItems.occurs_inh[silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefixItems] = "silver:modification:copper:componentGrammarName";
		silver.modification.copper.NTerminalPrefixItems.decorators.add(silver.modification.copper.DcomponentGrammarName.singleton);
		silver.modification.copper.NTerminalPrefixItems.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NTerminalPrefixItems.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NTerminalPrefixItems.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarDependencies__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:core:grammarDependencies";
		silver.modification.copper.NTerminalPrefixItems.decorators.add(silver.definition.core.DgrammarDependencies.singleton);
		silver.modification.copper.NTerminalPrefixItems.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:env:pp";
		silver.modification.copper.NTerminalPrefixItems.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems] = "silver:definition:core:errors";
		silver.modification.copper.NTerminalPrefixItems.occurs_syn[silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems] = "silver:modification:copper:prefixItemNames";
		//	local attribute med::ModuleExportedDefs;
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.med__ON__silver_modification_copper_allTerminalPrefixItem] = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_inh_attrs];
		silver.modification.copper.PallTerminalPrefixItem.occurs_local[silver.modification.copper.Init.med__ON__silver_modification_copper_allTerminalPrefixItem] = "silver:modification:copper:allTerminalPrefixItem:local:med";
		//	local attribute syntax::Syntax;
		silver.modification.copper.PallTerminalPrefixItem.localInheritedAttributes[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];
		silver.modification.copper.PallTerminalPrefixItem.occurs_local[silver.modification.copper.Init.syntax__ON__silver_modification_copper_allTerminalPrefixItem] = "silver:modification:copper:allTerminalPrefixItem:local:syntax";
		silver.modification.copper.NTerminalPrefixItem.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_TerminalPrefixItem] = "silver:definition:env:config";
		silver.modification.copper.NTerminalPrefixItem.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NTerminalPrefixItem.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_TerminalPrefixItem] = "silver:definition:env:env";
		silver.modification.copper.NTerminalPrefixItem.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.NTerminalPrefixItem.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_TerminalPrefixItem] = "silver:definition:core:grammarName";
		silver.modification.copper.NTerminalPrefixItem.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NTerminalPrefixItem.occurs_inh[silver.modification.copper.Init.silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefixItem] = "silver:modification:copper:componentGrammarName";
		silver.modification.copper.NTerminalPrefixItem.decorators.add(silver.modification.copper.DcomponentGrammarName.singleton);
		silver.modification.copper.NTerminalPrefixItem.occurs_inh[silver.modification.copper.Init.silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefixItem] = "silver:definition:env:compiledGrammars";
		silver.modification.copper.NTerminalPrefixItem.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.modification.copper.NTerminalPrefixItem.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItem] = "silver:definition:env:pp";
		silver.modification.copper.NTerminalPrefixItem.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem] = "silver:definition:core:errors";
		silver.modification.copper.NTerminalPrefixItem.occurs_syn[silver.modification.copper.Init.silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItem] = "silver:modification:copper:prefixItemNames";
		silver.modification.copper.NTermList.occurs_inh[silver.modification.copper.Init.silver_definition_env_config__ON__silver_modification_copper_TermList] = "silver:definition:env:config";
		silver.modification.copper.NTermList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.modification.copper.NTermList.occurs_inh[silver.modification.copper.Init.silver_definition_core_grammarName__ON__silver_modification_copper_TermList] = "silver:definition:core:grammarName";
		silver.modification.copper.NTermList.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.modification.copper.NTermList.occurs_syn[silver.modification.copper.Init.silver_definition_env_pp__ON__silver_modification_copper_TermList] = "silver:definition:env:pp";
		silver.modification.copper.NTermList.occurs_syn[silver.modification.copper.Init.silver_modification_copper_termList__ON__silver_modification_copper_TermList] = "silver:modification:copper:termList";
		silver.modification.copper.NTermList.occurs_syn[silver.modification.copper.Init.silver_definition_env_defs__ON__silver_modification_copper_TermList] = "silver:definition:env:defs";
		silver.modification.copper.NTermList.occurs_syn[silver.modification.copper.Init.silver_definition_core_errors__ON__silver_modification_copper_TermList] = "silver:definition:core:errors";
		silver.modification.copper.NTermList.occurs_inh[silver.modification.copper.Init.silver_definition_env_env__ON__silver_modification_copper_TermList] = "silver:definition:env:env";
		silver.modification.copper.NTermList.decorators.add(silver.definition.env.Denv.singleton);
		silver.modification.copper.PtermList.occurs_local[silver.modification.copper.Init.fName__ON__silver_modification_copper_termList] = "silver:modification:copper:termList:local:fName";
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.copper.PconcreteProductionDclAction.initProductionAttributeDefinitions();
		silver.modification.copper.PactionCode_c.initProductionAttributeDefinitions();
		//FUNCTION hacklocaldeclarations String ::= d::Def 
		//FUNCTION hackTransformLocals [Def] ::= d::Def 
		//ASPECT PRODUCTION productionSignature top ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
		// top.actionDefs = rhs.actionDefs
		silver.definition.core.PproductionSignature.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionSignature.i_rhs).synthesized(silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHS)); } };
		//ASPECT PRODUCTION productionRHSNil top ::= 
		// top.actionDefs = []
		silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION productionRHSCons top ::= h::ProductionRHSElem t::ProductionRHS 
		// top.actionDefs = h.actionDefs ++ t.actionDefs
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHS))); } };
		//ASPECT PRODUCTION productionRHSElem top ::= id::Name '::' t::TypeExpr 
		// top.actionDefs = [ actionChildDef(top.grammarName, t.location, id.name, t.typerep) ]
		silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.modification.copper.PactionChildDef.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_ProductionRHSElem), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.core.PproductionRHSElem.i_t).undecorate()).getAnno_core_location()); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSElem.i_id, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSElem.i_t, silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		//ASPECT PRODUCTION actionCode_c top ::= '{' stmts::ProductionStmts '}' 
		// stmts.defaultInheritedAnnos = []
		silver.modification.copper.PactionCode_c.childInheritedAttributes[silver.modification.copper.PactionCode_c.i_stmts][silver.definition.core.Init.silver_definition_core_defaultInheritedAnnos__ON__silver_definition_core_ProductionStmts] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.modification.copper.PparserAttrDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PpluckTermDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PdisambigLexemeDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PtermAttrValueDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PactionChildDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PparserLocalDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PprefixSeparatorDcl.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for BlockContext
		// top.permitActions = false
		silver.definition.core.NBlockContext.defaultSynthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_permitActions__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.permitPluck = false
		silver.definition.core.NBlockContext.defaultSynthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_permitPluck__ON__silver_definition_core_BlockContext] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.modification.copper.PactionContext.initProductionAttributeDefinitions();
		silver.modification.copper.PdisambiguationContext.initProductionAttributeDefinitions();
		silver.modification.copper.PreduceActionContext.initProductionAttributeDefinitions();
		silver.modification.copper.PparserDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PnilParserComponent.initProductionAttributeDefinitions();
		silver.modification.copper.PconsParserComponent.initProductionAttributeDefinitions();
		silver.modification.copper.PparserComponent.initProductionAttributeDefinitions();
		silver.modification.copper.PnilParserComponentModifier.initProductionAttributeDefinitions();
		silver.modification.copper.PconsParserComponentModifier.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION parserDcl top ::= 'parser' n::Name '::' t::TypeExpr '{' m::ParserComponents '}' 
		// className = "P" ++ n.name
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.className__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("P")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.copper.PparserDcl.i_n).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name))); } };
		// packageName = makeName(top.grammarName)
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.packageName__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeName.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl))); } };
		// parserName = makeParserName(fName)
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.parserName__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeParserName.invoke(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_parserDcl))); } };
		// top.initWeaving := "\tpublic static int " ++ localVar ++ " = 0;\n"
		if(silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAinitWeaving(silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_initWeaving__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("\tpublic static int ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.localVar__ON__silver_modification_copper_parserDcl)), (common.StringCatter)(new common.StringCatter(" = 0;\n")))); } });
		// top.valueWeaving := ""
		if(silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAvalueWeaving(silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_valueWeaving__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } });
		// localVar = "count_local__ON__" ++ makeIdName(fName)
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.localVar__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("count_local__ON__")), (common.StringCatter)((common.StringCatter)silver.translation.java.core.PmakeIdName.invoke(context.localAsIsLazy(silver.modification.copper.Init.fName__ON__silver_modification_copper_parserDcl)))); } };
		// top.genFiles := liftedAGDcls.genFiles ++ [ pair(className ++ ".java", generateFunctionClassString(top.grammarName, n.name, namedSig, parseResult)) ]
		if(silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl] == null)
			silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl] = new silver.translation.java.core.CAgenFiles(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl);
		((common.CollectionAttribute)silver.modification.copper.PparserDcl.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.modification.copper.Init.liftedAGDcls__ON__silver_modification_copper_parserDcl).synthesized(silver.translation.java.core.Init.silver_translation_java_core_genFiles__ON__silver_definition_core_AGDcl)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.className__ON__silver_modification_copper_parserDcl)), (common.StringCatter)(new common.StringCatter(".java"))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PgenerateFunctionClassString.invoke(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_grammarName__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.modification.copper.PparserDcl.i_n, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_Name), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.copper.Init.namedSig__ON__silver_modification_copper_parserDcl)), context.localAsIsLazy(silver.modification.copper.Init.parseResult__ON__silver_modification_copper_parserDcl))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } });
		// parseResult = "return common.Util.callCopperParser(new " ++ packageName ++ "." ++ parserName ++ "(), c_stringToParse, c_filenameToReport);"
		silver.modification.copper.PparserDcl.localAttributes[silver.modification.copper.Init.parseResult__ON__silver_modification_copper_parserDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("return common.Util.callCopperParser(new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.packageName__ON__silver_modification_copper_parserDcl)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.modification.copper.Init.parserName__ON__silver_modification_copper_parserDcl)), (common.StringCatter)(new common.StringCatter("(), c_stringToParse, c_filenameToReport);")))))); } };
		//ASPECT PRODUCTION nilDefs top ::= 
		// top.lexerClassList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consDefs top ::= e1::Def e2::Defs 
		// top.lexerClassList = e1.lexerClassList ++ e2.lexerClassList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Defs))); } };
		//ASPECT DEFAULT PRODUCTION for Def
		// top.lexerClassList = []
		silver.definition.env.NDef.defaultSynthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Def] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.modification.copper.PlxrClsDef.initProductionAttributeDefinitions();
		//FUNCTION parserAttrDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION pluckTermDef Def ::= sg::String sl::Location fn::String 
		//FUNCTION disambigLexemeDef Def ::= sg::String sl::Location 
		//FUNCTION lexerClassDef Def ::= sg::String sl::Location fn::String 
		//FUNCTION termAttrValueDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION actionChildDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION parserLocalDef Def ::= sg::String sl::Location fn::String ty::Type 
		//FUNCTION prefixSeparatorDef Def ::= sg::String sl::Location s::String 
		//ASPECT PRODUCTION i_emptyEnv top ::= 
		// top.lexerClassTree = emptyEnvScope()
		silver.definition.env.Pi_emptyEnv.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PemptyEnvScope.invoke()); } };
		//ASPECT PRODUCTION i_appendEnv top ::= e1::Decorated Env e2::Decorated Env 
		// top.lexerClassTree = appendEnvScope(e1.lexerClassTree, e2.lexerClassTree)
		silver.definition.env.Pi_appendEnv.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PappendEnvScope.invoke(context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e1, silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env), context.childAsIsSynthesizedLazy(silver.definition.env.Pi_appendEnv.i_e2, silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env))); } };
		//ASPECT PRODUCTION i_newScopeEnv top ::= d::Defs e::Decorated Env 
		// top.lexerClassTree = consEnvScope(buildTree(d.lexerClassList), e.lexerClassTree)
		silver.definition.env.Pi_newScopeEnv.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)silver.definition.env.PconsEnvScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.env.PbuildTree.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_d, silver.modification.copper.Init.silver_modification_copper_lexerClassList__ON__silver_definition_env_Defs))); } }, context.childAsIsSynthesizedLazy(silver.definition.env.Pi_newScopeEnv.i_e, silver.modification.copper.Init.silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env))); } };
		//FUNCTION getLexerClassDcl [DclInfo] ::= search::String e::Decorated Env 
		//ASPECT PRODUCTION qNameId top ::= id::Name 
		// top.lookupLexerClass = decorate customLookup("lexer class", getLexerClassDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameId.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lookupLexerClass__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("lexer class")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.copper.PgetLexerClassDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		//ASPECT PRODUCTION qNameCons top ::= id::Name ':' qn::QName 
		// top.lookupLexerClass = decorate customLookup("lexer class", getLexerClassDcl(top.name, top.env), top.name, top.location) with {}
		silver.definition.core.PqNameCons.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lookupLexerClass__ON__silver_definition_core_QName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.core.NQNameLookup)new silver.definition.core.PcustomLookup((new common.StringCatter("lexer class")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.modification.copper.PgetLexerClassDcl.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName))); } }, context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)context.undecorate()).getAnno_core_location()); } })).decorate(context, (common.Lazy[])null); } };
		silver.modification.copper.PattributeDclParser.initProductionAttributeDefinitions();
		silver.modification.copper.PterminalModifierDominates.initProductionAttributeDefinitions();
		silver.modification.copper.PterminalModifierSubmitsTo.initProductionAttributeDefinitions();
		silver.modification.copper.PterminalModifierClassSpec.initProductionAttributeDefinitions();
		silver.modification.copper.PterminalModifierActionCode.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for TerminalModifier
		// top.lexerClasses = []
		silver.definition.concrete_syntax.NTerminalModifier.defaultSynthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.modification.copper.PtermPrecListOne.initProductionAttributeDefinitions();
		silver.modification.copper.PtermPrecListCons.initProductionAttributeDefinitions();
		silver.modification.copper.PtermPrecList.initProductionAttributeDefinitions();
		silver.modification.copper.PtermPrecListNull.initProductionAttributeDefinitions();
		//FUNCTION addTerminalAttrDefs [Def] ::= moredefs::[Def] 
		silver.modification.copper.PlexerClassesOne.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassesCons.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassesMain.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassesNull.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION terminalModifiersNone top ::= 
		// top.lexerClasses = []
		silver.definition.concrete_syntax.PterminalModifiersNone.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION terminalModifierSingle top ::= tm::TerminalModifier 
		// top.lexerClasses = tm.lexerClasses
		silver.definition.concrete_syntax.PterminalModifierSingle.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PterminalModifierSingle.i_tm).synthesized(silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifier)); } };
		//ASPECT PRODUCTION terminalModifiersCons top ::= h::TerminalModifier ',' t::TerminalModifiers 
		// top.lexerClasses = h.lexerClasses ++ t.lexerClasses
		silver.definition.concrete_syntax.PterminalModifiersCons.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalModifiersCons.i_h, silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifier), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.PterminalModifiersCons.i_t, silver.modification.copper.Init.silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifiers))); } };
		//FUNCTION quote String ::= s::String 
		silver.modification.copper.PdisambiguationGroupDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PactionChildReference.initProductionAttributeDefinitions();
		silver.modification.copper.PpluckTerminalReference.initProductionAttributeDefinitions();
		silver.modification.copper.PdisambigLexemeReference.initProductionAttributeDefinitions();
		silver.modification.copper.PparserAttributeReference.initProductionAttributeDefinitions();
		silver.modification.copper.PtermAttrValueReference.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassDclEmpty.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassDecl.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassModifiersNone.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassModifierSingle.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassModifiersCons.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassModifierDominates.initProductionAttributeDefinitions();
		silver.modification.copper.PlexerClassModifierSubmitsTo.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION endCmdArgs top ::= _ 
		// top.forceCopperDump = false
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_forceCopperDump__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		silver.modification.copper.PcopperdumpFlag.initProductionAttributeDefinitions();
		//ASPECT FUNCTION parseArgs Either<String Decorated CmdArgs> ::= args::[String] 
		// flags <- [ pair("--copperdump", flag(copperdumpFlag)) ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flags__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair((new common.StringCatter("--copperdump")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NFlag)new silver.util.cmdargs.Pflag(silver.modification.copper.PcopperdumpFlag.factory)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// flagdescs <- [ "\t--copperdump  : force Copper to dump parse table information" ]
		((common.CollectionAttribute)silver.driver.PparseArgs.localAttributes[silver.driver.Init.flagdescs__ON__silver_driver_parseArgs]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("\t--copperdump  : force Copper to dump parse table information")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//ASPECT PRODUCTION compilation top ::= g::Grammars _ buildGrammar::String benv::BuildEnv 
		// classpathCompiler <- [ "${sh}/jars/CopperCompiler.jar" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.classpathCompiler__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("${sh}/jars/CopperCompiler.jar")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// classpathRuntime <- [ "${sh}/jars/CopperRuntime.jar" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.classpathRuntime__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("${sh}/jars/CopperRuntime.jar")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// extraTopLevelDecls <- [ "  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>", "  <target name='copper'>\n" ++ implode("", map(buildAntParserPart(_, top.config), allParsers)) ++ "  </target>" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.extraTopLevelDecls__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("  <taskdef name='copper' classname='edu.umn.cs.melt.copper.ant.CopperAntTask' classpathref='compile.classpath'/>")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("  <target name='copper'>\n")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.modification.copper.PbuildAntParserPart.factory.invokePartial(new int[]{1}, new Object[]{context.contextInheritedLazy(silver.driver.util.Init.silver_definition_env_config__ON__silver_driver_util_Compilation)}); } }, context.localAsIsLazy(silver.modification.copper.Init.allParsers__ON__silver_driver_util_compilation))); } })), (common.StringCatter)(new common.StringCatter("  </target>")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } });
		// extraGrammarsDeps <- [ "copper" ]
		((common.CollectionAttribute)silver.driver.util.Pcompilation.localAttributes[silver.translation.java.driver.Init.extraGrammarsDeps__ON__silver_driver_util_compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke((new common.StringCatter("copper")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// allParsers = flatMap((.parserSpecs), grammarsRelevant)
		silver.driver.util.Pcompilation.localAttributes[silver.modification.copper.Init.allParsers__ON__silver_driver_util_compilation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.PflatMap.invoke(new common.AttributeSection(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec), context.localAsIsLazy(silver.driver.util.Init.grammarsRelevant__ON__silver_driver_util_compilation))); } };
		// top.postOps <- map(parserSpecUnit(_, g.compiledGrammars, benv.silverGen), allParsers)
		if(silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] == null)
			silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation] = new silver.driver.util.CApostOps(silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation);
		((common.CollectionAttribute)silver.driver.util.Pcompilation.synthesizedAttributes[silver.driver.util.Init.silver_driver_util_postOps__ON__silver_driver_util_Compilation]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.modification.copper.PparserSpecUnit.factory.invokePartial(new int[]{1, 2}, new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)context.childDecorated(silver.driver.util.Pcompilation.i_g).inherited(silver.driver.util.Init.silver_definition_env_compiledGrammars__ON__silver_driver_util_Grammars)); } }, context.childDecoratedSynthesizedLazy(silver.driver.util.Pcompilation.i_benv, silver.driver.util.Init.silver_driver_util_silverGen__ON__silver_driver_util_BuildEnv)}); } }, context.localAsIsLazy(silver.modification.copper.Init.allParsers__ON__silver_driver_util_compilation))); } });
		//FUNCTION buildAntParserPart String ::= p::ParserSpec a::Decorated CmdArgs 
		// parserName = makeParserName(p.fullName)
		silver.modification.copper.PbuildAntParserPart.localAttributes[silver.modification.copper.Init.parserName__ON__silver_modification_copper_buildAntParserPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.translation.java.core.PmakeParserName.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PbuildAntParserPart.i_p, silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec))); } };
		// packagepath = grammarToPath(p.sourceGrammar)
		silver.modification.copper.PbuildAntParserPart.localAttributes[silver.modification.copper.Init.packagepath__ON__silver_modification_copper_buildAntParserPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.driver.util.PgrammarToPath.invoke(context.childDecoratedSynthesizedLazy(silver.modification.copper.PbuildAntParserPart.i_p, silver.definition.concrete_syntax.Init.silver_definition_env_sourceGrammar__ON__silver_definition_concrete_syntax_ParserSpec))); } };
		// varyingopts = if a.forceCopperDump then "avoidRecompile='false' dump='ON'" else "avoidRecompile='true' dump='ERROR_ONLY'"
		silver.modification.copper.PbuildAntParserPart.localAttributes[silver.modification.copper.Init.varyingopts__ON__silver_modification_copper_buildAntParserPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PbuildAntParserPart.i_a)).synthesized(silver.modification.copper.Init.silver_modification_copper_forceCopperDump__ON__silver_util_cmdargs_CmdArgs)) ? (new common.StringCatter("avoidRecompile='false' dump='ON'")) : (new common.StringCatter("avoidRecompile='true' dump='ERROR_ONLY'"))); } };
		silver.modification.copper.PparserSpecUnit.initProductionAttributeDefinitions();
		silver.modification.copper.PproductionModifierLayout.initProductionAttributeDefinitions();
		silver.modification.copper.PproductionModifierLayoutNone.initProductionAttributeDefinitions();
		silver.modification.copper.PnamePrint.initProductionAttributeDefinitions();
		silver.modification.copper.PnamePluck.initProductionAttributeDefinitions();
		silver.modification.copper.PpluckDef.initProductionAttributeDefinitions();
		silver.modification.copper.PprintStmt.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION localAttributeDcl top ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';' 
		//ASPECT PRODUCTION defaultProdAnno top ::= 'default' 'annotation' qn::QName '=' aexpr::AppExpr ';' 
		// top.translation = "\n"
		silver.definition.core.PdefaultProdAnno.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_ProductionStmt] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("\n")); } };
		silver.modification.copper.PparserAttributeValueDef.initProductionAttributeDefinitions();
		silver.modification.copper.PpushTokenStmt.initProductionAttributeDefinitions();
		silver.modification.copper.PpushTokenIfStmt.initProductionAttributeDefinitions();
		silver.modification.copper.PparserAttributeDefLHS.initProductionAttributeDefinitions();
		silver.modification.copper.PtermAttrValueValueDef.initProductionAttributeDefinitions();
		silver.modification.copper.PprefixParserComponentModifier.initProductionAttributeDefinitions();
		silver.modification.copper.PnameTerminalPrefix.initProductionAttributeDefinitions();
		silver.modification.copper.PnewTermModifiersTerminalPrefix.initProductionAttributeDefinitions();
		silver.modification.copper.PnewTermTerminalPrefix.initProductionAttributeDefinitions();
		silver.modification.copper.PseperatedTerminalPrefix.initProductionAttributeDefinitions();
		silver.modification.copper.PconsTerminalPrefixItem.initProductionAttributeDefinitions();
		silver.modification.copper.PoneTerminalPrefixItem.initProductionAttributeDefinitions();
		silver.modification.copper.PallTerminalPrefixItem.initProductionAttributeDefinitions();
		silver.modification.copper.PqNameTerminalPrefixItem.initProductionAttributeDefinitions();
		silver.modification.copper.PeasyTerminalRefTerminalPrefixItem.initProductionAttributeDefinitions();
		silver.modification.copper.PdisambiguateParserComponent.initProductionAttributeDefinitions();
		silver.modification.copper.PprefixSeparatorParserComponent.initProductionAttributeDefinitions();
		silver.modification.copper.PprefixSeparatorAGDcl.initProductionAttributeDefinitions();
		silver.modification.copper.PtermListOne.initProductionAttributeDefinitions();
		silver.modification.copper.PtermListCons.initProductionAttributeDefinitions();
		silver.modification.copper.PtermList.initProductionAttributeDefinitions();
		silver.modification.copper.PtermListNull.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_copper_concreteProductionDclAction = 0;
	public static int count_inh__ON__ActionCode_c = 0;
	public static int count_syn__ON__ActionCode_c = 0;
	public static int count_local__ON__silver_modification_copper_actionCode_c = 0;
	public static int count_local__ON__silver_modification_copper_hacklocaldeclarations = 0;
	public static int count_local__ON__silver_modification_copper_hackTransformLocals = 0;
	public static int count_local__ON__silver_modification_copper_parserAttrDcl = 0;
	public static int count_local__ON__silver_modification_copper_pluckTermDcl = 0;
	public static int count_local__ON__silver_modification_copper_disambigLexemeDcl = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassDcl = 0;
	public static int count_local__ON__silver_modification_copper_termAttrValueDcl = 0;
	public static int count_local__ON__silver_modification_copper_actionChildDcl = 0;
	public static int count_local__ON__silver_modification_copper_parserLocalDcl = 0;
	public static int count_local__ON__silver_modification_copper_prefixSeparatorDcl = 0;
	public static int count_local__ON__silver_modification_copper_actionContext = 0;
	public static int count_local__ON__silver_modification_copper_disambiguationContext = 0;
	public static int count_local__ON__silver_modification_copper_reduceActionContext = 0;
	public static int count_local__ON__silver_modification_copper_parserDcl = 0;
	public static int count_inh__ON__ParserComponents = 0;
	public static int count_syn__ON__ParserComponents = 0;
	public static int count_local__ON__silver_modification_copper_nilParserComponent = 0;
	public static int count_local__ON__silver_modification_copper_consParserComponent = 0;
	public static int count_inh__ON__ParserComponent = 0;
	public static int count_syn__ON__ParserComponent = 0;
	public static int count_local__ON__silver_modification_copper_parserComponent = 0;
	public static int count_inh__ON__ParserComponentModifiers = 0;
	public static int count_syn__ON__ParserComponentModifiers = 0;
	public static int count_local__ON__silver_modification_copper_nilParserComponentModifier = 0;
	public static int count_local__ON__silver_modification_copper_consParserComponentModifier = 0;
	public static int count_inh__ON__ParserComponentModifier = 0;
	public static int count_syn__ON__ParserComponentModifier = 0;
	public static int count_local__ON__silver_modification_copper_lxrClsDef = 0;
	public static int count_local__ON__silver_modification_copper_parserAttrDef = 0;
	public static int count_local__ON__silver_modification_copper_pluckTermDef = 0;
	public static int count_local__ON__silver_modification_copper_disambigLexemeDef = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassDef = 0;
	public static int count_local__ON__silver_modification_copper_termAttrValueDef = 0;
	public static int count_local__ON__silver_modification_copper_actionChildDef = 0;
	public static int count_local__ON__silver_modification_copper_parserLocalDef = 0;
	public static int count_local__ON__silver_modification_copper_prefixSeparatorDef = 0;
	public static int count_local__ON__silver_modification_copper_getLexerClassDcl = 0;
	public static int count_local__ON__silver_modification_copper_attributeDclParser = 0;
	public static int count_local__ON__silver_modification_copper_terminalModifierDominates = 0;
	public static int count_local__ON__silver_modification_copper_terminalModifierSubmitsTo = 0;
	public static int count_local__ON__silver_modification_copper_terminalModifierClassSpec = 0;
	public static int count_local__ON__silver_modification_copper_terminalModifierActionCode = 0;
	public static int count_inh__ON__TermPrecList = 0;
	public static int count_syn__ON__TermPrecList = 0;
	public static int count_local__ON__silver_modification_copper_termPrecListOne = 0;
	public static int count_local__ON__silver_modification_copper_termPrecListCons = 0;
	public static int count_local__ON__silver_modification_copper_termPrecList = 0;
	public static int count_local__ON__silver_modification_copper_termPrecListNull = 0;
	public static int count_local__ON__silver_modification_copper_addTerminalAttrDefs = 0;
	public static int count_inh__ON__ClassList = 0;
	public static int count_syn__ON__ClassList = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassesOne = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassesCons = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassesMain = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassesNull = 0;
	public static int count_local__ON__silver_modification_copper_quote = 0;
	public static int count_local__ON__silver_modification_copper_disambiguationGroupDcl = 0;
	public static int count_local__ON__silver_modification_copper_actionChildReference = 0;
	public static int count_local__ON__silver_modification_copper_pluckTerminalReference = 0;
	public static int count_local__ON__silver_modification_copper_disambigLexemeReference = 0;
	public static int count_local__ON__silver_modification_copper_parserAttributeReference = 0;
	public static int count_local__ON__silver_modification_copper_termAttrValueReference = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassDclEmpty = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassDecl = 0;
	public static int count_inh__ON__LexerClassModifiers = 0;
	public static int count_syn__ON__LexerClassModifiers = 0;
	public static int count_inh__ON__LexerClassModifier = 0;
	public static int count_syn__ON__LexerClassModifier = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassModifiersNone = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassModifierSingle = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassModifiersCons = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassModifierDominates = 0;
	public static int count_local__ON__silver_modification_copper_lexerClassModifierSubmitsTo = 0;
	public static int count_local__ON__silver_modification_copper_copperdumpFlag = 0;
	public static int count_local__ON__silver_modification_copper_buildAntParserPart = 0;
	public static int count_local__ON__silver_modification_copper_parserSpecUnit = 0;
	public static int count_local__ON__silver_modification_copper_productionModifierLayout = 0;
	public static int count_local__ON__silver_modification_copper_productionModifierLayoutNone = 0;
	public static int count_local__ON__silver_modification_copper_namePrint = 0;
	public static int count_local__ON__silver_modification_copper_namePluck = 0;
	public static int count_local__ON__silver_modification_copper_pluckDef = 0;
	public static int count_local__ON__silver_modification_copper_printStmt = 0;
	public static int count_local__ON__silver_modification_copper_parserAttributeValueDef = 0;
	public static int count_local__ON__silver_modification_copper_pushTokenStmt = 0;
	public static int count_local__ON__silver_modification_copper_pushTokenIfStmt = 0;
	public static int count_local__ON__silver_modification_copper_parserAttributeDefLHS = 0;
	public static int count_local__ON__silver_modification_copper_termAttrValueValueDef = 0;
	public static int count_local__ON__silver_modification_copper_prefixParserComponentModifier = 0;
	public static int count_inh__ON__TerminalPrefix = 0;
	public static int count_syn__ON__TerminalPrefix = 0;
	public static int count_local__ON__silver_modification_copper_nameTerminalPrefix = 0;
	public static int count_local__ON__silver_modification_copper_newTermModifiersTerminalPrefix = 0;
	public static int count_local__ON__silver_modification_copper_newTermTerminalPrefix = 0;
	public static int count_local__ON__silver_modification_copper_seperatedTerminalPrefix = 0;
	public static int count_inh__ON__TerminalPrefixItems = 0;
	public static int count_syn__ON__TerminalPrefixItems = 0;
	public static int count_local__ON__silver_modification_copper_consTerminalPrefixItem = 0;
	public static int count_local__ON__silver_modification_copper_oneTerminalPrefixItem = 0;
	public static int count_local__ON__silver_modification_copper_allTerminalPrefixItem = 0;
	public static int count_inh__ON__TerminalPrefixItem = 0;
	public static int count_syn__ON__TerminalPrefixItem = 0;
	public static int count_local__ON__silver_modification_copper_qNameTerminalPrefixItem = 0;
	public static int count_local__ON__silver_modification_copper_easyTerminalRefTerminalPrefixItem = 0;
	public static int count_local__ON__silver_modification_copper_disambiguateParserComponent = 0;
	public static int count_local__ON__silver_modification_copper_prefixSeparatorParserComponent = 0;
	public static int count_local__ON__silver_modification_copper_prefixSeparatorAGDcl = 0;
	public static int count_inh__ON__TermList = 0;
	public static int count_syn__ON__TermList = 0;
	public static int count_local__ON__silver_modification_copper_termListOne = 0;
	public static int count_local__ON__silver_modification_copper_termListCons = 0;
	public static int count_local__ON__silver_modification_copper_termList = 0;
	public static int count_local__ON__silver_modification_copper_termListNull = 0;
public static final int fName__ON__silver_modification_copper_concreteProductionDclAction = silver.modification.copper.Init.count_local__ON__silver_modification_copper_concreteProductionDclAction++;
public static final int silver_definition_env_config__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_inh__ON__ActionCode_c++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_syn__ON__ActionCode_c++;
public static final int silver_modification_copper_actionCode__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_syn__ON__ActionCode_c++;
public static final int silver_definition_env_env__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_inh__ON__ActionCode_c++;
public static final int silver_definition_env_defs__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_syn__ON__ActionCode_c++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_inh__ON__ActionCode_c++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_syn__ON__ActionCode_c++;
public static final int silver_definition_core_frame__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_inh__ON__ActionCode_c++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_inh__ON__ActionCode_c++;
public static final int silver_definition_flow_env_flowEnv__ON__silver_modification_copper_ActionCode_c = silver.modification.copper.Init.count_inh__ON__ActionCode_c++;
public static final int silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionSignature = silver.definition.core.Init.count_syn__ON__ProductionSignature++;
public static final int silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHS = silver.definition.core.Init.count_syn__ON__ProductionRHS++;
public static final int silver_modification_copper_actionDefs__ON__silver_definition_core_ProductionRHSElem = silver.definition.core.Init.count_syn__ON__ProductionRHSElem++;
public static final int silver_modification_copper_permitActions__ON__silver_definition_core_BlockContext = silver.definition.core.Init.count_syn__ON__BlockContext++;
public static final int silver_modification_copper_permitPluck__ON__silver_definition_core_BlockContext = silver.definition.core.Init.count_syn__ON__BlockContext++;
public static final int liftedAGDcls__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int med__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int fName__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int namedSig__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int spec__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int silver_definition_env_config__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_inh__ON__ParserComponents++;
public static final int silver_definition_env_env__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_inh__ON__ParserComponents++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_inh__ON__ParserComponents++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_syn__ON__ParserComponents++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_syn__ON__ParserComponents++;
public static final int silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_syn__ON__ParserComponents++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_inh__ON__ParserComponents++;
public static final int silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_inh__ON__ParserComponents++;
public static final int silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_syn__ON__ParserComponents++;
public static final int silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponents = silver.modification.copper.Init.count_syn__ON__ParserComponents++;
public static final int silver_definition_env_config__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_inh__ON__ParserComponent++;
public static final int silver_definition_env_env__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_inh__ON__ParserComponent++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_inh__ON__ParserComponent++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_syn__ON__ParserComponent++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_syn__ON__ParserComponent++;
public static final int silver_definition_env_moduleNames__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_syn__ON__ParserComponent++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_inh__ON__ParserComponent++;
public static final int silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_inh__ON__ParserComponent++;
public static final int silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_syn__ON__ParserComponent++;
public static final int silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponent = silver.modification.copper.Init.count_syn__ON__ParserComponent++;
public static final int silver_definition_env_config__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_inh__ON__ParserComponentModifiers++;
public static final int silver_definition_env_env__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_inh__ON__ParserComponentModifiers++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_inh__ON__ParserComponentModifiers++;
public static final int silver_modification_copper_componentGrammarName__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_inh__ON__ParserComponentModifiers++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_inh__ON__ParserComponentModifiers++;
public static final int silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_inh__ON__ParserComponentModifiers++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_syn__ON__ParserComponentModifiers++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_syn__ON__ParserComponentModifiers++;
public static final int silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_syn__ON__ParserComponentModifiers++;
public static final int silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifiers = silver.modification.copper.Init.count_syn__ON__ParserComponentModifiers++;
public static final int silver_definition_env_config__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_inh__ON__ParserComponentModifier++;
public static final int silver_definition_env_env__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_inh__ON__ParserComponentModifier++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_inh__ON__ParserComponentModifier++;
public static final int silver_modification_copper_componentGrammarName__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_inh__ON__ParserComponentModifier++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_inh__ON__ParserComponentModifier++;
public static final int silver_definition_core_grammarDependencies__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_inh__ON__ParserComponentModifier++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_syn__ON__ParserComponentModifier++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_syn__ON__ParserComponentModifier++;
public static final int silver_definition_concrete_syntax_terminalPrefixes__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_syn__ON__ParserComponentModifier++;
public static final int silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_ParserComponentModifier = silver.modification.copper.Init.count_syn__ON__ParserComponentModifier++;
public static final int className__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int packageName__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int parserName__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int localVar__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int parseResult__ON__silver_modification_copper_parserDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserDcl++;
public static final int silver_modification_copper_lexerClassList__ON__silver_definition_env_Defs = silver.definition.env.Init.count_syn__ON__Defs++;
public static final int silver_modification_copper_lexerClassList__ON__silver_definition_env_Def = silver.definition.env.Init.count_syn__ON__Def++;
public static final int silver_modification_copper_lexerClassTree__ON__silver_definition_env_Env = silver.definition.env.Init.count_syn__ON__Env++;
public static final int silver_modification_copper_lookupLexerClass__ON__silver_definition_core_QName = silver.definition.core.Init.count_syn__ON__QName++;
public static final int fName__ON__silver_modification_copper_attributeDclParser = silver.modification.copper.Init.count_local__ON__silver_modification_copper_attributeDclParser++;
public static final int silver_definition_env_config__ON__silver_modification_copper_TermPrecList = silver.modification.copper.Init.count_inh__ON__TermPrecList++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_TermPrecList = silver.modification.copper.Init.count_inh__ON__TermPrecList++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_TermPrecList = silver.modification.copper.Init.count_syn__ON__TermPrecList++;
public static final int silver_modification_copper_precTermList__ON__silver_modification_copper_TermPrecList = silver.modification.copper.Init.count_syn__ON__TermPrecList++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_TermPrecList = silver.modification.copper.Init.count_syn__ON__TermPrecList++;
public static final int silver_definition_env_env__ON__silver_modification_copper_TermPrecList = silver.modification.copper.Init.count_inh__ON__TermPrecList++;
public static final int fName__ON__silver_modification_copper_termPrecList = silver.modification.copper.Init.count_local__ON__silver_modification_copper_termPrecList++;
public static final int silver_definition_env_config__ON__silver_modification_copper_ClassList = silver.modification.copper.Init.count_inh__ON__ClassList++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_ClassList = silver.modification.copper.Init.count_syn__ON__ClassList++;
public static final int silver_modification_copper_lexerClasses__ON__silver_modification_copper_ClassList = silver.modification.copper.Init.count_syn__ON__ClassList++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_ClassList = silver.modification.copper.Init.count_syn__ON__ClassList++;
public static final int silver_definition_env_env__ON__silver_modification_copper_ClassList = silver.modification.copper.Init.count_inh__ON__ClassList++;
public static final int silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifier++;
public static final int silver_modification_copper_lexerClasses__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifiers++;
public static final int fName__ON__silver_modification_copper_disambiguationGroupDcl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_disambiguationGroupDcl++;
public static final int fName__ON__silver_modification_copper_lexerClassDecl = silver.modification.copper.Init.count_local__ON__silver_modification_copper_lexerClassDecl++;
public static final int silver_definition_env_config__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_inh__ON__LexerClassModifiers++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_syn__ON__LexerClassModifiers++;
public static final int silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_syn__ON__LexerClassModifiers++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_syn__ON__LexerClassModifiers++;
public static final int silver_definition_env_env__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_inh__ON__LexerClassModifiers++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_inh__ON__LexerClassModifiers++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_inh__ON__LexerClassModifiers++;
public static final int silver_definition_flow_env_flowEnv__ON__silver_modification_copper_LexerClassModifiers = silver.modification.copper.Init.count_inh__ON__LexerClassModifiers++;
public static final int silver_definition_env_config__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_inh__ON__LexerClassModifier++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_syn__ON__LexerClassModifier++;
public static final int silver_modification_copper_lexerClassModifiers__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_syn__ON__LexerClassModifier++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_syn__ON__LexerClassModifier++;
public static final int silver_definition_env_env__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_inh__ON__LexerClassModifier++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_inh__ON__LexerClassModifier++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_inh__ON__LexerClassModifier++;
public static final int silver_definition_flow_env_flowEnv__ON__silver_modification_copper_LexerClassModifier = silver.modification.copper.Init.count_inh__ON__LexerClassModifier++;
public static final int silver_modification_copper_forceCopperDump__ON__silver_util_cmdargs_CmdArgs = silver.util.cmdargs.Init.count_syn__ON__CmdArgs++;
public static final int allParsers__ON__silver_driver_util_compilation = silver.driver.util.Init.count_local__ON__silver_driver_util_compilation++;
public static final int parserName__ON__silver_modification_copper_buildAntParserPart = silver.modification.copper.Init.count_local__ON__silver_modification_copper_buildAntParserPart++;
public static final int packagepath__ON__silver_modification_copper_buildAntParserPart = silver.modification.copper.Init.count_local__ON__silver_modification_copper_buildAntParserPart++;
public static final int varyingopts__ON__silver_modification_copper_buildAntParserPart = silver.modification.copper.Init.count_local__ON__silver_modification_copper_buildAntParserPart++;
public static final int file__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int newSpec__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int specCst__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int ex__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int oldSpec__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int join__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int err__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int doUTD__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int doWR__ON__silver_modification_copper_parserSpecUnit = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserSpecUnit++;
public static final int errCheck1__ON__silver_modification_copper_printStmt = silver.modification.copper.Init.count_local__ON__silver_modification_copper_printStmt++;
public static final int errCheck1__ON__silver_modification_copper_parserAttributeValueDef = silver.modification.copper.Init.count_local__ON__silver_modification_copper_parserAttributeValueDef++;
public static final int errCheck1__ON__silver_modification_copper_pushTokenIfStmt = silver.modification.copper.Init.count_local__ON__silver_modification_copper_pushTokenIfStmt++;
public static final int errCheck2__ON__silver_modification_copper_pushTokenIfStmt = silver.modification.copper.Init.count_local__ON__silver_modification_copper_pushTokenIfStmt++;
public static final int memberfunc__ON__silver_modification_copper_termAttrValueValueDef = silver.modification.copper.Init.count_local__ON__silver_modification_copper_termAttrValueValueDef++;
public static final int errCheck1__ON__silver_modification_copper_termAttrValueValueDef = silver.modification.copper.Init.count_local__ON__silver_modification_copper_termAttrValueValueDef++;
public static final int silver_definition_env_config__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_inh__ON__TerminalPrefix++;
public static final int silver_definition_env_env__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_inh__ON__TerminalPrefix++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_inh__ON__TerminalPrefix++;
public static final int silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_inh__ON__TerminalPrefix++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_inh__ON__TerminalPrefix++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_syn__ON__TerminalPrefix++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_syn__ON__TerminalPrefix++;
public static final int silver_modification_copper_liftedAGDcls__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_syn__ON__TerminalPrefix++;
public static final int silver_modification_copper_prefixFullName__ON__silver_modification_copper_TerminalPrefix = silver.modification.copper.Init.count_syn__ON__TerminalPrefix++;
public static final int terminalName__ON__silver_modification_copper_newTermModifiersTerminalPrefix = silver.modification.copper.Init.count_local__ON__silver_modification_copper_newTermModifiersTerminalPrefix++;
public static final int seperatorLookup__ON__silver_modification_copper_seperatedTerminalPrefix = silver.modification.copper.Init.count_local__ON__silver_modification_copper_seperatedTerminalPrefix++;
public static final int seperator__ON__silver_modification_copper_seperatedTerminalPrefix = silver.modification.copper.Init.count_local__ON__silver_modification_copper_seperatedTerminalPrefix++;
public static final int givenPrefix__ON__silver_modification_copper_seperatedTerminalPrefix = silver.modification.copper.Init.count_local__ON__silver_modification_copper_seperatedTerminalPrefix++;
public static final int regex__ON__silver_modification_copper_seperatedTerminalPrefix = silver.modification.copper.Init.count_local__ON__silver_modification_copper_seperatedTerminalPrefix++;
public static final int silver_definition_env_config__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItems++;
public static final int silver_definition_env_env__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItems++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItems++;
public static final int silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItems++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItems++;
public static final int silver_definition_core_grammarDependencies__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItems++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_syn__ON__TerminalPrefixItems++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_syn__ON__TerminalPrefixItems++;
public static final int silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItems = silver.modification.copper.Init.count_syn__ON__TerminalPrefixItems++;
public static final int med__ON__silver_modification_copper_allTerminalPrefixItem = silver.modification.copper.Init.count_local__ON__silver_modification_copper_allTerminalPrefixItem++;
public static final int syntax__ON__silver_modification_copper_allTerminalPrefixItem = silver.modification.copper.Init.count_local__ON__silver_modification_copper_allTerminalPrefixItem++;
public static final int silver_definition_env_config__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItem++;
public static final int silver_definition_env_env__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItem++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItem++;
public static final int silver_modification_copper_componentGrammarName__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItem++;
public static final int silver_definition_env_compiledGrammars__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_inh__ON__TerminalPrefixItem++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_syn__ON__TerminalPrefixItem++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_syn__ON__TerminalPrefixItem++;
public static final int silver_modification_copper_prefixItemNames__ON__silver_modification_copper_TerminalPrefixItem = silver.modification.copper.Init.count_syn__ON__TerminalPrefixItem++;
public static final int silver_definition_env_config__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_inh__ON__TermList++;
public static final int silver_definition_core_grammarName__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_inh__ON__TermList++;
public static final int silver_definition_env_pp__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_syn__ON__TermList++;
public static final int silver_modification_copper_termList__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_syn__ON__TermList++;
public static final int silver_definition_env_defs__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_syn__ON__TermList++;
public static final int silver_definition_core_errors__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_syn__ON__TermList++;
public static final int silver_definition_env_env__ON__silver_modification_copper_TermList = silver.modification.copper.Init.count_inh__ON__TermList++;
public static final int fName__ON__silver_modification_copper_termList = silver.modification.copper.Init.count_local__ON__silver_modification_copper_termList++;
}
