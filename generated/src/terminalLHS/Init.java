package terminalLHS;

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
		terminalLHS.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();
		terminalLHS.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();
		terminalLHS.Init.postInit();


		common.Decorator.applyDecorators(terminalLHS.NA_t.decorators, PterminalLHS.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		terminalLHS.PterminalLHS.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__terminalLHS_terminalLHS = 0;
	public static int count_local__ON__terminalLHS_extendedParser = 0;
}
