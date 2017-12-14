package silver.util.raw.graph;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.util.raw.treeset.Init.initAllStatics();
		silver.util.raw.graph.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.raw.treeset.Init.init();
		silver.util.raw.graph.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.raw.treeset.Init.postInit();
		silver.util.raw.graph.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

	public static int count_local__ON__silver_util_raw_graph_empty = 0;
	public static int count_local__ON__silver_util_raw_graph_add = 0;
	public static int count_local__ON__silver_util_raw_graph_edgesFrom = 0;
	public static int count_local__ON__silver_util_raw_graph_contains = 0;
	public static int count_local__ON__silver_util_raw_graph_toList = 0;
	public static int count_local__ON__silver_util_raw_graph_transitiveClosure = 0;
	public static int count_local__ON__silver_util_raw_graph_repairClosure = 0;
}
