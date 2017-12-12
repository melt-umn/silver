package silver.definition.concrete_syntax.ast.env_parser;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver.definition.flow.env_parser.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.driver.util.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.env_parser.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver.definition.flow.env_parser.Init.init();
		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		silver.analysis.typechecking.core.Init.init();
		core.Init.init();
		silver.driver.util.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.definition.env.Init.init();
		silver.definition.concrete_syntax.ast.env_parser.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver.definition.flow.env_parser.Init.postInit();
		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		core.Init.postInit();
		silver.driver.util.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.concrete_syntax.ast.env_parser.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIRootPart.decorators, PaRootSyntax.class);
		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIRootPart.decorators, PaRootParsers.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIParsers.decorators, PaParsersNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIParsers.decorators, PaParsersSome.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.decorators, PaParsersOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.decorators, PaParsersCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIParser.decorators, PaParser.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntax.decorators, PaSyntaxNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntax.decorators, PaSyntaxSome.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.decorators, PaSyntaxOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.decorators, PaSyntaxCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators, PaSyntaxNt.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators, PaSyntaxTerm.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators, PaSyntaxProd.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators, PaSyntaxLclass.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators, PaSyntaxPattr.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators, PaSyntaxDisambig.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.decorators, PaTerminalModifiersNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.decorators, PaTerminalModifiersOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.decorators, PaTerminalModifiersInnerOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.decorators, PaTerminalModifierInnersCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierIgnore.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierMarking.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierPrec.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierAssoc.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierClasses.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierSubmits.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierDominates.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierAcode.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers.decorators, PaProductionModifiersNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers.decorators, PaProductionModifiersOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner.decorators, PaProductionModifiersInnerOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner.decorators, PaProductionModifierInnersCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.decorators, PaProductionModifierPrecedence.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.decorators, PaProductionModifierOperator.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.decorators, PaProductionModifierAcode.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.decorators, PaProductionModifierLayout.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers.decorators, PaLexerClassModifiersNone.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers.decorators, PaLexerClassModifiersOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner.decorators, PaLexerClassModifiersInnerOne.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner.decorators, PaLexerClassModifierInnersCons.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier.decorators, PaLexerClassModifierSubmits.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier.decorators, PaLexerClassModifierDominates.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.env.env_parser.NIRoot.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRoot] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.env.env_parser.NIRootPart.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRootPart] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.env.env_parser.NIRoot.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRoot] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.env.env_parser.NIRootPart.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRootPart] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.concrete_syntax.ast.env_parser.NIParsers.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParsers] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.concrete_syntax.ast.env_parser.NIParsers.occurs_inh[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_IParsers] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.ast.env_parser.NIParsers.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParsersInner] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.occurs_inh[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_IParsersInner] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.ast.env_parser.NIParsersInner.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.ast.env_parser.NIParser.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParser] = "silver:definition:concrete_syntax:parserSpecs";
		silver.definition.concrete_syntax.ast.env_parser.NIParser.occurs_inh[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_IParser] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.ast.env_parser.NIParser.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.ast.env_parser.NISyntax.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntax] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.concrete_syntax.ast.env_parser.NISyntax.occurs_inh[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntax] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.ast.env_parser.NISyntax.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxInner] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.occurs_inh[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxInner] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl] = "silver:definition:concrete_syntax:syntaxAst";
		silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.occurs_inh[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl] = "silver:definition:core:grammarName";
		silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl.decorators.add(silver.definition.core.DgrammarName.singleton);
		silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiers] = "silver:definition:concrete_syntax:ast:env_parser:terminalModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiersInner] = "silver:definition:concrete_syntax:ast:env_parser:terminalModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifier] = "silver:definition:concrete_syntax:ast:env_parser:terminalModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifiers] = "silver:definition:concrete_syntax:ast:env_parser:productionModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifiersInner] = "silver:definition:concrete_syntax:ast:env_parser:productionModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifier] = "silver:definition:concrete_syntax:ast:env_parser:productionModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifiers] = "silver:definition:concrete_syntax:ast:env_parser:lexerClassModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifiersInner] = "silver:definition:concrete_syntax:ast:env_parser:lexerClassModifiers";
		silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier.occurs_syn[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifier] = "silver:definition:concrete_syntax:ast:env_parser:lexerClassModifiers";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION interfaceRootSpec top ::= p::IRoot _ 
		// top.syntaxAst = p.syntaxAst
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRoot)); } };
		// top.parserSpecs = p.parserSpecs
		silver.driver.util.PinterfaceRootSpec.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_driver_util_RootSpec] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.driver.util.PinterfaceRootSpec.i_p).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRoot)); } };
		//ASPECT PRODUCTION aRoot1 top ::= r::IRootPart 
		// top.syntaxAst = r.syntaxAst
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRootPart)); } };
		// top.parserSpecs = r.parserSpecs
		silver.definition.env.env_parser.PaRoot1.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.env.env_parser.PaRoot1.i_r).synthesized(silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRootPart)); } };
		//ASPECT PRODUCTION aRoot2 top ::= r1::IRootPart r2::IRoot 
		// top.syntaxAst = r1.syntaxAst ++ r2.syntaxAst
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRoot))); } };
		// top.parserSpecs = r1.parserSpecs ++ r2.parserSpecs
		silver.definition.env.env_parser.PaRoot2.synthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r1, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRootPart), context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaRoot2.i_r2, silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRoot))); } };
		//ASPECT DEFAULT PRODUCTION for IRootPart
		// top.syntaxAst = []
		silver.definition.env.env_parser.NIRootPart.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRootPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.parserSpecs = []
		silver.definition.env.env_parser.NIRootPart.defaultSynthesizedAttributes[silver.definition.concrete_syntax.ast.env_parser.Init.silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRootPart] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		silver.definition.concrete_syntax.ast.env_parser.PaRootSyntax.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaRootParsers.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaParsersNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaParsersSome.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaParsersOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaParsersCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaParser.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxSome.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxNt.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxLclass.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxPattr.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersInnerOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierInnersCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierIgnore.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierMarking.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierPrec.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierAssoc.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierClasses.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierSubmits.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierDominates.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierAcode.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersInnerOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierInnersCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierPrecedence.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierOperator.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierAcode.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierLayout.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersNone.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersInnerOne.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierInnersCons.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierSubmits.initProductionAttributeDefinitions();
		silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierDominates.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aRootSyntax = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aRootParsers = 0;
	public static int count_inh__ON__IParsers = 0;
	public static int count_syn__ON__IParsers = 0;
	public static int count_inh__ON__IParsersInner = 0;
	public static int count_syn__ON__IParsersInner = 0;
	public static int count_inh__ON__IParser = 0;
	public static int count_syn__ON__IParser = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParsersNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParsersSome = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParsersOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParsersCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aParser = 0;
	public static int count_inh__ON__ISyntax = 0;
	public static int count_syn__ON__ISyntax = 0;
	public static int count_inh__ON__ISyntaxInner = 0;
	public static int count_syn__ON__ISyntaxInner = 0;
	public static int count_inh__ON__ISyntaxDcl = 0;
	public static int count_syn__ON__ISyntaxDcl = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxSome = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxNt = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxTerm = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxProd = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxLclass = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxPattr = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aSyntaxDisambig = 0;
	public static int count_inh__ON__ITerminalModifiers = 0;
	public static int count_syn__ON__ITerminalModifiers = 0;
	public static int count_inh__ON__ITerminalModifiersInner = 0;
	public static int count_syn__ON__ITerminalModifiersInner = 0;
	public static int count_inh__ON__ITerminalModifier = 0;
	public static int count_syn__ON__ITerminalModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifiersNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifiersOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifiersInnerOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierInnersCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierIgnore = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierMarking = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierPrec = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierAssoc = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierClasses = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierSubmits = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierDominates = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aTerminalModifierAcode = 0;
	public static int count_inh__ON__IProductionModifiers = 0;
	public static int count_syn__ON__IProductionModifiers = 0;
	public static int count_inh__ON__IProductionModifiersInner = 0;
	public static int count_syn__ON__IProductionModifiersInner = 0;
	public static int count_inh__ON__IProductionModifier = 0;
	public static int count_syn__ON__IProductionModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifiersNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifiersOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifiersInnerOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifierInnersCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifierPrecedence = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifierOperator = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifierAcode = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aProductionModifierLayout = 0;
	public static int count_inh__ON__ILexerClassModifiers = 0;
	public static int count_syn__ON__ILexerClassModifiers = 0;
	public static int count_inh__ON__ILexerClassModifiersInner = 0;
	public static int count_syn__ON__ILexerClassModifiersInner = 0;
	public static int count_inh__ON__ILexerClassModifier = 0;
	public static int count_syn__ON__ILexerClassModifier = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifiersNone = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifiersOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifiersInnerOne = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifierInnersCons = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifierSubmits = 0;
	public static int count_local__ON__silver_definition_concrete_syntax_ast_env_parser_aLexerClassModifierDominates = 0;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRoot = silver.definition.env.env_parser.Init.count_syn__ON__IRoot++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_env_env_parser_IRootPart = silver.definition.env.env_parser.Init.count_syn__ON__IRootPart++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRoot = silver.definition.env.env_parser.Init.count_syn__ON__IRoot++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_env_env_parser_IRootPart = silver.definition.env.env_parser.Init.count_syn__ON__IRootPart++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParsers = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__IParsers++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_IParsers = silver.definition.concrete_syntax.ast.env_parser.Init.count_inh__ON__IParsers++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParsersInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__IParsersInner++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_IParsersInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_inh__ON__IParsersInner++;
