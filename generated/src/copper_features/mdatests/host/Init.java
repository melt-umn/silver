package copper_features.mdatests.host;

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

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		copper_features.mdatests.host.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		copper_features.mdatests.host.Init.postInit();


		common.Decorator.applyDecorators(copper_features.mdatests.host.NRoot.decorators, Paaa.class);
		common.Decorator.applyDecorators(copper_features.mdatests.host.NRoot.decorators, Pbbb.class);
		common.Decorator.applyDecorators(copper_features.mdatests.host.NRoot.decorators, Pnoroot.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		copper_features.mdatests.host.Paaa.initProductionAttributeDefinitions();
		copper_features.mdatests.host.Pbbb.initProductionAttributeDefinitions();
		copper_features.mdatests.host.Pnoroot.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__copper_features_mdatests_host_doParse = 0;
	public static int count_inh__ON__Root = 0;
	public static int count_syn__ON__Root = 0;
	public static int count_local__ON__copper_features_mdatests_host_aaa = 0;
	public static int count_local__ON__copper_features_mdatests_host_bbb = 0;
	public static int count_local__ON__copper_features_mdatests_host_noroot = 0;
}
