package silver.definition.concrete_syntax.ast;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.translation.java.core.Init.init();
		silver.translation.java.type.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.decorators, PconsLexerClassMod.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.decorators, PnilLexerClassMod.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.decorators, PlexerClassSubmits.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.decorators, PlexerClassDominates.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.decorators, PconsTerminalMod.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.decorators, PnilTerminalMod.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermIgnore.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermMarking.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermPrecedence.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermAssociation.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermClasses.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermSubmits.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermDominates.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermAction.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.decorators, PconsProductionMod.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.decorators, PnilProductionMod.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.decorators, PprodPrecedence.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.decorators, PprodOperator.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.decorators, PprodAction.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.decorators, PprodLayout.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxRoot.decorators, PcstRoot.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntax.decorators, PnilSyntax.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntax.decorators, PconsSyntax.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxNonterminal.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxTerminal.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxProduction.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxLexerClass.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxParserAttribute.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxDisambiguationGroup.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = "silver:definition:concrete_syntax:ast:dominatesXML";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = "silver:definition:concrete_syntax:ast:submitsXML";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = "silver:definition:concrete_syntax:ast:dominatesXML";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = "silver:definition:concrete_syntax:ast:submitsXML";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.PlexerClassSubmits.occurs_local[silver.definition.concrete_syntax.ast.Init.subRefs__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits] = "silver:definition:concrete_syntax:ast:lexerClassSubmits:local:subRefs";
		silver.definition.concrete_syntax.ast.PlexerClassDominates.occurs_local[silver.definition.concrete_syntax.ast.Init.domRefs__ON__silver_definition_concrete_syntax_ast_lexerClassDominates] = "silver:definition:concrete_syntax:ast:lexerClassDominates:local:domRefs";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:dominatesXML";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:submitsXML";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:ignored";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:acode";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:lexerclassesXML";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:opPrecedence";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:opAssociation";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:marking";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_terminalName__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:definition:concrete_syntax:ast:terminalName";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.decorators.add(silver.definition.concrete_syntax.ast.DterminalName.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:dominatesXML";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:submitsXML";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:ignored";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:acode";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:lexerclassesXML";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:opPrecedence";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:opAssociation";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:marking";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_terminalName__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:definition:concrete_syntax:ast:terminalName";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators.add(silver.definition.concrete_syntax.ast.DterminalName.singleton);
		silver.definition.concrete_syntax.ast.PtermClasses.occurs_local[silver.definition.concrete_syntax.ast.Init.clsRefsL__ON__silver_definition_concrete_syntax_ast_termClasses] = "silver:definition:concrete_syntax:ast:termClasses:local:clsRefsL";
		silver.definition.concrete_syntax.ast.PtermClasses.occurs_local[silver.definition.concrete_syntax.ast.Init.clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses] = "silver:definition:concrete_syntax:ast:termClasses:local:clsRefs";
		silver.definition.concrete_syntax.ast.PtermSubmits.occurs_local[silver.definition.concrete_syntax.ast.Init.subRefs__ON__silver_definition_concrete_syntax_ast_termSubmits] = "silver:definition:concrete_syntax:ast:termSubmits:local:subRefs";
		silver.definition.concrete_syntax.ast.PtermDominates.occurs_local[silver.definition.concrete_syntax.ast.Init.domRefs__ON__silver_definition_concrete_syntax_ast_termDominates] = "silver:definition:concrete_syntax:ast:termDominates:local:domRefs";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:acode";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:productionPrecedence";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:customLayout";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:productionOperator";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers] = "silver:definition:concrete_syntax:productionName";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifiers.decorators.add(silver.definition.concrete_syntax.DproductionName.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:acode";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:productionPrecedence";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:customLayout";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:productionOperator";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = "silver:definition:concrete_syntax:productionName";
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.decorators.add(silver.definition.concrete_syntax.DproductionName.singleton);
		silver.definition.concrete_syntax.ast.PprodOperator.occurs_local[silver.definition.concrete_syntax.ast.Init.termRef__ON__silver_definition_concrete_syntax_ast_prodOperator] = "silver:definition:concrete_syntax:ast:prodOperator:local:termRef";
		silver.definition.concrete_syntax.ast.PprodLayout.occurs_local[silver.definition.concrete_syntax.ast.Init.termRefs__ON__silver_definition_concrete_syntax_ast_prodLayout] = "silver:definition:concrete_syntax:ast:prodLayout:local:termRefs";
		silver.definition.regex.NRegex.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegexSeq.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegexRepetition.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegexItem.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegexCharSet.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegexCharSetItem.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSetItem] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegexChar.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.regex.NRegex.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex] = "silver:definition:concrete_syntax:ast:unwrappedXML";
		silver.definition.regex.NRegexSeq.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_RegexSeq] = "silver:definition:concrete_syntax:ast:unwrappedXML";
		silver.definition.concrete_syntax.ast.NSyntaxRoot.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxRoot.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.concrete_syntax.ast.NSyntaxRoot.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = "silver:definition:env:unparse";
		//	local attribute s2::Syntax;
		silver.definition.concrete_syntax.ast.PcstRoot.localInheritedAttributes[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot] = new common.Lazy[silver.definition.concrete_syntax.ast.NSyntax.num_inh_attrs];
		silver.definition.concrete_syntax.ast.PcstRoot.occurs_local[silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot] = "silver:definition:concrete_syntax:ast:cstRoot:local:s2";
		silver.definition.concrete_syntax.ast.PcstRoot.occurs_local[silver.definition.concrete_syntax.ast.Init.startFound__ON__silver_definition_concrete_syntax_ast_cstRoot] = "silver:definition:concrete_syntax:ast:cstRoot:local:startFound";
		silver.definition.concrete_syntax.ast.PcstRoot.occurs_local[silver.definition.concrete_syntax.ast.Init.univLayout__ON__silver_definition_concrete_syntax_ast_cstRoot] = "silver:definition:concrete_syntax:ast:cstRoot:local:univLayout";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:cstDcls";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntax.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:cstProds";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:cstNTProds";
		silver.definition.concrete_syntax.ast.NSyntax.decorators.add(silver.definition.concrete_syntax.ast.DcstNTProds.singleton);
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:cstNormalize";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:allIgnoreTerminals";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:allMarkingTerminals";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:univLayout";
		silver.definition.concrete_syntax.ast.NSyntax.decorators.add(silver.definition.concrete_syntax.ast.DunivLayout.singleton);
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:containingGrammar";
		silver.definition.concrete_syntax.ast.NSyntax.decorators.add(silver.definition.concrete_syntax.ast.DcontainingGrammar.singleton);
		silver.definition.concrete_syntax.ast.NSyntax.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:definition:concrete_syntax:ast:prefixesForTerminals";
		silver.definition.concrete_syntax.ast.NSyntax.decorators.add(silver.definition.concrete_syntax.ast.DprefixesForTerminals.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:cstDcls";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:cstEnv";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators.add(silver.definition.concrete_syntax.ast.DcstEnv.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:cstErrors";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:cstProds";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:cstNTProds";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators.add(silver.definition.concrete_syntax.ast.DcstNTProds.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:cstNormalize";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:sortKey";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:allIgnoreTerminals";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:allMarkingTerminals";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:univLayout";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators.add(silver.definition.concrete_syntax.ast.DunivLayout.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:xmlCopper";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classDomContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:classDomContribs";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classSubContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:classSubContribs";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:unparses";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:containingGrammar";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators.add(silver.definition.concrete_syntax.ast.DcontainingGrammar.singleton);
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_inh[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:definition:concrete_syntax:ast:prefixesForTerminals";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators.add(silver.definition.concrete_syntax.ast.DprefixesForTerminals.singleton);
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.occurs_local[silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_syntaxTerminal] = "silver:definition:concrete_syntax:ast:syntaxTerminal:local:pfx";
		silver.definition.concrete_syntax.ast.PconvertAssocNXML.occurs_local[silver.definition.concrete_syntax.ast.Init.assoc__ON__silver_definition_concrete_syntax_ast_convertAssocNXML] = "silver:definition:concrete_syntax:ast:convertAssocNXML:local:assoc";
		silver.definition.concrete_syntax.ast.PsyntaxProduction.occurs_local[silver.definition.concrete_syntax.ast.Init.lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction] = "silver:definition:concrete_syntax:ast:syntaxProduction:local:lhsRef";
		silver.definition.concrete_syntax.ast.PsyntaxProduction.occurs_local[silver.definition.concrete_syntax.ast.Init.rhsRefs__ON__silver_definition_concrete_syntax_ast_syntaxProduction] = "silver:definition:concrete_syntax:ast:syntaxProduction:local:rhsRefs";
		silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.occurs_local[silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation] = "silver:definition:concrete_syntax:ast:insertLocationAnnotation:local:pfx";
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.occurs_local[silver.definition.concrete_syntax.ast.Init.trefs__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup] = "silver:definition:concrete_syntax:ast:syntaxDisambiguationGroup:local:trefs";
	}

	private static void initProductionAttributeDefinitions(){
		silver.definition.concrete_syntax.ast.PconsLexerClassMod.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PnilLexerClassMod.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for SyntaxLexerClassModifier
		// top.dominatesXML = ""
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.submitsXML = ""
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		silver.definition.concrete_syntax.ast.PlexerClassSubmits.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PlexerClassDominates.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PconsTerminalMod.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PnilTerminalMod.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for SyntaxTerminalModifier
		// top.cstErrors := []
		if(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] == null)
			silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.dominatesXML = ""
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.submitsXML = ""
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.lexerclassesXML = ""
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.ignored = false
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.marking = false
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.acode = ""
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.opPrecedence = nothing()
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// top.opAssociation = nothing()
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		silver.definition.concrete_syntax.ast.PtermIgnore.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermMarking.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermPrecedence.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermAssociation.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermClasses.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermSubmits.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermDominates.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PtermAction.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PconsProductionMod.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PnilProductionMod.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for SyntaxProductionModifier
		// top.cstErrors := []
		if(silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] == null)
			silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new silver.definition.concrete_syntax.ast.CAcstErrors(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier);
		((common.CollectionAttribute)silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.acode = ""
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.customLayout = nothing()
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// top.productionOperator = nothing()
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// top.productionPrecedence = nothing()
		silver.definition.concrete_syntax.ast.NSyntaxProductionModifier.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		silver.definition.concrete_syntax.ast.PprodPrecedence.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PprodOperator.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PprodAction.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PprodLayout.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION regexEpsilon top ::= 
		// top.xmlCopper = "<EmptyString/>"
		silver.definition.regex.PregexEpsilon.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("<EmptyString/>")); } };
		// top.unwrappedXML = top.xmlCopper
		silver.definition.regex.PregexEpsilon.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex)); } };
		//ASPECT PRODUCTION regexSeq top ::= h::RegexSeq 
		// top.xmlCopper = h.xmlCopper
		silver.definition.regex.PregexSeq.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeq.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq)); } };
		// top.unwrappedXML = top.xmlCopper
		silver.definition.regex.PregexSeq.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex)); } };
		//ASPECT PRODUCTION regexChoice top ::= h::RegexSeq _ t::Regex 
		// top.xmlCopper = "<Choice>" ++ h.xmlCopper ++ t.unwrappedXML ++ "</Choice>"
		silver.definition.regex.PregexChoice.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<Choice>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexChoice.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexChoice.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex)), (common.StringCatter)(new common.StringCatter("</Choice>"))))); } };
		// top.unwrappedXML = h.xmlCopper ++ t.unwrappedXML
		silver.definition.regex.PregexChoice.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexChoice.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexChoice.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex))); } };
		//ASPECT PRODUCTION regexSeqSnoc top ::= h::RegexSeq t::RegexRepetition 
		// top.xmlCopper = "<Concatenation>" ++ h.unwrappedXML ++ t.xmlCopper ++ "</Concatenation>"
		silver.definition.regex.PregexSeqSnoc.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<Concatenation>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqSnoc.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_RegexSeq)), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqSnoc.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition)), (common.StringCatter)(new common.StringCatter("</Concatenation>"))))); } };
		// top.unwrappedXML = h.unwrappedXML ++ t.xmlCopper
		silver.definition.regex.PregexSeqSnoc.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_RegexSeq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqSnoc.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_RegexSeq)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqSnoc.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition))); } };
		//ASPECT PRODUCTION regexSeqOne top ::= t::RegexRepetition 
		// top.xmlCopper = t.xmlCopper
		silver.definition.regex.PregexSeqOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSeqOne.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition)); } };
		// top.unwrappedXML = top.xmlCopper
		silver.definition.regex.PregexSeqOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_RegexSeq] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq)); } };
		//ASPECT PRODUCTION regexKleene top ::= i::RegexItem _ 
		// top.xmlCopper = "<KleeneStar>" ++ i.xmlCopper ++ "</KleeneStar>"
		silver.definition.regex.PregexKleene.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<KleeneStar>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexKleene.i_i).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem)), (common.StringCatter)(new common.StringCatter("</KleeneStar>")))); } };
		//ASPECT PRODUCTION regexPlus top ::= i::RegexItem _ 
		// top.xmlCopper = "<Concatenation>" ++ i.xmlCopper ++ "<KleeneStar>" ++ i.xmlCopper ++ "</KleeneStar></Concatenation>"
		silver.definition.regex.PregexPlus.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<Concatenation>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexPlus.i_i).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("<KleeneStar>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexPlus.i_i).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem)), (common.StringCatter)(new common.StringCatter("</KleeneStar></Concatenation>")))))); } };
		//ASPECT PRODUCTION regexOptional top ::= i::RegexItem _ 
		// top.xmlCopper = "<Choice>" ++ i.xmlCopper ++ "<EmptyString/></Choice>"
		silver.definition.regex.PregexOptional.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<Choice>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexOptional.i_i).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem)), (common.StringCatter)(new common.StringCatter("<EmptyString/></Choice>")))); } };
		//ASPECT PRODUCTION regexOnce top ::= i::RegexItem 
		// top.xmlCopper = i.xmlCopper
		silver.definition.regex.PregexOnce.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexOnce.i_i).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem)); } };
		//ASPECT PRODUCTION regexCharItem top ::= char::RegexChar 
		// top.xmlCopper = "<CharacterSet><SingleCharacter char=\"" ++ char.xmlCopper ++ "\"/></CharacterSet>"
		silver.definition.regex.PregexCharItem.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<CharacterSet><SingleCharacter char=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexCharItem.i_char).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar)), (common.StringCatter)(new common.StringCatter("\"/></CharacterSet>")))); } };
		//ASPECT PRODUCTION regexWildcard top ::= _ 
		// top.xmlCopper = "<CharacterSet invert=\"true\"><SingleCharacter char=\"&#10;\"/></CharacterSet>"
		silver.definition.regex.PregexWildcard.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("<CharacterSet invert=\"true\"><SingleCharacter char=\"&#10;\"/></CharacterSet>")); } };
		//ASPECT PRODUCTION regexSet top ::= _ g::RegexCharSet _ 
		// top.xmlCopper = "<CharacterSet>" ++ g.xmlCopper ++ "</CharacterSet>"
		silver.definition.regex.PregexSet.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<CharacterSet>")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSet.i_g).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet)), (common.StringCatter)(new common.StringCatter("</CharacterSet>")))); } };
		//ASPECT PRODUCTION regexSetInverted top ::= _ _ g::RegexCharSet _ 
		// top.xmlCopper = "<CharacterSet invert=\"true\">" ++ g.xmlCopper ++ "</CharacterSet>"
		silver.definition.regex.PregexSetInverted.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<CharacterSet invert=\"true\">")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetInverted.i_g).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet)), (common.StringCatter)(new common.StringCatter("</CharacterSet>")))); } };
		//ASPECT PRODUCTION regexGroup top ::= _ r::Regex _ 
		// top.xmlCopper = r.xmlCopper
		silver.definition.regex.PregexGroup.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexGroup.i_r).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex)); } };
		//ASPECT PRODUCTION regexCharSetSnoc top ::= h::RegexCharSet t::RegexCharSetItem 
		// top.xmlCopper = h.xmlCopper ++ t.xmlCopper
		silver.definition.regex.PregexCharSetSnoc.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexCharSetSnoc.i_h).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexCharSetSnoc.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSetItem))); } };
		//ASPECT PRODUCTION regexCharSetOne top ::= t::RegexCharSetItem 
		// top.xmlCopper = t.xmlCopper
		silver.definition.regex.PregexCharSetOne.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.regex.PregexCharSetOne.i_t).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSetItem)); } };
		//ASPECT PRODUCTION regexSetChar top ::= char::RegexChar 
		// top.xmlCopper = "<SingleCharacter char=\"" ++ char.xmlCopper ++ "\"/>"
		silver.definition.regex.PregexSetChar.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSetItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<SingleCharacter char=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetChar.i_char).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar)), (common.StringCatter)(new common.StringCatter("\"/>")))); } };
		//ASPECT PRODUCTION regexSetRange top ::= l::RegexChar _ u::RegexChar 
		// top.xmlCopper = "<CharacterRange lower=\"" ++ l.xmlCopper ++ "\" upper=\"" ++ u.xmlCopper ++ "\"/>"
		silver.definition.regex.PregexSetRange.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSetItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("<CharacterRange lower=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetRange.i_l).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" upper=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.regex.PregexSetRange.i_u).synthesized(silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar)), (common.StringCatter)(new common.StringCatter("\"/>")))))); } };
		//ASPECT PRODUCTION regexChar top ::= char::RegexChar_t 
		// top.xmlCopper = xmlEscapeChar(char.lexeme)
		silver.definition.regex.PregexChar.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.concrete_syntax.ast.PxmlEscapeChar.invoke(((common.StringCatter)((silver.definition.regex.TRegexChar_t)context.childAsIs(silver.definition.regex.PregexChar.i_char)).lexeme))); } };
		//ASPECT PRODUCTION regexEscapedChar top ::= esc::EscapedChar_t 
		// top.xmlCopper = if esc.lexeme == "\\r" then "&#13;" else if esc.lexeme == "\\n" then "&#10;" else if esc.lexeme == "\\t" then "&#9;" else xmlEscapeChar(substring(1, 2, esc.lexeme))
		silver.definition.regex.PregexEscapedChar.synthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((common.StringCatter)((silver.definition.regex.TEscapedChar_t)context.childAsIs(silver.definition.regex.PregexEscapedChar.i_esc)).lexeme).equals((new common.StringCatter("\\r"))) ? (new common.StringCatter("&#13;")) : (((common.StringCatter)((silver.definition.regex.TEscapedChar_t)context.childAsIs(silver.definition.regex.PregexEscapedChar.i_esc)).lexeme).equals((new common.StringCatter("\\n"))) ? (new common.StringCatter("&#10;")) : (((common.StringCatter)((silver.definition.regex.TEscapedChar_t)context.childAsIs(silver.definition.regex.PregexEscapedChar.i_esc)).lexeme).equals((new common.StringCatter("\\t"))) ? (new common.StringCatter("&#9;")) : ((common.StringCatter)silver.definition.concrete_syntax.ast.PxmlEscapeChar.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)1), Integer.valueOf((int)2), ((common.StringCatter)((silver.definition.regex.TEscapedChar_t)context.childAsIs(silver.definition.regex.PregexEscapedChar.i_esc)).lexeme))); } }))))); } };
		//FUNCTION xmlEscapeChar String ::= ch::String 
		silver.definition.concrete_syntax.ast.PcstRoot.initProductionAttributeDefinitions();
		//FUNCTION makeCopperName String ::= str::String 
		silver.definition.concrete_syntax.ast.PnilSyntax.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PconsSyntax.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for SyntaxDcl
		// top.cstProds = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.allIgnoreTerminals = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.allMarkingTerminals = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.classDomContribs = error("Internal compiler error: should only ever be demanded of lexer classes")
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classDomContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal compiler error: should only ever be demanded of lexer classes")))); } };
		// top.classSubContribs = error("Internal compiler error: should only ever be demanded of lexer classes")
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.Init.silver_definition_concrete_syntax_ast_classSubContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal compiler error: should only ever be demanded of lexer classes")))); } };
		silver.definition.concrete_syntax.ast.PsyntaxNonterminal.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.initProductionAttributeDefinitions();
		//FUNCTION convertAssocNXML String ::= opassoc::Maybe<String> 
		// assoc = fromMaybe("", opassoc)
		silver.definition.concrete_syntax.ast.PconvertAssocNXML.localAttributes[silver.definition.concrete_syntax.ast.Init.assoc__ON__silver_definition_concrete_syntax_ast_convertAssocNXML] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.PfromMaybe.invoke((new common.StringCatter("")), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.concrete_syntax.ast.PconvertAssocNXML.i_opassoc)))); } };
		silver.definition.concrete_syntax.ast.PsyntaxProduction.initProductionAttributeDefinitions();
		//FUNCTION fetchChildren String ::= i::Integer ns::[NamedSignatureElement] 
		//FUNCTION insertLocationAnnotation String ::= ns::Decorated NamedSignature 
		// pfx = if null(ns.inputElements) then "" else ", "
		silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.localAttributes[silver.definition.concrete_syntax.ast.Init.pfx__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsSynthesizedLazy(silver.definition.concrete_syntax.ast.PinsertLocationAnnotation.i_ns, silver.definition.env.Init.silver_definition_env_inputElements__ON__silver_definition_env_NamedSignature))) ? (new common.StringCatter("")) : (new common.StringCatter(", "))); } };
		//FUNCTION lookupStrings [[Decorated SyntaxDcl]] ::= t::[String] e::EnvTree<Decorated SyntaxDcl> 
		//FUNCTION checkRHS [String] ::= pn::String rhs::[Type] refs::[[Decorated SyntaxDcl]] 
		silver.definition.concrete_syntax.ast.PsyntaxLexerClass.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PsyntaxParserAttribute.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.PsyntaxDisambiguationGroup.initProductionAttributeDefinitions();
		//FUNCTION syntaxDclLte Boolean ::= l::SyntaxDcl r::SyntaxDcl 
		//FUNCTION xmlCopperRef String ::= d::Decorated SyntaxDcl 
	}

	public static int count_inh__ON__SyntaxLexerClassModifiers = 0;
	public static int count_syn__ON__SyntaxLexerClassModifiers = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_consLexerClassMod = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_nilLexerClassMod = 0;
	public static int count_inh__ON__SyntaxLexerClassModifier = 0;
	public static int count_syn__ON__SyntaxLexerClassModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_lexerClassDominates = 0;
	public static int count_inh__ON__SyntaxTerminalModifiers = 0;
	public static int count_syn__ON__SyntaxTerminalModifiers = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_consTerminalMod = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_nilTerminalMod = 0;
	public static int count_inh__ON__SyntaxTerminalModifier = 0;
	public static int count_syn__ON__SyntaxTerminalModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termIgnore = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termMarking = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termPrecedence = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termAssociation = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termClasses = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termSubmits = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termDominates = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_termAction = 0;
	public static int count_inh__ON__SyntaxProductionModifiers = 0;
	public static int count_syn__ON__SyntaxProductionModifiers = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_consProductionMod = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_nilProductionMod = 0;
	public static int count_inh__ON__SyntaxProductionModifier = 0;
	public static int count_syn__ON__SyntaxProductionModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_prodPrecedence = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_prodOperator = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_prodAction = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_prodLayout = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_xmlEscapeChar = 0;
	public static int count_inh__ON__SyntaxRoot = 0;
	public static int count_syn__ON__SyntaxRoot = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_cstRoot = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_makeCopperName = 0;
	public static int count_inh__ON__Syntax = 0;
	public static int count_syn__ON__Syntax = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_nilSyntax = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_consSyntax = 0;
	public static int count_inh__ON__SyntaxDcl = 0;
	public static int count_syn__ON__SyntaxDcl = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxNonterminal = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxTerminal = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_convertAssocNXML = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxProduction = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_fetchChildren = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_lookupStrings = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_checkRHS = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxLexerClass = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxParserAttribute = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_syntaxDclLte = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_xmlCopperRef = 0;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxLexerClassModifiers++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifiers++;
