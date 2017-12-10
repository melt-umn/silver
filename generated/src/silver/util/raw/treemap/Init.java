package silver.util.raw.treemap;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.raw.treemap.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.raw.treemap.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.raw.treemap.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

	public static int count_local__ON__silver_util_raw_treemap_empty = 0;
	public static int count_local__ON__silver_util_raw_treemap_add = 0;
	public static int count_local__ON__silver_util_raw_treemap_lookup = 0;
	public static int count_local__ON__silver_util_raw_treemap_toList = 0;
	public static int count_local__ON__silver_util_raw_treemap_update = 0;
}
