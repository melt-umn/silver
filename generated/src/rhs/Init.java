package rhs;

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
		rhs.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();
		test.term_b.Init.init();
		rhs.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();
		test.term_b.Init.postInit();
		rhs.Init.postInit();


		common.Decorator.applyDecorators(rhs.NA.decorators, PrhsMissing.class);
	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		rhs.PrhsMissing.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__A = 0;
	public static int count_syn__ON__A = 0;
	public static int count_local__ON__rhs_rhsMissing = 0;
	public static int count_local__ON__rhs_extendedParser = 0;
}
