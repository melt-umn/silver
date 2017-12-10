package missingLHS;

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
		test.nonterm_b.Init.initAllStatics();
		missingLHS.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();
		test.nonterm_b.Init.init();
		missingLHS.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();
		test.nonterm_b.Init.postInit();
		missingLHS.Init.postInit();


		common.Decorator.applyDecorators(test.nonterm_b.NB.decorators, PmissingLHS.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		missingLHS.PmissingLHS.initProductionAttributeDefinitions();
	}

	public static int count_local__ON__missingLHS_missingLHS = 0;
	public static int count_local__ON__missingLHS_extendedParser = 0;
}
