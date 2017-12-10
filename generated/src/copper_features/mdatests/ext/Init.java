package copper_features.mdatests.ext;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		copper_features.mdatests.host.Init.initAllStatics();
		copper_features.mdatests.ext.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		copper_features.mdatests.host.Init.init();
		copper_features.mdatests.ext.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		copper_features.mdatests.host.Init.postInit();
		copper_features.mdatests.ext.Init.postInit();


		common.Decorator.applyDecorators(copper_features.mdatests.host.NRoot.decorators, Pccc.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.mdatests.ext.Pccc.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__copper_features_mdatests_ext_ccc = 0;
}
