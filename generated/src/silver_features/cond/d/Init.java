package silver_features.cond.d;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		silver_features.cond.e.Init.initAllStatics();
		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver_features.cond.d.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		silver_features.cond.e.Init.init();
		core.monad.Init.init();
		core.Init.init();
		silver_features.cond.d.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		silver_features.cond.e.Init.postInit();
		core.monad.Init.postInit();
		core.Init.postInit();
		silver_features.cond.d.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

	public static final common.Thunk<Object> bVal = new common.Thunk<Object>(common.TopNode.singleton) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)5); } };
}
