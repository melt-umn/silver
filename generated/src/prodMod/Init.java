package prodMod;

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
		test.term_b.Init.initAllStatics();
		prodMod.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();
		test.term_b.Init.init();
		prodMod.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();
		test.term_b.Init.postInit();
		prodMod.Init.postInit();


		common.Decorator.applyDecorators(prodMod.NNT.decorators, PmissingModifiers.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		prodMod.PmissingModifiers.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__NT = 0;
	public static int count_syn__ON__NT = 0;
	public static int count_local__ON__prodMod_missingModifiers = 0;
	public static int count_local__ON__prodMod_extendedParser = 0;
}
