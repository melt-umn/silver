package silver.definition.concrete_syntax;

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
		silver.driver.util.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.driver.util.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();
		silver.definition.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.core.Init.init();
		silver.definition.concrete_syntax.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PconcreteProductionDcl.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifiers.decorators, PproductionModifiersNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifiers.decorators, PproductionModifierSome.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifierList.decorators, PproductionModifierSingle.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifierList.decorators, PproductionModifiersCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifier.decorators, PproductionModifierPrecedence.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NProductionModifier.decorators, PproductionModifierOperator.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PterminalDclDefault.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PterminalDclKwdModifiers.class);
		common.Decorator.applyDecorators(silver.definition.core.NAGDcl.decorators, PterminalDclAllModifiers.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NRegExpr.decorators, PregExpr.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalKeywordModifier.decorators, PterminalKeywordModifierIgnore.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalKeywordModifier.decorators, PterminalKeywordModifierMarking.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalKeywordModifier.decorators, PterminalKeywordModifierNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifiers.decorators, PterminalModifiersNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifiers.decorators, PterminalModifierSingle.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifiers.decorators, PterminalModifiersCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierLeft.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierRight.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NTerminalModifier.decorators, PterminalModifierPrecedence.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.NParserSpec.decorators, PparserSpec.class);
	}

	private static void setupInheritedAttributes(){
		silver.driver.util.NRootSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.core.NModuleExportedDefs.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.core.NGrammar.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Grammar] = "silver:definition:concrete_syntax:syntaxAst";
		silver.driver.util.NRootSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.core.NModuleExportedDefs.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_ModuleExportedDefs] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.core.NGrammar.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Grammar] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.concrete_syntax.PconcreteProductionDcl.occurs_local[silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_concreteProductionDcl] = "silver:definition:concrete_syntax:concreteProductionDcl:local:fName";
		//	local attribute namedSig::NamedSignature;
		silver.definition.concrete_syntax.PconcreteProductionDcl.localInheritedAttributes[silver.definition.concrete_syntax.Init.namedSig__ON__silver_definition_concrete_syntax_concreteProductionDcl] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];
		silver.definition.concrete_syntax.PconcreteProductionDcl.occurs_local[silver.definition.concrete_syntax.Init.namedSig__ON__silver_definition_concrete_syntax_concreteProductionDcl] = "silver:definition:concrete_syntax:concreteProductionDcl:local:namedSig";
		silver.definition.concrete_syntax.NProductionModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_config__ON__silver_definition_concrete_syntax_ProductionModifiers] = "silver:definition:env:config";
		silver.definition.concrete_syntax.NProductionModifiers.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.concrete_syntax.NProductionModifiers.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifiers] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NProductionModifiers.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifiers] = "silver:definition:concrete_syntax:productionModifiers";
		silver.definition.concrete_syntax.NProductionModifiers.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers] = "silver:definition:core:errors";
		silver.definition.concrete_syntax.NProductionModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_env__ON__silver_definition_concrete_syntax_ProductionModifiers] = "silver:definition:env:env";
		silver.definition.concrete_syntax.NProductionModifiers.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.concrete_syntax.NProductionModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifiers] = "silver:definition:concrete_syntax:productionName";
		silver.definition.concrete_syntax.NProductionModifiers.decorators.add(silver.definition.concrete_syntax.DproductionName.singleton);
		silver.definition.concrete_syntax.NProductionModifierList.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_config__ON__silver_definition_concrete_syntax_ProductionModifierList] = "silver:definition:env:config";
		silver.definition.concrete_syntax.NProductionModifierList.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.concrete_syntax.NProductionModifierList.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifierList] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NProductionModifierList.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifierList] = "silver:definition:concrete_syntax:productionModifiers";
		silver.definition.concrete_syntax.NProductionModifierList.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList] = "silver:definition:core:errors";
		silver.definition.concrete_syntax.NProductionModifierList.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_env__ON__silver_definition_concrete_syntax_ProductionModifierList] = "silver:definition:env:env";
		silver.definition.concrete_syntax.NProductionModifierList.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.concrete_syntax.NProductionModifierList.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifierList] = "silver:definition:concrete_syntax:productionName";
		silver.definition.concrete_syntax.NProductionModifierList.decorators.add(silver.definition.concrete_syntax.DproductionName.singleton);
		silver.definition.concrete_syntax.NProductionModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_config__ON__silver_definition_concrete_syntax_ProductionModifier] = "silver:definition:env:config";
		silver.definition.concrete_syntax.NProductionModifier.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.concrete_syntax.NProductionModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifier] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NProductionModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifier] = "silver:definition:concrete_syntax:productionModifiers";
		silver.definition.concrete_syntax.NProductionModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier] = "silver:definition:core:errors";
		silver.definition.concrete_syntax.NProductionModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_env__ON__silver_definition_concrete_syntax_ProductionModifier] = "silver:definition:env:env";
		silver.definition.concrete_syntax.NProductionModifier.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.concrete_syntax.NProductionModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifier] = "silver:definition:concrete_syntax:productionName";
		silver.definition.concrete_syntax.NProductionModifier.decorators.add(silver.definition.concrete_syntax.DproductionName.singleton);
		silver.definition.core.NProductionSignature.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature] = "silver:definition:concrete_syntax:concreteSyntaxTypeErrors";
		silver.definition.core.NProductionRHS.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS] = "silver:definition:concrete_syntax:concreteSyntaxTypeErrors";
		silver.definition.core.NProductionRHSElem.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem] = "silver:definition:concrete_syntax:concreteSyntaxTypeErrors";
		//	local attribute fstType::Type;
		silver.definition.core.PproductionSignature.localInheritedAttributes[silver.definition.concrete_syntax.Init.fstType__ON__silver_definition_core_productionSignature] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.definition.core.PproductionSignature.occurs_local[silver.definition.concrete_syntax.Init.fstType__ON__silver_definition_core_productionSignature] = "silver:definition:core:productionSignature:local:fstType";
		//	local attribute lstType::Type;
		silver.definition.core.PproductionSignature.localInheritedAttributes[silver.definition.concrete_syntax.Init.lstType__ON__silver_definition_core_productionSignature] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
		silver.definition.core.PproductionSignature.occurs_local[silver.definition.concrete_syntax.Init.lstType__ON__silver_definition_core_productionSignature] = "silver:definition:core:productionSignature:local:lstType";
		silver.definition.core.PproductionSignature.occurs_local[silver.definition.concrete_syntax.Init.checkFirst__ON__silver_definition_core_productionSignature] = "silver:definition:core:productionSignature:local:checkFirst";
		silver.definition.core.PproductionSignature.occurs_local[silver.definition.concrete_syntax.Init.checkSecond__ON__silver_definition_core_productionSignature] = "silver:definition:core:productionSignature:local:checkSecond";
		silver.definition.core.PproductionSignature.occurs_local[silver.definition.concrete_syntax.Init.errFirst__ON__silver_definition_core_productionSignature] = "silver:definition:core:productionSignature:local:errFirst";
		silver.definition.core.PproductionSignature.occurs_local[silver.definition.concrete_syntax.Init.errSecond__ON__silver_definition_core_productionSignature] = "silver:definition:core:productionSignature:local:errSecond";
		silver.definition.type.NType.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_permittedInConcreteSyntax__ON__silver_definition_type_Type] = "silver:definition:concrete_syntax:permittedInConcreteSyntax";
		silver.definition.core.NRoot.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Root] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.core.NAGDcls.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.core.NAGDcl.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.core.NRoot.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Root] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.core.NAGDcls.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.core.NAGDcl.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.concrete_syntax.PterminalDclDefault.occurs_local[silver.definition.concrete_syntax.Init.fName__ON__silver_definition_concrete_syntax_terminalDclDefault] = "silver:definition:concrete_syntax:terminalDclDefault:local:fName";
		silver.definition.concrete_syntax.NRegExpr.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_config__ON__silver_definition_concrete_syntax_RegExpr] = "silver:definition:env:config";
		silver.definition.concrete_syntax.NRegExpr.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.concrete_syntax.NRegExpr.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_RegExpr] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.NRegExpr.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.NRegExpr.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_RegExpr] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NRegExpr.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr] = "silver:definition:concrete_syntax:terminalRegExprSpec";
		silver.definition.concrete_syntax.NTerminalKeywordModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalKeywordModifier] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NTerminalKeywordModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalKeywordModifier] = "silver:definition:concrete_syntax:terminalModifiers";
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_config__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:env:config";
		silver.definition.concrete_syntax.NTerminalModifiers.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:concrete_syntax:terminalModifiers";
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:core:errors";
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_env__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:env:env";
		silver.definition.concrete_syntax.NTerminalModifiers.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.NTerminalModifiers.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:env:compiledGrammars";
		silver.definition.concrete_syntax.NTerminalModifiers.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.definition.concrete_syntax.NTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_concrete_syntax_TerminalModifiers] = "silver:definition:flow:env:flowEnv";
		silver.definition.concrete_syntax.NTerminalModifiers.decorators.add(silver.definition.flow.env.DflowEnv.singleton);
		silver.definition.concrete_syntax.NTerminalModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_config__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:env:config";
		silver.definition.concrete_syntax.NTerminalModifier.decorators.add(silver.definition.env.Dconfig.singleton);
		silver.definition.concrete_syntax.NTerminalModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:env:pp";
		silver.definition.concrete_syntax.NTerminalModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:concrete_syntax:terminalModifiers";
		silver.definition.concrete_syntax.NTerminalModifier.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:core:errors";
		silver.definition.concrete_syntax.NTerminalModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_env__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:env:env";
		silver.definition.concrete_syntax.NTerminalModifier.decorators.add(silver.definition.env.Denv.singleton);
		silver.definition.concrete_syntax.NTerminalModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.NTerminalModifier.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.NTerminalModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:env:compiledGrammars";
		silver.definition.concrete_syntax.NTerminalModifier.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.definition.concrete_syntax.NTerminalModifier.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_flow_env_flowEnv__ON__silver_definition_concrete_syntax_TerminalModifier] = "silver:definition:flow:env:flowEnv";
		silver.definition.concrete_syntax.NTerminalModifier.decorators.add(silver.definition.flow.env.DflowEnv.singleton);
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_sourceGrammar__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:env:sourceGrammar";
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_sourceLocation__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:env:sourceLocation";
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:env:fullName";
		silver.definition.concrete_syntax.NParserSpec.occurs_inh[silver.definition.concrete_syntax.Init.silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:env:compiledGrammars";
		silver.definition.concrete_syntax.NParserSpec.decorators.add(silver.definition.env.DcompiledGrammars.singleton);
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_cstAst__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:concrete_syntax:cstAst";
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_startNT__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:concrete_syntax:startNT";
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_moduleNames__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:env:moduleNames";
		silver.definition.concrete_syntax.NParserSpec.occurs_syn[silver.definition.concrete_syntax.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ParserSpec] = "silver:definition:env:unparse";
		//	local attribute med::ModuleExportedDefs;
		silver.definition.concrete_syntax.PparserSpec.localInheritedAttributes[silver.definition.concrete_syntax.Init.med__ON__silver_definition_concrete_syntax_parserSpec] = new common.Lazy[silver.definition.core.NModuleExportedDefs.num_inh_attrs];
		silver.definition.concrete_syntax.PparserSpec.occurs_local[silver.definition.concrete_syntax.Init.med__ON__silver_definition_concrete_syntax_parserSpec] = "silver:definition:concrete_syntax:parserSpec:local:med";
		//	local attribute decomposedTerminalPrefixes::Pair<[String] [String]>;
		silver.definition.concrete_syntax.PparserSpec.localInheritedAttributes[silver.definition.concrete_syntax.Init.decomposedTerminalPrefixes__ON__silver_definition_concrete_syntax_parserSpec] = new common.Lazy[core.NPair.num_inh_attrs];
		silver.definition.concrete_syntax.PparserSpec.occurs_local[silver.definition.concrete_syntax.Init.decomposedTerminalPrefixes__ON__silver_definition_concrete_syntax_parserSpec] = "silver:definition:concrete_syntax:parserSpec:local:decomposedTerminalPrefixes";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT FUNCTION unparseRootSpec String ::= r::Decorated RootSpec 
		// unparses <- [ "syntax [" ++ implode(",\n ", foldr(consSyntax, nilSyntax(), r.syntaxAst).unparses) ++ "]" ]
		((common.CollectionAttribute)silver.driver.util.PunparseRootSpec.localAttributes[silver.driver.util.Init.unparses__ON__silver_driver_util_unparseRootSpec]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("syntax [")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",\n ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.concrete_syntax.ast.NSyntax)core.Pfoldr.invoke(silver.definition.concrete_syntax.ast.PconsSyntax.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } }, context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax)); } })), (common.StringCatter)(new common.StringCatter("]")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		// unparses <- [ "parsers [" ++ implode(",\n ", map((.unparse), r.parserSpecs)) ++ "]" ]
		((common.CollectionAttribute)silver.driver.util.PunparseRootSpec.localAttributes[silver.driver.util.Init.unparses__ON__silver_driver_util_unparseRootSpec]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("parsers [")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",\n ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.concrete_syntax.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ParserSpec, context), context.childAsIsSynthesizedLazy(silver.driver.util.PunparseRootSpec.i_r, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec))); } })), (common.StringCatter)(new common.StringCatter("]")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } });
		//ASPECT PRODUCTION errorRootSpec top ::= _ _ _ _ 
		// top.syntaxAst = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parserSpecs = []
		silver.driver.util.PerrorRootSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION grammarRootSpec top ::= c1::Grammar _ _ _ 
		// top.syntaxAst = c1.syntaxAst
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Grammar)); } };
		// top.parserSpecs = c1.parserSpecs
		silver.driver.util.PgrammarRootSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PgrammarRootSpec.i_g).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Grammar)); } };
		//ASPECT PRODUCTION nilGrammar top ::= 
		// top.syntaxAst = []
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parserSpecs = []
		silver.definition.core.PnilGrammar.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consGrammar top ::= c1::Root c2::Grammar 
		// top.syntaxAst = c1.syntaxAst ++ c2.syntaxAst
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Grammar))); } };
		// top.parserSpecs = c1.parserSpecs ++ c2.parserSpecs
		silver.definition.core.PconsGrammar.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Grammar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Root), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsGrammar.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Grammar))); } };
		//ASPECT PRODUCTION moduleExportedDefs top ::= l::Location compiled::EnvTree<Decorated RootSpec> grammarDependencies::[String] need::[String] seen::[String] 
		// top.syntaxAst = if null(need) || null(rs) then [] else (head(rs).syntaxAst ++ recurse.syntaxAst)
		silver.definition.core.PmoduleExportedDefs.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))) || ((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs)); } }))); } };
		// top.parserSpecs = if null(need) || null(rs) then [] else (head(rs).parserSpecs ++ recurse.parserSpecs)
		silver.definition.core.PmoduleExportedDefs.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_ModuleExportedDefs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PmoduleExportedDefs.i_need))) || ((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs)))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.rs__ON__silver_definition_core_moduleExportedDefs))).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.core.Init.recurse__ON__silver_definition_core_moduleExportedDefs).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_ModuleExportedDefs)); } }))); } };
		silver.definition.concrete_syntax.PconcreteProductionDcl.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PproductionModifiersNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PproductionModifierSome.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PproductionModifierSingle.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PproductionModifiersCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PproductionModifierPrecedence.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PproductionModifierOperator.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION productionSignature top ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
		// top.concreteSyntaxTypeErrors := rhs.concreteSyntaxTypeErrors
		if(silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature] == null)
			silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature] = new silver.definition.concrete_syntax.CAconcreteSyntaxTypeErrors(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature);
		((common.CollectionAttribute)silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.PproductionSignature.i_rhs).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS)); } });
		// fstType = head(top.namedSignature.inputElements).typerep
		silver.definition.core.PproductionSignature.localAttributes[silver.definition.concrete_syntax.Init.fstType__ON__silver_definition_core_productionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } })).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)); } };
		// lstType = last(top.namedSignature.inputElements).typerep
		silver.definition.core.PproductionSignature.localAttributes[silver.definition.concrete_syntax.Init.lstType__ON__silver_definition_core_productionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)core.Plast.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } })).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)); } };
		// checkFirst = fstType.isTerminal || ! null(getOccursDcl("core:location", fstType.typeName, top.env))
		silver.definition.core.PproductionSignature.localAttributes[silver.definition.concrete_syntax.Init.checkFirst__ON__silver_definition_core_productionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.definition.concrete_syntax.Init.fstType__ON__silver_definition_core_productionSignature).synthesized(silver.definition.type.Init.silver_definition_type_isTerminal__ON__silver_definition_type_Type)) || (!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetOccursDcl.invoke((new common.StringCatter("core:location")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.definition.concrete_syntax.Init.fstType__ON__silver_definition_core_productionSignature).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionSignature))); } })))); } };
		// checkSecond = lstType.isTerminal || ! null(getOccursDcl("core:location", lstType.typeName, top.env))
		silver.definition.core.PproductionSignature.localAttributes[silver.definition.concrete_syntax.Init.checkSecond__ON__silver_definition_core_productionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localDecorated(silver.definition.concrete_syntax.Init.lstType__ON__silver_definition_core_productionSignature).synthesized(silver.definition.type.Init.silver_definition_type_isTerminal__ON__silver_definition_type_Type)) || (!((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetOccursDcl.invoke((new common.StringCatter("core:location")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.definition.concrete_syntax.Init.lstType__ON__silver_definition_core_productionSignature).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionSignature))); } })))); } };
		// errFirst = if checkFirst then [] else [ err(top.location, "Production has location annotation, but first element of signature does not have a location.") ]
		silver.definition.core.PproductionSignature.localAttributes[silver.definition.concrete_syntax.Init.errFirst__ON__silver_definition_core_productionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localAsIs(silver.definition.concrete_syntax.Init.checkFirst__ON__silver_definition_core_productionSignature)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Production has location annotation, but first element of signature does not have a location.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// errSecond = if checkSecond then [] else [ err(top.location, "Production has location annotation, but last element of signature does not have a location.") ]
		silver.definition.core.PproductionSignature.localAttributes[silver.definition.concrete_syntax.Init.errSecond__ON__silver_definition_core_productionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.localAsIs(silver.definition.concrete_syntax.Init.checkSecond__ON__silver_definition_core_productionSignature)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Production has location annotation, but last element of signature does not have a location.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } };
		// top.concreteSyntaxTypeErrors <- if null(top.namedSignature.namedInputElements) then [] else if length(top.namedSignature.namedInputElements) == 1 then if head(top.namedSignature.namedInputElements).elementName == "core:location" then if length(top.namedSignature.inputElements) > 1 then errFirst ++ errSecond else if null(top.namedSignature.inputElements) then [] else errFirst else [ err(top.location, "Annotation on this production is not handlable by the parser generator.") ] else [ err(top.location, "Annotations on this production are not handlable by the parser generator.") ]
		if(silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature] == null)
			silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature] = new silver.definition.concrete_syntax.CAconcreteSyntaxTypeErrors(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature);
		((common.CollectionAttribute)silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature)); } })) ? ((common.ConsCell)core.Pnil.invoke()) : (((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature)); } })).equals(Integer.valueOf((int)1)) ? (((common.StringCatter)((silver.definition.env.NNamedSignatureElement)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_namedInputElements__ON__silver_definition_env_NamedSignature)); } })).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_elementName__ON__silver_definition_env_NamedSignatureElement)).equals((new common.StringCatter("core:location"))) ? ((((Integer)core.PlistLength.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } })) > Integer.valueOf((int)1)) ? ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.Init.errFirst__ON__silver_definition_core_productionSignature), context.localAsIsLazy(silver.definition.concrete_syntax.Init.errSecond__ON__silver_definition_core_productionSignature))) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NNamedSignature)context.synthesized(silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature)); } })) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)context.localAsIs(silver.definition.concrete_syntax.Init.errFirst__ON__silver_definition_core_productionSignature)))) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Annotation on this production is not handlable by the parser generator.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionSignature)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Annotations on this production are not handlable by the parser generator.")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })))); } });
		//ASPECT PRODUCTION productionRHSNil top ::= 
		// top.concreteSyntaxTypeErrors := []
		if(silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS] == null)
			silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS] = new silver.definition.concrete_syntax.CAconcreteSyntaxTypeErrors(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS);
		((common.CollectionAttribute)silver.definition.core.PproductionRHSNil.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		//ASPECT PRODUCTION productionRHSCons top ::= h::ProductionRHSElem t::ProductionRHS 
		// top.concreteSyntaxTypeErrors := h.concreteSyntaxTypeErrors ++ t.concreteSyntaxTypeErrors
		if(silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS] == null)
			silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS] = new silver.definition.concrete_syntax.CAconcreteSyntaxTypeErrors(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS);
		((common.CollectionAttribute)silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS))); } });
		//ASPECT PRODUCTION productionRHSElem top ::= id::Name '::' t::TypeExpr 
		// top.concreteSyntaxTypeErrors := if t.typerep.permittedInConcreteSyntax then [] else [ err(t.location, t.pp ++ " is not permitted on concrete productions.  Only terminals and nonterminals (without type variables) can appear here") ]
		if(silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem] == null)
			silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem] = new silver.definition.concrete_syntax.CAconcreteSyntaxTypeErrors(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem);
		((common.CollectionAttribute)silver.definition.core.PproductionRHSElem.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)context.childDecorated(silver.definition.core.PproductionRHSElem.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_permittedInConcreteSyntax__ON__silver_definition_type_Type)) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.type.syntax.NTypeExpr)context.childDecorated(silver.definition.core.PproductionRHSElem.i_t).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionRHSElem.i_t).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)(new common.StringCatter(" is not permitted on concrete productions.  Only terminals and nonterminals (without type variables) can appear here"))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		//ASPECT DEFAULT PRODUCTION for Type
		// top.permittedInConcreteSyntax = false
		silver.definition.type.NType.defaultSynthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_permittedInConcreteSyntax__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		//ASPECT PRODUCTION nonterminalType top ::= fn::String params::[Type] 
		// top.permittedInConcreteSyntax = null(params)
		silver.definition.type.PnonterminalType.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_permittedInConcreteSyntax__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PnonterminalType.i_params))); } };
		//ASPECT PRODUCTION terminalType top ::= fn::String 
		// top.permittedInConcreteSyntax = true
		silver.definition.type.PterminalType.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_permittedInConcreteSyntax__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		//ASPECT PRODUCTION nonterminalDcl top ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeExprs ';' 
		// top.syntaxAst = [ syntaxNonterminal(nonterminalType(fName, tl.types), nilSyntax()) ]
		silver.definition.core.PnonterminalDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntaxDcl)new silver.definition.concrete_syntax.ast.PsyntaxNonterminal(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PnonterminalType(context.localAsIsLazy(silver.definition.core.Init.fName__ON__silver_definition_core_nonterminalDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PnonterminalDcl.i_tl, silver.definition.type.syntax.Init.silver_definition_type_syntax_types__ON__silver_definition_type_syntax_BracketedOptTypeExprs))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.concrete_syntax.ast.NSyntax)new silver.definition.concrete_syntax.ast.PnilSyntax()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		//ASPECT PRODUCTION root top ::= gdcl::GrammarDcl ms::ModuleStmts ims::ImportStmts ags::AGDcls 
		// top.syntaxAst = ags.syntaxAst
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls)); } };
		// top.parserSpecs = ags.parserSpecs
		silver.definition.core.Proot.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Root] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.core.Proot.i_ags).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls)); } };
		//ASPECT PRODUCTION nilAGDcls top ::= 
		// top.syntaxAst = []
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parserSpecs = []
		silver.definition.core.PnilAGDcls.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consAGDcls top ::= h::AGDcl t::AGDcls 
		// top.syntaxAst = h.syntaxAst ++ t.syntaxAst
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls))); } };
		// top.parserSpecs = h.parserSpecs ++ t.parserSpecs
		silver.definition.core.PconsAGDcls.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PconsAGDcls.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls))); } };
		//ASPECT DEFAULT PRODUCTION for AGDcl
		// top.syntaxAst = []
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parserSpecs = []
		silver.definition.core.NAGDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION appendAGDcl top ::= ag1::AGDcl ag2::AGDcl 
		// top.syntaxAst = ag1.syntaxAst ++ ag2.syntaxAst
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl))); } };
		// top.parserSpecs = ag1.parserSpecs ++ ag2.parserSpecs
		silver.definition.core.PappendAGDcl.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_h, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl), context.childDecoratedSynthesizedLazy(silver.definition.core.PappendAGDcl.i_t, silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl))); } };
		silver.definition.concrete_syntax.PterminalDclDefault.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalDclKwdModifiers.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalDclAllModifiers.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PregExpr.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalKeywordModifierIgnore.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalKeywordModifierMarking.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalKeywordModifierNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalModifiersNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalModifierSingle.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalModifiersCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalModifierLeft.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalModifierRight.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PterminalModifierPrecedence.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.PparserSpec.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_definition_concrete_syntax_concreteProductionDcl = 0;
	public static int count_inh__ON__ProductionModifiers = 0;
	public static int count_syn__ON__ProductionModifiers = 0;
	public static int count_inh__ON__ProductionModifierList = 0;
	public static int count_syn__ON__ProductionModifierList = 0;
	public static int count_inh__ON__ProductionModifier = 0;
	public static int count_syn__ON__ProductionModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_productionModifiersNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_productionModifierSome = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_productionModifierSingle = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_productionModifiersCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_productionModifierPrecedence = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_productionModifierOperator = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalDclDefault = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalDclKwdModifiers = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalDclAllModifiers = 0;
	public static int count_inh__ON__RegExpr = 0;
	public static int count_syn__ON__RegExpr = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_regExpr = 0;
	public static int count_inh__ON__TerminalKeywordModifier = 0;
	public static int count_syn__ON__TerminalKeywordModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalKeywordModifierIgnore = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalKeywordModifierMarking = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalKeywordModifierNone = 0;
	public static int count_inh__ON__TerminalModifiers = 0;
	public static int count_syn__ON__TerminalModifiers = 0;
	public static int count_inh__ON__TerminalModifier = 0;
	public static int count_syn__ON__TerminalModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalModifiersNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalModifierSingle = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalModifiersCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalModifierLeft = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalModifierRight = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_terminalModifierPrecedence = 0;
	public static int count_inh__ON__ParserSpec = 0;
	public static int count_syn__ON__ParserSpec = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_parserSpec = 0;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_ModuleExportedDefs = silver.definition.core.Init.count_syn__ON__ModuleExportedDefs++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec = silver.driver.util.Init.count_syn__ON__RootSpec++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_ModuleExportedDefs = silver.definition.core.Init.count_syn__ON__ModuleExportedDefs++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Grammar = silver.definition.core.Init.count_syn__ON__Grammar++;
