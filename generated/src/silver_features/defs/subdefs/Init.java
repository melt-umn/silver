package silver_features.defs.subdefs;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver_features.defs.subdefs.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver_features.defs.subdefs.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver_features.defs.subdefs.Init.postInit();


		common.Decorator.applyDecorators(silver_features.defs.subdefs.NBar.decorators, PbarBar.class);
	}

	private static void setupInheritedAttributes(){
		silver_features.defs.subdefs.NBar.occurs_syn[silver_features.defs.subdefs.Init.silver_features_defs_subdefs_bs__ON__silver_features_defs_subdefs_Bar] = "silver_features:defs:subdefs:bs";
		silver_features.defs.subdefs.NBar.occurs_inh[silver_features.defs.subdefs.Init.silver_features_defs_subdefs_bi__ON__silver_features_defs_subdefs_Bar] = "silver_features:defs:subdefs:bi";
	}

	private static void initProductionAttributeDefinitions(){
		silver_features.defs.subdefs.PbarBar.initProductionAttributeDefinitions();
	}

	public static int count_inh__ON__Bar = 0;
	public static int count_syn__ON__Bar = 0;
	public static int count_local__ON__silver_features_defs_subdefs_barBar = 0;
public static final int silver_features_defs_subdefs_bs__ON__silver_features_defs_subdefs_Bar = silver_features.defs.subdefs.Init.count_syn__ON__Bar++;
public static final int silver_features_defs_subdefs_bi__ON__silver_features_defs_subdefs_Bar = silver_features.defs.subdefs.Init.count_inh__ON__Bar++;
}
