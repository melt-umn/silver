package host;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		host.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();


		common.Decorator.applyDecorators(host.NRoot.decorators, Proot_c.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		host.Proot_c.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__Root = 0;
	public static int count_syn__ON__Root = 0;
	public static int count_local__ON__host_root_c = 0;
	public static int count_local__ON__host_p = 0;
}
