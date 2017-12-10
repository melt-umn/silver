package startMissing;

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
		test.term_b.Init.initAllStatics();
		startMissing.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();
		test.nonterm_b.Init.init();
		test.term_b.Init.init();
		startMissing.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();
		test.nonterm_b.Init.postInit();
		test.term_b.Init.postInit();
		startMissing.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

	public static int count_local__ON__startMissing_p2 = 0;
	public static int count_local__ON__startMissing_extendedParser = 0;
}
