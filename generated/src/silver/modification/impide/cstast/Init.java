package silver.modification.impide.cstast;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.modification.impide.spec.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.type.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.Init.initAllStatics();
		silver.modification.impide.cstast.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.modification.impide.spec.Init.init();
		silver.definition.env.Init.init();
		silver.definition.type.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.concrete_syntax.ast.Init.init();
		silver.modification.impide.cstast.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.modification.impide.spec.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.type.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.concrete_syntax.ast.Init.postInit();
		silver.modification.impide.cstast.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.decorators, PlexerClassFont.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.decorators, PtermFont.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.NSyntaxDcl.decorators, PsyntaxFont.class);
	}

	private static void setupInheritedAttributes(){
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = "silver:modification:impide:cstast:fontAttr";
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifiers.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = "silver:modification:impide:cstast:fontAttr";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:modification:impide:cstast:fontAttr";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:modification:impide:cstast:fontAttr";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = "silver:modification:impide:cstast:fontAttrFromClass";
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifiers.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = "silver:modification:impide:cstast:fontAttrFromClass";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:modification:impide:cstast:fontList";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:modification:impide:cstast:fontList";
		silver.definition.concrete_syntax.ast.NSyntaxRoot.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = "silver:modification:impide:cstast:fontList";
		silver.definition.concrete_syntax.ast.NSyntax.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_Syntax] = "silver:modification:impide:cstast:termFontPairList";
		silver.definition.concrete_syntax.ast.NSyntaxDcl.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = "silver:modification:impide:cstast:termFontPairList";
		silver.definition.concrete_syntax.ast.NSyntaxRoot.occurs_syn[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = "silver:modification:impide:cstast:termFontPairList";
	}

	private static void initProductionAttributeDefinitions(){
		//ASPECT PRODUCTION consLexerClassMod top ::= h::SyntaxLexerClassModifier t::SyntaxLexerClassModifiers 
		// top.fontAttr = if h.fontAttr != "" then h.fontAttr else t.fontAttr
		silver.definition.concrete_syntax.ast.PconsLexerClassMod.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_h).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_h).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier)) : ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsLexerClassMod.i_t).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers))); } };
		//ASPECT PRODUCTION nilLexerClassMod top ::= 
		// top.fontAttr = ""
		silver.definition.concrete_syntax.ast.PnilLexerClassMod.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		//ASPECT DEFAULT PRODUCTION for SyntaxLexerClassModifier
		// top.fontAttr = ""
		silver.definition.concrete_syntax.ast.NSyntaxLexerClassModifier.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		silver.modification.impide.cstast.PlexerClassFont.initProductionAttributeDefinitions();
		//ASPECT PRODUCTION consTerminalMod top ::= h::SyntaxTerminalModifier t::SyntaxTerminalModifiers 
		// top.fontAttr = if h.fontAttr != "" then h.fontAttr else t.fontAttr
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)) : ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		// top.fontAttrFromClass = if h.fontAttrFromClass != "" then h.fontAttrFromClass else t.fontAttrFromClass
		silver.definition.concrete_syntax.ast.PconsTerminalMod.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (!((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_h).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier)) : ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PconsTerminalMod.i_t).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } };
		//ASPECT PRODUCTION nilTerminalMod top ::= 
		// top.fontAttr = ""
		silver.definition.concrete_syntax.ast.PnilTerminalMod.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.fontAttrFromClass = ""
		silver.definition.concrete_syntax.ast.PnilTerminalMod.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		//ASPECT DEFAULT PRODUCTION for SyntaxTerminalModifier
		// top.fontAttr = ""
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.fontAttrFromClass = ""
		silver.definition.concrete_syntax.ast.NSyntaxTerminalModifier.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		//ASPECT PRODUCTION termClasses top ::= cls::[String] 
		// top.fontAttrFromClass = dumbExtractFont(clsRefs)
		silver.definition.concrete_syntax.ast.PtermClasses.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.modification.impide.cstast.PdumbExtractFont.invoke(context.localAsIsLazy(silver.definition.concrete_syntax.ast.Init.clsRefs__ON__silver_definition_concrete_syntax_ast_termClasses))); } };
		//FUNCTION dumbExtractFont String ::= d::[Decorated SyntaxDcl] 
		silver.modification.impide.cstast.PtermFont.initProductionAttributeDefinitions();
		//ASPECT DEFAULT PRODUCTION for SyntaxRoot
		// top.fontList = error("This should only ever be demanded from cstRoot.")
		silver.definition.concrete_syntax.ast.NSyntaxRoot.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("This should only ever be demanded from cstRoot.")))); } };
		// top.termFontPairList = error("This should only ever be demanded from cstRoot.")
		silver.definition.concrete_syntax.ast.NSyntaxRoot.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("This should only ever be demanded from cstRoot.")))); } };
		//ASPECT PRODUCTION cstRoot top ::= parsername::String startnt::String s::Syntax terminalPrefixes::[Pair<String String>] 
		// top.fontList = s2.fontList
		silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_Syntax)); } };
		// top.termFontPairList = s2.termFontPairList
		silver.definition.concrete_syntax.ast.PcstRoot.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.concrete_syntax.ast.Init.s2__ON__silver_definition_concrete_syntax_ast_cstRoot).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_Syntax)); } };
		//ASPECT PRODUCTION nilSyntax top ::= 
		// top.fontList = []
		silver.definition.concrete_syntax.ast.PnilSyntax.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.termFontPairList = []
		silver.definition.concrete_syntax.ast.PnilSyntax.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION consSyntax top ::= s1::SyntaxDcl s2::Syntax 
		// top.fontList = s1.fontList ++ s2.fontList
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		// top.termFontPairList = s1.termFontPairList ++ s2.termFontPairList
		silver.definition.concrete_syntax.ast.PconsSyntax.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_Syntax] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s1, silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl), context.childDecoratedSynthesizedLazy(silver.definition.concrete_syntax.ast.PconsSyntax.i_s2, silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_Syntax))); } };
		//ASPECT DEFAULT PRODUCTION for SyntaxDcl
		// top.fontList = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.termFontPairList = []
		silver.definition.concrete_syntax.ast.NSyntaxDcl.defaultSynthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		//ASPECT PRODUCTION syntaxNonterminal top ::= t::Type subdcls::Syntax 
		//ASPECT PRODUCTION syntaxTerminal top ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers 
		// top.termFontPairList = [ pair(n, if modifiers.fontAttr == "" then modifiers.fontAttrFromClass else modifiers.fontAttr) ]
		silver.definition.concrete_syntax.ast.PsyntaxTerminal.synthesizedAttributes[silver.modification.impide.cstast.Init.silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)).equals((new common.StringCatter(""))) ? ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers)) : ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.ast.PsyntaxTerminal.i_modifiers).synthesized(silver.modification.impide.cstast.Init.silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
		//ASPECT PRODUCTION syntaxProduction top ::= ns::NamedSignature modifiers::SyntaxProductionModifiers 
		//ASPECT PRODUCTION syntaxLexerClass top ::= n::String modifiers::SyntaxLexerClassModifiers 
		//ASPECT PRODUCTION syntaxParserAttribute top ::= n::String ty::Type acode::String 
		//ASPECT PRODUCTION syntaxDisambiguationGroup top ::= n::String terms::[String] acode::String 
		silver.modification.impide.cstast.PsyntaxFont.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_impide_cstast_lexerClassFont = 0;
	public static int count_local__ON__silver_modification_impide_cstast_dumbExtractFont = 0;
	public static int count_local__ON__silver_modification_impide_cstast_termFont = 0;
	public static int count_local__ON__silver_modification_impide_cstast_syntaxFont = 0;
public static final int silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifier++;
public static final int silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxLexerClassModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxLexerClassModifiers++;
public static final int silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_modification_impide_cstast_fontAttr__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifier = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifier++;
public static final int silver_modification_impide_cstast_fontAttrFromClass__ON__silver_definition_concrete_syntax_ast_SyntaxTerminalModifiers = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxTerminalModifiers++;
public static final int silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_modification_impide_cstast_fontList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxRoot++;
public static final int silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_Syntax = silver.definition.concrete_syntax.ast.Init.count_syn__ON__Syntax++;
public static final int silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxDcl = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxDcl++;
public static final int silver_modification_impide_cstast_termFontPairList__ON__silver_definition_concrete_syntax_ast_SyntaxRoot = silver.definition.concrete_syntax.ast.Init.count_syn__ON__SyntaxRoot++;
}
