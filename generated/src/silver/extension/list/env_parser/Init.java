package silver.extension.list.env_parser;

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
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.extension.list.Init.initAllStatics();
		silver.extension.list.env_parser.Init.initAllStatics();

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
		silver.definition.env.env_parser.Init.init();
		silver.definition.env.Init.init();
		silver.extension.list.Init.init();
		silver.extension.list.env_parser.Init.init();

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
		silver.definition.env.env_parser.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.extension.list.Init.postInit();
		silver.extension.list.env_parser.Init.postInit();


		common.Decorator.applyDecorators(silver.definition.env.env_parser.NITypeRep.decorators, PaTypeRepList.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		silver.extension.list.env_parser.PaTypeRepList.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__silver_extension_list_env_parser_aTypeRepList = 0;
}
