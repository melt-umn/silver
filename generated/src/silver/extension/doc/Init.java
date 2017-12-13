package silver.extension.doc;

public class Init{

	private static boolean preInit = false;
	private static boolean init = false;
	private static boolean postInit = false;

	public static void initAllStatics(){
		if(preInit) return;
		preInit = true;

		core.monad.Init.initAllStatics();
		core.Init.initAllStatics();
		silver.extension.doc.driver.Init.initAllStatics();
		silver.extension.doc.core.Init.initAllStatics();
		silver.extension.doc.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		silver.extension.doc.driver.Init.init();
		silver.extension.doc.core.Init.init();
		silver.extension.doc.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		silver.extension.doc.driver.Init.postInit();
		silver.extension.doc.core.Init.postInit();
		silver.extension.doc.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

}
