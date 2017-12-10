package test.nonterm_b;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		test.nonterm_b.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		test.nonterm_b.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		test.nonterm_b.Init.postInit();


		common.Decorator.applyDecorators(test.nonterm_b.NB.decorators, Pnonterm_b_cnc.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		test.nonterm_b.Pnonterm_b_cnc.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__B = 0;
	public static int count_syn__ON__B = 0;
	public static int count_local__ON__test_nonterm_b_nonterm_b_cnc = 0;
}
