package silver.host;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		silver.definition.flow.env.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.analysis.typechecking.core.Init.initAllStatics();
		silver.definition.flow.syntax.Init.initAllStatics();
		silver.definition.regex.Init.initAllStatics();
		silver.definition.type.syntax.Init.initAllStatics();
		silver.definition.concrete_syntax.Init.initAllStatics();
		silver.definition.core.Init.initAllStatics();
		silver.host.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		silver.definition.flow.env.Init.init();
		core.Init.init();
		silver.analysis.typechecking.core.Init.init();
		silver.definition.flow.syntax.Init.init();
		silver.definition.regex.Init.init();
		silver.definition.type.syntax.Init.init();
		silver.definition.concrete_syntax.Init.init();
		silver.definition.core.Init.init();
		silver.host.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		silver.definition.flow.env.Init.postInit();
		core.Init.postInit();
		silver.analysis.typechecking.core.Init.postInit();
		silver.definition.flow.syntax.Init.postInit();
		silver.definition.regex.Init.postInit();
		silver.definition.type.syntax.Init.postInit();
		silver.definition.concrete_syntax.Init.postInit();
		silver.definition.core.Init.postInit();
		silver.host.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

}
