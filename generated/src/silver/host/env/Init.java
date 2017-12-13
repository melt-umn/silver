package silver.host.env;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.definition.env.Init.initAllStatics();
		silver.definition.flow.env_parser.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.concrete_syntax.ast.env_parser.Init.initAllStatics();
		silver.definition.env.env_parser.Init.initAllStatics();
		silver.host.env.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.definition.env.Init.init();
		silver.definition.flow.env_parser.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.concrete_syntax.ast.env_parser.Init.init();
		silver.definition.env.env_parser.Init.init();
		silver.host.env.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.definition.env.Init.postInit();
		silver.definition.flow.env_parser.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.concrete_syntax.ast.env_parser.Init.postInit();
		silver.definition.env.env_parser.Init.postInit();
		silver.host.env.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

}