public static final int fName__ON__silver_definition_concrete_syntax_concreteProductionDcl = silver.definition.concrete_syntax.Init.count_local__ON__silver_definition_concrete_syntax_concreteProductionDcl++;
public static final int namedSig__ON__silver_definition_concrete_syntax_concreteProductionDcl = silver.definition.concrete_syntax.Init.count_local__ON__silver_definition_concrete_syntax_concreteProductionDcl++;
public static final int silver_definition_env_config__ON__silver_definition_concrete_syntax_ProductionModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifiers++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifiers++;
public static final int silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifiers++;
public static final int silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifiers++;
public static final int silver_definition_env_env__ON__silver_definition_concrete_syntax_ProductionModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifiers++;
public static final int silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifiers++;
public static final int silver_definition_env_config__ON__silver_definition_concrete_syntax_ProductionModifierList = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifierList++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifierList = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifierList++;
public static final int silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifierList = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifierList++;
public static final int silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifierList++;
public static final int silver_definition_env_env__ON__silver_definition_concrete_syntax_ProductionModifierList = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifierList++;
public static final int silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifierList = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifierList++;
public static final int silver_definition_env_config__ON__silver_definition_concrete_syntax_ProductionModifier = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifier++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifier = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifier++;
public static final int silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifier = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifier++;
public static final int silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier = silver.definition.concrete_syntax.Init.count_syn__ON__ProductionModifier++;
public static final int silver_definition_env_env__ON__silver_definition_concrete_syntax_ProductionModifier = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifier++;
public static final int silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ProductionModifier = silver.definition.concrete_syntax.Init.count_inh__ON__ProductionModifier++;
public static final int silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionSignature = silver.definition.core.Init.count_syn__ON__ProductionSignature++;
public static final int silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHS = silver.definition.core.Init.count_syn__ON__ProductionRHS++;
public static final int silver_definition_concrete_syntax_concreteSyntaxTypeErrors__ON__silver_definition_core_ProductionRHSElem = silver.definition.core.Init.count_syn__ON__ProductionRHSElem++;
public static final int fstType__ON__silver_definition_core_productionSignature = silver.definition.core.Init.count_local__ON__silver_definition_core_productionSignature++;
public static final int lstType__ON__silver_definition_core_productionSignature = silver.definition.core.Init.count_local__ON__silver_definition_core_productionSignature++;
public static final int checkFirst__ON__silver_definition_core_productionSignature = silver.definition.core.Init.count_local__ON__silver_definition_core_productionSignature++;
public static final int checkSecond__ON__silver_definition_core_productionSignature = silver.definition.core.Init.count_local__ON__silver_definition_core_productionSignature++;
public static final int errFirst__ON__silver_definition_core_productionSignature = silver.definition.core.Init.count_local__ON__silver_definition_core_productionSignature++;
public static final int errSecond__ON__silver_definition_core_productionSignature = silver.definition.core.Init.count_local__ON__silver_definition_core_productionSignature++;
public static final int silver_definition_concrete_syntax_permittedInConcreteSyntax__ON__silver_definition_type_Type = silver.definition.type.Init.count_syn__ON__Type++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_Root = silver.definition.core.Init.count_syn__ON__Root++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcls = silver.definition.core.Init.count_syn__ON__AGDcls++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_core_AGDcl = silver.definition.core.Init.count_syn__ON__AGDcl++;
public static final int fName__ON__silver_definition_concrete_syntax_terminalDclDefault = silver.definition.concrete_syntax.Init.count_local__ON__silver_definition_concrete_syntax_terminalDclDefault++;
public static final int silver_definition_env_config__ON__silver_definition_concrete_syntax_RegExpr = silver.definition.concrete_syntax.Init.count_inh__ON__RegExpr++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_RegExpr = silver.definition.concrete_syntax.Init.count_inh__ON__RegExpr++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_RegExpr = silver.definition.concrete_syntax.Init.count_syn__ON__RegExpr++;
public static final int silver_definition_concrete_syntax_terminalRegExprSpec__ON__silver_definition_concrete_syntax_RegExpr = silver.definition.concrete_syntax.Init.count_syn__ON__RegExpr++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalKeywordModifier = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalKeywordModifier++;
public static final int silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalKeywordModifier = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalKeywordModifier++;
public static final int silver_definition_env_config__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifiers++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifiers++;
public static final int silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifiers++;
public static final int silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifiers++;
public static final int silver_definition_env_env__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifiers++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifiers++;
public static final int silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifiers++;
public static final int silver_definition_flow_env_flowEnv__ON__silver_definition_concrete_syntax_TerminalModifiers = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifiers++;
public static final int silver_definition_env_config__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifier++;
public static final int silver_definition_env_pp__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifier++;
public static final int silver_definition_concrete_syntax_terminalModifiers__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifier++;
public static final int silver_definition_core_errors__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_syn__ON__TerminalModifier++;
public static final int silver_definition_env_env__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifier++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifier++;
public static final int silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifier++;
public static final int silver_definition_flow_env_flowEnv__ON__silver_definition_concrete_syntax_TerminalModifier = silver.definition.concrete_syntax.Init.count_inh__ON__TerminalModifier++;
public static final int silver_definition_env_sourceGrammar__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int silver_definition_env_sourceLocation__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int silver_definition_env_fullName__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int silver_definition_env_compiledGrammars__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_inh__ON__ParserSpec++;
public static final int silver_definition_concrete_syntax_cstAst__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int silver_definition_concrete_syntax_startNT__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int silver_definition_env_moduleNames__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ParserSpec = silver.definition.concrete_syntax.Init.count_syn__ON__ParserSpec++;
public static final int med__ON__silver_definition_concrete_syntax_parserSpec = silver.definition.concrete_syntax.Init.count_local__ON__silver_definition_concrete_syntax_parserSpec++;
public static final int decomposedTerminalPrefixes__ON__silver_definition_concrete_syntax_parserSpec = silver.definition.concrete_syntax.Init.count_local__ON__silver_definition_concrete_syntax_parserSpec++;
}
