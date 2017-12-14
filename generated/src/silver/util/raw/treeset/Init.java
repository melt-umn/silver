package silver.util.raw.treeset;

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

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.util.raw.treeset.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.util.raw.treeset.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
		//FUNCTION equals Boolean ::= l::Set<a> r::Set<a> 
	}

	public static int count_local__ON__silver_util_raw_treeset_empty = 0;
	public static int count_local__ON__silver_util_raw_treeset_add = 0;
	public static int count_local__ON__silver_util_raw_treeset_toList = 0;
	public static int count_local__ON__silver_util_raw_treeset_union = 0;
	public static int count_local__ON__silver_util_raw_treeset_intersect = 0;
	public static int count_local__ON__silver_util_raw_treeset_difference = 0;
	public static int count_local__ON__silver_util_raw_treeset_contains = 0;
	public static int count_local__ON__silver_util_raw_treeset_containsAll = 0;
	public static int count_local__ON__silver_util_raw_treeset_subset = 0;
	public static int count_local__ON__silver_util_raw_treeset_equals = 0;
	public static int count_local__ON__silver_util_raw_treeset_isEmpty = 0;
	public static int count_local__ON__silver_util_raw_treeset_size = 0;
	public static int count_local__ON__silver_util_raw_treeset_filter = 0;
	public static int count_local__ON__silver_util_raw_treeset_removeAll = 0;
}