public static final int silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifiers++;
public static final int silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifiers++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifiers++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxLexerClassModifier++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifier++;
public static final int silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifier++;
public static final int silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifier++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifier++;
public static final int subRefs__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_lexerClassSubmits++;
public static final int domRefs__ON__silver_definition_concrete_syntax_ast_lexerClassDominates = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_lexerClassDominates++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_terminalName__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxTerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_dominatesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_submitsXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_ignored__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_lexerclassesXML__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_opPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_opAssociation__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_marking__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_terminalName__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxTerminalModifier++;
public static final int clsRefsL__ON__silver_definition_concrete_syntax_ast_termClasses = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_termClasses++;
public static final int clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_termClasses++;
public static final int subRefs__ON__silver_definition_concrete_syntax_ast_termSubmits = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_termSubmits++;
public static final int domRefs__ON__silver_definition_concrete_syntax_ast_termDominates = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_termDominates++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifiers = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_acode__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_productionPrecedence__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_customLayout__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_productionOperator__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxProductionModifier++;
public static final int silver_definition_concrete_syntax_productionName__ON__silver_definition_concrete_syntax_ast_SyntaxProductionModifier = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxProductionModifier++;
public static final int termRef__ON__silver_definition_concrete_syntax_ast_prodOperator = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_prodOperator++;
public static final int termRefs__ON__silver_definition_concrete_syntax_ast_prodLayout = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_prodLayout++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_Regex = silver.definition.regex.Init.count_syn__ON__Regex++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexSeq = silver.definition.regex.Init.count_syn__ON__RegexSeq++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexRepetition = silver.definition.regex.Init.count_syn__ON__RegexRepetition++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexItem = silver.definition.regex.Init.count_syn__ON__RegexItem++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSet = silver.definition.regex.Init.count_syn__ON__RegexCharSet++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexCharSetItem = silver.definition.regex.Init.count_syn__ON__RegexCharSetItem++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_regex_RegexChar = silver.definition.regex.Init.count_syn__ON__RegexChar++;
public static final int silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_Regex = silver.definition.regex.Init.count_syn__ON__Regex++;
public static final int silver_definition_concrete_syntax_ast_unwrappedXML__ON__silver_definition_regex_RegexSeq = silver.definition.regex.Init.count_syn__ON__RegexSeq++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxRoot = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxRoot++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxRoot = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxRoot++;
public static final int silver_definition_env_unparse__ON__silver_definition_concrete_syntax_ast_SyntaxRoot = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxRoot++;
public static final int s2__ON__silver_definition_concrete_syntax_ast_cstRoot = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_cstRoot++;
public static final int startFound__ON__silver_definition_concrete_syntax_ast_cstRoot = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_cstRoot++;
public static final int univLayout__ON__silver_definition_concrete_syntax_ast_cstRoot = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_cstRoot++;
public static final int silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_inh__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_inh__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_inh__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_inh__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_inh__ON__Syntax++;
public static final int silver_definition_concrete_syntax_ast_cstDcls__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_cstEnv__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_cstErrors__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_cstProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_cstNTProds__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_cstNormalize__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_sortKey__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_allIgnoreTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_allMarkingTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_univLayout__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_xmlCopper__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_classDomContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_classSubContribs__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_unparses__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_containingGrammar__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_prefixesForTerminals__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_inh__ON__SyntaxDcl++;
public static final int pfx__ON__silver_definition_concrete_syntax_ast_syntaxTerminal = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxTerminal++;
public static final int assoc__ON__silver_definition_concrete_syntax_ast_convertAssocNXML = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_convertAssocNXML++;
public static final int lhsRef__ON__silver_definition_concrete_syntax_ast_syntaxProduction = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxProduction++;
public static final int rhsRefs__ON__silver_definition_concrete_syntax_ast_syntaxProduction = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxProduction++;
public static final int pfx__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_insertLocationAnnotation++;
public static final int trefs__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup = silver.definition.concrete_syntax.ast.Init.count_local__ON__silver_definition_concrete_syntax_ast_syntaxDisambiguationGroup++;
}
