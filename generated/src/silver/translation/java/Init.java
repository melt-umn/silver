package silver.translation.java;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.translation.java.driver.Init.initAllStatics();
		silver.translation.java.type.Init.initAllStatics();
		silver.translation.java.core.Init.initAllStatics();
		silver.translation.java.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.translation.java.driver.Init.init();
		silver.translation.java.type.Init.init();
		silver.translation.java.core.Init.init();
		silver.translation.java.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.translation.java.driver.Init.postInit();
		silver.translation.java.type.Init.postInit();
		silver.translation.java.core.Init.postInit();
		silver.translation.java.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

}
