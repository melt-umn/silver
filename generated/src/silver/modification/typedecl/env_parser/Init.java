package silver.modification.typedecl.env_parser;

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
		silver.definition.core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.modification.typedecl.Init.initAllStatics();
		silver.modification.typedecl.env_parser.Init.initAllStatics();

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
		silver.definition.core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.modification.typedecl.Init.init();
		silver.modification.typedecl.env_parser.Init.init();

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
		silver.definition.core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.modification.typedecl.Init.postInit();
		silver.modification.typedecl.env_parser.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.env_parser.NIDclInfo.decorators, PaDclInfoType.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		silver.modification.typedecl.env_parser.PaDclInfoType.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_modification_typedecl_env_parser_aDclInfoType = 0;
}
