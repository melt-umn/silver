package silver.modification.impide.env_parser;

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
		silver.definition.concrete_syntax.ast.env_parser.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.modification.impide.cstast.Init.initAllStatics();
		silver.modification.impide.Init.initAllStatics();
		silver.modification.impide.env_parser.Init.initAllStatics();

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
		silver.definition.concrete_syntax.ast.env_parser.Init.init();
		silver.definition.core.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.definition.env.Init.init();
		silver.modification.impide.cstast.Init.init();
		silver.modification.impide.Init.init();
		silver.modification.impide.env_parser.Init.init();

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
		silver.definition.concrete_syntax.ast.env_parser.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.modification.impide.cstast.Init.postInit();
		silver.modification.impide.Init.postInit();
		silver.modification.impide.env_parser.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIDclInfo.decorators, PaDclInfoFont.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier.decorators, PaLexerClassModifierFont.class);
		common.Decorator.applyDecorators(silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier.decorators, PaTerminalModifierFont.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.impide.env_parser.PaDclInfoFont.initProductionAttributeDefinitions();
		silver.modification.impide.env_parser.PaLexerClassModifierFont.initProductionAttributeDefinitions();
		silver.modification.impide.env_parser.PaTerminalModifierFont.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_impide_env_parser_aDclInfoFont = 0;
	public static int count_local__ON__silver_modification_impide_env_parser_aLexerClassModifierFont = 0;
	public static int count_local__ON__silver_modification_impide_env_parser_aTerminalModifierFont = 0;
}
