package terminalLexer;

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
		test.lexer_b.Init.initAllStatics();
		terminalLexer.Init.initAllStatics();

	}

	public static void init(){
		if(init) return;
		init = true;

		setupInheritedAttributes();

		core.monad.Init.init();
		core.Init.init();
		host.Init.init();
		test.lexer_b.Init.init();
		terminalLexer.Init.init();

		initProductionAttributeDefinitions();
	}

	public static void postInit(){
		if(postInit) return;
		postInit = true;

		core.monad.Init.postInit();
		core.Init.postInit();
		host.Init.postInit();
		test.lexer_b.Init.postInit();
		terminalLexer.Init.postInit();


	}

	private static void setupInheritedAttributes(){
	}

	private static void initProductionAttributeDefinitions(){
	}

	public static int count_local__ON__terminalLexer_extendedParser = 0;
}