public static final int silver_definition_concrete_syntax_parserSpecs__ON__silver_definition_concrete_syntax_ast_env_parser_IParser = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__IParser++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_IParser = silver.definition.concrete_syntax.ast.env_parser.Init.count_inh__ON__IParser++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntax = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ISyntax++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntax = silver.definition.concrete_syntax.ast.env_parser.Init.count_inh__ON__ISyntax++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ISyntaxInner++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_inh__ON__ISyntaxInner++;
public static final int silver_definition_concrete_syntax_syntaxAst__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ISyntaxDcl++;
public static final int silver_definition_core_grammarName__ON__silver_definition_concrete_syntax_ast_env_parser_ISyntaxDcl = silver.definition.concrete_syntax.ast.env_parser.Init.count_inh__ON__ISyntaxDcl++;
public static final int silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiers = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ITerminalModifiers++;
public static final int silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifiersInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ITerminalModifiersInner++;
public static final int silver_definition_concrete_syntax_ast_env_parser_terminalModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ITerminalModifier = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ITerminalModifier++;
public static final int silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifiers = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__IProductionModifiers++;
public static final int silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifiersInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__IProductionModifiersInner++;
public static final int silver_definition_concrete_syntax_ast_env_parser_productionModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_IProductionModifier = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__IProductionModifier++;
public static final int silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifiers = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ILexerClassModifiers++;
public static final int silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifiersInner = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ILexerClassModifiersInner++;
public static final int silver_definition_concrete_syntax_ast_env_parser_lexerClassModifiers__ON__silver_definition_concrete_syntax_ast_env_parser_ILexerClassModifier = silver.definition.concrete_syntax.ast.env_parser.Init.count_syn__ON__ILexerClassModifier++;
}
